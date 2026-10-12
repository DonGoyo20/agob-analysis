/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2323
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_2756
 *  net.minecraft.class_2769
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_3341
 *  net.minecraft.class_3532
 */
package trep.got.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2323;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2756;
import net.minecraft.class_2769;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_3341;
import net.minecraft.class_3532;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.castle.CityStructureClaim;
import trep.got.entity.AbstractKingdomNpcEntity;

final class KingdomNpcCastleGoalSupport {
    private KingdomNpcCastleGoalSupport() {
    }

    static boolean isNight(class_3218 world) {
        long timeOfDay = world.method_8532() % 24000L;
        return timeOfDay >= 12500L && timeOfDay < 23500L;
    }

    static CastleInstance castleFor(AbstractKingdomNpcEntity npc, class_3218 world) {
        String castleId = npc.got$getCastle();
        if (castleId == null || castleId.isBlank()) {
            return null;
        }
        return CastleWorldState.get(world).get(castleId);
    }

    static Optional<CityStructureClaim> ownedHouse(AbstractKingdomNpcEntity npc, CastleInstance castle) {
        if (castle == null || npc.getCastleNpcId().isBlank()) {
            return Optional.empty();
        }
        return castle.getCityStructureClaims().stream().filter(claim -> npc.getCastleNpcId().equals(claim.ownerProfileId())).filter(KingdomNpcCastleGoalSupport::isHouse).findFirst();
    }

    static Optional<CityStructureClaim> market(CastleInstance castle, AbstractKingdomNpcEntity npc) {
        if (castle == null) {
            return Optional.empty();
        }
        List<CityStructureClaim> markets = castle.getCityStructureClaims().stream().filter(KingdomNpcCastleGoalSupport::isMarket).sorted(Comparator.comparing(CityStructureClaim::id)).toList();
        if (markets.isEmpty()) {
            return Optional.empty();
        }
        int index = Math.floorMod(npc.method_5667().hashCode() + npc.field_6012, markets.size());
        return Optional.of(markets.get(index));
    }

    static class_2338 structureCenter(CityStructureClaim claim) {
        class_3341 footprint = claim.footprint();
        return new class_2338(class_3532.method_15357((double)((double)(footprint.method_35415() + footprint.method_35418()) * 0.5)), claim.origin().method_10264(), class_3532.method_15357((double)((double)(footprint.method_35417() + footprint.method_35420()) * 0.5)));
    }

    static class_2338 findInteriorTarget(class_3218 world, CityStructureClaim claim) {
        class_2338 center = KingdomNpcCastleGoalSupport.structureCenter(claim);
        class_2338 target = KingdomNpcCastleGoalSupport.nearestWalkableInside(world, center, 8, claim.footprint());
        if (target == null) {
            target = KingdomNpcCastleGoalSupport.nearestWalkableSurface(world, center, 8, claim.footprint());
        }
        return target == null ? center : target;
    }

    static Optional<HomeDoorTarget> findHomeDoorTarget(class_3218 world, CityStructureClaim claim) {
        class_3341 footprint = claim.footprint();
        class_2338 center = KingdomNpcCastleGoalSupport.structureCenter(claim);
        class_2338 bestDoor = null;
        double bestDistance = Double.MAX_VALUE;
        for (int y = footprint.method_35416(); y <= footprint.method_35419(); ++y) {
            for (int x = footprint.method_35415(); x <= footprint.method_35418(); ++x) {
                for (int z = footprint.method_35417(); z <= footprint.method_35420(); ++z) {
                    double distance;
                    class_2338 pos = new class_2338(x, y, z);
                    class_2680 state = world.method_8320(pos);
                    if (!(state.method_26204() instanceof class_2323) || state.method_28498((class_2769)class_2323.field_10946) && state.method_11654((class_2769)class_2323.field_10946) == class_2756.field_12609 || !((distance = pos.method_10262((class_2382)center)) < bestDistance)) continue;
                    bestDistance = distance;
                    bestDoor = pos;
                }
            }
        }
        if (bestDoor == null) {
            return Optional.empty();
        }
        class_2338 interior = KingdomNpcCastleGoalSupport.findInteriorTarget(world, claim);
        return Optional.of(new HomeDoorTarget(bestDoor.method_10062(), interior.method_10062()));
    }

