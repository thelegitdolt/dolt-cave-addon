package com.dolthhaven.doltcaveaddon.core.mixin;

import com.github.alexmodguy.alexscaves.server.item.RadioactiveOnDestroyedBlockItem;
import com.ninni.etcetera.block.entity.PricklyCanBlockEntity;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(ServerGamePacketListenerImpl.class)
public class C2SPricklyCanMixin {
    @Shadow
    public ServerPlayer player;

    @Inject(method = "handleContainerButtonClick", at = @At(value = "HEAD"), cancellable = true)
    private void DoltModHow$ExplodeIfYouSeeRadioactiveStuff(ServerboundContainerButtonClickPacket packet, CallbackInfo ci) {
        if (this.player.containerMenu instanceof PricklyCanScreenHandler menu && packet.buttonId() == 1) {
            if (menu.getInventory() instanceof PricklyCanBlockEntity can) {
                for (int i = 0; i < menu.getInventory().getContainerSize(); i++) {
                    if (explodePricklyCan(menu.getInventory().getItem(i), i, can)) {
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Unique
    private static boolean explodePricklyCan(ItemStack stack, int index, PricklyCanBlockEntity can) {
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
