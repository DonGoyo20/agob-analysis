/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1324
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 *  net.minecraft.class_4174
 *  net.minecraft.class_5134
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_4174;
import net.minecraft.class_5134;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.castle.CastleHolder;
import trep.got.castle.PlayerCastleFastTravelCooldownHolder;
import trep.got.castle.PlayerCastleTravelHolder;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.character.PlayerGeneticTrait;
import trep.got.character.PlayerTraitEffects;
import trep.got.entity.DragonEntity;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationProfile;
import trep.got.lockpicking.PlayerLockpickingHolder;
import trep.got.mainquest.PlayerMainQuestHolder;
import trep.got.mainquest.PlayerMainQuestProfile;
import trep.got.quest.PlayerQuestHolder;
import trep.got.quest.PlayerQuestProfile;
import trep.got.registry.GotSkills;
import trep.got.relations.PlayerKingdomRelationsHolder;
import trep.got.relations.PlayerKingdomRelationsProfile;
import trep.got.relations.PlayerMarriageHolder;
import trep.got.relations.PlayerMarriageProfile;
import trep.got.relations.PlayerNpcRelationsHolder;
import trep.got.relations.PlayerNpcRelationsProfile;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.PlayerSkillProfile;
import trep.got.skill.SkillAttributeType;
import trep.got.troop.GotTroops;
import trep.got.troop.PlayerTroopHolder;
import trep.got.troop.PlayerTroopProfile;
import trep.got.village.trade.PlayerVillageTradeHolder;
import trep.got.village.trade.PlayerVillageTradeProfile;

