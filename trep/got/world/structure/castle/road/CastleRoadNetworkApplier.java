/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2350
 *  net.minecraft.class_2680
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3481
 */
package trep.got.world.structure.castle.road;

import java.util.Collection;
import java.util.List;
import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3481;
import trep.got.world.structure.apply.StructureBlockEditor;
import trep.got.world.structure.apply.WorldStructureBlockEditors;
import trep.got.world.structure.castle.road.CastleRoadCell;
import trep.got.world.structure.castle.road.CastleRoadNetworkPlan;

public final class CastleRoadNetworkApplier {
    private static final int BLOCK_FLAGS = 48;

    private CastleRoadNetworkApplier() {
    }

    public static void applyFull(class_3218 world, CastleRoadNetworkPlan plan, class_2338 seedAnchor) {
        if (world != null) {
            CastleRoadNetworkApplier.apply(WorldStructureBlockEditors.forWorld(world), null, plan, seedAnchor);
        }
    }

    public static void applyChunk(class_3233 region, class_1923 chunkPos, CastleRoadNetworkPlan plan, class_2338 seedAnchor) {
        if (region != null && chunkPos != null) {
            CastleRoadNetworkApplier.apply(WorldStructureBlockEditors.forRegion(region, chunkPos), chunkPos, plan, seedAnchor);
        }
    }

    public static void applyCell(class_3218 world, CastleRoadCell cell, class_2338 seedAnchor) {
        if (world != null && cell != null) {
            CastleRoadNetworkApplier.applyCell(WorldStructureBlockEditors.forWorld(world), cell, seedAnchor);
        }
    }

    private static void apply(StructureBlockEditor editor, class_1923 chunkPos, CastleRoadNetworkPlan plan, class_2338 seedAnchor) {
        if (editor == null || plan == null || plan.version() != 2) {
            return;
        }
        List<CastleRoadCell> cells = chunkPos == null ? plan.cellsByChunk().values().stream().flatMap(Collection::stream).toList() : plan.cellsForChunk(chunkPos);
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (CastleRoadCell cell : cells) {
            CastleRoadNetworkApplier.applyCell(editor, cell, seedAnchor, mutable);
        }
    }

    private static void applyCell(StructureBlockEditor editor, CastleRoadCell cell, class_2338 seedAnchor) {
        CastleRoadNetworkApplier.applyCell(editor, cell, seedAnchor, new class_2338.class_2339());
    }

    private static void applyCell(StructureBlockEditor editor, CastleRoadCell cell, class_2338 seedAnchor, class_2338.class_2339 mutable) {
        class_2680 aboveState;
        mutable.method_10103(cell.x(), cell.y(), cell.z());
        if (!editor.canEdit((class_2338)mutable) || cell.y() <= editor.getBottomY()) {
            return;
        }
        class_2680 state = editor.getBlockState((class_2338)mutable);
        if (state.method_27852(class_2246.field_10477)) {
            editor.setBlockState((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
            mutable.method_10098(class_2350.field_11033);
            state = editor.getBlockState((class_2338)mutable);
        }
        if (!CastleRoadNetworkApplier.isRoadSurface(state)) {
            return;
        }
        editor.setBlockState((class_2338)mutable, CastleRoadNetworkApplier.roadState(state, seedAnchor, cell), 48);
        class_2338 above = mutable.method_10084();
        if (editor.canEdit(above) && CastleRoadNetworkApplier.isDisposableRoadObstruction(aboveState = editor.getBlockState(above))) {
            editor.setBlockState(above, class_2246.field_10124.method_9564(), 48);
        }
    }

    private static boolean isDisposableRoadObstruction(class_2680 state) {
        if (state.method_26215() || state.method_31709() || !state.method_26227().method_15769()) {
            return false;
        }
        return state.method_27852(class_2246.field_10477) || state.method_27852(class_2246.field_10479) || state.method_27852(class_2246.field_10214) || state.method_27852(class_2246.field_10112) || state.method_27852(class_2246.field_10313) || state.method_27852(class_2246.field_10428) || state.method_26164(class_3481.field_20339);
    }

    private static boolean isRoadSurface(class_2680 state) {
        return state.method_27852(class_2246.field_10219) || state.method_27852(class_2246.field_10566) || state.method_27852(class_2246.field_10194) || state.method_27852(class_2246.field_10253) || state.method_27852(class_2246.field_10255) || state.method_27852(class_2246.field_10520) || state.method_27852(class_2246.field_10340) || state.method_27852(class_2246.field_10445) || state.method_27852(class_2246.field_9989) || state.method_27852(class_2246.field_10056) || state.method_27852(class_2246.field_28888) || state.method_27852(class_2246.field_29031) || state.method_27852(class_2246.field_10491) || state.method_27852(class_2246.field_10102) || state.method_27852(class_2246.field_9979) || state.method_27852(class_2246.field_10467) || state.method_27852(class_2246.field_10361) || state.method_27852(class_2246.field_22091) || state.method_27852(class_2246.field_23869);
    }

    private static class_2680 roadState(class_2680 original, class_2338 seedAnchor, CastleRoadCell cell) {
        double edge;
        if (original.method_27852(class_2246.field_10194) || original.method_27852(class_2246.field_10253) || original.method_27852(class_2246.field_10255) || original.method_27852(class_2246.field_10467) || original.method_27852(class_2246.field_10445)) {
            return original;
        }
        double noise = CastleRoadNetworkApplier.noise(seedAnchor, cell.x(), cell.z());
        if (noise < 0.08 + (edge = (double)cell.distanceFromCenter() / 2.0) * 0.25) {
            return original;
        }
        if (original.method_27852(class_2246.field_10102) || original.method_27852(class_2246.field_9979) || original.method_27852(class_2246.field_10467) || original.method_27852(class_2246.field_10361)) {
            return noise < 0.72 ? class_2246.field_10467.method_9564() : class_2246.field_10255.method_9564();
        }
        if (original.method_27852(class_2246.field_10491)) {
            return noise < 0.62 ? class_2246.field_10253.method_9564() : class_2246.field_10255.method_9564();
        }
        if (original.method_27852(class_2246.field_10340) || original.method_27852(class_2246.field_10445) || original.method_27852(class_2246.field_9989) || original.method_27852(class_2246.field_10056) || original.method_27852(class_2246.field_28888) || original.method_27852(class_2246.field_29031) || original.method_27852(class_2246.field_22091) || original.method_27852(class_2246.field_23869)) {
            return noise < 0.66 ? class_2246.field_10445.method_9564() : class_2246.field_10255.method_9564();
        }
        return noise < 0.62 ? class_2246.field_10194.method_9564() : class_2246.field_10253.method_9564();
    }

    private static double noise(class_2338 anchor, int x, int z) {
        long value = (long)x * -7046029254386353131L ^ (long)z * -4658895280553007687L ^ (anchor == null ? 0L : anchor.method_10063()) * -7723592293110705685L;
        value ^= value >>> 30;
        value *= -4658895280553007687L;
        value ^= value >>> 27;
        value *= -7723592293110705685L;
        value ^= value >>> 31;
        return (double)(value & 0xFFFFFFL) / 1.6777216E7;
    }
}

