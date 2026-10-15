package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, PomengiMod.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> WHISPER_POMENGI = SOUND_EVENTS.register(
            "pomengi", () -> SoundEvent.createVariableRangeEvent(
                    ResourceLocation.fromNamespaceAndPath(PomengiMod.MODID, "pomengi")));

    private ModSounds() {
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}