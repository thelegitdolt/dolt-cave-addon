package com.dolthhaven.doltcaveaddon.core;

import com.dolthhaven.doltcaveaddon.core.data.tags.DCABlockTags;
import com.dolthhaven.doltcaveaddon.core.registry.DCACriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(DoltCaveAddon.MOD_ID)
public class DoltCaveAddon {
    public static final String MOD_ID = "dolt_cave_addon";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DoltCaveAddon(IEventBus bus, ModContainer modContainer) {
        bus.addListener(this::dataSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, DCAConfig.COMMON_SPEC);

        DCACriteriaTriggers.TRIGGERS.register(bus);
        DCATrackedData.registerTrackedData();
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void dataSetup(GatherDataEvent event) {
        boolean includeServer = event.includeServer();
        var dataGen = event.getGenerator();

        DCABlockTags taggies = new DCABlockTags(event);
        dataGen.addProvider(includeServer, taggies);

        boolean includeClient = event.includeClient();
    }
}
