/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 */
package trep.got.house;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import trep.got.castle.HouseLordWorldState;
import trep.got.character.NpcAppearanceProfile;
import trep.got.character.NpcAttributeProfile;
import trep.got.house.HouseLineageWorldState;
import trep.got.kingdom.Kingdom;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.registry.GotCastleOwnerNames;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotNpcMarriage;
import trep.got.skill.SkillAttributeType;

public final class HouseLineageService {
    public static final String HERITAGE_HOUSE_LEADER = "house_leader";
    public static final String HERITAGE_SIBLING = "sibling";
    public static final String HERITAGE_CHILD = "child";

    private HouseLineageService() {
    }

    public static HouseLineageWorldState.HouseLineageProfile ensureLineage(class_3218 world, String kingdomId, String houseId, List<HouseLordWorldState.HouseLordProfile> houseLords) {
        if (world == null || kingdomId == null || kingdomId.isBlank() || houseId == null || houseId.isBlank() || houseLords == null || houseLords.isEmpty()) {
            return new HouseLineageWorldState.HouseLineageProfile(kingdomId, houseId, "", List.of(), List.of());
        }
        List<HouseLordWorldState.HouseLordProfile> lords = houseLords.stream().sorted(Comparator.comparingInt(HouseLordWorldState.HouseLordProfile::index)).toList();
        HouseLordWorldState.HouseLordProfile leader = lords.stream().filter(HouseLordWorldState.HouseLordProfile::leader).findFirst().orElse(lords.get(0));
        HouseLineageWorldState state = HouseLineageWorldState.get(world);
        HouseLineageWorldState.HouseLineageProfile existing = state.getLineage(kingdomId, houseId);
        LinkedHashMap<String, HouseLineageWorldState.LordHeritageProfile> existingLords = new LinkedHashMap<String, HouseLineageWorldState.LordHeritageProfile>();
        if (existing != null) {
            for (HouseLineageWorldState.LordHeritageProfile lord : existing.lords()) {
                existingLords.put(lord.lordId(), lord);
            }
        }
        long currentDay = GotDynastyNpcs.currentTrackedDay(world);
        ArrayList<HouseLineageWorldState.LordHeritageProfile> heritageProfiles = new ArrayList<HouseLineageWorldState.LordHeritageProfile>();
        for (HouseLordWorldState.HouseLordProfile lord : lords) {
            HouseLineageWorldState.LordHeritageProfile current = (HouseLineageWorldState.LordHeritageProfile)existingLords.get(lord.id());
            String gender = current == null || current.gender().isBlank() ? HouseLineageService.deterministicLordGender(lord.id(), lord.index()) : current.gender();
            String heritage = lord.id().equals(leader.id()) ? HERITAGE_HOUSE_LEADER : HERITAGE_SIBLING;
            String relatedLordId = lord.id().equals(leader.id()) ? "" : leader.id();
            long bornDay = current == null ? Math.max(0L, currentDay - (45L + (long)(lords.size() - lord.index()) * 5L)) : current.bornDay();
            NpcAppearanceProfile appearance = current == null || !current.appearance().isAssigned() ? HouseLineageService.generateAppearance(kingdomId, lord.id(), gender) : current.appearance();
            heritageProfiles.add(new HouseLineageWorldState.LordHeritageProfile(lord.id(), gender, heritage, relatedLordId, bornDay, appearance));
        }
        ArrayList<HouseLineageWorldState.ChildProfile> children = new ArrayList<HouseLineageWorldState.ChildProfile>(existing == null ? List.of() : existing.children());
        LinkedHashSet<String> usedNames = HouseLineageService.collectUsedNames(lords, children);
        HouseLineageService.ensureChildren(world, kingdomId, houseId, leader, lords, children, usedNames);
        HouseLineageWorldState.HouseLineageProfile updated = new HouseLineageWorldState.HouseLineageProfile(kingdomId, houseId, leader.id(), heritageProfiles, children);
        if (!updated.equals(existing)) {
            state.putLineage(updated);
        }
        return updated;
    }

