/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_5195
 */
package trep.got.client.audio;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_5195;
import trep.got.client.audio.GotMusicMode;
import trep.got.client.audio.GotMusicSelector;
import trep.got.client.util.ClientJsonConfigHelper;
import trep.got.mixin.client.MinecraftClientMusicAccess;

@Environment(value=EnvType.CLIENT)
public final class GotMusicSettings {
    private static final String FILE_NAME = "got_music_settings.json";
    private static GotMusicMode musicMode = GotMusicMode.MIXED;

    private GotMusicSettings() {
    }

    public static void initialize(class_310 client) {
        GotMusicSettings.load(client);
    }

    public static GotMusicMode getMusicMode() {
        return musicMode;
    }

    public static void setMusicMode(class_310 client, GotMusicMode value) {
        musicMode = value == null ? GotMusicMode.MIXED : value;
        GotMusicSelector.resetSelection();
        GotMusicSettings.save(client);
        GotMusicSettings.applyImmediately(client);
    }

    private static void load(class_310 client) {
        ClientJsonConfigHelper.load(client, FILE_NAME, "Failed to load AGOB music settings").ifPresent(root -> {
            musicMode = root.has("music_mode") ? GotMusicMode.byId(root.get("music_mode").getAsString()) : GotMusicMode.MIXED;
        });
    }

    private static void save(class_310 client) {
        JsonObject root = new JsonObject();
        root.addProperty("music_mode", musicMode.getId());
        ClientJsonConfigHelper.save(client, FILE_NAME, root, "Failed to save AGOB music settings");
    }

    private static void applyImmediately(class_310 client) {
        if (client == null) {
            return;
        }
        class_5195 nextMusic = client.method_1544();
        ((MinecraftClientMusicAccess)client).agob$getMusicTracker().method_4859();
        if (nextMusic != null) {
            ((MinecraftClientMusicAccess)client).agob$getMusicTracker().method_4858(nextMusic);
        }
    }
}

