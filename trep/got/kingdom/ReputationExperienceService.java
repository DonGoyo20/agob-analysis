/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_1802
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 */
package trep.got.kingdom;

import net.minecraft.class_1309;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import trep.got.advancement.ModAdvancementService;
import trep.got.advancement.ModAdvancementType;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationRulesRegistry;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotCharacters;

public final class ReputationExperienceService {
    private static final int CIVILIAN_KILL_EXPERIENCE = 5;
    private static final int GUARD_KILL_EXPERIENCE = 20;
    private static final int LORD_KILL_EXPERIENCE = 100;

    public static void onNpcKilled(AbstractKingdomNpcEntity npc, class_1309 attacker) {
        if (!(attacker instanceof class_3222)) {
            return;
        }
        class_3222 player = (class_3222)attacker;
        if (ReputationExperienceService.isSameFaction(player, npc)) {
            ReputationExperienceService.addExperience(player, -ReputationExperienceService.getKillExperience(npc) * 2);
        } else {
            ReputationExperienceService.addExperience(player, ReputationExperienceService.getKillExperience(npc));
        }
    }

    public static void addExperience(class_3222 player, int amount) {
        ReputationProfile profile = ((PlayerReputationHolder)player).got$getReputationProfile();
        ReputationTitle previousTitle = profile.getTitle();
        profile.addExperience(amount);
        ReputationTitle currentTitle = profile.getTitle();
        if (previousTitle != currentTitle) {
            ReputationExperienceService.announceRankUps(player, previousTitle, currentTitle, profile.getCurrentRoute());
        }
        GotCharacters.sendCharacterSelectionState(player);
    }

    public static void removeExperience(class_3222 player, int amount) {
        ReputationExperienceService.addExperience(player, -amount);
    }

    public static void setTitle(class_3222 player, ReputationTitle title) {
        ReputationProfile profile = ((PlayerReputationHolder)player).got$getReputationProfile();
        profile.setExactTitle(title);
        GotCharacters.sendCharacterSelectionState(player);
    }

    public static void completeKnighthoodCeremony(class_3222 player, int targetEntityId) {
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        if (!ReputationRulesRegistry.isKnighthoodCeremonyAvailable(character.getSelectedKingdomId())) {
            return;
        }
        ReputationProfile profile = ((PlayerReputationHolder)player).got$getReputationProfile();
        profile.setKnighthoodCeremonyCompleted(true);
        GotCharacters.sendCharacterSelectionState(player);
    }

    public static void register() {
        ReputationRulesRegistry.initialize();
    }

    public static boolean isKnighthoodAvailable(ReputationProfile profile, String kingdomId) {
        if (profile.getTitle() != ReputationTitle.SOLDIER) {
            return false;
        }
        if (!ReputationRulesRegistry.isKnighthoodCeremonyAvailable(kingdomId)) {
            return false;
        }
        int totalExpRequired = ReputationTitle.SOLDIER.totalExperienceRequired() + ReputationTitle.SOLDIER.getExperienceToNextTitle();
        return profile.getExperience() >= totalExpRequired && !profile.hasKnighthoodCeremonyCompleted();
    }

    private static boolean isSameFaction(class_3222 player, AbstractKingdomNpcEntity npc) {
        PlayerCharacterProfile profile = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        String playerKingdomId = profile.getSelectedKingdomId();
        class_2960 npcKingdomId = npc.getKingdomId();
        if (npcKingdomId == null || npcKingdomId.toString().isBlank()) {
            return false;
        }
        return !playerKingdomId.isBlank() && playerKingdomId.equals(npcKingdomId.toString());
    }

    private static int getKillExperience(AbstractKingdomNpcEntity npc) {
        if (npc instanceof AbstractKingdomLordNpcEntity) {
            return 100;
        }
        if (npc instanceof AbstractKingdomGuardNpcEntity) {
            return 20;
        }
        return 5;
    }

    private static void announceRankUps(class_3222 player, ReputationTitle previousTitle, ReputationTitle currentTitle, ReputationRoute route) {
        if (previousTitle == null || currentTitle == null || previousTitle == currentTitle) {
            return;
        }
        if (previousTitle.getRoute() == currentTitle.getRoute() && previousTitle.getTier() < currentTitle.getTier()) {
            for (ReputationTitle title : ReputationTitle.ALL) {
                if (title.getRoute() != currentTitle.getRoute() || title.getTier() <= previousTitle.getTier() || title.getTier() > currentTitle.getTier()) continue;
                ReputationExperienceService.announceRankUp(player, title);
            }
            return;
        }
        ReputationExperienceService.announceRankUp(player, currentTitle);
    }

    private static void announceRankUp(class_3222 player, ReputationTitle title) {
        ModAdvancementService.send(player, ModAdvancementType.REPUTATION_RANK_UP, "Reputation Increased", "Achieved rank: " + title.getDisplayName(), class_1802.field_8862);
    }
}

