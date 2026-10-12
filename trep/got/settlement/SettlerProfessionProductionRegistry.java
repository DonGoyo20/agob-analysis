/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
 *  net.fabricmc.fabric.api.resource.ResourceManagerHelper
 *  net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
 *  net.minecraft.class_2960
 *  net.minecraft.class_3264
 *  net.minecraft.class_3298
 *  net.minecraft.class_3300
 *  net.minecraft.class_7923
 */
package trep.got.settlement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import net.minecraft.class_7923;
import trep.got.GotMod;
import trep.got.castle.SettlerProfession;

public final class SettlerProfessionProductionRegistry {
    private static final class_2960 RELOAD_ID = class_2960.method_60655((String)"agob", (String)"settler_profession_production");
    private static final String ROOT = "settler_production";
    private static volatile Map<SettlerProfession, List<ProductionEntry>> entriesByProfession = Map.of();

    private SettlerProfessionProductionRegistry() {
    }

    public static void initialize() {
        ResourceManagerHelper.get((class_3264)class_3264.field_14190).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(){

            public class_2960 getFabricId() {
                return RELOAD_ID;
            }

            public void method_14491(class_3300 manager) {
                entriesByProfession = SettlerProfessionProductionRegistry.loadEntries(manager);
            }
        });
    }

    public static List<ProductionEntry> getEntries(SettlerProfession profession) {
        return entriesByProfession.getOrDefault((Object)profession, List.of());
    }

    private static Map<SettlerProfession, List<ProductionEntry>> loadEntries(class_3300 manager) {
        EnumMap loaded = new EnumMap(SettlerProfession.class);
        for (SettlerProfession profession : SettlerProfession.values()) {
            class_2960 resourceId = class_2960.method_60655((String)"agob", (String)("settler_production/" + profession.getId() + ".json"));
            class_3298 resource = manager.method_14486(resourceId).orElse(null);
            if (resource == null) {
                GotMod.LOGGER.warn("Missing settler production resource {}", (Object)resourceId);
                loaded.put(profession, List.of());
                continue;
            }
            try (InputStreamReader reader = new InputStreamReader(resource.method_14482(), StandardCharsets.UTF_8);){
                JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
                JsonArray entries = root.getAsJsonArray("entries");
                ArrayList<ProductionEntry> parsed = new ArrayList<ProductionEntry>();
                for (int index = 0; index < entries.size(); ++index) {
                    JsonObject entryObject = entries.get(index).getAsJsonObject();
                    if (entryObject.has("item_id")) {
                        class_2960 itemId = class_2960.method_60654((String)entryObject.get("item_id").getAsString());
                        if (!class_7923.field_41178.method_10250(itemId)) {
                            throw new IllegalStateException("Unknown item " + String.valueOf(itemId) + " in " + String.valueOf(resourceId));
                        }
                        parsed.add(new ProductionEntry(ProductionEntryType.ITEM, itemId));
                        continue;
                    }
                    String special = entryObject.get("special").getAsString();
                    parsed.add(new ProductionEntry(ProductionEntryType.byId(special), null));
                }
                loaded.put(profession, List.copyOf(parsed));
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to read settler production resource " + String.valueOf(resourceId), e);
            }
        }
        return Map.copyOf(loaded);
    }

    public record ProductionEntry(ProductionEntryType type, class_2960 itemId) {
    }

    public static enum ProductionEntryType {
        ITEM("item"),
        BIOME_LOG("biome_log"),
        BIOME_PLANKS("biome_planks");

        private final String id;

        private ProductionEntryType(String id) {
            this.id = id;
        }

        public static ProductionEntryType byId(String id) {
            for (ProductionEntryType type : ProductionEntryType.values()) {
                if (!type.id.equalsIgnoreCase(id)) continue;
                return type;
            }
            throw new IllegalArgumentException("Unknown settler production entry type: " + id);
        }
    }
}

