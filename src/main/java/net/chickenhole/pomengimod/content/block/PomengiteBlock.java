package net.chickenhole.pomengimod.content.block;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.chickenhole.pomengimod.core.registry.ModSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

// Terra corrotta dalla pomengite: chi ci cammina sopra sente sussurrare "pomengi".
public class PomengiteBlock extends Block {
    // Deve coprire la durata del sussurro: finché non scade, non parte un'altra voce.
    private static final int WHISPER_COOLDOWN_TICKS = 100;
    private static final Map<UUID, Long> LAST_WHISPER = new HashMap<>();

    public PomengiteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (level.isClientSide || !(entity instanceof ServerPlayer player)) {
            return;
        }
        if (level.random.nextFloat() >= 0.05F) {
            return;
        }
        long last = LAST_WHISPER.getOrDefault(player.getUUID(), -(long) WHISPER_COOLDOWN_TICKS);
        if (level.getGameTime() - last < WHISPER_COOLDOWN_TICKS) {
            return;
        }
        LAST_WHISPER.put(player.getUUID(), level.getGameTime());
        level.playSound(null, pos, ModSounds.WHISPER_POMENGI.value(), SoundSource.BLOCKS, 0.8F, 1.0F);
        player.displayClientMessage(Component.translatable("message.pomengimod.whisper"), true);
    }



    @Override
    public void playerDestroy(Level level, net.minecraft.world.entity.player.Player player, BlockPos pos, BlockState state, @org.jetbrains.annotations.Nullable net.minecraft.world.level.block.entity.BlockEntity blockEntity, net.minecraft.world.item.ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
        if (!level.isClientSide && !player.isCreative()) {
            popResource(level, pos, new net.minecraft.world.item.ItemStack(this));
        }
    }

}