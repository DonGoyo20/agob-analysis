/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1314
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1324
 *  net.minecraft.class_1352
 *  net.minecraft.class_1361
 *  net.minecraft.class_1376
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 */
package trep.got.entity;

import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1314;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1352;
import net.minecraft.class_1361;
import net.minecraft.class_1376;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import trep.got.castle.CastleHolder;
import trep.got.character.NpcAttributeProfile;
import trep.got.character.PlayerTraitEffects;
import trep.got.combat.BetterCombatAnimationResolver;
import trep.got.entity.KingdomNpcPoiWanderGoal;
import trep.got.entity.NpcHumanoidAnimation;
import trep.got.skill.SkillAttributeType;

public abstract class AbstractKingdomHumanoidEntity
extends class_1314
implements CastleHolder {
    private static final double BASE_ATTACK_SPEED = 4.0;
    private static final class_2940<String> ATTACK_ANIMATION_ID = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Integer> ATTACK_ANIMATION_SEQUENCE = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<String> CASTLE_ID = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Integer> HUMANOID_ANIMATION_ID = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> HUMANOID_ANIMATION_SEQUENCE = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Long> HUMANOID_ANIMATION_START_TICK = class_2945.method_12791(AbstractKingdomHumanoidEntity.class, (class_2941)class_2943.field_39965);
    private static final class_2960 NPC_GENETIC_SCALE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"npc_genetic_trait_scale");
    private static final class_2960 NPC_HEALTH_SKILL_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"npc_health_skill_bonus");
    private static final String POI_POS_KEY = "gotmod.poi_pos";
    private static final String POI_RADIUS_KEY = "gotmod.poi_radius";
    private static final String NPC_ATTRIBUTES_KEY = "gotmod.npc_attributes";
    private static final String CASTLE_KEY = "castle";
    private static final int DEFAULT_POI_RADIUS = 32;
    private static final int VILLAGE_POI_RADIUS = 48;
    private class_2338 pointOfInterest;
    private int pointOfInterestRadius;
    private String castle = "";
    private NpcAttributeProfile npcAttributes = NpcAttributeProfile.EMPTY;
    private float appliedNpcScaleMultiplier = 1.0f;
    private int appliedNpcHealthBonus;
    private int attackAnimationCycle;
    private long nextAttackAllowedTick;

    protected AbstractKingdomHumanoidEntity(class_1299<? extends class_1314> entityType, class_1937 world) {
        super(entityType, world);
        this.method_5880(false);
        this.method_5971();
    }

    public static class_5132.class_5133 createKingdomHumanoidAttributes() {
        return class_1308.method_26828().method_26868(class_5134.field_23716, 20.0).method_26868(class_5134.field_23719, 0.23).method_26868(class_5134.field_23721, 3.0).method_26868(class_5134.field_23723, 4.0).method_26868(class_5134.field_23717, 16.0);
    }

    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(ATTACK_ANIMATION_ID, (Object)"");
        builder.method_56912(ATTACK_ANIMATION_SEQUENCE, (Object)0);
        builder.method_56912(CASTLE_ID, (Object)"");
        builder.method_56912(HUMANOID_ANIMATION_ID, (Object)0);
        builder.method_56912(HUMANOID_ANIMATION_SEQUENCE, (Object)0);
        builder.method_56912(HUMANOID_ANIMATION_START_TICK, (Object)0L);
    }

    protected void method_5959() {
        this.field_6201.method_6277(7, this.createPoiWanderGoal());
        this.field_6201.method_6277(8, (class_1352)new class_1361((class_1308)this, class_1657.class, 8.0f));
        this.field_6201.method_6277(8, (class_1352)new class_1376((class_1308)this));
    }

    protected class_1352 createPoiWanderGoal() {
        return new KingdomNpcPoiWanderGoal(this, 1.0, 40);
    }

    public void method_5773() {
        class_1937 class_19372;
        super.method_5773();
        if (this.method_37908().method_8608()) {
            return;
        }
        if (this.pointOfInterest == null || this.field_6012 % 20 != 0 || !((class_19372 = this.method_37908()) instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        int maxDistance = this.getPointOfInterestRadius() + 8;
        if (this.method_24515().method_10262((class_2382)this.pointOfInterest) > (double)(maxDistance * maxDistance)) {
            KingdomNpcPoiWanderGoal.startMovingToPoiArea(this, serverWorld, this.pointOfInterest, this.getPointOfInterestRadius(), 1.1);
        }
    }

    public boolean method_6121(class_1297 target) {
        long currentTick = this.method_37908().method_8510();
        if (currentTick < this.nextAttackAllowedTick) {
            return false;
        }
        boolean attacked = super.method_6121(target);
        if (attacked) {
            this.method_23667(class_1268.field_5808, true);
            this.startSyncedAttackAnimation();
            this.nextAttackAllowedTick = currentTick + (long)this.attackCooldownTicks();
        }
        return attacked;
    }

    public String agob$getAttackAnimationId() {
        return (String)this.field_6011.method_12789(ATTACK_ANIMATION_ID);
    }

    public int agob$getAttackAnimationSequence() {
        return (Integer)this.field_6011.method_12789(ATTACK_ANIMATION_SEQUENCE);
    }

    public void agob$playHumanoidAnimation(int animationId) {
        if (this.method_37908().method_8608() || NpcHumanoidAnimation.durationTicks(animationId) <= 0) {
            return;
        }
        this.field_6011.method_12778(HUMANOID_ANIMATION_ID, (Object)animationId);
        this.field_6011.method_12778(HUMANOID_ANIMATION_SEQUENCE, (Object)((Integer)this.field_6011.method_12789(HUMANOID_ANIMATION_SEQUENCE) + 1));
        this.field_6011.method_12778(HUMANOID_ANIMATION_START_TICK, (Object)this.method_37908().method_8510());
    }

    public void agob$playRandomTalkAnimation() {
        this.agob$playHumanoidAnimation(this.method_59922().method_43056() ? 1 : 2);
    }

    public int agob$getHumanoidAnimationId() {
        int animationId = (Integer)this.field_6011.method_12789(HUMANOID_ANIMATION_ID);
        int duration = NpcHumanoidAnimation.durationTicks(animationId);
        if (duration <= 0 || this.method_37908() == null || this.method_37908().method_8510() - (Long)this.field_6011.method_12789(HUMANOID_ANIMATION_START_TICK) > (long)duration) {
            return 0;
        }
        return animationId;
    }

    public int agob$getHumanoidAnimationSequence() {
        return (Integer)this.field_6011.method_12789(HUMANOID_ANIMATION_SEQUENCE);
    }

    public long agob$getHumanoidAnimationStartTick() {
        return (Long)this.field_6011.method_12789(HUMANOID_ANIMATION_START_TICK);
    }

    private void startSyncedAttackAnimation() {
        class_2960 animationId;
        if ((animationId = BetterCombatAnimationResolver.resolve((class_1309)this, this.attackAnimationCycle++)) == null) {
            return;
        }
        this.field_6011.method_12778(ATTACK_ANIMATION_ID, (Object)animationId.toString());
        this.field_6011.method_12778(ATTACK_ANIMATION_SEQUENCE, (Object)((Integer)this.field_6011.method_12789(ATTACK_ANIMATION_SEQUENCE) + 1));
    }

    private int attackCooldownTicks() {
        double attackSpeed = this.method_45325(class_5134.field_23723);
        if (!Double.isFinite(attackSpeed) || attackSpeed <= 0.0) {
            attackSpeed = 4.0;
        }
        return Math.max(1, (int)Math.ceil(20.0 / attackSpeed));
    }

    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        if (this.pointOfInterest != null) {
            nbt.method_10544(POI_POS_KEY, this.pointOfInterest.method_10063());
        }
        if (this.pointOfInterestRadius > 0) {
            nbt.method_10569(POI_RADIUS_KEY, this.pointOfInterestRadius);
        }
        nbt.method_10582(CASTLE_KEY, this.castle);
        nbt.method_10566(NPC_ATTRIBUTES_KEY, (class_2520)this.npcAttributes.toNbt());
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(POI_POS_KEY)) {
            this.pointOfInterest = class_2338.method_10092((long)nbt.method_10537(POI_POS_KEY));
        }
        this.pointOfInterestRadius = nbt.method_10545(POI_RADIUS_KEY) ? Math.max(0, nbt.method_10550(POI_RADIUS_KEY)) : 0;
        this.got$setCastle(nbt.method_10558(CASTLE_KEY));
        this.setNpcAttributes(NpcAttributeProfile.fromNbt(nbt.method_10562(NPC_ATTRIBUTES_KEY)));
    }

    public void setPointOfInterest(class_2338 pointOfInterest) {
        this.pointOfInterest = pointOfInterest == null ? null : pointOfInterest.method_10062();
    }

    public class_2338 getPointOfInterest() {
        return this.pointOfInterest;
    }

    public void setPointOfInterestRadius(int pointOfInterestRadius) {
        this.pointOfInterestRadius = Math.max(0, pointOfInterestRadius);
    }

    public int getPointOfInterestRadius() {
        if (this.pointOfInterestRadius > 0) {
            return this.pointOfInterestRadius;
        }
        return this.got$getCastle().endsWith("_village") ? 48 : 32;
    }

    @Override
    public String got$getCastle() {
        return (String)this.field_6011.method_12789(CASTLE_ID);
    }

    @Override
    public void got$setCastle(String castleId) {
        this.castle = castleId == null ? "" : castleId;
        this.field_6011.method_12778(CASTLE_ID, (Object)this.castle);
    }

    public NpcAttributeProfile getNpcAttributes() {
        return this.npcAttributes;
    }

    public void setNpcAttributes(NpcAttributeProfile attributes) {
        this.npcAttributes = attributes == null ? NpcAttributeProfile.EMPTY : attributes;
        this.syncNpcHealthSkillBonus();
        this.syncNpcGeneticTraitScale();
    }

    private void syncNpcHealthSkillBonus() {
        class_1324 maxHealth = this.method_5996(class_5134.field_23716);
        if (maxHealth == null) {
            return;
        }
        int healthPoints = this.npcAttributes.skillPoints().getOrDefault((Object)SkillAttributeType.HEALTH, 0);
        int targetBonus = healthPoints / 10 * 2;
        if (targetBonus == this.appliedNpcHealthBonus) {
            return;
        }
        maxHealth.method_6200(NPC_HEALTH_SKILL_MODIFIER_ID);
        if (targetBonus > 0) {
            maxHealth.method_26837(new class_1322(NPC_HEALTH_SKILL_MODIFIER_ID, (double)targetBonus, class_1322.class_1323.field_6328));
        }
        this.appliedNpcHealthBonus = targetBonus;
        if (this.method_6032() > this.method_6063()) {
            this.method_6033(this.method_6063());
        }
    }

    private void syncNpcGeneticTraitScale() {
        class_1324 scaleAttribute = this.method_5996(class_5134.field_47760);
        if (scaleAttribute == null) {
            return;
        }
        float targetScale = PlayerTraitEffects.resolveScaleMultiplier(this.npcAttributes.geneticTrait());
        if (Math.abs(targetScale - this.appliedNpcScaleMultiplier) < 1.0E-4f) {
            return;
        }
        scaleAttribute.method_6200(NPC_GENETIC_SCALE_MODIFIER_ID);
        if (Math.abs(targetScale - 1.0f) > 1.0E-4f) {
            scaleAttribute.method_26837(new class_1322(NPC_GENETIC_SCALE_MODIFIER_ID, (double)(targetScale - 1.0f), class_1322.class_1323.field_6331));
        }
        this.appliedNpcScaleMultiplier = targetScale;
        this.method_18382();
    }

    public void method_5674(class_2940<?> data) {
        super.method_5674(data);
        if (CASTLE_ID.equals(data)) {
            this.castle = (String)this.field_6011.method_12789(CASTLE_ID);
        }
    }

    public abstract class_2960 getKingdomId();
}

