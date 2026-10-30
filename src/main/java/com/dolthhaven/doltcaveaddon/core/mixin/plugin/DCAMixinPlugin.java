package com.dolthhaven.doltcaveaddon.core.mixin.plugin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class DCAMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {}

    private static boolean etceteraloaded() {
        try {
            Class.forName("com.ninni.etcetera.Etcetera");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean doltsmetadelightsanddungeonsdelightloaded() {
        try {
            Class.forName("com.dolthhaven.doltmetadelights.DoltsMetadelights");
            Class.forName("net.yirmiri.dungeonsdelight.DungeonsDelight");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("com.dolthhaven.doltcaveaddon.core.mixin.C2SPricklyCanMixin")) {
            return etceteraloaded();
        }
        if (mixinClassName.equals("com.dolthhaven.doltcaveaddon.core.mixin.dungeonsdelight.CleaverEntityMixin")) {
            return doltsmetadelightsanddungeonsdelightloaded();
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
