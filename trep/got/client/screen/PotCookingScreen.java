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
import trep.got.screen.PotCookingScreenHandler;

@Environment(value=EnvType.CLIENT)
public final class PotCookingScreen
extends class_465<PotCookingScreenHandler> {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/ui/cooking_gui.png");

    public PotCookingScreen(PotCookingScreenHandler handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
        this.field_2792 = 175;
        this.field_2779 = 165;
        this.field_25270 = this.field_2779 - 94;
    }

    protected void method_25426() {
        super.method_25426();
        this.field_25267 = (this.field_2792 - this.field_22793.method_27525((class_5348)this.field_22785)) / 2;
        this.field_25268 = 6;
    }

    protected void method_2389(class_332 context, float delta, int mouseX, int mouseY) {
        int x = this.field_2776;
        int y = this.field_2800;
        context.method_25291(TEXTURE, x, y, 0, 0.0f, 0.0f, this.field_2792, this.field_2779, 256, 256);
        int progress = ((PotCookingScreenHandler)this.field_2797).getScaledProgress();
        context.method_25290(TEXTURE, x + 96, y + 34, 176.0f, 0.0f, progress + 1, 17, 256, 256);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_25420(context, mouseX, mouseY, delta);
        super.method_25394(context, mouseX, mouseY, delta);
        this.method_2380(context, mouseX, mouseY);
    }
}

