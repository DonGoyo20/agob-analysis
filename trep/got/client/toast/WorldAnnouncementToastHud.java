/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_7923
 *  net.minecraft.class_9779
 */
package trep.got.client.toast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_7923;
import net.minecraft.class_9779;
import trep.got.client.util.ClientTextureHelper;

@Environment(value=EnvType.CLIENT)
public final class WorldAnnouncementToastHud {
    private static final class_2960 BACKGROUND = class_2960.method_60655((String)"agob", (String)"textures/ui/world_announcement.png");
    private static final int TEXTURE_WIDTH = 128;
    private static final int TEXTURE_HEIGHT = 48;
    private static final float SCALE = 0.41f;
    private static final int DRAW_WIDTH = Math.round(52.48f);
    private static final int DRAW_HEIGHT = Math.round(19.68f);
    private static final int MAX_VISIBLE = 5;
    private static final int EDGE_MARGIN = 8;
    private static final int STACK_GAP = 3;
    private static final long ENTER_MS = 420L;
    private static final long HOLD_MS = 3600L;
    private static final long EXIT_MS = 420L;
    private static final long DISPLAY_MS = 4440L;
    private static final long SPAWN_DELAY_MS = 250L;
    private static final long STACK_SETTLE_GRACE_MS = 250L;
    private static final float STACK_MOVE_LERP = 0.18f;
    private static final int GENERAL_TEXT_WIDTH = 86;
    private static final int WAR_TITLE_TEXT_WIDTH = 96;
    private static final int WAR_DESCRIPTION_TEXT_WIDTH = 54;
    private static final int WAR_ICON_SIZE = 13;
    private static final float GENERAL_ICON_SCALE = 1.25f;
    private static final float TITLE_SCALE = 0.95f;
    private static final float DESCRIPTION_SCALE = 0.85f;
    private static final float WAR_TITLE_SCALE = 0.925f;
    private static final float WAR_DESCRIPTION_SCALE = 0.825f;
    private static final Queue<Entry> PENDING = new ArrayDeque<Entry>();
    private static final List<ActiveEntry> ACTIVE = new ArrayList<ActiveEntry>();
    private static long lastSpawnMs;
    private static long stackBlockedUntilMs;

    private WorldAnnouncementToastHud() {
    }

    public static void initialize() {
        HudRenderCallback.EVENT.register(WorldAnnouncementToastHud::render);
    }

    public static void show(String typeId, String title, String description, String iconItemId, int titleColor, int descriptionColor, String topTextureId, String bottomTextureId) {
        PENDING.add(new Entry(typeId == null ? "" : typeId, title == null ? "" : title, description == null ? "" : description, iconItemId == null ? "" : iconItemId, titleColor, descriptionColor, WorldAnnouncementToastHud.resolveTexture(topTextureId), WorldAnnouncementToastHud.resolveTexture(bottomTextureId)));
    }

    private static void render(class_332 context, class_9779 tickCounter) {
        class_310 client = class_310.method_1551();
        if (client == null || client.field_1690.field_1842) {
            return;
        }
        long now = System.currentTimeMillis();
        WorldAnnouncementToastHud.pruneExpired(now);
        WorldAnnouncementToastHud.promotePending(now);
        if (ACTIVE.isEmpty()) {
            return;
        }
        int rightX = context.method_51421() - 8 - DRAW_WIDTH;
        int bottomY = context.method_51443() - 8 - DRAW_HEIGHT;
        boolean stackGraceActive = now < stackBlockedUntilMs;
        for (int index = 0; index < ACTIVE.size(); ++index) {
            ActiveEntry active = ACTIVE.get(index);
            int targetY = bottomY - index * (DRAW_HEIGHT + 3);
            if (!active.positioned) {
                active.currentY = targetY;
                active.positioned = true;
            }
            if (stackGraceActive) {
                targetY = Math.round(active.currentY);
            }
            active.moveToward(targetY);
            WorldAnnouncementToastHud.drawEntry(context, client, active.entry, rightX, Math.round(active.currentY), WorldAnnouncementToastHud.progressFor(active, now));
        }
    }

    private static void promotePending(long now) {
        if (PENDING.isEmpty() || ACTIVE.size() >= 5 || now < stackBlockedUntilMs || now - lastSpawnMs < 250L) {
            return;
        }
        ACTIVE.add(new ActiveEntry(PENDING.remove(), now));
        lastSpawnMs = now;
    }

    private static void pruneExpired(long now) {
        Iterator<ActiveEntry> iterator = ACTIVE.iterator();
        boolean removedAny = false;
        while (iterator.hasNext()) {
            ActiveEntry active = iterator.next();
            if (now - active.startedMs < 4440L) continue;
            iterator.remove();
            removedAny = true;
        }
        if (removedAny) {
            stackBlockedUntilMs = Math.max(stackBlockedUntilMs, now + 250L);
        }
    }

    private static float progressFor(ActiveEntry active, long now) {
        long age = Math.max(0L, now - active.startedMs);
        if (age < 420L) {
            return WorldAnnouncementToastHud.easeOutCubic((float)age / 420.0f);
        }
        if (age <= 4020L) {
            return 1.0f;
        }
        long exitAge = age - 420L - 3600L;
        return 1.0f - WorldAnnouncementToastHud.easeInCubic(Math.min(1.0f, (float)exitAge / 420.0f));
    }

