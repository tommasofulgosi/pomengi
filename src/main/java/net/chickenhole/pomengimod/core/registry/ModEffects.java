package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;
import net.chickenhole.pomengimod.content.effect.PomengiteEffect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, PomengiMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> POMENGITE = MOB_EFFECTS.register(
            "pomengite", PomengiteEffect::new);

    private ModEffects() {
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}