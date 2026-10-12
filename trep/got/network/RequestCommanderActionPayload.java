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
import trep.got.troop.PartyFormationType;

public record RequestCommanderActionPayload(String actionId, boolean hasTarget, class_2338 targetPos, String formationId) implements class_8710
{
    public static final class_8710.class_9154<RequestCommanderActionPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_commander_action"));
    public static final class_9139<class_9129, RequestCommanderActionPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.actionId);
        buf.method_52964(value.hasTarget);
        if (value.hasTarget) {
            buf.method_10807(value.targetPos);
        }
        buf.method_10814(value.formationId);
    }, buf -> {
        String actionId = buf.method_19772();
        boolean hasTarget = buf.readBoolean();
        class_2338 targetPos = hasTarget ? buf.method_10811() : class_2338.field_10980;
        String formationId = buf.method_19772();
        return new RequestCommanderActionPayload(actionId, hasTarget, targetPos, formationId.isBlank() ? PartyFormationType.LINE.id() : formationId);
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