    public static NpcAppearanceProfile lordAppearance(class_3218 world, String kingdomId, String houseId, HouseLordWorldState.HouseLordProfile lord, List<HouseLordWorldState.HouseLordProfile> houseLords) {
        if (lord == null) {
            return NpcAppearanceProfile.EMPTY;
        }
        HouseLineageWorldState.HouseLineageProfile lineage = HouseLineageService.ensureLineage(world, kingdomId, houseId, houseLords);
        HouseLineageWorldState.LordHeritageProfile heritage = lineage.lord(lord.id());
        return heritage == null ? HouseLineageService.generateAppearance(kingdomId, lord.id(), HouseLineageService.deterministicLordGender(lord.id(), lord.index())) : heritage.appearance();
    }

    public static HouseLineageWorldState.LordHeritageProfile findLordHeritage(class_3218 world, String lordId) {
        if (world == null || lordId == null || lordId.isBlank()) {
            return null;
        }
        for (HouseLineageWorldState.HouseLineageProfile lineage : HouseLineageWorldState.get(world).getAllLineages()) {
            HouseLineageWorldState.LordHeritageProfile heritage = lineage.lord(lordId);
            if (heritage == null) continue;
            return heritage;
        }
        return null;
    }

    public static String deterministicLordGender(String lordId, int lordIndex) {
        long seed = 0x5DEECE66DL ^ (long)(lordId == null ? 0 : lordId.hashCode()) ^ (long)lordIndex * -7046029254386353131L;
        return Math.floorMod((int)seed, 4) == 0 ? "Female" : "Male";
    }

    public static String oppositeGender(String gender) {
        return "Female".equals(new NpcAppearanceProfile(gender, 1, 1, 0).gender()) ? "Male" : "Female";
    }

    public static NpcAppearanceProfile generateAppearance(String kingdomId, String seedKey, String gender) {
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        Random random = new Random((long)(seedKey == null ? "" : seedKey).hashCode() * 31L ^ 0x7A7C15E3779B97F4L);
        String resolvedGender = new NpcAppearanceProfile(gender, 1, 1, 0).gender();
        List<Integer> clothesOptions = "Female".equals(resolvedGender) ? NpcAppearanceProfile.availableClothesVariants(parsedKingdomId) : NpcAppearanceProfile.availableMaleClothesVariants(parsedKingdomId);
        int clothesVariant = clothesOptions.get(random.nextInt(clothesOptions.size()));
        return new NpcAppearanceProfile(resolvedGender, 1 + random.nextInt(5), 1 + random.nextInt(5), clothesVariant);
    }

    public static List<PlayerHouseStatePayload.MemberEntry> createAiLineageMemberEntries(class_3218 world) {
        if (world == null) {
            return List.of();
        }
        ArrayList<PlayerHouseStatePayload.MemberEntry> entries = new ArrayList<PlayerHouseStatePayload.MemberEntry>();
        for (HouseLineageWorldState.HouseLineageProfile lineage : HouseLineageWorldState.get(world).getAllLineages()) {
            for (HouseLineageWorldState.ChildProfile child : lineage.children()) {
                String parentLine = HouseLineageService.parentLine(child);
                entries.add(new PlayerHouseStatePayload.MemberEntry(child.id(), "", child.displayName(), child.kingdomId(), child.houseId(), "", "", HouseLineageService.childLocationName(world, child), parentLine, Math.max(0, (int)(GotDynastyNpcs.currentTrackedDay(world) - child.bornDay())), GotDynastyNpcs.FamilyStage.CHILD.id(), List.of(HERITAGE_CHILD), false, 0L, true, child.appearance().gender(), child.appearance().skinVariant(), child.appearance().hairVariant(), child.appearance().clothesVariant(), HouseLineageService.skillPoints(child.attributes().skillPoints()), HouseLineageService.defaultSkillMaxPoints()));
            }
        }
        return List.copyOf(entries);
    }

