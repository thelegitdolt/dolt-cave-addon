package com.dolthhaven.doltcaveaddon.core.other;

import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.core.registry.DCAItems;
import com.dolthhaven.doltcaveaddon.integration.CavernsChasmsIntegration;

public class DCAClientSetup {
    public static void clientSetup() {
        registerItemProps();
    }

    public static void registerItemProps() {
        if (Consts.Mod.CAVERNS_AND_CHASMS.loaded()) {
            CavernsChasmsIntegration.goldBucketItemProp(DCAItems.GOLDEN_ACID_BUCKET.get());
            CavernsChasmsIntegration.goldBucketItemProp(DCAItems.GOLDEN_PURPLE_SODA_BUCKET.get());
        }
    }
}
