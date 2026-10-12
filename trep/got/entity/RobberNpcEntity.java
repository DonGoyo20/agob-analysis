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
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
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
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3730;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5425;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.AbstractHostileWorldEventNpcEntity;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.quest.BanditCampQuestService;
import trep.got.registry.GotItems;

public final class RobberNpcEntity
extends AbstractHostileWorldEventNpcEntity {
    private static final class_2940<Boolean> AMBUSH_LEADER = class_2945.method_12791(RobberNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> AWAITING_AMBUSH_DECISION = class_2945.method_12791(RobberNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> QUEST_CAMP_LEADER = class_2945.method_12791(RobberNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> AWAITING_QUEST_CAMP_DECISION = class_2945.method_12791(RobberNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<String> TARGET_LORD_NAME = class_2945.method_12791(RobberNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final String AMBUSH_GROUP_ID_KEY = "gotmod.ambush_group_id";
    private static final String AGGRESSION_ENABLED_KEY = "gotmod.robber_aggression_enabled";
    private static final String QUEST_CAMP_QUEST_ID_KEY = "gotmod.quest_camp_quest_id";
    private static final class_2960[] TEXTURES = new class_2960[]{class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_1.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_2.png"), class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_3.png")};
    private static final class_1792[] HELMETS = new class_1792[]{class_1802.field_8267, class_1802.field_8283, GotItems.COPPER_HELMET};
    private static final class_1792[] CHESTPLATES = new class_1792[]{class_1802.field_8577, class_1802.field_8873, GotItems.COPPER_CHESTPLATE};
    private static final class_1792[] LEGGINGS = new class_1792[]{class_1802.field_8570, class_1802.field_8218, GotItems.COPPER_LEGGINGS};
    private static final class_1792[] BOOTS = new class_1792[]{class_1802.field_8370, class_1802.field_8313, GotItems.COPPER_BOOTS};
    private static final class_1792[] SHIELDS = new class_1792[]{class_1802.field_8255, GotItems.ROUND_SHIELD, GotItems.SQUARE_SHIELD, GotItems.KITE_SHIELD};
    private String ambushGroupId = "";
    private String questCampQuestId = "";
    private boolean aggressionEnabled;

    public RobberNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createRobberAttributes() {
        return AbstractWorldEventNpcEntity.createWorldEventNpcAttributes().method_26868(class_5134.field_23719, 0.23);
    }

    @Override
    protected int getBowAttackBaseIntervalTicks() {
        return 60;
    }

    protected void method_5958() {
        class_3222 closest;
        super.method_5958();
        if (!this.method_37908().method_8608() && this.isQuestCampLeader() && this.isAwaitingQuestCampDecision() && this.field_6012 % 10 == 0 && (closest = (class_3222)this.method_37908().method_18460((class_1297)this, 20.0)) != null) {
            BanditCampQuestService.checkPlayerProximity(this, closest);
        }
    }

    @Override
    public void method_6078(class_1282 source) {
        super.method_6078(source);
        if (!this.method_37908().method_8608() && !this.questCampQuestId.isBlank()) {
            BanditCampQuestService.onRobberKilled(this);
        }
    }

    public boolean method_5643(class_1282 source, float amount) {
        boolean damaged = super.method_5643(source, amount);
        if (damaged && !this.method_37908().method_8608() && !this.questCampQuestId.isBlank()) {
            BanditCampQuestService.onQuestCampRobberDamaged(this, source);
        }
        return damaged;
    }

    @Nullable
    public class_1315 method_5943(class_5425 world, class_1266 difficulty, class_3730 spawnReason, @Nullable class_1315 entityData) {
        class_1315 initializedData = super.method_5943(world, difficulty, spawnReason, entityData);
        if (spawnReason == class_3730.field_16459 || spawnReason == class_3730.field_16472) {
            this.aggressionEnabled = true;
            this.field_6011.method_12778(AMBUSH_LEADER, (Object)false);
            this.field_6011.method_12778(AWAITING_AMBUSH_DECISION, (Object)false);
            this.field_6011.method_12778(QUEST_CAMP_LEADER, (Object)false);
            this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)false);
            this.field_6011.method_12778(TARGET_LORD_NAME, (Object)"");
            this.ambushGroupId = "";
            this.questCampQuestId = "";
        }
        return initializedData;
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(AMBUSH_LEADER, (Object)false);
        builder.method_56912(AWAITING_AMBUSH_DECISION, (Object)false);
        builder.method_56912(QUEST_CAMP_LEADER, (Object)false);
        builder.method_56912(AWAITING_QUEST_CAMP_DECISION, (Object)false);
        builder.method_56912(TARGET_LORD_NAME, (Object)"");
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(AMBUSH_GROUP_ID_KEY, this.ambushGroupId);
        nbt.method_10556(AGGRESSION_ENABLED_KEY, this.aggressionEnabled);
        nbt.method_10582(QUEST_CAMP_QUEST_ID_KEY, this.questCampQuestId);
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.ambushGroupId = nbt.method_10558(AMBUSH_GROUP_ID_KEY);
        this.aggressionEnabled = nbt.method_10577(AGGRESSION_ENABLED_KEY);
        this.questCampQuestId = nbt.method_10558(QUEST_CAMP_QUEST_ID_KEY);
    }

    @Override
    public class_2960 getTextureId() {
        return TEXTURES[Math.floorMod(this.getSkinVariant(), TEXTURES.length)];
    }

    @Override
    protected int variantCount() {
        return TEXTURES.length;
    }

    @Override
    protected void applyWorldEventLoadout() {
        this.method_5673(class_1304.field_6169, this.randomArmorPiece(HELMETS));
        this.method_5673(class_1304.field_6174, this.randomArmorPiece(CHESTPLATES));
        this.method_5673(class_1304.field_6172, this.randomArmorPiece(LEGGINGS));
        this.method_5673(class_1304.field_6166, this.randomArmorPiece(BOOTS));
        if (this.method_59922().method_43056()) {
            this.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8371));
            this.method_5673(class_1304.field_6171, new class_1799((class_1935)SHIELDS[this.method_59922().method_43048(SHIELDS.length)]));
            return;
        }
        this.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8102));
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return this.isAmbushLeader() && this.isAwaitingAmbushDecision() || this.isQuestCampLeader() && this.isAwaitingQuestCampDecision();
    }

    @Override
    protected boolean isHostileToPlayers() {
        return this.aggressionEnabled;
    }

    public void configureAmbushLeader(String ambushGroupId, String targetLordName) {
        this.ambushGroupId = ambushGroupId == null ? "" : ambushGroupId;
        this.field_6011.method_12778(AMBUSH_LEADER, (Object)true);
        this.field_6011.method_12778(AWAITING_AMBUSH_DECISION, (Object)true);
        this.field_6011.method_12778(TARGET_LORD_NAME, (Object)(targetLordName == null ? "" : targetLordName));
        this.aggressionEnabled = false;
    }

    public void configureAmbushMember(String ambushGroupId) {
        this.ambushGroupId = ambushGroupId == null ? "" : ambushGroupId;
        this.field_6011.method_12778(AMBUSH_LEADER, (Object)false);
        this.field_6011.method_12778(AWAITING_AMBUSH_DECISION, (Object)false);
        this.field_6011.method_12778(TARGET_LORD_NAME, (Object)"");
        this.aggressionEnabled = false;
    }

    public void configureQuestCamp(String questId, boolean leader) {
        this.questCampQuestId = questId == null ? "" : questId;
        this.field_6011.method_12778(QUEST_CAMP_LEADER, (Object)leader);
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)leader);
        this.aggressionEnabled = false;
    }

    public void activateAmbushAggression(class_1309 target) {
        this.field_6011.method_12778(AWAITING_AMBUSH_DECISION, (Object)false);
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)false);
        this.aggressionEnabled = true;
        if (target != null) {
            this.method_5980(target);
        }
    }

    public boolean isAmbushLeader() {
        return (Boolean)this.field_6011.method_12789(AMBUSH_LEADER);
    }

    public boolean isAwaitingAmbushDecision() {
        return (Boolean)this.field_6011.method_12789(AWAITING_AMBUSH_DECISION);
    }

    public boolean isQuestCampLeader() {
        return (Boolean)this.field_6011.method_12789(QUEST_CAMP_LEADER);
    }

    public boolean isAwaitingQuestCampDecision() {
        return (Boolean)this.field_6011.method_12789(AWAITING_QUEST_CAMP_DECISION);
    }

    public String getTargetLordName() {
        return (String)this.field_6011.method_12789(TARGET_LORD_NAME);
    }

    public String getAmbushGroupId() {
        return this.ambushGroupId;
    }

    public String getQuestCampQuestId() {
        return this.questCampQuestId;
    }

    public void setAwaitingQuestCampDecision(boolean v) {
        this.field_6011.method_12778(AWAITING_QUEST_CAMP_DECISION, (Object)v);
    }

    private class_1799 randomArmorPiece(class_1792[] items) {
        return new class_1799((class_1935)items[this.method_59922().method_43048(items.length)]);
    }
}

