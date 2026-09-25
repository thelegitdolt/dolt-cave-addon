package com.dolthhaven.doltcaveaddon.core.registry;

import com.dolthhaven.doltcaveaddon.common.blocks.ChiseledPewenBookshelfBlock;
import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.minecraft.world.item.crafting.Ingredient.of;
import static net.minecraft.world.level.material.MapColor.TERRACOTTA_YELLOW;

public class DCABlocks {
    public static final BlockSubRegistryHelper HELPER = DoltCaveAddon.REGISTRY_HELPER.getBlockSubHelper();
    public static final ItemSubRegistryHelper ITEMS = DoltCaveAddon.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredBlock<Block> PINE_NUTS_CRATE = HELPER.createBlock("pine_nuts_crate", () ->
            new BlueprintDirectionalBlock(DCABlockProps.PINE_NUT_CRATE));

    public static final DeferredBlock<Block> PEWEN_BEEHIVE = HELPER.createBlock("pewen_beehive", () ->
            new BlueprintBeehiveBlock(DCABlockProps.PEWEN.beehive()));
    public static final DeferredBlock<BlueprintChestBlock> PEWEN_CHEST = HELPER
            .createChestBlock("pewen", DCABlockProps.PEWEN.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_PEWEN_CHEST = HELPER
            .createTrappedChestBlock("pewen", DCABlockProps.PEWEN.chest());
    public static final DeferredBlock<Block> PEWEN_LADDER = HELPER.createBlock("pewen_ladder", () ->
            new LadderBlock(DCABlockProps.PEWEN.ladder()));
    public static final DeferredBlock<Block> PEWEN_BOARDS = HELPER.createBlock("pewen_boards", () ->
            new RotatedPillarBlock(DCABlockProps.PEWEN.planks()));
    public static final DeferredBlock<Block> PEWEN_BOOKSHELF = HELPER.createBlock("pewen_bookshelf", () ->
            new Block(DCABlockProps.PEWEN.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_PEWEN_BOOKSHELF = HELPER.createBlock("chiseled_pewen_bookshelf", () ->
            new ChiseledPewenBookshelfBlock(DCABlockProps.PEWEN.chiseledBookshelf()));
    public static final DeferredBlock<Block> PEWEN_CABINET = HELPER.createBlock("pewen_cabinet", () ->
            new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.OAK_CABINET.get())));

    public static final DeferredBlock<Block> ANCIENT_LEAF_PILE = HELPER.createBlock("ancient_leaf_pile", () ->
            new LeafPileBlock(DCABlockProps.PEWEN.leafPile()));

    public static final DeferredBlock<Block> THORNWOOD_BEEHIVE = HELPER.createBlock("thornwood_beehive", () ->
            new BlueprintBeehiveBlock(DCABlockProps.THORNWOOD.beehive()));
    public static final DeferredBlock<BlueprintChestBlock> THORNWOOD_CHEST = HELPER
            .createChestBlock("thornwood", DCABlockProps.PEWEN.chest());
    public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_THORNWOOD_CHEST = HELPER
            .createTrappedChestBlock("thornwood", DCABlockProps.THORNWOOD.chest());
    public static final DeferredBlock<Block> THORNWOOD_LADDER = HELPER.createBlock("thornwood_ladder", () ->
            new LadderBlock(DCABlockProps.THORNWOOD.ladder()));
    public static final DeferredBlock<Block> THORNWOOD_BOARDS = HELPER.createBlock("thornwood_boards", () ->
            new RotatedPillarBlock(DCABlockProps.THORNWOOD.planks()));
    public static final DeferredBlock<Block> THORNWOOD_BOOKSHELF = HELPER.createBlock("thornwood_bookshelf", () ->
            new Block(DCABlockProps.THORNWOOD.bookshelf()));
    public static final DeferredBlock<Block> CHISELED_THORNWOOD_BOOKSHELF = HELPER.createBlock("chiseled_thornwood_bookshelf", () ->
            new ChiseledPewenBookshelfBlock(DCABlockProps.THORNWOOD.chiseledBookshelf()));
    public static final DeferredBlock<Block> THORNWOOD_CABINET = HELPER.createBlock("thornwood_cabinet", () ->
            new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.DARK_OAK_CABINET.get())));

    public static void setUpTabEditors() {
        var thing = CreativeModeTabContentsPopulator.mod(DoltCaveAddon.MOD_ID)
                .tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .addItemsAfter(of(Blocks.LADDER), PEWEN_LADDER, THORNWOOD_LADDER)
                .addItemsAfter(of(Blocks.BEEHIVE), PEWEN_BEEHIVE, THORNWOOD_BEEHIVE)
                .addItemsAfter(of(Blocks.BOOKSHELF), PEWEN_BOOKSHELF, THORNWOOD_BOOKSHELF)
                .addItemsAfter(of(Blocks.CHISELED_BOOKSHELF), CHISELED_PEWEN_BOOKSHELF, CHISELED_THORNWOOD_BOOKSHELF)
                .addItemsAfter(of(Blocks.CHEST), PEWEN_CHEST, THORNWOOD_CHEST)

                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItemsBefore(of(Blocks.BAMBOO_BLOCK), PEWEN_BOARDS, THORNWOOD_BOARDS)
                .addItemsAfter(modLoaded(Blocks.HAY_BLOCK), PINE_NUTS_CRATE)

                .tab(CreativeModeTabs.REDSTONE_BLOCKS)
                .addItemsAfter(of(Blocks.TRAPPED_CHEST), TRAPPED_PEWEN_CHEST, TRAPPED_THORNWOOD_CHEST)

                .predicate(DCABlocks::fdPredicate)
                .addItemsBefore(of(ModItems.BAMBOO_CABINET.get()), PEWEN_CABINET, THORNWOOD_CABINET);

    }

    public static class DCABlockProps {
        public static final BlockBehaviour.Properties PINE_NUT_CRATE = BlockBehaviour.Properties.of().mapColor(TERRACOTTA_YELLOW)
                .strength(1.5f).sound(SoundType.WOOD).ignitedByLava();

        public static final PropertyUtil.WoodSetProperties PEWEN = PropertyUtil
                .WoodSetProperties.builder(MapColor.WOOD).sound(SoundType.CHERRY_WOOD).instrument(NoteBlockInstrument.BASS).build();
        public static final PropertyUtil.WoodSetProperties THORNWOOD = PropertyUtil
                .WoodSetProperties.builder(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASS).build();
    }

    public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
        return stack -> BlockSubRegistryHelper.areModsLoaded(modids) && of(item).test(stack);
    }

    public static boolean fdPredicate(BuildCreativeModeTabContentsEvent event) {
        return event.getTabKey() == ModCreativeTabs.TAB_FARMERS_DELIGHT;
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(RegUtils.item(location)).test(stack));
    }
    public static Predicate<ItemStack> ofLoaded(Supplier<Item> item, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(item.get()).test(stack));
    }

}
