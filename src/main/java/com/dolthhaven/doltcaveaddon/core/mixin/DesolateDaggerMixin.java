package com.dolthhaven.doltcaveaddon.core.mixin;

import com.dolthhaven.doltcaveaddon.core.other.DCATrackedData;
import com.dolthhaven.doltmetadelights.core.data.dataregistries.DMDEnchantments;
import com.dolthhaven.doltmetadelights.integration.DungeonsDelightIntegration;
import com.github.alexmodguy.alexscaves.server.enchantment.ACEnchantmentRegistry;
import com.github.alexmodguy.alexscaves.server.item.DesolateDaggerItem;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class DesolateDaggerMixin {
    @Inject(method = "releaseUsing", at = @At("HEAD"))
    private void DMH$BallisticReleaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft, CallbackInfo ci) {
        if (DMDEnchantments.canThrowKnife(stack)) {
            throwCleaver(level, entity, stack, timeLeft);
        }
    }

    @Unique
    private void throwCleaver(Level level, LivingEntity entity, ItemStack stack, int timeLeft) {
        if (entity instanceof Player player && stack.getItem() instanceof DesolateDaggerItem daggerItem) {
            if (daggerItem.getUseDuration(stack, entity) - timeLeft >= 6 && !player.getCooldowns().isOnCooldown(stack.getItem())) {
                if (!level.isClientSide) {
                    DungeonsDelightIntegration.makeCleaverAndThrowIt(stack, player, level, daggerItem.getTier().getAttackDamageBonus(),
                            cleaver -> saveToCleaver(cleaver, stack, level));
                }
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }
        }
    }

    @Unique
    private static void saveToCleaver(Entity entity, ItemStack stack, Level level) {
        IDataManager cleaverData = (IDataManager) entity;
        cleaverData.setValue(DCATrackedData.IS_DESOLATE_DAGGER, true);
        cleaverData.setValue(DCATrackedData.LEVEL_IMPENDING_STAB, (byte) ACEnchantmentRegistry.getEnchantmentLevel(level, stack, ACEnchantmentRegistry.IMPENDING_STAB));
        cleaverData.setValue(DCATrackedData.LEVEL_DOUBLE_STAB, (byte) ACEnchantmentRegistry.getEnchantmentLevel(level, stack, ACEnchantmentRegistry.DOUBLE_STAB));
        cleaverData.setValue(DCATrackedData.LEVEL_SATED_BLADE, (byte) ACEnchantmentRegistry.getEnchantmentLevel(level, stack, ACEnchantmentRegistry.SATED_BLADE));
    }
}
