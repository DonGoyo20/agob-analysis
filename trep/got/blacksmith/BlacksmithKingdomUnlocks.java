/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import org.jetbrains.annotations.Nullable;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;

public final class BlacksmithKingdomUnlocks {
    public static final int INTELLIGENCE_INCREMENT = 5;
    private static final List<class_2960> WESTEROS_KINGDOM_TABS = List.of(class_2960.method_60655((String)"agob", (String)"the_north"), class_2960.method_60655((String)"agob", (String)"the_vale"), class_2960.method_60655((String)"agob", (String)"the_riverlands"), class_2960.method_60655((String)"agob", (String)"the_westerlands"), class_2960.method_60655((String)"agob", (String)"iron_islands"), class_2960.method_60655((String)"agob", (String)"the_reach"), class_2960.method_60655((String)"agob", (String)"the_stormlands"), class_2960.method_60655((String)"agob", (String)"the_crownlands"), class_2960.method_60655((String)"agob", (String)"dorne"));
    private static final List<class_2960> ESSOS_KINGDOM_TABS = List.of(class_2960.method_60655((String)"agob", (String)"targaryen"), class_2960.method_60655((String)"agob", (String)"braavos"), class_2960.method_60655((String)"agob", (String)"pentos"), class_2960.method_60655((String)"agob", (String)"volantis"), class_2960.method_60655((String)"agob", (String)"tyrosh"), class_2960.method_60655((String)"agob", (String)"myr"), class_2960.method_60655((String)"agob", (String)"blackfyre"), class_2960.method_60655((String)"agob", (String)"golden_company"), class_2960.method_60655((String)"agob", (String)"meereen"), class_2960.method_60655((String)"agob", (String)"yunkai"), class_2960.method_60655((String)"agob", (String)"astapor"), class_2960.method_60655((String)"agob", (String)"qarth"));
    private static final List<class_2960> MATERIAL_TABS = List.of(class_2960.method_60655((String)"agob", (String)"copper"), class_2960.method_60655((String)"agob", (String)"iron"), class_2960.method_60655((String)"agob", (String)"steel"));

    private BlacksmithKingdomUnlocks() {
    }

    public static boolean isMaterialTab(class_2960 kingdomId) {
        return MATERIAL_TABS.contains(kingdomId);
    }

    public static List<class_2960> westerosTabs() {
        return WESTEROS_KINGDOM_TABS;
    }

    public static List<class_2960> essosTabs() {
        return ESSOS_KINGDOM_TABS;
    }

    public static List<class_2960> addonTabs() {
        ArrayList<class_2960> tabs = new ArrayList<class_2960>();
        for (class_2960 kingdomId : AgobAddonRegistry.blacksmithKingdomTabs()) {
            Kingdom kingdom;
            if (kingdomId == null || kingdomId.method_12836().equals("agob") || BlacksmithKingdomUnlocks.isMaterialTab(kingdomId) || (kingdom = GotKingdoms.get(kingdomId)) == null) continue;
            tabs.add(kingdomId);
        }
        return List.copyOf(tabs);
    }

    public static boolean hasAddonTabs() {
        return !BlacksmithKingdomUnlocks.addonTabs().isEmpty();
    }

    public static List<class_2960> kingdomTabs() {
        ArrayList<class_2960> tabs = new ArrayList<class_2960>(WESTEROS_KINGDOM_TABS.size() + ESSOS_KINGDOM_TABS.size() + MATERIAL_TABS.size() + BlacksmithKingdomUnlocks.addonTabs().size());
        tabs.addAll(WESTEROS_KINGDOM_TABS);
        tabs.addAll(ESSOS_KINGDOM_TABS);
        tabs.addAll(BlacksmithKingdomUnlocks.addonTabs());
        tabs.addAll(MATERIAL_TABS);
        return List.copyOf(tabs);
    }

    public static List<class_2960> unlockableKingdomTabs() {
        return BlacksmithKingdomUnlocks.kingdomTabs().stream().filter(kingdomId -> !BlacksmithKingdomUnlocks.isMaterialTab(kingdomId)).toList();
    }

