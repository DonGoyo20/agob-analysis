/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4185
 *  net.minecraft.class_4185$class_4241
 *  net.minecraft.class_7919
 */
package trep.got.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_7919;

@Environment(value=EnvType.CLIENT)
public class CharacterMenuButtonWidget
extends class_4185 {
    private static final int FRAME_SIZE = 19;
    private static final int ICON_SIZE = 16;
    private static final class_2960 FRAME_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_frame.png");
    private static final class_2960 ACTIVE_FRAME_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/icon_frame_active.png");
    private final class_2960 iconTexture;

    public CharacterMenuButtonWidget(int x, int y, class_2960 iconTexture, class_2561 tooltipText, class_4185.class_4241 onPress) {
        super(x, y, 19, 19, (class_2561)class_2561.method_43473(), onPress, field_40754);
        this.iconTexture = iconTexture;
        this.method_47400(class_7919.method_47407((class_2561)tooltipText));
    }

    public void method_48579(class_332 context, int mouseX, int mouseY, float delta) {
        class_2960 frameTexture = this.method_25367() || this.method_49606() ? ACTIVE_FRAME_TEXTURE : FRAME_TEXTURE;
        context.method_25291(frameTexture, this.method_46426(), this.method_46427(), 0, 0.0f, 0.0f, 19, 19, 19, 19);
        int iconX = this.method_46426() + 2;
        int iconY = this.method_46427() + 1;
        context.method_25291(this.iconTexture, iconX, iconY, 0, 0.0f, 0.0f, 16, 16, 16, 16);
    }
}

