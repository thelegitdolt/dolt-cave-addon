package com.dolthhaven.doltcaveaddon.core.mixin;

import com.github.alexmodguy.alexscaves.server.item.RadioactiveOnDestroyedBlockItem;
import com.ninni.etcetera.block.entity.PricklyCanBlockEntity;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreen;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreenHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.ninni.etcetera.client.gui.screen.PricklyCanScreen.DeleteButtonWidget")
public class C2SPricklyCanMixin {
    @Shadow
    @Final
    private PricklyCanScreen this$0;

    @Inject(method = "onPress", at = @At(value = "HEAD"))
    private void DoltModHow$ExplodeIfYouSeeRadioactiveStuff(CallbackInfo ci) {
        if (this.this$0.getMenu() instanceof PricklyCanScreenHandler menu && menu.getInventory() instanceof Container container) {
            for (int i = 0; i <  container.getContainerSize(); i++) {
                ItemStack stack = container.getItem(i);
//
//                if (explodePricklyCan(stack, i, )) {
//                    return;
//                }
            }
        }
    }

    @Unique
    private static boolean explodePricklyCan(ItemStack stack, int index, RandomizableContainer can) {
        if (stack.getItem() instanceof RadioactiveOnDestroyedBlockItem radioactiveItem) {
            Level level = can.getLevel();
            BlockPos pos = can.getBlockPos();
            if (level != null) {
                can.removeItem(index, index);
                radioactiveItem.onDestroyed(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack),
                                level.damageSources().cactus());
                level.destroyBlock(pos, true);
                return true;
            }
        }
        return false;
    }
}