    static void openDoor(class_3218 world, AbstractKingdomNpcEntity npc, class_2338 doorPos) {
        class_2680 state = world.method_8320(doorPos);
        class_2248 class_22482 = state.method_26204();
        if (!(class_22482 instanceof class_2323)) {
            return;
        }
        class_2323 door = (class_2323)class_22482;
        if (state.method_28498((class_2769)class_2323.field_10946) && state.method_11654((class_2769)class_2323.field_10946) == class_2756.field_12609 && !((state = world.method_8320(doorPos = doorPos.method_10074())).method_26204() instanceof class_2323)) {
            return;
        }
        if (state.method_28498((class_2769)class_2323.field_10945) && !((Boolean)state.method_11654((class_2769)class_2323.field_10945)).booleanValue()) {
            door.method_10033((class_1297)npc, (class_1937)world, state, doorPos, true);
        }
    }

    static Optional<class_2338> roadTarget(class_3218 world, CastleInstance castle, AbstractKingdomNpcEntity npc) {
        if (castle == null) {
            return Optional.empty();
        }
        class_2338 banner = castle.getBannerPos();
        ArrayList<class_2338> centers = new ArrayList<class_2338>();
        for (CityStructureClaim claim : castle.getCityStructureClaims()) {
            centers.add(KingdomNpcCastleGoalSupport.structureCenter(claim));
        }
        if (centers.isEmpty()) {
            return KingdomNpcCastleGoalSupport.randomWalkableNear(world, npc, banner, 8, Math.max(24, castle.getAreaRadiusBlocks() / 3));
        }
        int minX = centers.stream().mapToInt(class_2382::method_10263).min().orElse(banner.method_10263());
        int maxX = centers.stream().mapToInt(class_2382::method_10263).max().orElse(banner.method_10263());
        int minZ = centers.stream().mapToInt(class_2382::method_10260).min().orElse(banner.method_10260());
        int maxZ = centers.stream().mapToInt(class_2382::method_10260).max().orElse(banner.method_10260());
        boolean eastWest = maxX - minX >= maxZ - minZ;
        int spread = Math.max(12, eastWest ? maxX - minX : maxZ - minZ);
        int centerX = eastWest ? banner.method_10263() : class_3532.method_15357((double)centers.stream().mapToInt(class_2382::method_10263).average().orElse(banner.method_10263()));
        int centerZ = eastWest ? class_3532.method_15357((double)centers.stream().mapToInt(class_2382::method_10260).average().orElse(banner.method_10260())) : banner.method_10260();
        for (int attempt = 0; attempt < 18; ++attempt) {
            int z;
            int along = npc.method_59922().method_39332(-spread / 2, spread / 2);
            int side = npc.method_59922().method_39332(-2, 2);
            int x = eastWest ? centerX + along : centerX + side;
            class_2338 target = KingdomNpcCastleGoalSupport.surfaceWalkable(world, x, z = eastWest ? centerZ + side : centerZ + along);
            if (target == null || npc.method_5942().method_6348(target, 0) == null) continue;
            return Optional.of(target);
        }
        return Optional.empty();
    }

    static Optional<class_2338> randomWalkableNear(class_3218 world, AbstractKingdomNpcEntity npc, class_2338 center, int minRadius, int maxRadius) {
        int minSquared = minRadius * minRadius;
        int maxSquared = maxRadius * maxRadius;
        for (int attempt = 0; attempt < 18; ++attempt) {
            class_2338 target;
            int offsetZ;
            int offsetX = npc.method_59922().method_39332(-maxRadius, maxRadius);
            int distance = offsetX * offsetX + (offsetZ = npc.method_59922().method_39332(-maxRadius, maxRadius)) * offsetZ;
            if (distance < minSquared || distance > maxSquared || (target = KingdomNpcCastleGoalSupport.surfaceWalkable(world, center.method_10263() + offsetX, center.method_10260() + offsetZ)) == null || npc.method_5942().method_6348(target, 0) == null) continue;
            return Optional.of(target);
        }
        return Optional.empty();
    }

