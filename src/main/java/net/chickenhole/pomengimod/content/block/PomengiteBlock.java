package net.chickenhole.pomengimod.content.block;

import net.chickenhole.pomengimod.core.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

// Terra corrotta dalla pomengite: chi ci cammina sopra sente sussurrare "pomengi".
public class PomengiteBlock extends Block {
    public PomengiteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (!level.isClientSide
                && entity instanceof Player player
                && level.random.nextFloat() < 0.05F) {
            level.playSound(null, pos, ModSounds.WHISPER_POMENGI.value(), SoundSource.BLOCKS, 0.8F, 1.0F);
            player.displayClientMessage(Component.translatable("message.pomengimod.whisper"), true);
        }
    }
}