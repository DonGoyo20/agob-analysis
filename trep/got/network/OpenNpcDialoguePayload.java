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

public record OpenNpcDialoguePayload(int targetEntityId, NpcAttributeProfile attributes) implements class_8710
{
    public static final class_8710.class_9154<OpenNpcDialoguePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"open_npc_dialogue"));
    public static final class_9139<class_9129, OpenNpcDialoguePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId());
        OpenNpcDialoguePayload.writeAttributes(buf, value.attributes());
    }, buf -> new OpenNpcDialoguePayload(buf.method_10816(), OpenNpcDialoguePayload.readAttributes(buf)));

    public OpenNpcDialoguePayload {
        attributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
    }

    public OpenNpcDialoguePayload(int targetEntityId) {
        this(targetEntityId, NpcAttributeProfile.EMPTY);
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

