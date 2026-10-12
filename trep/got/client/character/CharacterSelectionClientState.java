/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_434
 *  net.minecraft.class_434$class_9678
 *  net.minecraft.class_437
 */
package trep.got.client.character;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_434;
import net.minecraft.class_437;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.client.screen.CharacterSelectionScreen;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;
import trep.got.network.CharacterSelectionStatePayload;
import trep.got.network.SkillPointGainPayload;
import trep.got.skill.SkillAttributeType;

@Environment(value=EnvType.CLIENT)
public final class CharacterSelectionClientState {
    private static String selectedReligionId = "";
    private static String selectedKingdomId = "";
    private static String selectedOptionId = "";
    private static String originalSelectedKingdomId = "";
    private static String originalSelectedOptionId = "";
    private static String selectedGeneticTraitId = "";
    private static String[] selectedPersonalityTraitIds = new String[0];
    private static PlayerSkinBuilderProfile skinBuilderProfile = PlayerSkinBuilderProfile.DEFAULT;
    private static boolean requiresSelection;
    private static boolean spawnSearchInProgress;
    private static final EnumMap<SkillAttributeType, Integer> skillPoints;
    private static final EnumMap<SkillAttributeType, Integer> skillMaxPoints;
    private static final EnumMap<SkillAttributeType, Integer> shirtSkillBonuses;
    private static final EnumMap<SkillAttributeType, Integer> pantsSkillBonuses;
    private static int reputationExperience;
    private static String reputationTitleId;
    private static ReputationRoute reputationRoute;
    private static boolean knighthoodCeremonyCompleted;
    private static boolean sellswordContractCompleted;
    private static boolean baptised;

    private CharacterSelectionClientState() {
    }

    public static void clear() {
        selectedReligionId = "";
        selectedKingdomId = "";
        selectedOptionId = "";
        originalSelectedKingdomId = "";
        originalSelectedOptionId = "";
        selectedGeneticTraitId = "";
        selectedPersonalityTraitIds = new String[0];
        skinBuilderProfile = PlayerSkinBuilderProfile.DEFAULT;
        requiresSelection = false;
        spawnSearchInProgress = false;
        reputationExperience = 0;
        reputationTitleId = "peasant";
        reputationRoute = ReputationRoute.NONE;
        knighthoodCeremonyCompleted = false;
        sellswordContractCompleted = false;
        baptised = false;
        CharacterSelectionClientState.clearSkillState();
    }

    public static String getSelectedReligionId() {
        return selectedReligionId;
    }

    public static String getSelectedKingdomId() {
        return selectedKingdomId;
    }

    public static String getSelectedOptionId() {
        return selectedOptionId;
    }

    public static String getOriginalSelectedKingdomId() {
        return originalSelectedKingdomId;
    }

    public static String getOriginalSelectedOptionId() {
        return originalSelectedOptionId;
    }

    public static int getSkillPoints(SkillAttributeType type) {
        return skillPoints.getOrDefault((Object)type, 0);
    }

    public static int getEffectiveSkillPoints(SkillAttributeType type) {
        return CharacterSelectionClientState.getSkillPoints(type) + CharacterSelectionClientState.getShirtSkillBonus(type) + CharacterSelectionClientState.getPantsSkillBonus(type);
    }

    public static int getShirtSkillBonus(SkillAttributeType type) {
        return shirtSkillBonuses.getOrDefault((Object)type, 0);
    }

    public static int getPantsSkillBonus(SkillAttributeType type) {
        return pantsSkillBonuses.getOrDefault((Object)type, 0);
    }

    public static String getSelectedGeneticTraitId() {
        return selectedGeneticTraitId;
    }

    public static String[] getSelectedPersonalityTraitIds() {
        return Arrays.copyOf(selectedPersonalityTraitIds, selectedPersonalityTraitIds.length);
    }

    public static PlayerSkinBuilderProfile getSkinBuilderProfile() {
        return skinBuilderProfile;
    }

    public static void setSkinBuilderProfile(PlayerSkinBuilderProfile profile) {
        skinBuilderProfile = profile == null ? PlayerSkinBuilderProfile.DEFAULT : profile;
    }

    public static Map<SkillAttributeType, Integer> getSkillPoints() {
        return Map.copyOf(skillPoints);
    }

    public static int getSkillMaxPoints(SkillAttributeType type) {
        return skillMaxPoints.getOrDefault((Object)type, 100);
    }

