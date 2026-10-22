package com.dolthhaven.doltcaveaddon.integration;

import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DCACCCompat {
    public static final Supplier<List<Item>> COPPER_INGOTS = Suppliers.memoize(() -> Stream.of(
            ResourceLocation.withDefaultNamespace( "copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("exposed_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("weathered_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("oxidized_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("waxed_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("waxed_exposed_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("waxed_weathered_copper_ingot"),
            Consts.Mod.CAVERNS_AND_CHASMS.rl("waxed_oxidized_copper_ingot")).map(RegUtils::item).toList());
}
