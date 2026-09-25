package com.dolthhaven.doltcaveaddon.core.mixin;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Disables Upgrade Aquatic's chud glow ink sac reviving corals feature
 */
@Pseudo
@Mixin(targets = "com.teamabnormals.upgrade_aquatic.core.other.UAEvents")
public class DisableGlowSquidResuscitateCoral {
    @Inject(method = "onRightClickBlock", at = @At("HEAD"), cancellable = true, remap = false)
    private static void thisFeatureSucksAndIWIN_MWAHAHAHAHAHA(PlayerInteractEvent.RightClickBlock event, CallbackInfo ci) {
        ci.cancel();
    }
}

