package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;
import net.chickenhole.pomengimod.config.Config;
import net.chickenhole.pomengimod.content.item.PomengiteSwordItem;
import net.chickenhole.pomengimod.core.ModTiers;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(
            new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Valori letti dal config al momento della registrazione: servono riavvio se cambiati.
    public static final DeferredItem<SwordItem> SPADA_DI_MINERALE_POMENGIATO = ITEMS.register(
            "spada_di_minerale_pomengiato",
            () -> new PomengiteSwordItem(ModTiers.POMENGITE, new Item.Properties()
                    .durability(Config.POMENGITE_SWORD_DURABILITY.get())
                    .attributes(SwordItem.createAttributes(
                            ModTiers.POMENGITE, Config.POMENGITE_SWORD_DAMAGE.get(), -2.4F))
                    .fireResistant()));

    private ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}