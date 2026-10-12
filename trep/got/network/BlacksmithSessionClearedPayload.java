/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record BlacksmithSessionClearedPayload(class_2338 furnaceOrigin, String reason) implements class_8710
{
    public static final class_8710.class_9154<BlacksmithSessionClearedPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"blacksmith_session_cleared"));
    public static final class_9139<class_9129, BlacksmithSessionClearedPayload> CODEC = class_8710.method_56484((value, buf) -> {
        class_2338.field_48404.encode(buf, (Object)value.furnaceOrigin());
        buf.method_10814(value.reason());
    }, buf -> new BlacksmithSessionClearedPayload((class_2338)class_2338.field_48404.decode(buf), buf.method_19772()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

