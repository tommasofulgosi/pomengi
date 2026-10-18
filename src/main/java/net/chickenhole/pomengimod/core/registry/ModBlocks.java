package net.chickenhole.pomengimod.core.registry;

import net.chickenhole.pomengimod.PomengiMod;
import net.chickenhole.pomengimod.content.block.PomengiteBlock;

import net.chickenhole.pomengimod.content.block.PomengiteBlock_Variante;
import net.chickenhole.pomengimod.content.block.PomengiteOre;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PomengiMod.MODID);

    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock(
            "example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));

    public static final DeferredBlock<PomengiteBlock> BLOCCO_DI_POMENGITE = BLOCKS.registerBlock(
            "blocco_di_pomengite",
            PomengiteBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.5F)
                    .sound(SoundType.GRAVEL));

    public static final DeferredBlock<PomengiteBlock_Variante> BLOCCO_DI_POMENGITE_VARIANTE = BLOCKS.registerBlock(
            "blocco_di_pomengite_variante",
            PomengiteBlock_Variante::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(1.5F)
                    .sound(SoundType.GRAVEL));



    public static final DeferredBlock<PomengiteOre> POMENGITE_ORE = BLOCKS.register(
            "pomengite_ore",
            () -> new PomengiteOre(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(4.0F, 4.0F) // Più duro del diamante (3.0F)
                    .requiresCorrectToolForDrops() // Richiede lo strumento adatto
                    .sound(SoundType.STONE))
    );

    private ModBlocks() {
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}