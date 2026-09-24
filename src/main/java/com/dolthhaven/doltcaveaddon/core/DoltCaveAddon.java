package com.dolthhaven.doltcaveaddon.core;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(DoltCaveAddon.MODID)
public class DoltCaveAddon {
    public static final String MODID = "dolt_cave_addon";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DoltCaveAddon(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, DCAConfig.COMMON_SPEC);
    }
}
