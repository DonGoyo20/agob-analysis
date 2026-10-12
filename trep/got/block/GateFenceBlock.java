/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1657
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2351
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2753
 *  net.minecraft.class_2754
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1657;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2753;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import trep.got.block.GateMechanismBlock;
import trep.got.block.GateWoodType;
import trep.got.registry.GotBlocks;

public final class GateFenceBlock
extends class_2248 {
    public static final MapCodec<GateFenceBlock> CODEC = GateFenceBlock.method_54094(GateFenceBlock::new);
    public static final class_2753 FACING = class_2741.field_12481;
    public static final class_2754<GateWoodType> WOOD = class_2754.method_11850((String)"wood", GateWoodType.class);
    private static final class_265 NORTH_SOUTH_SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)7.0, (double)16.0, (double)16.0, (double)9.0);
    private static final class_265 EAST_WEST_SHAPE = class_2248.method_9541((double)7.0, (double)0.0, (double)0.0, (double)9.0, (double)16.0, (double)16.0);
    private static final class_265 FULL_COLLISION_SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0);

    public GateFenceBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043)).method_11657(WOOD, (Comparable)((Object)GateWoodType.SPRUCE)));
    }

    public MapCodec<GateFenceBlock> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING, WOOD});
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return ((class_2350)state.method_11654((class_2769)FACING)).method_10166() == class_2350.class_2351.field_11051 ? NORTH_SOUTH_SHAPE : EAST_WEST_SHAPE;
    }

    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return FULL_COLLISION_SHAPE;
    }

    public class_2680 method_9576(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
        if (!world.field_9236) {
            GateFenceBlock.breakGateColumn((class_3218)world, pos);
        }
        return super.method_9576(world, pos, state, player);
    }

    public static void breakGateColumn(class_3218 world, class_2338 pos) {
        class_2338 cursor = pos.method_10084();
        int steps = 0;
        while (steps <= 6) {
            class_2680 candidate = world.method_8320(cursor);
            if (candidate.method_26204() instanceof GateMechanismBlock) {
                GateMechanismBlock.breakFromFence(world, cursor, candidate);
                return;
            }
            if (!candidate.method_27852(GotBlocks.GATE_FENCE)) {
                return;
            }
            ++steps;
            cursor = cursor.method_10084();
        }
    }
}

