package com.dolthhaven.doltcaveaddon.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class RegUtils {
    public static Block block(ResourceLocation location) {
        Block block = BuiltInRegistries.BLOCK.get(location);
        if (block == Blocks.AIR && !location.equals(BuiltInRegistries.BLOCK.getDefaultKey())) return null;
        return BuiltInRegistries.BLOCK.get(location);
    }

    public static ResourceLocation blockId(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static Item item(ResourceLocation location) {
        Item item = BuiltInRegistries.ITEM.get(location);
        if (item == Items.AIR && !location.equals(BuiltInRegistries.ITEM.getDefaultKey())) return null;
        return BuiltInRegistries.ITEM.get(location);
    }
}
