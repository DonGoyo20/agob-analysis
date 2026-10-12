/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_247
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_2591
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3726
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5558
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_247;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import net.minecraft.class_5558;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.AbstractMultiBlockMachineBlock;
import trep.got.block.entity.BlacksmithFurnaceBlockEntity;
import trep.got.registry.GotBlockEntities;

public final class BlacksmithFurnaceBlock
extends AbstractMultiBlockMachineBlock {
    public static final MapCodec<BlacksmithFurnaceBlock> CODEC = BlacksmithFurnaceBlock.method_54094(BlacksmithFurnaceBlock::new);
    private static final class_265[][][] SHAPES = BlacksmithFurnaceBlock.createShapes();

    public BlacksmithFurnaceBlock(class_4970.class_2251 settings) {
        super(settings, 2, 2, 2);
    }

    protected MapCodec<? extends AbstractMultiBlockMachineBlock> method_53969() {
        return CODEC;
    }

    @Override
    @Nullable
    protected class_2586 createOriginBlockEntity(class_2338 pos, class_2680 state) {
        return new BlacksmithFurnaceBlockEntity(pos, state);
    }

    @Override
    protected class_265 getPartShape(class_2680 state, boolean collision) {
        return this.rotateShape(SHAPES[(Integer)state.method_11654((class_2769)PART_X)][(Integer)state.method_11654((class_2769)PART_Z)][(Integer)state.method_11654((class_2769)PART_Y)], (class_2350)state.method_11654((class_2769)FACING));
    }

    @Override
    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return class_259.method_1077();
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (BlacksmithCrafting.tryHandleHammerInteraction(player, world, pos, state)) {
            return class_1269.field_5812;
        }
        return BlacksmithWorkbenchAccess.tryOpen(world, pos, state, player);
    }

    @Override
    public class_2680 method_9576(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
        BlacksmithWorkbenchAccess.cancelActiveSession(world, pos, state);
        return super.method_9576(world, pos, state, player);
    }

    @Nullable
    public <T extends class_2586> class_5558<T> method_31645(class_1937 world, class_2680 state, class_2591<T> type) {
        return type == GotBlockEntities.BLACKSMITH_FURNACE ? (tickerWorld, tickerPos, tickerState, blockEntity) -> {
            BlacksmithFurnaceBlockEntity furnace;
            class_3218 serverWorld;
            block5: {
                block4: {
                    if (!(tickerWorld instanceof class_3218)) break block4;
                    serverWorld = (class_3218)tickerWorld;
                    if (blockEntity instanceof BlacksmithFurnaceBlockEntity && (furnace = (BlacksmithFurnaceBlockEntity)blockEntity).getSession() != null && furnace.getPair() != null) break block5;
                }
                return;
            }
            class_3222 smith = serverWorld.method_8503().method_3760().method_14602(furnace.getSession().smithUuid());
            if (smith == null || !BlacksmithWorkbenchAccess.isStillValid((class_1937)serverWorld, furnace.getPair(), (class_1657)smith)) {
                BlacksmithCrafting.cancelSession(serverWorld, furnace.getPair());
            }
        } : null;
    }

    private static class_265[][][] createShapes() {
        class_265[][][] shapes = new class_265[2][2][2];
        shapes[0][0][0] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(10.0, 2.0, 0.0, 16.0, 14.0, 3.0), BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 0.0, 4.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 12.0, 0.0, 4.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 12.0, 0.0, 16.0, 16.0, 4.0), BlacksmithFurnaceBlock.cuboid(4.0, 2.0, 0.0, 16.0, 12.0, 4.0), BlacksmithFurnaceBlock.cuboid(15.0, 2.0, 4.0, 16.0, 4.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 2.0, 4.0, 15.0, 4.0, 16.0));
        shapes[1][0][0] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 0.0, 6.0, 14.0, 3.0), BlacksmithFurnaceBlock.cuboid(12.0, 2.0, 0.0, 16.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 12.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 12.0, 0.0, 12.0, 16.0, 4.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 0.0, 12.0, 12.0, 4.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 4.0, 12.0, 4.0, 16.0));
        shapes[0][1][0] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 0.0, 4.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 12.0, 0.0, 4.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 2.0, 12.0, 16.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 12.0, 12.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(15.0, 2.0, 0.0, 16.0, 4.0, 12.0), BlacksmithFurnaceBlock.cuboid(4.0, 2.0, 0.0, 15.0, 4.0, 12.0));
        shapes[1][1][0] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 2.0, 12.0, 16.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 12.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 12.0, 12.0, 12.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 12.0, 12.0, 12.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 2.0, 0.0, 12.0, 4.0, 12.0));
        shapes[0][0][1] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 14.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 4.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 0.0, 0.0, 16.0, 6.0, 4.0), BlacksmithFurnaceBlock.cuboid(0.0, 6.0, 0.0, 4.0, 14.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 6.0, 0.0, 16.0, 14.0, 4.0));
        shapes[1][0][1] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 14.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 0.0, 0.0, 16.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 12.0, 6.0, 4.0), BlacksmithFurnaceBlock.cuboid(12.0, 6.0, 0.0, 16.0, 14.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 6.0, 0.0, 12.0, 14.0, 4.0));
        shapes[0][1][1] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 14.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 0.0, 4.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 0.0, 12.0, 16.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 6.0, 0.0, 4.0, 14.0, 16.0), BlacksmithFurnaceBlock.cuboid(4.0, 6.0, 12.0, 16.0, 14.0, 16.0));
        shapes[1][1][1] = BlacksmithFurnaceBlock.join(BlacksmithFurnaceBlock.cuboid(0.0, 14.0, 0.0, 16.0, 16.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 0.0, 0.0, 16.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 0.0, 12.0, 12.0, 6.0, 16.0), BlacksmithFurnaceBlock.cuboid(12.0, 6.0, 0.0, 16.0, 14.0, 16.0), BlacksmithFurnaceBlock.cuboid(0.0, 6.0, 12.0, 12.0, 14.0, 16.0));
        return shapes;
    }

    private static class_265 cuboid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return class_2248.method_9541((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
    }

    private static class_265 join(class_265 ... shapes) {
        class_265 result = class_259.method_1073();
        for (class_265 shape : shapes) {
            result = class_259.method_1082((class_265)result, (class_265)shape, (class_247)class_247.field_1366);
        }
        return result.method_1097();
    }
}

