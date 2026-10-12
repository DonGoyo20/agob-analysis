/*
 * Decompiled with CFR 0.152.
 */
package trep.got.world.map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import trep.got.world.map.MapImage;

public final class RoadMapHelper {
    public static final double DEFAULT_DIAGONAL_RADIUS_PIXELS = 0.24;

    private RoadMapHelper() {
    }

    public static boolean isRoadPixel(MapImage image, int pixelX, int pixelZ, int roadColor) {
        if (!image.contains(pixelX, pixelZ)) {
            return false;
        }
        int argb = image.argb(pixelX, pixelZ);
        return argb >>> 24 != 0 && (argb & 0xFFFFFF) == roadColor;
    }

    public static boolean isRoadPixel(BufferedImage image, int pixelX, int pixelZ, int roadColor) {
        if (pixelX < 0 || pixelZ < 0 || pixelX >= image.getWidth() || pixelZ >= image.getHeight()) {
            return false;
        }
        int argb = image.getRGB(pixelX, pixelZ);
        if (argb >>> 24 == 0) {
            return false;
        }
        return (argb & 0xFFFFFF) == roadColor;
    }

    public static boolean isRoadAtPixelPosition(MapImage image, double pixelX, double pixelZ, int roadColor, double diagonalRadiusPixels) {
        int basePixelZ;
        int basePixelX = (int)Math.floor(pixelX);
        if (RoadMapHelper.isRoadPixel(image, basePixelX, basePixelZ = (int)Math.floor(pixelZ), roadColor)) {
            return true;
        }
        for (int sampleZ = basePixelZ - 1; sampleZ <= basePixelZ; ++sampleZ) {
            for (int sampleX = basePixelX - 1; sampleX <= basePixelX; ++sampleX) {
                if (RoadMapHelper.hasNorthWestSouthEastConnection(image, sampleX, sampleZ, roadColor) && RoadMapHelper.distanceToSegment(pixelX, pixelZ, (double)sampleX + 0.5, (double)sampleZ + 0.5, (double)sampleX + 1.5, (double)sampleZ + 1.5) <= diagonalRadiusPixels) {
                    return true;
                }
                if (!RoadMapHelper.hasNorthEastSouthWestConnection(image, sampleX, sampleZ, roadColor) || !(RoadMapHelper.distanceToSegment(pixelX, pixelZ, (double)sampleX + 1.5, (double)sampleZ + 0.5, (double)sampleX + 0.5, (double)sampleZ + 1.5) <= diagonalRadiusPixels)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isRoadAtPixelPosition(BufferedImage image, double pixelX, double pixelZ, int roadColor, double diagonalRadiusPixels) {
        int basePixelZ;
        int basePixelX = (int)Math.floor(pixelX);
        if (RoadMapHelper.isRoadPixel(image, basePixelX, basePixelZ = (int)Math.floor(pixelZ), roadColor)) {
            return true;
        }
        for (int sampleZ = basePixelZ - 1; sampleZ <= basePixelZ; ++sampleZ) {
            for (int sampleX = basePixelX - 1; sampleX <= basePixelX; ++sampleX) {
                if (RoadMapHelper.hasNorthWestSouthEastConnection(image, sampleX, sampleZ, roadColor) && RoadMapHelper.distanceToSegment(pixelX, pixelZ, (double)sampleX + 0.5, (double)sampleZ + 0.5, (double)sampleX + 1.5, (double)sampleZ + 1.5) <= diagonalRadiusPixels) {
                    return true;
                }
                if (!RoadMapHelper.hasNorthEastSouthWestConnection(image, sampleX, sampleZ, roadColor) || !(RoadMapHelper.distanceToSegment(pixelX, pixelZ, (double)sampleX + 1.5, (double)sampleZ + 0.5, (double)sampleX + 0.5, (double)sampleZ + 1.5) <= diagonalRadiusPixels)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isRoadAtWorldBlock(MapImage image, int worldX, int worldZ, int roadColor, double diagonalRadiusPixels) {
        double pixelX = ((double)worldX + 0.5) / 32.0 + 1500.0;
        double pixelZ = ((double)worldZ + 0.5) / 32.0 + 1500.0;
        return RoadMapHelper.isRoadAtPixelPosition(image, pixelX, pixelZ, roadColor, diagonalRadiusPixels);
    }

    public static boolean isRoadAtWorldBlock(BufferedImage image, int worldX, int worldZ, int roadColor, double diagonalRadiusPixels) {
        double pixelX = ((double)worldX + 0.5) / 32.0 + 1500.0;
        double pixelZ = ((double)worldZ + 0.5) / 32.0 + 1500.0;
        return RoadMapHelper.isRoadAtPixelPosition(image, pixelX, pixelZ, roadColor, diagonalRadiusPixels);
    }

    public static List<DiagonalConnection> collectDiagonalConnections(MapImage image, int roadColor) {
        ArrayList<DiagonalConnection> connections = new ArrayList<DiagonalConnection>();
        for (int pixelZ = 0; pixelZ < image.height() - 1; ++pixelZ) {
            for (int pixelX = 0; pixelX < image.width() - 1; ++pixelX) {
                if (RoadMapHelper.hasNorthWestSouthEastConnection(image, pixelX, pixelZ, roadColor)) {
                    connections.add(new DiagonalConnection(pixelX, pixelZ, pixelX + 1, pixelZ + 1));
                }
                if (!RoadMapHelper.hasNorthEastSouthWestConnection(image, pixelX, pixelZ, roadColor)) continue;
                connections.add(new DiagonalConnection(pixelX + 1, pixelZ, pixelX, pixelZ + 1));
            }
        }
        return List.copyOf(connections);
    }

    private static boolean hasNorthWestSouthEastConnection(MapImage image, int pixelX, int pixelZ, int roadColor) {
        return RoadMapHelper.isRoadPixel(image, pixelX, pixelZ, roadColor) && RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ + 1, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX, pixelZ + 1, roadColor);
    }

    private static boolean hasNorthWestSouthEastConnection(BufferedImage image, int pixelX, int pixelZ, int roadColor) {
        return RoadMapHelper.isRoadPixel(image, pixelX, pixelZ, roadColor) && RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ + 1, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX, pixelZ + 1, roadColor);
    }

    private static boolean hasNorthEastSouthWestConnection(MapImage image, int pixelX, int pixelZ, int roadColor) {
        return RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ, roadColor) && RoadMapHelper.isRoadPixel(image, pixelX, pixelZ + 1, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX, pixelZ, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ + 1, roadColor);
    }

    private static boolean hasNorthEastSouthWestConnection(BufferedImage image, int pixelX, int pixelZ, int roadColor) {
        return RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ, roadColor) && RoadMapHelper.isRoadPixel(image, pixelX, pixelZ + 1, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX, pixelZ, roadColor) && !RoadMapHelper.isRoadPixel(image, pixelX + 1, pixelZ + 1, roadColor);
    }

    private static double distanceToSegment(double pointX, double pointZ, double startX, double startZ, double endX, double endZ) {
        double deltaX = endX - startX;
        double deltaZ = endZ - startZ;
        double lengthSquared = deltaX * deltaX + deltaZ * deltaZ;
        if (lengthSquared <= 1.0E-6) {
            return Math.hypot(pointX - startX, pointZ - startZ);
        }
        double projection = ((pointX - startX) * deltaX + (pointZ - startZ) * deltaZ) / lengthSquared;
        double clamped = Math.max(0.0, Math.min(1.0, projection));
        double closestX = startX + deltaX * clamped;
        double closestZ = startZ + deltaZ * clamped;
        return Math.hypot(pointX - closestX, pointZ - closestZ);
    }

    public record DiagonalConnection(int startPixelX, int startPixelZ, int endPixelX, int endPixelZ) {
    }
}

