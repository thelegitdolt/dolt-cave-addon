package com.dolthhaven.doltcaveaddon.core;

import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.LivingEntity;

public class DCATrackedData {
    public static final TrackedData<Boolean> VALLUMRAPTOR_CAN_OPEN_CHESTS = TrackedData.Builder.create(ByteBufCodecs.BOOL, () -> true).build();

    public static void registerTrackedData() {
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("vallumraptor_can_open_chest"), VALLUMRAPTOR_CAN_OPEN_CHESTS);
    }

    public static boolean getVallumraptorChestData(LivingEntity entity) {
        return ((IDataManager) entity).getValue(VALLUMRAPTOR_CAN_OPEN_CHESTS);
    }

    public static void setVallumraptorChestData(LivingEntity entity, boolean set) {
        ((IDataManager) entity).setValue(VALLUMRAPTOR_CAN_OPEN_CHESTS, set);
    }
}
