/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_353
 *  net.minecraft.class_440
 *  net.minecraft.class_4667
 *  net.minecraft.class_7172
 *  net.minecraft.class_7172$class_7178
 *  net.minecraft.class_8710
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_353;
import net.minecraft.class_440;
import net.minecraft.class_4667;
import net.minecraft.class_7172;
import net.minecraft.class_8710;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.character.PlayerSkinBuilderOptions;
import trep.got.network.UpdatePlayerSkinBuilderPayload;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_4667.class})
public abstract class SkinOptionsScreenMixin {
    @Shadow
    protected class_353 field_51824;
    @Unique
    private class_7172<Boolean> agob$baseSkinOption;

    @Inject(method={"method_60329"}, at={@At(value="TAIL")})
    private void agob$addBaseSkinOption(CallbackInfo ci) {
        if (!(this instanceof class_440)) {
            return;
        }
        class_310 client = class_310.method_1551();
        this.agob$baseSkinOption = new class_7172("options.agob.base_skin", class_7172.method_42399(), (optionText, value) -> class_2561.method_43470((String)("AGoB Base Skin: " + (value != false ? "NPC" : "Player"))), (class_7172.class_7178)class_7172.field_38278, (Object)CharacterSelectionClientState.getSkinBuilderProfile().npcBaseSkin(), value -> {
            PlayerSkinBuilderProfile profile = CharacterSelectionClientState.getSkinBuilderProfile().withNpcBaseSkin((boolean)value);
            if (profile.npcBaseSkin()) {
                List<PlayerSkinBuilderOptions.LayerOption> faces = PlayerSkinBuilderOptions.faces(profile.gender());
                List<PlayerSkinBuilderOptions.LayerOption> shirts = PlayerSkinBuilderOptions.shirts(false);
                List<PlayerSkinBuilderOptions.LayerOption> pants = PlayerSkinBuilderOptions.pants(false);
                if (!faces.isEmpty() && profile.faceId().isBlank()) {
                    profile = profile.withFaceId(faces.get(0).id());
                }
                if (!shirts.isEmpty() && profile.shirtId().isBlank()) {
                    profile = profile.withShirtId(shirts.get(0).id());
                }
                if (!pants.isEmpty() && profile.pantsId().isBlank()) {
                    profile = profile.withPantsId(pants.get(0).id());
                }
            }
            CharacterSelectionClientState.setSkinBuilderProfile(profile);
            if (client.method_1562() != null) {
                ClientPlayNetworking.send((class_8710)new UpdatePlayerSkinBuilderPayload(profile));
            }
        });
        this.field_51824.method_20406(this.agob$baseSkinOption);
    }
}

