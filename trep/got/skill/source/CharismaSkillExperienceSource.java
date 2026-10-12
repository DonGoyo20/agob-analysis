/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3222
 */
package trep.got.skill.source;

import net.minecraft.class_3222;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.registry.GotCharacters;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.skill.SkillExperienceService;
import trep.got.skill.source.SkillExperienceSource;

public final class CharismaSkillExperienceSource
implements SkillExperienceSource {
    private static final long NPC_INTERACTION_COOLDOWN_TICKS = 6000L;

    @Override
    public SkillAttributeType type() {
        return SkillAttributeType.CHARISMA;
    }

    public static void onNpcInteraction(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return;
        }
        PlayerSkillProfile profile = ((PlayerSkillHolder)player).got$getSkillProfile();
        long worldTime = player.method_37908().method_8510();
        if (!profile.canTriggerCharismaInteraction(npc.method_5667(), worldTime)) {
            return;
        }
        profile.setCharismaInteractionCooldown(npc.method_5667(), worldTime, 6000L);
        if (player.method_59922().method_43056() && SkillExperienceService.addPoints(player, SkillAttributeType.CHARISMA, 1) > 0) {
            GotCharacters.sendCharacterSelectionState(player);
        }
    }
}

