package com.dolthhaven.doltcaveaddon.core.data;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;
import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.THORNWOOD_LADDER;

import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.function.UnaryOperator;

public class DCABlockStatesModel extends BlueprintBlockStateProvider {
    public DCABlockStatesModel(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), DoltCaveAddon.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        this.leafPileBlock(ACBlockRegistry.ANCIENT_LEAVES, ANCIENT_LEAF_PILE);
        this.woodworksBlocks(ACBlockRegistry.PEWEN_PLANKS, PEWEN_BOARDS, PEWEN_LADDER, PEWEN_BOOKSHELF, PEWEN_BEEHIVE, PEWEN_CHEST, TRAPPED_PEWEN_CHEST);
        this.chiseledBookshelfBlock(CHISELED_PEWEN_BOOKSHELF);

        this.woodworksBlocks(ACBlockRegistry.THORNWOOD_PLANKS, THORNWOOD_BOARDS, THORNWOOD_LADDER, THORNWOOD_BOOKSHELF, THORNWOOD_BEEHIVE, THORNWOOD_CHEST, TRAPPED_THORNWOOD_CHEST);
        this.chiseledBookshelfBlock(CHISELED_THORNWOOD_BOOKSHELF);

        cabinetBlock(PEWEN_CABINET.get());
        cabinetBlock(THORNWOOD_CABINET.get());
    }

    public void cabinetBlock(Block block) {
        this.horizontalBlock(block, (state) -> {
            String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return this.models().orientable(name(block) + suffix,
                    after(block,"_side"),
                    after(block, "_front" + suffix),
                    after(block, "_top"));
        });

        this.blockItem(block);
    }

    private ResourceLocation after(Block block, String string) {
        return blockTexture(block, str -> str + string);
    }

    private ResourceLocation blockTexture(Block block, UnaryOperator<String> mapper) {
        return loc(block).withPath(str -> "block/" + mapper.apply(str));
    }

    private ResourceLocation loc(Block block) {
        return RegUtils.blockId(block);
    }
}
