/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_368
 *  net.minecraft.class_368$class_369
 *  net.minecraft.class_374
 *  net.minecraft.class_7923
 */
package trep.got.client.toast;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_368;
import net.minecraft.class_374;
import net.minecraft.class_7923;
import trep.got.client.util.ClientTextureHelper;

@Environment(value=EnvType.CLIENT)
public final class ModAdvancementToast
implements class_368 {
    private static final class_2960 TEXTURE = class_2960.method_60656((String)"toast/advancement");
    private static final long APPEAR_ANIMATION_MS = 600L;
    private static final long FULLY_VISIBLE_HOLD_MS = 1500L;
    private static final long DISPLAY_DURATION_MS = 2100L;
    private static final float PRIMARY_WAR_TITLE_SCALE = 1.12f;
    private final String typeId;
    private final class_2561 title;
    private final class_2561 description;
    private final class_1799 iconStack;
    private final int titleColor;
    private final int descriptionColor;
    private final class_2960 topTexture;
    private final class_2960 bottomTexture;

    public ModAdvancementToast(String typeId, String title, String description, String iconItemId, int titleColor, int descriptionColor, String topTextureId, String bottomTextureId) {
        this.typeId = typeId;
        this.title = class_2561.method_43470((String)title);
        this.description = class_2561.method_43470((String)description);
        this.iconStack = ModAdvancementToast.resolveIcon(iconItemId);
        this.titleColor = titleColor;
        this.descriptionColor = descriptionColor;
        this.topTexture = ModAdvancementToast.resolveTexture(topTextureId);
        this.bottomTexture = ModAdvancementToast.resolveTexture(bottomTextureId);
    }

    public class_368.class_369 method_1986(class_332 context, class_374 manager, long startTime) {
        context.method_52706(TEXTURE, 0, 0, this.method_29049(), this.method_29050());
        class_310 client = manager.method_1995();
        if (this.topTexture != null || this.bottomTexture != null) {
            this.drawWarStyleToast(context, client);
        } else {
            context.method_51439(client.field_1772, this.title, 30, 8, this.resolveTitleColor(), false);
            context.method_51439(client.field_1772, this.description, 30, 20, this.descriptionColor, false);
            context.method_51445(this.iconStack, 8, 8);
        }
        return startTime >= 2100L ? class_368.class_369.field_2209 : class_368.class_369.field_2210;
    }

    private void drawWarStyleToast(class_332 context, class_310 client) {
        int topY = 5;
        int bottomY = 20;
        int topIconX = 8;
        int textX = 20;
        int bottomPrefixX = 20;
        String bottomPrefix = switch (this.typeId) {
            case "kingdom_war_peace" -> "Peace with ";
            default -> "War on ";
        };
        int bottomPrefixWidth = client.field_1772.method_1727(bottomPrefix);
        int bottomIconX = bottomPrefixX + bottomPrefixWidth;
        int bottomTextX = bottomIconX + 12;
        if (this.topTexture != null) {
            ModAdvancementToast.drawFittedTexture(context, client, this.topTexture, topIconX, topY, 10, 10);
        }
        ModAdvancementToast.drawScaledText(context, client, this.title, textX, topY, this.resolveTitleColor(), 1.12f);
        context.method_51439(client.field_1772, (class_2561)class_2561.method_43470((String)bottomPrefix), bottomPrefixX, bottomY + 1, this.descriptionColor, false);
        if (this.bottomTexture != null) {
            ModAdvancementToast.drawFittedTexture(context, client, this.bottomTexture, bottomIconX, bottomY, 10, 10);
        }
        context.method_51439(client.field_1772, this.description, bottomTextX, bottomY + 1, this.descriptionColor, false);
    }

    private static void drawFittedTexture(class_332 context, class_310 client, class_2960 texture, int x, int y, int boxWidth, int boxHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(client, texture);
        float scale = Math.min((float)boxWidth / (float)size.width(), (float)boxHeight / (float)size.height());
        int drawWidth = Math.max(1, Math.round((float)size.width() * scale));
        int drawHeight = Math.max(1, Math.round((float)size.height() * scale));
        int drawX = x + (boxWidth - drawWidth) / 2;
        int drawY = y + (boxHeight - drawHeight) / 2;
        context.method_25293(texture, drawX, drawY, drawWidth, drawHeight, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
    }

    private static void drawScaledText(class_332 context, class_310 client, class_2561 text, int x, int y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51439(client.field_1772, text, 0, 0, color, false);
        context.method_51448().method_22909();
    }

    private int resolveTitleColor() {
        if (this.titleColor != 0) {
            return this.titleColor;
        }
        return switch (this.typeId) {
            case "quest_accepted" -> -10638849;
            case "quest_completed" -> -10497171;
            default -> -11187;
        };
    }

    private static class_1799 resolveIcon(String iconItemId) {
        class_2960 itemId = class_2960.method_12829((String)iconItemId);
        if (itemId == null) {
            return new class_1799((class_1935)class_1802.field_8288);
        }
        return class_7923.field_41178.method_17966(itemId).map(class_1799::new).orElseGet(() -> new class_1799((class_1935)class_1802.field_8288));
    }

    private static class_2960 resolveTexture(String textureId) {
        if (textureId == null || textureId.isBlank()) {
            return null;
        }
        return class_2960.method_12829((String)textureId);
    }
}

