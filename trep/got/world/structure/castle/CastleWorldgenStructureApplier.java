/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2415
 *  net.minecraft.class_2586
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3793
 *  net.minecraft.class_3829
 *  net.minecraft.class_5281
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_8934
 */
package trep.got.world.structure.castle;

import java.util.List;
import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2415;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3793;
import net.minecraft.class_3829;
import net.minecraft.class_5281;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_8934;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.CastleWorldgenStructurePlan;

public final class CastleWorldgenStructureApplier {
    private static final int CASTLE_GENERATION_BLOCK_FLAGS = 48;

    private CastleWorldgenStructureApplier() {
    }

    public static void applyFull(class_3218 world, CastleWorldgenPlan plan) {
        CastleWorldgenStructureApplier.apply((class_5281)world, null, world.method_14183(), plan.structures());
    }

    public static void applyFull(class_3218 world, CastleWorldgenStructurePlan plan) {
        if (world != null && plan != null) {
            CastleWorldgenStructureApplier.apply((class_5281)world, null, world.method_14183(), List.of(plan));
        }
    }

    public static void applyChunk(class_3233 region, class_1923 chunkPos, CastleWorldgenPlan plan) {
        CastleWorldgenStructureApplier.apply((class_5281)region, chunkPos, region.method_8410().method_14183(), plan.structures());
    }

    public static void applyNatureFull(class_3218 world, CastleWorldgenPlan plan) {
        CastleWorldgenStructureApplier.apply((class_5281)world, null, world.method_14183(), plan.natureStructures());
    }

    public static void applyNatureFull(class_3218 world, CastleWorldgenStructurePlan plan) {
        if (world != null && plan != null) {
            CastleWorldgenStructureApplier.apply((class_5281)world, null, world.method_14183(), List.of(plan));
        }
    }

    public static void applyNatureChunk(class_3233 region, class_1923 chunkPos, CastleWorldgenPlan plan) {
        CastleWorldgenStructureApplier.apply((class_5281)region, chunkPos, region.method_8410().method_14183(), plan.natureStructures());
    }

    private static void apply(class_5281 world, class_1923 clipChunk, class_3485 templateManager, List<CastleWorldgenStructurePlan> structures) {
        if (structures == null || structures.isEmpty()) {
            return;
        }
        for (CastleWorldgenStructurePlan structurePlan : structures) {
            class_2960 structureId;
            class_3499 template;
            if (clipChunk != null && !structurePlan.intersectsChunk(clipChunk) || (template = (class_3499)templateManager.method_15094(structureId = class_2960.method_60655((String)"agob", (String)structurePlan.structurePath())).orElse(null)) == null) continue;
            class_3341 absoluteBounds = template.method_27267(structurePlan.placementOrigin(), structurePlan.rotation(), class_2338.field_10980, class_2415.field_11302);
            if (clipChunk != null && !CastleWorldgenStructureApplier.intersectsChunk(absoluteBounds, clipChunk) || clipChunk == null && !CastleWorldgenStructureApplier.canPlaceBounds(world, absoluteBounds)) continue;
            CastleWorldgenStructureApplier.prepareStructureFootprint(world, clipChunk, structurePlan);
            class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false).method_15123(structurePlan.rotation()).method_16184((class_3491)class_3793.field_16721);
            if (clipChunk != null) {
                placement.method_15126(new class_3341(clipChunk.method_8326(), world.method_31607(), clipChunk.method_8328(), clipChunk.method_8327(), world.method_31600(), clipChunk.method_8329()));
            }
            template.method_15172((class_5425)world, structurePlan.placementOrigin(), structurePlan.placementOrigin(), placement, class_5819.method_43049((long)structurePlan.placementOrigin().method_10063()), 48);
        }
    }

    private static void prepareStructureFootprint(class_5281 world, class_1923 clipChunk, CastleWorldgenStructurePlan structurePlan) {
        int maxZ;
        class_3341 footprint = structurePlan.footprint();
        int minX = clipChunk == null ? footprint.method_35415() : Math.max(footprint.method_35415(), clipChunk.method_8326());
        int maxX = clipChunk == null ? footprint.method_35418() : Math.min(footprint.method_35418(), clipChunk.method_8327());
        int minZ = clipChunk == null ? footprint.method_35417() : Math.max(footprint.method_35417(), clipChunk.method_8328());
        int n = maxZ = clipChunk == null ? footprint.method_35420() : Math.min(footprint.method_35420(), clipChunk.method_8329());
        if (minX > maxX || minZ > maxZ) {
            return;
        }
        int floorY = footprint.method_35416();
        int clearTop = Math.min(world.method_31600(), floorY + Math.max(18, structurePlan.size().method_10264() + 4));
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                int y;
                for (int y2 = floorY; y2 <= clearTop; ++y2) {
                    mutable.method_10103(x, y2, z);
                    if (!CastleWorldgenStructureApplier.canEdit(world, (class_2338)mutable) || world.method_8320((class_2338)mutable).method_26215()) continue;
                    CastleWorldgenStructureApplier.clearReplacedBlockEntity(world, (class_2338)mutable);
                    world.method_8652((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
                }
                for (int depth = 1; depth <= 6 && (y = floorY - depth) > world.method_31607(); ++depth) {
                    mutable.method_10103(x, y, z);
                    if (!CastleWorldgenStructureApplier.canEdit(world, (class_2338)mutable) || !world.method_8320((class_2338)mutable).method_26215()) continue;
                    world.method_8652((class_2338)mutable, class_2246.field_10340.method_9564(), 48);
                }
            }
        }
    }

    private static void clearReplacedBlockEntity(class_5281 world, class_2338 pos) {
        class_2586 blockEntity = world.method_8321(pos);
        if (blockEntity instanceof class_8934) {
            class_8934 lootableInventory = (class_8934)blockEntity;
            lootableInventory.method_11285(null);
        }
        if (blockEntity instanceof class_3829) {
            class_3829 clearable = (class_3829)blockEntity;
            clearable.method_5448();
        }
    }

    private static boolean canPlaceBounds(class_5281 world, class_3341 bounds) {
        if (world instanceof class_3233) {
            class_3233 region = (class_3233)world;
            return bounds.method_35416() < region.method_31600() && bounds.method_35419() >= region.method_31607();
        }
        return true;
    }

    private static boolean canEdit(class_5281 world, class_2338 pos) {
        if (world instanceof class_3233) {
            class_3233 region = (class_3233)world;
            class_1923 centerChunk = region.method_33561();
            return pos.method_10263() >= centerChunk.method_8326() && pos.method_10263() <= centerChunk.method_8327() && pos.method_10260() >= centerChunk.method_8328() && pos.method_10260() <= centerChunk.method_8329() && pos.method_10264() >= region.method_31607() && pos.method_10264() < region.method_31600();
        }
        return true;
    }

    private static boolean intersectsChunk(class_3341 bounds, class_1923 chunkPos) {
        return bounds.method_35415() <= chunkPos.method_8327() && bounds.method_35418() >= chunkPos.method_8326() && bounds.method_35417() <= chunkPos.method_8329() && bounds.method_35420() >= chunkPos.method_8328();
    }
}

