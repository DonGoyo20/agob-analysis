/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1297$class_4738
 *  net.minecraft.class_1297$class_5529
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1496
 *  net.minecraft.class_1542
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1928
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_4048
 *  net.minecraft.class_4050
 */
package trep.got.entity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1496;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import net.minecraft.class_4050;
import trep.got.entity.CartGroundSupport;
import trep.got.entity.PartyHorseEntity;
import trep.got.registry.GotEntities;
import trep.got.registry.GotItems;
import trep.got.vehicle.platform.PlatformEntity;
import trep.got.vehicle.platform.PlatformHull;
import trep.got.vehicle.platform.PlatformLocalBox;
import trep.got.vehicle.platform.PlatformRuntime;
import trep.got.vehicle.platform.PlatformTransform;

public final class CartEntity
extends class_1297
implements PlatformEntity {
    private static final class_2940<Optional<UUID>> ATTACHED_HORSE_UUID = class_2945.method_12791(CartEntity.class, (class_2941)class_2943.field_13313);
    private static final class_2940<Float> HEALTH = class_2945.method_12791(CartEntity.class, (class_2941)class_2943.field_13320);
    private static final Set<UUID> ATTACHED_CART_HORSE_UUIDS = ConcurrentHashMap.newKeySet();
    private static final float MAX_HEALTH = 30.0f;
    private static final double DRAWBAR_TO_HORSE_DISTANCE = 2.7;
    private static final double HORSE_HITCH_BACKSET = 0.9;
    private static final double DRAWBAR_HITCH_OFFSET = 2.15;
    private static final double DRAWBAR_HITCH_HEIGHT = 0.72;
    private static final class_243 PASSENGER_SEAT_OFFSET = new class_243(0.0, 1.0, 0.1);
    private static final double WHEEL_RADIUS = 0.25;
    private static final double FRONT_AXLE_OFFSET = -0.65;
    private static final double BACK_AXLE_OFFSET = 0.85;
    private static final double AXLE_Y_OFFSET = 0.28;
    private static final double MAX_STEP_HEIGHT = 1.2;
    private static final double SUPPORT_Y = 0.42;
    private static final PlatformHull PLATFORM_HULL = new PlatformHull(List.of(new PlatformLocalBox(-0.9, 0.42, -0.95, 0.9, 0.56, 1.15, 3), new PlatformLocalBox(-1.02, 0.42, -0.95, -0.88, 1.48, 1.15, 2), new PlatformLocalBox(0.88, 0.42, -0.95, 1.02, 1.48, 1.15, 2), new PlatformLocalBox(-0.88, 0.42, -0.95, 0.88, 1.48, -0.82, 2), new PlatformLocalBox(-0.88, 0.42, 1.02, 0.88, 1.48, 1.15, 2)));
    private final PlatformRuntime platformRuntime = new PlatformRuntime();
    private class_243 lastWheelSamplePos = class_243.field_1353;
    private UUID registeredAttachedHorseUuid;
    private float wheelRotation;
    private float prevWheelRotation;

    public CartEntity(class_1299<? extends CartEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_23807 = true;
    }

    public CartEntity(class_1937 world, double x, double y, double z) {
        this(GotEntities.CART, world);
        this.method_5808(x, y, z, this.method_36454(), this.method_36455());
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(ATTACHED_HORSE_UUID, Optional.empty());
        builder.method_56912(HEALTH, (Object)Float.valueOf(30.0f));
    }

    public void method_5773() {
        super.method_5773();
        this.prevWheelRotation = this.wheelRotation;
        this.syncAttachedHorseRegistry();
        if (!this.method_37908().field_9236) {
            this.tickServerMovement();
        }
        this.advanceWheelRotation();
        this.platformRuntime.update(this, this.createPlatformTransform(), PLATFORM_HULL);
    }

    private void tickServerMovement() {
        double backGround;
        class_1496 horse = this.getAttachedHorse();
        if (horse == null || !horse.method_5805()) {
            this.detachHorse();
            this.settleOnGround();
            return;
        }
        class_243 horseForward = CartEntity.horizontalForward(horse.method_43078());
        class_243 horsePos = horse.method_19538();
        class_243 hitchPos = horsePos.method_1020(horseForward.method_1021(0.9));
        class_243 currentCenter = this.method_19538();
        class_243 cartBack = new class_243(currentCenter.field_1352 - hitchPos.field_1352, 0.0, currentCenter.field_1350 - hitchPos.field_1350);
        cartBack = cartBack.method_1027() < 1.0E-5 ? CartEntity.horizontalForward(this.method_36454()) : cartBack.method_1029();
        float cartYaw = CartEntity.yawFromBackVector(cartBack);
        class_243 targetCenter = hitchPos.method_1019(cartBack.method_1021(2.15));
        class_243 frontSample = targetCenter.method_1019(cartBack.method_1021(-0.65));
        class_243 backSample = targetCenter.method_1019(cartBack.method_1021(0.85));
        double frontGround = this.sampleGroundHeight(frontSample);
        double heightDelta = frontGround - (backGround = this.sampleGroundHeight(backSample));
        if (Math.abs(heightDelta) > 1.2) {
            backGround = frontGround - Math.copySign(1.2, heightDelta);
        }
        double pitchRadians = Math.atan2(frontGround - backGround, Math.abs(-1.5));
        double cartCenterGround = (frontGround + backGround) * 0.5;
        double currentY = cartCenterGround + 0.28;
        this.method_5814(targetCenter.field_1352, currentY, targetCenter.field_1350);
        this.method_36456(cartYaw);
        this.method_36457((float)(-Math.toDegrees(pitchRadians)));
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
        this.updateAttachedHorseIdleState(horse, hitchPos);
    }

    private void updateAttachedHorseIdleState(class_1496 horse, class_243 hitchPos) {
        if (horse.method_5782()) {
            return;
        }
        class_243 horseForward = CartEntity.horizontalForward(horse.method_43078());
        class_243 desiredHorsePos = hitchPos.method_1019(horseForward.method_1021(0.9));
        double ground = this.sampleGroundHeight(desiredHorsePos);
        horse.method_5942().method_6340();
        horse.method_18799(class_243.field_1353);
        horse.method_5814(desiredHorsePos.field_1352, ground, desiredHorsePos.field_1350);
        horse.method_36456(this.method_36454() + 180.0f);
        horse.method_36457(0.0f);
        horse.method_5636(horse.method_36454());
        horse.method_5847(horse.method_36454());
        horse.field_5982 = horse.method_36454();
        horse.field_6220 = horse.method_43078();
        horse.field_6259 = horse.method_5791();
        horse.field_6004 = 0.0f;
    }

    private void settleOnGround() {
        double ground = this.sampleGroundHeight(this.method_19538());
        this.method_5814(this.method_23317(), ground + 0.28, this.method_23321());
        this.method_36457(0.0f);
        this.field_6004 = 0.0f;
    }

    private void advanceWheelRotation() {
        class_243 currentPos = this.method_19538();
        if (this.field_6012 == 1 || this.lastWheelSamplePos.equals((Object)class_243.field_1353)) {
            this.lastWheelSamplePos = currentPos;
            return;
        }
        class_243 delta = currentPos.method_1020(this.lastWheelSamplePos);
        double signedDistance = delta.method_1026(CartEntity.horizontalForward(class_3532.method_15393((float)(this.method_36454() + 180.0f))));
        if (Math.abs(signedDistance) > 1.0E-4) {
            this.wheelRotation = CartEntity.wrapAngle(this.wheelRotation + (float)(signedDistance / 0.25));
        }
        this.lastWheelSamplePos = currentPos;
    }

    public float getWheelRotation(float tickDelta) {
        return class_3532.method_16439((float)tickDelta, (float)this.prevWheelRotation, (float)this.wheelRotation);
    }

    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        if (player.method_5998(hand).method_31574(class_1802.field_8719)) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            class_1496 horse = this.findAttachableHorse(player);
            if (horse == null) {
                return class_1269.field_5811;
            }
            this.attachHorse(horse);
            if (!horse.method_60953() && !player.method_7337()) {
                player.method_5998(hand).method_7934(1);
            }
            return class_1269.field_21466;
        }
        if (hand == class_1268.field_5808 && player.method_5998(hand).method_7960() && ((Optional)this.field_6011.method_12789(ATTACHED_HORSE_UUID)).isPresent() && !player.method_21823()) {
            if (this.method_37908().field_9236) {
                return class_1269.field_5812;
            }
            return player.method_5804((class_1297)this) ? class_1269.field_21466 : class_1269.field_5811;
        }
        return class_1269.field_5811;
    }

    public class_1269 method_5664(class_1657 player, class_243 hitPos, class_1268 hand) {
        return this.method_5688(player, hand);
    }

    protected boolean method_5818(class_1297 passenger) {
        return this.method_5685().isEmpty();
    }

    public void method_5865(class_1297 passenger, class_1297.class_4738 positionUpdater) {
        if (!this.method_5626(passenger)) {
            return;
        }
        class_243 seatPos = this.getSeatWorldPos();
        class_243 attachment = passenger.method_55668((class_1297)this);
        positionUpdater.accept(passenger, seatPos.field_1352 - attachment.field_1352, seatPos.field_1351 - attachment.field_1351, seatPos.field_1350 - attachment.field_1350);
        passenger.method_36456(passenger.method_36454() + this.method_36454() - this.field_5982);
        passenger.method_5636(this.method_36454());
        passenger.method_5847(passenger.method_5791() + this.method_36454() - this.field_5982);
    }

    public class_243 method_24829(class_1309 passenger) {
        class_243 right = this.getSeatWorldPos().method_1019(CartEntity.horizontalRight(this.method_36454()).method_1021(1.2));
        class_243 left = this.getSeatWorldPos().method_1019(CartEntity.horizontalRight(this.method_36454()).method_1021(-1.2));
        return this.isDismountSpaceClear(passenger, left) ? left : right;
    }

    private class_1496 findAttachableHorse(class_1657 player) {
        class_238 searchBox = this.method_5829().method_1014(6.0);
        List candidates = this.method_37908().method_8390(class_1496.class, searchBox, horse -> true);
        class_1496 best = null;
        boolean bestLeashed = false;
        double bestDistance = Double.MAX_VALUE;
        for (class_1496 horse2 : candidates) {
            boolean leashedToPlayer;
            boolean bl = leashedToPlayer = horse2.method_60953() && horse2.method_60952() == player;
            if (!horse2.method_5805() || !horse2.method_6727() || !player.method_5667().equals(horse2.method_6139()) && !leashedToPlayer || horse2 instanceof PartyHorseEntity) continue;
            double distance = horse2.method_5858((class_1297)this);
            if (best != null && (!leashedToPlayer || bestLeashed) && (leashedToPlayer != bestLeashed || !(distance < bestDistance))) continue;
            best = horse2;
            bestLeashed = leashedToPlayer;
            bestDistance = distance;
        }
        return best;
    }

    private void attachHorse(class_1496 horse) {
        CartEntity existingCart = CartEntity.findCartAttachedToHorse(horse.method_5667(), this.method_37908(), this);
        if (existingCart != null) {
            existingCart.detachHorseAndDropLead();
        }
        if (horse.method_60953()) {
            horse.method_60970();
        }
        this.field_6011.method_12778(ATTACHED_HORSE_UUID, Optional.of(horse.method_5667()));
    }

    public void attachHorseEntity(class_1496 horse) {
        if (horse == null || !horse.method_5805()) {
            return;
        }
        this.attachHorse(horse);
    }

    private void detachHorse() {
        this.field_6011.method_12778(ATTACHED_HORSE_UUID, Optional.empty());
    }

    private void detachHorseAndDropLead() {
        class_1496 horse = this.getAttachedHorse();
        this.detachHorse();
        if (horse != null && horse.method_5805()) {
            horse.method_37908().method_8649((class_1297)new class_1542(horse.method_37908(), horse.method_23317(), horse.method_23318() + 0.5, horse.method_23321(), new class_1799((class_1935)class_1802.field_8719)));
        }
    }

    private void syncAttachedHorseRegistry() {
        UUID currentUuid = ((Optional)this.field_6011.method_12789(ATTACHED_HORSE_UUID)).orElse(null);
        if (this.registeredAttachedHorseUuid != null && !this.registeredAttachedHorseUuid.equals(currentUuid)) {
            ATTACHED_CART_HORSE_UUIDS.remove(this.registeredAttachedHorseUuid);
            this.registeredAttachedHorseUuid = null;
        }
        if (currentUuid != null && !currentUuid.equals(this.registeredAttachedHorseUuid)) {
            ATTACHED_CART_HORSE_UUIDS.add(currentUuid);
            this.registeredAttachedHorseUuid = currentUuid;
        }
    }

    public static boolean isAttachedCartHorse(class_1297 entity) {
        return entity instanceof class_1496 && ATTACHED_CART_HORSE_UUIDS.contains(entity.method_5667());
    }

    public static boolean detachCartForHorse(class_1496 horse) {
        CartEntity cart = CartEntity.findCartAttachedToHorse(horse.method_5667(), horse.method_37908(), null);
        if (cart == null) {
            return false;
        }
        cart.detachHorseAndDropLead();
        return true;
    }

    private static CartEntity findCartAttachedToHorse(UUID horseUuid, class_1937 world, CartEntity excludedCart) {
        class_238 searchBox = new class_238(horseUuid != null ? -3.0E7 : 0.0, (double)world.method_31607(), -3.0E7, 3.0E7, (double)world.method_31600(), 3.0E7);
        return world.method_8390(CartEntity.class, searchBox, cart -> {
            if (cart == excludedCart) return false;
            if (!((Optional)cart.field_6011.method_12789(ATTACHED_HORSE_UUID)).filter(horseUuid::equals).isPresent()) return false;
            return true;
        }).stream().findFirst().orElse(null);
    }

    private class_1496 getAttachedHorse() {
        class_1496 horse;
        Optional horseUuid = (Optional)this.field_6011.method_12789(ATTACHED_HORSE_UUID);
        if (horseUuid.isEmpty()) {
            return null;
        }
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return null;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_1297 entity = serverWorld.method_14190((UUID)horseUuid.get());
        return entity instanceof class_1496 ? (horse = (class_1496)entity) : null;
    }

    public class_1496 getAttachedHorseForRendering() {
        Optional horseUuid = (Optional)this.field_6011.method_12789(ATTACHED_HORSE_UUID);
        if (horseUuid.isEmpty()) {
            return null;
        }
        return this.method_37908().method_8390(class_1496.class, this.method_5829().method_1014(16.0), horse -> horse.method_5667().equals(horseUuid.get())).stream().findFirst().orElse(null);
    }

    public class_243 getDrawbarHitchPos(float tickDelta) {
        double x = class_3532.method_16436((double)tickDelta, (double)this.field_6014, (double)this.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)this.field_6036, (double)this.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)this.field_5969, (double)this.method_23321());
        float yaw = class_3532.method_17821((float)tickDelta, (float)this.field_5982, (float)this.method_36454());
        class_243 cartBack = CartEntity.horizontalForward(yaw);
        return new class_243(x, y, z).method_1020(cartBack.method_1021(2.15)).method_1031(0.0, 0.72, 0.0);
    }

    private class_243 getSeatWorldPos() {
        class_243 center = this.method_19538();
        float yaw = this.method_36454();
        class_243 forward = CartEntity.horizontalForward(yaw);
        class_243 right = CartEntity.horizontalRight(yaw);
        return center.method_1019(right.method_1021(CartEntity.PASSENGER_SEAT_OFFSET.field_1352)).method_1031(0.0, CartEntity.PASSENGER_SEAT_OFFSET.field_1351, 0.0).method_1019(forward.method_1021(CartEntity.PASSENGER_SEAT_OFFSET.field_1350));
    }

    private boolean isDismountSpaceClear(class_1309 passenger, class_243 pos) {
        class_238 box = passenger.method_18377(passenger.method_18376()).method_30757(pos);
        return this.method_37908().method_8587((class_1297)this, box);
    }

    private double sampleGroundHeight(class_243 samplePos) {
        return CartGroundSupport.sampleGroundHeight(this.method_37908(), this.method_23318(), samplePos);
    }

    private PlatformTransform createPlatformTransform() {
        return new PlatformTransform(this.method_19538(), class_243.field_1353, 1.0, 0.0, this.method_36454(), this.method_36454());
    }

    private static class_243 horizontalForward(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yawRadians)), 0.0, (double)class_3532.method_15362((float)yawRadians));
    }

    private static class_243 horizontalRight(float yawDegrees) {
        float yawRadians = yawDegrees * ((float)Math.PI / 180);
        return new class_243((double)class_3532.method_15362((float)yawRadians), 0.0, (double)class_3532.method_15374((float)yawRadians));
    }

    private static float yawFromBackVector(class_243 back) {
        return (float)Math.toDegrees(Math.atan2(-back.field_1352, back.field_1350));
    }

    private static float wrapAngle(float angle) {
        float tau = (float)Math.PI * 2;
        float wrapped = angle % tau;
        return wrapped < 0.0f ? wrapped + tau : wrapped;
    }

    protected void method_5749(class_2487 nbt) {
        if (nbt.method_25928("AttachedHorse")) {
            this.field_6011.method_12778(ATTACHED_HORSE_UUID, Optional.of(nbt.method_25926("AttachedHorse")));
        } else {
            this.field_6011.method_12778(ATTACHED_HORSE_UUID, Optional.empty());
        }
        this.prevWheelRotation = this.wheelRotation = nbt.method_10583("WheelRotation");
        this.setHealth(nbt.method_10545("Health") ? nbt.method_10583("Health") : 30.0f);
    }

    protected void method_5652(class_2487 nbt) {
        ((Optional)this.field_6011.method_12789(ATTACHED_HORSE_UUID)).ifPresent(uuid -> nbt.method_25927("AttachedHorse", uuid));
        nbt.method_10548("WheelRotation", this.wheelRotation);
        nbt.method_10548("Health", this.getHealth());
    }

    private float getHealth() {
        return ((Float)this.field_6011.method_12789(HEALTH)).floatValue();
    }

    private void setHealth(float health) {
        this.field_6011.method_12778(HEALTH, (Object)Float.valueOf(class_3532.method_15363((float)health, (float)0.0f, (float)30.0f)));
    }

    public void method_5650(class_1297.class_5529 reason) {
        if (this.registeredAttachedHorseUuid != null) {
            ATTACHED_CART_HORSE_UUIDS.remove(this.registeredAttachedHorseUuid);
            this.registeredAttachedHorseUuid = null;
        }
        super.method_5650(reason);
    }

    public class_4048 method_18377(class_4050 pose) {
        return this.method_5864().method_18386();
    }

    public boolean method_30949(class_1297 other) {
        return false;
    }

    public boolean method_5810() {
        return false;
    }

    public boolean method_30948() {
        return false;
    }

    public boolean method_5863() {
        return true;
    }

    public boolean method_5732() {
        return true;
    }

    public boolean method_5643(class_1282 source, float amount) {
        if (this.method_31481() || this.method_5679(source)) {
            return false;
        }
        if (this.method_37908().field_9236) {
            return true;
        }
        class_1297 class_12972 = source.method_5529();
        if (class_12972 instanceof class_1657) {
            class_1657 player = (class_1657)class_12972;
            if (player.method_31549().field_7477) {
                this.breakCart(source);
                return true;
            }
        }
        this.setHealth(this.getHealth() - amount);
        if (this.getHealth() > 0.0f) {
            return true;
        }
        this.breakCart(source);
        return true;
    }

    private void breakCart(class_1282 source) {
        this.method_5772();
        this.detachHorse();
        if (!this.isVoyageCart() && this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
            this.method_5775(new class_1799((class_1935)this.asItem()));
        }
        this.method_31472();
    }

    private boolean isVoyageCart() {
        return this.method_5752().stream().anyMatch(tag -> tag.startsWith("agob_voyage_"));
    }

    public class_1792 asItem() {
        return GotItems.CART;
    }

    @Override
    public PlatformHull agob$getPlatformHull() {
        return PLATFORM_HULL;
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
}

