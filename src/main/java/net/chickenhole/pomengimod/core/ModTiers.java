package net.chickenhole.pomengimod.core;

import net.chickenhole.pomengimod.core.registry.ModItems;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

// Materiale raro più forte della netherite.
//
// ATTENZIONE: questi valori NON possono stare nel config, perché il config
// viene caricato DOPO le registrazioni. Per ritoccarli modifica qui sotto
// e riavvia. Nel config restano solo i parametri degli effetti, che si
// leggono a mondo avviato (anche senza riavvio).
public class ModTiers {
    public static final int DAMAGE_BASE = 4;
    public static final int DAMAGE_BONUS = 5; // totale spada = base + bonus = 9 (netherite = 8)
    public static final int DURABILITY = 2500;
    public static final float ATTACK_SPEED = -2.4F;
    public static final int ENCHANTABILITY = 18;

    public static final Tier POMENGITE = new Tier() {
        @Override
        public int getUses() {
            return DURABILITY;
        }

        @Override
        public float getSpeed() {
            return 9.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return DAMAGE_BONUS;
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_NETHERITE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return ENCHANTABILITY;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.BLOCCO_DI_POMENGITE_ITEM.get());
        }
    };

    private ModTiers() {
    }
}