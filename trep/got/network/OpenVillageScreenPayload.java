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

public record OpenVillageScreenPayload(String villageId, String villageName, String kingdomId, String kingdomName, String houseId, String houseName, String religionId, String religionName, String resourceId, int currentStock, int maxStock) implements class_8710
{
    public static final class_8710.class_9154<OpenVillageScreenPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"open_village_screen"));
    public static final class_9139<class_9129, OpenVillageScreenPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.villageId);
        buf.method_10814(value.villageName);
        buf.method_10814(value.kingdomId);
        buf.method_10814(value.kingdomName);
        buf.method_10814(value.houseId);
        buf.method_10814(value.houseName);
        buf.method_10814(value.religionId);
        buf.method_10814(value.religionName);
        buf.method_10814(value.resourceId);
        buf.method_10804(value.currentStock);
        buf.method_10804(value.maxStock);
    }, buf -> new OpenVillageScreenPayload(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.method_10816()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

