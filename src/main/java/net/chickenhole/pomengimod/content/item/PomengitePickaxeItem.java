package net.chickenhole.pomengimod.content.item;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.chickenhole.pomengimod.config.Config;
import net.chickenhole.pomengimod.core.registry.ModEffects;
import net.chickenhole.pomengimod.core.registry.ModSounds;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

// Piccone di minerale pomengiato con abilita "risonanza fratturante":
// se colpisce un mob affetto da pomengite, gliela rimuove e gli infligge
// subito il danno di un minuto di pomengite (1/8 dei cuori massimi).
// L'abilita ha 1 minuto di cooldown per player.
public class PomengitePickaxeItem extends PickaxeItem {
    private static final int RESONANCE_COOLDOWN_TICKS = 1200; // 1 minuto
    private static final Map<UUID, Long> LAST_RESONANCE = new HashMap<>();

    public PomengitePickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide
                && attacker instanceof ServerPlayer player
                && target.hasEffect(ModEffects.POMENGITE)) {
            long elapsed = attacker.level().getGameTime()
                    - LAST_RESONANCE.getOrDefault(player.getUUID(), -(long) RESONANCE_COOLDOWN_TICKS);
            if (elapsed >= RESONANCE_COOLDOWN_TICKS) {
                LAST_RESONANCE.put(player.getUUID(), attacker.level().getGameTime());
                target.removeEffect(ModEffects.POMENGITE);
                float burst = target.getMaxHealth() / 16.0F
                        * Config.POMENGITE_DOT_DAMAGE.get().floatValue();
                target.hurt(attacker.damageSources().playerAttack(player), burst);
                attacker.level().playSound(null, target.blockPosition(),
                        ModSounds.WHISPER_POMENGI.value(), SoundSource.PLAYERS, 1.0F, 1.5F);
                // Spazzata bianca sull'item + messaggio: il cooldown si vede.
                player.getCooldowns().addCooldown(stack.getItem(), RESONANCE_COOLDOWN_TICKS);
                player.displayClientMessage(
                        Component.translatable("message.pomengimod.resonance"), true);
            } else {
                long secondsLeft = (RESONANCE_COOLDOWN_TICKS - elapsed + 19) / 20;
                player.displayClientMessage(
                        Component.translatable("message.pomengimod.resonance_cooldown", secondsLeft), true);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}