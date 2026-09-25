package com.dolthhaven.doltcaveaddon.core.registry;

import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DCACriteriaTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, DoltCaveAddon.MOD_ID);

    public static final PlayerTrigger USE_OCTOPUS_TO_PREVENT_VALLUMRAPTOR_TOMFOOLERY = CriteriaTriggers.register("use_octopus_to_prevent_vallumraptor_tomfoolery", new PlayerTrigger());
}