@Mixin(value={class_1657.class})
public class PlayerEntityMixin
implements PlayerSkillHolder,
PlayerCharacterHolder,
PlayerReputationHolder,
PlayerKingdomRelationsHolder,
PlayerNpcRelationsHolder,
PlayerMarriageHolder,
CastleHolder,
PlayerQuestHolder,
PlayerMainQuestHolder,
PlayerTroopHolder,
PlayerCastleTravelHolder,
PlayerCastleFastTravelCooldownHolder,
PlayerVillageTradeHolder,
PlayerLockpickingHolder {
    @Unique
    private static final String GOT_SKILLS_KEY = "gotmod.skills";
    @Unique
    private static final String GOT_CHARACTER_KEY = "gotmod.character";
    @Unique
    private static final String GOT_REPUTATION_KEY = "gotmod.reputation";
    @Unique
    private static final String GOT_KINGDOM_RELATIONS_KEY = "gotmod.kingdom_relations";
    @Unique
    private static final String GOT_QUESTS_KEY = "gotmod.quests";
    @Unique
    private static final String GOT_MAIN_QUESTS_KEY = "gotmod.main_quests";
    @Unique
    private static final String GOT_NPC_RELATIONS_KEY = "gotmod.npc_relations";
    @Unique
    private static final String GOT_TROOPS_KEY = "gotmod.troops";
    @Unique
    private static final String GOT_MARRIAGE_KEY = "gotmod.marriage";
    @Unique
    private static final String GOT_CASTLE_KEY = "castle";
    @Unique
    private static final String GOT_UNLOCKED_CASTLES_KEY = "gotmod.unlocked_castles";
    @Unique
    private static final String GOT_FAST_TRAVEL_COOLDOWN_ENDS_AT_MS_KEY = "gotmod.fast_travel_cooldown_ends_at_ms";
    @Unique
    private static final String GOT_VILLAGE_TRADES_KEY = "gotmod.village_trades";
    @Unique
    private static final String GOT_UNLOCKED_LOCKPICK_TARGETS_KEY = "gotmod.unlocked_lockpick_targets";
    @Unique
    private static final class_2960 GOT_HEALTH_SKILL_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"health_skill_bonus");
    @Unique
    private static final class_2960 GOT_GENETIC_SCALE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"genetic_trait_scale");
    @Unique
    private PlayerSkillProfile got$skillProfile = new PlayerSkillProfile();
    @Unique
    private PlayerCharacterProfile got$characterProfile = new PlayerCharacterProfile();
    @Unique
    private ReputationProfile got$reputationProfile = new ReputationProfile();
    @Unique
    private PlayerKingdomRelationsProfile got$kingdomRelationsProfile = new PlayerKingdomRelationsProfile();
    @Unique
    private PlayerQuestProfile got$questProfile = new PlayerQuestProfile();
    @Unique
    private PlayerMainQuestProfile got$mainQuestProfile = new PlayerMainQuestProfile();
    @Unique
    private PlayerNpcRelationsProfile got$npcRelationsProfile = new PlayerNpcRelationsProfile();
    @Unique
    private PlayerTroopProfile got$troopProfile = new PlayerTroopProfile();
    @Unique
    private PlayerMarriageProfile got$marriageProfile = new PlayerMarriageProfile();
    @Unique
    private PlayerVillageTradeProfile got$villageTradeProfile = new PlayerVillageTradeProfile();
    @Unique
    private int got$appliedHealthBonus;
    @Unique
    private String got$castle = "";
    @Unique
    private final Set<String> got$unlockedCastleIds = new LinkedHashSet<String>();
    @Unique
    private final Set<String> got$unlockedLockpickTargets = new LinkedHashSet<String>();
    @Unique
    private long got$fastTravelCooldownEndsAtMs;
    @Unique
    private float got$appliedScaleMultiplier = 1.0f;

    @Override
    public PlayerSkillProfile got$getSkillProfile() {
        return this.got$skillProfile;
    }

    @Override
    public PlayerCharacterProfile got$getCharacterProfile() {
        return this.got$characterProfile;
    }

    @Override
    public ReputationProfile got$getReputationProfile() {
        return this.got$reputationProfile;
    }

    @Override
    public PlayerKingdomRelationsProfile got$getKingdomRelationsProfile() {
        return this.got$kingdomRelationsProfile;
    }

    @Override
    public PlayerQuestProfile got$getQuestProfile() {
        return this.got$questProfile;
    }

    @Override
    public PlayerMainQuestProfile got$getMainQuestProfile() {
        return this.got$mainQuestProfile;
    }

    @Override
    public PlayerNpcRelationsProfile got$getNpcRelationsProfile() {
        return this.got$npcRelationsProfile;
    }

    @Override
    public PlayerTroopProfile got$getTroopProfile() {
        return this.got$troopProfile;
    }

    @Override
    public PlayerMarriageProfile got$getMarriageProfile() {
        return this.got$marriageProfile;
    }

    @Override
    public PlayerVillageTradeProfile got$getVillageTradeProfile() {
        return this.got$villageTradeProfile;
    }

    @Override
    public String got$getCastle() {
        return this.got$castle;
    }

    @Override
    public void got$setCastle(String castleId) {
        this.got$castle = castleId == null ? "" : castleId;
    }

    @Override
    public Set<String> got$getUnlockedCastleIds() {
        return Set.copyOf(this.got$unlockedCastleIds);
    }

    @Override
    public boolean got$unlockCastle(String castleId) {
        return castleId != null && !castleId.isBlank() && this.got$unlockedCastleIds.add(castleId);
    }

    @Override
    public void got$copyUnlockedCastlesFrom(PlayerCastleTravelHolder other) {
        this.got$unlockedCastleIds.clear();
        this.got$unlockedCastleIds.addAll(other.got$getUnlockedCastleIds());
    }

    @Override
    public void got$clearUnlockedCastles() {
        this.got$unlockedCastleIds.clear();
    }

    @Override
    public Set<String> got$getUnlockedLockpickTargets() {
        return Set.copyOf(this.got$unlockedLockpickTargets);
    }

    @Override
    public boolean got$hasUnlockedLockpickTarget(String targetKey) {
        return targetKey != null && this.got$unlockedLockpickTargets.contains(targetKey);
    }

    @Override
    public boolean got$unlockLockpickTarget(String targetKey) {
        return targetKey != null && !targetKey.isBlank() && this.got$unlockedLockpickTargets.add(targetKey);
    }

    @Override
    public void got$copyUnlockedLockpickTargetsFrom(PlayerLockpickingHolder other) {
        this.got$unlockedLockpickTargets.clear();
        this.got$unlockedLockpickTargets.addAll(other.got$getUnlockedLockpickTargets());
    }

    @Override
    public long got$getFastTravelCooldownEndsAtMs() {
        return this.got$fastTravelCooldownEndsAtMs;
    }

    @Override
    public void got$setFastTravelCooldownEndsAtMs(long endsAtMs) {
        this.got$fastTravelCooldownEndsAtMs = Math.max(0L, endsAtMs);
    }

    @Inject(method={"method_5652"}, at={@At(value="TAIL")})
    private void got$writeSkillData(class_2487 nbt, CallbackInfo ci) {
        nbt.method_10566(GOT_SKILLS_KEY, (class_2520)this.got$skillProfile.toNbt());
        nbt.method_10566(GOT_CHARACTER_KEY, (class_2520)this.got$characterProfile.toNbt());
        nbt.method_10566(GOT_REPUTATION_KEY, (class_2520)this.got$reputationProfile.toNbt());
        nbt.method_10566(GOT_KINGDOM_RELATIONS_KEY, (class_2520)this.got$kingdomRelationsProfile.toNbt());
        nbt.method_10566(GOT_NPC_RELATIONS_KEY, (class_2520)this.got$npcRelationsProfile.toNbt());
        nbt.method_10566(GOT_QUESTS_KEY, (class_2520)this.got$questProfile.toNbt());
        nbt.method_10566(GOT_MAIN_QUESTS_KEY, (class_2520)this.got$mainQuestProfile.toNbt());
        nbt.method_10566(GOT_TROOPS_KEY, (class_2520)this.got$troopProfile.toNbt());
        nbt.method_10566(GOT_MARRIAGE_KEY, (class_2520)this.got$marriageProfile.toNbt());
        nbt.method_10566(GOT_VILLAGE_TRADES_KEY, (class_2520)this.got$villageTradeProfile.toNbt());
        nbt.method_10582(GOT_CASTLE_KEY, this.got$castle);
        class_2499 unlockedCastles = new class_2499();
        for (String castleId : this.got$unlockedCastleIds) {
            unlockedCastles.add((Object)class_2519.method_23256((String)castleId));
        }
        nbt.method_10566(GOT_UNLOCKED_CASTLES_KEY, (class_2520)unlockedCastles);
        class_2499 unlockedLockpickTargets = new class_2499();
        for (String targetKey : this.got$unlockedLockpickTargets) {
            unlockedLockpickTargets.add((Object)class_2519.method_23256((String)targetKey));
        }
        nbt.method_10566(GOT_UNLOCKED_LOCKPICK_TARGETS_KEY, (class_2520)unlockedLockpickTargets);
        nbt.method_10544(GOT_FAST_TRAVEL_COOLDOWN_ENDS_AT_MS_KEY, this.got$fastTravelCooldownEndsAtMs);
    }

    @Inject(method={"method_5749"}, at={@At(value="TAIL")})
    private void got$readSkillData(class_2487 nbt, CallbackInfo ci) {
        if (nbt.method_10545(GOT_SKILLS_KEY)) {
            this.got$skillProfile = PlayerSkillProfile.fromNbt(nbt.method_10562(GOT_SKILLS_KEY));
        }
        if (nbt.method_10545(GOT_CHARACTER_KEY)) {
            this.got$characterProfile = PlayerCharacterProfile.fromNbt(nbt.method_10562(GOT_CHARACTER_KEY));
        }
        if (nbt.method_10545(GOT_REPUTATION_KEY)) {
            this.got$reputationProfile = ReputationProfile.fromNbt(nbt.method_10562(GOT_REPUTATION_KEY));
        }
        if (nbt.method_10545(GOT_KINGDOM_RELATIONS_KEY)) {
            this.got$kingdomRelationsProfile = PlayerKingdomRelationsProfile.fromNbt(nbt.method_10562(GOT_KINGDOM_RELATIONS_KEY));
        }
        if (nbt.method_10545(GOT_NPC_RELATIONS_KEY)) {
            this.got$npcRelationsProfile = PlayerNpcRelationsProfile.fromNbt(nbt.method_10562(GOT_NPC_RELATIONS_KEY));
        }
        if (nbt.method_10545(GOT_QUESTS_KEY)) {
            this.got$questProfile = PlayerQuestProfile.fromNbt(nbt.method_10562(GOT_QUESTS_KEY));
        }
        if (nbt.method_10545(GOT_MAIN_QUESTS_KEY)) {
            this.got$mainQuestProfile = PlayerMainQuestProfile.fromNbt(nbt.method_10562(GOT_MAIN_QUESTS_KEY));
        }
        if (nbt.method_10545(GOT_TROOPS_KEY)) {
            this.got$troopProfile = PlayerTroopProfile.fromNbt(nbt.method_10562(GOT_TROOPS_KEY));
        }
        if (nbt.method_10545(GOT_MARRIAGE_KEY)) {
            this.got$marriageProfile = PlayerMarriageProfile.fromNbt(nbt.method_10562(GOT_MARRIAGE_KEY));
        }
        if (nbt.method_10545(GOT_VILLAGE_TRADES_KEY)) {
            this.got$villageTradeProfile = PlayerVillageTradeProfile.fromNbt(nbt.method_10562(GOT_VILLAGE_TRADES_KEY));
        }
        this.got$castle = nbt.method_10558(GOT_CASTLE_KEY);
        this.got$unlockedCastleIds.clear();
        class_2499 unlockedCastles = nbt.method_10554(GOT_UNLOCKED_CASTLES_KEY, 8);
        for (int index = 0; index < unlockedCastles.size(); ++index) {
            String castleId = unlockedCastles.method_10608(index);
            if (castleId.isBlank()) continue;
            this.got$unlockedCastleIds.add(castleId);
        }
        this.got$unlockedLockpickTargets.clear();
        class_2499 unlockedLockpickTargets = nbt.method_10554(GOT_UNLOCKED_LOCKPICK_TARGETS_KEY, 8);
        for (int index = 0; index < unlockedLockpickTargets.size(); ++index) {
            String targetKey = unlockedLockpickTargets.method_10608(index);
            if (targetKey.isBlank()) continue;
            this.got$unlockedLockpickTargets.add(targetKey);
        }
        this.got$fastTravelCooldownEndsAtMs = nbt.method_10537(GOT_FAST_TRAVEL_COOLDOWN_ENDS_AT_MS_KEY);
        this.got$syncHealthSkillModifier();
        this.got$syncGeneticTraitScale();
    }

    @Inject(method={"method_5842"}, at={@At(value="TAIL")})
    private void got$snapDragonRiderToSeat(CallbackInfo ci) {
        class_1657 self = (class_1657)this;
        class_1297 class_12972 = self.method_5854();
        if (!(class_12972 instanceof DragonEntity)) {
            return;
        }
        DragonEntity dragon = (DragonEntity)class_12972;
        if (dragon.isFlightActive()) {
            return;
        }
        class_243 seatPos = dragon.getMountedSeatWorldPos((class_1297)self);
        class_243 attachment = self.method_55668((class_1297)dragon);
        self.method_5814(seatPos.field_1352 - attachment.field_1352, seatPos.field_1351 - attachment.field_1351, seatPos.field_1350 - attachment.field_1350);
    }

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void got$updateSkillBonuses(CallbackInfo ci) {
        class_1657 player = (class_1657)this;
        if (player instanceof class_3222) {
            class_3222 serverPlayer = (class_3222)player;
            GotSkills.tick(serverPlayer);
        }
        this.got$syncHealthSkillModifier();
        this.got$syncGeneticTraitScale();
    }

    @Inject(method={"method_5643"}, at={@At(value="RETURN")})
    private void got$notifyTroopsWhenDamaged(class_1282 source, float amount, CallbackInfoReturnable<Boolean> cir) {
        class_1657 player = (class_1657)this;
        if (!cir.getReturnValueZ() || !(player instanceof class_3222)) {
            return;
        }
        class_3222 serverPlayer = (class_3222)player;
        class_1297 attacker = source.method_5529();
        if (attacker == null) {
            attacker = source.method_5526();
        }
        GotTroops.onPartyOwnerDamaged(serverPlayer, attacker);
    }

    @Inject(method={"method_18866"}, at={@At(value="TAIL")})
    private void got$trackSkillFoodConsumption(class_1937 world, class_1799 stack, class_4174 foodComponent, CallbackInfoReturnable<class_1799> cir) {
        class_1657 player = (class_1657)this;
        if (!world.method_8608() && player instanceof class_3222) {
            class_3222 serverPlayer = (class_3222)player;
            GotSkills.onFoodConsumed(serverPlayer, stack);
        }
    }

    @Unique
    private void got$syncHealthSkillModifier() {
        class_1657 player = (class_1657)this;
        class_1324 maxHealth = player.method_5996(class_5134.field_23716);
        if (maxHealth == null) {
            return;
        }
        int heartsFromHealthSkill = this.got$skillProfile.get(SkillAttributeType.HEALTH).getPoints() / 10;
        int targetBonus = heartsFromHealthSkill * 2;
        if (targetBonus == this.got$appliedHealthBonus) {
            return;
        }
        maxHealth.method_6200(GOT_HEALTH_SKILL_MODIFIER_ID);
        if (targetBonus > 0) {
            maxHealth.method_26837(new class_1322(GOT_HEALTH_SKILL_MODIFIER_ID, (double)targetBonus, class_1322.class_1323.field_6328));
        }
        this.got$appliedHealthBonus = targetBonus;
        if (player.method_6032() > player.method_6063()) {
            player.method_6033(player.method_6063());
        }
    }

    @Unique
    private void got$syncGeneticTraitScale() {
        class_1657 player = (class_1657)this;
        class_1324 scaleAttribute = player.method_5996(class_5134.field_47760);
        if (scaleAttribute == null) {
            return;
        }
        PlayerGeneticTrait geneticTrait = PlayerGeneticTrait.byId(this.got$characterProfile.getSelectedGeneticTraitId());
        float targetScale = PlayerTraitEffects.resolveScaleMultiplier(geneticTrait);
        if (Math.abs(targetScale - this.got$appliedScaleMultiplier) < 1.0E-4f) {
            return;
        }
        scaleAttribute.method_6200(GOT_GENETIC_SCALE_MODIFIER_ID);
        if (Math.abs(targetScale - 1.0f) > 1.0E-4f) {
            scaleAttribute.method_26837(new class_1322(GOT_GENETIC_SCALE_MODIFIER_ID, (double)(targetScale - 1.0f), class_1322.class_1323.field_6331));
        }
        this.got$appliedScaleMultiplier = targetScale;
        player.method_18382();
    }
}

