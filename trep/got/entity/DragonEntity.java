/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
 *  net.minecraft.class_1266
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_4738
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1313
 *  net.minecraft.class_1315
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1324
 *  net.minecraft.class_1429
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2394
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3489
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_3908
 *  net.minecraft.class_4048
 *  net.minecraft.class_4174
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_7248
 *  net.minecraft.class_9334
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package trep.got.entity;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.class_1266;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1313;
import net.minecraft.class_1315;
import net.minecraft.class_1322;
import net.minecraft.class_1324;
import net.minecraft.class_1429;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_3908;
import net.minecraft.class_4048;
import net.minecraft.class_4174;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_7248;
import net.minecraft.class_9334;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import trep.got.GotMod;
import trep.got.entity.DragonAge;
import trep.got.entity.DragonFireEntity;
import trep.got.entity.DragonModelData;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonAnimationResolver;
import trep.got.entity.dragon.DragonBondWorldState;
import trep.got.entity.dragon.DragonEggColor;
import trep.got.entity.dragon.DragonModelPartNbtHelper;
import trep.got.entity.dragon.DragonModelParts;
import trep.got.entity.dragon.DragonOrigin;
import trep.got.item.DragonEggItem;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotGameRules;
import trep.got.registry.GotParticles;
import trep.got.screen.DragonStatsScreenHandler;
import trep.got.screen.DragonStatsScreenOpeningData;

