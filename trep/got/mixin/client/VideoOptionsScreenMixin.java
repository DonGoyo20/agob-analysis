/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_353
 *  net.minecraft.class_446
 *  net.minecraft.class_4667
 *  net.minecraft.class_7172
 *  net.minecraft.class_7172$class_7178
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_353;
import net.minecraft.class_446;
import net.minecraft.class_4667;
import net.minecraft.class_7172;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.announcement.GotInvasionAnnouncementSettings;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_4667.class})
public abstract class VideoOptionsScreenMixin {
    @Shadow
    protected class_353 field_51824;
    @Unique
    private class_7172<Boolean> agob$invasionAnnouncementsOption;

    @Inject(method={"method_60329"}, at={@At(value="TAIL")})
    private void agob$addInvasionAnnouncementsOption(CallbackInfo ci) {
        if (!(this instanceof class_446)) {
            return;
        }
        class_310 client = class_310.method_1551();
        this.agob$invasionAnnouncementsOption = new class_7172("options.agob.invasion_announcements", class_7172.method_42399(), class_7172.field_41333, (class_7172.class_7178)class_7172.field_38278, (Object)GotInvasionAnnouncementSettings.isEnabled(), value -> GotInvasionAnnouncementSettings.setEnabled(client, value));
        this.field_51824.method_20406(this.agob$invasionAnnouncementsOption);
    }
}

