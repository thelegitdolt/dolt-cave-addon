package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.DCAConfig;
import com.github.alexmodguy.alexscaves.server.entity.living.TremorzillaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(TremorzillaEntity.class)
public class TremorzillaMixin {
    @Inject(method = "breakBlocksInBoundingBox", at = @At("HEAD"), cancellable = true, remap = false)
    private void DoltModHow$NOTVANILLAPLUS(float dropChance, CallbackInfoReturnable<Boolean> cir) {
        if (DCAConfig.COMMON.removeTremorzillaGrief.get()) {
            cir.setReturnValue(false);
        }
    }
}
