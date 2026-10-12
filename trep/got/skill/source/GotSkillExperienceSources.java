/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_3222
 */
package trep.got.skill.source;

import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import trep.got.skill.source.CharismaSkillExperienceSource;
import trep.got.skill.source.HealthSkillExperienceSource;
import trep.got.skill.source.IntelligenceSkillExperienceSource;
import trep.got.skill.source.LuckSkillExperienceSource;
import trep.got.skill.source.RidingSkillExperienceSource;
import trep.got.skill.source.SkillExperienceSource;
import trep.got.skill.source.SmithingSkillExperienceSource;
import trep.got.skill.source.TradingSkillExperienceSource;

public final class GotSkillExperienceSources {
    private static final List<SkillExperienceSource> SOURCES = List.of(new HealthSkillExperienceSource(), new IntelligenceSkillExperienceSource(), new CharismaSkillExperienceSource(), new LuckSkillExperienceSource(), new TradingSkillExperienceSource(), new SmithingSkillExperienceSource(), new RidingSkillExperienceSource());

    private GotSkillExperienceSources() {
    }

    public static void initialize() {
        for (SkillExperienceSource source : SOURCES) {
            source.register();
        }
    }

    public static void tick(class_3222 player) {
        for (SkillExperienceSource source : SOURCES) {
            source.tick(player);
        }
    }

    public static void onFoodConsumed(class_3222 player, class_1799 stack) {
        for (SkillExperienceSource source : SOURCES) {
            source.onFoodConsumed(player, stack);
        }
    }
}

