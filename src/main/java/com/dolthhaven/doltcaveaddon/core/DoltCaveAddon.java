package com.dolthhaven.doltcaveaddon.core;

import com.dolthhaven.doltcaveaddon.core.data.DCABlockStatesModel;
import com.dolthhaven.doltcaveaddon.core.data.DCALootTables;
import com.dolthhaven.doltcaveaddon.core.data.DCARecipes;
import com.dolthhaven.doltcaveaddon.core.data.tags.DCABlockTags;
import com.dolthhaven.doltcaveaddon.core.data.tags.DCADataMaps;
import com.dolthhaven.doltcaveaddon.core.data.tags.DCAItemTags;
import com.dolthhaven.doltcaveaddon.core.other.DCAClientSetup;
import com.dolthhaven.doltcaveaddon.core.other.DCACommonSetup;
import com.dolthhaven.doltcaveaddon.core.other.DCATrackedData;
import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.core.registry.DCABlocks;
import com.dolthhaven.doltcaveaddon.core.registry.DCAItems;
import com.dolthhaven.doltcaveaddon.core.registry.DCAParticles;
import com.mojang.logging.LogUtils;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(DoltCaveAddon.MOD_ID)
public class DoltCaveAddon {
    public static final String MOD_ID = "dolt_cave_addon";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);


    public DoltCaveAddon(IEventBus bus, ModContainer modContainer) {
        DCABlocks.BLOCKS.register(bus);
        DCAItems.ITEMS.register(bus);

        DCAParticles.PARTICLES.register(bus);

        bus.addListener(this::dataSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, DCAConfig.COMMON_SPEC);

        DCATrackedData.registerTrackedData();
        NeoForgeMod.enableMilkFluid();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(DCACommonSetup::doCommonSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            DCABlocks.setUpTabEditors();
            if (Consts.Mod.CAVERNS_AND_CHASMS.loaded()) {
                DCAClientSetup.clientSetup();
            }
        });
    }


    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void dataSetup(GatherDataEvent event) {
        boolean includeServer = event.includeServer();
        var dataGen = event.getGenerator();

        DCABlockTags taggies = new DCABlockTags(event);
        dataGen.addProvider(includeServer, taggies);
        dataGen.addProvider(includeServer, new DCAItemTags(event, taggies));
        dataGen.addProvider(includeServer, new DCADataMaps(event));
        dataGen.addProvider(includeServer, new DCALootTables(event));
        dataGen.addProvider(includeServer, new DCARecipes(event));

        boolean includeClient = event.includeClient();
        dataGen.addProvider(includeClient, new DCABlockStatesModel(event));
    }
}
