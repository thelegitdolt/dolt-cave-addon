package com.dolthhaven.doltcaveaddon.core.registry;

import com.dolthhaven.doltcaveaddon.client.particles.RustScrapeProvider;
import com.dolthhaven.doltcaveaddon.core.DoltCaveAddon;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = DoltCaveAddon.MOD_ID, value = Dist.CLIENT)
public class DCAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, DoltCaveAddon.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RUST_SCRAPE = PARTICLES.register("rust_scrape", () -> new SimpleParticleType(false));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(RUST_SCRAPE.get(), RustScrapeProvider::new);
    }
}

