package com.dolthhaven.doltcaveaddon.integration;

import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.core.registry.DCAItems;
import com.github.alexmodguy.alexscaves.server.block.fluid.ACFluidRegistry;
import com.teamabnormals.caverns_and_chasms.common.dispenser.FilledGoldenBucketDispenseBehavior;
import com.teamabnormals.caverns_and_chasms.common.item.GoldenBucketItem;
import com.teamabnormals.caverns_and_chasms.core.registry.CCItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public class CavernsChasmsIntegration {
    public static Supplier<Item> item(DeferredHolder<Fluid, ?> fluid) {
        return () -> new GoldenBucketItem(fluid.get(), new Item.Properties().stacksTo(1).craftRemainder(CCItems.GOLDEN_BUCKET.get()));
    }

    public static void registerDispensers() {
        DispenserBlock.registerBehavior(DCAItems.GOLDEN_ACID_BUCKET.get(), new FilledGoldenBucketDispenseBehavior());
        DispenserBlock.registerBehavior(DCAItems.GOLDEN_PURPLE_SODA_BUCKET.get(), new FilledGoldenBucketDispenseBehavior());
    }

    public static void goldBucketItemProp(Item item) {
        ItemProperties.register(item, Consts.Mod.CAVERNS_AND_CHASMS.rl("level"), (stack, level, entity, hash) -> GoldenBucketItem.getFluidLevel(stack));
    }

    public static void registerGoldenBuckets() {
        putIfNotNull(ACFluidRegistry.ACID_FLUID_SOURCE.get(), DCAItems.GOLDEN_ACID_BUCKET);
        putIfNotNull(ACFluidRegistry.PURPLE_SODA_FLUID_SOURCE.get(), DCAItems.GOLDEN_PURPLE_SODA_BUCKET);
    }

    private static void putIfNotNull(Fluid fluid, DeferredItem<Item> bucket) {
        GoldenBucketItem.GOLDEN_BUCKET_BEHAVIORS.put(state -> state.getFluidState().is(fluid), bucket);
    }
}
