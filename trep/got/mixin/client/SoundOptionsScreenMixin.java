/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_353
 *  net.minecraft.class_443
 *  net.minecraft.class_4667
 *  net.minecraft.class_7172
 *  net.minecraft.class_7172$class_7173
 *  net.minecraft.class_7172$class_7178
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import com.mojang.serialization.Codec;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_353;
import net.minecraft.class_443;
import net.minecraft.class_4667;
import net.minecraft.class_7172;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.audio.GotMusicMode;
import trep.got.client.audio.GotMusicSettings;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_4667.class})
public abstract class SoundOptionsScreenMixin {
    @Shadow
    protected class_353 field_51824;
    @Unique
    private class_7172<GotMusicMode> agob$musicModeOption;

    @Inject(method={"method_60329"}, at={@At(value="TAIL")})
    private void agob$addMusicModeOption(CallbackInfo ci) {
        if (!(this instanceof class_443)) {
            return;
        }
        class_310 client = class_310.method_1551();
        this.agob$musicModeOption = this.agob$createMusicModeOption(client);
        this.field_51824.method_20406(this.agob$musicModeOption);
    }

    @Unique
    private class_7172<GotMusicMode> agob$createMusicModeOption(class_310 client) {
        return new class_7172("options.agob.music_mode", class_7172.method_42399(), (optionText, value) -> value.getDisplayText(), (class_7172.class_7178)new class_7172.class_7173(List.of(GotMusicMode.values()), Codec.STRING.xmap(GotMusicMode::byId, GotMusicMode::getId)), (Object)GotMusicSettings.getMusicMode(), value -> GotMusicSettings.setMusicMode(client, value));
    }
}

