package com.dolthhaven.doltcaveaddon.core.data.tags;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.function.Supplier;

import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;
import static com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry.*;

public class DCADataMaps extends DataMapProvider {
    public DCADataMaps(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(DCADataMapsReg.UNRUST_MAP)
                .add(RUSTY_SCRAP_METAL.getId(), holder(provider, SCRAP_METAL), false)
                .add(RUSTY_SCRAP_METAL_PLATE.getId(), holder(provider, SCRAP_METAL_PLATE), false)
                .add(RUSTY_SCAFFOLDING.getId(), holder(provider, METAL_SCAFFOLDING), false)
                .add(RUSTY_BARREL.getId(), holder(provider, METAL_BARREL), false)
                .add(RUSTY_REBAR.getId(), holder(provider, METAL_REBAR), false);
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

    private static Holder<Block> holder(HolderLookup.Provider provider, DeferredHolder<Block, Block> blockSupplier) {
        return provider.holderOrThrow(blockSupplier.getKey());
    }
}
