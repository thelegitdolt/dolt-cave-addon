package com.dolthhaven.doltcaveaddon.core.data.tags;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DCABlockTags extends BlockTagsProvider {
    public DCABlockTags(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider(), DoltCaveAddon.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DCATags.Blocks.BIOME_CRUCIBLE_CAN_CONVERT)
                .addTag(BlockTags.BASE_STONE_OVERWORLD)
                .addTag(BlockTags.DIRT).addTag(BlockTags.SAND);
    }
}
