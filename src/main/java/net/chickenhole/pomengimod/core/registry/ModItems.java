package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;
import net.chickenhole.pomengimod.content.item.MineralePomengiato;
import net.chickenhole.pomengimod.content.item.PomengiteAxeItem;
import net.chickenhole.pomengimod.content.item.PomengiteSwordItem;
import net.chickenhole.pomengimod.content.item.PomengitePickaxeItem;
import net.chickenhole.pomengimod.core.ModTiers;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PomengiMod.MODID);

    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "example_block", ModBlocks.EXAMPLE_BLOCK);

    public static final DeferredItem<BlockItem> BLOCCO_DI_POMENGITE_ITEM = ITEMS.registerSimpleBlockItem(
            "blocco_di_pomengite", ModBlocks.BLOCCO_DI_POMENGITE);

    public static final DeferredItem<BlockItem> BLOCCO_DI_POMENGITE_VARIANTE_ITEM = ITEMS.registerSimpleBlockItem(
            "blocco_di_pomengite_variante", ModBlocks.BLOCCO_DI_POMENGITE_VARIANTE);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(
            new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));


    public static final DeferredItem<MineralePomengiato> MINERALE_POMENGIATO = ITEMS.register(
            "minerale_pomengiato",
            () -> new MineralePomengiato(new Item.Properties())
    );

    public static final DeferredItem<PomengiteAxeItem> POMENGITE_AXE = ITEMS.register(
            "pomengite_axe",
            () -> new PomengiteAxeItem(new Item.Properties()
                    .durability(ModTiers.DURABILITY)
                    .attributes(AxeItem.createAttributes(
                            ModTiers.POMENGITE, ModTiers.AXE_DAMAGE, ModTiers.AXE_SPEED)))
    );

    public static final DeferredItem<SwordItem> SPADA_DI_MINERALE_POMENGIATO = ITEMS.register(
            "spada_di_minerale_pomengiato",
            () -> new PomengiteSwordItem(ModTiers.POMENGITE, new Item.Properties()
                    .durability(ModTiers.DURABILITY)
                    .attributes(SwordItem.createAttributes(
                            ModTiers.POMENGITE, ModTiers.DAMAGE_BASE, ModTiers.ATTACK_SPEED))
                    .fireResistant()));

    public static final DeferredItem<PickaxeItem> PICCONE_DI_MINERALE_POMENGIATO = ITEMS.register(
            "piccone_di_minerale_pomengiato",
            () -> new PomengitePickaxeItem(ModTiers.POMENGITE, new Item.Properties()
                    .durability(ModTiers.DURABILITY)
                    .attributes(PickaxeItem.createAttributes(
                            ModTiers.POMENGITE, ModTiers.PICKAXE_DAMAGE_BASE, ModTiers.PICKAXE_SPEED))
                    .fireResistant()));



    public static final DeferredItem<BlockItem> POMENGITE_ORE_ITEM = ITEMS.registerSimpleBlockItem(
            "pomengite_ore",
            ModBlocks.POMENGITE_ORE
    );

    public static final DeferredItem<BlockItem> POMENGITE_MINERAL_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "pomengite_mineral_block",
            ModBlocks.POMENGITE_MINERAL_BLOCK
    );

    public static final DeferredItem<BlockItem> STONE_POMENGIADED_ITEM = ITEMS.registerSimpleBlockItem(
            "stone_pomengiato", ModBlocks.STONE_POMENGIADED);

    public static final DeferredItem<BlockItem> SMOOTH_STONE_POMENGIADED_ITEM = ITEMS.registerSimpleBlockItem(
            "smooth_pomengiato", ModBlocks.SMOOTH_STONE_POMENGIADED);

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}