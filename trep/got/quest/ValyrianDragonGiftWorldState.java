/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.quest;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;

public final class ValyrianDragonGiftWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.valyrian_dragon_gifts";
    private static final class_18.class_8645<ValyrianDragonGiftWorldState> TYPE = new class_18.class_8645(ValyrianDragonGiftWorldState::new, ValyrianDragonGiftWorldState::fromNbt, class_4284.field_45077);
    private final Set<UUID> deliveredPlayers = new LinkedHashSet<UUID>();
    private final Set<UUID> completedQuestPlayers = new LinkedHashSet<UUID>();
    private final Map<String, GiftRecord> giftsByLordKey = new LinkedHashMap<String, GiftRecord>();

    public static ValyrianDragonGiftWorldState get(class_3218 world) {
        return (ValyrianDragonGiftWorldState)world.method_8503().method_30002().method_17983().method_17924(TYPE, STATE_ID);
    }

    public boolean hasPlayerDelivered(UUID playerUuid) {
        return playerUuid != null && this.deliveredPlayers.contains(playerUuid);
    }

    public boolean hasPlayerCompletedQuest(UUID playerUuid) {
        return playerUuid != null && this.completedQuestPlayers.contains(playerUuid);
    }

    public void markPlayerCompletedQuest(UUID playerUuid) {
        if (playerUuid == null) {
            return;
        }
        this.completedQuestPlayers.add(playerUuid);
        this.method_80();
    }

    public boolean hasLordGift(String lordKey) {
        return lordKey != null && this.giftsByLordKey.containsKey(lordKey);
    }

    public void recordGift(UUID playerUuid, GiftRecord record) {
        if (playerUuid != null) {
            this.deliveredPlayers.add(playerUuid);
        }
        if (record != null && !record.lordKey().isBlank()) {
            this.giftsByLordKey.put(record.lordKey(), record);
        }
        this.method_80();
    }

    public void updateDragon(String lordKey, UUID dragonUuid, UUID lordEntityUuid) {
        GiftRecord record = this.giftsByLordKey.get(lordKey);
        if (record == null) {
            return;
        }
        this.giftsByLordKey.put(lordKey, new GiftRecord(record.lordKey(), record.castleId(), record.lordNpcId(), lordEntityUuid == null ? record.lordEntityUuid() : lordEntityUuid, dragonUuid));
        this.method_80();
    }

    public Collection<GiftRecord> gifts() {
        return List.copyOf(this.giftsByLordKey.values());
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 players = new class_2499();
        for (UUID uUID : this.deliveredPlayers) {
            players.add((Object)class_2519.method_23256((String)uUID.toString()));
        }
        nbt.method_10566("delivered_players", (class_2520)players);
        class_2499 completedPlayers = new class_2499();
        for (UUID playerUuid : this.completedQuestPlayers) {
            completedPlayers.add((Object)class_2519.method_23256((String)playerUuid.toString()));
        }
        nbt.method_10566("completed_quest_players", (class_2520)completedPlayers);
        class_2499 class_24992 = new class_2499();
        for (GiftRecord record : this.giftsByLordKey.values()) {
            class_2487 gift = new class_2487();
            gift.method_10582("lord_key", record.lordKey());
            gift.method_10582("castle_id", record.castleId());
            gift.method_10582("lord_npc_id", record.lordNpcId());
            if (record.lordEntityUuid() != null) {
                gift.method_25927("lord_entity_uuid", record.lordEntityUuid());
            }
            if (record.dragonUuid() != null) {
                gift.method_25927("dragon_uuid", record.dragonUuid());
            }
            class_24992.add((Object)gift);
        }
        nbt.method_10566("gifts", (class_2520)class_24992);
        return nbt;
    }

    private static ValyrianDragonGiftWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        ValyrianDragonGiftWorldState state = new ValyrianDragonGiftWorldState();
        class_2499 players = nbt.method_10554("delivered_players", 8);
        for (int index = 0; index < players.size(); ++index) {
            try {
                state.deliveredPlayers.add(UUID.fromString(players.method_10608(index)));
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        class_2499 completedPlayers = nbt.method_10554("completed_quest_players", 8);
        for (int index = 0; index < completedPlayers.size(); ++index) {
            try {
                state.completedQuestPlayers.add(UUID.fromString(completedPlayers.method_10608(index)));
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        class_2499 gifts = nbt.method_10554("gifts", 10);
        for (int index = 0; index < gifts.size(); ++index) {
            class_2487 gift = gifts.method_10602(index);
            String lordKey = gift.method_10558("lord_key");
            if (lordKey.isBlank()) continue;
            UUID lordEntityUuid = gift.method_25928("lord_entity_uuid") ? gift.method_25926("lord_entity_uuid") : null;
            UUID dragonUuid = gift.method_25928("dragon_uuid") ? gift.method_25926("dragon_uuid") : null;
            state.giftsByLordKey.put(lordKey, new GiftRecord(lordKey, gift.method_10558("castle_id"), gift.method_10558("lord_npc_id"), lordEntityUuid, dragonUuid));
        }
        return state;
    }

    public record GiftRecord(String lordKey, String castleId, String lordNpcId, UUID lordEntityUuid, UUID dragonUuid) {
    }
}

