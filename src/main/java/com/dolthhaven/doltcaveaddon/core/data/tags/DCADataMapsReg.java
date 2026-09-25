package com.dolthhaven.doltcaveaddon.core.data.tags;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = DoltCaveAddon.MOD_ID)
public class DCADataMapsReg {
    public static final DataMapType<Block, Holder<Block>> UNRUST_MAP = DataMapType
            .builder(DoltCaveAddon.rl("unrust_map"), Registries.BLOCK, RegistryFixedCodec.create(Registries.BLOCK))
            .build();

    @SubscribeEvent
    public static void registerDataMaps(RegisterDataMapTypesEvent event) {
        event.register(UNRUST_MAP);
    }
}
