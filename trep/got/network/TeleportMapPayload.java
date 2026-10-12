/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record TeleportMapPayload(int x, int z) implements class_8710
{
    public static final class_8710.class_9154<TeleportMapPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"teleport_map"));
    public static final class_9139<class_9129, TeleportMapPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_53002(value.x);
        buf.method_53002(value.z);
    }, buf -> new TeleportMapPayload(buf.readInt(), buf.readInt()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

