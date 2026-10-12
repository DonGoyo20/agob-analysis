/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 *  net.minecraft.class_5348
 */
package trep.got.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_5348;
import trep.got.screen.AgingBarrelScreenHandler;

@Environment(value=EnvType.CLIENT)
public final class AgingBarrelScreen
extends class_465<AgingBarrelScreenHandler> {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/aging_gui.png");

    public AgingBarrelScreen(AgingBarrelScreenHandler handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
        this.field_2792 = 176;
        this.field_2779 = 166;
        this.field_25270 = this.field_2779 - 92;
    }

    protected void method_25426() {
        super.method_25426();
        this.field_25267 = (this.field_2792 - this.field_22793.method_27525((class_5348)this.field_22785)) / 2;
        this.field_25268 = 3;
    }

    protected void method_2389(class_332 context, float delta, int mouseX, int mouseY) {
        context.method_25291(TEXTURE, this.field_2776, this.field_2800, 0, 0.0f, 0.0f, this.field_2792, this.field_2779, 256, 256);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        super.method_25394(context, mouseX, mouseY, delta);
        this.method_2380(context, mouseX, mouseY);
    }
}