    public static ReputationProfile getReputationProfile() {
        ReputationProfile profile = new ReputationProfile();
        profile.setExactTitle(ReputationTitle.byId(reputationTitleId));
        profile.setCurrentRoute(reputationRoute);
        profile.setExperience(reputationExperience);
        profile.setKnighthoodCeremonyCompleted(knighthoodCeremonyCompleted);
        profile.setSellswordContractCompleted(sellswordContractCompleted);
        profile.setBaptised(baptised);
        return profile;
    }

    public static ReputationTitle getReputationTitle() {
        return ReputationTitle.byId(reputationTitleId);
    }

    public static boolean requiresSelection() {
        return requiresSelection;
    }

    public static void applySkillPointGain(SkillPointGainPayload payload, class_310 client) {
        SkillAttributeType type = SkillAttributeType.byId(payload.skillId());
        if (type != null) {
            skillPoints.put(type, payload.totalPoints());
        }
        if (client.field_1724 == null || client.field_1705 == null || type == null) {
            return;
        }
        String pointLabel = payload.gainedPoints() == 1 ? "skill point" : "skill points";
        client.execute(() -> client.field_1705.method_1743().method_1812((class_2561)class_2561.method_43470((String)(payload.playerName() + " earned " + payload.gainedPoints() + " " + pointLabel + " in " + type.getDisplayName())).method_27692(class_124.field_1068)));
    }

    public static void setSpawnSearchInProgress(boolean spawnSearchInProgress, class_310 client) {
        CharacterSelectionClientState.spawnSearchInProgress = spawnSearchInProgress;
        if (spawnSearchInProgress) {
            client.method_1507((class_437)new class_434(() -> !spawnSearchInProgress, class_434.class_9678.field_51489));
            return;
        }
        if (client.field_1755 instanceof class_434) {
            client.method_1507(null);
        }
    }

    public static void apply(CharacterSelectionStatePayload payload, class_310 client) {
        selectedReligionId = payload.selectedReligionId();
        selectedKingdomId = payload.selectedKingdomId();
        selectedOptionId = payload.selectedOptionId();
        originalSelectedKingdomId = payload.originalSelectedKingdomId();
        originalSelectedOptionId = payload.originalSelectedOptionId();
        selectedGeneticTraitId = payload.selectedGeneticTraitId();
        selectedPersonalityTraitIds = Arrays.copyOf(payload.selectedPersonalityTraitIds(), payload.selectedPersonalityTraitIds().length);
        skinBuilderProfile = payload.skinBuilderProfile();
        requiresSelection = payload.requiresSelection();
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            skillPoints.put(type, payload.getSkillPoints(type));
            skillMaxPoints.put(type, payload.getSkillMaxPoints(type));
            shirtSkillBonuses.put(type, payload.getShirtSkillBonus(type));
            pantsSkillBonuses.put(type, payload.getPantsSkillBonus(type));
        }
        reputationExperience = payload.reputationExperience();
        reputationTitleId = payload.reputationTitleId();
        reputationRoute = payload.reputationRoute();
        knighthoodCeremonyCompleted = payload.knighthoodCeremonyCompleted();
        sellswordContractCompleted = payload.sellswordContractCompleted();
        baptised = payload.baptised();
        if (requiresSelection) {
            if (!(client.field_1755 instanceof CharacterSelectionScreen)) {
                client.method_1507((class_437)new CharacterSelectionScreen());
            }
            return;
        }
        if (spawnSearchInProgress) {
            return;
        }
        class_437 currentScreen = client.field_1755;
        if (currentScreen instanceof CharacterSelectionScreen) {
            client.method_1507(null);
        }
    }

    public static void enforceScreen(class_310 client) {
        if (!requiresSelection || client.field_1724 == null || spawnSearchInProgress) {
            return;
        }
        if (!(client.field_1755 instanceof CharacterSelectionScreen)) {
            client.method_1507((class_437)new CharacterSelectionScreen());
        }
    }

    private static void clearSkillState() {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            skillPoints.put(type, 0);
            skillMaxPoints.put(type, 100);
            shirtSkillBonuses.put(type, 0);
            pantsSkillBonuses.put(type, 0);
        }
    }

    static {
        skillPoints = new EnumMap(SkillAttributeType.class);
        skillMaxPoints = new EnumMap(SkillAttributeType.class);
        shirtSkillBonuses = new EnumMap(SkillAttributeType.class);
        pantsSkillBonuses = new EnumMap(SkillAttributeType.class);
        reputationTitleId = "peasant";
        reputationRoute = ReputationRoute.NONE;
        CharacterSelectionClientState.clearSkillState();
    }
}

