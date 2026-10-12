/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_638
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  net.minecraft.class_8685
 */
package trep.got.client.character;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_8685;
import trep.got.character.PlayerSkinBuilderProfile;

@Environment(value=EnvType.CLIENT)
public final class CharacterPreviewPlayerEntity
extends class_742 {
    private static final class_2561 EMPTY_NAME = class_2561.method_43473();
    private final class_8685 skinTextures;
    private final PlayerSkinBuilderProfile skinBuilderProfile;

    public CharacterPreviewPlayerEntity(class_638 world, class_8685 skinTextures, class_746 sourcePlayer) {
        this(world, skinTextures, sourcePlayer, PlayerSkinBuilderProfile.DEFAULT);
    }

    public CharacterPreviewPlayerEntity(class_638 world, class_8685 skinTextures, class_746 sourcePlayer, PlayerSkinBuilderProfile skinBuilderProfile) {
        super(world, new GameProfile(UUID.nameUUIDFromBytes(("agob-preview:" + String.valueOf(skinTextures.comp_1626())).getBytes()), "got_mod_preview"));
        this.skinTextures = skinTextures;
        this.skinBuilderProfile = skinBuilderProfile == null ? PlayerSkinBuilderProfile.DEFAULT : skinBuilderProfile;
        this.copySourceState(sourcePlayer);
    }

    public class_8685 method_52814() {
        return this.skinTextures;
    }

    public PlayerSkinBuilderProfile getSkinBuilderProfile() {
        return this.skinBuilderProfile;
    }

    public boolean method_7325() {
        return false;
    }

    public boolean method_7337() {
        return false;
    }

    public boolean method_5733() {
        return false;
    }

    public boolean method_16914() {
        return false;
    }

    public class_2561 method_5797() {
        return null;
    }

    public class_2561 method_5477() {
        return EMPTY_NAME;
    }

    public class_2561 method_5476() {
        return EMPTY_NAME;
    }

    public String method_5820() {
        return "";
    }

    private void copySourceState(class_746 sourcePlayer) {
        this.method_5880(false);
        this.method_18380(sourcePlayer.method_18376());
        this.method_24830(sourcePlayer.method_24828());
        this.method_5660(sourcePlayer.method_5715());
        this.method_5728(sourcePlayer.method_5624());
        this.method_5796(sourcePlayer.method_5681());
        this.method_6019(sourcePlayer.method_6058());
        this.method_6033(sourcePlayer.method_6032());
        this.field_6235 = sourcePlayer.field_6235;
        this.field_6251 = sourcePlayer.field_6251;
        this.field_6279 = sourcePlayer.field_6279;
        this.method_7283(sourcePlayer.method_6068());
        this.method_5636(sourcePlayer.field_6283);
        this.method_36456(sourcePlayer.method_36454());
        this.method_36457(sourcePlayer.method_36455());
        this.field_6220 = sourcePlayer.field_6220;
        this.field_5982 = sourcePlayer.field_5982;
        this.field_6004 = sourcePlayer.field_6004;
        this.field_6241 = sourcePlayer.field_6241;
        this.field_6259 = sourcePlayer.field_6259;
        this.field_42108.method_48567(sourcePlayer.field_42108.method_48566());
    }
}

