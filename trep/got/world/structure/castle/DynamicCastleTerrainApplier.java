/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 *  net.minecraft.class_2382
 *  net.minecraft.class_2488
 *  net.minecraft.class_2680
 *  net.minecraft.class_2769
 *  net.minecraft.class_2794
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_3532
 */
package trep.got.world.structure.castle;

import net.minecraft.class_1923;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2488;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_2794;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_3532;
import trep.got.registry.GotCastles;
import trep.got.world.gen.MapChunkGenerator;
import trep.got.world.structure.apply.StructureBlockEditor;
import trep.got.world.structure.apply.WorldStructureBlockEditors;
import trep.got.world.structure.castle.CastleWorldgenPlan;
import trep.got.world.structure.castle.DynamicCastleWorldgenFootprint;

public final class DynamicCastleTerrainApplier {
    private static final int MAX_CASTLE_PAD_CUT_DEPTH = 4;
    private static final int CASTLE_BUILD_CLEARANCE_HEIGHT = 28;
    private static final int DYNAMIC_CASTLE_OUTER_EDGE_BLEND_WIDTH = 16;
    private static final int CASTLE_GENERATION_BLOCK_FLAGS = 48;
    private static final int DYNAMIC_CASTLE_FLATTEN_EXTRA_BLOCKS = 0;

    private DynamicCastleTerrainApplier() {
    }

    public static void applyFull(class_3218 world, CastleWorldgenPlan plan) {
        DynamicCastleTerrainApplier.apply(WorldStructureBlockEditors.forWorld(world), null, plan, DynamicCastleTerrainApplier.naturalSurfaceSampler(world));
    }

    public static void applyChunk(class_3233 region, class_1923 chunkPos, CastleWorldgenPlan plan) {
        DynamicCastleTerrainApplier.apply(WorldStructureBlockEditors.forRegion(region, chunkPos), chunkPos, plan, DynamicCastleTerrainApplier.naturalSurfaceSampler(region.method_8410()));
    }

    private static void apply(StructureBlockEditor editor, class_1923 clipChunk, CastleWorldgenPlan plan, MapChunkGenerator.NaturalSurfaceSampler naturalSurfaces) {
        if (plan == null || plan.dynamicFootprint() == null || naturalSurfaces == null) {
            return;
        }
        DynamicCastleWorldgenFootprint footprint = plan.dynamicFootprint();
        int floorY = plan.placementPlan().floorY();
        int edgeBlendWidth = DynamicCastleTerrainApplier.terrainMergeRadius(plan.placementPlan().blendRadius());
        boolean southOnlyGeneration = GotCastles.usesSouthOnlyCastleGeneration(plan.castleId());
        String kingdomId = plan.metadata() == null ? "" : plan.metadata().kingdomId();
        class_2338 anchor = plan.anchor();
        PlatformSurfaceStyle surfaceStyle = DynamicCastleTerrainApplier.resolvePlatformSurfaceStyle(editor, anchor, kingdomId);
        DynamicCastleTerrainApplier.prepareCastlePad(editor, clipChunk, footprint.origin(), footprint.size(), floorY, 0, southOnlyGeneration, surfaceStyle, anchor, naturalSurfaces);
        DynamicCastleTerrainApplier.flattenDynamicCastleClaimSurface(editor, clipChunk, DynamicCastleTerrainApplier.expandDynamicFlattenOrigin(footprint.origin()), DynamicCastleTerrainApplier.expandDynamicFlattenSize(footprint.size()), floorY, southOnlyGeneration, surfaceStyle, anchor, naturalSurfaces);
        DynamicCastleTerrainApplier.blendCastlePlatformOuterEdge(editor, clipChunk, footprint.origin(), footprint.size(), floorY, edgeBlendWidth, southOnlyGeneration, surfaceStyle, anchor, naturalSurfaces);
        DynamicCastleTerrainApplier.clearBuildVolume(editor, clipChunk, footprint.origin(), footprint.size(), floorY + 1, Math.max(28, footprint.clearanceHeight()));
    }

