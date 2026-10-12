/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.character;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.character.PlayerSkinBuilderProfile;

public final class PlayerTailorProfile {
    private static final int DISCOVERY_SCHEMA = 1;
    private final Set<String> discoveredClothing = new LinkedHashSet<String>();
    private final Set<String> unlockedClothing = new LinkedHashSet<String>();
    private final Set<String> unlockedDyeClothing = new LinkedHashSet<String>();

    public Set<String> getDiscoveredClothing() {
        return Set.copyOf(this.discoveredClothing);
    }

    public Set<String> getUnlockedClothing() {
        return Set.copyOf(this.unlockedClothing);
    }

    public Set<String> getUnlockedDyeClothing() {
        return Set.copyOf(this.unlockedDyeClothing);
    }

    public boolean isDiscovered(String slot, String clothingId) {
        return this.discoveredClothing.contains(PlayerTailorProfile.key(slot, clothingId));
    }

    public boolean isUnlocked(String slot, String clothingId) {
        return this.unlockedClothing.contains(PlayerTailorProfile.key(slot, clothingId));
    }

    public boolean isDyeUnlocked(String slot, String clothingId, String dyeId) {
        return this.unlockedDyeClothing.contains(PlayerTailorProfile.dyeKey(slot, clothingId, dyeId));
    }

    public boolean discover(String slot, String clothingId) {
        return this.discoveredClothing.add(PlayerTailorProfile.key(slot, clothingId));
    }

    public boolean unlock(String slot, String clothingId) {
        String key = PlayerTailorProfile.key(slot, clothingId);
        this.discoveredClothing.add(key);
        this.unlockedDyeClothing.add(PlayerTailorProfile.dyeKey(slot, clothingId, "none"));
        return this.unlockedClothing.add(key);
    }

    public boolean unlockDye(String slot, String clothingId, String dyeId) {
        String key = PlayerTailorProfile.key(slot, clothingId);
        this.discoveredClothing.add(key);
        this.unlockedClothing.add(key);
        return this.unlockedDyeClothing.add(PlayerTailorProfile.dyeKey(slot, clothingId, dyeId));
    }

    public void unlockDefaultClothes(PlayerSkinBuilderProfile skinBuilderProfile) {
        if (skinBuilderProfile == null) {
            return;
        }
        if (!skinBuilderProfile.shirtId().isBlank()) {
            this.unlock("shirt", skinBuilderProfile.shirtId());
        }
        if (!skinBuilderProfile.pantsId().isBlank()) {
            this.unlock("pants", skinBuilderProfile.pantsId());
        }
    }

    public void copyFrom(PlayerTailorProfile other) {
        this.discoveredClothing.clear();
        this.unlockedClothing.clear();
        this.unlockedDyeClothing.clear();
        if (other != null) {
            this.discoveredClothing.addAll(other.discoveredClothing);
            this.unlockedClothing.addAll(other.unlockedClothing);
            this.unlockedDyeClothing.addAll(other.unlockedDyeClothing);
        }
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10569("discovery_schema", 1);
        nbt.method_10566("discovered", (class_2520)PlayerTailorProfile.toList(this.discoveredClothing));
        nbt.method_10566("unlocked", (class_2520)PlayerTailorProfile.toList(this.unlockedClothing));
        nbt.method_10566("unlocked_dyes", (class_2520)PlayerTailorProfile.toList(this.unlockedDyeClothing));
        return nbt;
    }

    public static PlayerTailorProfile fromNbt(class_2487 nbt) {
        PlayerTailorProfile profile = new PlayerTailorProfile();
        if (nbt == null || nbt.method_33133()) {
            return profile;
        }
        PlayerTailorProfile.readInto(profile.discoveredClothing, nbt.method_10554("discovered", 8));
        PlayerTailorProfile.readInto(profile.unlockedClothing, nbt.method_10554("unlocked", 8));
        PlayerTailorProfile.readInto(profile.unlockedDyeClothing, nbt.method_10554("unlocked_dyes", 8));
        for (String key : profile.unlockedClothing) {
            profile.unlockedDyeClothing.add(key + ":none");
        }
        if (!nbt.method_10573("discovery_schema", 99)) {
            profile.discoveredClothing.retainAll(profile.unlockedClothing);
        }
        return profile;
    }

    public static String key(String slot, String clothingId) {
        String safeSlot = slot == null ? "" : slot.trim().toLowerCase(Locale.ROOT);
        String safeId = clothingId == null ? "" : clothingId.trim().toLowerCase(Locale.ROOT);
        return safeSlot + ":" + safeId;
    }

    public static String dyeKey(String slot, String clothingId, String dyeId) {
        String safeDye = dyeId == null || dyeId.isBlank() ? "none" : dyeId.trim().toLowerCase(Locale.ROOT);
        return PlayerTailorProfile.key(slot, clothingId) + ":" + safeDye;
    }

    private static class_2499 toList(Set<String> values) {
        class_2499 list = new class_2499();
        for (String value : values) {
            if (value == null || value.isBlank()) continue;
            list.add((Object)class_2519.method_23256((String)value));
        }
        return list;
    }

    private static void readInto(Set<String> target, class_2499 list) {
        for (int index = 0; index < list.size(); ++index) {
            String value = list.method_10608(index);
            if (value.isBlank()) continue;
            target.add(value);
        }
    }
}

