package com.dolthhaven.doltcaveaddon.core;

import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

public class DoltCaveAddon {
    public static final Logger LOGGER = LogUtils.getLogger();

        modContainer.registerConfig(ModConfig.Type.COMMON, DCAConfig.COMMON_SPEC);
    }
}
