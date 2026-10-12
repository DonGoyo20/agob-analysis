/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2874
 *  net.minecraft.class_2960
 *  net.minecraft.class_5321
 *  net.minecraft.class_5363
 *  net.minecraft.class_7924
 */
package trep.got.world.dimension;

import net.minecraft.class_1937;
import net.minecraft.class_2874;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_5363;
import net.minecraft.class_7924;

public final class GotDimensionKeys {
    public static final class_5321<class_1937> ICEANDFIRE = GotDimensionKeys.world("iceandfire");
    public static final class_5321<class_2874> ICEANDFIRE_TYPE = GotDimensionKeys.type("iceandfire");
    public static final class_5321<class_5363> ICEANDFIRE_OPTIONS = GotDimensionKeys.options("iceandfire");

    private GotDimensionKeys() {
    }

    public static void initialize() {
    }

    private static class_5321<class_1937> world(String path) {
        return class_5321.method_29179((class_5321)class_7924.field_41223, (class_2960)class_2960.method_60655((String)"agob", (String)path));
    }

    private static class_5321<class_2874> type(String path) {
        return class_5321.method_29179((class_5321)class_7924.field_41241, (class_2960)class_2960.method_60655((String)"agob", (String)path));
    }

    private static class_5321<class_5363> options(String path) {
        return class_5321.method_29179((class_5321)class_7924.field_41224, (class_2960)class_2960.method_60655((String)"agob", (String)path));
    }
}

