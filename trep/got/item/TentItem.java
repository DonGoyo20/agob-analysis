/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1838
 *  net.minecraft.class_1937
 *  net.minecraft.class_2350
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.item;

import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1792;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2350;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.GotMod;
import trep.got.block.TentCollisionBlock;
import trep.got.entity.TentEntity;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;
import trep.got.registry.GotEntities;

public final class TentItem
extends class_1792 {
    public TentItem(class_1792.class_1793 settings) {
        super(settings);
    }

    /*
     * Unable to fully structure code
     */
    public class_1269 method_7884(class_1838 context) {
        var3_2 = context.method_8045();
        if (!(var3_2 instanceof class_3218)) {
            return class_1269.field_5812;
        }
        world = (class_3218)var3_2;
        targetPos = context.method_8037();
        if (!context.method_8045().method_8320(targetPos).method_45474()) {
            targetPos = targetPos.method_10093(context.method_8038());
        }
        if (!((player = context.method_8036()) instanceof class_3222)) ** GOTO lbl-1000
        serverPlayer = (class_3222)player;
        if (GotCastles.shouldAttemptPlayerSettlementClaim(world, targetPos)) {
            v0 = true;
        } else lbl-1000:
        // 2 sources

        {
            v0 = playerSettlement = false;
        }
        if (playerSettlement && player instanceof class_3222 && !GotCastles.canCreatePlayerSettlementClaim(world, serverPlayer = (class_3222)player, targetPos)) {
            return class_1269.field_5814;
        }
        tentBlock = (TentCollisionBlock)GotBlocks.TENT_COLLISION;
        facing = context.method_8036() == null ? class_2350.field_11043 : context.method_8036().method_5735().method_10153();
        if (!tentBlock.canPlaceStructure((class_1937)world, targetPos, facing)) {
            return class_1269.field_5814;
        }
        tentEntity = new TentEntity(GotEntities.TENT, (class_1937)world);
        tentEntity.setOriginPos(targetPos);
        tentEntity.setFacing(facing);
        center = tentBlock.centerForEntity(targetPos, facing);
        tentEntity.method_5808(center.x(), center.y(), center.z(), facing.method_10144(), 0.0f);
        spawned = world.method_8649((class_1297)tentEntity);
        GotMod.LOGGER.info("[tent-place-debug] spawned={} pos={} uuid={}", new Object[]{spawned, targetPos.method_23854(), tentEntity.method_5667()});
        if (!spawned) {
            return class_1269.field_5814;
        }
        tentBlock.placeStructure(world, targetPos, facing, tentEntity.method_5667());
        if (playerSettlement && player instanceof class_3222) {
            serverPlayer = (class_3222)player;
            GotCastles.createPlayerSettlementClaim(world, serverPlayer, targetPos);
        }
        if (context.method_8036() == null || !context.method_8036().method_7337()) {
            context.method_8041().method_7934(1);
        }
        return class_1269.field_5812;
    }
}

