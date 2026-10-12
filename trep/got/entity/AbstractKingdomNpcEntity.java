/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1314
 *  net.minecraft.class_1352
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5132$class_5133
 */
package trep.got.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1352;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5132;
import trep.got.character.NpcAppearanceProfile;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.entity.KingdomNpcCastleRoadWanderGoal;
import trep.got.entity.KingdomNpcConversationGoal;
import trep.got.entity.KingdomNpcNightHomeGoal;
import trep.got.entity.KingdomNpcRole;
import trep.got.entity.KingdomNpcVisitMarketGoal;
import trep.got.entity.PartyTroopEntity;
import trep.got.registry.GotCastles;
import trep.got.troop.CommanderActionType;
import trep.got.troop.GotTroops;
import trep.got.troop.PartyFormationType;

public abstract class AbstractKingdomNpcEntity
extends AbstractDialogueHumanoidEntity {
    private static final double FLEE_DISTANCE = 12.0;
    private static final class_2940<String> CASTLE_NPC_ID = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> NPC_GENDER = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Integer> FEMALE_SKIN_VARIANT = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> FEMALE_HAIR_VARIANT = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> FEMALE_CLOTHES_VARIANT = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<String> MARRIED_PLAYER_UUID = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> MARRIED_PLAYER_NAME = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Boolean> INDEPENDENT_MARRIAGE_NPC = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> NOBLE_SPOUSE = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<String> SPOUSE_PROFILE_ID = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Boolean> MARRIAGE_PARTY_COMPANION = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Long> BABY_COOLDOWN_UNLOCK_DAY = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_39965);
    private static final class_2940<Long> NPC_TALK_UNTIL = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_39965);
    private static final class_2940<Integer> NPC_TALK_VARIANT = class_2945.method_12791(AbstractKingdomNpcEntity.class, (class_2941)class_2943.field_13327);
    private static final String CASTLE_NPC_ID_KEY = "gotmod.castle_npc_id";
    private static final String NPC_GENDER_KEY = "gotmod.npc_gender";
    private static final String NPC_APPEARANCE_KEY = "gotmod.npc_appearance";
    private static final String MARRIED_PLAYER_UUID_KEY = "gotmod.married_player_uuid";
    private static final String MARRIED_PLAYER_NAME_KEY = "gotmod.married_player_name";
    private static final String INDEPENDENT_MARRIAGE_NPC_KEY = "gotmod.independent_marriage_npc";
    private static final String NOBLE_SPOUSE_KEY = "gotmod.noble_spouse";
    private static final String SPOUSE_PROFILE_ID_KEY = "gotmod.spouse_profile_id";
    private static final String MARRIAGE_PARTY_COMPANION_KEY = "gotmod.marriage_party_companion";
    private static final String BABY_COOLDOWN_UNLOCK_DAY_KEY = "gotmod.baby_cooldown_unlock_day";
    private static final String COMMAND_TYPE_KEY = "gotmod.marriage_command_type";
    private static final String COMMAND_TARGET_KEY = "gotmod.marriage_command_target";
    private static final String COMMAND_ORIGIN_KEY = "gotmod.marriage_command_origin";
    private static final String FORMATION_TYPE_KEY = "gotmod.marriage_formation_type";
    private static final String TARGET_ENTITY_UUID_KEY = "gotmod.marriage_target_entity_uuid";
    private static final String TARGET_GATE_POS_KEY = "gotmod.marriage_target_gate_pos";
    private static final String COMMAND_FACING_YAW_KEY = "gotmod.marriage_command_facing_yaw";
    private static final double PARTY_COMPANION_ATTACK_RANGE = 3.0;
    private static final double PARTY_COMPANION_COMMAND_REACHED_DISTANCE = 1.15;
    private UUID castleThreatPlayerUuid;
    private long castleThreatExpiresAt;
    private boolean fleeingThreat;
    private class_1309 cachedCombatThreat;
    private int combatThreatCacheTick = -1;
    private NpcAppearanceProfile npcAppearance = NpcAppearanceProfile.DEFAULT_MALE;
    private CommanderActionType partyCommandType = CommanderActionType.RECALL;
    private class_2338 partyCommandTarget;
    private class_2338 partyCommandOrigin;
    private PartyFormationType partyFormationType = PartyFormationType.LINE;
    private UUID partyTargetEntityUuid;
    private class_2338 partyTargetGatePos;
    private float partyCommandFacingYaw;
    private UUID npcSocialPartnerUuid;
    private long npcSocialReservedUntil;
    private UUID lastGreetedPlayerUuid;
    private long lastGreetedPlayerAt = Long.MIN_VALUE;

    protected AbstractKingdomNpcEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createKingdomNpcAttributes() {
        return AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes();
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(CASTLE_NPC_ID, (Object)"");
        builder.method_56912(NPC_GENDER, (Object)"Male");
        builder.method_56912(FEMALE_SKIN_VARIANT, (Object)1);
        builder.method_56912(FEMALE_HAIR_VARIANT, (Object)1);
        builder.method_56912(FEMALE_CLOTHES_VARIANT, (Object)0);
        builder.method_56912(MARRIED_PLAYER_UUID, (Object)"");
        builder.method_56912(MARRIED_PLAYER_NAME, (Object)"");
        builder.method_56912(INDEPENDENT_MARRIAGE_NPC, (Object)false);
        builder.method_56912(NOBLE_SPOUSE, (Object)false);
        builder.method_56912(SPOUSE_PROFILE_ID, (Object)"");
        builder.method_56912(MARRIAGE_PARTY_COMPANION, (Object)false);
        builder.method_56912(BABY_COOLDOWN_UNLOCK_DAY, (Object)0L);
        builder.method_56912(NPC_TALK_UNTIL, (Object)0L);
        builder.method_56912(NPC_TALK_VARIANT, (Object)0);
    }

    @Override
    protected void method_5959() {
        super.method_5959();
        this.field_6201.method_6277(3, (class_1352)new KingdomNpcNightHomeGoal(this));
        this.field_6201.method_6277(5, (class_1352)new KingdomNpcConversationGoal(this));
        this.field_6201.method_6277(6, (class_1352)new KingdomNpcVisitMarketGoal(this));
        this.field_6201.method_6277(6, (class_1352)new KingdomNpcCastleRoadWanderGoal(this));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.CIVILIAN;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return class_1799.field_8037;
    }

    public String getCastleNpcId() {
        return (String)this.field_6011.method_12789(CASTLE_NPC_ID);
    }

    public void setCastleNpcId(String castleNpcId) {
        this.field_6011.method_12778(CASTLE_NPC_ID, (Object)(castleNpcId == null ? "" : castleNpcId));
    }

    public String getPersistentNpcId() {
        return this.getCastleNpcId();
    }

    public String getMarriedPlayerUuid() {
        return (String)this.field_6011.method_12789(MARRIED_PLAYER_UUID);
    }

    public void setMarriedPlayerUuid(String marriedPlayerUuid) {
        String currentOwnerUuid;
        String previousOwnerUuid = this.getMarriedPlayerUuid();
        String string = currentOwnerUuid = marriedPlayerUuid == null ? "" : marriedPlayerUuid;
        if (previousOwnerUuid.equals(currentOwnerUuid)) {
            return;
        }
        boolean partyCompanion = this.isMarriagePartyCompanion();
        this.field_6011.method_12778(MARRIED_PLAYER_UUID, (Object)currentOwnerUuid);
        GotTroops.onMarriageCompanionMembershipChanged(this, previousOwnerUuid, partyCompanion, currentOwnerUuid, partyCompanion);
    }

    public String getMarriedPlayerName() {
        return (String)this.field_6011.method_12789(MARRIED_PLAYER_NAME);
    }

    public void setMarriedPlayerName(String marriedPlayerName) {
        this.field_6011.method_12778(MARRIED_PLAYER_NAME, (Object)(marriedPlayerName == null ? "" : marriedPlayerName));
    }

    public boolean isMarriedToPlayer() {
        return !this.getMarriedPlayerUuid().isBlank();
    }

    public boolean isIndependentMarriageNpc() {
        return (Boolean)this.field_6011.method_12789(INDEPENDENT_MARRIAGE_NPC);
    }

    public void setIndependentMarriageNpc(boolean independentMarriageNpc) {
        this.field_6011.method_12778(INDEPENDENT_MARRIAGE_NPC, (Object)independentMarriageNpc);
    }

    public boolean isNobleSpouse() {
        return (Boolean)this.field_6011.method_12789(NOBLE_SPOUSE);
    }

    public void setNobleSpouse(boolean nobleSpouse) {
        this.field_6011.method_12778(NOBLE_SPOUSE, (Object)nobleSpouse);
    }

    public String getSpouseProfileId() {
        return (String)this.field_6011.method_12789(SPOUSE_PROFILE_ID);
    }

    public void setSpouseProfileId(String spouseProfileId) {
        this.field_6011.method_12778(SPOUSE_PROFILE_ID, (Object)(spouseProfileId == null ? "" : spouseProfileId));
    }

    public boolean isMarriagePartyCompanion() {
        return (Boolean)this.field_6011.method_12789(MARRIAGE_PARTY_COMPANION);
    }

    public void setMarriagePartyCompanion(boolean marriagePartyCompanion) {
        boolean previousPartyCompanion = this.isMarriagePartyCompanion();
        if (previousPartyCompanion == marriagePartyCompanion) {
            return;
        }
        String ownerUuid = this.getMarriedPlayerUuid();
        this.field_6011.method_12778(MARRIAGE_PARTY_COMPANION, (Object)marriagePartyCompanion);
        GotTroops.onMarriageCompanionMembershipChanged(this, ownerUuid, previousPartyCompanion, ownerUuid, marriagePartyCompanion);
    }

    public boolean agob$isNpcTalking() {
        class_1937 world = this.method_37908();
        return world != null && world.method_8510() < (Long)this.field_6011.method_12789(NPC_TALK_UNTIL);
    }

    public int agob$getNpcTalkAnimationVariant() {
        return Math.max(0, (Integer)this.field_6011.method_12789(NPC_TALK_VARIANT));
    }

    boolean canStartNpcSocialInteraction(long now) {
        return this.method_5805() && !this.isInDialogueScene() && !this.isMarriagePartyCompanion() && this.method_5968() == null && now >= this.npcSocialReservedUntil && now >= (Long)this.field_6011.method_12789(NPC_TALK_UNTIL);
    }

    boolean reserveNpcSocialInteraction(AbstractKingdomNpcEntity partner, long reservedUntil) {
        if (partner == null || !this.canStartNpcSocialInteraction(this.method_37908().method_8510())) {
            return false;
        }
        this.npcSocialPartnerUuid = partner.method_5667();
        this.npcSocialReservedUntil = reservedUntil;
        this.method_5942().method_6340();
        return true;
    }

    void startNpcTalking(AbstractKingdomNpcEntity partner, long talkUntil, int animationVariant) {
        if (partner == null) {
            return;
        }
        this.npcSocialPartnerUuid = partner.method_5667();
        this.npcSocialReservedUntil = talkUntil;
        this.field_6011.method_12778(NPC_TALK_UNTIL, (Object)Math.max((Long)this.field_6011.method_12789(NPC_TALK_UNTIL), talkUntil));
        this.field_6011.method_12778(NPC_TALK_VARIANT, (Object)Math.max(0, animationVariant));
        this.method_5942().method_6340();
    }

    void clearNpcSocialInteraction(AbstractKingdomNpcEntity partner) {
        if (partner != null && this.npcSocialPartnerUuid != null && !partner.method_5667().equals(this.npcSocialPartnerUuid)) {
            return;
        }
        this.npcSocialPartnerUuid = null;
        this.npcSocialReservedUntil = 0L;
        this.field_6011.method_12778(NPC_TALK_UNTIL, (Object)0L);
    }

    AbstractKingdomNpcEntity getNpcSocialPartner(class_3218 world) {
        AbstractKingdomNpcEntity npc;
        if (world == null || this.npcSocialPartnerUuid == null) {
            return null;
        }
        class_1297 entity = world.method_14190(this.npcSocialPartnerUuid);
        return entity instanceof AbstractKingdomNpcEntity && (npc = (AbstractKingdomNpcEntity)entity).method_5805() ? npc : null;
    }

    boolean canGreetPlayer(class_3222 player, long now) {
        if (player == null || !player.method_5805() || this.isInDialogueScene() || this.method_5968() != null || this.agob$getHumanoidAnimationId() != 0) {
            return false;
        }
        return this.lastGreetedPlayerUuid == null || !this.lastGreetedPlayerUuid.equals(player.method_5667()) || now - this.lastGreetedPlayerAt >= 1200L;
    }

    void startPlayerGreeting(class_3222 player) {
        if (player == null) {
            return;
        }
        this.lastGreetedPlayerUuid = player.method_5667();
        this.lastGreetedPlayerAt = this.method_37908().method_8510();
        this.method_5942().method_6340();
        this.agob$playHumanoidAnimation(4);
    }

    public long getBabyCooldownUnlockDay() {
        return (Long)this.field_6011.method_12789(BABY_COOLDOWN_UNLOCK_DAY);
    }

    public void setBabyCooldownUnlockDay(long babyCooldownUnlockDay) {
        this.field_6011.method_12778(BABY_COOLDOWN_UNLOCK_DAY, (Object)Math.max(0L, babyCooldownUnlockDay));
    }

    public CommanderActionType getPartyCommandType() {
        return this.partyCommandType;
    }

    public void setPartyCommandType(CommanderActionType partyCommandType) {
        this.partyCommandType = partyCommandType == null ? CommanderActionType.RECALL : partyCommandType;
    }

    public class_2338 getPartyCommandTarget() {
        return this.partyCommandTarget;
    }

    public void setPartyCommandTarget(class_2338 partyCommandTarget) {
        this.partyCommandTarget = partyCommandTarget == null ? null : partyCommandTarget.method_10062();
    }

    public class_2338 getPartyCommandOrigin() {
        return this.partyCommandOrigin;
    }

    public void setPartyCommandOrigin(class_2338 partyCommandOrigin) {
        this.partyCommandOrigin = partyCommandOrigin == null ? null : partyCommandOrigin.method_10062();
    }

    public PartyFormationType getPartyFormationType() {
        return this.partyFormationType;
    }

    public void setPartyFormationType(PartyFormationType partyFormationType) {
        this.partyFormationType = partyFormationType == null ? PartyFormationType.LINE : partyFormationType;
    }

    public UUID getPartyTargetEntityUuid() {
        return this.partyTargetEntityUuid;
    }

    public void setPartyTargetEntityUuid(UUID partyTargetEntityUuid) {
        this.partyTargetEntityUuid = partyTargetEntityUuid;
    }

    public class_2338 getPartyTargetGatePos() {
        return this.partyTargetGatePos;
    }

    public void setPartyTargetGatePos(class_2338 partyTargetGatePos) {
        this.partyTargetGatePos = partyTargetGatePos == null ? null : partyTargetGatePos.method_10062();
    }

    public float getPartyCommandFacingYaw() {
        return this.partyCommandFacingYaw;
    }

    public void setPartyCommandFacingYaw(float partyCommandFacingYaw) {
        this.partyCommandFacingYaw = partyCommandFacingYaw;
    }

    public void clearPartyCommandState() {
        this.partyCommandType = CommanderActionType.RECALL;
        this.partyCommandTarget = null;
        this.partyCommandOrigin = null;
        this.partyFormationType = PartyFormationType.LINE;
        this.partyTargetEntityUuid = null;
        this.partyTargetGatePos = null;
        this.partyCommandFacingYaw = 0.0f;
    }

    public NpcAppearanceProfile getNpcAppearance() {
        return this.npcAppearance;
    }

    public void setNpcAppearance(NpcAppearanceProfile appearance) {
        NpcAppearanceProfile normalized;
        this.npcAppearance = normalized = appearance == null || !appearance.isAssigned() ? NpcAppearanceProfile.DEFAULT_MALE : appearance;
        this.field_6011.method_12778(NPC_GENDER, (Object)normalized.gender());
        this.field_6011.method_12778(FEMALE_SKIN_VARIANT, (Object)normalized.skinVariant());
        this.field_6011.method_12778(FEMALE_HAIR_VARIANT, (Object)normalized.hairVariant());
        this.field_6011.method_12778(FEMALE_CLOTHES_VARIANT, (Object)normalized.clothesVariant());
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(CASTLE_NPC_ID_KEY, this.getCastleNpcId());
        nbt.method_10582(NPC_GENDER_KEY, this.npcAppearance.gender());
        nbt.method_10566(NPC_APPEARANCE_KEY, (class_2520)this.npcAppearance.toNbt());
        nbt.method_10582(MARRIED_PLAYER_UUID_KEY, this.getMarriedPlayerUuid());
        nbt.method_10582(MARRIED_PLAYER_NAME_KEY, this.getMarriedPlayerName());
        nbt.method_10556(INDEPENDENT_MARRIAGE_NPC_KEY, this.isIndependentMarriageNpc());
        nbt.method_10556(NOBLE_SPOUSE_KEY, this.isNobleSpouse());
        nbt.method_10582(SPOUSE_PROFILE_ID_KEY, this.getSpouseProfileId());
        nbt.method_10556(MARRIAGE_PARTY_COMPANION_KEY, this.isMarriagePartyCompanion());
        nbt.method_10544(BABY_COOLDOWN_UNLOCK_DAY_KEY, this.getBabyCooldownUnlockDay());
        nbt.method_10582(COMMAND_TYPE_KEY, this.partyCommandType.id());
        nbt.method_10582(FORMATION_TYPE_KEY, this.partyFormationType.id());
        if (this.partyCommandTarget != null) {
            nbt.method_10544(COMMAND_TARGET_KEY, this.partyCommandTarget.method_10063());
        }
        if (this.partyCommandOrigin != null) {
            nbt.method_10544(COMMAND_ORIGIN_KEY, this.partyCommandOrigin.method_10063());
        }
        if (this.partyTargetEntityUuid != null) {
            nbt.method_25927(TARGET_ENTITY_UUID_KEY, this.partyTargetEntityUuid);
        }
        if (this.partyTargetGatePos != null) {
            nbt.method_10544(TARGET_GATE_POS_KEY, this.partyTargetGatePos.method_10063());
        }
        nbt.method_10548(COMMAND_FACING_YAW_KEY, this.partyCommandFacingYaw);
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(CASTLE_NPC_ID_KEY)) {
            this.setCastleNpcId(nbt.method_10558(CASTLE_NPC_ID_KEY));
        }
        if (nbt.method_10573(NPC_APPEARANCE_KEY, 10)) {
            this.setNpcAppearance(NpcAppearanceProfile.fromNbt(nbt.method_10562(NPC_APPEARANCE_KEY)));
        } else if (nbt.method_10545(NPC_GENDER_KEY)) {
            this.setNpcAppearance(new NpcAppearanceProfile(nbt.method_10558(NPC_GENDER_KEY), 1, 1, 0));
        } else {
            this.setNpcAppearance(NpcAppearanceProfile.DEFAULT_MALE);
        }
        this.setMarriedPlayerUuid(nbt.method_10558(MARRIED_PLAYER_UUID_KEY));
        this.setMarriedPlayerName(nbt.method_10558(MARRIED_PLAYER_NAME_KEY));
        this.setIndependentMarriageNpc(nbt.method_10577(INDEPENDENT_MARRIAGE_NPC_KEY));
        this.setNobleSpouse(nbt.method_10577(NOBLE_SPOUSE_KEY));
        this.setSpouseProfileId(nbt.method_10558(SPOUSE_PROFILE_ID_KEY));
        this.setMarriagePartyCompanion(nbt.method_10577(MARRIAGE_PARTY_COMPANION_KEY));
        if (nbt.method_10573(BABY_COOLDOWN_UNLOCK_DAY_KEY, 99)) {
            this.setBabyCooldownUnlockDay(nbt.method_10537(BABY_COOLDOWN_UNLOCK_DAY_KEY));
        }
        if (nbt.method_10545(COMMAND_TYPE_KEY)) {
            this.partyCommandType = CommanderActionType.byId(nbt.method_10558(COMMAND_TYPE_KEY));
        }
        if (nbt.method_10545(FORMATION_TYPE_KEY)) {
            this.partyFormationType = PartyFormationType.byId(nbt.method_10558(FORMATION_TYPE_KEY));
        }
        if (nbt.method_10545(COMMAND_TARGET_KEY)) {
            this.partyCommandTarget = class_2338.method_10092((long)nbt.method_10537(COMMAND_TARGET_KEY));
        }
        if (nbt.method_10545(COMMAND_ORIGIN_KEY)) {
            this.partyCommandOrigin = class_2338.method_10092((long)nbt.method_10537(COMMAND_ORIGIN_KEY));
        }
        if (nbt.method_25928(TARGET_ENTITY_UUID_KEY)) {
            this.partyTargetEntityUuid = nbt.method_25926(TARGET_ENTITY_UUID_KEY);
        }
        if (nbt.method_10545(TARGET_GATE_POS_KEY)) {
            this.partyTargetGatePos = class_2338.method_10092((long)nbt.method_10537(TARGET_GATE_POS_KEY));
        }
        if (nbt.method_10545(COMMAND_FACING_YAW_KEY)) {
            this.partyCommandFacingYaw = nbt.method_10583(COMMAND_FACING_YAW_KEY);
        }
    }

    @Override
    public void method_5674(class_2940<?> data) {
        super.method_5674(data);
        if (NPC_GENDER.equals(data) || FEMALE_SKIN_VARIANT.equals(data) || FEMALE_HAIR_VARIANT.equals(data) || FEMALE_CLOTHES_VARIANT.equals(data)) {
            this.npcAppearance = new NpcAppearanceProfile((String)this.field_6011.method_12789(NPC_GENDER), (Integer)this.field_6011.method_12789(FEMALE_SKIN_VARIANT), (Integer)this.field_6011.method_12789(FEMALE_HAIR_VARIANT), (Integer)this.field_6011.method_12789(FEMALE_CLOTHES_VARIANT));
        }
    }

    public boolean method_5643(class_1282 source, float amount) {
        class_1937 class_19372;
        class_1297 attackingEntity = source.method_5529();
        class_1297 sourceEntity = source.method_5526();
        if (GotTroops.areAlliedPartyEntities(attackingEntity, (class_1297)this) || GotTroops.areAlliedPartyEntities(sourceEntity, (class_1297)this)) {
            return false;
        }
        boolean damaged = super.method_5643(source, amount);
        if (damaged && !this.method_37908().method_8608() && (class_19372 = this.method_37908()) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            class_3222 attacker = GotCastles.resolvePlayerAttacker(source);
            if (attacker != null && !this.got$getCastle().isBlank()) {
                GotCastles.onCastleNpcHurt(serverWorld, this, attacker);
            }
        }
        return damaged;
    }

    public void method_6078(class_1282 damageSource) {
        class_1937 class_19372;
        if (!this.method_37908().method_8608() && (class_19372 = this.method_37908()) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            if (!this.got$getCastle().isBlank()) {
                GotCastles.onCastleNpcKilled(serverWorld, this);
            }
        }
        super.method_6078(damageSource);
    }

    @Override
    public void method_5773() {
        AbstractKingdomNpcEntity mob;
        class_3218 serverWorld;
        block6: {
            block5: {
                class_1937 class_19372;
                super.method_5773();
                if (this.method_37908().method_8608() || !((class_19372 = this.method_37908()) instanceof class_3218)) break block5;
                serverWorld = (class_3218)class_19372;
                if (!this.isInDialogueScene()) break block6;
            }
            return;
        }
        if (this.isMarriagePartyCompanion()) {
            this.tickMarriagePartyCompanion(serverWorld);
            return;
        }
        AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
        if (abstractKingdomNpcEntity instanceof class_1308 && (mob = abstractKingdomNpcEntity).method_5968() != null && !this.method_6057((class_1297)mob.method_5968())) {
            mob.method_5980(null);
            this.method_5942().method_6340();
        }
        this.tickCastleThreat(serverWorld);
    }

    public void alertAgainst(class_3222 player, long expiresAtTick) {
        if (player == null) {
            return;
        }
        this.castleThreatPlayerUuid = player.method_5667();
        this.castleThreatExpiresAt = Math.max(this.castleThreatExpiresAt, expiresAtTick);
        this.fleeingThreat = this.shouldFleeThreat();
    }

    protected boolean shouldFightThreat() {
        KingdomNpcRole role = this.getNpcRole();
        return role.isGuardDuty() || role == KingdomNpcRole.LORD;
    }

    protected boolean shouldFleeThreat() {
        return !this.shouldFightThreat();
    }

    protected void tickCastleThreat(class_3218 world) {
        class_1309 resolvedThreat;
        if (this.castleThreatPlayerUuid == null) {
            return;
        }
        class_3222 threat = world.method_8503().method_3760().method_14602(this.castleThreatPlayerUuid);
        if (threat == null || !threat.method_5805() || threat.method_37908() != world) {
            this.clearCastleThreat();
            return;
        }
        boolean threatInCastle = GotCastles.isWithinCastleClaim(world, this.got$getCastle(), threat.method_24515());
        if (threatInCastle) {
            this.castleThreatExpiresAt = world.method_8510() + 1200L;
        } else if (world.method_8510() >= this.castleThreatExpiresAt) {
            this.clearCastleThreat();
            return;
        }
        if (this.fleeingThreat) {
            this.fleeFromThreat(threat);
            return;
        }
        if (this.shouldFightThreat() && (resolvedThreat = this.resolveCombatThreat(world, threat)) != null) {
            this.fightThreat(resolvedThreat);
        }
    }

    protected void fightThreat(class_1309 threat) {
        if (threat == null || !threat.method_5805() || !this.method_6057((class_1297)threat)) {
            AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
            if (abstractKingdomNpcEntity instanceof class_1308) {
                AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
                mob.method_5980(null);
            }
            this.method_5942().method_6340();
            return;
        }
        AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
        if (abstractKingdomNpcEntity instanceof class_1308) {
            AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
            mob.method_5980(threat);
        }
        if (this.method_5858((class_1297)threat) > 9.0) {
            this.method_5942().method_6335((class_1297)threat, 1.15);
        } else if (this.field_6012 % 10 == 0) {
            this.method_6121((class_1297)threat);
        }
    }

    protected void fleeFromThreat(class_3222 threat) {
        class_243 away = this.method_19538().method_1020(threat.method_19538());
        class_243 horizontalAway = new class_243(away.field_1352, 0.0, away.field_1350);
        if (horizontalAway.method_1027() < 1.0E-4) {
            horizontalAway = new class_243(1.0, 0.0, 0.0);
        }
        class_243 target = this.method_19538().method_1019(horizontalAway.method_1029().method_1021(12.0));
        this.method_5942().method_6337(target.field_1352, this.method_23318(), target.field_1350, 1.2);
    }

    protected class_1309 resolveCombatThreat(class_3218 world, class_3222 threatPlayer) {
        if (this.combatThreatCacheTick >= 0 && this.field_6012 - this.combatThreatCacheTick < 10 && this.cachedCombatThreat != null && this.cachedCombatThreat.method_5805()) {
            return this.cachedCombatThreat;
        }
        this.combatThreatCacheTick = this.field_6012;
        ArrayList<Object> candidates = new ArrayList<Object>();
        if (threatPlayer.method_5805() && threatPlayer.method_37908() == world) {
            candidates.add(threatPlayer);
        }
        class_238 searchBox = this.method_5829().method_1009(24.0, 8.0, 24.0);
        for (PartyTroopEntity troop2 : world.method_8390(PartyTroopEntity.class, searchBox, troop -> troop.method_5805() && threatPlayer.method_5667().equals(troop.getOwnerUuid()) && (this.got$getCastle().isBlank() || GotCastles.isWithinCastleClaim(world, this.got$getCastle(), troop.method_24515())))) {
            candidates.add(troop2);
        }
        this.cachedCombatThreat = candidates.stream().filter(arg_0 -> ((AbstractKingdomNpcEntity)this).method_6057(arg_0)).min(Comparator.comparingDouble(arg_0 -> ((AbstractKingdomNpcEntity)this).method_5858(arg_0))).orElse(null);
        return this.cachedCombatThreat;
    }

    protected void clearCastleThreat() {
        this.castleThreatPlayerUuid = null;
        this.castleThreatExpiresAt = 0L;
        this.fleeingThreat = false;
        this.cachedCombatThreat = null;
        this.combatThreatCacheTick = -1;
        AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
        if (abstractKingdomNpcEntity instanceof class_1308) {
            AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
            mob.method_5980(null);
        }
        this.method_5942().method_6340();
    }

    public void resetCastleThreat() {
        this.clearCastleThreat();
    }

    protected void tickMarriagePartyCompanion(class_3218 world) {
        class_1309 playerConflictTarget;
        class_3222 owner = this.resolveMarriedPlayer(world);
        if (owner == null || !owner.method_5805()) {
            AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
            if (abstractKingdomNpcEntity instanceof class_1308) {
                AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
                mob.method_5980(null);
            }
            this.method_5942().method_6340();
            return;
        }
        if (this.partyCommandType == CommanderActionType.HALT) {
            AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
            if (abstractKingdomNpcEntity instanceof class_1308) {
                AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
                mob.method_5980(null);
            }
            this.method_5942().method_6340();
            return;
        }
        if (this.partyCommandType == CommanderActionType.FOLLOW_ME) {
            AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
            if (abstractKingdomNpcEntity instanceof class_1308) {
                AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
                mob.method_5980(null);
            }
            class_2338 slot = GotTroops.calculateMarriageCompanionFormationSlot(world, owner.method_5667(), this, owner.method_19538(), owner.method_36454(), this.partyFormationType);
            this.moveToPartyCommandTarget(class_243.method_24955((class_2382)slot));
            return;
        }
        if (this.partyTargetEntityUuid != null) {
            class_1309 livingTarget;
            class_1297 explicitTarget = world.method_14190(this.partyTargetEntityUuid);
            if (explicitTarget instanceof class_1309 && (livingTarget = (class_1309)explicitTarget).method_5805()) {
                this.partyCommandTarget = explicitTarget.method_24515();
                this.fightPartyTarget(livingTarget);
                return;
            }
            this.partyTargetEntityUuid = null;
        }
        if ((playerConflictTarget = GotTroops.findPlayerConflictTarget(world, owner.method_5667(), (class_1309)this)) != null) {
            this.fightPartyTarget(playerConflictTarget);
            return;
        }
        class_1309 battleTarget = GotCastles.findBattleTargetForMarriageCompanion(world, this);
        if (battleTarget != null) {
            this.fightPartyTarget(battleTarget);
            return;
        }
        class_243 targetPos = null;
        if (this.partyCommandTarget != null) {
            targetPos = class_243.method_24955((class_2382)this.partyCommandTarget);
        } else if (this.partyCommandType == CommanderActionType.RECALL || this.partyCommandType == null) {
            targetPos = owner.method_19538();
        }
        if (targetPos == null) {
            this.method_5942().method_6340();
            return;
        }
        this.moveToPartyCommandTarget(targetPos);
    }

    private void moveToPartyCommandTarget(class_243 targetPos) {
        if (this.method_5707(targetPos) > 1.3224999999999998) {
            this.method_5942().method_6337(targetPos.field_1352, targetPos.field_1351, targetPos.field_1350, this.partyCommandSpeed());
        } else {
            this.method_5942().method_6340();
        }
    }

    protected void fightPartyTarget(class_1309 target) {
        if (target == null || !target.method_5805()) {
            AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
            if (abstractKingdomNpcEntity instanceof class_1308) {
                AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
                mob.method_5980(null);
            }
            this.method_5942().method_6340();
            return;
        }
        AbstractKingdomNpcEntity abstractKingdomNpcEntity = this;
        if (abstractKingdomNpcEntity instanceof class_1308) {
            AbstractKingdomNpcEntity mob = abstractKingdomNpcEntity;
            mob.method_5980(target);
        }
        if (this.method_5858((class_1297)target) > 9.0) {
            this.method_5942().method_6335((class_1297)target, 1.15);
        } else if (this.field_6012 % 10 == 0) {
            this.method_6121((class_1297)target);
        }
    }

    protected double partyCommandSpeed() {
        return this.partyCommandType == CommanderActionType.CHARGE ? 1.15 : 1.05;
    }

    public class_3222 resolveMarriedPlayer(class_3218 world) {
        String uuid = this.getMarriedPlayerUuid();
        if (world == null || uuid.isBlank()) {
            return null;
        }
        try {
            return world.method_8503().method_3760().method_14602(UUID.fromString(uuid));
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }
}

