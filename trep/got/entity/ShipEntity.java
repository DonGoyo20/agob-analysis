/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_4738
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1690
 *  net.minecraft.class_1690$class_1692
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1928
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3532
 *  net.minecraft.class_3610
 *  net.minecraft.class_3611
 *  net.minecraft.class_3612
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 *  net.minecraft.class_5712
 *  net.minecraft.class_6880
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Vector3f
 */
package trep.got.entity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import net.minecraft.class_5712;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import trep.got.entity.AbstractKingdomCaptainNpcEntity;
import trep.got.entity.ShipModelData;
import trep.got.entity.VesselMaterialProfile;
import trep.got.entity.VesselVariant;
import trep.got.physics.PhysicsCoreRegistry;
import trep.got.registry.GotCastles;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;
import trep.got.vehicle.collision.VesselCollisionResolver;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformRuntime;
import trep.got.vehicle.platform.PlatformTransform;

public final class ShipEntity
extends class_1690
implements PlatformEntity {
    private static final class_2940<Float> SAIL_BEND = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13320);
    private static final class_2940<String> PLANK_WOOD_MATERIAL = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> LOG_WOOD_MATERIAL = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> WOOL_MATERIAL = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Integer> HULL_HP = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<Boolean> VOYAGE_MODE = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13323);
    private static final class_2940<Optional<UUID>> VOYAGE_CAPTAIN_UUID = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13313);
    private static final class_2940<Optional<UUID>> VOYAGE_PLAYER_UUID = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13313);
    private static final class_2940<Float> VOYAGE_TARGET_SPEED = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13320);
    private static final class_2940<Float> VOYAGE_TARGET_YAW = class_2945.method_12791(ShipEntity.class, (class_2941)class_2943.field_13320);
    public static final double MODEL_SCALE = 2.0;
    private static final double DEFAULT_WATERLINE_OFFSET = 0.25;
    private static final double BASE_MAX_CRUISE_SPEED = 0.68;
    private static final double BASE_SPEED_STEP = 0.0028333333333333335;
    private static final float TURN_ACCELERATION = 0.045f;
    private static final float TURN_DAMPING_IDLE = 0.84f;
    private static final float TURN_DAMPING_ACTIVE = 0.95f;
    private static final float MAX_TURN_RATE = 0.95f;
    private final PlatformRuntime platformRuntime = new PlatformRuntime();
    private float sailTargetBend;
    private float sailForwardSpeed;
    private float sailResponse;
    private boolean shipPressingLeft;
    private boolean shipPressingRight;
    private boolean shipPressingForward;
    private boolean shipPressingBack;
    private double cruiseSpeed;
    private double persistedCruiseSpeed;
    private float turnVelocity;
    private class_243 collisionDriftVelocity = class_243.field_1353;
    private float collisionTurnVelocity;
    private int impactCooldownTicks;
    private double cachedSurfaceY = Double.NaN;
    private int surfaceYCacheTick = -1;

    public ShipEntity(class_1299<? extends class_1690> entityType, class_1937 world) {
        super(entityType, world);
    }

    public ShipEntity(class_1937 world, double x, double y, double z) {
        this(GotEntities.SHIP, world);
        this.method_5808(x, y, z, this.method_36454(), this.method_36455());
    }

    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(SAIL_BEND, (Object)Float.valueOf(0.0f));
        builder.method_56912(PLANK_WOOD_MATERIAL, (Object)VesselMaterialProfile.DEFAULT.plankWoodFamily().plankId().toString());
        builder.method_56912(LOG_WOOD_MATERIAL, (Object)VesselMaterialProfile.DEFAULT.logWoodFamily().plankId().toString());
        builder.method_56912(WOOL_MATERIAL, (Object)VesselMaterialProfile.DEFAULT.woolFamily().blockId().toString());
        builder.method_56912(HULL_HP, (Object)this.getMaxHullHp());
        builder.method_56912(VOYAGE_MODE, (Object)false);
        builder.method_56912(VOYAGE_CAPTAIN_UUID, Optional.empty());
        builder.method_56912(VOYAGE_PLAYER_UUID, Optional.empty());
        builder.method_56912(VOYAGE_TARGET_SPEED, (Object)Float.valueOf(0.0f));
        builder.method_56912(VOYAGE_TARGET_YAW, (Object)Float.valueOf(0.0f));
    }

    public void method_5773() {
        if (this.method_5787() || this.isVoyageMode() && this.method_37908().field_9236) {
            this.applyShipControls();
        }
        super.method_5773();
        this.stabilizeOnWaterSurface();
        PhysicsCoreRegistry.tickShip(this);
        this.platformRuntime.update((class_1297)this, this.createPlatformTransform(), this.agob$getPlatformHull());
        if (!this.method_37908().field_9236) {
            VesselCollisionResolver.resolve(this);
        }
    }

    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        if (this.method_5626((class_1297)player)) {
            return class_1269.field_5811;
        }
        return super.method_5688(player, hand);
    }

    public class_1309 method_5642() {
        for (class_1297 passenger : this.method_5685()) {
            if (this.isVoyageCaptain(passenger) && passenger instanceof class_1309) {
                class_1309 living = (class_1309)passenger;
                return living;
            }
            if (this.isVoyageMode() && passenger instanceof AbstractKingdomCaptainNpcEntity) {
                AbstractKingdomCaptainNpcEntity captain = (AbstractKingdomCaptainNpcEntity)passenger;
                return captain;
            }
            if (!(passenger instanceof AbstractKingdomCaptainNpcEntity)) continue;
            AbstractKingdomCaptainNpcEntity captain = (AbstractKingdomCaptainNpcEntity)passenger;
            return captain;
        }
        return super.method_5642();
    }

    public class_1297 method_31483() {
        if (this.isVoyageMode()) {
            for (class_1297 passenger : this.method_5685()) {
                if (!this.isVoyageCaptain(passenger)) continue;
                return passenger;
            }
            for (class_1297 passenger : this.method_5685()) {
                if (!(passenger instanceof AbstractKingdomCaptainNpcEntity)) continue;
                return passenger;
            }
        }
        return super.method_31483();
    }

    protected boolean method_5818(class_1297 passenger) {
        return this.method_5685().size() < this.method_42281();
    }

    public void method_5793(class_1297 passenger) {
        if (this.isVoyageMode() && (this.isVoyageCaptain(passenger) || this.isVoyagePlayer(passenger))) {
            return;
        }
        super.method_5793(passenger);
    }

    protected int method_42281() {
        return this.getVesselVariant().seatCount();
    }

    protected class_243 method_52533(class_1297 passenger, class_4048 dimensions, float scaleFactor) {
        int seatIndex = this.resolveSeatIndex(passenger);
        if (seatIndex < 0) {
            return super.method_52533(passenger, dimensions, scaleFactor);
        }
        return this.getAttachmentOffset(this.getSeatAnchorLocalPos(seatIndex));
    }

    protected void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        int seatIndex = this.resolveSeatIndex(passenger);
        if (seatIndex < 0) {
            super.method_5865(passenger, positionUpdater);
            return;
        }
        class_243 worldPos = this.getSeatWorldPos(seatIndex);
        if (this.isVoyageMode() && passenger instanceof AbstractKingdomCaptainNpcEntity) {
            double captainY = worldPos.field_1351 - (double)passenger.method_17682() * 0.5;
            positionUpdater.accept(passenger, worldPos.field_1352, captainY, worldPos.field_1350);
            passenger.method_36456(this.method_36454());
            passenger.method_5636(this.method_36454());
            passenger.method_5847(this.method_36454());
            passenger.method_36457(0.0f);
            return;
        }
        class_243 vehicleAttachment = passenger.method_55668((class_1297)this);
        positionUpdater.accept(passenger, worldPos.field_1352 - vehicleAttachment.field_1352, worldPos.field_1351 - vehicleAttachment.field_1351, worldPos.field_1350 - vehicleAttachment.field_1350);
        passenger.method_36456(passenger.method_36454() + this.method_36454() - this.field_5982);
        passenger.method_5636(this.method_36454());
        passenger.method_5847(passenger.method_5791() + this.method_36454() - this.field_5982);
        this.method_7546(passenger);
    }

    public class_243 getSeatWorldPos(int seatIndex) {
        return this.toWorld(this.getSeatAnchorLocalPos(seatIndex));
    }

    public class_243 method_24829(class_1309 passenger) {
        class_243 deckTop = this.toWorld(new class_243(0.0, 1.0, 0.0)).method_1031(0.0, 1.0, 0.0);
        class_2338 deckPos = class_2338.method_49638((class_2374)deckTop);
        if (this.method_37908().method_22347(deckPos) || this.method_37908().method_8320(deckPos).method_26220((class_1922)this.method_37908(), deckPos).method_1110()) {
            return deckTop;
        }
        class_243 starboard = this.toWorld(new class_243(1.8, 0.1, 0.0));
        class_243 port = this.toWorld(new class_243(-1.8, 0.1, 0.0));
        class_2338 portPos = class_2338.method_49638((class_2374)port);
        if (this.method_37908().method_22347(portPos) || this.method_37908().method_8320(portPos).method_26220((class_1922)this.method_37908(), portPos).method_1110()) {
            return port;
        }
        return starboard;
    }

    public void method_5700(boolean drag) {
    }

    public void method_5759(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
        double targetY = this.method_5799() || this.method_5869() ? this.findSurfaceY() - this.getWaterlineOffset() : y;
        super.method_5759(x, targetY, z, yaw, 0.0f, interpolationSteps);
    }

    public double method_53827() {
        if (this.method_5799() || this.method_5869()) {
            return this.method_23318();
        }
        return super.method_53827();
    }

    public class_1792 method_7557() {
        return switch (this.getVesselVariant()) {
            default -> throw new MatchException(null, null);
            case VesselVariant.SHIP -> GotItems.SHIP;
            case VesselVariant.GALLEY -> GotItems.GALLEY;
            case VesselVariant.LONGSHIP -> GotItems.LONGSHIP;
        };
    }

    public class_1690.class_1692 method_47885() {
        return class_1690.class_1692.field_7727;
    }

    public void method_47884(class_1690.class_1692 type) {
    }

    public void method_7535(boolean pressingLeft, boolean pressingRight, boolean pressingForward, boolean pressingBack) {
        this.shipPressingLeft = pressingLeft;
        this.shipPressingRight = pressingRight;
        this.shipPressingForward = pressingForward;
        this.shipPressingBack = pressingBack;
        super.method_7535(false, false, false, false);
    }

    public float getSailBend() {
        return ((Float)this.field_6011.method_12789(SAIL_BEND)).floatValue();
    }

    public void setSailBend(float sailBend) {
        this.field_6011.method_12778(SAIL_BEND, (Object)Float.valueOf(sailBend));
    }

    public void setSailPhysicsDebug(float sailTargetBend, float sailForwardSpeed, float sailResponse) {
        this.sailTargetBend = sailTargetBend;
        this.sailForwardSpeed = sailForwardSpeed;
        this.sailResponse = sailResponse;
    }

    public float getCruiseSpeed() {
        return (float)this.cruiseSpeed;
    }

    public void setVoyageMode(@Nullable UUID captainUuid, @Nullable UUID playerUuid) {
        this.field_6011.method_12778(VOYAGE_MODE, (Object)(captainUuid != null && playerUuid != null ? 1 : 0));
        this.field_6011.method_12778(VOYAGE_CAPTAIN_UUID, Optional.ofNullable(captainUuid));
        this.field_6011.method_12778(VOYAGE_PLAYER_UUID, Optional.ofNullable(playerUuid));
        if (captainUuid == null || playerUuid == null) {
            this.setVoyageAutopilot(0.0f, 0.0);
        }
    }

    public boolean isVoyageMode() {
        return (Boolean)this.field_6011.method_12789(VOYAGE_MODE);
    }

    public void setVoyageAutopilot(float targetYaw, double targetSpeed) {
        this.field_6011.method_12778(VOYAGE_TARGET_YAW, (Object)Float.valueOf(targetYaw));
        this.field_6011.method_12778(VOYAGE_TARGET_SPEED, (Object)Float.valueOf((float)Math.max(0.0, targetSpeed)));
    }

    public float getPersistedCruiseSpeed() {
        return (float)this.persistedCruiseSpeed;
    }

    public String getPlankWoodMaterialId() {
        return (String)this.field_6011.method_12789(PLANK_WOOD_MATERIAL);
    }

    public String getLogWoodMaterialId() {
        return (String)this.field_6011.method_12789(LOG_WOOD_MATERIAL);
    }

    public String getWoolMaterialId() {
        return (String)this.field_6011.method_12789(WOOL_MATERIAL);
    }

    public VesselMaterialProfile getMaterialProfile() {
        return VesselMaterialProfile.fromIds(this.getPlankWoodMaterialId(), this.getLogWoodMaterialId(), this.getWoolMaterialId());
    }

    public void applyMaterialProfile(VesselMaterialProfile profile) {
        this.field_6011.method_12778(PLANK_WOOD_MATERIAL, (Object)profile.plankWoodFamily().plankId().toString());
        this.field_6011.method_12778(LOG_WOOD_MATERIAL, (Object)profile.logWoodFamily().plankId().toString());
        this.field_6011.method_12778(WOOL_MATERIAL, (Object)profile.woolFamily().blockId().toString());
    }

    public int getHullHp() {
        return (Integer)this.field_6011.method_12789(HULL_HP);
    }

    public void setHullHp(int hullHp) {
        this.field_6011.method_12778(HULL_HP, (Object)class_3532.method_15340((int)hullHp, (int)0, (int)this.getMaxHullHp()));
    }

    public int getMaxHullHp() {
        return this.getVesselVariant().maxHullHp();
    }

    public void resetHullHp() {
        this.setHullHp(this.getMaxHullHp());
    }

    public boolean isDrivingForward() {
        return this.shipPressingForward;
    }

    public void method_5652(class_2487 nbt) {
        UUID voyagePlayerUuid;
        super.method_5652(nbt);
        nbt.method_10549("ShipCruiseSpeed", this.cruiseSpeed);
        nbt.method_10549("ShipPersistedCruiseSpeed", this.persistedCruiseSpeed);
        nbt.method_10548("ShipTurnVelocity", this.turnVelocity);
        nbt.method_10549("ShipCollisionDriftX", this.collisionDriftVelocity.field_1352);
        nbt.method_10549("ShipCollisionDriftZ", this.collisionDriftVelocity.field_1350);
        nbt.method_10548("ShipCollisionTurnVelocity", this.collisionTurnVelocity);
        nbt.method_10569("ShipImpactCooldown", this.impactCooldownTicks);
        nbt.method_10582("ShipPlankWoodMaterial", this.getPlankWoodMaterialId());
        nbt.method_10582("ShipLogWoodMaterial", this.getLogWoodMaterialId());
        nbt.method_10582("ShipWoolMaterial", this.getWoolMaterialId());
        nbt.method_10569("ShipHullHp", this.getHullHp());
        nbt.method_10556("ShipVoyageMode", this.isVoyageMode());
        nbt.method_10548("ShipVoyageTargetSpeed", ((Float)this.field_6011.method_12789(VOYAGE_TARGET_SPEED)).floatValue());
        nbt.method_10548("ShipVoyageTargetYaw", ((Float)this.field_6011.method_12789(VOYAGE_TARGET_YAW)).floatValue());
        UUID voyageCaptainUuid = this.getVoyageCaptainUuid();
        if (voyageCaptainUuid != null) {
            nbt.method_25927("ShipVoyageCaptainUuid", voyageCaptainUuid);
        }
        if ((voyagePlayerUuid = this.getVoyagePlayerUuid()) != null) {
            nbt.method_25927("ShipVoyagePlayerUuid", voyagePlayerUuid);
        }
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.cruiseSpeed = nbt.method_10574("ShipCruiseSpeed");
        this.persistedCruiseSpeed = nbt.method_10545("ShipPersistedCruiseSpeed") ? nbt.method_10574("ShipPersistedCruiseSpeed") : this.cruiseSpeed;
        this.turnVelocity = nbt.method_10583("ShipTurnVelocity");
        this.collisionDriftVelocity = new class_243(nbt.method_10545("ShipCollisionDriftX") ? nbt.method_10574("ShipCollisionDriftX") : 0.0, 0.0, nbt.method_10545("ShipCollisionDriftZ") ? nbt.method_10574("ShipCollisionDriftZ") : 0.0);
        this.collisionTurnVelocity = nbt.method_10545("ShipCollisionTurnVelocity") ? nbt.method_10583("ShipCollisionTurnVelocity") : 0.0f;
        this.impactCooldownTicks = nbt.method_10550("ShipImpactCooldown");
        String plankWood = nbt.method_10545("ShipPlankWoodMaterial") ? nbt.method_10558("ShipPlankWoodMaterial") : nbt.method_10558("ShipWoodMaterial");
        String logWood = nbt.method_10545("ShipLogWoodMaterial") ? nbt.method_10558("ShipLogWoodMaterial") : plankWood;
        this.applyMaterialProfile(VesselMaterialProfile.fromIds(plankWood, logWood, nbt.method_10558("ShipWoolMaterial")));
        this.setHullHp(nbt.method_10545("ShipHullHp") ? nbt.method_10550("ShipHullHp") : this.getMaxHullHp());
        this.setVoyageMode(nbt.method_25928("ShipVoyageCaptainUuid") ? nbt.method_25926("ShipVoyageCaptainUuid") : null, nbt.method_25928("ShipVoyagePlayerUuid") ? nbt.method_25926("ShipVoyagePlayerUuid") : null);
        this.setVoyageAutopilot(nbt.method_10545("ShipVoyageTargetYaw") ? nbt.method_10583("ShipVoyageTargetYaw") : this.method_36454(), nbt.method_10545("ShipVoyageTargetSpeed") ? (double)nbt.method_10583("ShipVoyageTargetSpeed") : 0.0);
    }

    public class_1799 method_31480() {
        return this.createDropStack();
    }

    public class_4048 method_18377(class_4050 pose) {
        return switch (this.getVesselVariant()) {
            default -> throw new MatchException(null, null);
            case VesselVariant.SHIP -> class_4048.method_18385((float)2.2f, (float)3.0f);
            case VesselVariant.GALLEY -> class_4048.method_18385((float)2.6f, (float)3.0f);
            case VesselVariant.LONGSHIP -> class_4048.method_18385((float)3.4f, (float)5.0f);
        };
    }

    protected class_238 method_33332() {
        class_4048 dimensions = this.method_18377(this.method_18376());
        double halfWidth = (double)dimensions.comp_2185() * 0.5;
        return new class_238(this.method_23317() - halfWidth, this.method_23318(), this.method_23321() - halfWidth, this.method_23317() + halfWidth, this.method_23318() + (double)dimensions.comp_2186(), this.method_23321() + halfWidth);
    }

    public boolean method_30948() {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30949(class_1297 other) {
        return false;
    }

    public void method_5697(class_1297 entity) {
    }

    public boolean method_5643(class_1282 source, float amount) {
        if (this.method_31481() || this.method_5679(source)) {
            return false;
        }
        this.method_54300(-this.method_54296());
        this.method_54299(10);
        this.method_54297(this.method_54294() + amount * 10.0f);
        this.method_5785();
        if (this.method_37908().field_9236) {
            return true;
        }
        class_1297 class_12972 = source.method_5529();
        if (class_12972 instanceof class_1657) {
            class_1657 player = (class_1657)class_12972;
            if (player.method_31549().field_7477) {
                GotCastles.handleVoyageShipDestroyed(this);
                this.method_31472();
                return true;
            }
        }
        this.setHullHp(this.getHullHp() - class_3532.method_15386((float)amount));
        this.method_32875((class_6880)class_5712.field_28736, source.method_5529());
        if (this.getHullHp() <= 0) {
            this.method_7516(source);
        }
        return true;
    }

    protected void method_7516(class_1282 source) {
        GotCastles.handleVoyageShipDestroyed(this);
        this.method_5772();
        this.method_32876((class_6880)class_5712.field_37676);
        if (this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
            this.method_5775(this.createDropStack());
        }
        this.method_31472();
    }

    @Override
    public PlatformHull agob$getPlatformHull() {
        return ShipModelData.get(this.getVesselVariant()).platformHull();
    }

    @Override
    public PlatformTransform agob$getPlatformTransform() {
        return this.platformRuntime.currentTransform();
    }

    @Override
    public PlatformTransform agob$getPreviousPlatformTransform() {
        return this.platformRuntime.previousTransform();
    }

    @Override
    public class_238 agob$getPlatformBroadPhaseBox() {
        return this.platformRuntime.broadPhaseBox();
    }

    public class_243 toWorld(class_243 local) {
        return this.createSeatTransform().localToWorld(local);
    }

    private class_243 getAttachmentOffset(class_243 local) {
        class_243 world = this.createSeatTransform().localToWorld(local);
        return world.method_1020(this.method_19538());
    }

    public class_243 toWorld(Vector3f local) {
        return this.toWorld(new class_243((double)local.x(), (double)local.y(), (double)local.z()));
    }

    private void applyShipControls() {
        if (this.isVoyageMode()) {
            this.shipPressingLeft = false;
            this.shipPressingRight = false;
            this.shipPressingForward = false;
            this.shipPressingBack = false;
            double maxCruiseSpeed = this.getMaxCruiseSpeed();
            double desiredSpeed = Math.min(maxCruiseSpeed, Math.max(0.0, (double)((Float)this.field_6011.method_12789(VOYAGE_TARGET_SPEED)).floatValue()));
            this.cruiseSpeed = this.cruiseSpeed < desiredSpeed ? Math.min(desiredSpeed, this.cruiseSpeed + this.getSpeedStep()) : Math.max(desiredSpeed, this.cruiseSpeed - this.getSpeedStep());
            this.persistedCruiseSpeed = this.cruiseSpeed;
            float targetYaw = ((Float)this.field_6011.method_12789(VOYAGE_TARGET_YAW)).floatValue();
            float yawDelta = class_3532.method_15393((float)(targetYaw - this.method_36454()));
            this.turnVelocity = class_3532.method_15363((float)(yawDelta * 0.15f), (float)-0.95f, (float)0.95f);
            this.method_36456(this.method_36454() + this.turnVelocity + this.collisionTurnVelocity);
            this.turnVelocity *= 0.95f;
            this.collisionTurnVelocity *= 0.88f;
            class_243 velocity = this.method_18798();
            class_243 forward = class_243.method_1030((float)0.0f, (float)this.method_36454());
            class_243 forwardHorizontal = new class_243(forward.field_1352, 0.0, forward.field_1350).method_1029();
            class_243 adjustedVelocity = forwardHorizontal.method_1021(this.cruiseSpeed).method_1019(this.collisionDriftVelocity);
            this.collisionDriftVelocity = this.dampCollisionDrift(this.collisionDriftVelocity, 0.9);
            this.method_18800(adjustedVelocity.field_1352, velocity.field_1351, adjustedVelocity.field_1350);
            return;
        }
        class_1309 controller = this.method_5642();
        double maxCruiseSpeed = this.getMaxCruiseSpeed();
        double speedStep = this.getSpeedStep();
        if (!(controller instanceof class_1657)) {
            this.shipPressingLeft = false;
            this.shipPressingRight = false;
            this.shipPressingForward = false;
            this.shipPressingBack = false;
            this.cruiseSpeed = Math.max(this.cruiseSpeed, this.persistedCruiseSpeed);
        } else {
            if (this.shipPressingForward) {
                this.cruiseSpeed = Math.min(maxCruiseSpeed, this.cruiseSpeed + speedStep);
            }
            if (this.shipPressingBack) {
                this.cruiseSpeed = Math.max(0.0, this.cruiseSpeed - speedStep);
            }
            this.persistedCruiseSpeed = this.cruiseSpeed;
        }
        int turnInput = 0;
        if (this.shipPressingLeft) {
            --turnInput;
        }
        if (this.shipPressingRight) {
            ++turnInput;
        }
        if (turnInput != 0) {
            this.turnVelocity += (float)turnInput * 0.045f;
        }
        float speedRatio = maxCruiseSpeed <= 0.0 ? 0.0f : (float)(this.cruiseSpeed / maxCruiseSpeed);
        float turnScale = 0.2f + speedRatio * 0.6f;
        this.turnVelocity = class_3532.method_15363((float)this.turnVelocity, (float)-0.95f, (float)0.95f);
        this.method_36456(this.method_36454() + this.turnVelocity * turnScale + this.collisionTurnVelocity);
        this.turnVelocity *= turnInput == 0 ? 0.84f : 0.95f;
        this.collisionTurnVelocity *= 0.86f;
        class_243 velocity = this.method_18798();
        class_243 forward = class_243.method_1030((float)0.0f, (float)this.method_36454());
        class_243 forwardHorizontal = new class_243(forward.field_1352, 0.0, forward.field_1350).method_1029();
        class_243 horizontalVelocity = new class_243(velocity.field_1352, 0.0, velocity.field_1350);
        double forwardComponent = horizontalVelocity.method_1026(forwardHorizontal);
        class_243 lateralVelocity = horizontalVelocity.method_1020(forwardHorizontal.method_1021(forwardComponent)).method_1021(0.65);
        class_243 cruiseVelocity = forwardHorizontal.method_1021(this.cruiseSpeed);
        class_243 adjustedVelocity = cruiseVelocity.method_1019(lateralVelocity).method_1019(this.collisionDriftVelocity);
        this.collisionDriftVelocity = this.dampCollisionDrift(this.collisionDriftVelocity, 0.82);
        this.method_18800(adjustedVelocity.field_1352, velocity.field_1351, adjustedVelocity.field_1350);
    }

    private class_243 dampCollisionDrift(class_243 drift, double damping) {
        class_243 damped = new class_243(drift.field_1352 * damping, 0.0, drift.field_1350 * damping);
        return damped.method_37268() < 1.0E-4 ? class_243.field_1353 : damped;
    }

    private int resolveSeatIndex(class_1297 passenger) {
        if (this.isVoyageMode()) {
            if (this.isVoyageCaptain(passenger) || passenger instanceof AbstractKingdomCaptainNpcEntity) {
                return 0;
            }
            if (this.isVoyagePlayer(passenger) || passenger instanceof class_1657) {
                return 1;
            }
        }
        return this.method_5685().indexOf(passenger);
    }

    private boolean isVoyageCaptain(class_1297 passenger) {
        UUID voyageCaptainUuid = this.getVoyageCaptainUuid();
        return voyageCaptainUuid != null && passenger != null && voyageCaptainUuid.equals(passenger.method_5667());
    }

    private boolean isVoyagePlayer(class_1297 passenger) {
        UUID voyagePlayerUuid = this.getVoyagePlayerUuid();
        return voyagePlayerUuid != null && passenger != null && voyagePlayerUuid.equals(passenger.method_5667());
    }

    @Nullable
    public UUID getVoyageCaptainUuid() {
        return ((Optional)this.field_6011.method_12789(VOYAGE_CAPTAIN_UUID)).orElse(null);
    }

    @Nullable
    public UUID getVoyagePlayerUuid() {
        return ((Optional)this.field_6011.method_12789(VOYAGE_PLAYER_UUID)).orElse(null);
    }

    private void stabilizeOnWaterSurface() {
        if (!this.method_5799() && !this.method_5869()) {
            this.method_5875(false);
            return;
        }
        this.method_5875(true);
        double targetY = this.findSurfaceY() - this.getWaterlineOffset();
        if (Math.abs(this.method_23318() - targetY) > 0.0025) {
            this.method_5814(this.method_23317(), targetY, this.method_23321());
        }
        this.field_6036 = targetY;
        this.field_5971 = targetY;
        class_243 velocity = this.method_18798();
        this.method_18800(velocity.field_1352, 0.0, velocity.field_1350);
        this.method_36457(0.0f);
        this.field_6017 = 0.0f;
    }

    private double findSurfaceY() {
        if (!Double.isNaN(this.cachedSurfaceY) && this.field_6012 - this.surfaceYCacheTick < 4) {
            return this.cachedSurfaceY;
        }
        this.surfaceYCacheTick = this.field_6012;
        class_2338.class_2339 mutable = class_2338.method_49637((double)this.method_23317(), (double)this.method_23318(), (double)this.method_23321()).method_25503();
        for (int offset = 2; offset >= -6; --offset) {
            mutable.method_10102(this.method_23317(), this.method_23318() + (double)offset, this.method_23321());
            class_3610 fluidState = this.method_37908().method_8316((class_2338)mutable);
            if (!fluidState.method_15771() || !fluidState.method_39360((class_3611)class_3612.field_15910)) continue;
            this.cachedSurfaceY = (float)mutable.method_10264() + fluidState.method_15763((class_1922)this.method_37908(), (class_2338)mutable);
            return this.cachedSurfaceY;
        }
        this.cachedSurfaceY = this.method_7544();
        return this.cachedSurfaceY;
    }

    public VesselVariant getVesselVariant() {
        if (this.method_5864() == GotEntities.GALLEY) {
            return VesselVariant.GALLEY;
        }
        if (this.method_5864() == GotEntities.LONGSHIP) {
            return VesselVariant.LONGSHIP;
        }
        return VesselVariant.SHIP;
    }

    private double getMaxCruiseSpeed() {
        return 0.68 * this.getVesselVariant().speedMultiplier();
    }

    private class_243 getSeatAnchorLocalPos(int seatIndex) {
        List<ShipModelData.SeatAnchor> seats = ShipModelData.get(this.getVesselVariant()).seats();
        ShipModelData.SeatAnchor seat = seats.get(Math.min(Math.max(seatIndex, 0), seats.size() - 1));
        return seat.localPos();
    }

    private double getSpeedStep() {
        return 0.0028333333333333335 * this.getVesselVariant().accelerationMultiplier();
    }

    public int getImpactCooldownTicks() {
        return this.impactCooldownTicks;
    }

    public void setImpactCooldownTicks(int impactCooldownTicks) {
        this.impactCooldownTicks = Math.max(impactCooldownTicks, 0);
    }

    public void applyCollisionImpulse(class_243 horizontalImpulse, float yawImpulse) {
        class_243 nextDrift = new class_243(this.collisionDriftVelocity.field_1352 + horizontalImpulse.field_1352, 0.0, this.collisionDriftVelocity.field_1350 + horizontalImpulse.field_1350);
        double maxDrift = 1.25;
        double length = nextDrift.method_37267();
        if (length > maxDrift) {
            nextDrift = nextDrift.method_1029().method_1021(maxDrift);
        }
        this.collisionDriftVelocity = nextDrift;
        this.collisionTurnVelocity = class_3532.method_15363((float)(this.collisionTurnVelocity + yawImpulse), (float)-2.5f, (float)2.5f);
        this.method_5785();
    }

    public void displaceForCollision(class_243 horizontalDisplacement) {
        if (horizontalDisplacement.method_1027() < 1.0E-6) {
            return;
        }
        this.method_5814(this.method_23317() + horizontalDisplacement.field_1352, this.method_23318(), this.method_23321() + horizontalDisplacement.field_1350);
        if (!this.method_37908().field_9236) {
            this.platformRuntime.refresh((class_1297)this, this.createPlatformTransform(), this.agob$getPlatformHull());
        }
        this.method_5785();
    }

    public class_243 getCollisionDriftVelocity() {
        return this.collisionDriftVelocity;
    }

    public double getCollisionMass() {
        return switch (this.getVesselVariant()) {
            default -> throw new MatchException(null, null);
            case VesselVariant.SHIP -> 1.0;
            case VesselVariant.GALLEY -> 1.35;
            case VesselVariant.LONGSHIP -> 2.2;
        };
    }

    private double getWaterlineOffset() {
        return 0.25;
    }

    public double getModelYOffset() {
        return switch (this.getVesselVariant()) {
            default -> throw new MatchException(null, null);
            case VesselVariant.SHIP, VesselVariant.GALLEY -> 2.75;
            case VesselVariant.LONGSHIP -> 6.875;
        };
    }

    private class_1799 createDropStack() {
        class_1799 stack = new class_1799((class_1935)this.method_7557());
        this.getMaterialProfile().apply(stack);
        return stack;
    }

    private PlatformTransform createPlatformTransform() {
        return new PlatformTransform(this.method_19538(), ShipModelData.get(this.getVesselVariant()).pivot(), 2.0, this.getModelYOffset(), this.method_36454(), class_3532.method_15393((float)this.method_36454()));
    }

    private PlatformTransform createSeatTransform() {
        return new PlatformTransform(this.method_19538(), ShipModelData.get(this.getVesselVariant()).pivot(), 2.0, this.getModelYOffset(), this.method_36454(), this.method_36454());
    }
}

