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

public record RequestCommanderBlockTargetPayload(class_2338 targetPos, float facingYaw) implements class_8710
{
    public static final class_8710.class_9154<RequestCommanderBlockTargetPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_commander_block_target"));
    public static final class_9139<class_9129, RequestCommanderBlockTargetPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10807(value.targetPos);
        buf.method_52941(value.facingYaw);
    }, buf -> new RequestCommanderBlockTargetPayload(buf.method_10811(), buf.readFloat()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

