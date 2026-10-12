/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3491
 *  net.minecraft.class_3492
 *  net.minecraft.class_3499
 *  net.minecraft.class_3793
 *  net.minecraft.class_5281
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 */
package trep.got.world.structure.castle;

import java.util.Optional;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3491;
import net.minecraft.class_3492;
import net.minecraft.class_3499;
import net.minecraft.class_3793;
import net.minecraft.class_5281;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import trep.got.world.structure.apply.StructureBlockEditor;
import trep.got.world.structure.apply.WorldStructureBlockEditors;
import trep.got.world.structure.castle.DynamicCastleCoreSearch;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleKeepPiecePlan;

public final class DynamicCastleCoreApplier {
    private static final int CASTLE_GENERATION_BLOCK_FLAGS = 48;

    private DynamicCastleCoreApplier() {
    }

    public static Optional<class_2338> applyFull(class_3218 world, DynamicCastleCoreWorldgenPlan plan) {
        return DynamicCastleCoreApplier.apply((class_5281)world, null, world.method_14183(), plan);
    }

    public static Optional<class_2338> applyChunk(class_3233 region, class_1923 chunkPos, DynamicCastleCoreWorldgenPlan plan) {
        return DynamicCastleCoreApplier.apply((class_5281)region, chunkPos, region.method_8410().method_14183(), plan);
    }

    public static Optional<class_2338> findExistingBanner(class_3218 world, DynamicCastleCoreWorldgenPlan plan) {
        return DynamicCastleCoreSearch.findBanner(WorldStructureBlockEditors.forWorld(world), plan);
    }

    private static Optional<class_2338> apply(class_5281 world, class_1923 clipChunk, class_3485 templateManager, DynamicCastleCoreWorldgenPlan plan) {
        StructureBlockEditor editor;
        if (world instanceof class_3233) {
            class_3233 region = (class_3233)world;
            v0 = WorldStructureBlockEditors.forRegion(region, clipChunk == null ? region.method_33561() : clipChunk);
        } else {
            v0 = editor = WorldStructureBlockEditors.forWorld((class_3218)world);
        }
        if (plan.usesCastleKeep()) {
            boolean placedAny = false;
            for (DynamicCastleKeepPiecePlan piece : plan.keepPieces()) {
                class_3499 template = templateManager.method_15094(piece.structureId()).orElse(null);
                if (template == null) {
                    return Optional.empty();
                }
                class_2338 pieceOrigin = plan.templateOrigin().method_10081(piece.offset());
                if (!DynamicCastleCoreApplier.placeTemplate(world, clipChunk, template, class_2470.field_11467, pieceOrigin)) continue;
                placedAny = true;
            }
            if (!placedAny && clipChunk != null) {
                return Optional.empty();
            }
            Optional<class_2338> placedBanner = DynamicCastleCoreSearch.findBanner(editor, plan);
            return placedBanner.isPresent() ? placedBanner : Optional.of(plan.fallbackBannerPos());
        }
        if (plan.claimTemplateId() == null) {
            return Optional.empty();
        }
        class_3499 claimTemplate = templateManager.method_15094(plan.claimTemplateId()).orElse(null);
        if (claimTemplate == null) {
            return Optional.empty();
        }
        boolean placed = DynamicCastleCoreApplier.placeTemplate(world, clipChunk, claimTemplate, class_2470.field_11467, plan.templateOrigin());
        if (!placed && clipChunk != null) {
            return Optional.empty();
        }
        Optional<class_2338> placedBanner = DynamicCastleCoreSearch.findBanner(editor, plan);
        return placedBanner.isPresent() ? placedBanner : Optional.of(plan.fallbackBannerPos());
    }

    private static boolean placeTemplate(class_5281 world, class_1923 clipChunk, class_3499 template, class_2470 rotation, class_2338 origin) {
        class_3341 absoluteBounds = template.method_27267(origin, rotation, class_2338.field_10980, class_2415.field_11302);
        if (clipChunk != null && !DynamicCastleCoreApplier.intersectsChunk(absoluteBounds, clipChunk)) {
            return false;
        }
        if (clipChunk == null && !DynamicCastleCoreApplier.canPlaceBounds(world, absoluteBounds)) {
            return false;
        }
        class_3492 placement = new class_3492().method_15133(false).method_15131(false).method_27264(false).method_15123(rotation).method_16184((class_3491)class_3793.field_16721);
        if (clipChunk != null) {
            placement.method_15126(new class_3341(clipChunk.method_8326(), world.method_31607(), clipChunk.method_8328(), clipChunk.method_8327(), world.method_31600(), clipChunk.method_8329()));
        }
        return template.method_15172((class_5425)world, origin, origin, placement, class_5819.method_43049((long)origin.method_10063()), 48);
    }

    private static boolean canPlaceBounds(class_5281 world, class_3341 bounds) {
        if (world instanceof class_3233) {
            class_3233 region = (class_3233)world;
            return bounds.method_35416() < region.method_31600() && bounds.method_35419() >= region.method_31607();
        }
        return true;
    }

    private static boolean intersectsChunk(class_3341 bounds, class_1923 chunkPos) {
        return bounds.method_35415() <= chunkPos.method_8327() && bounds.method_35418() >= chunkPos.method_8326() && bounds.method_35417() <= chunkPos.method_8329() && bounds.method_35420() >= chunkPos.method_8328();
    }
}

