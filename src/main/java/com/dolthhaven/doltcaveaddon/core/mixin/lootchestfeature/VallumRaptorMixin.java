package com.dolthhaven.doltcaveaddon.core.mixin.lootchestfeature;

import com.dolthhaven.doltcaveaddon.core.DCATrackedData;
import com.dolthhaven.doltcaveaddon.core.FleeingHolder;
import com.github.alexmodguy.alexscaves.server.entity.living.VallumraptorEntity;
import com.github.alexmodguy.alexscaves.server.misc.ACSoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VallumraptorEntity.class)
public abstract class VallumRaptorMixin extends LivingEntity implements FleeingHolder {

    @Shadow private Vec3 fleeFromPosition;

    @Shadow private int fleeTicks;

    protected VallumRaptorMixin(EntityType<? extends LivingEntity> p_20966_, Level p_20967_) {
        super(p_20966_, p_20967_);
    }

    @Override
    public void flee(BlockPos stealPos) {
        this.playSound(ACSoundRegistry.VALLUMRAPTOR_CALL.get(), 1.2f, 1.2f);
        this.fleeFromPosition = Vec3.atCenterOf(stealPos);
        this.fleeTicks = 300 + this.random.nextInt(80);
        DCATrackedData.setVallumraptorChestData(this, false);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void sex(CallbackInfo ci) {
        if ((this.level().getGameTime() & this.uuid.getLeastSignificantBits() % 4096) == 0) {
            DCATrackedData.setVallumraptorChestData(this, true);
        }
    }
}
