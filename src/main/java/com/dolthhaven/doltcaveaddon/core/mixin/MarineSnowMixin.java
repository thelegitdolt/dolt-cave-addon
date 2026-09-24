package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.item.MarineSnowItem;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Pseudo
@Mixin(MarineSnowItem.class)
public class MarineSnowMixin {
    @Inject(method = "lambda$initGrowth$0", at = @At(value = "HEAD"))
    private static  <K, V> void changeGlowInteractions(HashMap<K, V> map, CallbackInfo ci) {
        BuiltInRegistries.BLOCK.keySet().stream().filter(rl -> rl.getPath().contains("coral")
                        && (rl.getPath().contains("dead") || rl.getPath().contains("elder_prismarine")) && !rl.getPath().contains("coralstone"))
                .forEach(location -> {
                    Block dead = RegUtils.block(location);
                    Block alive = RegUtils.block(location.withPath(path -> path.replace("dead_", "").replace("elder_", "")));
                    if (dead != null && alive != null) ((HashMap<Block, Block>) map).put(dead, alive);
                });
    }

    @WrapWithCondition(method = "lambda$initGrowth$0", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static  <K, V> boolean changeGlowInteractions(HashMap<K, V> instance, K key, V value) {
        if (key instanceof Block block) {
            ResourceLocation location = RegUtils.blockId(block);
            if (location.getPath().endsWith("coral") || location.getPath().endsWith("coral_fan")) return false;
        }
        return true;
    }

    @Inject(method = "lambda$initGrowth$1", at = @At(value = "HEAD"))
    private static  <K, V> void changeDupeInteraction(HashMap<K, V> map, CallbackInfo ci) {
        ((HashMap<Block, ItemStack>)map).put(ACBlockRegistry.PING_PONG_SPONGE.get(), new ItemStack(ACBlockRegistry.PING_PONG_SPONGE.get().asItem()));
    }

    @WrapWithCondition(method = "lambda$initGrowth$1", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static <K, V> boolean changeDupeInteractions(HashMap<K, V> instance, K key, V value) {
        if (key instanceof Block block) {
            return RegUtils.blockId(block).getNamespace().equals(AlexsCaves.MODID);
        } return true;
    }
}
