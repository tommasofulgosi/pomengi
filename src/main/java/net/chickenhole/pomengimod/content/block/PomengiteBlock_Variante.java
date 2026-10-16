package net.chickenhole.pomengimod.content.block;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.chickenhole.pomengimod.config.Config;
import net.chickenhole.pomengimod.core.registry.ModEffects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PomengiteBlock_Variante extends Block {
    // Cooldown per entita: finche non scade, l'effetto non viene riapplicato.
    private static final int EFFECT_COOLDOWN = 100; // 5 secondi (20 tick = 1 secondo)
    private static final Map<UUID, Long> LAST_EFFECT = new HashMap<>();

    public PomengiteBlock_Variante(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        // Eseguiamo la logica solo sul lato server per evitare conflitti multiplayer/client
        if (level.isClientSide || !(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        // Probabilita del 10% a ogni passo di applicare l'effetto di Pomengite
        if (level.random.nextFloat() >= 0.10F) {
            return;
        }
        long last = LAST_EFFECT.getOrDefault(livingEntity.getUUID(), -(long) EFFECT_COOLDOWN);
        if (level.getGameTime() - last < EFFECT_COOLDOWN) {
            return;
        }
        LAST_EFFECT.put(livingEntity.getUUID(), level.getGameTime());
        int duration = Config.POMENGITE_EFFECT_DURATION.get();
        if (duration > 0) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.POMENGITE, duration, 0));
        }
    }
}