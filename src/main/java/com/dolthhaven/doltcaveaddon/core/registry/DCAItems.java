package com.dolthhaven.doltcaveaddon.core.registry;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.dolthhaven.doltcaveaddon.integration.CavernsChasmsIntegration;
import com.github.alexmodguy.alexscaves.server.block.fluid.ACFluidRegistry;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public class DCAItems {
    public static final ItemSubRegistryHelper ITEMS = DoltCaveAddon.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> GOLDEN_ACID_BUCKET = goldenBucket("acid", ACFluidRegistry.ACID_FLUID_SOURCE);
    public static final DeferredItem<Item> GOLDEN_PURPLE_SODA_BUCKET = goldenBucket("purple_soda", ACFluidRegistry.PURPLE_SODA_FLUID_SOURCE);

    private static DeferredItem<Item> goldenBucket(String name, DeferredHolder<Fluid, ?> fluid) {
        boolean prereqs = Consts.Mod.CAVERNS_AND_CHASMS.loaded();
        Supplier<Item> item = prereqs ? CavernsChasmsIntegration.item(fluid) : () -> new Item(new Item.Properties().stacksTo(1));
        return ITEMS.createItem("golden_" + name + "_bucket", item);
    }
}
