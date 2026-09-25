package com.dolthhaven.doltcaveaddon.core.data.tags;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import vectorwing.farmersdelight.common.tag.ModTags;

public class DCAItemTags extends ItemTagsProvider {
    public DCAItemTags(GatherDataEvent event, BlockTagsProvider provider) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), provider.contentsGetter(), DoltCaveAddon.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(BlueprintBlockTags.WOODEN_CHESTS, BlueprintItemTags.WOODEN_CHESTS);
        copy(BlueprintBlockTags.WOODEN_BOARDS, BlueprintItemTags.WOODEN_BOARDS);
        copy(BlueprintBlockTags.WOODEN_BOOKSHELVES, BlueprintItemTags.WOODEN_BOOKSHELVES);
        copy(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS, BlueprintItemTags.WOODEN_TRAPPED_CHESTS);
        copy(BlueprintBlockTags.WOODEN_LADDERS, BlueprintItemTags.WOODEN_LADDERS);
        copy(BlueprintBlockTags.WOODEN_BEEHIVES, BlueprintItemTags.WOODEN_BEEHIVES);
        copy(BlueprintBlockTags.LEAF_PILES, BlueprintItemTags.LEAF_PILES);

        copy(ModTags.Blocks.CABINETS_WOODEN, ModTags.Items.CABINETS_WOODEN);

    }
}
