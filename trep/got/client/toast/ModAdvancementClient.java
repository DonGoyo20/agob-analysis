/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_368
 */
package trep.got.client.toast;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_368;
import trep.got.client.announcement.GotInvasionAnnouncementSettings;
import trep.got.client.toast.ModAdvancementToast;
import trep.got.client.toast.WorldAnnouncementToastHud;
import trep.got.network.ModAdvancementPayload;

@Environment(value=EnvType.CLIENT)
public final class ModAdvancementClient {
    private static final long KINGDOM_ANNOUNCEMENT_DEDUPE_WINDOW_MS = 4000L;
    private static final Map<String, Long> RECENT_KINGDOM_ANNOUNCEMENTS = new HashMap<String, Long>();

    private ModAdvancementClient() {
    }

    public static void show(ModAdvancementPayload payload, class_310 client) {
        if (payload.typeId().startsWith("kingdom_conflict_") && !GotInvasionAnnouncementSettings.isEnabled()) {
            return;
        }
        if (ModAdvancementClient.shouldSuppressDuplicateKingdomAnnouncement(payload)) {
            return;
        }
        if (ModAdvancementClient.isWorldAnnouncement(payload)) {
            client.execute(() -> WorldAnnouncementToastHud.show(payload.typeId(), payload.title(), payload.description(), payload.iconItemId(), payload.titleColor(), payload.descriptionColor(), payload.topTextureId(), payload.bottomTextureId()));
            return;
        }
        client.execute(() -> client.method_1566().method_1999((class_368)new ModAdvancementToast(payload.typeId(), payload.title(), payload.description(), payload.iconItemId(), payload.titleColor(), payload.descriptionColor(), payload.topTextureId(), payload.bottomTextureId())));
    }

    private static boolean isWorldAnnouncement(ModAdvancementPayload payload) {
        if (payload == null || payload.typeId() == null) {
            return false;
        }
        return payload.typeId().startsWith("kingdom_war_") || payload.typeId().startsWith("kingdom_conflict_") || payload.typeId().startsWith("village_conflict_");
    }

    private static boolean shouldSuppressDuplicateKingdomAnnouncement(ModAdvancementPayload payload) {
        String secondTexture;
        if (payload == null) {
            return false;
        }
        if (!payload.typeId().startsWith("kingdom_war_")) {
            return false;
        }
        String firstTexture = payload.topTextureId() == null ? "" : payload.topTextureId();
        String string = secondTexture = payload.bottomTextureId() == null ? "" : payload.bottomTextureId();
        if (firstTexture.isBlank() || secondTexture.isBlank()) {
            return false;
        }
        String left = firstTexture.compareTo(secondTexture) <= 0 ? firstTexture : secondTexture;
        String right = firstTexture.compareTo(secondTexture) <= 0 ? secondTexture : firstTexture;
        String key = payload.typeId() + "|" + left + "|" + right;
        long now = System.currentTimeMillis();
        RECENT_KINGDOM_ANNOUNCEMENTS.entrySet().removeIf(entry -> now - (Long)entry.getValue() > 4000L);
        Long previous = RECENT_KINGDOM_ANNOUNCEMENTS.put(key, now);
        return previous != null && now - previous <= 4000L;
    }
}

