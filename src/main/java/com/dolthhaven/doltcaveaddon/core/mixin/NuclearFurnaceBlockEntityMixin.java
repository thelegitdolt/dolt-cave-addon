package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.DCAConfig;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NuclearFurnaceBlockEntity.class)
public class NuclearFurnaceBlockEntityMixin {
    @Inject(method = "getMaxFissionTime", at = @At("HEAD"), cancellable = true, remap = false)
    private static void superCook(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(20 * DCAConfig.COMMON.stackSize.get() * 100);
    }
}
