package com.dolthhaven.doltcaveaddon.core.mixin.lootchestfeature;

import com.teamabnormals.caverns_and_chasms.core.CavernsAndChasms;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = CavernsAndChasms.class)
public class Debug {
    @Inject(method = "dataSetup", at = @At("HEAD"), cancellable = true)
    private void sex(GatherDataEvent event, CallbackInfo ci) {
        ci.cancel();
    }

}
