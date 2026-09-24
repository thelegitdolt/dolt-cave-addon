package com.dolthhaven.doltcaveaddon.integration;

import com.dolthhaven.doltcaveaddon.core.registry.DCAConstants;
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
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "exposed_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "weathered_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "oxidized_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "waxed_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "waxed_exposed_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "waxed_weathered_copper_ingot"),
            ResourceLocation.fromNamespaceAndPath(DCAConstants.Mod.CAVERNS_AND_CHASMS, "waxed_oxidized_copper_ingot")).map(RegUtils::item).toList());
}
