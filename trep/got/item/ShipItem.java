/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1271
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1301
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_3468
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3965
 *  net.minecraft.class_5712
 *  net.minecraft.class_6880
 */
package trep.got.item;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1301;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_3468;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5712;
import net.minecraft.class_6880;
import trep.got.entity.ShipEntity;
import trep.got.entity.VesselMaterialProfile;
import trep.got.entity.VesselVariant;
import trep.got.registry.GotEntities;

public final class ShipItem
extends class_1792 {
    private static final Predicate<class_1297> RIDERS = class_1301.field_6155.and(class_1297::method_5863);
    private static final class_2583 TYPE_STYLE = class_2583.field_24360.method_10977(class_124.field_1063).method_10978(Boolean.valueOf(false));
    private static final class_2583 VALUE_STYLE = class_2583.field_24360.method_10977(class_124.field_1068).method_10978(Boolean.valueOf(false));
    private final class_1299<ShipEntity> entityType;

    public ShipItem(class_1299<ShipEntity> entityType, class_1792.class_1793 settings) {
        super(settings);
        this.entityType = entityType;
    }

    public VesselVariant getVesselVariant() {
        if (this.entityType == GotEntities.GALLEY) {
            return VesselVariant.GALLEY;
        }
        if (this.entityType == GotEntities.LONGSHIP) {
            return VesselVariant.LONGSHIP;
        }
        return VesselVariant.SHIP;
    }

    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        class_1799 stack = user.method_5998(hand);
        class_3965 hitResult = ShipItem.method_7872((class_1937)world, (class_1657)user, (class_3959.class_242)class_3959.class_242.field_1347);
        if (hitResult.method_17783() == class_239.class_240.field_1333) {
            return class_1271.method_22430((Object)stack);
        }
        class_243 rotation = user.method_5828(1.0f);
        List blockers = world.method_8333((class_1297)user, user.method_5829().method_18804(rotation.method_1021(5.0)).method_1014(1.0), RIDERS);
        if (!blockers.isEmpty()) {
            class_243 eyePos = user.method_33571();
            for (class_1297 blocker : blockers) {
                class_238 box = blocker.method_5829().method_1014((double)blocker.method_5871());
                if (!box.method_1006(eyePos)) continue;
                return class_1271.method_22430((Object)stack);
            }
        }
        if (hitResult.method_17783() != class_239.class_240.field_1332) {
            return class_1271.method_22430((Object)stack);
        }
        class_243 hitPos = hitResult.method_17784();
        ShipEntity ship = new ShipEntity(this.entityType, world);
        ship.method_5808(hitPos.field_1352, hitPos.field_1351, hitPos.field_1350, user.method_36454(), 0.0f);
        ship.method_36456(user.method_36454());
        ship.field_5982 = ship.method_36454();
        ship.applyMaterialProfile(VesselMaterialProfile.fromStack(stack));
        ship.resetHullHp();
        if (!world.method_8587((class_1297)ship, ship.method_5829())) {
            return class_1271.method_22431((Object)stack);
        }
        if (!world.field_9236) {
            world.method_8649((class_1297)ship);
            world.method_43275((class_1297)user, (class_6880)class_5712.field_28738, hitPos);
            stack.method_57008(1, (class_1309)user);
        }
        user.method_7259(class_3468.field_15372.method_14956((Object)this));
        return class_1271.method_29237((Object)stack, (boolean)world.method_8608());
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        VesselMaterialProfile profile = VesselMaterialProfile.fromStack(stack);
        tooltip.add((class_2561)class_2561.method_43470((String)"Type:").method_10862(TYPE_STYLE));
        tooltip.add((class_2561)class_2561.method_43470((String)("- " + profile.plankWoodLabel())).method_10862(VALUE_STYLE));
        tooltip.add((class_2561)class_2561.method_43470((String)("- " + profile.logWoodLabel())).method_10862(VALUE_STYLE));
        tooltip.add((class_2561)class_2561.method_43470((String)("- " + profile.woolLabel())).method_10862(VALUE_STYLE));
    }
}