    public static Set<String> unlockedKingdomIds(class_3222 player) {
        LinkedHashSet<String> unlocked = new LinkedHashSet<String>(BlacksmithKingdomUnlocks.materialTabIds());
        class_2960 ownKingdomId = BlacksmithKingdomUnlocks.getOwnKingdomId(player);
        if (ownKingdomId != null) {
            unlocked.add(ownKingdomId.toString());
        }
        if (player instanceof PlayerSkillHolder) {
            PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
            unlocked.addAll(skillHolder.got$getSkillProfile().getUnlockedBlacksmithKingdomIds());
        }
        return Set.copyOf(unlocked);
    }

    public static Set<String> talkedKingdomIds(class_3222 player) {
        if (player instanceof PlayerSkillHolder) {
            PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
            return skillHolder.got$getSkillProfile().getTalkedBlacksmithKingdomIds();
        }
        return Set.of();
    }

    public static boolean isUnlocked(class_3222 player, class_2960 kingdomId) {
        PlayerSkillHolder skillHolder;
        if (BlacksmithKingdomUnlocks.isMaterialTab(kingdomId)) {
            return true;
        }
        class_2960 ownKingdomId = BlacksmithKingdomUnlocks.getOwnKingdomId(player);
        if (kingdomId.equals((Object)ownKingdomId)) {
            return true;
        }
        return player instanceof PlayerSkillHolder && (skillHolder = (PlayerSkillHolder)player).got$getSkillProfile().hasUnlockedBlacksmithKingdom(kingdomId);
    }

    public static boolean hasTalkedToBlacksmith(class_3222 player, class_2960 kingdomId) {
        PlayerSkillHolder skillHolder;
        return player instanceof PlayerSkillHolder && (skillHolder = (PlayerSkillHolder)player).got$getSkillProfile().hasTalkedToBlacksmithKingdom(kingdomId);
    }

    public static int unlockedKingdomCount(class_3222 player) {
        if (!(player instanceof PlayerSkillHolder)) {
            return 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        PlayerSkillProfile profile = skillHolder.got$getSkillProfile();
        int count = 0;
        for (String kingdomId : profile.getUnlockedBlacksmithKingdomIds()) {
            class_2960 parsed = class_2960.method_12829((String)kingdomId);
            if (parsed == null || BlacksmithKingdomUnlocks.isMaterialTab(parsed)) continue;
            ++count;
        }
        return count;
    }

    public static int nextIntelligenceRequirement(class_3222 player) {
        return (BlacksmithKingdomUnlocks.unlockedKingdomCount(player) + 1) * 5;
    }

    public static boolean canUnlock(class_3222 player, class_2960 kingdomId) {
        return BlacksmithKingdomUnlocks.isUnlockEligible(player, kingdomId, BlacksmithKingdomUnlocks.nextIntelligenceRequirement(player));
    }

    public static boolean isUnlockEligible(class_3222 player, class_2960 kingdomId, int intelligenceRequirement) {
        if (kingdomId == null || BlacksmithKingdomUnlocks.isMaterialTab(kingdomId) || BlacksmithKingdomUnlocks.isUnlocked(player, kingdomId)) {
            return false;
        }
        if (!BlacksmithKingdomUnlocks.hasTalkedToBlacksmith(player, kingdomId)) {
            return false;
        }
        return BlacksmithKingdomUnlocks.getIntelligencePoints(player) >= intelligenceRequirement;
    }

    public static int getIntelligencePoints(class_3222 player) {
        if (!(player instanceof PlayerSkillHolder)) {
            return 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        return skillHolder.got$getSkillProfile().get(SkillAttributeType.INTELLIGENCE).getPoints();
    }

    @Nullable
    public static class_2960 getOwnKingdomId(class_3222 player) {
        if (!(player instanceof PlayerCharacterHolder)) {
            return null;
        }
        PlayerCharacterHolder characterHolder = (PlayerCharacterHolder)player;
        PlayerCharacterProfile profile = characterHolder.got$getCharacterProfile();
        String kingdomId = profile.getOriginalSelectedKingdomId();
        if (kingdomId.isBlank()) {
            kingdomId = profile.getSelectedKingdomId();
        }
        return kingdomId.isBlank() ? null : class_2960.method_12829((String)kingdomId);
    }

    public static Set<String> materialTabIds() {
        return MATERIAL_TABS.stream().map(class_2960::toString).collect(Collectors.toUnmodifiableSet());
    }
}

