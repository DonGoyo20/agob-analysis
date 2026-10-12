/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2746
 *  net.minecraft.class_2753
 *  net.minecraft.class_2754
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_5819
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
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
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2754;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import net.minecraft.class_5819;
import org.jetbrains.annotations.Nullable;
import trep.got.block.GateFenceBlock;
import trep.got.block.GateLocator;
import trep.got.block.GateWoodType;
import trep.got.item.GateMechanismItem;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotCastles;

public final class GateMechanismBlock
extends class_2248 {
    public static final MapCodec<GateMechanismBlock> CODEC = GateMechanismBlock.method_54094(GateMechanismBlock::new);
    public static final int MAX_EXTENSION = 6;
    public static final class_2753 FACING = class_2741.field_12481;
    public static final class_2746 POWERED = class_2741.field_12484;
    public static final class_2746 BROKEN = class_2746.method_11825((String)"broken");
    public static final class_2746 INVASION_LOCKED = class_2746.method_11825((String)"invasion_locked");
    public static final class_2746 STORED_POWERED = class_2746.method_11825((String)"stored_powered");
    public static final class_2758 EXTENSION = class_2758.method_11867((String)"extension", (int)0, (int)6);
    public static final class_2754<GateWoodType> WOOD = class_2754.method_11850((String)"wood", GateWoodType.class);
    private static final class_265 SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0);

    public GateMechanismBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043)).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)BROKEN, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)INVASION_LOCKED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)STORED_POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0))).method_11657(WOOD, (Comparable)((Object)GateWoodType.SPRUCE)));
    }

    public MapCodec<GateMechanismBlock> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING, POWERED, BROKEN, INVASION_LOCKED, STORED_POWERED, EXTENSION, WOOD});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)ctx.method_8042().method_10153())).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)BROKEN, (Comparable)Boolean.valueOf(GateMechanismItem.isBroken(ctx.method_8041())))).method_11657((class_2769)INVASION_LOCKED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)STORED_POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0))).method_11657(WOOD, (Comparable)((Object)GateMechanismItem.getWoodType(ctx.method_8041())));
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        if (!world.field_9236) {
            GateLocator.track((class_3218)world, pos);
            if (!((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue()) {
                GateMechanismBlock.updateLinePower((class_3218)world, pos, state);
            }
        }
    }

    protected void method_9612(class_2680 state, class_1937 world, class_2338 pos, class_2248 sourceBlock, class_2338 sourcePos, boolean notify) {
        super.method_9612(state, world, pos, sourceBlock, sourcePos, notify);
        if (!(world.field_9236 || ((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue() || ((Boolean)state.method_11654((class_2769)INVASION_LOCKED)).booleanValue())) {
            GateMechanismBlock.updateLinePower((class_3218)world, pos, state);
        }
    }

    protected void method_9588(class_2680 state, class_3218 world, class_2338 pos, class_5819 random) {
        int actualDepth;
        if (!(world.method_8320(pos).method_26204() instanceof GateMechanismBlock)) {
            return;
        }
        class_2680 liveState = world.method_8320(pos);
        if (((Boolean)liveState.method_11654((class_2769)BROKEN)).booleanValue()) {
            if ((Integer)liveState.method_11654((class_2769)EXTENSION) != 0) {
                world.method_8652(pos, (class_2680)((class_2680)liveState.method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0))).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false)), 2);
            }
            return;
        }
        if (((Boolean)liveState.method_11654((class_2769)INVASION_LOCKED)).booleanValue() && !((Boolean)liveState.method_11654((class_2769)POWERED)).booleanValue()) {
            liveState = (class_2680)liveState.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(true));
            world.method_8652(pos, liveState, 2);
        }
        if ((actualDepth = GateMechanismBlock.getActualDepth(world, pos, liveState)) != (Integer)liveState.method_11654((class_2769)EXTENSION)) {
            liveState = (class_2680)liveState.method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(actualDepth));
            world.method_8652(pos, liveState, 2);
        }
        if (((Boolean)liveState.method_11654((class_2769)POWERED)).booleanValue()) {
            if (actualDepth >= 6) {
                return;
            }
            class_2338 nextPos = pos.method_10087(actualDepth + 1);
            if (!world.method_8320(nextPos).method_26215()) {
                return;
            }
            class_2680 fenceState = (class_2680)((class_2680)GotBlocks.GATE_FENCE.method_9564().method_11657((class_2769)GateFenceBlock.FACING, (Comparable)((class_2350)liveState.method_11654((class_2769)FACING)))).method_11657(GateFenceBlock.WOOD, (Comparable)((Object)((GateWoodType)((Object)liveState.method_11654(WOOD)))));
            world.method_8652(nextPos, fenceState, 3);
            class_2680 updated = (class_2680)world.method_8320(pos).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(actualDepth + 1));
            world.method_8652(pos, updated, 2);
            world.method_39279(pos, (class_2248)this, 2);
            return;
        }
        if (actualDepth <= 0) {
            return;
        }
        class_2338 lastFence = pos.method_10087(actualDepth);
        if (world.method_8320(lastFence).method_27852(GotBlocks.GATE_FENCE)) {
            world.method_8650(lastFence, false);
        }
        class_2680 updated = (class_2680)world.method_8320(pos).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(Math.max(0, actualDepth - 1)));
        world.method_8652(pos, updated, 2);
        if (actualDepth - 1 > 0) {
            world.method_39279(pos, (class_2248)this, 2);
        }
    }

    public class_2680 method_9576(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
        if (!world.field_9236) {
            GateMechanismBlock.clearFencesBelow((class_3218)world, pos, state);
            if (!player.method_7337()) {
                class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)GateMechanismItem.createStack((GateWoodType)((Object)state.method_11654(WOOD)), (Boolean)state.method_11654((class_2769)BROKEN)));
            }
        }
        return super.method_9576(world, pos, state, player);
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        if (!state.method_27852(newState.method_26204()) && !world.field_9236) {
            GateMechanismBlock.clearFencesBelow((class_3218)world, pos, state);
            GateLocator.untrack((class_3218)world, pos);
        }
        super.method_9536(state, world, pos, newState, moved);
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    static void syncDepthAndSchedule(class_3218 world, class_2338 pos, class_2680 state) {
        class_2680 liveState = world.method_8320(pos);
        if (!(liveState.method_26204() instanceof GateMechanismBlock)) {
            return;
        }
        if (((Boolean)liveState.method_11654((class_2769)BROKEN)).booleanValue()) {
            if (((Boolean)liveState.method_11654((class_2769)POWERED)).booleanValue() || (Integer)liveState.method_11654((class_2769)EXTENSION) != 0) {
                world.method_8652(pos, (class_2680)((class_2680)liveState.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0)), 2);
            }
            return;
        }
        int depth = GateMechanismBlock.getActualDepth(world, pos, liveState);
        if (depth != (Integer)liveState.method_11654((class_2769)EXTENSION)) {
            liveState = (class_2680)liveState.method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(depth));
            world.method_8652(pos, liveState, 2);
        }
        if (((Boolean)liveState.method_11654((class_2769)POWERED)).booleanValue() && depth < 6 && world.method_8320(pos.method_10087(depth + 1)).method_26215()) {
            world.method_39279(pos, liveState.method_26204(), 2);
        }
    }

    private static void updateLinePower(class_3218 world, class_2338 pos, class_2680 state) {
        if (((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue()) {
            class_2680 reset = (class_2680)((class_2680)state.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0));
            if (reset != state) {
                world.method_8652(pos, reset, 2);
            }
            return;
        }
        if (((Boolean)state.method_11654((class_2769)INVASION_LOCKED)).booleanValue()) {
            if (!((Boolean)state.method_11654((class_2769)POWERED)).booleanValue()) {
                world.method_8652(pos, (class_2680)state.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(true)), 2);
            }
            return;
        }
        List<class_2338> line = GateMechanismBlock.getConnectedLine(world, pos, state);
        boolean shouldPower = false;
        for (class_2338 linePos : line) {
            if (!world.method_49803(linePos)) continue;
            shouldPower = true;
            break;
        }
        for (class_2338 linePos : line) {
            class_2680 lineState = world.method_8320(linePos);
            if (!(lineState.method_26204() instanceof GateMechanismBlock)) continue;
            if (((Boolean)lineState.method_11654((class_2769)BROKEN)).booleanValue()) {
                if (!((Boolean)lineState.method_11654((class_2769)POWERED)).booleanValue() && (Integer)lineState.method_11654((class_2769)EXTENSION) == 0) continue;
                world.method_8652(linePos, (class_2680)((class_2680)lineState.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0)), 2);
                continue;
            }
            GateMechanismBlock.syncDepthAndSchedule(world, linePos, lineState);
            if ((Boolean)lineState.method_11654((class_2769)POWERED) == shouldPower) continue;
            class_2680 updated = (class_2680)lineState.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(shouldPower));
            world.method_8652(linePos, updated, 2);
            world.method_39279(linePos, updated.method_26204(), 2);
        }
    }

    private static List<class_2338> getConnectedLine(class_3218 world, class_2338 origin, class_2680 originState) {
        ArrayList<class_2338> line = new ArrayList<class_2338>();
        class_2350 axis = ((class_2350)originState.method_11654((class_2769)FACING)).method_10170();
        class_2338 start = origin;
        while (GateMechanismBlock.isConnectedGate(world, start.method_10093(axis.method_10153()), originState)) {
            start = start.method_10093(axis.method_10153());
        }
        class_2338 cursor = start;
        while (GateMechanismBlock.isConnectedGate(world, cursor, originState)) {
            line.add(cursor);
            cursor = cursor.method_10093(axis);
        }
        return line;
    }

    private static boolean isConnectedGate(class_3218 world, class_2338 pos, class_2680 originState) {
        class_2680 state = world.method_8320(pos);
        return state.method_26204() instanceof GateMechanismBlock && (Boolean)state.method_11654((class_2769)BROKEN) == false && state.method_11654((class_2769)FACING) == originState.method_11654((class_2769)FACING);
    }

    private static int getActualDepth(class_3218 world, class_2338 pos, class_2680 mechanismState) {
        class_2680 state;
        int depth = 0;
        for (int i = 1; i <= 6 && (state = world.method_8320(pos.method_10087(i))).method_27852(GotBlocks.GATE_FENCE) && state.method_11654((class_2769)GateFenceBlock.FACING) == mechanismState.method_11654((class_2769)FACING) && state.method_11654(GateFenceBlock.WOOD) == mechanismState.method_11654(WOOD); ++i) {
            ++depth;
        }
        return depth;
    }

    private static void clearFencesBelow(class_3218 world, class_2338 pos, class_2680 mechanismState) {
        class_2338 fencePos;
        class_2680 fenceState;
        for (int i = 1; i <= 6 && (fenceState = world.method_8320(fencePos = pos.method_10087(i))).method_27852(GotBlocks.GATE_FENCE) && fenceState.method_11654((class_2769)GateFenceBlock.FACING) == mechanismState.method_11654((class_2769)FACING); ++i) {
            world.method_8650(fencePos, false);
        }
    }

    public static void breakFromFence(class_3218 world, class_2338 pos, class_2680 mechanismState) {
        GateMechanismBlock.clearFencesBelow(world, pos, mechanismState);
        class_2680 brokenState = (class_2680)((class_2680)((class_2680)mechanismState.method_11657((class_2769)BROKEN, (Comparable)Boolean.valueOf(true))).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0));
        world.method_8652(pos, brokenState, 3);
    }

    public static void forceOpenForInvasion(class_3218 world, class_2338 pos, class_2680 state) {
        if (!(state.method_26204() instanceof GateMechanismBlock) || ((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue() || ((Boolean)state.method_11654((class_2769)INVASION_LOCKED)).booleanValue()) {
            return;
        }
        class_2680 updated = (class_2680)((class_2680)((class_2680)state.method_11657((class_2769)STORED_POWERED, (Comparable)((Boolean)state.method_11654((class_2769)POWERED)))).method_11657((class_2769)INVASION_LOCKED, (Comparable)Boolean.valueOf(true))).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(true));
        world.method_8652(pos, updated, 3);
        world.method_39279(pos, updated.method_26204(), 1);
    }

    public static void restoreAfterInvasion(class_3218 world, class_2338 pos, class_2680 state) {
        if (!(state.method_26204() instanceof GateMechanismBlock) || !((Boolean)state.method_11654((class_2769)INVASION_LOCKED)).booleanValue()) {
            return;
        }
        class_2680 updated = (class_2680)((class_2680)((class_2680)state.method_11657((class_2769)INVASION_LOCKED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)POWERED, (Comparable)((Boolean)state.method_11654((class_2769)STORED_POWERED)))).method_11657((class_2769)STORED_POWERED, (Comparable)Boolean.valueOf(false));
        world.method_8652(pos, updated, 3);
        GateMechanismBlock.syncDepthAndSchedule(world, pos, updated);
    }

    public static void repairBrokenGate(class_3218 world, class_2338 pos, class_2680 state) {
        if (!(state.method_26204() instanceof GateMechanismBlock) || !((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue()) {
            return;
        }
        class_2680 repaired = (class_2680)((class_2680)((class_2680)((class_2680)((class_2680)state.method_11657((class_2769)BROKEN, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)INVASION_LOCKED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)STORED_POWERED, (Comparable)Boolean.valueOf(false))).method_11657((class_2769)EXTENSION, (Comparable)Integer.valueOf(0));
        world.method_8652(pos, repaired, 3);
        GateMechanismBlock.reconcileInvasionState(world, pos, repaired);
        if (!((Boolean)world.method_8320(pos).method_11654((class_2769)INVASION_LOCKED)).booleanValue()) {
            GateMechanismBlock.syncDepthAndSchedule(world, pos, world.method_8320(pos));
        }
    }

    public static void reconcileInvasionState(class_3218 world, class_2338 pos, class_2680 state) {
        if (!(state.method_26204() instanceof GateMechanismBlock) || ((Boolean)state.method_11654((class_2769)BROKEN)).booleanValue()) {
            return;
        }
        boolean shouldBeLocked = GotCastles.shouldGateStayInvasionLocked(world, pos);
        if (((Boolean)state.method_11654((class_2769)INVASION_LOCKED)).booleanValue()) {
            if (shouldBeLocked) {
                if (!((Boolean)state.method_11654((class_2769)POWERED)).booleanValue()) {
                    class_2680 updated = (class_2680)state.method_11657((class_2769)POWERED, (Comparable)Boolean.valueOf(true));
                    world.method_8652(pos, updated, 3);
                    world.method_39279(pos, updated.method_26204(), 1);
                }
            } else {
                GateMechanismBlock.restoreAfterInvasion(world, pos, state);
            }
            return;
        }
        if (shouldBeLocked) {
            GateMechanismBlock.forceOpenForInvasion(world, pos, state);
        }
    }

    public static boolean isPassable(class_2680 state) {
        return state.method_26204() instanceof GateMechanismBlock && ((Boolean)state.method_11654((class_2769)BROKEN) != false || (Integer)state.method_11654((class_2769)EXTENSION) <= 0);
    }
}

