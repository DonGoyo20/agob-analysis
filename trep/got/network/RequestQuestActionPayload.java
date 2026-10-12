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
import trep.got.quest.QuestActionType;

public record RequestQuestActionPayload(int targetEntityId, String actionId) implements class_8710
{
    public static final class_8710.class_9154<RequestQuestActionPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_quest_action"));
    public static final class_9139<class_9129, RequestQuestActionPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId);
        buf.method_10814(value.actionId);
    }, buf -> new RequestQuestActionPayload(buf.method_10816(), buf.method_19772()));

    public RequestQuestActionPayload(int targetEntityId, QuestActionType actionType) {
        this(targetEntityId, actionType.getId());
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

