/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.announcement;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.client.util.ClientJsonConfigHelper;

@Environment(value=EnvType.CLIENT)
public final class GotInvasionAnnouncementSettings {
    private static final String FILE_NAME = "got_invasion_announcements.json";
    private static boolean enabled = true;

    private GotInvasionAnnouncementSettings() {
    }

    public static void initialize(class_310 client) {
        GotInvasionAnnouncementSettings.load(client);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(class_310 client, boolean value) {
        enabled = value;
        GotInvasionAnnouncementSettings.save(client);
    }

    private static void load(class_310 client) {
        ClientJsonConfigHelper.load(client, FILE_NAME, "Failed to load AGOB invasion announcement settings").ifPresent(root -> {
            enabled = !root.has("enabled") || root.get("enabled").getAsBoolean();
        });
    }

    private static void save(class_310 client) {
        JsonObject root = new JsonObject();
        root.addProperty("enabled", Boolean.valueOf(enabled));
        ClientJsonConfigHelper.save(client, FILE_NAME, root, "Failed to save AGOB invasion announcement settings");
    }
}

