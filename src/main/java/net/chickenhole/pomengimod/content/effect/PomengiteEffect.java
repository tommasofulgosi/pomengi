package net.chickenhole.pomengimod.content.effect;

import net.chickenhole.pomengimod.config.Config;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

// Effetto pomengite: danno nel tempo lento ma inesorabile.
// Intervallo e danno si regolano dal config, anche a mondo avviato.
public class PomengiteEffect extends MobEffect {
    public PomengiteEffect() {
        super(MobEffectCategory.HARMFUL, 0x5A1A6E);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            // 1/8 dei cuori massimi al minuto: cuori = vita max / 2, quindi danno = vita max / 16.
            float damage = entity.getMaxHealth() / 16.0F
                    * Config.POMENGITE_DOT_DAMAGE.get().floatValue()
                    * (amplifier + 1);
            entity.hurt(entity.damageSources().magic(), damage);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = Config.POMENGITE_DOT_INTERVAL.get();
        return interval > 0 && duration % interval == 0;
    }
}