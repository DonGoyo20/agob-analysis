/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1266
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1315
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3730
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 *  net.minecraft.class_5425
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import net.minecraft.class_1266;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1315;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3730;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5425;
import org.jetbrains.annotations.Nullable;
import trep.got.character.PlayerCharacterHolder;
import trep.got.entity.AbstractHostileWorldEventNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.quest.WildlingRaiderCampQuestService;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;

public final class WildlingRaiderNpcEntity
extends AbstractHostileWorldEventNpcEntity {
    private static final class_2940<Boolean> QUEST_CAMP_LEADER = class_2945.method_12791(WildlingRaiderNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> AWAITING_QUEST_CAMP_DECISION = class_2945.method_12791(WildlingRaiderNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final String AGGRESSION_ENABLED_KEY = "gotmod.wildling_raider_aggression_enabled";
    private static final String QUEST_CAMP_QUEST_ID_KEY = "gotmod.wildling_raider_quest_camp_quest_id";
    private static final String WILDLING_KINGDOM_ID = "agob:wildling";
    private static final class_2960[] TEXTURES = new class_2960[]{class_2960.method_60655((String)"agob", (String)"textures/entity/npc/wildling.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/peasant_wildling.png")};
    private static final class_1792[] WEAPONS = new class_1792[]{class_1802.field_8475, class_1802.field_8371, GotItems.COPPER_AXE, GotItems.COPPER_SWORD, class_1802.field_8062, class_1802.field_8528};
    private String questCampQuestId = "";
    private boolean aggressionEnabled = true;

    public WildlingRaiderNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createWildlingRaiderAttributes() {
        return AbstractWorldEventNpcEntity.createWorldEventNpcAttributes().method_26868(class_5134.field_23719, 0.24);
    }

    protected void method_5958() {
        class_3222 closest;
        super.method_5958();
        if (!this.method_37908().method_8608() && this.isQuestCampLeader() && this.isAwaitingQuestCampDecision() && this.field_6012 % 10 == 0 && (closest = (class_3222)this.method_37908().method_18460((class_1297)this, 20.0)) != null) {
            WildlingRaiderCampQuestService.checkPlayerProximity(this, closest);
        }
    }

    @Override
    public void method_5773() {
        super.method_5773();
        if (this.method_37908().method_8608() || this.isInDialogueScene() || !this.method_5805() || !this.aggressionEnabled || this.field_6012 % 10 != 0) {
            return;
        }
        class_1309 target = this.method_5968();
        if (!(target == null || target.method_5805() && this.isValidWildlingRaiderTarget(target))) {
            this.method_5980(null);
            target = null;
        }
        if (target == null) {
            this.acquireNearbyWildlingRaiderTarget();
        }
    }

    @Override
    public void method_6078(class_1282 source) {
        super.method_6078(source);
        if (!this.method_37908().method_8608() && !this.questCampQuestId.isBlank()) {
            WildlingRaiderCampQuestService.onRaiderKilled(this);
        }
    }

    public boolean method_5643(class_1282 source, float amount) {
        boolean damaged = super.method_5643(source, amount);
        if (damaged && !this.method_37908().method_8608() && !this.questCampQuestId.isBlank()) {
            WildlingRaiderCampQuestService.onQuestCampRaiderDamaged(this, source);
        }
        return damaged;
    }

    @Nullable
    public class_1315 method_5943(class_5425 world, class_1266 difficulty, class_3730 spawnReason, @Nullable class_1315 entityData) {
        class_1315 initializedData = super.method_5943(world, difficulty, spawnReason, entityData);
        if (spawnReason == class_3730.field_16459 || spawnReason == class_3730.field_16472) {
            this.aggressionEnabled = true;
            this.field_6011.method_12778(QUEST_CAMP_LEADER, (Object)false);
            this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)false);
            this.questCampQuestId = "";
        }
        return initializedData;
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(QUEST_CAMP_LEADER, (Object)false);
        builder.method_56912(AWAITING_QUEST_CAMP_DECISION, (Object)false);
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10556(AGGRESSION_ENABLED_KEY, this.aggressionEnabled);
        nbt.method_10582(QUEST_CAMP_QUEST_ID_KEY, this.questCampQuestId);
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.aggressionEnabled = nbt.method_10577(AGGRESSION_ENABLED_KEY);
        this.questCampQuestId = nbt.method_10558(QUEST_CAMP_QUEST_ID_KEY);
    }

    @Override
    public class_2960 getTextureId() {
        return TEXTURES[Math.floorMod(this.getSkinVariant(), TEXTURES.length)];
    }

    @Override
    public class_2960 getKingdomId() {
        return class_2960.method_60655((String)"agob", (String)"wildling");
    }

    @Override
    protected int variantCount() {
        return TEXTURES.length;
    }

    @Override
    protected void applyWorldEventLoadout() {
        this.method_5673(class_1304.field_6173, new class_1799((class_1935)WEAPONS[this.method_59922().method_43048(WEAPONS.length)]));
        this.method_5673(class_1304.field_6169, new class_1799((class_1935)GotItems.WILDLING_HELMET));
        this.method_5673(class_1304.field_6174, new class_1799((class_1935)GotItems.WILDLING_CHESTPLATE));
        this.method_5673(class_1304.field_6172, new class_1799((class_1935)GotItems.WILDLING_LEGGINGS));
        this.method_5673(class_1304.field_6166, new class_1799((class_1935)GotItems.WILDLING_BOOTS));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return this.isQuestCampLeader() && this.isAwaitingQuestCampDecision();
    }

    @Override
    protected boolean isHostileToPlayers() {
        return false;
    }

    public void configureQuestCamp(String questId, boolean leader) {
        this.questCampQuestId = questId == null ? "" : questId;
        this.field_6011.method_12778(QUEST_CAMP_LEADER, (Object)leader);
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)leader);
        this.aggressionEnabled = false;
    }

    public void activateAmbushAggression(class_1309 target) {
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)false);
        this.aggressionEnabled = true;
        if (target != null && this.isValidWildlingRaiderTarget(target)) {
            this.method_5980(target);
        }
    }

    public boolean isQuestCampLeader() {
        return (Boolean)this.field_6011.method_12789(QUEST_CAMP_LEADER);
    }

    public boolean isAwaitingQuestCampDecision() {
        return (Boolean)this.field_6011.method_12789(AWAITING_QUEST_CAMP_DECISION);
    }

    public String getQuestCampQuestId() {
        return this.questCampQuestId;
    }

    public void setAwaitingQuestCampDecision(boolean awaiting) {
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)awaiting);
    }

    private void acquireNearbyWildlingRaiderTarget() {
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 world = (class_3218)class_19372;
        class_238 searchBox = this.method_5829().method_1009(18.0, 8.0, 18.0);
        class_1309 nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        for (class_1297 entity2 : world.method_8333((class_1297)this, searchBox, entity -> {
            class_1309 living;
            return entity instanceof class_1309 && (living = (class_1309)entity).method_5805();
        })) {
            double distance;
            class_1309 candidate = (class_1309)entity2;
            if (!this.isValidWildlingRaiderTarget(candidate) || !this.method_6057((class_1297)candidate) || !((distance = this.method_5858((class_1297)candidate)) < nearestDistance)) continue;
            nearest = candidate;
            nearestDistance = distance;
        }
        if (nearest != null) {
            this.method_5980(nearest);
        }
    }

    private boolean isValidWildlingRaiderTarget(class_1309 target) {
        if (target == null || target == this) {
            return false;
        }
        if (target instanceof class_3222) {
            class_3222 player = (class_3222)target;
            if (!GotCastles.playerRuledKingdomId(player.method_51469(), player).isBlank()) {
                return true;
            }
            return !WILDLING_KINGDOM_ID.equals(((PlayerCharacterHolder)player).got$getCharacterProfile().getSelectedKingdomId());
        }
        if (target instanceof AbstractKingdomNpcEntity) {
            AbstractKingdomNpcEntity npc = (AbstractKingdomNpcEntity)target;
            return npc.getKingdomId() == null || !WILDLING_KINGDOM_ID.equals(npc.getKingdomId().toString());
        }
        if (target instanceof DynamicTroopEntity) {
            DynamicTroopEntity troop = (DynamicTroopEntity)target;
            return troop.getKingdomId() == null || !WILDLING_KINGDOM_ID.equals(troop.getKingdomId().toString());
        }
        if (target instanceof PartyTroopEntity) {
            PartyTroopEntity troop = (PartyTroopEntity)target;
            class_3222 owner = troop.getOwnerUuid() == null ? null : this.method_37908().method_8503().method_3760().method_14602(troop.getOwnerUuid());
            return owner == null || !WILDLING_KINGDOM_ID.equals(((PlayerCharacterHolder)owner).got$getCharacterProfile().getSelectedKingdomId());
        }
        return false;
    }
}

