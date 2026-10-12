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

public record RequestSpearStabPayload() implements class_8710
{
    public static final class_8710.class_9154<RequestSpearStabPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_spear_stab"));
    public static final class_9139<class_9129, RequestSpearStabPayload> CODEC = class_8710.method_56484((value, buf) -> {}, buf -> new RequestSpearStabPayload());

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

