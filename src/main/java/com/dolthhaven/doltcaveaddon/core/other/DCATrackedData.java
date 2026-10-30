package com.dolthhaven.doltcaveaddon.core.other;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import com.teamabnormals.blueprint.common.world.storage.tracking.IDataManager;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedData;
import com.teamabnormals.blueprint.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.LivingEntity;

public class DCATrackedData {
    public static final TrackedData<Boolean> VALLUMRAPTOR_CAN_OPEN_CHESTS = TrackedData.Builder.create(ByteBufCodecs.BOOL, () -> true).build();

    public static final TrackedData<Byte> LEVEL_IMPENDING_STAB = TrackedData.Builder.create(ByteBufCodecs.BYTE, () -> (byte) 0).build();
    public static final TrackedData<Byte> LEVEL_DOUBLE_STAB = TrackedData.Builder.create(ByteBufCodecs.BYTE, () -> (byte) 0).build();
    public static final TrackedData<Byte> LEVEL_SATED_BLADE = TrackedData.Builder.create(ByteBufCodecs.BYTE, () -> (byte) 0).build();
    public static final TrackedData<Boolean> IS_DESOLATE_DAGGER = TrackedData.Builder.create(ByteBufCodecs.BOOL, () -> false).build();


    public static void registerTrackedData() {
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("vallumraptor_can_open_chest"), VALLUMRAPTOR_CAN_OPEN_CHESTS);
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("desolate"), IS_DESOLATE_DAGGER);
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("level_double_stab"), LEVEL_DOUBLE_STAB);
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("level_sated_blade"), LEVEL_SATED_BLADE);
        TrackedDataManager.INSTANCE.registerData(DoltCaveAddon.rl("level_impending_stab"), LEVEL_IMPENDING_STAB);
    }

    public static boolean getVallumraptorChestData(LivingEntity entity) {
        return ((IDataManager) entity).getValue(VALLUMRAPTOR_CAN_OPEN_CHESTS);
    }

    public static void setVallumraptorChestData(LivingEntity entity, boolean set) {
        ((IDataManager) entity).setValue(VALLUMRAPTOR_CAN_OPEN_CHESTS, set);
    }
}
