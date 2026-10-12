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

public record BanditCampNegotiationResultPayload(int targetEntityId, boolean success) implements class_8710
{
    public static final class_8710.class_9154<BanditCampNegotiationResultPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"bandit_camp_negotiation_result"));
    public static final class_9139<class_9129, BanditCampNegotiationResultPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId());
        buf.method_52964(value.success());
    }, buf -> new BanditCampNegotiationResultPayload(buf.method_10816(), buf.readBoolean()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

