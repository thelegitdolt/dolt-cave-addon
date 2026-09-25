package com.dolthhaven.doltcaveaddon.core.data;

import com.dolthhaven.doltcaveaddon.core.registry.DCABlocks;
import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

public class DCADataMaps extends DataMapProvider {
    public DCADataMaps(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(DCABlocks.PINE_NUTS_CRATE.getId(), new Compostable(1.0f), false);

        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(DCABlocks.PINE_NUTS_CRATE.getId(), new FurnaceFuel(300), false);
    }
}
