/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.character.NpcAttributeProfile;
import trep.got.skill.SkillAttributeType;

public record NpcDialogueRelationPayload(int targetEntityId, String npcId, int relation, String kingdomId, String dialogueContextId, String dialogueSubjectName, NpcAttributeProfile attributes, boolean titleChallengeAvailable, int titleChallengeCooldownSeconds, boolean tourneyAvailable, boolean tourneyAttended) implements class_8710
{
    public static final class_8710.class_9154<NpcDialogueRelationPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"npc_dialogue_relation"));
    public static final class_9139<class_9129, NpcDialogueRelationPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId);
        buf.method_10814(value.npcId);
        buf.method_10804(value.relation + 100);
        buf.method_10814(value.kingdomId);
        buf.method_10814(value.dialogueContextId);
        buf.method_10814(value.dialogueSubjectName);
        NpcDialogueRelationPayload.writeAttributes(buf, value.attributes);
        buf.method_52964(value.titleChallengeAvailable);
        buf.method_10804(Math.max(0, value.titleChallengeCooldownSeconds));
        buf.method_52964(value.tourneyAvailable);
        buf.method_52964(value.tourneyAttended);
    }, buf -> new NpcDialogueRelationPayload(buf.method_10816(), buf.method_19772(), buf.method_10816() - 100, buf.method_19772(), buf.method_19772(), buf.method_19772(), NpcDialogueRelationPayload.readAttributes(buf), buf.readBoolean(), buf.method_10816(), buf.readBoolean(), buf.readBoolean()));

    public NpcDialogueRelationPayload {
        kingdomId = kingdomId == null ? "" : kingdomId;
        dialogueContextId = dialogueContextId == null ? "" : dialogueContextId;
        dialogueSubjectName = dialogueSubjectName == null ? "" : dialogueSubjectName;
        attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
        titleChallengeCooldownSeconds = Math.max(0, titleChallengeCooldownSeconds);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    private static void writeAttributes(class_9129 buf, NpcAttributeProfile attributes) {
        NpcAttributeProfile profile = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
        buf.method_10814(profile.geneticTraitId());
        buf.method_10804(profile.personalityTraitIds().size());
        for (String string : profile.personalityTraitIds()) {
            buf.method_10814(string);
        }
        buf.method_10804(profile.skillPoints().size());
        for (Map.Entry entry : profile.skillPoints().entrySet()) {
            buf.method_10814(((SkillAttributeType)((Object)entry.getKey())).getId());
            buf.method_10804(((Integer)entry.getValue()).intValue());
        }
    }

    private static NpcAttributeProfile readAttributes(class_9129 buf) {
        String geneticTraitId = buf.method_19772();
        int personalityCount = buf.method_10816();
        ArrayList<String> personalityTraits = new ArrayList<String>();
        for (int index = 0; index < personalityCount; ++index) {
            personalityTraits.add(buf.method_19772());
        }
        int skillCount = buf.method_10816();
        EnumMap<SkillAttributeType, Integer> skillPoints = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (int index = 0; index < skillCount; ++index) {
            SkillAttributeType type = SkillAttributeType.byId(buf.method_19772());
            int value = buf.method_10816();
            if (type == null) continue;
            skillPoints.put(type, value);
        }
        return new NpcAttributeProfile(geneticTraitId, personalityTraits, skillPoints);
    }
}

