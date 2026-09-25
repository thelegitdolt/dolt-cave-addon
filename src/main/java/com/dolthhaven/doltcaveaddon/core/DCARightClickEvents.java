package com.dolthhaven.doltcaveaddon.core;

import com.dolthhaven.doltcaveaddon.core.networking.BlockFaceParticlePayload;
import com.github.alexmodguy.alexscaves.server.block.ACBlockRegistry;
import com.github.alexmodguy.alexscaves.server.block.AcidBlock;
import com.github.alexmodguy.alexscaves.server.block.MetalBarrelBlock;
import com.github.alexmodguy.alexscaves.server.block.blockentity.MetalBarrelBlockEntity;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;

@EventBusSubscriber(modid = DoltCaveAddon.MOD_ID)
public class DCARightClickEvents {
    @SubscribeEvent
    private static void tryUnrustRustyStuff(PlayerInteractEvent.RightClickBlock event) {

        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        HashMap<Block, Block> UNRUST_MAP = Util.make(new HashMap<>(), map -> {
            map.put(ACBlockRegistry.RUSTY_BARREL.get(), ACBlockRegistry.METAL_BARREL.get());
        });

        if (stack.canPerformAction(ItemAbilities.AXE_SCRAPE) && UNRUST_MAP.containsKey(state.getBlock())) {
            for (Direction dir : Direction.Plane.HORIZONTAL)
                if (level.getBlockState(pos.relative(dir)).getBlock() instanceof AcidBlock) return;

            BlockState newState = UNRUST_MAP.get(state.getBlock()).defaultBlockState();

            for(Property prop : newState.getProperties()) {
                newState = state.hasProperty(prop) ? newState.setValue(prop, state.getValue(prop)) : newState;
            }

            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);



            if (player instanceof ServerPlayer serverPlayer)
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS);
            ParticleUtils.spawnParticlesOnBlockFaces(event.getLevel(), pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));

//            if (player instanceof ServerPlayer)
//                PacketDistributor.sendToPlayer((ServerPlayer) player, new BlockFaceParticlePayload(pos));

            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));


            stack.hurtAndBreak(1, player, event.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            event.setCanceled(true);
        }
    }

}
