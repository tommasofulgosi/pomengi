package net.chickenhole.pomengimod.config;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ModConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    public static final ModConfigSpec.IntValue POMENGITE_SWORD_DAMAGE = BUILDER
            .comment("Base damage of the pomengiated mineral sword (tier bonus is added on top)")
            .defineInRange("pomengiteSwordDamage", 4, 0, 100);

    public static final ModConfigSpec.IntValue POMENGITE_SWORD_DAMAGE_BONUS = BUILDER
            .comment("Tier damage bonus of the pomengiated mineral sword")
            .defineInRange("pomengiteSwordDamageBonus", 5, 0, 100);

    public static final ModConfigSpec.IntValue POMENGITE_SWORD_DURABILITY = BUILDER
            .comment("Durability of the pomengiated mineral sword")
            .defineInRange("pomengiteSwordDurability", 2500, 1, 100000);

    public static final ModConfigSpec.IntValue POMENGITE_SWORD_ENCHANTABILITY = BUILDER
            .comment("Enchantability of the pomengiated mineral sword")
            .defineInRange("pomengiteSwordEnchantability", 18, 0, 100);

    public static final ModConfigSpec.IntValue POMENGITE_EFFECT_DURATION = BUILDER
            .comment("Duration in ticks of the pomengite effect bundle (unluck, weakness, damage over time). 0 disables it.")
            .defineInRange("pomengiteEffectDuration", 3600, 0, 72000);

    public static final ModConfigSpec.IntValue POMENGITE_DOT_INTERVAL = BUILDER
            .comment("Ticks between damage ticks of the pomengite effect (1200 ticks = half a heart per minute)")
            .defineInRange("pomengiteDotInterval", 1200, 20, 72000);

    public static final ModConfigSpec.DoubleValue POMENGITE_DOT_DAMAGE = BUILDER
            .comment("Damage (half-hearts) dealt by each damage tick of the pomengite effect")
            .defineInRange("pomengiteDotDamage", 1.0, 0.5, 100.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}