package net.chickenhole.pomengimod.content.item;

import net.chickenhole.pomengimod.config.Config;
import net.chickenhole.pomengimod.core.registry.ModEffects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

// Spada di minerale pomengiato: oltre al danno applica l'effetto pomengite
// (danno nel tempo) più sfortuna e debolezza. Durate dal config.
public class PomengiteSwordItem extends SwordItem {
    public PomengiteSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            int duration = Config.POMENGITE_EFFECT_DURATION.get();
            if (duration > 0) {
                target.addEffect(new MobEffectInstance(ModEffects.POMENGITE, duration, 0));
                target.addEffect(new MobEffectInstance(MobEffects.UNLUCK, duration, 0));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, 0));
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}