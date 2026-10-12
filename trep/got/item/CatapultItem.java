/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1271
 *  net.minecraft.class_1297
 *  net.minecraft.class_1301
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3468
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3965
 *  net.minecraft.class_5712
 *  net.minecraft.class_6880
 */
package trep.got.item;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1297;
import net.minecraft.class_1301;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3468;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_5712;
import net.minecraft.class_6880;
import trep.got.entity.CatapultEntity;
import trep.got.registry.GotCastles;

public final class CatapultItem
extends class_1792 {
    private static final Predicate<class_1297> BLOCKERS = class_1301.field_6155.and(class_1297::method_5863);

    public CatapultItem(class_1792.class_1793 settings) {
        super(settings);
    }

    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        class_1799 stack = user.method_5998(hand);
        class_3965 hitResult = CatapultItem.method_7872((class_1937)world, (class_1657)user, (class_3959.class_242)class_3959.class_242.field_1348);
        if (hitResult.method_17783() != class_239.class_240.field_1332) {
            return class_1271.method_22430((Object)stack);
        }
        class_243 rotation = user.method_5828(1.0f);
        List blockers = world.method_8333((class_1297)user, user.method_5829().method_18804(rotation.method_1021(5.0)).method_1014(1.0), BLOCKERS);
        class_243 eyePos = user.method_33571();
        for (class_1297 blocker : blockers) {
            if (!blocker.method_5829().method_1014((double)blocker.method_5871()).method_1006(eyePos)) continue;
            return class_1271.method_22430((Object)stack);
        }
        class_2338 spawnPos = hitResult.method_17777().method_10084();
        if (!world.field_9236 && user instanceof class_3222) {
            class_3218 serverWorld;
            class_3222 serverPlayer = (class_3222)user;
            if (world instanceof class_3218 && !GotCastles.canPlaceVehicleInCastleClaim(serverPlayer, serverWorld = (class_3218)world, spawnPos)) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"You cannot place this vehicle inside a castle claim you do not own."), true);
                return class_1271.method_22431((Object)stack);
            }
        }
        CatapultEntity catapult = new CatapultEntity(world, (double)spawnPos.method_10263() + 0.5, spawnPos.method_10264(), (double)spawnPos.method_10260() + 0.5);
        catapult.method_36456(user.method_36454());
        catapult.field_5982 = catapult.method_36454();
        if (!world.method_8587((class_1297)catapult, catapult.method_5829())) {
            return class_1271.method_22431((Object)stack);
        }
        if (!world.field_9236) {
            world.method_8649((class_1297)catapult);
            world.method_43275((class_1297)user, (class_6880)class_5712.field_28738, catapult.method_19538());
            stack.method_57008(1, (class_1309)user);
        }
        user.method_7259(class_3468.field_15372.method_14956((Object)this));
        return class_1271.method_29237((Object)stack, (boolean)world.method_8608());
    }
}

