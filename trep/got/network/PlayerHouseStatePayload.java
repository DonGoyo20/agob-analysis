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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.skill.SkillAttributeType;

public record PlayerHouseStatePayload(List<Entry> houses, List<MemberEntry> members, List<HouseLordEntry> houseLords) implements class_8710
{
    public static final class_8710.class_9154<PlayerHouseStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"player_house_state"));
    public static final class_9139<class_9129, PlayerHouseStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.houses.size());
        for (Entry entry : value.houses) {
            buf.method_10814(entry.ownerUuid());
            buf.method_10814(entry.houseId());
            buf.method_10814(entry.kingdomId());
            buf.method_10814(entry.lordHouseId());
            buf.method_10814(entry.displayName());
            buf.method_10804(entry.sigilColors().length);
            for (int color : entry.sigilColors()) {
                buf.method_53002(color);
            }
        }
        buf.method_10804(value.members.size());
        for (MemberEntry memberEntry : value.members) {
            buf.method_10814(memberEntry.entryId());
            buf.method_10814(memberEntry.playerUuid());
            buf.method_10814(memberEntry.playerName());
            buf.method_10814(memberEntry.kingdomId());
            buf.method_10814(memberEntry.houseId());
            buf.method_10814(memberEntry.reputationTitle());
            buf.method_10814(memberEntry.marriedToName());
            buf.method_10814(memberEntry.locationName());
            buf.method_10814(memberEntry.parentsLine());
            buf.method_10804(memberEntry.ageDays());
            buf.method_10814(memberEntry.familyStageId());
            PlayerHouseStatePayload.writeRoleIconIds(buf, memberEntry.roleIconIds());
            buf.method_52964(memberEntry.spouseInParty());
            buf.method_52974(memberEntry.summonCooldownEndsAtMs());
            buf.method_52964(memberEntry.npc());
            buf.method_10814(memberEntry.npcGender());
            buf.method_10804(memberEntry.npcSkinVariant());
            buf.method_10804(memberEntry.npcHairVariant());
            buf.method_10804(memberEntry.npcClothesVariant());
            PlayerHouseStatePayload.writeSkillValues(buf, memberEntry.skillPoints());
            PlayerHouseStatePayload.writeSkillValues(buf, memberEntry.skillMaxPoints());
        }
        buf.method_10804(value.houseLords.size());
        for (HouseLordEntry houseLordEntry : value.houseLords) {
            buf.method_10814(houseLordEntry.kingdomId());
            buf.method_10814(houseLordEntry.houseId());
            buf.method_10814(houseLordEntry.displayName());
            buf.method_10814(houseLordEntry.marriedToName());
            buf.method_10814(houseLordEntry.locationName());
            PlayerHouseStatePayload.writeRoleIconIds(buf, houseLordEntry.roleIconIds());
            PlayerHouseStatePayload.writeSkillValues(buf, houseLordEntry.skillPoints());
        }
    }, buf -> {
        int size = buf.method_10816();
        ArrayList<Entry> entries = new ArrayList<Entry>(size);
        for (int index = 0; index < size; ++index) {
            String ownerUuid = buf.method_19772();
            String houseId = buf.method_19772();
            String kingdomId = buf.method_19772();
            String lordHouseId = buf.method_19772();
            String displayName = buf.method_19772();
            int colorCount = buf.method_10816();
            int[] colors = new int[colorCount];
            for (int colorIndex = 0; colorIndex < colorCount; ++colorIndex) {
                colors[colorIndex] = buf.readInt();
            }
            entries.add(new Entry(ownerUuid, houseId, kingdomId, lordHouseId, displayName, colors));
        }
        int memberSize = buf.method_10816();
        ArrayList<MemberEntry> members = new ArrayList<MemberEntry>(memberSize);
        for (int index = 0; index < memberSize; ++index) {
            members.add(new MemberEntry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_10816(), buf.method_19772(), PlayerHouseStatePayload.readRoleIconIds(buf), buf.readBoolean(), buf.readLong(), buf.readBoolean(), buf.method_19772(), buf.method_10816(), buf.method_10816(), buf.method_10816(), PlayerHouseStatePayload.readSkillValues(buf), PlayerHouseStatePayload.readSkillValues(buf)));
        }
        int lordSize = buf.method_10816();
        ArrayList<HouseLordEntry> houseLords = new ArrayList<HouseLordEntry>(lordSize);
        for (int index = 0; index < lordSize; ++index) {
            houseLords.add(new HouseLordEntry(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), PlayerHouseStatePayload.readRoleIconIds(buf), PlayerHouseStatePayload.readSkillValues(buf)));
        }
        return new PlayerHouseStatePayload(List.copyOf(entries), List.copyOf(members), List.copyOf(houseLords));
    });

    public PlayerHouseStatePayload {
        houses = List.copyOf(houses == null ? List.of() : houses);
        members = List.copyOf(members == null ? List.of() : members);
        houseLords = List.copyOf(houseLords == null ? List.of() : houseLords);
    }

    private static void writeRoleIconIds(class_9129 buf, List<String> roleIconIds) {
        List<String> normalized = PlayerHouseStatePayload.normalizeRoleIconIds(roleIconIds);
        buf.method_10804(normalized.size());
        for (String roleIconId : normalized) {
            buf.method_10814(roleIconId);
        }
    }

    private static List<String> readRoleIconIds(class_9129 buf) {
        int size = buf.method_10816();
        ArrayList<String> roleIconIds = new ArrayList<String>(size);
        for (int index = 0; index < size; ++index) {
            roleIconIds.add(buf.method_19772());
        }
        return PlayerHouseStatePayload.normalizeRoleIconIds(roleIconIds);
    }

    private static void writeSkillValues(class_9129 buf, int[] values) {
        int[] normalized;
        for (int value : normalized = PlayerHouseStatePayload.normalizeSkillValues(values, 0)) {
            buf.method_10804(value);
        }
    }

    private static int[] readSkillValues(class_9129 buf) {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < values.length; ++index) {
            values[index] = buf.method_10816();
        }
        return values;
    }

    private static int[] normalizeSkillValues(int[] values, int defaultValue) {
        int[] normalized = new int[SkillAttributeType.ALL.size()];
        Arrays.fill(normalized, defaultValue);
        if (values != null) {
            System.arraycopy(values, 0, normalized, 0, Math.min(values.length, normalized.length));
        }
        return normalized;
    }

    private static List<String> normalizeRoleIconIds(List<String> roleIconIds) {
        if (roleIconIds == null || roleIconIds.isEmpty()) {
            return List.of();
        }
        LinkedHashSet<String> normalized = new LinkedHashSet<String>();
        for (String roleIconId : roleIconIds) {
            if (roleIconId == null || roleIconId.isBlank()) continue;
            normalized.add(roleIconId);
        }
        return List.copyOf(normalized);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record Entry(String ownerUuid, String houseId, String kingdomId, String lordHouseId, String displayName, int[] sigilColors) {
        public Entry {
            sigilColors = Arrays.copyOf(sigilColors == null ? new int[]{} : sigilColors, sigilColors == null ? 0 : sigilColors.length);
        }
    }

    public record MemberEntry(String entryId, String playerUuid, String playerName, String kingdomId, String houseId, String reputationTitle, String marriedToName, String locationName, String parentsLine, int ageDays, String familyStageId, List<String> roleIconIds, boolean spouseInParty, long summonCooldownEndsAtMs, boolean npc, String npcGender, int npcSkinVariant, int npcHairVariant, int npcClothesVariant, int[] skillPoints, int[] skillMaxPoints) {
        public MemberEntry {
            entryId = entryId == null ? "" : entryId;
            playerUuid = playerUuid == null ? "" : playerUuid;
            playerName = playerName == null ? "" : playerName;
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            reputationTitle = reputationTitle == null ? "" : reputationTitle;
            marriedToName = marriedToName == null ? "" : marriedToName;
            locationName = locationName == null ? "" : locationName;
            parentsLine = parentsLine == null ? "" : parentsLine;
            ageDays = Math.max(0, ageDays);
            familyStageId = familyStageId == null ? "" : familyStageId;
            roleIconIds = PlayerHouseStatePayload.normalizeRoleIconIds(roleIconIds);
            summonCooldownEndsAtMs = Math.max(0L, summonCooldownEndsAtMs);
            npcGender = npcGender == null ? "" : npcGender;
            skillPoints = PlayerHouseStatePayload.normalizeSkillValues(skillPoints, 0);
            skillMaxPoints = PlayerHouseStatePayload.normalizeSkillValues(skillMaxPoints, 100);
        }
    }

    public record HouseLordEntry(String kingdomId, String houseId, String displayName, String marriedToName, String locationName, List<String> roleIconIds, int[] skillPoints) {
        public HouseLordEntry {
            kingdomId = kingdomId == null ? "" : kingdomId;
            houseId = houseId == null ? "" : houseId;
            displayName = displayName == null ? "" : displayName;
            marriedToName = marriedToName == null ? "" : marriedToName;
            locationName = locationName == null ? "" : locationName;
            roleIconIds = PlayerHouseStatePayload.normalizeRoleIconIds(roleIconIds);
            skillPoints = PlayerHouseStatePayload.normalizeSkillValues(skillPoints, 0);
        }
    }
}

