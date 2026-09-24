package com.dolthhaven.doltcaveaddon.core.mixin;

import com.github.alexmodguy.alexscaves.server.block.AcidBlock;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.level.block.WeatheringCopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;

@Mixin(value = AcidBlock.class, remap = false)
public class AcidBlockMixin {
    @WrapWithCondition(method = "lambda$initCorrosion$0", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static <K, V> boolean DoltModHow$NoAutomaticallyOxidatingCopperPlease(HashMap<K, V> instance, K key, V value) {
        return !(key instanceof WeatheringCopper);
    }
}
