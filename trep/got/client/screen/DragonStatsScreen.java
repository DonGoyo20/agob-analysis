/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 *  net.minecraft.class_490
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_490;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.DragonEntity;
import trep.got.screen.DragonStatsScreenHandler;

@Environment(value=EnvType.CLIENT)
public final class DragonStatsScreen
extends class_465<DragonStatsScreenHandler> {
    private static final class_2960 TEXTURE = class_2960.method_60656((String)"textures/gui/container/horse.png");
    private float mouseX;
    private float mouseY;

    public DragonStatsScreen(DragonStatsScreenHandler handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    protected void method_2389(class_332 context, float delta, int mouseX, int mouseY) {
        int x = (this.field_22789 - this.field_2792) / 2;
        int y = (this.field_22790 - this.field_2779) / 2;
        context.method_25302(TEXTURE, x, y, 0, 0, this.field_2792, this.field_2779);
        DragonEntity dragon = this.getDragon();
        if (dragon != null) {
            class_490.method_2486((class_332)context, (int)(x + 26), (int)(y + 18), (int)(x + 78), (int)(y + 70), (int)9, (float)0.25f, (float)this.mouseX, (float)this.mouseY, (class_1309)dragon);
        }
        this.drawStats(context, x, y);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        super.method_25394(context, mouseX, mouseY, delta);
        this.method_2380(context, mouseX, mouseY);
    }

    private void drawStats(class_332 context, int x, int y) {
        String ageMax = ((DragonStatsScreenHandler)this.field_2797).getAgeMaxDays() < 0 ? "-" : Integer.toString(((DragonStatsScreenHandler)this.field_2797).getAgeMaxDays());
        context.method_51433(this.field_22793, "Age: " + ((DragonStatsScreenHandler)this.field_2797).getAgeDays() + " / " + ageMax, x + 84, y + 28, 0x404040, false);
        context.method_51433(this.field_22793, "Hunger: " + ((DragonStatsScreenHandler)this.field_2797).getHunger() + " / " + ((DragonStatsScreenHandler)this.field_2797).getHungerMax(), x + 84, y + 44, 0x404040, false);
    }

    @Nullable
    private DragonEntity getDragon() {
        DragonEntity dragon;
        if (((DragonStatsScreenHandler)this.field_2797).getDragon() != null) {
            return ((DragonStatsScreenHandler)this.field_2797).getDragon();
        }
        class_310 client = class_310.method_1551();
        if (client.field_1687 == null) {
            return null;
        }
        class_1297 entity = client.field_1687.method_8469(((DragonStatsScreenHandler)this.field_2797).getEntityId());
        return entity instanceof DragonEntity ? (dragon = (DragonEntity)entity) : null;
    }
}

