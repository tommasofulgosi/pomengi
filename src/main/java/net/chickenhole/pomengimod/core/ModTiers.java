package net.chickenhole.pomengimod.core;

import net.chickenhole.pomengimod.config.Config;
import net.chickenhole.pomengimod.core.registry.ModItems;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

// Materiale raro più forte della netherite. Tutti i valori leggono dal config,
// così danno e durabilità si ritoccano senza toccare il codice (serve riavvio).
public class ModTiers {
    public static final Tier POMENGITE = new Tier() {
        @Override
        public int getUses() {
            return Config.POMENGITE_SWORD_DURABILITY.get();
        }

        @Override
        public float getSpeed() {
            return 9.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return Config.POMENGITE_SWORD_DAMAGE_BONUS.get();
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return Config.POMENGITE_SWORD_ENCHANTABILITY.get();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.BLOCCO_DI_POMENGITE_ITEM.get());
        }
    };

    private ModTiers() {
    }
}