package net.chickenhole.pomengimod.content.block;

import net.chickenhole.pomengimod.core.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PomengiteMineralBlock extends Block {

    public PomengiteMineralBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);

        // Rilascia se stesso in survival
        if (!level.isClientSide && !player.isCreative()) {
            popResource(level, pos, new ItemStack(ModBlocks.POMENGITE_MINERAL_BLOCK.get()));
        }
    }
}