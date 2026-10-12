/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record OpenCastleScreenPayload(String castleId, String castleName, String lordName, String kingdomId, String kingdomName, String houseId, String houseName, String religionId, String religionName, String tierName, boolean canAccessStorage, boolean canEditBanner, boolean settlementOwner, boolean foundedKingdom, String settlementTierId, int settlementLevel, int settlers, int settlerCapacity, String nextLevelRequirement, List<SettlerEntry> settlerEntries) implements class_8710
{
    public static final class_8710.class_9154<OpenCastleScreenPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"open_castle_screen"));
    public static final class_9139<class_9129, OpenCastleScreenPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.castleId);
        buf.method_10814(value.castleName);
        buf.method_10814(value.lordName);
        buf.method_10814(value.kingdomId);
        buf.method_10814(value.kingdomName);
        buf.method_10814(value.houseId);
        buf.method_10814(value.houseName);
        buf.method_10814(value.religionId);
        buf.method_10814(value.religionName);
        buf.method_10814(value.tierName);
        buf.method_52964(value.canAccessStorage);
        buf.method_52964(value.canEditBanner);
        buf.method_52964(value.settlementOwner);
        buf.method_52964(value.foundedKingdom);
        buf.method_10814(value.settlementTierId);
        buf.method_10804(value.settlementLevel);
        buf.method_10804(value.settlers);
        buf.method_10804(value.settlerCapacity);
        buf.method_10814(value.nextLevelRequirement);
        buf.method_10804(value.settlerEntries.size());
        for (SettlerEntry entry : value.settlerEntries) {
            entry.write((class_9129)buf);
        }
    }, buf -> new OpenCastleScreenPayload(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.readBoolean(), buf.readBoolean(), buf.readBoolean(), buf.readBoolean(), buf.method_19772(), buf.method_10816(), buf.method_10816(), buf.method_10816(), buf.method_19772(), OpenCastleScreenPayload.readSettlerEntries(buf)));

    private static List<SettlerEntry> readSettlerEntries(class_9129 buf) {
        int size = buf.method_10816();
        ArrayList<SettlerEntry> entries = new ArrayList<SettlerEntry>(size);
        for (int index = 0; index < size; ++index) {
            entries.add(SettlerEntry.read(buf));
        }
        return List.copyOf(entries);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record SettlerEntry(String settlerId, String displayName, String optionId, String houseTextureId, String professionId, String professionDisplayName, String skillTierId, String skillTierDisplayName, class_1799 toolStack) {
        private void write(class_9129 buf) {
            buf.method_10814(this.settlerId);
            buf.method_10814(this.displayName);
            buf.method_10814(this.optionId);
            buf.method_10814(this.houseTextureId);
            buf.method_10814(this.professionId);
            buf.method_10814(this.professionDisplayName);
            buf.method_10814(this.skillTierId);
            buf.method_10814(this.skillTierDisplayName);
            class_1799.field_48349.encode((Object)buf, (Object)this.toolStack);
        }

        private static SettlerEntry read(class_9129 buf) {
            return new SettlerEntry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), (class_1799)class_1799.field_48349.decode((Object)buf));
        }
    }
}

