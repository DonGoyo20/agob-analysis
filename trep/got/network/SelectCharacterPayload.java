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

import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.character.PlayerSkinBuilderProfile;

public record SelectCharacterPayload(String religionId, String kingdomId, String optionId, String geneticTraitId, String[] personalityTraitIds, PlayerSkinBuilderProfile skinBuilderProfile) implements class_8710
{
    public static final class_8710.class_9154<SelectCharacterPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"select_character"));
    public static final class_9139<class_9129, SelectCharacterPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.religionId);
        buf.method_10814(value.kingdomId);
        buf.method_10814(value.optionId);
        buf.method_10814(value.geneticTraitId);
        buf.method_10804(value.personalityTraitIds.length);
        for (String traitId : value.personalityTraitIds) {
            buf.method_10814(traitId);
        }
        value.skinBuilderProfile.write((class_9129)buf);
    }, buf -> {
        String religionId = buf.method_19772();
        String kingdomId = buf.method_19772();
        String optionId = buf.method_19772();
        String geneticTraitId = buf.method_19772();
        int personalityTraitCount = buf.method_10816();
        String[] personalityTraitIds = new String[personalityTraitCount];
        for (int index = 0; index < personalityTraitCount; ++index) {
            personalityTraitIds[index] = buf.method_19772();
        }
        return new SelectCharacterPayload(religionId, kingdomId, optionId, geneticTraitId, personalityTraitIds, PlayerSkinBuilderProfile.read(buf));
    });

    public SelectCharacterPayload {
        skinBuilderProfile = skinBuilderProfile == null ? PlayerSkinBuilderProfile.DEFAULT : skinBuilderProfile;
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