    private static void ensureChildren(class_3218 world, String kingdomId, String houseId, HouseLordWorldState.HouseLordProfile leader, List<HouseLordWorldState.HouseLordProfile> lords, List<HouseLineageWorldState.ChildProfile> children, LinkedHashSet<String> usedNames) {
        List<HouseLordWorldState.HouseLordProfile> eligibleParents = lords.stream().filter(HouseLordWorldState.HouseLordProfile::hasSpouse).toList();
        if (eligibleParents.isEmpty()) {
            return;
        }
        Random random = new Random(world.method_8412() ^ (long)HouseLordWorldState.houseKey(kingdomId, houseId).hashCode() ^ 0x42E1C0FFEEBABEL);
        int targetChildCount = children.isEmpty() ? 2 + random.nextInt(3) : Math.max(2, Math.min(4, children.size()));
        boolean leaderHasChild = children.stream().anyMatch(child -> leader.id().equals(child.parentLordId()));
        if (!leaderHasChild && leader.hasSpouse()) {
            children.add(HouseLineageService.createChild(world, kingdomId, houseId, leader, children.size(), usedNames));
        }
        while (children.size() < targetChildCount) {
            HouseLordWorldState.HouseLordProfile parent = eligibleParents.get(random.nextInt(eligibleParents.size()));
            children.add(HouseLineageService.createChild(world, kingdomId, houseId, parent, children.size(), usedNames));
        }
    }

    private static HouseLineageWorldState.ChildProfile createChild(class_3218 world, String kingdomId, String houseId, HouseLordWorldState.HouseLordProfile parent, int childIndex, LinkedHashSet<String> usedNames) {
        String id = "house_lineage_child:" + kingdomId + ":" + houseId + ":" + childIndex;
        class_2960 kingdomIdentifier = class_2960.method_12829((String)kingdomId);
        Kingdom kingdom = kingdomIdentifier == null ? null : GotKingdoms.get(kingdomIdentifier);
        String houseSurname = GotNpcMarriage.normalizeHouseSurname(GotNpcMarriage.resolveHouseName(world, houseId, kingdomId));
        String displayName = GotCastleOwnerNames.createHouseLordName(kingdom, houseSurname, id, usedNames);
        usedNames.add(displayName);
        NpcAppearanceProfile appearance = NpcAppearanceProfile.generate(kingdomIdentifier, id);
        long currentDay = GotDynastyNpcs.currentTrackedDay(world);
        long ageDays = 1L + (long)Math.floorMod(id.hashCode(), 9);
        return new HouseLineageWorldState.ChildProfile(id, UUID.nameUUIDFromBytes(("house_lineage_child:" + id).getBytes(StandardCharsets.UTF_8)), kingdomId, houseId, displayName, parent.id(), parent.displayName(), parent.spouseId(), parent.spouseDisplayName(), Math.max(0L, currentDay - ageDays), "", NpcAttributeProfile.generate(GotCharacterOptions.get(houseId), id), appearance);
    }

    private static LinkedHashSet<String> collectUsedNames(List<HouseLordWorldState.HouseLordProfile> lords, List<HouseLineageWorldState.ChildProfile> children) {
        LinkedHashSet<String> usedNames = new LinkedHashSet<String>();
        for (HouseLordWorldState.HouseLordProfile lord : lords) {
            if (!lord.displayName().isBlank()) {
                usedNames.add(lord.displayName());
            }
            if (!lord.hasSpouse()) continue;
            usedNames.add(lord.spouseDisplayName());
        }
        for (HouseLineageWorldState.ChildProfile child : children) {
            if (child.displayName().isBlank()) continue;
            usedNames.add(child.displayName());
        }
        return usedNames;
    }

    private static String parentLine(HouseLineageWorldState.ChildProfile child) {
        if (child == null) {
            return "";
        }
        if (child.parentName().isBlank()) {
            return child.otherParentName();
        }
        if (child.otherParentName().isBlank()) {
            return child.parentName();
        }
        return child.parentName() + " / " + child.otherParentName();
    }

    private static String childLocationName(class_3218 world, HouseLineageWorldState.ChildProfile child) {
        if (child == null) {
            return "";
        }
        if (!child.castleId().isBlank()) {
            return GotCastles.resolveCastleLocationName(world, child.castleId());
        }
        return GotCastles.resolveHouseLordLocationName(world, child.kingdomId(), child.houseId(), child.parentName());
    }

    private static int[] skillPoints(Map<SkillAttributeType, Integer> skillPoints) {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < SkillAttributeType.ALL.size(); ++index) {
            values[index] = skillPoints.getOrDefault((Object)SkillAttributeType.ALL.get(index), 0);
        }
        return values;
    }

    private static int[] defaultSkillMaxPoints() {
        int[] values = new int[SkillAttributeType.ALL.size()];
        for (int index = 0; index < values.length; ++index) {
            values[index] = 100;
        }
        return values;
    }
}

