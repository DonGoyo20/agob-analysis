/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_247
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_247;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.blacksmith.BlacksmithCrafting;
import trep.got.blacksmith.BlacksmithWorkbenchAccess;
import trep.got.block.AbstractMultiBlockMachineBlock;
import trep.got.block.entity.BlacksmithTableBlockEntity;

public final class BlacksmithTableBlock
extends AbstractMultiBlockMachineBlock {
    public static final MapCodec<BlacksmithTableBlock> CODEC = BlacksmithTableBlock.method_54094(BlacksmithTableBlock::new);
    private static final class_265[][] SHAPES = BlacksmithTableBlock.createShapes();

    public BlacksmithTableBlock(class_4970.class_2251 settings) {
        super(settings, 2, 2, 1);
    }

    protected MapCodec<? extends AbstractMultiBlockMachineBlock> method_53969() {
        return CODEC;
    }

    @Override
    @Nullable
    protected class_2586 createOriginBlockEntity(class_2338 pos, class_2680 state) {
        return new BlacksmithTableBlockEntity(pos, state);
    }

    @Override
    protected class_265 getPartShape(class_2680 state, boolean collision) {
        return this.rotateShape(SHAPES[(Integer)state.method_11654((class_2769)PART_X)][(Integer)state.method_11654((class_2769)PART_Z)], (class_2350)state.method_11654((class_2769)FACING));
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

    private static class_265[][] createShapes() {
        class_265[][] shapes = new class_265[2][2];
        shapes[0][0] = BlacksmithTableBlock.join(BlacksmithTableBlock.cuboid(2.0, 8.0, 1.0, 16.0, 10.0, 14.0), BlacksmithTableBlock.cuboid(2.0, 1.0, 2.0, 5.0, 8.0, 5.0), BlacksmithTableBlock.cuboid(2.0, 2.0, 10.0, 5.0, 8.0, 13.0), BlacksmithTableBlock.cuboid(4.0, 4.0, 3.0, 16.0, 5.0, 12.0), BlacksmithTableBlock.cuboid(4.0, 10.0, 14.0, 16.0, 12.0, 16.0));
        shapes[1][0] = BlacksmithTableBlock.join(BlacksmithTableBlock.cuboid(0.0, 8.0, 1.0, 14.0, 10.0, 14.0), BlacksmithTableBlock.cuboid(11.0, 1.0, 2.0, 14.0, 8.0, 5.0), BlacksmithTableBlock.cuboid(11.0, 2.0, 10.0, 14.0, 8.0, 13.0), BlacksmithTableBlock.cuboid(0.0, 4.0, 3.0, 12.0, 5.0, 12.0), BlacksmithTableBlock.cuboid(0.0, 10.0, 14.0, 12.0, 12.0, 16.0));
        shapes[0][1] = BlacksmithTableBlock.join(BlacksmithTableBlock.cuboid(4.0, 10.0, 0.0, 16.0, 12.0, 2.0), BlacksmithTableBlock.cuboid(0.0, 2.0, 0.0, 4.0, 10.0, 16.0), BlacksmithTableBlock.cuboid(4.0, 2.0, 12.0, 16.0, 12.0, 16.0), BlacksmithTableBlock.cuboid(4.0, 2.0, 0.0, 16.0, 8.0, 2.0), BlacksmithTableBlock.cuboid(4.0, 2.0, 2.0, 16.0, 4.0, 12.0));
        shapes[1][1] = BlacksmithTableBlock.join(BlacksmithTableBlock.cuboid(0.0, 10.0, 0.0, 12.0, 12.0, 2.0), BlacksmithTableBlock.cuboid(12.0, 2.0, 0.0, 16.0, 10.0, 16.0), BlacksmithTableBlock.cuboid(0.0, 2.0, 12.0, 12.0, 12.0, 16.0), BlacksmithTableBlock.cuboid(0.0, 2.0, 0.0, 12.0, 8.0, 2.0), BlacksmithTableBlock.cuboid(0.0, 2.0, 2.0, 12.0, 4.0, 12.0));
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

