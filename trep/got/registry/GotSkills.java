/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.minecraft.class_1799
 *  net.minecraft.class_3222
 */
package trep.got.registry;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import trep.got.GotMod;
import trep.got.registry.GotSkillAttributes;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillDataLoader;
import trep.got.skill.SkillLevelCurve;
import trep.got.skill.source.GotSkillExperienceSources;

public final class GotSkills {
    private static SkillLevelCurve levelCurve;

    private GotSkills() {
    }

    public static void initialize() {
        levelCurve = SkillDataLoader.loadLevelCurve();
        GotSkillAttributes.initialize();
        GotSkillExperienceSources.initialize();
        ServerPlayerEvents.COPY_FROM.register(GotSkills::copyPlayerSkills);
    }

    public static SkillLevelCurve getLevelCurve() {
        if (levelCurve == null) {
            throw new IllegalStateException("Skill system has not been initialized");
        }
        return levelCurve;
    }

    private static void copyPlayerSkills(class_3222 oldPlayer, class_3222 newPlayer, boolean alive) {
        PlayerSkillProfile source = ((PlayerSkillHolder)oldPlayer).got$getSkillProfile();
        ((PlayerSkillHolder)newPlayer).got$getSkillProfile().copyFrom(source);
        GotMod.LOGGER.debug("Copied skill profile for {}", (Object)newPlayer.method_7334().getName());
    }

    public static void tick(class_3222 player) {
        GotSkillExperienceSources.tick(player);
    }

    public static void onFoodConsumed(class_3222 player, class_1799 stack) {
        GotSkillExperienceSources.onFoodConsumed(player, stack);
    }
}

