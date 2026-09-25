package com.dolthhaven.doltcaveaddon.core.data.tags;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;

public class DCABlockTags extends BlockTagsProvider {
    public DCABlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), DoltCaveAddon.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DCATags.Blocks.BIOME_CRUCIBLE_CAN_CONVERT)
                .addTag(BlockTags.BASE_STONE_OVERWORLD)
                .addTag(BlockTags.DIRT).addTag(BlockTags.SAND);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(PINE_NUTS_CRATE.get(), PEWEN_BEEHIVE.get(), PEWEN_BOOKSHELF.get(), PEWEN_CHEST.get(),
                        PEWEN_LADDER.get(), TRAPPED_PEWEN_CHEST.get(), PEWEN_BEEHIVE.get(), PEWEN_BOARDS.get(),
                        CHISELED_PEWEN_BOOKSHELF.get(), PEWEN_CABINET.get(),
                        THORNWOOD_BEEHIVE.get(), THORNWOOD_LADDER.get(), THORNWOOD_BOOKSHELF.get(),
                        THORNWOOD_BOARDS.get(), CHISELED_THORNWOOD_BOOKSHELF.get(), THORNWOOD_CHEST.get(),
                        TRAPPED_THORNWOOD_CHEST.get());
        this.tag(BlueprintBlockTags.WOODEN_CHESTS).add(PEWEN_CHEST.get(), THORNWOOD_CHEST.get());
        this.tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(TRAPPED_PEWEN_CHEST.get(), TRAPPED_PEWEN_CHEST.get());
        this.tag(BlueprintBlockTags.WOODEN_LADDERS).add(PEWEN_LADDER.get(), THORNWOOD_LADDER.get());
        this.tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(PEWEN_BEEHIVE.get(), THORNWOOD_BEEHIVE.get());
        this.tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(PEWEN_BOOKSHELF.get(), THORNWOOD_BOOKSHELF.get());
        this.tag(BlueprintBlockTags.WOODEN_BOARDS).add(PEWEN_BOARDS.get(), THORNWOOD_BOARDS.get());
        this.tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES).add(CHISELED_PEWEN_BOOKSHELF.get(), CHISELED_THORNWOOD_BOOKSHELF.get());
        this.tag(BlueprintBlockTags.LEAF_PILES).add(ANCIENT_LEAF_PILE.get());

    }
}
