/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback
 *  net.minecraft.class_2378
 *  net.minecraft.class_2582
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_6862
 *  net.minecraft.class_7924
 */
package trep.got.registry;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.class_2378;
import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public final class GotBannerPatterns {
    public static final class_5321<class_2582> ARRYN = GotBannerPatterns.key("arryn");
    public static final class_5321<class_2582> BARATHEON = GotBannerPatterns.key("baratheon");
    public static final class_5321<class_2582> GREYJOY = GotBannerPatterns.key("greyjoy");
    public static final class_5321<class_2582> LANNISTER = GotBannerPatterns.key("lannister");
    public static final class_5321<class_2582> MARTELL = GotBannerPatterns.key("martell");
    public static final class_5321<class_2582> STARK = GotBannerPatterns.key("stark");
    public static final class_5321<class_2582> TULLY = GotBannerPatterns.key("tully");

    private GotBannerPatterns() {
    }

    public static void initialize() {
        DynamicRegistrySetupCallback.EVENT.register(view -> view.getOptional(class_7924.field_41252).ifPresent(registry -> {
            GotBannerPatterns.register((class_2378<class_2582>)registry, ARRYN);
            GotBannerPatterns.register((class_2378<class_2582>)registry, BARATHEON);
            GotBannerPatterns.register((class_2378<class_2582>)registry, GREYJOY);
            GotBannerPatterns.register((class_2378<class_2582>)registry, LANNISTER);
            GotBannerPatterns.register((class_2378<class_2582>)registry, MARTELL);
            GotBannerPatterns.register((class_2378<class_2582>)registry, STARK);
            GotBannerPatterns.register((class_2378<class_2582>)registry, TULLY);
        }));
    }

    private static void register(class_2378<class_2582> registry, class_5321<class_2582> key) {
        if (registry.method_35842(key)) {
            return;
        }
        class_2960 id = key.method_29177();
        class_2378.method_39197(registry, key, (Object)new class_2582(id, "block." + id.method_43903() + ".banner." + id.method_12832()));
    }

    private static class_5321<class_2582> key(String path) {
        return class_5321.method_29179((class_5321)class_7924.field_41252, (class_2960)class_2960.method_60655((String)"agob", (String)path));
    }

    public static class_6862<class_2582> itemTag(String path) {
        return class_6862.method_40092((class_5321)class_7924.field_41252, (class_2960)class_2960.method_60655((String)"agob", (String)("pattern_item/" + path)));
    }
}