public final class DragonEntity
extends class_1429
implements class_7248 {
    private static final class_2940<Boolean> FLIGHT_ACTIVE = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Boolean> DIVING = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Float> FLIGHT_ROLL = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13320);
    private static final class_2940<Boolean> FIRE_BREATHING = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<String> VARIANT = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> ORIGIN = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> MODEL_TYPE = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> MODEL_PARTS = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> DRAGON_AGE = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> BOUND_PLAYER_UUID = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> BOUND_NPC_UUID = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Integer> AGE_DAYS = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Integer> HUNGER = class_2945.method_12791(DragonEntity.class, (class_2941)class_2943.field_13327);
    private static final String FLIGHT_ACTIVE_KEY = "agob.flight_active";
    private static final String DIVING_KEY = "agob.diving";
    private static final String FLIGHT_ROLL_KEY = "agob.flight_roll";
    private static final String VARIANT_KEY = "agob.dragon_variant";
    private static final String VARIANT_ALIAS_KEY = "Variant";
    private static final String ORIGIN_KEY = "agob.dragon_origin";
    private static final String ORIGIN_ALIAS_KEY = "Origin";
    private static final String MODEL_TYPE_KEY = "agob.dragon_model_type";
    private static final String MODEL_TYPE_ALIAS_KEY = "ModelType";
    private static final String DRAGON_AGE_KEY = "agob.dragon_age";
    private static final String DRAGON_AGE_ALIAS_KEY = "DragonAge";
    private static final String AGE_STRING_ALIAS_KEY = "Age";
    private static final String BOUND_PLAYER_UUID_KEY = "agob.bound_player_uuid";
    private static final String BOUND_PLAYER_UUID_ALIAS_KEY = "BoundPlayer";
    private static final String BOUND_NPC_UUID_KEY = "agob.bound_npc_uuid";
    private static final String BOUND_NPC_UUID_ALIAS_KEY = "BoundNpc";
    private static final String AGE_DAYS_KEY = "agob.age_days";
    private static final String AGE_DAYS_ALIAS_KEY = "AgeDays";
    private static final String HUNGER_KEY = "agob.hunger";
    private static final String HUNGER_ALIAS_KEY = "Hunger";
    private static final String LAST_GROWTH_OBSERVED_TIME_KEY = "agob.last_growth_observed_time";
    private static final String GROWTH_TIME_INITIALIZED_KEY = "agob.growth_time_initialized";
    private static final class_2960 DRAGON_AGE_SCALE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"dragon_age_scale");
    private static final class_2960 DRAGON_AGE_HEALTH_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"dragon_age_health");
    private static final String DEFAULT_VARIANT = "red";
    private static final List<String> SPAWN_VARIANTS = List.of("black", "blue", "gray", "green", "light_red", "orange", "red", "white", "yellow");
    private static final int BABY_GROW_DAYS = 30;
    private static final int ADOLESCENT_GROW_DAYS = 60;
    private static final int BABY_HUNGER_MAX = 20;
    private static final int ADOLESCENT_HUNGER_MAX = 40;
    private static final int ADULT_HUNGER_MAX = 75;
    private static final float BABY_DAILY_SCALE_GAIN = 0.01f;
    private static final float ADOLESCENT_DAILY_SCALE_GAIN = 0.04f;
    private static final float DIVE_TRIGGER_PITCH = 25.0f;
    private static final double WALK_SPEED = 0.34;
    private static final double WALK_STRAFE = 0.6;
    private static final double FLY_SPEED = 1.35;
    private static final double MAX_FLY_SPEED = 1.7;
    private static final double MAX_DIVE_SPEED = 2.65;
    private static final double TAKEOFF_BOOST = 0.52;
    private static final double RIDER_YAW_INFLUENCE = 1.0;
    private static final double MIN_FORWARD_INPUT = 0.02;
    private static final double FLIGHT_THRUST = 0.095;
    private static final double DIVE_THRUST = 0.145;
    private static final double FLIGHT_GLIDE_GRAVITY = 0.016;
    private static final double DIVE_GRAVITY_ACCELERATION = 0.055;
    private static final double FLIGHT_POWERED_STEERING = 0.24;
    private static final double FLIGHT_COAST_STEERING = 0.09;
    private static final double DIVE_POWERED_STEERING = 0.32;
    private static final double DIVE_COAST_STEERING = 0.14;
    private static final double FLIGHT_MIN_GLIDE_SPEED = 0.65;
    private static final double FLIGHT_MOMENTUM_DRAG = 0.997;
    private static final double DIVE_MOMENTUM_DRAG = 0.999;
    private static final double TURN_ALIGNMENT = 0.1;
    private static final float FLIGHT_YAW_LERP = 0.18f;
    private static final float FLIGHT_PITCH_LERP = 0.16f;
    private static final float FLIGHT_INPUT_YAW_OFFSET = 10.0f;
    private static final float MAX_FLIGHT_ROLL = 55.0f;
    private static final float ROLL_BLEND_SPEED = 0.18f;
    private static final float ROLL_RETURN_SPEED = 0.9f;
    private static final float GROUND_YAW_LERP = 0.18f;
    private static final float GROUND_YAW_LERP_MOVING = 0.28f;
    private static final float GROUND_PITCH_RETURN = 0.2f;
    private static final class_243 RIDER_SEAT_OFFSET = class_243.field_1353;
    private static final boolean DEBUG_MOUNT_PIVOT = Boolean.getBoolean("agob.debugDragonMountPivot");
    private static final int MOUNT_PIVOT_DEBUG_INTERVAL_TICKS = 100;
    private static final int MOUNT_PIVOT_PROBE_INTERVAL_TICKS = 20;
    private static final int FIRE_BREATH_WARMUP_TICKS = 10;
    private static final int FIRE_BREATH_INTERVAL_TICKS = 2;
    private static final double FIRE_BREATH_PROJECTILE_SPEED = 3.15;
    private static final double FIRE_BREATH_INHERITED_FORWARD_SPEED = 0.65;
    private static final double BOUND_NPC_FOLLOW_DISTANCE = 4.0;
    private static final double BOUND_NPC_TELEPORT_DISTANCE = 48.0;
    private float clientWalkWeight;
    private float clientFlyWeight;
    private float clientDiveWeight;
    private float clientFireBreathWeight;
    private float clientFireBreathWeightPrev;
    private float clientWalkAnimationTime;
    private float clientFlyAnimationTime;
    private float clientDiveAnimationTime;
    private float clientIdleAnimationTime;
    private float clientFlightRoll;
    private float clientFlightRollPrev;
    private boolean clientLocalFireBreathHeld;
    private int clientLocalFireBreathInputTicks;
    private boolean serverClimbHeld;
    private boolean serverFireBreathHeld;
    private float serverForwardInput;
    private float serverSidewaysInput;
    private int serverFireBreathTicks;
    private long lastGrowthObservedTime;
    private boolean growthTimeInitialized;
    private class_243 mountedFlightPivot;
    private int nextMountPivotDebugAge;
    private int nextMountPivotProbeAge;
    private boolean loggedMountedFlightStart;

    public DragonEntity(class_1299<? extends DragonEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createDragonAttributes() {
        return class_1429.method_26828().method_26868(class_5134.field_23716, 60.0).method_26868(class_5134.field_23719, 0.34).method_26868(class_5134.field_23720, 1.35).method_26868(class_5134.field_47760, 1.0).method_26868(class_5134.field_47761, 1.25);
    }

    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(FLIGHT_ACTIVE, (Object)false);
        builder.method_56912(DIVING, (Object)false);
        builder.method_56912(FLIGHT_ROLL, (Object)Float.valueOf(0.0f));
        builder.method_56912(FIRE_BREATHING, (Object)false);
        builder.method_56912(VARIANT, (Object)DEFAULT_VARIANT);
        builder.method_56912(ORIGIN, (Object)DragonOrigin.DEFAULT.id());
        builder.method_56912(MODEL_TYPE, (Object)DragonModelType.DEFAULT.id());
        builder.method_56912(MODEL_PARTS, (Object)DragonModelParts.DEFAULT.encode());
        builder.method_56912(DRAGON_AGE, (Object)DragonAge.DEFAULT.id());
        builder.method_56912(BOUND_PLAYER_UUID, (Object)"");
        builder.method_56912(BOUND_NPC_UUID, (Object)"");
        builder.method_56912(AGE_DAYS, (Object)0);
        builder.method_56912(HUNGER, (Object)0);
    }

    public void method_5773() {
        super.method_5773();
        if (!this.method_37908().field_9236) {
            this.tickGrowthAge();
            this.tickBoundNpcFollow();
            this.tickServerFlightState();
        }
        this.tickAnimationState();
        this.method_5875(this.isFlightActive());
        if (this.method_24828() && this.isFlightActive()) {
            this.exitFlightMode();
        }
    }

    private void exitFlightMode() {
        this.setFlightActive(false);
        this.setDiving(false);
        this.serverClimbHeld = false;
        this.serverForwardInput = 0.0f;
        this.serverSidewaysInput = 0.0f;
        this.mountedFlightPivot = null;
        this.loggedMountedFlightStart = false;
        this.setFlightRoll(0.0f);
    }

    private void tickServerFlightState() {
        class_1309 rider = this.method_5642();
        if (!(rider instanceof class_1657)) {
            this.serverClimbHeld = false;
            this.serverFireBreathHeld = false;
            this.serverForwardInput = 0.0f;
            this.serverSidewaysInput = 0.0f;
            this.serverFireBreathTicks = 0;
            this.setFireBreathing(false);
            this.mountedFlightPivot = null;
            this.loggedMountedFlightStart = false;
            if (this.isFlightActive()) {
                this.setDiving(false);
            }
            this.setFlightRoll(0.0f);
            return;
        }
        class_1657 player = (class_1657)rider;
        this.tickMountedFireBreath(player);
        if (player.method_31549().field_7479) {
            player.method_31549().field_7479 = false;
            if (player instanceof class_3222) {
                class_3222 serverPlayer = (class_3222)player;
                serverPlayer.method_7355();
            }
        }
        if (!this.isFlightActive()) {
            this.mountedFlightPivot = null;
            this.loggedMountedFlightStart = false;
            this.setDiving(false);
            this.setFlightRoll(this.getFlightRoll() * 0.9f);
            return;
        }
        boolean shouldDive = !this.method_24828() && (double)this.serverForwardInput > 0.02 && player.method_36455() >= 25.0f;
        this.setDiving(shouldDive);
    }

    private void tickAnimationState() {
        double horizontalSpeed = this.method_18798().method_37267();
        boolean grounded = this.method_24828() && !this.isFlightActive();
        this.clientFlightRollPrev = this.clientFlightRoll;
        this.clientFireBreathWeightPrev = this.clientFireBreathWeight;
        float targetRoll = this.isFlightActive() ? this.getFlightRoll() : 0.0f;
        this.clientFlightRoll += (targetRoll - this.clientFlightRoll) * 0.35f;
        boolean localRiderHoldingFire = this.clientLocalFireBreathInputTicks > 0 && this.clientLocalFireBreathHeld;
        this.clientFireBreathWeight = DragonEntity.approach(this.clientFireBreathWeight, localRiderHoldingFire || this.isFireBreathing() ? 1.0f : 0.0f, 0.1f);
        if (this.clientLocalFireBreathInputTicks > 0) {
            --this.clientLocalFireBreathInputTicks;
        }
        float targetWalk = grounded ? class_3532.method_15363((float)((float)(horizontalSpeed / 0.24)), (float)0.0f, (float)1.0f) : 0.0f;
        float targetFly = this.isFlightActive() && !this.isDiving() ? 1.0f : 0.0f;
        float targetDive = this.isDiving() ? 1.0f : 0.0f;
        this.clientWalkWeight = DragonEntity.approach(this.clientWalkWeight, targetWalk, 0.08f);
        this.clientFlyWeight = DragonEntity.approach(this.clientFlyWeight, targetFly, 0.07f);
        this.clientDiveWeight = DragonEntity.approach(this.clientDiveWeight, targetDive, 0.1f);
        this.clientWalkAnimationTime += Math.max(0.05f, targetWalk) * 0.12f;
        this.clientFlyAnimationTime += targetFly > 0.0f ? 0.02f : 0.0f;
        this.clientDiveAnimationTime += targetDive > 0.0f ? 0.06f : 0.0f;
        this.clientIdleAnimationTime += 0.04f;
    }

    private static float approach(float current, float target, float step) {
        if (current < target) {
            return Math.min(target, current + step);
        }
        return Math.max(target, current - step);
    }

    public float getWalkAnimationWeight() {
        return this.clientWalkWeight;
    }

    public float getFlyAnimationWeight() {
        return this.clientFlyWeight;
    }

    public float getDiveAnimationWeight() {
        return this.clientDiveWeight;
    }

    public float getWalkAnimationTime(float tickDelta) {
        return this.clientWalkAnimationTime + tickDelta * 0.12f;
    }

    public float getFlyAnimationTime(float tickDelta) {
        return this.clientFlyAnimationTime + tickDelta * 0.02f;
    }

    public float getDiveAnimationTime(float tickDelta) {
        return this.clientDiveAnimationTime + tickDelta * 0.06f;
    }

    public float getIdleAnimationTime(float tickDelta) {
        return this.clientIdleAnimationTime + tickDelta * 0.04f;
    }

    public float getFireBreathAnimationProgress(float tickDelta) {
        if (this.method_37908().field_9236) {
            return class_3532.method_16439((float)tickDelta, (float)this.clientFireBreathWeightPrev, (float)this.clientFireBreathWeight);
        }
        return class_3532.method_15363((float)((float)this.serverFireBreathTicks / 10.0f), (float)0.0f, (float)1.0f);
    }

    public boolean isFlightActive() {
        return (Boolean)this.field_6011.method_12789(FLIGHT_ACTIVE);
    }

    public void setFlightActive(boolean flightActive) {
        this.field_6011.method_12778(FLIGHT_ACTIVE, (Object)flightActive);
    }

    public boolean isDiving() {
        return (Boolean)this.field_6011.method_12789(DIVING);
    }

    public void setDiving(boolean diving) {
        this.field_6011.method_12778(DIVING, (Object)diving);
    }

    public float getFlightRoll() {
        return ((Float)this.field_6011.method_12789(FLIGHT_ROLL)).floatValue();
    }

    public void setFlightRoll(float flightRoll) {
        this.field_6011.method_12778(FLIGHT_ROLL, (Object)Float.valueOf(flightRoll));
    }

    public boolean isFireBreathing() {
        return (Boolean)this.field_6011.method_12789(FIRE_BREATHING);
    }

    public void setFireBreathing(boolean fireBreathing) {
        this.field_6011.method_12778(FIRE_BREATHING, (Object)fireBreathing);
    }

    public String getVariant() {
        return DragonEntity.sanitizeVariant((String)this.field_6011.method_12789(VARIANT));
    }

    public void setVariant(String variant) {
        this.field_6011.method_12778(VARIANT, (Object)DragonEntity.sanitizeVariant(variant));
    }

    public DragonOrigin getOrigin() {
        return DragonOrigin.byId((String)this.field_6011.method_12789(ORIGIN));
    }

    public String getOriginId() {
        return this.getOrigin().id();
    }

    public void setOrigin(String origin) {
        this.setOrigin(DragonOrigin.byId(origin));
    }

    public void setOrigin(DragonOrigin origin) {
        DragonOrigin resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
        this.field_6011.method_12778(ORIGIN, (Object)resolvedOrigin.id());
        DragonModelType currentModelType = DragonModelType.byId((String)this.field_6011.method_12789(MODEL_TYPE));
        if (currentModelType.origin() != resolvedOrigin) {
            this.field_6011.method_12778(MODEL_TYPE, (Object)resolvedOrigin.defaultModelType().id());
        }
        DragonModelParts currentParts = DragonModelParts.decode((String)this.field_6011.method_12789(MODEL_PARTS), resolvedOrigin).normalizeForOrigin(resolvedOrigin);
        this.field_6011.method_12778(MODEL_PARTS, (Object)currentParts.encode());
    }

    public DragonModelType getModelType() {
        return this.getBaseModelType();
    }

    public DragonModelType getAnimationModelType() {
        return DragonAnimationResolver.resolveAnimationModelType(this.getBaseModelType(), this.getModelParts());
    }

    public DragonModelType getBaseModelType() {
        DragonModelType modelType = DragonModelType.byId((String)this.field_6011.method_12789(MODEL_TYPE));
        return modelType.origin() == this.getOrigin() ? modelType : this.getOrigin().defaultModelType();
    }

    public String getModelTypeId() {
        return this.getBaseModelType().id();
    }

    public void setModelType(String modelType) {
        DragonModelType resolved = DragonModelType.byId(modelType);
        if (resolved.origin() != this.getOrigin()) {
            resolved = this.getOrigin().defaultModelType();
        }
        this.field_6011.method_12778(MODEL_TYPE, (Object)resolved.id());
    }

    public DragonModelParts getModelParts() {
        return DragonModelParts.decode((String)this.field_6011.method_12789(MODEL_PARTS), this.getOrigin()).normalizeForOrigin(this.getOrigin());
    }

    public void setModelParts(DragonModelParts modelParts) {
        DragonModelParts resolved = (modelParts == null ? DragonModelParts.DEFAULT : modelParts).normalizeForOrigin(this.getOrigin());
        this.field_6011.method_12778(MODEL_PARTS, (Object)resolved.encode());
    }

    public DragonAge getDragonAge() {
        return DragonAge.byId((String)this.field_6011.method_12789(DRAGON_AGE));
    }

    public String getDragonAgeId() {
        return this.getDragonAge().id();
    }

    public void setDragonAge(String dragonAge) {
        this.setDragonAge(DragonAge.byId(dragonAge));
    }

    public void setDragonAge(DragonAge dragonAge) {
        DragonAge resolvedAge = dragonAge == null ? DragonAge.DEFAULT : dragonAge;
        this.field_6011.method_12778(DRAGON_AGE, (Object)resolvedAge.id());
        this.syncDragonAgeScale();
        this.syncDragonAgeHealth();
        if (!this.method_37908().field_9236 && !resolvedAge.rideable()) {
            this.method_5772();
            this.exitFlightMode();
        }
    }

    public float getModelScale() {
        return switch (this.getDragonAge()) {
            default -> throw new MatchException(null, null);
            case DragonAge.BABY -> DragonAge.BABY.scale() + (float)Math.min(this.getAgeDays(), 30) * 0.01f;
            case DragonAge.ADOLESCENT -> Math.min(DragonAge.ADULT.scale(), DragonAge.ADOLESCENT.scale() + (float)Math.min(this.getAgeDays(), 60) * 0.04f);
            case DragonAge.ADULT -> DragonAge.ADULT.scale();
        };
    }

    public int getAgeDays() {
        return Math.max(0, (Integer)this.field_6011.method_12789(AGE_DAYS));
    }

    public int getAgeMaxDays() {
        return switch (this.getDragonAge()) {
            default -> throw new MatchException(null, null);
            case DragonAge.BABY -> 30;
            case DragonAge.ADOLESCENT -> 60;
            case DragonAge.ADULT -> -1;
        };
    }

    public int getHunger() {
        return Math.max(0, (Integer)this.field_6011.method_12789(HUNGER));
    }

    public int getHungerMax() {
        return switch (this.getDragonAge()) {
            default -> throw new MatchException(null, null);
            case DragonAge.BABY -> 20;
            case DragonAge.ADOLESCENT -> 40;
            case DragonAge.ADULT -> 75;
        };
    }

    private void setAgeDays(int ageDays) {
        this.field_6011.method_12778(AGE_DAYS, (Object)Math.max(0, ageDays));
        this.syncDragonAgeScale();
    }

    private void setHunger(int hunger) {
        this.field_6011.method_12778(HUNGER, (Object)class_3532.method_15340((int)hunger, (int)0, (int)(this.getHungerMax() - 1)));
    }

    @Nullable
    public UUID getBoundPlayerUuid() {
        String value = (String)this.field_6011.method_12789(BOUND_PLAYER_UUID);
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(value);
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public void setBoundPlayerUuid(@Nullable UUID playerUuid) {
        this.field_6011.method_12778(BOUND_PLAYER_UUID, (Object)(playerUuid == null ? "" : playerUuid.toString()));
    }

    @Nullable
    public UUID getBoundNpcUuid() {
        String value = (String)this.field_6011.method_12789(BOUND_NPC_UUID);
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return UUID.fromString(value);
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public void setBoundNpcUuid(@Nullable UUID npcUuid) {
        this.field_6011.method_12778(BOUND_NPC_UUID, (Object)(npcUuid == null ? "" : npcUuid.toString()));
    }

    public boolean isBoundTo(class_1657 player) {
        if (this.getBoundNpcUuid() != null) {
            return false;
        }
        UUID boundPlayerUuid = this.getBoundPlayerUuid();
        return boundPlayerUuid == null || boundPlayerUuid.equals(player.method_5667());
    }

    public boolean canBeRidden() {
        return this.getDragonAge().rideable();
    }

    private void tickGrowthAge() {
        long dayIncrease;
        long observedTime;
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        boolean affectedByTimeSkip = GotGameRules.affectedByTimeSkip(serverWorld);
        long l = observedTime = affectedByTimeSkip ? serverWorld.method_8532() : serverWorld.method_8510();
        if (!this.growthTimeInitialized) {
            this.lastGrowthObservedTime = observedTime;
            this.growthTimeInitialized = true;
            return;
        }
        long currentDay = observedTime / 24000L;
        long previousDay = this.lastGrowthObservedTime / 24000L;
        long l2 = dayIncrease = currentDay > previousDay ? currentDay - previousDay : 0L;
        if (dayIncrease == 0L && affectedByTimeSkip && observedTime < this.lastGrowthObservedTime) {
            dayIncrease = 1L;
        }
        this.lastGrowthObservedTime = observedTime;
        if (dayIncrease > 0L) {
            this.addGrowthDays(dayIncrease);
        }
    }

    private void addGrowthDays(long days) {
        for (long day = 0L; day < days; ++day) {
            this.setAgeDays(this.getAgeDays() + 1);
            if (this.getDragonAge() == DragonAge.BABY && this.getAgeDays() >= 30) {
                this.setDragonAge(DragonAge.ADOLESCENT);
                this.setAgeDays(0);
                this.setHunger(Math.min(this.getHunger(), this.getHungerMax() - 1));
                continue;
            }
            if (this.getDragonAge() != DragonAge.ADOLESCENT || this.getAgeDays() < 60) continue;
            this.setDragonAge(DragonAge.ADULT);
            this.setAgeDays(0);
            this.setHunger(Math.min(this.getHunger(), this.getHungerMax() - 1));
        }
    }

    private void tickBoundNpcFollow() {
        class_1309 lord;
        class_3218 serverWorld;
        block8: {
            block7: {
                class_1937 class_19372 = this.method_37908();
                if (!(class_19372 instanceof class_3218)) break block7;
                serverWorld = (class_3218)class_19372;
                if (!this.method_5782() && !this.isFlightActive()) break block8;
            }
            return;
        }
        UUID npcUuid = this.getBoundNpcUuid();
        if (npcUuid == null) {
            return;
        }
        class_1297 entity = serverWorld.method_14190(npcUuid);
        if (!(entity instanceof class_1309) || !(lord = (class_1309)entity).method_5805()) {
            this.method_5942().method_6340();
            return;
        }
        double distanceSq = this.method_5858((class_1297)lord);
        if (distanceSq > 2304.0) {
            this.method_5808(lord.method_23317() + 1.5, lord.method_23318(), lord.method_23321() + 1.5, lord.method_36454(), 0.0f);
            this.method_5942().method_6340();
            return;
        }
        if (distanceSq > 16.0 && this.field_6012 % 20 == 0) {
            this.method_5942().method_6335((class_1297)lord, 1.15);
        }
    }

    private boolean feedDragon(class_1657 player, class_1799 stack) {
        if (!DragonEntity.isDragonFood(stack)) {
            return false;
        }
        class_4174 food = (class_4174)stack.method_57824(class_9334.field_50075);
        if (food == null || food.comp_2491() <= 0) {
            return false;
        }
        if (!this.canAccessBoundDragon(player, true)) {
            return true;
        }
        if (!this.method_37908().field_9236) {
            int totalHunger;
            for (totalHunger = this.getHunger() + food.comp_2491(); totalHunger >= this.getHungerMax(); totalHunger -= this.getHungerMax()) {
                this.addGrowthDays(1L);
            }
            this.setHunger(totalHunger);
            this.method_6025(food.comp_2491());
            if (!player.method_7337()) {
                stack.method_7934(1);
            }
        }
        return true;
    }

    private static boolean isDragonFood(class_1799 stack) {
        return stack.method_31573(class_3489.field_49932) && stack.method_57824(class_9334.field_50075) != null;
    }

    private boolean canAccessBoundDragon(class_1657 player, boolean notify) {
        class_3222 serverPlayer;
        if (this.isBoundTo(player)) {
            return true;
        }
        if (player instanceof class_3222) {
            serverPlayer = (class_3222)player;
            if (notify) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"Not your Dragon"), true);
            }
        }
        return player instanceof class_3222 && (serverPlayer = (class_3222)player).method_5687(2);
    }

    private void syncDragonAgeScale() {
        class_1324 scaleAttribute = this.method_5996(class_5134.field_47760);
        if (scaleAttribute == null) {
            return;
        }
        float targetScale = this.getModelScale();
        scaleAttribute.method_6200(DRAGON_AGE_SCALE_MODIFIER_ID);
        if (Math.abs(targetScale - 1.0f) > 1.0E-4f) {
            scaleAttribute.method_26837(new class_1322(DRAGON_AGE_SCALE_MODIFIER_ID, (double)(targetScale - 1.0f), class_1322.class_1323.field_6331));
        }
        this.method_18382();
    }

    private void syncDragonAgeHealth() {
        class_1324 maxHealthAttribute = this.method_5996(class_5134.field_23716);
        if (maxHealthAttribute == null) {
            return;
        }
        double targetMaxHealth = switch (this.getDragonAge()) {
            default -> throw new MatchException(null, null);
            case DragonAge.BABY -> 20.0;
            case DragonAge.ADOLESCENT -> 40.0;
            case DragonAge.ADULT -> 60.0;
        };
        maxHealthAttribute.method_6200(DRAGON_AGE_HEALTH_MODIFIER_ID);
        double delta = targetMaxHealth - maxHealthAttribute.method_6201();
        if (Math.abs(delta) > 1.0E-4) {
            maxHealthAttribute.method_26837(new class_1322(DRAGON_AGE_HEALTH_MODIFIER_ID, delta, class_1322.class_1323.field_6328));
        }
        if (!this.method_37908().field_9236 && this.field_6012 <= 1) {
            this.method_6033(this.method_6063());
        } else if (this.method_6032() > this.method_6063()) {
            this.method_6033(this.method_6063());
        }
    }

    public void method_6078(class_1282 damageSource) {
        class_1937 class_19372;
        if (!this.method_37908().field_9236 && (class_19372 = this.method_37908()) instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            UUID boundPlayerUuid = this.getBoundPlayerUuid();
            if (boundPlayerUuid != null) {
                DragonBondWorldState.get(serverWorld).unbind(boundPlayerUuid, this.method_5667());
                this.setBoundPlayerUuid(null);
                this.method_5775(DragonEggItem.createStack(GotBlocks.DRAGON_EGG, DragonEggColor.random(this.field_5974)));
            }
        }
        super.method_6078(damageSource);
    }

    private static String sanitizeVariant(@Nullable String variant) {
        if (variant == null || variant.isBlank()) {
            return DEFAULT_VARIANT;
        }
        String normalized = variant.trim().toLowerCase(Locale.ROOT);
        return normalized.startsWith("dragons_") ? normalized.substring("dragons_".length()) : normalized;
    }

    private String chooseRandomVariant() {
        return DragonEntity.randomSpawnVariant(this.field_5974);
    }

    public static String randomSpawnVariant(class_5819 random) {
        return SPAWN_VARIANTS.get(random.method_43048(SPAWN_VARIANTS.size()));
    }

    private DragonModelType chooseRandomModelType() {
        List<DragonModelType> spawnTypes = DragonModelType.spawnTypes(this.getOrigin());
        return spawnTypes.get(this.field_5974.method_43048(spawnTypes.size()));
    }

    @Nullable
    public class_1315 method_5943(class_5425 world, class_1266 difficulty, class_3730 spawnReason, @Nullable class_1315 entityData) {
        class_1315 initializedData = super.method_5943(world, difficulty, spawnReason, entityData);
        this.setOrigin(DragonOrigin.random(this.field_5974));
        if (this.getVariant().equals(DEFAULT_VARIANT)) {
            this.setVariant(this.chooseRandomVariant());
        }
        if (this.getBaseModelType() == DragonModelType.DEFAULT && this.getModelParts().isDefault()) {
            this.setModelType(this.chooseRandomModelType().id());
        } else {
            this.setModelType(this.getBaseModelType().id());
        }
        this.setModelParts(this.getModelParts());
        return initializedData;
    }

    public float getVisualFlightRoll(float tickDelta) {
        if (this.method_37908().field_9236) {
            return class_3532.method_16439((float)tickDelta, (float)this.clientFlightRollPrev, (float)this.clientFlightRoll);
        }
        return this.getFlightRoll();
    }

    public void setClientLocalFireBreathHeld(boolean held) {
        if (this.method_37908().field_9236) {
            this.clientLocalFireBreathHeld = held;
            this.clientLocalFireBreathInputTicks = 2;
        }
    }

    public void setClientMountedFlightInput(boolean climbHeld, float forwardInput, float sidewaysInput) {
        if (this.method_37908().field_9236) {
            this.serverClimbHeld = climbHeld;
            this.serverForwardInput = class_3532.method_15363((float)forwardInput, (float)-1.0f, (float)1.0f);
            this.serverSidewaysInput = class_3532.method_15363((float)sidewaysInput, (float)-1.0f, (float)1.0f);
        }
    }

    public void requestMountedFlightInput(class_1657 player, boolean toggleFlight, boolean climbHeld) {
        this.requestMountedFlightInput(player, toggleFlight, climbHeld, this.serverFireBreathHeld, this.serverForwardInput, this.serverSidewaysInput);
    }

    public void requestMountedFlightInput(class_1657 player, boolean toggleFlight, boolean climbHeld, boolean fireBreathHeld) {
        this.requestMountedFlightInput(player, toggleFlight, climbHeld, fireBreathHeld, this.serverForwardInput, this.serverSidewaysInput);
    }

    public void requestMountedFlightInput(class_1657 player, boolean toggleFlight, boolean climbHeld, boolean fireBreathHeld, float forwardInput, float sidewaysInput) {
        if (player.method_5854() != this) {
            return;
        }
        this.serverClimbHeld = climbHeld;
        this.serverFireBreathHeld = fireBreathHeld;
        this.serverForwardInput = class_3532.method_15363((float)forwardInput, (float)-1.0f, (float)1.0f);
        this.serverSidewaysInput = class_3532.method_15363((float)sidewaysInput, (float)-1.0f, (float)1.0f);
        this.setFireBreathing(fireBreathHeld);
        if (toggleFlight && !this.isFlightActive()) {
            this.setFlightActive(true);
            this.setDiving(false);
            class_243 velocity = this.method_18798();
            this.method_18800(velocity.field_1352, Math.max(velocity.field_1351, 0.52), velocity.field_1350);
            this.field_6007 = true;
        }
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        if (player.method_21823()) {
            if (!this.method_37908().field_9236) {
                this.method_6722(player);
            }
            return class_1269.field_5812;
        }
        class_1799 stack = player.method_5998(hand);
        if (this.feedDragon(player, stack)) {
            return class_1269.field_5812;
        }
        if (!this.canBeRidden()) {
            return class_1269.field_5811;
        }
        if (!this.canAccessBoundDragon(player, true)) {
            return class_1269.field_5812;
        }
        if (this.method_5626((class_1297)player)) {
            return class_1269.field_5811;
        }
        if (!this.method_37908().field_9236) {
            player.method_5804((class_1297)this);
        }
        return class_1269.field_5812;
    }

    public void method_6722(class_1657 player) {
        class_3222 serverPlayer;
        block3: {
            block2: {
                if (!(player instanceof class_3222)) break block2;
                serverPlayer = (class_3222)player;
                if (this.canAccessBoundDragon(player, true) && player.method_56094((class_1297)this, 4.0)) break block3;
            }
            return;
        }
        serverPlayer.method_17355((class_3908)new DragonStatsScreenFactory(this));
    }

    protected boolean method_5818(class_1297 passenger) {
        if (this.getBoundNpcUuid() != null) {
            return false;
        }
        return this.canBeRidden() && this.method_5685().isEmpty() && passenger instanceof class_1309;
    }

    public class_1309 method_5642() {
        class_1309 living;
        class_1297 passenger = this.method_31483();
        return passenger instanceof class_1309 ? (living = (class_1309)passenger) : null;
    }

    protected class_243 method_52533(class_1297 passenger, class_4048 dimensions, float scaleFactor) {
        return this.getScaledSeatOffset(1.0f);
    }

    public void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        if (!this.method_5626(passenger)) {
            return;
        }
        class_243 seatPos = this.getSeatWorldPos(passenger);
        class_243 attachment = passenger.method_55668((class_1297)this);
        positionUpdater.accept(passenger, seatPos.field_1352 - attachment.field_1352, seatPos.field_1351 - attachment.field_1351, seatPos.field_1350 - attachment.field_1350);
        if (this.isFlightActive()) {
            this.copyFlightRotationToPassenger(passenger);
        } else {
            passenger.method_36456(passenger.method_36454() + this.method_36454() - this.field_5982);
            passenger.method_5636(this.method_36454());
            passenger.method_5847(passenger.method_5791() + this.method_36454() - this.field_5982);
        }
    }

    private void copyFlightRotationToPassenger(class_1297 passenger) {
        if (passenger instanceof class_1309) {
            class_1309 livingPassenger = (class_1309)passenger;
            livingPassenger.field_6283 = this.method_36454();
            livingPassenger.field_6220 = this.method_36454();
        }
    }

    private class_243 getSeatWorldPos(class_1297 passenger) {
        class_243 local = this.getScaledSeatOffset(1.0f);
        return this.method_19538().method_1019(this.rotateLocalOffset(local));
    }

    public class_243 getMountedSeatWorldPos(class_1297 passenger) {
        return this.getSeatWorldPos(passenger);
    }

    public class_243 getMountedSeatWorldPos(class_1297 passenger, float tickDelta) {
        class_243 local = this.getScaledSeatOffset(tickDelta);
        return this.method_30950(tickDelta).method_1019(this.rotateLocalOffset(local, tickDelta));
    }

    public class_243 getMountedFlightRenderRootPos(class_1297 passenger, float tickDelta) {
        class_243 passengerPivot = passenger.method_30950(tickDelta).method_1019(passenger.method_55668((class_1297)this));
        return passengerPivot.method_1020(this.getRotatedSeatOffset(tickDelta));
    }

    private class_243 rotateLocalOffset(class_243 local) {
        return this.rotateLocalOffset(local, 1.0f);
    }

    private class_243 rotateLocalOffset(class_243 local, float tickDelta) {
        Quaternionf rotation = new Quaternionf().rotateY((float)Math.toRadians(180.0f - class_3532.method_17821((float)tickDelta, (float)this.field_5982, (float)this.method_36454()))).rotateX((float)Math.toRadians(-class_3532.method_16439((float)tickDelta, (float)this.field_6004, (float)this.method_36455()))).rotateZ((float)Math.toRadians(this.getVisualFlightRoll(tickDelta)));
        Vector3f rotated = rotation.transform(new Vector3f((float)local.field_1352, (float)local.field_1351, (float)local.field_1350));
        return new class_243((double)rotated.x, (double)rotated.y, (double)rotated.z);
    }

    public class_243 method_24829(class_1309 passenger) {
        class_243 forward = class_243.method_1030((float)0.0f, (float)this.method_36454());
        class_243 side = new class_243(forward.field_1350, 0.0, -forward.field_1352);
        return this.method_19538().method_1019(side.method_1021(2.0)).method_1031(0.0, 0.5, 0.0);
    }

    public void method_6091(class_243 movementInput) {
        class_1309 rider = this.method_5642();
        if (this.method_5805() && rider instanceof class_1657) {
            class_1657 player = (class_1657)rider;
            this.syncMountedOrientation(player);
            if (this.method_5787()) {
                if (this.isFlightActive()) {
                    this.logMountedFlightStart(player);
                    this.travelMountedFlight(player);
                } else {
                    float sideways = player.field_6212 * 0.6f;
                    float forward = player.field_6250;
                    if (forward < 0.0f) {
                        forward *= 0.35f;
                    }
                    this.method_6125((float)this.method_45325(class_5134.field_23719));
                    super.method_6091(new class_243((double)sideways, movementInput.field_1351, (double)forward));
                }
            }
            return;
        }
        super.method_6091(movementInput);
    }

    private void syncMountedOrientation(class_1657 rider) {
        float targetYaw = rider.method_36454() * 1.0f;
        if (this.isFlightActive()) {
            float steeringYaw = targetYaw - this.serverSidewaysInput * 10.0f;
            float smoothedYaw = class_3532.method_17821((float)0.18f, (float)this.method_36454(), (float)steeringYaw);
            float smoothedPitch = class_3532.method_16439((float)0.16f, (float)this.method_36455(), (float)rider.method_36455());
            this.method_36456(smoothedYaw);
            this.method_36457(smoothedPitch);
            this.field_6283 = smoothedYaw;
            this.field_6241 = smoothedYaw;
            return;
        }
        float yawLerp = (double)Math.abs(rider.field_6250) > 0.02 || (double)Math.abs(rider.field_6212) > 0.02 ? 0.28f : 0.18f;
        float smoothedYaw = class_3532.method_17821((float)yawLerp, (float)this.method_36454(), (float)targetYaw);
        this.method_36456(smoothedYaw);
        this.method_36457(class_3532.method_16439((float)0.2f, (float)this.method_36455(), (float)0.0f));
        this.field_6283 = smoothedYaw;
        this.field_6241 = smoothedYaw;
    }

    private class_243 getMountedFlightPivot(class_1657 rider) {
        if (this.mountedFlightPivot == null) {
            this.mountedFlightPivot = rider.method_19538().method_1019(rider.method_55668((class_1297)this));
        }
        return this.mountedFlightPivot;
    }

    private void setMountedFlightPivot(class_243 pivot) {
        this.mountedFlightPivot = pivot;
    }

    private void placeRootFromMountedFlightPivot(class_243 pivot) {
        class_243 rootPos = pivot.method_1020(this.getRotatedSeatOffset(1.0f));
        this.method_5814(rootPos.field_1352, rootPos.field_1351, rootPos.field_1350);
    }

    private class_243 getRotatedSeatOffset(float tickDelta) {
        class_243 localSeatOffset = this.getScaledSeatOffset(tickDelta);
        return this.rotateLocalOffset(localSeatOffset, tickDelta);
    }

    private class_243 getScaledSeatOffset(float tickDelta) {
        return DragonModelData.get(this).seatOffset(this, tickDelta).method_1019(RIDER_SEAT_OFFSET).method_1021((double)this.getModelScale());
    }

    private void snapPassengerToMountedFlightPivot(class_1297 passenger, class_243 pivot) {
        class_243 attachment = passenger.method_55668((class_1297)this);
        passenger.method_5814(pivot.field_1352 - attachment.field_1352, pivot.field_1351 - attachment.field_1351, pivot.field_1350 - attachment.field_1350);
    }

    private void debugMountedFlightPivot(class_1657 rider, class_243 pivot, String phase) {
        if (!DEBUG_MOUNT_PIVOT || this.field_6012 < this.nextMountPivotDebugAge) {
            return;
        }
        this.nextMountPivotDebugAge = this.field_6012 + 100;
        class_243 seat = this.method_19538().method_1019(this.getRotatedSeatOffset(1.0f));
        double error = seat.method_1022(pivot);
        GotMod.LOGGER.info("Dragon mount pivot {} dragonId={} rider={} error={} pivot={} root={} yaw={} pitch={} roll={}", new Object[]{phase, this.method_5628(), rider.method_5477().getString(), String.format(Locale.ROOT, "%.5f", error), pivot, this.method_19538(), String.format(Locale.ROOT, "%.2f", Float.valueOf(this.method_36454())), String.format(Locale.ROOT, "%.2f", Float.valueOf(this.method_36455())), String.format(Locale.ROOT, "%.2f", Float.valueOf(this.getFlightRoll()))});
    }

    private void logMountedFlightStart(class_1657 rider) {
        if (this.loggedMountedFlightStart) {
            return;
        }
        this.loggedMountedFlightStart = true;
        class_243 seat = this.method_19538().method_1019(this.getRotatedSeatOffset(1.0f));
        class_243 riderPivot = rider.method_19538().method_1019(rider.method_55668((class_1297)this));
        GotMod.LOGGER.info("Dragon mounted flight pivot active dragonId={} rider={} seatError={} root={} riderPivot={} yaw={} pitch={} roll={}", new Object[]{this.method_5628(), rider.method_5477().getString(), DragonEntity.format(seat.method_1022(riderPivot)), this.method_19538(), riderPivot, DragonEntity.format(this.method_36454()), DragonEntity.format(this.method_36455()), DragonEntity.format(this.getFlightRoll())});
    }

    private void probeMountedFlightPivot(class_1657 rider, class_243 pivot) {
        if (this.field_6012 < this.nextMountPivotProbeAge) {
            return;
        }
        this.nextMountPivotProbeAge = this.field_6012 + 20;
        class_243 seat = this.method_19538().method_1019(this.getRotatedSeatOffset(1.0f));
        double error = seat.method_1022(pivot);
        if (error <= 0.08) {
            return;
        }
        GotMod.LOGGER.warn("Dragon mounted flight pivot drift dragonId={} rider={} error={} root={} pivot={} yaw={} pitch={} roll={}", new Object[]{this.method_5628(), rider.method_5477().getString(), DragonEntity.format(error), this.method_19538(), pivot, DragonEntity.format(this.method_36454()), DragonEntity.format(this.method_36455()), DragonEntity.format(this.getFlightRoll())});
    }

    private static String format(double value) {
        return String.format(Locale.ROOT, "%.3f", value);
    }

    private void travelMountedFlight(class_1657 rider) {
        this.updateFlightRoll(rider);
        class_243 look = this.method_5720().method_1029();
        class_243 velocity = this.method_18798();
        double forwardInput = Math.max(0.0, (double)this.serverForwardInput);
        double downwardIntent = class_3532.method_15350((double)(-look.field_1351), (double)0.0, (double)1.0);
        double speed = velocity.method_1033();
        double maxSpeed = this.isDiving() ? 2.65 : 1.7;
        double targetSpeed = speed;
        if (forwardInput > 0.02) {
            double thrust = this.isDiving() ? 0.145 : 0.095;
            targetSpeed = Math.min(maxSpeed, Math.max(speed, 0.65) + thrust * forwardInput);
        }
        if (downwardIntent > 0.0) {
            targetSpeed = Math.min(maxSpeed, Math.max(targetSpeed, 0.65) + downwardIntent * 0.055);
        }
        if (targetSpeed > 1.0E-4) {
            double steering = this.isDiving() ? (forwardInput > 0.02 ? 0.32 : 0.14) : (forwardInput > 0.02 ? 0.24 : 0.09);
            velocity = DragonEntity.lerpVelocity(velocity, look.method_1021(targetSpeed), steering);
        }
        if ((velocity = velocity.method_1021(this.isDiving() ? 0.999 : 0.997).method_1031(0.0, -0.016 * (1.0 - downwardIntent * 0.45), 0.0)).method_1027() > maxSpeed * maxSpeed) {
            velocity = velocity.method_1029().method_1021(maxSpeed);
        }
        this.method_18799(velocity);
        this.method_5784(class_1313.field_6308, this.method_18798());
        this.field_6007 = true;
        this.method_29242(false);
    }

    private static class_243 lerpVelocity(class_243 current, class_243 target, double amount) {
        return new class_243(class_3532.method_16436((double)amount, (double)current.field_1352, (double)target.field_1352), class_3532.method_16436((double)amount, (double)current.field_1351, (double)target.field_1351), class_3532.method_16436((double)amount, (double)current.field_1350, (double)target.field_1350));
    }

    private void tickMountedFireBreath(class_1657 rider) {
        if (this.method_37908().field_9236) {
            return;
        }
        if (!this.serverFireBreathHeld) {
            this.serverFireBreathTicks = Math.max(0, this.serverFireBreathTicks - 1);
            this.setFireBreathing(false);
            return;
        }
        this.setFireBreathing(true);
        this.serverFireBreathTicks = Math.min(10, this.serverFireBreathTicks + 1);
        if (this.serverFireBreathTicks < 10 || this.field_6012 % 2 != 0) {
            return;
        }
        this.spawnDragonFireProjectile(rider);
    }

    private void spawnDragonFireProjectile(class_1657 rider) {
        class_243 direction = rider.method_5828(1.0f).method_1029();
        if (direction.method_1027() < 1.0E-6) {
            return;
        }
        class_243 outputPos = this.getDragonFireOutputWorldPos(1.0f);
        class_243 spread = new class_243((this.method_59922().method_43058() - 0.5) * 0.026, (this.method_59922().method_43058() - 0.5) * 0.018, (this.method_59922().method_43058() - 0.5) * 0.026);
        class_243 dragonVelocity = this.method_18798();
        double inheritedForwardSpeed = Math.max(0.0, dragonVelocity.method_1026(direction)) * 0.65;
        class_243 velocity = direction.method_1019(spread).method_1029().method_1021(3.15 + inheritedForwardSpeed);
        DragonFireEntity fire = new DragonFireEntity(this.method_37908(), (class_1297)this, velocity);
        fire.method_5808(outputPos.field_1352, outputPos.field_1351, outputPos.field_1350, this.method_36454(), this.method_36455());
        fire.method_7432((class_1297)this);
        this.method_37908().method_8649((class_1297)fire);
        class_1937 class_19372 = this.method_37908();
        if (class_19372 instanceof class_3218) {
            class_3218 serverWorld = (class_3218)class_19372;
            serverWorld.method_14199((class_2394)GotParticles.DRAGON_FIRE, outputPos.field_1352, outputPos.field_1351, outputPos.field_1350, 5, 0.08, 0.08, 0.08, 0.01);
        }
    }

    public class_243 getDragonFireOutputWorldPos(float tickDelta) {
        class_243 local = DragonModelData.get(this).fireOutputOffset(this, tickDelta).method_1021((double)this.getModelScale());
        return this.method_30950(tickDelta).method_1019(this.rotateLocalOffset(local, tickDelta));
    }

    public void method_5674(class_2940<?> data) {
        super.method_5674(data);
        if (DRAGON_AGE.equals(data) || AGE_DAYS.equals(data)) {
            this.syncDragonAgeScale();
            if (!this.method_37908().field_9236 && !this.canBeRidden()) {
                this.method_5772();
                this.exitFlightMode();
            }
        }
    }

    private void updateFlightRoll(class_1657 rider) {
        float roll = this.getFlightRoll();
        float targetRoll = class_3532.method_15363((float)(this.serverSidewaysInput * 55.0f), (float)-55.0f, (float)55.0f);
        roll = class_3532.method_16439((float)0.18f, (float)roll, (float)targetRoll);
        if (!this.isFlightActive()) {
            roll *= 0.9f;
        }
        this.setFlightRoll(roll);
    }

    public boolean method_5747(float fallDistance, float damageMultiplier, class_1282 damageSource) {
        return false;
    }

    public boolean method_6481(class_1799 stack) {
        return false;
    }

    public class_1296 method_5613(class_3218 world, class_1296 entity) {
        return null;
    }

    public void method_5652(class_2487 nbt) {
        UUID boundNpcUuid;
        super.method_5652(nbt);
        nbt.method_10556(FLIGHT_ACTIVE_KEY, this.isFlightActive());
        nbt.method_10556(DIVING_KEY, this.isDiving());
        nbt.method_10548(FLIGHT_ROLL_KEY, this.getFlightRoll());
        nbt.method_10582(VARIANT_KEY, this.getVariant());
        nbt.method_10582(VARIANT_ALIAS_KEY, this.getVariant());
        nbt.method_10582(ORIGIN_KEY, this.getOriginId());
        nbt.method_10582(ORIGIN_ALIAS_KEY, this.getOriginId());
        nbt.method_10582(MODEL_TYPE_KEY, this.getModelTypeId());
        nbt.method_10582(MODEL_TYPE_ALIAS_KEY, this.getModelTypeId());
        nbt.method_10582(DRAGON_AGE_KEY, this.getDragonAgeId());
        nbt.method_10582(DRAGON_AGE_ALIAS_KEY, this.getDragonAgeId());
        nbt.method_10569(AGE_DAYS_KEY, this.getAgeDays());
        nbt.method_10569(AGE_DAYS_ALIAS_KEY, this.getAgeDays());
        nbt.method_10569(HUNGER_KEY, this.getHunger());
        nbt.method_10569(HUNGER_ALIAS_KEY, this.getHunger());
        nbt.method_10544(LAST_GROWTH_OBSERVED_TIME_KEY, this.lastGrowthObservedTime);
        nbt.method_10556(GROWTH_TIME_INITIALIZED_KEY, this.growthTimeInitialized);
        UUID boundPlayerUuid = this.getBoundPlayerUuid();
        if (boundPlayerUuid != null) {
            nbt.method_25927(BOUND_PLAYER_UUID_KEY, boundPlayerUuid);
            nbt.method_25927(BOUND_PLAYER_UUID_ALIAS_KEY, boundPlayerUuid);
        }
        if ((boundNpcUuid = this.getBoundNpcUuid()) != null) {
            nbt.method_25927(BOUND_NPC_UUID_KEY, boundNpcUuid);
            nbt.method_25927(BOUND_NPC_UUID_ALIAS_KEY, boundNpcUuid);
        }
        DragonModelPartNbtHelper.write(nbt, this.getModelParts());
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.setFlightActive(nbt.method_10577(FLIGHT_ACTIVE_KEY));
        this.setDiving(nbt.method_10577(DIVING_KEY));
        this.setFlightRoll(nbt.method_10583(FLIGHT_ROLL_KEY));
        if (nbt.method_10545(ORIGIN_KEY)) {
            this.setOrigin(nbt.method_10558(ORIGIN_KEY));
        } else {
            this.setOrigin(nbt.method_10545(ORIGIN_ALIAS_KEY) ? nbt.method_10558(ORIGIN_ALIAS_KEY) : DragonOrigin.DEFAULT.id());
        }
        if (nbt.method_10545(VARIANT_KEY)) {
            this.setVariant(nbt.method_10558(VARIANT_KEY));
        } else {
            this.setVariant(nbt.method_10545(VARIANT_ALIAS_KEY) ? nbt.method_10558(VARIANT_ALIAS_KEY) : DEFAULT_VARIANT);
        }
        if (nbt.method_10545(MODEL_TYPE_KEY)) {
            this.setModelType(nbt.method_10558(MODEL_TYPE_KEY));
        } else {
            this.setModelType(nbt.method_10545(MODEL_TYPE_ALIAS_KEY) ? nbt.method_10558(MODEL_TYPE_ALIAS_KEY) : DragonModelType.DEFAULT.id());
        }
        if (nbt.method_10545(DRAGON_AGE_KEY)) {
            this.setDragonAge(nbt.method_10558(DRAGON_AGE_KEY));
        } else if (nbt.method_10545(DRAGON_AGE_ALIAS_KEY)) {
            this.setDragonAge(nbt.method_10558(DRAGON_AGE_ALIAS_KEY));
        } else if (nbt.method_10573(AGE_STRING_ALIAS_KEY, 8)) {
            this.setDragonAge(nbt.method_10558(AGE_STRING_ALIAS_KEY));
        } else {
            this.setDragonAge(DragonAge.DEFAULT);
        }
        if (nbt.method_25928(BOUND_PLAYER_UUID_KEY)) {
            this.setBoundPlayerUuid(nbt.method_25926(BOUND_PLAYER_UUID_KEY));
        } else if (nbt.method_25928(BOUND_PLAYER_UUID_ALIAS_KEY)) {
            this.setBoundPlayerUuid(nbt.method_25926(BOUND_PLAYER_UUID_ALIAS_KEY));
        } else {
            this.setBoundPlayerUuid(null);
        }
        if (nbt.method_25928(BOUND_NPC_UUID_KEY)) {
            this.setBoundNpcUuid(nbt.method_25926(BOUND_NPC_UUID_KEY));
        } else if (nbt.method_25928(BOUND_NPC_UUID_ALIAS_KEY)) {
            this.setBoundNpcUuid(nbt.method_25926(BOUND_NPC_UUID_ALIAS_KEY));
        } else {
            this.setBoundNpcUuid(null);
        }
        if (nbt.method_10573(AGE_DAYS_KEY, 99)) {
            this.setAgeDays(nbt.method_10550(AGE_DAYS_KEY));
        } else if (nbt.method_10573(AGE_DAYS_ALIAS_KEY, 99)) {
            this.setAgeDays(nbt.method_10550(AGE_DAYS_ALIAS_KEY));
        } else {
            this.setAgeDays(0);
        }
        if (nbt.method_10573(HUNGER_KEY, 99)) {
            this.setHunger(nbt.method_10550(HUNGER_KEY));
        } else if (nbt.method_10573(HUNGER_ALIAS_KEY, 99)) {
            this.setHunger(nbt.method_10550(HUNGER_ALIAS_KEY));
        } else {
            this.setHunger(0);
        }
        this.lastGrowthObservedTime = nbt.method_10537(LAST_GROWTH_OBSERVED_TIME_KEY);
        this.growthTimeInitialized = nbt.method_10577(GROWTH_TIME_INITIALIZED_KEY);
        DragonModelPartNbtHelper.read(nbt).ifPresent(this::setModelParts);
        this.setModelType(this.getBaseModelType().id());
        this.setModelParts(this.getModelParts());
    }

    private record DragonStatsScreenFactory(DragonEntity dragon) implements ExtendedScreenHandlerFactory<DragonStatsScreenOpeningData>
    {
        public DragonStatsScreenOpeningData getScreenOpeningData(class_3222 player) {
            return new DragonStatsScreenOpeningData(this.dragon.method_5628());
        }

        public class_2561 method_5476() {
            return this.dragon.method_5476();
        }

        public class_1703 createMenu(int syncId, class_1661 playerInventory, class_1657 player) {
            return DragonStatsScreenHandler.createServer(syncId, playerInventory, this.dragon);
        }
    }
}

