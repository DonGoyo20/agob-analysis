/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1675
 *  net.minecraft.class_1676
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2246
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2487
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_7923
 */
package trep.got.entity;

import java.util.HashSet;
import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1675;
import net.minecraft.class_1676;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_7923;
import trep.got.block.GateFenceBlock;
import trep.got.block.GateMechanismBlock;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotEntities;

public final class CatapultMunitionEntity
extends class_1676 {
    private static final float GRAVITY = 0.045f;
    private static final float DRAG = 0.99f;
    private String blockId = class_7923.field_41175.method_10221((Object)class_2246.field_10340).toString();

    public CatapultMunitionEntity(class_1299<? extends CatapultMunitionEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public CatapultMunitionEntity(class_1937 world, class_1297 owner, class_2248 block) {
        this(GotEntities.CATAPULT_MUNITION, world);
        this.method_7432(owner);
        this.setMunitionBlock(block);
    }

    public void method_5773() {
        super.method_5773();
        class_243 velocity = this.method_18798();
        class_239 hitResult = class_1675.method_49997((class_1297)this, x$0 -> this.method_26958((class_1297)x$0));
        if (hitResult.method_17783() == class_239.class_240.field_1331) {
            this.method_7454((class_3966)hitResult);
            return;
        }
        if (hitResult.method_17783() == class_239.class_240.field_1332) {
            this.method_24920((class_3965)hitResult);
            return;
        }
        class_243 nextPos = this.method_19538().method_1019(velocity);
        this.method_33574(nextPos);
        this.method_5852();
        class_243 nextVelocity = velocity.method_1021((double)0.99f).method_1031(0.0, (double)-0.045f, 0.0);
        this.method_18799(nextVelocity);
        this.updateTrackedRotation(nextVelocity);
    }

    private void updateTrackedRotation(class_243 velocity) {
        double horizontal = Math.sqrt(velocity.field_1352 * velocity.field_1352 + velocity.field_1350 * velocity.field_1350);
        if (horizontal < 1.0E-6 && Math.abs(velocity.field_1351) < 1.0E-6) {
            return;
        }
        this.method_36456((float)(class_3532.method_15349((double)velocity.field_1352, (double)velocity.field_1350) * 57.2957763671875));
        this.method_36457((float)(class_3532.method_15349((double)velocity.field_1351, (double)horizontal) * 57.2957763671875));
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
    }

    protected void method_24920(class_3965 blockHitResult) {
        class_1937 class_19372 = this.method_37908();
        if (!(class_19372 instanceof class_3218)) {
            return;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_2338 impactPos = blockHitResult.method_17777();
        class_2680 hitState = serverWorld.method_8320(impactPos);
        if (hitState.method_27852(GotBlocks.GATE_FENCE)) {
            this.destroyGateFenceImpactArea(serverWorld, impactPos, hitState);
        }
        this.method_5775(new class_1799((class_1935)this.getMunitionBlock()));
        this.method_31472();
    }

    protected void method_7454(class_3966 entityHitResult) {
        if (this.method_37908() instanceof class_3218) {
            entityHitResult.method_17782().method_5643(this.method_48923().method_48811((class_1297)this, this.method_24921()), 14.0f);
            this.method_5775(new class_1799((class_1935)this.getMunitionBlock()));
        }
        this.method_31472();
    }

    private void destroyGateFenceImpactArea(class_3218 world, class_2338 centerPos, class_2680 centerState) {
        class_2350 facing = centerState.method_28500((class_2769)GateFenceBlock.FACING).orElse(class_2350.field_11043);
        class_2350.class_2351 axis = facing.method_10166();
        HashSet linkedMechanisms = new HashSet();
        for (int verticalOffset = -1; verticalOffset <= 1; ++verticalOffset) {
            for (int horizontalOffset = -1; horizontalOffset <= 1; ++horizontalOffset) {
                class_2338 targetPos = axis == class_2350.class_2351.field_11051 ? centerPos.method_10069(horizontalOffset, verticalOffset, 0) : centerPos.method_10069(0, verticalOffset, horizontalOffset);
                class_2680 targetState = world.method_8320(targetPos);
                if (!targetState.method_27852(GotBlocks.GATE_FENCE)) continue;
                this.findLinkedMechanism(world, targetPos, targetState).ifPresent(linkedMechanisms::add);
                world.method_8652(targetPos, class_2246.field_10124.method_9564(), 3);
                world.method_20290(2001, targetPos, class_2248.method_9507((class_2680)targetState));
            }
        }
        for (class_2338 mechanismPos : linkedMechanisms) {
            class_2680 mechanismState = world.method_8320(mechanismPos);
            if (!(mechanismState.method_26204() instanceof GateMechanismBlock)) continue;
            GateMechanismBlock.breakFromFence(world, mechanismPos, mechanismState);
        }
    }

    private Optional<class_2338> findLinkedMechanism(class_3218 world, class_2338 fencePos, class_2680 fenceState) {
        class_2350 facing = fenceState.method_28500((class_2769)GateFenceBlock.FACING).orElse(class_2350.field_11043);
        class_2338 cursor = fencePos.method_10084();
        int steps = 0;
        while (steps <= 6) {
            class_2680 candidate = world.method_8320(cursor);
            class_2248 class_22482 = candidate.method_26204();
            if (class_22482 instanceof GateMechanismBlock) {
                GateMechanismBlock mechanismBlockState = (GateMechanismBlock)class_22482;
                if (candidate.method_11654((class_2769)GateMechanismBlock.FACING) == facing) {
                    return Optional.of(cursor.method_10062());
                }
            }
            if (!candidate.method_27852(GotBlocks.GATE_FENCE) || candidate.method_28500((class_2769)GateFenceBlock.FACING).orElse(facing) != facing) {
                return Optional.empty();
            }
            ++steps;
            cursor = cursor.method_10084();
        }
        return Optional.empty();
    }

    protected void method_5693(class_2945.class_9222 builder) {
    }

    public void setMunitionBlock(class_2248 block) {
        this.blockId = class_7923.field_41175.method_10221((Object)block).toString();
    }

    public class_2248 getMunitionBlock() {
        return class_7923.field_41175.method_17966(class_2960.method_12829((String)this.blockId)).orElse(class_2246.field_10340);
    }

    protected void method_5749(class_2487 nbt) {
        if (nbt.method_10545("MunitionBlock")) {
            this.blockId = nbt.method_10558("MunitionBlock");
        }
    }

    protected void method_5652(class_2487 nbt) {
        nbt.method_10582("MunitionBlock", this.blockId);
    }
}

