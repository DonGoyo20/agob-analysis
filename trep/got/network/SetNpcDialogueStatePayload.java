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

public record SetNpcDialogueStatePayload(int targetEntityId, boolean active) implements class_8710
{
    public static final class_8710.class_9154<SetNpcDialogueStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"set_npc_dialogue_state"));
    public static final class_9139<class_9129, SetNpcDialogueStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId);
        buf.method_52964(value.active);
    }, buf -> new SetNpcDialogueStatePayload(buf.method_10816(), buf.readBoolean()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

