/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_2960;
import trep.got.GotMod;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.kingdom.Religion;
import trep.got.religion.ReligionSelectionOption;
import trep.got.util.ClasspathResourceHelper;

public final class GotReligionOptions {
    private static final String RESOURCE_ROOT = "religions";
    private static final List<String> ORDERED_RELIGION_IDS = List.of("faith_of_the_seven", "faith_of_the_drowned", "the_old_gods", "rhllor");
    private static List<ReligionSelectionOption> options = List.of();
    private static Map<String, ReligionSelectionOption> byId = Map.of();

    private GotReligionOptions() {
    }

    public static void initialize() {
        ArrayList<ReligionSelectionOption> loadedOptions = new ArrayList<ReligionSelectionOption>();
        LinkedHashMap<String, ReligionSelectionOption> loadedById = new LinkedHashMap<String, ReligionSelectionOption>();
        for (String optionId : ORDERED_RELIGION_IDS) {
            ReligionSelectionOption option = GotReligionOptions.parseOption(optionId);
            if (option == null) continue;
            ReligionSelectionOption previous = loadedById.putIfAbsent(option.id(), option);
            if (previous != null) {
                throw new IllegalStateException("Duplicate religion option id: " + option.id());
            }
            loadedOptions.add(option);
        }
        for (ReligionSelectionOption option : AgobAddonRegistry.religionOptions()) {
            if (option.religion() == null) {
                throw new IllegalStateException("Religion option " + option.id() + " is missing a registered religion");
            }
            ReligionSelectionOption previous = loadedById.putIfAbsent(option.id(), option);
            if (previous != null) {
                throw new IllegalStateException("Duplicate religion option id: " + option.id());
            }
            loadedOptions.add(option);
        }
        options = List.copyOf(loadedOptions);
        byId = Map.copyOf(loadedById);
        GotMod.LOGGER.info("Loaded {} religion options", (Object)options.size());
    }

    public static List<ReligionSelectionOption> getAll() {
        return options;
    }

    public static ReligionSelectionOption get(String optionId) {
        return byId.get(optionId);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static ReligionSelectionOption parseOption(String optionId) {
        class_2960 resourceId = class_2960.method_60655((String)"agob", (String)("religions/" + optionId + ".json"));
        String classpathPath = "data/" + resourceId.method_12836() + "/" + resourceId.method_12832();
        if (!ClasspathResourceHelper.resourceExists(GotReligionOptions.class.getClassLoader(), classpathPath)) {
            return null;
        }
        try (InputStream stream = GotReligionOptions.class.getClassLoader().getResourceAsStream(classpathPath);){
            if (stream == null) {
                ReligionSelectionOption religionSelectionOption2 = null;
                return religionSelectionOption2;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            JsonObject root = JsonParser.parseReader((Reader)reader).getAsJsonObject();
            String id = root.get("id").getAsString();
            Religion religion = Religion.byId(root.get("religion_id").getAsString());
            if (religion == null) {
                throw new IllegalStateException("Unknown religion id in " + String.valueOf(resourceId));
            }
            ReligionSelectionOption religionSelectionOption = new ReligionSelectionOption(id, religion, root.get("display_name").getAsString(), root.get("description").getAsString(), class_2960.method_60654((String)root.get("texture").getAsString()));
            return religionSelectionOption;
        }
        catch (IOException | IllegalStateException e) {
            GotMod.LOGGER.error("Failed to parse religion option {}", (Object)resourceId, (Object)e);
            return null;
        }
    }
}

