package net.chickenhole.pomengimod.content.block;

import net.chickenhole.pomengimod.core.registry.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.RandomSource;



public class PomengiteBlock_Variante extends Block {
    // Cooldown per evitare che l'effetto venga applicato di continuo a ogni singolo tick
    private static final int EFFECT_COOLDOWN = 100; // 5 secondi (20 tick = 1 secondo)
    private int cooldownTimer = 0;

    public PomengiteBlock_Variante(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // Eseguiamo la logica solo sul lato server per evitare conflitti multiplayer/client
        if (!level.isClientSide() && entity instanceof LivingEntity livingEntity) {
            if (cooldownTimer <= 0) {
                // Probabilità del 10% a ogni passo di applicare l'effetto di Pomengite
                if (level.random.nextFloat() < 0.10f) {
                    // Applica l'effetto Pomengite per 200 tick (10 secondi) con ampiezza 0
                    livingEntity.addEffect(new MobEffectInstance(ModEffects.POMENGITE, 200, 0));

                    // Riattiva il cooldown
                    cooldownTimer = EFFECT_COOLDOWN;
                }
            } else {
                cooldownTimer--;
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
