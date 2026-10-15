package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PomengiMod.MODID);

    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            "example_block", ModBlocks.EXAMPLE_BLOCK);

    public static final DeferredItem<BlockItem> BLOCCO_DI_POMENGITE_ITEM = ITEMS.registerSimpleBlockItem(
            "blocco_di_pomengite", ModBlocks.BLOCCO_DI_POMENGITE);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(
            new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}