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
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.market;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3298;
import net.minecraft.class_3300;
import org.jetbrains.annotations.Nullable;
import trep.got.market.MarketDisplaySetupDefinition;

public final class MarketDisplaySetupRegistry {
    private static final class_2960 RELOAD_ID = class_2960.method_60655((String)"agob", (String)"market_setups");
    private static final String ROOT = "market";
    private static volatile Map<class_2960, MarketDisplaySetupDefinition> setups = Map.of();

    private MarketDisplaySetupRegistry() {
    }

    public static void initialize() {
        ResourceManagerHelper.get((class_3264)class_3264.field_14190).registerReloadListener((IdentifiableResourceReloadListener)new SimpleSynchronousResourceReloadListener(){

            public class_2960 getFabricId() {
                return RELOAD_ID;
            }

            public void method_14491(class_3300 manager) {
                setups = MarketDisplaySetupRegistry.loadSetups(manager);
            }
        });
    }

    @Nullable
    public static MarketDisplaySetupDefinition get(class_2960 id) {
        return setups.get(id);
    }

    private static Map<class_2960, MarketDisplaySetupDefinition> loadSetups(class_3300 manager) {
        Map resources = manager.method_14488(ROOT, path -> path.method_12832().endsWith(".json"));
        LinkedHashMap<class_2960, MarketDisplaySetupDefinition> loaded = new LinkedHashMap<class_2960, MarketDisplaySetupDefinition>();
        for (Map.Entry entry : resources.entrySet()) {
            class_2960 resourceId = (class_2960)entry.getKey();
            class_2960 setupId = class_2960.method_60655((String)resourceId.method_12836(), (String)resourceId.method_12832().substring(0, resourceId.method_12832().length() - ".json".length()));
            try (InputStreamReader reader = new InputStreamReader(((class_3298)entry.getValue()).method_14482(), StandardCharsets.UTF_8);){
                JsonElement element = JsonParser.parseReader((Reader)reader);
                MarketDisplaySetupDefinition definition = (MarketDisplaySetupDefinition)MarketDisplaySetupDefinition.CODEC.parse((DynamicOps)JsonOps.INSTANCE, (Object)element).getOrThrow(message -> new IllegalStateException("Invalid market setup " + String.valueOf(resourceId) + ": " + message));
                loaded.put(setupId, definition);
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to read market setup " + String.valueOf(resourceId), e);
            }
        }
        return Map.copyOf(loaded);
    }
}

