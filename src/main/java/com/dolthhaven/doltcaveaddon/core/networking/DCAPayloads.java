package com.dolthhaven.doltcaveaddon.core.networking;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = DoltCaveAddon.MOD_ID)
public class DCAPayloads {
    @SubscribeEvent // on the mod event bus
    public static void register(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("mymod");
        registrar.playToClient(BlockFaceParticlePayload.TYPE, BlockFaceParticlePayload.STREAM_CODEC, BlockFaceParticlePayload::handle);
    }
}
