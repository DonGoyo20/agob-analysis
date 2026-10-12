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

public record OpenLockpickingPayload(class_2338 chestPos, int lockCount, float speedMultiplier, String difficultyId, long seed) implements class_8710
{
    public static final class_8710.class_9154<OpenLockpickingPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"open_lockpicking"));
    public static final class_9139<class_9129, OpenLockpickingPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10807(value.chestPos());
        buf.method_10804(value.lockCount());
        buf.method_52941(value.speedMultiplier());
        buf.method_10814(value.difficultyId());
        buf.method_52974(value.seed());
    }, buf -> new OpenLockpickingPayload(buf.method_10811(), buf.method_10816(), buf.readFloat(), buf.method_19772(), buf.readLong()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

