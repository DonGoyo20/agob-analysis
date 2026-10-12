/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 */
package trep.got.client.house;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import trep.got.network.PlayerHouseStatePayload;

@Environment(value=EnvType.CLIENT)
public final class PlayerHouseClientState {
    private static Map<String, PlayerHouseStatePayload.Entry> byHouseId = Map.of();
    private static Map<String, PlayerHouseStatePayload.Entry> byOwnerUuid = Map.of();
    private static List<PlayerHouseStatePayload.MemberEntry> members = List.of();
    private static List<PlayerHouseStatePayload.HouseLordEntry> houseLords = List.of();

    private PlayerHouseClientState() {
    }

    public static void apply(PlayerHouseStatePayload payload, class_310 client) {
        client.execute(() -> {
            List<PlayerHouseStatePayload.Entry> houses = payload.houses();
            byHouseId = houses.stream().collect(Collectors.toUnmodifiableMap(PlayerHouseStatePayload.Entry::houseId, entry -> entry, (left, right) -> right));
            byOwnerUuid = houses.stream().collect(Collectors.toUnmodifiableMap(PlayerHouseStatePayload.Entry::ownerUuid, entry -> entry, (left, right) -> right));
            members = List.copyOf(payload.members());
            houseLords = List.copyOf(payload.houseLords());
        });
    }

    public static void clear() {
        byHouseId = Map.of();
        byOwnerUuid = Map.of();
        members = List.of();
        houseLords = List.of();
    }

    public static PlayerHouseStatePayload.Entry getByHouseId(String houseId) {
        return houseId == null ? null : byHouseId.get(houseId);
    }

    public static PlayerHouseStatePayload.Entry getByOwnerUuid(String ownerUuid) {
        return ownerUuid == null ? null : byOwnerUuid.get(ownerUuid);
    }

    public static List<PlayerHouseStatePayload.Entry> getAll() {
        return List.copyOf(byHouseId.values());
    }

    public static List<PlayerHouseStatePayload.MemberEntry> getMembers(String kingdomId, String houseId) {
        return members.stream().filter(entry -> PlayerHouseClientState.matches(entry.kingdomId(), entry.houseId(), kingdomId, houseId)).toList();
    }

    public static List<PlayerHouseStatePayload.HouseLordEntry> getHouseLords(String kingdomId, String houseId) {
        return houseLords.stream().filter(entry -> PlayerHouseClientState.matches(entry.kingdomId(), entry.houseId(), kingdomId, houseId)).toList();
    }

    private static boolean matches(String entryKingdomId, String entryHouseId, String kingdomId, String houseId) {
        return entryKingdomId != null && entryKingdomId.equals(kingdomId) && entryHouseId != null && entryHouseId.equals(houseId);
    }
}

