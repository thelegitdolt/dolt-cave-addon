package com.dolthhaven.doltcaveaddon.core.mixin.dungeonsdelight;

import com.dolthhaven.doltmetadelights.core.other.DMDTrackedData;
import com.github.alexmodguy.alexscaves.server.enchantment.ACEnchantmentRegistry;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import com.github.alexmodguy.alexscaves.server.entity.item.DesolateDaggerEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.yirmiri.dungeonsdelight.common.entity.misc.CleaverEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(CleaverEntity.class)
public abstract class CleaverEntityMixin extends AbstractArrow {
    @Shadow public ItemStack cleaverItem;

    protected CleaverEntityMixin(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
    }

    @WrapOperation(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;doPostAttackEffectsWithItemSource(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/item/ItemStack;)V"))
    private void DoltModHow$DesolateDaggerWorksWithEnchants(ServerLevel level, Entity owner, DamageSource damageSource, ItemStack itemSource, Operation<Void> original, @Local(name = "living") LivingEntity hurtEntity) {
        original.call(level, owner, damageSource, itemSource);

        IDataManager cleaverData = (IDataManager) this;
        boolean desolateDagger = cleaverData.getValue(DMDTrackedData.IS_DESOLATE_DAGGER);
        byte doubleStab = cleaverData.getValue(DMDTrackedData.LEVEL_DOUBLE_STAB);
        byte impendingStab = cleaverData.getValue(DMDTrackedData.LEVEL_IMPENDING_STAB);
        byte satedBlade = cleaverData.getValue(DMDTrackedData.LEVEL_SATED_BLADE);

        if (desolateDagger) {
            ItemStack stack = cleaverItem.copy();
            decode(level(), cleaverItem, impendingStab, satedBlade);
            summonHoveringKnives(owner, hurtEntity, stack, doubleStab, impendingStab);
        }
    }

    private static void decode(Level level, ItemStack stack, int impendingStab, int satedBlade) {
        Holder<Enchantment> stab = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ACEnchantmentRegistry.IMPENDING_STAB);
        Holder<Enchantment> sate = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(ACEnchantmentRegistry.SATED_BLADE);
        stack.enchant(stab, impendingStab);
        stack.enchant(sate, satedBlade);
    }


    private static void summonHoveringKnives(Entity player, LivingEntity entity, ItemStack stack, int doubleStab, int delayedLevel) {
        for (int i = 0; i < 1 + doubleStab; ++i) {
            DesolateDaggerEntity daggerEntity = ACEntityRegistry.DESOLATE_DAGGER.get().create(player.level());
            if (daggerEntity == null) return;

            daggerEntity.setTargetId(entity.getId());
            daggerEntity.copyPosition(player);
            daggerEntity.setItemStack(stack);
            daggerEntity.orbitFor = (delayedLevel > 0 ? 40 : 20) + player.getRandom().nextInt(10);
            player.level().addFreshEntity(daggerEntity);
        }
    }
}
