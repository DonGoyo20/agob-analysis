/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.ui;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.client.util.ClientJsonConfigHelper;

@Environment(value=EnvType.CLIENT)
public final class GotClientUiSettings {
    private static final String FILE_NAME = "agob_client_ui.json";
    private static boolean defaultMainModMenu = true;

    private GotClientUiSettings() {
    }

    public static void initialize(class_310 client) {
        GotClientUiSettings.load(client);
        GotClientUiSettings.save(client);
    }

    public static boolean isDefaultMainModMenuEnabled() {
        return defaultMainModMenu;
    }

    public static void setDefaultMainModMenu(class_310 client, boolean enabled) {
        defaultMainModMenu = enabled;
        GotClientUiSettings.save(client);
    }

    private static void load(class_310 client) {
        ClientJsonConfigHelper.load(client, FILE_NAME, "Failed to load AGOB client UI settings").ifPresent(root -> {
            if (root.has("defaultMainModMenu")) {
                defaultMainModMenu = root.get("defaultMainModMenu").getAsBoolean();
            }
        });
    }

    private static void save(class_310 client) {
        JsonObject root = new JsonObject();
        root.addProperty("defaultMainModMenu", Boolean.valueOf(defaultMainModMenu));
        ClientJsonConfigHelper.save(client, FILE_NAME, root, "Failed to save AGOB client UI settings");
    }
}

