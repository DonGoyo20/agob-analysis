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

import java.util.Arrays;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record RequestCreatePlayerHousePayload(int targetEntityId, String houseName, int[] sigilColors, boolean completePledge) implements class_8710
{
    public static final class_8710.class_9154<RequestCreatePlayerHousePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"request_create_player_house"));
    public static final class_9139<class_9129, RequestCreatePlayerHousePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.targetEntityId);
        buf.method_10814(value.houseName);
        buf.method_10804(value.sigilColors.length);
        for (int color : value.sigilColors) {
            buf.method_53002(color);
        }
        buf.method_52964(value.completePledge);
    }, buf -> {
        int targetEntityId = buf.method_10816();
        String houseName = buf.method_19772();
        int colorCount = buf.method_10816();
        int[] sigilColors = new int[colorCount];
        for (int index = 0; index < colorCount; ++index) {
            sigilColors[index] = buf.readInt();
        }
        boolean completePledge = buf.readBoolean();
        return new RequestCreatePlayerHousePayload(targetEntityId, houseName, sigilColors, completePledge);
    });

    public RequestCreatePlayerHousePayload {
        sigilColors = Arrays.copyOf(sigilColors == null ? new int[]{} : sigilColors, sigilColors == null ? 0 : sigilColors.length);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

