package com.dolthhaven.doltcaveaddon.core.data.tags;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.dolthhaven.doltcaveaddon.core.registry.DCAConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DCATags {
    public static class Blocks {
        public static TagKey<Block> BIOME_CRUCIBLE_CAN_CONVERT = blockTag("biome_crucible_can_convert");

        public static TagKey<Block> blockTag(String path) {
            return TagKey.create(Registries.BLOCK, DoltCaveAddon.rl(path));
        }
    }

    public static class Items {
        public static TagKey<Item> COPPER_INGOTS = ext(DCAConstants.Mod.CAVERNS_AND_CHASMS, "copper_ingots");

        public static TagKey<Item> itemTag(String path) {
            return TagKey.create(Registries.ITEM, DoltCaveAddon.rl(path));
        }

        public static TagKey<Item> ext(String nameSpace, String path) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(nameSpace, path));
        }
    }
}
