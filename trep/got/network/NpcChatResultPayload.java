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

public record NpcChatResultPayload(int targetEntityId, String responseTypeId, long responseSeed, int relation, String kingdomId, String dialogueContextId, String dialogueSubjectName) implements class_8710
{
    public static final class_8710.class_9154<NpcChatResultPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"npc_chat_result"));
    public static final class_9139<class_9129, NpcChatResultPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId());
        buf.method_10814(value.responseTypeId());
        buf.method_52974(value.responseSeed());
        buf.method_10804(value.relation() + 100);
        buf.method_10814(value.kingdomId());
        buf.method_10814(value.dialogueContextId());
        buf.method_10814(value.dialogueSubjectName());
    }, buf -> new NpcChatResultPayload(buf.method_10816(), buf.method_19772(), buf.readLong(), buf.method_10816() - 100, buf.method_19772(), buf.method_19772(), buf.method_19772()));

    public NpcChatResultPayload {
        kingdomId = kingdomId == null ? "" : kingdomId;
        dialogueContextId = dialogueContextId == null ? "" : dialogueContextId;
        dialogueSubjectName = dialogueSubjectName == null ? "" : dialogueSubjectName;
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

