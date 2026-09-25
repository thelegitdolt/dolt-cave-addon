package com.dolthhaven.doltcaveaddon.core;


import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import static net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class DCAConfig {
    public static class Common {
        public final ConfigValue<Boolean> removeTremorzillaGrief;
        public final ConfigValue<Boolean> acidCorrodesCopper;
        public final ConfigValue<Boolean> sackOfSatingNoRestoreSat;
        public final ConfigValue<Boolean> actuallyGoodBiomeTreats;
        public final ConfigValue<Boolean> pathfinderQuillMakesCaveMaps;

        public final ConfigValue<Boolean> gingerBreadMenDropWindsweptCookies;

        Common(ModConfigSpec.Builder builder) {

            builder.push("Alexander Caverns");

            builder.push("Acid");
            acidCorrodesCopper = builder.comment("If acid should not corrode copper")
                    .define("Good Acid", true);
            builder.pop();

            builder.push("Cave Maps");
            pathfinderQuillMakesCaveMaps = builder.comment("If pathfinder quills should make a based sigma cave map instead of a cringe beta vanilla map")
                    .define("I'm sorry I said all that", true);
            builder.pop();

            builder.push("Biome Treats");
            actuallyGoodBiomeTreats = builder.comment("If biome treats should work regardless of hunger")
                    .define("Okay Treats", true);
            builder.pop();

            builder.push("Gingerbread Men");
            gingerBreadMenDropWindsweptCookies = builder.comment("If gingerbread men should drop windswept gingerbread cookies instead of gingerbread crumps")
                    .define("This Only Makes Sense In Dolt Modpack", false);
            builder.pop();

            builder.push("Sack of Sating");
            sackOfSatingNoRestoreSat = builder.comment("If sacks of sating no longer restore saturation")
                    .define("Mid sack of sating", false);
            builder.pop();

            builder.push("Tremorzilla");
            removeTremorzillaGrief = builder.comment("If tremorzillas should no longer passively break all blocks in its hitbox. Blocks are still broken when the tremorzilla attacks, or with its beam attack.")
                    .define("Marginally Less Griefy Tremorzillas", false);
            builder.pop();

            builder.pop();
        }
    }

    static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(DCAConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
