/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5819
 *  net.minecraft.class_8710
 */
package trep.got.registry;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5819;
import net.minecraft.class_8710;
import trep.got.dialogue.NpcChatAction;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.network.NpcChatResultPayload;
import trep.got.network.NpcDialogueRelationPayload;
import trep.got.registry.GotCastles;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotNpcMarriage;
import trep.got.registry.GotPlayerKingdomActions;
import trep.got.relations.NpcRelationTier;
import trep.got.relations.PlayerNpcRelationsHolder;
import trep.got.relations.PlayerNpcRelationsProfile;

public final class GotNpcRelations {
    private GotNpcRelations() {
    }

    public static void initialize() {
        GotNpcMarriage.initialize();
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            PlayerNpcRelationsProfile source = ((PlayerNpcRelationsHolder)oldPlayer).got$getNpcRelationsProfile();
            ((PlayerNpcRelationsHolder)newPlayer).got$getNpcRelationsProfile().copyFrom(source);
        });
    }

    public static int getRelation(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return 0;
        }
        return ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().getRelation(npc.getPersistentNpcId());
    }

    public static int addRelation(class_3222 player, String npcId, int delta) {
        if (player == null || npcId == null || npcId.isBlank() || delta == 0) {
            return 0;
        }
        return ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().addRelation(npcId, delta);
    }

    public static void sendDialogueRelation(class_3222 player, AbstractKingdomNpcEntity npc) {
        if (player == null || npc == null) {
            return;
        }
        class_1937 class_19372 = player.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            GotCastles.ensureCastleNpcIdentity(world, npc);
        }
        GotCastles.TitleChallengeAvailability titleChallenge = GotCastles.describeTitleChallenge(player, npc);
        GotCastles.TourneyAvailability tourney = GotCastles.describeTourney(player, npc);
        int relation = GotNpcRelations.getRelation(player, npc);
        String kingdomId = npc.getKingdomId() == null ? "" : npc.getKingdomId().toString();
        long contextSeed = player.method_37908().method_8510() ^ npc.method_5667().getMostSignificantBits() ^ player.method_5667().getLeastSignificantBits();
        GotPlayerKingdomActions.DialogueContext dialogueContext = GotPlayerKingdomActions.chooseDialogueContext(player, kingdomId, relation, contextSeed);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new NpcDialogueRelationPayload(npc.method_5628(), npc.getPersistentNpcId(), relation, kingdomId, dialogueContext.contextId(), dialogueContext.subjectName(), npc.getNpcAttributes(), titleChallenge.available(), titleChallenge.cooldownSeconds(), tourney.available(), tourney.attended()));
    }

    public static void handleChatAction(class_3222 player, int targetEntityId, NpcChatAction action) {
        AbstractKingdomNpcEntity npc;
        if (player == null || action == null) {
            return;
        }
        class_1297 entity = player.method_37908().method_8469(targetEntityId);
        if (!(entity instanceof AbstractKingdomNpcEntity) || !(npc = (AbstractKingdomNpcEntity)entity).supportsDialogueInteraction()) {
            return;
        }
        if (!player.method_5805() || player.method_5858((class_1297)npc) > 49.0) {
            return;
        }
        class_1937 class_19372 = player.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            GotCastles.ensureCastleNpcIdentity(world, npc);
        }
        int currentRelation = GotNpcRelations.getRelation(player, npc);
        if (action == NpcChatAction.FLIRT && GotDynastyNpcs.isChildNpc(npc)) {
            return;
        }
        if (action == NpcChatAction.FLIRT && NpcRelationTier.fromValue(currentRelation).ordinal() < NpcRelationTier.FRIENDLY.ordinal()) {
            return;
        }
        class_5819 random = player.method_59922();
        int delta = 0;
        String responseTypeId = "neutral";
        float charismaBonus = GotNpcMarriage.charismaSuccessBonus(player);
        switch (action) {
            case TALK: {
                if (!(random.method_43057() < Math.min(1.0f, 0.05f + charismaBonus))) break;
                delta = 1;
                responseTypeId = "positive";
                break;
            }
            case INTIMIDATE: {
                delta = -3;
                responseTypeId = "negative";
                break;
            }
            case COMPLIMENT: {
                if (!(random.method_43057() < Math.min(1.0f, 0.2f + charismaBonus))) break;
                delta = 2;
                responseTypeId = "positive";
                break;
            }
            case FLIRT: {
                float successChance = npc.getNpcRole() == KingdomNpcRole.LORD ? 0.2f : 0.5f;
                boolean success = random.method_43057() < Math.min(1.0f, successChance + charismaBonus);
                delta = success ? 3 : -1;
                responseTypeId = success ? "positive" : "negative";
                break;
            }
            case CHALLENGE_FOR_TITLE: {
                GotCastles.handleTitleChallengeRequest(player, npc);
                GotNpcRelations.sendDialogueRelation(player, npc);
                return;
            }
            case TOURNEY_EASY: {
                GotCastles.handleTourneyRequest(player, npc, GotCastles.TourneyDifficulty.EASY);
                GotNpcRelations.sendDialogueRelation(player, npc);
                return;
            }
            case TOURNEY_MEDIUM: {
                GotCastles.handleTourneyRequest(player, npc, GotCastles.TourneyDifficulty.MEDIUM);
                GotNpcRelations.sendDialogueRelation(player, npc);
                return;
            }
            case TOURNEY_HARD: {
                GotCastles.handleTourneyRequest(player, npc, GotCastles.TourneyDifficulty.HARD);
                GotNpcRelations.sendDialogueRelation(player, npc);
                return;
            }
            case TRY_FOR_BABY: {
                GotDynastyNpcs.handleTryForBabyAction(player, npc);
                return;
            }
            case MARRY: {
                GotNpcMarriage.handleMarryAction(player, npc);
                return;
            }
            case DIVORCE: {
                GotNpcMarriage.handleDivorceAction(player, npc);
                return;
            }
            case TAKE_TO_PARTY: {
                GotNpcMarriage.handleTakeToPartyAction(player, npc);
                return;
            }
            case ASK_TO_LEAVE: {
                GotNpcMarriage.handleAskToLeaveAction(player, npc);
                return;
            }
        }
        int updatedRelation = delta == 0 ? currentRelation : GotNpcRelations.addRelation(player, npc.getPersistentNpcId(), delta);
        responseTypeId = GotNpcRelations.responseTypeForRelation(updatedRelation, responseTypeId);
        long responseSeed = random.method_43055() ^ npc.method_5667().getLeastSignificantBits() ^ player.method_37908().method_8510();
        String kingdomId = npc.getKingdomId() == null ? "" : npc.getKingdomId().toString();
        GotPlayerKingdomActions.DialogueContext dialogueContext = GotPlayerKingdomActions.chooseDialogueContext(player, kingdomId, updatedRelation, responseSeed);
        ServerPlayNetworking.send((class_3222)player, (class_8710)new NpcChatResultPayload(targetEntityId, responseTypeId, responseSeed, updatedRelation, kingdomId, dialogueContext.contextId(), dialogueContext.subjectName()));
    }

    private static String responseTypeForRelation(int relation, String fallback) {
        NpcRelationTier tier = NpcRelationTier.fromValue(relation);
        return switch (tier) {
            default -> throw new MatchException(null, null);
            case NpcRelationTier.HOSTILE, NpcRelationTier.UNFRIENDLY -> "negative";
            case NpcRelationTier.FRIENDLY, NpcRelationTier.TRUSTED -> "positive";
            case NpcRelationTier.NEUTRAL -> fallback == null || fallback.isBlank() ? "neutral" : fallback;
        };
    }
}

