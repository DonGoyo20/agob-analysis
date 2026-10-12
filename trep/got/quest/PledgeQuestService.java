/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.quest;

import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.castle.CastleHolder;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleInvasionMode;
import trep.got.castle.CastleWorldState;
import trep.got.castle.PlayerCastleFastTravelCooldownHolder;
import trep.got.castle.PlayerCastleTravelHolder;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationProgressionService;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.quest.PledgeQuestLogic;
import trep.got.quest.QuestInstance;
import trep.got.registry.GotCastles;
import trep.got.registry.GotCharacters;
import trep.got.registry.GotMainQuests;
import trep.got.registry.GotNetworking;
import trep.got.registry.GotQuests;
import trep.got.registry.GotRelations;
import trep.got.troop.GotTroops;
import trep.got.world.dimension.GotDimensionKeys;

public final class PledgeQuestService {
    private PledgeQuestService() {
    }

    public static QuestInstance findActivePledgeQuest(PlayerQuestProfile profile) {
        if (profile == null) {
            return null;
        }
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!PledgeQuestLogic.isPledgeQuest(quest)) continue;
            return quest;
        }
        return null;
    }

    public static boolean canOffer(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (!(npc instanceof AbstractKingdomLordNpcEntity)) {
            return false;
        }
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        return PledgeQuestLogic.hasReachedSoldierCrossroads(reputation) && GotMainQuests.hasCompletedPledgePrerequisites(player) && !ReputationProgressionService.sharedPledgeOptions(PledgeQuestService.resolvePlayerKingdomId(player), PledgeQuestService.resolveNpcKingdomId(player, npc), reputation).isEmpty();
    }

    public static boolean isLocked(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (!(npc instanceof AbstractKingdomLordNpcEntity)) {
            return false;
        }
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        return reputation.getTitle() == ReputationTitle.SOLDIER && !PledgeQuestService.canOffer(player, npc);
    }

    public static boolean pledge(class_3222 player, AbstractKingdomLordNpcEntity lord, PledgeQuestLogic.PledgePath path) {
        PlayerQuestProfile questProfile = ((PlayerQuestHolder)player).got$getQuestProfile();
        if (!PledgeQuestService.canOffer(player, lord) || path == null) {
            return false;
        }
        CastleInstance castle = CastleWorldState.get(player.method_51469()).get(lord.got$getCastle());
        if (castle == null) {
            return false;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        character.initializeOriginalSelection(character.getSelectedKingdomId(), character.getSelectedOptionId());
        String kingdomId = castle.getOwnerKingdomId();
        ReputationPledgeOption availableOption = PledgeQuestService.resolveSharedPledgeOption(character.getSelectedKingdomId(), kingdomId, ((PlayerReputationHolder)player).got$getReputationProfile(), path.id());
        if (availableOption == null) {
            return false;
        }
        PledgeQuestService.clearActivePledgeQuests(questProfile);
        String houseId = GotCastles.resolveHouseIdForCastle(castle);
        character.setSelectedKingdomId(kingdomId);
        if (!houseId.isBlank()) {
            character.setSelectedOptionId(houseId);
        }
        GotCastles.updateDynastyOwnedCastlesForPlayer(player.method_51469(), player);
        GotRelations.initializePlayerRelationsFromCurrentKingdom(player, kingdomId);
        ((PlayerReputationHolder)player).got$getReputationProfile().setExactTitle(availableOption.title());
        GotCharacters.sendCharacterSelectionState(player);
        GotQuests.sendQuestState(player);
        player.method_7353((class_2561)class_2561.method_43470((String)("You have pledged to " + GotCastles.resolveLordName(player.method_51469(), castle) + ".")).method_27692(class_124.field_1065), true);
        return true;
    }

    public static boolean canRevoke(class_3222 player, AbstractKingdomLordNpcEntity lord) {
        boolean isRepledged;
        if (player == null || lord == null) {
            return false;
        }
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return false;
        }
        CastleInstance castle = CastleWorldState.get(world).get(lord.got$getCastle());
        if (castle == null) {
            return false;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        boolean bl = isRepledged = !character.getSelectedKingdomId().equals(character.getOriginalSelectedKingdomId()) || !character.getSelectedOptionId().equals(character.getOriginalSelectedOptionId());
        if (!isRepledged) {
            return false;
        }
        if (!GotCastles.resolveHouseIdForCastle(castle).equals(character.getSelectedOptionId())) {
            return false;
        }
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        return reputation.getTitle() != ReputationTitle.SOLDIER || reputation.getCurrentRoute() != ReputationRoute.NONE;
    }

    public static boolean revokePledge(class_3222 player, AbstractKingdomLordNpcEntity lord) {
        if (!PledgeQuestService.canRevoke(player, lord)) {
            return false;
        }
        class_3218 world = player.method_5682().method_3847(GotDimensionKeys.ICEANDFIRE);
        if (world == null) {
            return false;
        }
        PlayerCharacterProfile character = ((PlayerCharacterHolder)player).got$getCharacterProfile();
        ReputationProfile reputation = ((PlayerReputationHolder)player).got$getReputationProfile();
        GotCastles.resetPlayerHoldingsForRepledge(world, player);
        GotTroops.resetTroopsForPlayer(player);
        GotQuests.resetForPledgeRestart(player);
        character.resetCurrentSelectionToOriginal();
        GotCastles.updateDynastyOwnedCastlesForPlayer(world, player);
        character.clearCastleTitles();
        ((CastleHolder)player).got$setCastle("");
        ((PlayerCastleTravelHolder)player).got$clearUnlockedCastles();
        ((PlayerCastleFastTravelCooldownHolder)player).got$setFastTravelCooldownEndsAtMs(0L);
        GotRelations.initializePlayerRelationsFromCurrentKingdom(player, character.getOriginalSelectedKingdomId());
        reputation.setExactTitle(ReputationTitle.SOLDIER);
        reputation.setExperience(ReputationTitle.SOLDIER.totalExperienceRequired() + ReputationTitle.SOLDIER.getExperienceToNextTitle());
        GotCharacters.sendCharacterSelectionState(player);
        GotNetworking.syncFastTravelCooldown(player);
        return true;
    }

    private static String resolvePlayerKingdomId(class_3222 player) {
        return ((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId();
    }

    private static String resolveNpcKingdomId(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (npc instanceof AbstractKingdomLordNpcEntity) {
            AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)npc;
            CastleInstance castle = CastleWorldState.get(player.method_51469()).get(lord.got$getCastle());
            if (castle != null) {
                return castle.getOwnerKingdomId();
            }
        }
        return npc.getKingdomId() == null ? "" : npc.getKingdomId().toString();
    }

    private static ReputationPledgeOption resolveSharedPledgeOption(String currentKingdomId, String targetKingdomId, ReputationProfile profile, String optionId) {
        for (ReputationPledgeOption option : ReputationProgressionService.sharedPledgeOptions(currentKingdomId, targetKingdomId, profile)) {
            if (!option.id().equals(optionId)) continue;
            return option;
        }
        return null;
    }

    public static boolean canComplete(QuestInstance quest) {
        return false;
    }

    public static void onInvasionResolved(class_3222 player, CastleInvasionMode mode, boolean success) {
    }

    public static boolean canBypassTitleRequirement(class_3222 player, CastleInvasionMode mode) {
        return false;
    }

    public static String activeObjectiveActionId(PlayerQuestProfile profile) {
        return "";
    }

    public static boolean clearActivePledgeQuests(PlayerQuestProfile profile) {
        if (profile == null) {
            return false;
        }
        boolean removed = false;
        for (QuestInstance quest : profile.getActiveQuests()) {
            if (!PledgeQuestLogic.isPledgeQuest(quest)) continue;
            profile.removeQuest(quest.id());
            removed = true;
        }
        return removed;
    }
}

