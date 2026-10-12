/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
 *  net.fabricmc.fabric.api.resource.ResourceManagerHelper
 *  net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
 *  net.minecraft.class_2960
 *  net.minecraft.class_3264
 *  net.minecraft.class_3298
 *  net.minecraft.class_3300
 */
package trep.got.blacksmith;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import trep.got.blacksmith.BlacksmithGroupDefinition;

public final class BlacksmithGroupRegistry {
    private static final class_2960 RELOAD_ID = class_2960.method_60655((String)"agob", (String)"blacksmith_groups");
    private static final String ROOT = "blacksmith_groups";
    private static volatile List<BlacksmithGroupDefinition> groups = List.of();

    private BlacksmithGroupRegistry() {
    }

    public static void initialize() {
        ResourceManagerHelper.get((class_3264)class_3264.field_14190).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(){

            public class_2960 getFabricId() {
                return RELOAD_ID;
            }

            public void method_14491(class_3300 manager) {
                groups = BlacksmithGroupRegistry.loadGroups(manager);
            }
        });
    }

    public static List<BlacksmithGroupDefinition> getAll() {
        return groups;
    }

    private static List<BlacksmithGroupDefinition> loadGroups(class_3300 manager) {
        Map resources = manager.method_14488(ROOT, path -> path.method_12832().endsWith(".json"));
        ArrayList<BlacksmithGroupDefinition> loaded = new ArrayList<BlacksmithGroupDefinition>();
        for (Map.Entry entry : resources.entrySet()) {
            try (InputStreamReader reader = new InputStreamReader(((class_3298)entry.getValue()).method_14482(), StandardCharsets.UTF_8);){
                JsonElement element = JsonParser.parseReader((Reader)reader);
                BlacksmithGroupDefinition definition = (BlacksmithGroupDefinition)BlacksmithGroupDefinition.CODEC.parse((DynamicOps)JsonOps.INSTANCE, (Object)element).getOrThrow(message -> new IllegalStateException("Invalid blacksmith group " + String.valueOf(entry.getKey()) + ": " + message));
                loaded.add(definition);
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to read blacksmith group " + String.valueOf(entry.getKey()), e);
            }
        }
        loaded.sort(Comparator.comparingInt(BlacksmithGroupDefinition::order).thenComparing(BlacksmithGroupDefinition::displayName));
        return List.copyOf(loaded);
    }
}

