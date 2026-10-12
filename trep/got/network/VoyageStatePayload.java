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

public record VoyageStatePayload(boolean active, String voyageId) implements class_8710
{
    public static final class_8710.class_9154<VoyageStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"voyage_state"));
    public static final class_9139<class_9129, VoyageStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_52964(value.active);
        buf.method_10814(value.voyageId);
    }, buf -> new VoyageStatePayload(buf.readBoolean(), buf.method_19772()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

