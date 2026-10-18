package net.chickenhole.pomengimod.content.block;

import net.chickenhole.pomengimod.core.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PomengiteOre extends Block {

    public PomengiteOre(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);

        if (!level.isClientSide && !player.isCreative()) {
            // 1. Controlliamo se lo strumento è un piccone di Diamante, Netherite o il piccone di Pomengite
            boolean isDiamondPick = tool.is(net.minecraft.world.item.Items.DIAMOND_PICKAXE);
            boolean isNetheritePick = tool.is(net.minecraft.world.item.Items.NETHERITE_PICKAXE);
            boolean isPomengitePick = tool.is(ModItems.PICCONE_DI_MINERALE_POMENGIATO.get()); // Cambia PICCONE_DI_POMENGITE con il nome esatto della tua variabile in ModItems

            if (isDiamondPick || isNetheritePick || isPomengitePick) {
                // Rilascia la gemma/minerale
                popResource(level, pos, new ItemStack(ModItems.MINERALE_POMENGIATO.get()));

                // Rilascia esperienza (tra 3 e 7 punti XP)
                if (level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                    int expAmount = level.random.nextInt(5) + 3;
                    this.popExperience(serverLevel, pos, expAmount);
                }
            }
        }
    }
}