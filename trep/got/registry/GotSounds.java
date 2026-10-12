/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2378
 *  net.minecraft.class_2960
 *  net.minecraft.class_3414
 *  net.minecraft.class_7923
 */
package trep.got.registry;

import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public final class GotSounds {
    public static final class_2960 DIALOGUE_WRITING_1_ID = class_2960.method_60655((String)"agob", (String)"dialogue.writing_1");
    public static final class_2960 DIALOGUE_WRITING_2_ID = class_2960.method_60655((String)"agob", (String)"dialogue.writing_2");
    public static final class_2960 SOUNDTRACK_1_ID = class_2960.method_60655((String)"agob", (String)"music.soundtrack_1");
    public static final class_2960 SOUNDTRACK_2_ID = class_2960.method_60655((String)"agob", (String)"music.soundtrack_2");
    public static final class_2960 SOUNDTRACK_3_ID = class_2960.method_60655((String)"agob", (String)"music.soundtrack_3");
    public static final class_2960 SOUNDTRACK_4_ID = class_2960.method_60655((String)"agob", (String)"music.soundtrack_4");
    public static final class_3414 DIALOGUE_WRITING_1 = GotSounds.register(DIALOGUE_WRITING_1_ID);
    public static final class_3414 DIALOGUE_WRITING_2 = GotSounds.register(DIALOGUE_WRITING_2_ID);
    public static final class_3414 SOUNDTRACK_1 = GotSounds.register(SOUNDTRACK_1_ID);
    public static final class_3414 SOUNDTRACK_2 = GotSounds.register(SOUNDTRACK_2_ID);
    public static final class_3414 SOUNDTRACK_3 = GotSounds.register(SOUNDTRACK_3_ID);
    public static final class_3414 SOUNDTRACK_4 = GotSounds.register(SOUNDTRACK_4_ID);

    private GotSounds() {
    }

    public static void initialize() {
    }

    private static class_3414 register(class_2960 id) {
        return (class_3414)class_2378.method_10230((class_2378)class_7923.field_41172, (class_2960)id, (Object)class_3414.method_47908((class_2960)id));
    }
}

