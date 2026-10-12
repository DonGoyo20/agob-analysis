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

import java.util.Locale;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record RequestTailorActionPayload(String action, String slot, String clothingId, String dyeId) implements class_8710
{
    public static final class_8710.class_9154<RequestTailorActionPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_tailor_action"));
    public static final class_9139<class_9129, RequestTailorActionPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.action);
        buf.method_10814(value.slot);
        buf.method_10814(value.clothingId);
        buf.method_10814(value.dyeId);
    }, buf -> new RequestTailorActionPayload(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772()));

    public RequestTailorActionPayload {
        action = RequestTailorActionPayload.normalize(action);
        slot = RequestTailorActionPayload.normalize(slot);
        clothingId = RequestTailorActionPayload.normalize(clothingId);
        dyeId = RequestTailorActionPayload.normalize(dyeId);
        if (dyeId.isBlank()) {
            dyeId = "none";
        }
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}

