package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.data.tags.DCATags;
import com.dolthhaven.doltcaveaddon.core.registry.DCAConstants;
import com.dolthhaven.doltcaveaddon.integration.DCACCCompat;
import com.github.alexmodguy.alexscaves.server.entity.item.SubmarineEntity;
import com.github.alexmodguy.alexscaves.server.misc.ACSoundRegistry;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(SubmarineEntity.class)
public abstract class SubmarineMixin {
    @Shadow public abstract int getOxidizationLevel();

    @Shadow public abstract boolean isWaxed();

    @ModifyArg(method = "interact", at = @At(value = "INVOKE", target = "Lcom/github/alexmodguy/alexscaves/server/entity/item/SubmarineEntity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
    private SoundEvent DoltModHow$SameSoundEffectPlease(SoundEvent soundEvent) {
        if (soundEvent == ACSoundRegistry.SUBMARINE_REPAIR.get()) {
            SoundEvent newEvent = BuiltInRegistries.SOUND_EVENT.get(DCAConstants.Reg.COPPER_GOLEM_REPAIR);
            soundEvent = newEvent == null ? soundEvent : newEvent;
        }
        return soundEvent;
    }

    @Definition(id = "itemStack", local = @Local(type = ItemStack.class))
    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    @Definition(id = "COPPER_INGOT", field = "Lnet/minecraft/world/item/Items;COPPER_INGOT:Lnet/minecraft/world/item/Item;")
    @Expression("itemStack.is(COPPER_INGOT)")
    @WrapOperation(method = "interact", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean DoltModHow$SameSoundEffectPlease(ItemStack instance, Item item, Operation<Boolean> original) {
        if (DCAConstants.Mod.ccloaded()) return instance.is(DCATags.Items.COPPER_INGOTS);
        return original.call(instance, item);
    }

    @ModifyArg(method = "hurt", at = @At(value = "INVOKE", target = "Lcom/github/alexmodguy/alexscaves/server/entity/item/SubmarineEntity;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private ItemLike item(ItemLike par1) {
        if (DCAConstants.Mod.ccloaded()) {
            int oxy = this.getOxidizationLevel();
            int waxed = this.isWaxed() ? 4 : 0;
            return DCACCCompat.COPPER_INGOTS.get().get(oxy + waxed);
        }
        return par1;
    }
}
