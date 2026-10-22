package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.github.alexmodguy.alexscaves.server.item.ACItemRegistry;
import com.github.alexmodguy.alexscaves.server.item.CaveMapItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.violetmoon.quark.content.tools.item.PathfindersQuillItem;

@Pseudo
@Mixin(PathfindersQuillItem.class)
public abstract class PathfinderQuillMixin {
    @Shadow(remap = false) public abstract ResourceLocation getTarget(ItemStack stack);

    @Inject(method= "use", at = @At(value = "HEAD"), cancellable = true)
    private void DoltModHow$MakeACCaveMap(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (!RegUtils.itemId(stack.getItem()).equals(Consts.Reg.PATHFINDER_QUILL)) return;

        ItemStack mapStack = CaveMapItem.createMap(ResourceKey.create(Registries.BIOME, getTarget(stack)));
        player.setItemInHand(hand, mapStack);

        InteractionResultHolder<ItemStack> useResult = ACItemRegistry.CAVE_MAP.get().use(level, player, hand);

        cir.setReturnValue(useResult);
    }

}
