package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.DCAConfig;
import com.dolthhaven.doltcaveaddon.core.registry.Consts;
import com.dolthhaven.doltcaveaddon.util.RegUtils;
import com.github.alexmodguy.alexscaves.server.entity.living.GingerbreadManEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(GingerbreadManEntity.class)
public class GingerbreadManMixin {
    @WrapOperation(method = "onLoseArm",
            at = @At(value = "NEW", target = "(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack noMoreGingerbreadCrumbs(ItemLike item, Operation<ItemStack> original) {
        cookie: if (DCAConfig.COMMON.gingerBreadMenDropWindsweptCookies.get() && Consts.Mod.WINDSWEPT.loaded()) {
            Item cookie = RegUtils.item(Consts.Reg.GINGERBREAD_COOKIE);
            if (cookie == null) break cookie;

            return original.call(cookie);
        }

        return original.call(item);
    }
}
