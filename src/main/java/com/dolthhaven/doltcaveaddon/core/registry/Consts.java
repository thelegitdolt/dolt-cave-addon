package com.dolthhaven.doltcaveaddon.core.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public class Consts {
    public static class Mod {
        public static final ModId CAVERNS_AND_CHASMS = new ModId("caverns_and_chasms");
        public static final ModId QUARK = new ModId("quark");
        public static final ModId SPAWN = new ModId("spawn");
        public static final ModId WINDSWEPT = new ModId("windswept");

    }

    public static class Reg {
        public static final ResourceLocation COPPER_GOLEM_REPAIR = Mod.CAVERNS_AND_CHASMS.rl("entity.copper_golem.repair");
        public static final ResourceLocation GOLDEN_LAVA_BUCKET = Mod.CAVERNS_AND_CHASMS.rl("golden_lava_bucket");
        public static final ResourceLocation GINGERBREAD_COOKIE = Mod.WINDSWEPT.rl("gingerbread_cookie");
        public static final ResourceLocation PATHFINDER_QUILL = Mod.QUARK.rl("pathfinders_quill");
    }

    public record ModId(String id) {
        public boolean loaded() {
            return ModList.get().isLoaded(id);
        }

        public ResourceLocation rl(String path) {
            return ResourceLocation.fromNamespaceAndPath(id, path);
        }

        public MutableComponent translatable(String path) {
            return Component.translatable(path.formatted(id));
        }

        public ModLoadedCondition requiresLoaded() {
            return new ModLoadedCondition(id);
        }
    }
}
