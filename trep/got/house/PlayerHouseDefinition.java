/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2495
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.house;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.class_2487;
import net.minecraft.class_2495;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;

public record PlayerHouseDefinition(String ownerUuid, String houseId, String kingdomId, String lordHouseId, String displayName, int[] sigilColors, Set<String> memberUuids) {
    private final int[] sigilColors;

    public PlayerHouseDefinition(String ownerUuid, String houseId, String kingdomId, String lordHouseId, String displayName, int[] sigilColors) {
        this(ownerUuid, houseId, kingdomId, lordHouseId, displayName, sigilColors, Set.of());
    }

    public PlayerHouseDefinition {
        ownerUuid = ownerUuid == null ? "" : ownerUuid;
        houseId = houseId == null ? "" : houseId;
        kingdomId = kingdomId == null ? "" : kingdomId;
        lordHouseId = lordHouseId == null ? "" : lordHouseId;
        displayName = displayName == null ? "" : displayName;
        sigilColors = Arrays.copyOf(sigilColors == null ? new int[]{} : sigilColors, sigilColors == null ? 0 : sigilColors.length);
        LinkedHashSet<String> normalizedMembers = new LinkedHashSet<String>();
        if (memberUuids != null) {
            for (String memberUuid : memberUuids) {
                if (memberUuid == null || memberUuid.isBlank() || memberUuid.equals(ownerUuid)) continue;
                normalizedMembers.add(memberUuid);
            }
        }
        memberUuids = Set.copyOf(normalizedMembers);
    }

    public int[] sigilColors() {
        return Arrays.copyOf(this.sigilColors, this.sigilColors.length);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("owner_uuid", this.ownerUuid);
        nbt.method_10582("house_id", this.houseId);
        nbt.method_10582("kingdom_id", this.kingdomId);
        nbt.method_10582("lord_house_id", this.lordHouseId);
        nbt.method_10582("display_name", this.displayName);
        nbt.method_10566("sigil_colors", (class_2520)new class_2495(this.sigilColors));
        class_2499 members = new class_2499();
        for (String memberUuid : this.memberUuids) {
            members.add((Object)class_2519.method_23256((String)memberUuid));
        }
        nbt.method_10566("member_uuids", (class_2520)members);
        return nbt;
    }

    public static PlayerHouseDefinition fromNbt(class_2487 nbt) {
        LinkedHashSet<String> members = new LinkedHashSet<String>();
        class_2499 memberList = nbt.method_10554("member_uuids", 8);
        for (int index = 0; index < memberList.size(); ++index) {
            String memberUuid = memberList.method_10608(index);
            if (memberUuid.isBlank()) continue;
            members.add(memberUuid);
        }
        return new PlayerHouseDefinition(nbt.method_10558("owner_uuid"), nbt.method_10558("house_id"), nbt.method_10558("kingdom_id"), nbt.method_10558("lord_house_id"), nbt.method_10558("display_name"), nbt.method_10561("sigil_colors"), members);
    }
}