    private static void drawEntry(class_332 context, class_310 client, Entry entry, int targetX, int targetY, float progress) {
        if (progress <= 0.0f) {
            return;
        }
        float offsetX = (1.0f - progress) * (float)(DRAW_WIDTH + 8);
        int x = Math.round((float)targetX + offsetX);
        int y = targetY;
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(0.41f, 0.41f, 1.0f);
        context.method_25293(BACKGROUND, 0, 0, 128, 48, 0.0f, 0.0f, 128, 48, 128, 48);
        if (entry.topTexture() != null || entry.bottomTexture() != null) {
            WorldAnnouncementToastHud.drawWarContent(context, client, entry);
            context.method_51448().method_22909();
            return;
        }
        WorldAnnouncementToastHud.drawGeneralContent(context, client, entry);
        context.method_51448().method_22909();
    }

    private static void drawGeneralContent(class_332 context, class_310 client, Entry entry) {
        WorldAnnouncementToastHud.drawScaledItem(context, WorldAnnouncementToastHud.resolveIcon(entry.iconItemId()), 8, 13, 1.25f);
        WorldAnnouncementToastHud.drawTrimmedScaledText(context, client, (class_2561)class_2561.method_43470((String)entry.title()), 33, 8, 86, WorldAnnouncementToastHud.resolveTitleColor(entry), 0.95f);
        WorldAnnouncementToastHud.drawTrimmedScaledText(context, client, (class_2561)class_2561.method_43470((String)entry.description()), 33, 22, 86, entry.descriptionColor(), 0.85f);
    }

    private static void drawWarContent(class_332 context, class_310 client, Entry entry) {
        int topIconX = 8;
        int topY = 7;
        if (entry.topTexture() != null) {
            WorldAnnouncementToastHud.drawFittedTexture(context, client, entry.topTexture(), topIconX, topY, 13, 13);
        }
        WorldAnnouncementToastHud.drawTrimmedScaledText(context, client, (class_2561)class_2561.method_43470((String)entry.title()), 24, topY, 96, WorldAnnouncementToastHud.resolveTitleColor(entry), 0.925f);
        String prefix = "kingdom_war_peace".equals(entry.typeId()) ? "Peace " : "War ";
        int bottomY = 23;
        int prefixX = 24;
        int prefixWidth = Math.round((float)client.field_1772.method_1727(prefix) * 0.825f);
        WorldAnnouncementToastHud.drawScaledText(context, client, (class_2561)class_2561.method_43470((String)prefix), prefixX, bottomY + 1, entry.descriptionColor(), 0.825f);
        if (entry.bottomTexture() != null) {
            WorldAnnouncementToastHud.drawFittedTexture(context, client, entry.bottomTexture(), prefixX + prefixWidth + 2, bottomY, 13, 13);
        }
        WorldAnnouncementToastHud.drawTrimmedScaledText(context, client, (class_2561)class_2561.method_43470((String)entry.description()), prefixX + prefixWidth + 17, bottomY + 1, 54, entry.descriptionColor(), 0.825f);
    }

    private static void drawScaledItem(class_332 context, class_1799 stack, int x, int y, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51445(stack, 0, 0);
        context.method_51448().method_22909();
    }

    private static void drawTrimmedScaledText(class_332 context, class_310 client, class_2561 text, int x, int y, int maxWidth, int color, float scale) {
        String trimmed = client.field_1772.method_27523(text.getString(), Math.max(1, Math.round((float)maxWidth / scale)));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51433(client.field_1772, trimmed, 0, 0, color, false);
        context.method_51448().method_22909();
    }

    private static void drawScaledText(class_332 context, class_310 client, class_2561 text, int x, int y, int color, float scale) {
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_51439(client.field_1772, text, 0, 0, color, false);
        context.method_51448().method_22909();
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

    private static int resolveTitleColor(Entry entry) {
        return entry.titleColor() == 0 ? -11187 : entry.titleColor();
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

    private static float easeOutCubic(float t) {
        float clamped = Math.max(0.0f, Math.min(1.0f, t));
        float inverse = 1.0f - clamped;
        return 1.0f - inverse * inverse * inverse;
    }

    private static float easeInCubic(float t) {
        float clamped = Math.max(0.0f, Math.min(1.0f, t));
        return clamped * clamped * clamped;
    }

    @Environment(value=EnvType.CLIENT)
    private record Entry(String typeId, String title, String description, String iconItemId, int titleColor, int descriptionColor, class_2960 topTexture, class_2960 bottomTexture) {
    }

    @Environment(value=EnvType.CLIENT)
    private static final class ActiveEntry {
        private final Entry entry;
        private final long startedMs;
        private float currentY;
        private boolean positioned;

        private ActiveEntry(Entry entry, long startedMs) {
            this.entry = entry;
            this.startedMs = startedMs;
        }

        private void moveToward(int targetY) {
            this.currentY += ((float)targetY - this.currentY) * 0.18f;
            if (Math.abs((float)targetY - this.currentY) < 0.1f) {
                this.currentY = targetY;
            }
        }
    }
}

