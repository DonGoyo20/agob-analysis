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

public record UpdatePlayerSkinBuilderPayload(PlayerSkinBuilderProfile profile) implements class_8710
{
    public static final class_8710.class_9154<UpdatePlayerSkinBuilderPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"update_player_skin_builder"));
    public static final class_9139<class_9129, UpdatePlayerSkinBuilderPayload> CODEC = class_8710.method_56484((value, buf) -> value.profile.write((class_9129)buf), buf -> new UpdatePlayerSkinBuilderPayload(PlayerSkinBuilderProfile.read(buf)));

    public UpdatePlayerSkinBuilderPayload(PlayerSkinBuilderProfile profile) {
        this.profile = profile = profile == null ? PlayerSkinBuilderProfile.DEFAULT : profile;
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

