package com.dolthhaven.doltcaveaddon.core.data;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dolthhaven.doltcaveaddon.core.registry.DCABlocks.*;

public class DCALootTables extends LootTableProvider {
    public DCALootTables(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
        ), event.getLookupProvider());
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
    }

    public static class BlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());


        protected BlockLoot(HolderLookup.Provider registries) {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            this.dropSelf(PINE_NUTS_CRATE.get());

            this.add(PEWEN_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(TRAPPED_PEWEN_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(PEWEN_BOOKSHELF.get(), block -> this.createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3)));
            this.add(PEWEN_BEEHIVE.get(), this::createBeeHiveDrop);
            this.dropSelf(PEWEN_LADDER.get());
            this.dropSelf(PEWEN_BOARDS.get());
            this.add(PEWEN_CABINET.get(), this::createNameableBlockEntityTable);
            this.dropWhenSilkTouch(CHISELED_PEWEN_BOOKSHELF.get());

            this.add(ANCIENT_LEAF_PILE.get(), block -> createMultifaceBlockDrops(block,
                    MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR))));

            this.add(THORNWOOD_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(TRAPPED_THORNWOOD_CHEST.get(), this::createNameableBlockEntityTable);
            this.add(THORNWOOD_CABINET.get(), this::createNameableBlockEntityTable);
            this.add(THORNWOOD_BEEHIVE.get(), this::createBeeHiveDrop);
            this.dropSelf(THORNWOOD_LADDER.get());
            this.dropSelf(THORNWOOD_BOARDS.get());
            this.add(THORNWOOD_CABINET.get(), this::createNameableBlockEntityTable);
            this.add(THORNWOOD_BOOKSHELF.get(), block -> this.createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3)));
            this.dropWhenSilkTouch(CHISELED_THORNWOOD_BOOKSHELF.get());
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> DoltCaveAddon.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace())).collect(Collectors.toSet());
        }
    }
}
