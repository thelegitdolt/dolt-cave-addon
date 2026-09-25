package com.dolthhaven.doltcaveaddon.integration;

import com.dolthhaven.doltcaveaddon.core.registry.DCACriteriaTriggers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class DCASpawnCompat {
    public static boolean hasOctopus(Level level, BlockEntity entity) {
        try {
            Class<?> octoClass = Class.forName("com.ninni.spawn.server.entity.accessor.ChestBlockEntityAccessor");
            Object a = octoClass.cast(entity);
            UUID playerUUID = (UUID) octoClass.getMethod("getOctopusOwner").invoke(a);
            if (playerUUID != null) {
                if (level.getPlayerByUUID(playerUUID) instanceof ServerPlayer serverPlayer) {
                    DCACriteriaTriggers.USE_OCTOPUS_TO_PREVENT_VALLUMRAPTOR_TOMFOOLERY.trigger(serverPlayer);
                }
                return true;
            }
        } catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }
        return false;
    }

    public static SoundEvent octopusSquirtSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.fromNamespaceAndPath("spawn", "entity.octopus.squirt"));
    }
}
