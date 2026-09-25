package com.dolthhaven.doltcaveaddon.core.registry;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class DCAConstants {
    public static class Mod {
        public static final String CAVERNS_AND_CHASMS = "caverns_and_chasms";
        public static final String SPAWN = "spawn";
        public static final String WINDSWEPT = "windswept";


        public static boolean ccloaded() {
            return ModList.get().isLoaded(CAVERNS_AND_CHASMS);
        }
    }

    public static class Reg {
        public static final ResourceLocation COPPER_GOLEM_REPAIR = ResourceLocation.fromNamespaceAndPath(Mod.CAVERNS_AND_CHASMS, "entity.copper_golem.repair");
        public static final ResourceLocation GINGERBREAD_COOKIE = ResourceLocation.fromNamespaceAndPath(Mod.WINDSWEPT, "gingerbread_cookie");
    }
}