    static boolean contains(class_3341 footprint, class_2338 pos) {
        return pos.method_10263() >= footprint.method_35415() && pos.method_10263() <= footprint.method_35418() && pos.method_10264() >= footprint.method_35416() && pos.method_10264() <= footprint.method_35419() && pos.method_10260() >= footprint.method_35417() && pos.method_10260() <= footprint.method_35420();
    }

    static boolean isAt(class_2338 pos, class_243 entityPos, double distance) {
        return entityPos.method_1025(class_243.method_24953((class_2382)pos)) <= distance * distance;
    }

    private static boolean isMarket(CityStructureClaim claim) {
        String path = claim.structurePath() == null ? "" : claim.structurePath().toLowerCase(Locale.ROOT);
        return path.contains("market");
    }

    private static boolean isHouse(CityStructureClaim claim) {
        String path = claim.structurePath() == null ? "" : claim.structurePath().toLowerCase(Locale.ROOT);
        return path.contains("house");
    }

    private static class_2338 nearestWalkableSurface(class_3218 world, class_2338 center, int radius, class_3341 bounds) {
        class_2338 best = null;
        double bestDistance = Double.MAX_VALUE;
        for (int x = center.method_10263() - radius; x <= center.method_10263() + radius; ++x) {
            for (int z = center.method_10260() - radius; z <= center.method_10260() + radius; ++z) {
                double distance;
                class_2338 candidate = KingdomNpcCastleGoalSupport.surfaceWalkable(world, x, z);
                if (candidate == null || !KingdomNpcCastleGoalSupport.contains(bounds, candidate) || !((distance = candidate.method_10262((class_2382)center)) < bestDistance)) continue;
                bestDistance = distance;
                best = candidate;
            }
        }
        return best;
    }

    private static class_2338 nearestWalkableInside(class_3218 world, class_2338 center, int radius, class_3341 bounds) {
        class_2338 best = null;
        double bestDistance = Double.MAX_VALUE;
        for (int x = Math.max(bounds.method_35415(), center.method_10263() - radius); x <= Math.min(bounds.method_35418(), center.method_10263() + radius); ++x) {
            for (int z = Math.max(bounds.method_35417(), center.method_10260() - radius); z <= Math.min(bounds.method_35420(), center.method_10260() + radius); ++z) {
                for (int y = bounds.method_35416(); y <= bounds.method_35419(); ++y) {
                    double distance;
                    class_2338 feet = new class_2338(x, y, z);
                    if (!KingdomNpcCastleGoalSupport.isWalkable(world, feet) || !((distance = feet.method_10262((class_2382)center)) < bestDistance)) continue;
                    bestDistance = distance;
                    best = feet;
                }
            }
        }
        return best;
    }

    private static class_2338 surfaceWalkable(class_3218 world, int x, int z) {
        if (!world.method_8393(x >> 4, z >> 4)) {
            return null;
        }
        int topY = world.method_8624(class_2902.class_2903.field_13203, x, z);
        if (topY <= world.method_31607()) {
            return null;
        }
        class_2338 feet = new class_2338(x, topY, z);
        class_2338 ground = feet.method_10074();
        if (!(world.method_8320(ground).method_26212((class_1922)world, ground) && world.method_8320(feet).method_26215() && world.method_8320(feet.method_10084()).method_26215())) {
            return null;
        }
        return feet;
    }

    private static boolean isWalkable(class_3218 world, class_2338 feet) {
        class_2338 ground = feet.method_10074();
        return world.method_8320(ground).method_26212((class_1922)world, ground) && world.method_8320(feet).method_26215() && world.method_8320(feet.method_10084()).method_26215();
    }

    record HomeDoorTarget(class_2338 doorPos, class_2338 interiorPos) {
    }
}

