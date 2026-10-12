/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 *  net.minecraft.class_525
 *  net.minecraft.class_5321
 *  net.minecraft.class_7145
 *  net.minecraft.class_7924
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_525;
import net.minecraft.class_5321;
import net.minecraft.class_7145;
import net.minecraft.class_7924;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_525.class})
public class CreateWorldScreenMixin {
    private static final class_5321<class_7145> GOT_DEFAULT_WORLD_PRESET = class_5321.method_29179((class_5321)class_7924.field_41250, (class_2960)class_2960.method_60655((String)"agob", (String)"westeros"));

    @ModifyArg(method={"method_31130"}, at=@At(value="INVOKE", target="Ljava/util/Optional;of(Ljava/lang/Object;)Ljava/util/Optional;"))
    private static Object got$useWesterosWorldPreset(Object ignoredDefaultPreset) {
        return GOT_DEFAULT_WORLD_PRESET;
    }
}