    private static void clearBuildVolume(StructureBlockEditor editor, class_1923 clipChunk, class_2338 origin, class_2382 size, int floorY, int clearanceHeight) {
        int clipMaxZ;
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int clipMinX = clipChunk == null ? minX : Math.max(minX, clipChunk.method_8326());
        int clipMaxX = clipChunk == null ? maxX : Math.min(maxX, clipChunk.method_8327());
        int clipMinZ = clipChunk == null ? minZ : Math.max(minZ, clipChunk.method_8328());
        int n = clipMaxZ = clipChunk == null ? maxZ : Math.min(maxZ, clipChunk.method_8329());
        if (clipMinX > clipMaxX || clipMinZ > clipMaxZ) {
            return;
        }
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int x = clipMinX; x <= clipMaxX; ++x) {
            for (int z = clipMinZ; z <= clipMaxZ; ++z) {
                for (int y = floorY; y <= floorY + Math.max(0, clearanceHeight); ++y) {
                    mutable.method_10103(x, y, z);
                    if (!editor.canEdit((class_2338)mutable) || editor.getBlockState((class_2338)mutable).method_26215()) continue;
                    editor.setBlockState((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
                }
            }
        }
    }

    private static void flattenDynamicCastleClaimSurface(StructureBlockEditor editor, class_1923 clipChunk, class_2338 origin, class_2382 size, int floorY, boolean southOnlyGeneration, PlatformSurfaceStyle surfaceStyle, class_2338 anchor, MapChunkGenerator.NaturalSurfaceSampler naturalSurfaces) {
        int clipMaxZ;
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int clipMinX = clipChunk == null ? minX : Math.max(minX, clipChunk.method_8326());
        int clipMaxX = clipChunk == null ? maxX : Math.min(maxX, clipChunk.method_8327());
        int clipMinZ = clipChunk == null ? minZ : Math.max(minZ, clipChunk.method_8328());
        int n = clipMaxZ = clipChunk == null ? maxZ : Math.min(maxZ, clipChunk.method_8329());
        if (clipMinX > clipMaxX || clipMinZ > clipMaxZ) {
            return;
        }
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int x = clipMinX; x <= clipMaxX; ++x) {
            for (int z = clipMinZ; z <= clipMaxZ; ++z) {
                int y;
                int clearTop;
                MapChunkGenerator.NaturalSurfaceSample naturalSurface = naturalSurfaces.sample(x, z);
                int naturalSurfaceY = naturalSurface.groundY();
                int liveSurfaceY = editor.getTopY(class_2902.class_2903.field_13203, x, z) - 1;
                int supportBaseY = naturalSurfaceY;
                int targetFloorY = floorY;
                SurfacePalette palette = DynamicCastleTerrainApplier.paletteForSurfaceStyle(surfaceStyle, DynamicCastleTerrainApplier.resolveSurfacePalette(editor, x, z));
                for (int y2 = clearTop = DynamicCastleTerrainApplier.cleanupTopY(liveSurfaceY, naturalSurface, targetFloorY, 8, 30); y2 > targetFloorY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (!editor.canEdit((class_2338)mutable) || editor.getBlockState((class_2338)mutable).method_26215()) continue;
                    editor.setBlockState((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
                }
                class_2680 topSurface = DynamicCastleTerrainApplier.platformSurfaceState(surfaceStyle, palette, anchor, x, z);
                DynamicCastleTerrainApplier.setBlock(editor, mutable, x, targetFloorY, z, topSurface);
                for (int depth = 1; depth <= 8 && (y = targetFloorY - depth) > editor.getBottomY(); ++depth) {
                    DynamicCastleTerrainApplier.setBlock(editor, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                DynamicCastleTerrainApplier.fillSupportColumnToY(editor, x, z, targetFloorY - 9, Math.max(editor.getBottomY(), supportBaseY - 2), palette);
                DynamicCastleTerrainApplier.decoratePlatformSurface(editor, anchor, x, targetFloorY, z, surfaceStyle, topSurface, 0);
            }
        }
    }

    private static void prepareCastlePad(StructureBlockEditor editor, class_1923 clipChunk, class_2338 origin, class_2382 size, int floorY, int blendRadius, boolean southOnlyGeneration, PlatformSurfaceStyle surfaceStyle, class_2338 anchor, MapChunkGenerator.NaturalSurfaceSampler naturalSurfaces) {
        int clipMaxZ;
        int clipMaxX;
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int clipMinX = clipChunk == null ? minX - blendRadius : Math.max(minX - blendRadius, clipChunk.method_8326());
        int n = clipMaxX = clipChunk == null ? maxX + blendRadius : Math.min(maxX + blendRadius, clipChunk.method_8327());
        int clipMinZ = clipChunk == null ? (southOnlyGeneration ? minZ : minZ - blendRadius) : Math.max(southOnlyGeneration ? minZ : minZ - blendRadius, clipChunk.method_8328());
        int n2 = clipMaxZ = clipChunk == null ? maxZ + blendRadius : Math.min(maxZ + blendRadius, clipChunk.method_8329());
        if (clipMinX > clipMaxX || clipMinZ > clipMaxZ) {
            return;
        }
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int x = clipMinX; x <= clipMaxX; ++x) {
            for (int z = clipMinZ; z <= clipMaxZ; ++z) {
                int y;
                int clearTop;
                int distance = DynamicCastleTerrainApplier.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance > blendRadius) continue;
                MapChunkGenerator.NaturalSurfaceSample naturalSurface = naturalSurfaces.sample(x, z);
                int naturalSurfaceY = naturalSurface.groundY();
                int liveSurfaceY = editor.getTopY(class_2902.class_2903.field_13203, x, z) - 1;
                float alpha = distance == 0 ? 1.0f : 1.0f - (float)distance / (float)(blendRadius + 1);
                int desiredY = Math.round(class_3532.method_48781((float)alpha, (int)naturalSurfaceY, (int)floorY));
                int targetY = Math.max(desiredY, naturalSurfaceY - 4);
                int supportBaseY = naturalSurfaceY;
                SurfacePalette palette = DynamicCastleTerrainApplier.paletteForSurfaceStyle(surfaceStyle, DynamicCastleTerrainApplier.resolveSurfacePalette(editor, x, z));
                for (int y2 = clearTop = DynamicCastleTerrainApplier.cleanupTopY(liveSurfaceY, naturalSurface, targetY, 6, 6); y2 > targetY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (!editor.canEdit((class_2338)mutable) || editor.getBlockState((class_2338)mutable).method_26215()) continue;
                    editor.setBlockState((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
                }
                class_2680 topSurface = DynamicCastleTerrainApplier.platformSurfaceState(surfaceStyle, palette, anchor, x, z);
                DynamicCastleTerrainApplier.setBlock(editor, mutable, x, targetY, z, topSurface);
                for (int depth = 1; depth <= 6 && (y = targetY - depth) > editor.getBottomY(); ++depth) {
                    DynamicCastleTerrainApplier.setBlock(editor, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                if (distance == 0) {
                    DynamicCastleTerrainApplier.fillSupportColumn(editor, x, z, targetY - 7, palette);
                } else {
                    DynamicCastleTerrainApplier.fillSupportColumnToY(editor, x, z, targetY - 7, Math.max(editor.getBottomY(), supportBaseY - 2), palette);
                }
                DynamicCastleTerrainApplier.decoratePlatformSurface(editor, anchor, x, targetY, z, surfaceStyle, topSurface, distance);
            }
        }
    }

    private static void blendCastlePlatformOuterEdge(StructureBlockEditor editor, class_1923 clipChunk, class_2338 origin, class_2382 size, int floorY, int blendWidth, boolean southOnlyGeneration, PlatformSurfaceStyle surfaceStyle, class_2338 anchor, MapChunkGenerator.NaturalSurfaceSampler naturalSurfaces) {
        int clipMaxZ;
        if (blendWidth <= 0) {
            return;
        }
        int minX = origin.method_10263();
        int maxX = origin.method_10263() + size.method_10263() - 1;
        int minZ = origin.method_10260();
        int maxZ = origin.method_10260() + size.method_10260() - 1;
        int ringMinX = minX - blendWidth;
        int ringMaxX = maxX + blendWidth;
        int ringMinZ = minZ - blendWidth;
        int ringMaxZ = maxZ + blendWidth;
        int clipMinX = clipChunk == null ? ringMinX : Math.max(ringMinX, clipChunk.method_8326());
        int clipMaxX = clipChunk == null ? ringMaxX : Math.min(ringMaxX, clipChunk.method_8327());
        int clipMinZ = clipChunk == null ? ringMinZ : Math.max(ringMinZ, clipChunk.method_8328());
        int n = clipMaxZ = clipChunk == null ? ringMaxZ : Math.min(ringMaxZ, clipChunk.method_8329());
        if (clipMinX > clipMaxX || clipMinZ > clipMaxZ) {
            return;
        }
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int x = clipMinX; x <= clipMaxX; ++x) {
            for (int z = clipMinZ; z <= clipMaxZ; ++z) {
                int y;
                int clearTop;
                int distance = DynamicCastleTerrainApplier.outsideDistance(x, z, minX, maxX, minZ, maxZ);
                if (distance <= 0 || distance > blendWidth) continue;
                MapChunkGenerator.NaturalSurfaceSample naturalSurface = naturalSurfaces.sample(x, z);
                int naturalSurfaceY = naturalSurface.groundY();
                int liveSurfaceY = editor.getTopY(class_2902.class_2903.field_13203, x, z) - 1;
                int supportBaseY = naturalSurfaceY;
                int targetY = DynamicCastleTerrainApplier.resolveCastlePlatformOuterBlendHeight(naturalSurfaceY, distance, floorY, blendWidth, editor.getBottomY());
                SurfacePalette palette = DynamicCastleTerrainApplier.paletteForSurfaceStyle(surfaceStyle, DynamicCastleTerrainApplier.resolveSurfacePalette(editor, x, z));
                for (int y2 = clearTop = DynamicCastleTerrainApplier.cleanupTopY(liveSurfaceY, naturalSurface, targetY, 4, 4); y2 > targetY; --y2) {
                    mutable.method_10103(x, y2, z);
                    if (!editor.canEdit((class_2338)mutable) || editor.getBlockState((class_2338)mutable).method_26215()) continue;
                    editor.setBlockState((class_2338)mutable, class_2246.field_10124.method_9564(), 48);
                }
                class_2680 topSurface = DynamicCastleTerrainApplier.platformSurfaceState(surfaceStyle, palette, anchor, x, z);
                DynamicCastleTerrainApplier.setBlock(editor, mutable, x, targetY, z, topSurface);
                for (int depth = 1; depth <= 6 && (y = targetY - depth) > editor.getBottomY(); ++depth) {
                    DynamicCastleTerrainApplier.setBlock(editor, mutable, x, y, z, depth < 3 ? palette.subsurface() : palette.deep());
                }
                DynamicCastleTerrainApplier.fillSupportColumnToY(editor, x, z, targetY - 7, Math.max(editor.getBottomY(), supportBaseY - 2), palette);
                DynamicCastleTerrainApplier.decoratePlatformSurface(editor, anchor, x, targetY, z, surfaceStyle, topSurface, distance);
            }
        }
    }

    private static void setBlock(StructureBlockEditor editor, class_2338.class_2339 mutable, int x, int y, int z, class_2680 state) {
        mutable.method_10103(x, y, z);
        if (editor.canEdit((class_2338)mutable) && !editor.getBlockState((class_2338)mutable).equals(state)) {
            editor.setBlockState((class_2338)mutable, state, 48);
        }
    }

    private static int resolveCastlePlatformOuterBlendHeight(int naturalSurfaceY, int outsideDistance, int floorY, int blendWidth, int bottomY) {
        double platformWeight = 1.0 - class_3532.method_15350((double)((double)outsideDistance / (double)Math.max(1, blendWidth)), (double)0.0, (double)1.0);
        double alpha = DynamicCastleTerrainApplier.smoothstep(platformWeight);
        int targetY = Math.round((float)class_3532.method_16436((double)alpha, (double)naturalSurfaceY, (double)floorY));
        return Math.max(bottomY + 1, targetY);
    }

    private static int cleanupTopY(int liveSurfaceY, MapChunkGenerator.NaturalSurfaceSample naturalSurface, int targetY, int livePadding, int targetPadding) {
        return Math.max(Math.max(liveSurfaceY, naturalSurface.surfaceFluidTopY()) + Math.max(0, livePadding), targetY + Math.max(0, targetPadding));
    }

    public static int plannedSurfaceY(DynamicCastleWorldgenFootprint footprint, int floorY, int naturalSurfaceY, int bottomY, int x, int z) {
        int maxZ;
        int minZ;
        int maxX;
        if (footprint == null) {
            return naturalSurfaceY;
        }
        int minX = footprint.origin().method_10263();
        int distance = DynamicCastleTerrainApplier.outsideDistance(x, z, minX, maxX = minX + footprint.size().method_10263() - 1, minZ = footprint.origin().method_10260(), maxZ = minZ + footprint.size().method_10260() - 1);
        if (distance == 0) {
            return floorY;
        }
        int blendWidth = DynamicCastleTerrainApplier.terrainMergeRadius(0);
        if (distance > blendWidth) {
            return naturalSurfaceY;
        }
        return DynamicCastleTerrainApplier.resolveCastlePlatformOuterBlendHeight(naturalSurfaceY, distance, floorY, blendWidth, bottomY);
    }

    public static boolean replacesSurface(DynamicCastleWorldgenFootprint footprint, int floorY, int naturalSurfaceY, int bottomY, int x, int z) {
        int maxZ;
        int minZ;
        int maxX;
        if (footprint == null) {
            return false;
        }
        int minX = footprint.origin().method_10263();
        int distance = DynamicCastleTerrainApplier.outsideDistance(x, z, minX, maxX = minX + footprint.size().method_10263() - 1, minZ = footprint.origin().method_10260(), maxZ = minZ + footprint.size().method_10260() - 1);
        return distance <= DynamicCastleTerrainApplier.terrainMergeRadius(0);
    }

    public static TerrainApplicationContractAudit auditTerrainApplicationContract() {
        boolean outsideBlendPreserved;
        DynamicCastleWorldgenFootprint footprint = new DynamicCastleWorldgenFootprint(class_2338.field_10980, new class_2382(16, 1, 16), 28);
        MapChunkGenerator.NaturalSurfaceSample naturalSurface = new MapChunkGenerator.NaturalSurfaceSample(64, class_2246.field_10219.method_9564(), false, class_2246.field_10124.method_9564(), 64);
        int ringTargetY = DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, naturalSurface.groundY(), -64, 16, 8);
        int decoratedLiveTopY = 79;
        int cleanupTopY = DynamicCastleTerrainApplier.cleanupTopY(decoratedLiveTopY, naturalSurface, ringTargetY, 4, 4);
        boolean decoratedBlendCleared = DynamicCastleTerrainApplier.replacesSurface(footprint, 70, naturalSurface.groundY(), -64, 16, 8) && cleanupTopY >= decoratedLiveTopY + 4;
        TerrainApplicationContractAudit audit = new TerrainApplicationContractAudit(decoratedBlendCleared, outsideBlendPreserved = !DynamicCastleTerrainApplier.replacesSurface(footprint, 70, naturalSurface.groundY(), -64, 32, 8) && DynamicCastleTerrainApplier.plannedSurfaceY(footprint, 70, naturalSurface.groundY(), -64, 32, 8) == naturalSurface.groundY());
        if (!audit.passes()) {
            throw new IllegalStateException("Castle terrain application contract failed: " + String.valueOf(audit));
        }
        return audit;
    }

    private static MapChunkGenerator.NaturalSurfaceSampler naturalSurfaceSampler(class_3218 world) {
        class_2794 class_27942;
        if (world != null && (class_27942 = world.method_14178().method_12129()) instanceof MapChunkGenerator) {
            MapChunkGenerator mapGenerator = (MapChunkGenerator)class_27942;
            return mapGenerator.naturalSurfaceSampler();
        }
        return null;
    }

    private static PlatformSurfaceStyle resolvePlatformSurfaceStyle(StructureBlockEditor editor, class_2338 referencePos, String kingdomId) {
        String normalized;
        String string = normalized = kingdomId == null ? "" : kingdomId;
        if (normalized.equals("agob:the_north") || normalized.equals("agob:the_nights_watch") || normalized.equals("agob:wildling")) {
            return PlatformSurfaceStyle.SNOWY;
        }
        if (normalized.equals("agob:dorne") || normalized.equals("agob:meereen") || normalized.equals("agob:yunkai") || normalized.equals("agob:astapor") || normalized.equals("agob:qarth") || normalized.equals("agob:volantis") || normalized.equals("agob:tyrosh") || normalized.equals("agob:myr") || normalized.equals("agob:pentos") || normalized.equals("agob:golden_company")) {
            return PlatformSurfaceStyle.DESERT;
        }
        if (normalized.equals("agob:iron_islands")) {
            return PlatformSurfaceStyle.ROCKY;
        }
        if (normalized.equals("agob:targaryen") || normalized.equals("agob:blackfyre")) {
            return PlatformSurfaceStyle.VOLCANIC;
        }
        if (!normalized.isBlank()) {
            return PlatformSurfaceStyle.GRASS;
        }
        String biomePath = DynamicCastleTerrainApplier.safeBiomePath(editor, referencePos);
        if (biomePath.contains("dragonstone")) {
            return PlatformSurfaceStyle.VOLCANIC;
        }
        if (biomePath.contains("dorne") || biomePath.contains("slavers_bay") || biomePath.contains("step_stones") || biomePath.contains("desert")) {
            return PlatformSurfaceStyle.DESERT;
        }
        if (biomePath.contains("winter") || biomePath.contains("north") || biomePath.contains("snow") || biomePath.contains("frozen") || biomePath.contains("the_wall")) {
            return PlatformSurfaceStyle.SNOWY;
        }
        if (biomePath.contains("iron_islands") || biomePath.contains("rock") || biomePath.contains("stone_shore")) {
            return PlatformSurfaceStyle.ROCKY;
        }
        return PlatformSurfaceStyle.GRASS;
    }

    private static String safeBiomePath(StructureBlockEditor editor, class_2338 referencePos) {
        try {
            return editor.getBiome(new class_2338(referencePos.method_10263(), editor.getSeaLevel(), referencePos.method_10260())).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        }
        catch (IllegalStateException ignored) {
            return "";
        }
    }

    private static SurfacePalette paletteForSurfaceStyle(PlatformSurfaceStyle style, SurfacePalette biomePalette) {
        return switch (style.ordinal()) {
            default -> throw new MatchException(null, null);
            case 2 -> new SurfacePalette(class_2246.field_10102.method_9564(), class_2246.field_9979.method_9564(), class_2246.field_9979.method_9564());
            case 1 -> new SurfacePalette(class_2246.field_10219.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
            case 3 -> new SurfacePalette(class_2246.field_10255.method_9564(), class_2246.field_10253.method_9564(), class_2246.field_10340.method_9564());
            case 4 -> new SurfacePalette(class_2246.field_22091.method_9564(), class_2246.field_23869.method_9564(), class_2246.field_23869.method_9564());
            case 0 -> biomePalette;
        };
    }

    private static class_2680 platformSurfaceState(PlatformSurfaceStyle style, SurfacePalette palette, class_2338 anchor, int x, int z) {
        double noise = DynamicCastleTerrainApplier.platformNoise(anchor, x, z, 0);
        return switch (style.ordinal()) {
            default -> throw new MatchException(null, null);
            case 2 -> {
                if (noise < 0.58) {
                    yield class_2246.field_10102.method_9564();
                }
                if (noise < 0.82) {
                    yield class_2246.field_9979.method_9564();
                }
                if (noise < 0.94) {
                    yield class_2246.field_10467.method_9564();
                }
                yield class_2246.field_10361.method_9564();
            }
            case 1 -> {
                if (noise < 0.08) {
                    yield class_2246.field_10253.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case 3 -> {
                if (noise < 0.48) {
                    yield class_2246.field_10255.method_9564();
                }
                if (noise < 0.72) {
                    yield class_2246.field_10253.method_9564();
                }
                if (noise < 0.88) {
                    yield class_2246.field_10340.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case 0 -> {
                if (noise < 0.06) {
                    yield class_2246.field_10253.method_9564();
                }
                if (noise < 0.1) {
                    yield class_2246.field_10566.method_9564();
                }
                yield class_2246.field_10219.method_9564();
            }
            case 4 -> palette.surface();
        };
    }

    private static void decoratePlatformSurface(StructureBlockEditor editor, class_2338 anchor, int x, int y, int z, PlatformSurfaceStyle style, class_2680 surfaceState, int distanceFromFootprint) {
        class_2338 above = new class_2338(x, y + 1, z);
        if (!editor.canEdit(above) || !editor.getBlockState(above).method_26215()) {
            return;
        }
        double noise = DynamicCastleTerrainApplier.platformNoise(anchor, x, z, 1);
        if (style == PlatformSurfaceStyle.SNOWY) {
            if (noise > 0.1) {
                int extraLayer = (int)Math.floor(DynamicCastleTerrainApplier.platformNoise(anchor, x, z, 2) * 3.0);
                int edgeBonus = distanceFromFootprint > 0 && DynamicCastleTerrainApplier.platformNoise(anchor, x, z, 3) > 0.72 ? 1 : 0;
                int layers = class_3532.method_15340((int)(1 + extraLayer + edgeBonus), (int)1, (int)4);
                editor.setBlockState(above, (class_2680)class_2246.field_10477.method_9564().method_11657((class_2769)class_2488.field_11518, (Comparable)Integer.valueOf(layers)), 48);
            }
            return;
        }
        if (style == PlatformSurfaceStyle.DESERT) {
            if (surfaceState.method_27852(class_2246.field_10102) && noise < 0.018) {
                editor.setBlockState(above, class_2246.field_10428.method_9564(), 48);
            }
            return;
        }
        if ((style == PlatformSurfaceStyle.GRASS || style == PlatformSurfaceStyle.ROCKY) && surfaceState.method_27852(class_2246.field_10219)) {
            double d = style == PlatformSurfaceStyle.ROCKY ? 0.035 : 0.085;
            if (noise < d) {
                class_2680 foliage = DynamicCastleTerrainApplier.platformNoise(anchor, x, z, 2) < 0.22 ? class_2246.field_10112.method_9564() : class_2246.field_10479.method_9564();
                editor.setBlockState(above, foliage, 48);
            }
        }
    }

    private static double platformNoise(class_2338 anchor, int x, int z, int salt) {
        long value = (long)x * -7046029254386353131L ^ (long)z * -4658895280553007687L ^ anchor.method_10063() * -7723592293110705685L ^ (long)salt * -2960836687051489901L;
        value ^= value >>> 30;
        value *= -4658895280553007687L;
        value ^= value >>> 27;
        value *= -7723592293110705685L;
        value ^= value >>> 31;
        return (double)(value & 0xFFFFFFL) / 1.6777216E7;
    }

    private static SurfacePalette resolveSurfacePalette(StructureBlockEditor editor, int x, int z) {
        String biomePath = editor.getBiome(new class_2338(x, editor.getSeaLevel(), z)).method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (biomePath.contains("dragonstone")) {
            return new SurfacePalette(class_2246.field_22091.method_9564(), class_2246.field_23869.method_9564(), class_2246.field_23869.method_9564());
        }
        if (biomePath.contains("dorne") || biomePath.contains("slavers_bay") || biomePath.contains("step_stones")) {
            return new SurfacePalette(class_2246.field_10102.method_9564(), class_2246.field_9979.method_9564(), class_2246.field_10340.method_9564());
        }
        if (biomePath.contains("winter") || biomePath.contains("north")) {
            return new SurfacePalette(class_2246.field_10491.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
        }
        if (biomePath.contains("iron_islands")) {
            return new SurfacePalette(class_2246.field_10255.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
        }
        return new SurfacePalette(class_2246.field_10219.method_9564(), class_2246.field_10566.method_9564(), class_2246.field_10340.method_9564());
    }

    private static void fillSupportColumn(StructureBlockEditor editor, int x, int z, int startY, SurfacePalette palette) {
        int depth = 0;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int y = startY; y > editor.getBottomY(); --y) {
            mutable.method_10103(x, y, z);
            if (!editor.canEdit((class_2338)mutable) || !DynamicCastleTerrainApplier.needsSupportFill(editor.getBlockState((class_2338)mutable))) break;
            editor.setBlockState((class_2338)mutable, depth < 2 ? palette.subsurface() : palette.deep(), 48);
            ++depth;
        }
    }

    private static void fillSupportColumnToY(StructureBlockEditor editor, int x, int z, int startY, int stopYInclusive, SurfacePalette palette) {
        if (startY < stopYInclusive) {
            return;
        }
        int depth = 0;
        class_2338.class_2339 mutable = new class_2338.class_2339();
        for (int y = startY; y >= stopYInclusive && y > editor.getBottomY(); --y) {
            mutable.method_10103(x, y, z);
            if (!editor.canEdit((class_2338)mutable) || !DynamicCastleTerrainApplier.needsSupportFill(editor.getBlockState((class_2338)mutable))) continue;
            editor.setBlockState((class_2338)mutable, depth < 2 ? palette.subsurface() : palette.deep(), 48);
            ++depth;
        }
    }

    private static boolean needsSupportFill(class_2680 state) {
        return state.method_26215() || !state.method_26227().method_15769();
    }

    private static int outsideDistance(int x, int z, int minX, int maxX, int minZ, int maxZ) {
        int dx = x < minX ? minX - x : Math.max(0, x - maxX);
        int dz = z < minZ ? minZ - z : Math.max(0, z - maxZ);
        return Math.max(dx, dz);
    }

    private static double smoothstep(double value) {
        double clamped = class_3532.method_15350((double)value, (double)0.0, (double)1.0);
        return clamped * clamped * (3.0 - 2.0 * clamped);
    }

    public static int terrainMergeRadius(int blendRadius) {
        return 16;
    }

    private static class_2338 expandDynamicFlattenOrigin(class_2338 origin) {
        return origin.method_10069(0, 0, 0);
    }

    private static class_2382 expandDynamicFlattenSize(class_2382 size) {
        int extra = 0;
        return new class_2382(size.method_10263() + extra, size.method_10264(), size.method_10260() + extra);
    }

    private static enum PlatformSurfaceStyle {
        GRASS,
        SNOWY,
        DESERT,
        ROCKY,
        VOLCANIC;

    }

    private record SurfacePalette(class_2680 surface, class_2680 subsurface, class_2680 deep) {
    }

    public record TerrainApplicationContractAudit(boolean decoratedBlendCleared, boolean outsideBlendPreserved) {
        public boolean passes() {
            return this.decoratedBlendCleared && this.outsideBlendPreserved;
        }
    }
}

