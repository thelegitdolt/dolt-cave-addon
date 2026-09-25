package com.dolthhaven.doltcaveaddon.core.data;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;

public class DCADataMaps extends DataMapProvider {
    public DCADataMaps(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(PINE_NUTS_CRATE.getId(), new Compostable(1.0f), false);

        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(PINE_NUTS_CRATE.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_BEEHIVE.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_LADDER.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_BOARDS.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_CHEST.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_CABINET.getId(), new FurnaceFuel(300), false)
                .add(TRAPPED_PEWEN_CHEST.getId(), new FurnaceFuel(300), false)
                .add(PEWEN_BOOKSHELF.getId(), new FurnaceFuel(300), false)
                .add(CHISELED_PEWEN_BOOKSHELF.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_BEEHIVE.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_LADDER.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_BOARDS.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_CHEST.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_CABINET.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_CHEST.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_BOOKSHELF.getId(), new FurnaceFuel(300), false)
                .add(THORNWOOD_BOOKSHELF.getId(), new FurnaceFuel(300), false);
    }
}
