package com.dolthhaven.doltcaveaddon.core.networking;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BlockFaceParticlePayload(BlockPos pos) implements CustomPacketPayload {
    public static final StreamCodec<ByteBuf, BlockFaceParticlePayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            BlockFaceParticlePayload::pos,
            BlockFaceParticlePayload::new
    );

    public static final CustomPacketPayload.Type<BlockFaceParticlePayload> TYPE = new CustomPacketPayload.Type<>(DoltCaveAddon.rl("send_blockface_particle"));

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(BlockFaceParticlePayload payload, IPayloadContext ctx) {
        BlockPos pos = payload.pos;
        ParticleUtils.spawnParticlesOnBlockFaces(ctx.player().level(), pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));
    }
}
