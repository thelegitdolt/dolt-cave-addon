package com.dolthhaven.doltcaveaddon.core.data;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import com.teamabnormals.woodworks.core.other.WoodworksConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Supplier;

import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;

public class DCARecipes extends BlueprintRecipeProvider {
    public DCARecipes(GatherDataEvent event) {
        super(DoltCaveAddon.MOD_ID, event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        storageRecipes(recipeOutput, RecipeCategory.MISC, ACItemRegistry.PINE_NUTS.value(), RecipeCategory.BUILDING_BLOCKS, PINE_NUTS_CRATE.asItem());

        WoodworksRecipeProvider.baseRecipes(recipeOutput, ACBlockRegistry.PEWEN_PLANKS.get(), ACBlockRegistry.PEWEN_PLANKS_SLAB.get(), PEWEN_BOARDS.get(), PEWEN_BOOKSHELF.get(),
                CHISELED_PEWEN_BOOKSHELF.get(), PEWEN_LADDER.get(), PEWEN_BEEHIVE.get(), PEWEN_CHEST.get(),
                TRAPPED_PEWEN_CHEST.get());

        WoodworksRecipeProvider.baseRecipes(recipeOutput, ACBlockRegistry.THORNWOOD_PLANKS.get(), ACBlockRegistry.THORNWOOD_PLANKS_SLAB.get(), THORNWOOD_BOARDS.get(), THORNWOOD_BOOKSHELF.get(),
                CHISELED_THORNWOOD_BOOKSHELF.get(), THORNWOOD_LADDER.get(), THORNWOOD_BEEHIVE.get(), THORNWOOD_CHEST.get(),
                TRAPPED_THORNWOOD_CHEST.get());

        cabinet(recipeOutput, THORNWOOD_CABINET, ACBlockRegistry.THORNWOOD_PLANKS_SLAB, ACBlockRegistry.THORNWOOD_TRAPDOOR);
        cabinet(recipeOutput, PEWEN_CABINET, ACBlockRegistry.PEWEN_PLANKS_SLAB, ACBlockRegistry.PEWEN_TRAPDOOR);

        this.leafPileRecipes(recipeOutput, ACBlockRegistry.ANCIENT_LEAVES.get(), ANCIENT_LEAF_PILE.get());
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.PEWEN_LOG.get(), PEWEN_LADDER.get(), 4);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.PEWEN_PLANKS.get(), PEWEN_LADDER.get(), 1);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.PEWEN_PLANKS.get(), PEWEN_BOARDS.get(), 1);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.PEWEN_LOG.get(), PEWEN_BOARDS.get(), 4);

        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.THORNWOOD_LOG.get(), THORNWOOD_LADDER.get(), 4);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.THORNWOOD_PLANKS.get(), THORNWOOD_LADDER.get(), 1);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.THORNWOOD_LOG.get(), THORNWOOD_BOARDS.get(), 4);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput, WoodworksConditions.SAWMILL_ENABLED, RecipeCategory.BUILDING_BLOCKS, ACBlockRegistry.THORNWOOD_PLANKS.get(), THORNWOOD_BOARDS.get(), 1);

    }

    private void cabinet(RecipeOutput output, Supplier<Block> cabinet, Supplier<Block> slab, Supplier<Block> trapdoor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cabinet.get())
                .define('1', slab.get()).define('2', trapdoor.get())
                .pattern("111").pattern("2 2").pattern("111")
                .unlockedBy("has_pewen_slab", has(slab.get())).save(output);
    }
}
