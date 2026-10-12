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

public record RequestDragonFlightPayload(boolean toggleFlight, boolean climbHeld, boolean fireBreathHeld, float forwardInput, float sidewaysInput) implements class_8710
{
    public static final class_8710.class_9154<RequestDragonFlightPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_dragon_flight"));
    public static final class_9139<class_9129, RequestDragonFlightPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_52964(value.toggleFlight());
        buf.method_52964(value.climbHeld());
        buf.method_52964(value.fireBreathHeld());
        buf.method_52941(value.forwardInput());
        buf.method_52941(value.sidewaysInput());
    }, buf -> new RequestDragonFlightPayload(buf.readBoolean(), buf.readBoolean(), buf.readBoolean(), buf.readFloat(), buf.readFloat()));

    public RequestDragonFlightPayload(boolean toggleFlight, boolean climbHeld) {
        this(toggleFlight, climbHeld, false, 0.0f, 0.0f);
    }

    public RequestDragonFlightPayload(boolean toggleFlight, boolean climbHeld, boolean fireBreathHeld) {
        this(toggleFlight, climbHeld, fireBreathHeld, 0.0f, 0.0f);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

