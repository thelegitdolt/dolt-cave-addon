package com.dolthhaven.doltcaveaddon.core.other;

import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.integration.CavernsChasmsIntegration;

public class DCACommonSetup {
    public static void doCommonSetup() {
        if (Consts.Mod.CAVERNS_AND_CHASMS.loaded()) {
            CavernsChasmsIntegration.registerGoldenBuckets();
        }
    }
}
