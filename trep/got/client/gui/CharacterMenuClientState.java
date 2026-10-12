/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.gui;

import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.client.util.ClientJsonConfigHelper;

@Environment(value=EnvType.CLIENT)
public final class CharacterMenuClientState {
    private static final String FILE_NAME = "got_character_menu.json";
    private static boolean inventoryOpen;
    private static boolean creativeOpen;
    private static boolean partyOpen;
    private static String selectedPartyTroopId;

    private CharacterMenuClientState() {
    }

    public static void initialize(class_310 client) {
        CharacterMenuClientState.load(client);
    }

    public static boolean isInventoryOpen() {
        return inventoryOpen;
    }

    public static boolean isCreativeOpen() {
        return creativeOpen;
    }

    public static boolean isPartyOpen() {
        return partyOpen;
    }

    public static String getSelectedPartyTroopId() {
        return selectedPartyTroopId;
    }

    public static void setInventoryOpen(class_310 client, boolean open) {
        inventoryOpen = open;
        CharacterMenuClientState.save(client);
    }

    public static void setCreativeOpen(class_310 client, boolean open) {
        creativeOpen = open;
        CharacterMenuClientState.save(client);
    }

    public static void setPartyOpen(class_310 client, boolean open) {
        partyOpen = open;
        if (!open) {
            selectedPartyTroopId = "";
        }
        CharacterMenuClientState.save(client);
    }

    public static void setSelectedPartyTroopId(String troopId) {
        selectedPartyTroopId = troopId == null ? "" : troopId;
    }

    private static void load(class_310 client) {
        ClientJsonConfigHelper.load(client, FILE_NAME, "Failed to load character menu client state").ifPresent(root -> {
            inventoryOpen = root.has("inventory_open") && root.get("inventory_open").getAsBoolean();
            creativeOpen = root.has("creative_open") && root.get("creative_open").getAsBoolean();
            partyOpen = root.has("party_open") && root.get("party_open").getAsBoolean();
            selectedPartyTroopId = root.has("selected_party_troop_id") ? root.get("selected_party_troop_id").getAsString() : "";
        });
    }

    private static void save(class_310 client) {
        JsonObject root = new JsonObject();
        root.addProperty("inventory_open", Boolean.valueOf(inventoryOpen));
        root.addProperty("creative_open", Boolean.valueOf(creativeOpen));
        root.addProperty("party_open", Boolean.valueOf(partyOpen));
        root.addProperty("selected_party_troop_id", selectedPartyTroopId);
        ClientJsonConfigHelper.save(client, FILE_NAME, root, "Failed to save character menu client state");
    }

    static {
        selectedPartyTroopId = "";
    }
}

