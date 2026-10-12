/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import trep.got.entity.ShipModelData;

public enum VesselVariant {
    SHIP("ship", "ship", 4, 1.0, 1.0, 0.0, 50),
    GALLEY("galley", "galley", 4, 1.5, 2.0, 0.0, 100),
    LONGSHIP("longship", "longship", 7, 0.75, 0.75, 1.0, 250);

    private static final double DEFAULT_SEAT_Y = 0.375;
    private final String id;
    private final String assetName;
    private final int seatCount;
    private final double speedMultiplier;
    private final double accelerationMultiplier;
    private final double seatYOffset;
    private final int maxHullHp;

    private VesselVariant(String id, String assetName, int seatCount, double speedMultiplier, double accelerationMultiplier, double seatYOffset, int maxHullHp) {
        this.id = id;
        this.assetName = assetName;
        this.seatCount = seatCount;
        this.speedMultiplier = speedMultiplier;
        this.accelerationMultiplier = accelerationMultiplier;
        this.seatYOffset = seatYOffset;
        this.maxHullHp = maxHullHp;
    }

    public String id() {
        return this.id;
    }

    public int seatCount() {
        return this.seatCount;
    }

    public double speedMultiplier() {
        return this.speedMultiplier;
    }

    public double accelerationMultiplier() {
        return this.accelerationMultiplier;
    }

    public double seatYOffset() {
        return this.seatYOffset;
    }

    public int maxHullHp() {
        return this.maxHullHp;
    }

    public String modelResourcePath() {
        return "/assets/agob/models/entity/" + this.assetName + ".bbmodel";
    }

    public class_2960 textureId() {
        return class_2960.method_60655((String)"agob", (String)("textures/entity/ship/" + this.assetName + ".png"));
    }

    public List<ShipModelData.SeatAnchor> createFallbackSeats(class_238 localBounds) {
        double[] dArray;
        ArrayList<ShipModelData.SeatAnchor> seats = new ArrayList<ShipModelData.SeatAnchor>();
        double centerX = (localBounds.field_1323 + localBounds.field_1320) * 0.5;
        double seatY = 0.375 + this.seatYOffset;
        if (this == LONGSHIP) {
            double[] zFractions = new double[]{0.1, 0.24, 0.38, 0.52};
            for (int index = 0; index < this.seatCount; ++index) {
                double z = VesselVariant.interpolate(localBounds.field_1321, localBounds.field_1324, zFractions[Math.min(index / 2, zFractions.length - 1)]);
                double x = centerX + (switch (index) {
                    case 0, 2, 4, 6 -> -0.95;
                    case 1, 3, 5 -> 0.95;
                    default -> 0.0;
                });
                if (index == this.seatCount - 1) {
                    x = centerX;
                    z = VesselVariant.interpolate(localBounds.field_1321, localBounds.field_1324, 0.82);
                }
                seats.add(new ShipModelData.SeatAnchor("seat" + (index + 1), new class_243(x, seatY, z)));
            }
            return seats;
        }
        if (this == GALLEY) {
            double[] dArray2 = new double[4];
            dArray2[0] = 0.25;
            dArray2[1] = 0.42;
            dArray2[2] = 0.58;
            dArray = dArray2;
            dArray2[3] = 0.75;
        } else {
            double[] dArray3 = new double[4];
            dArray3[0] = 0.28;
            dArray3[1] = 0.42;
            dArray3[2] = 0.6;
            dArray = dArray3;
            dArray3[3] = 0.78;
        }
        double[] zFractions = dArray;
        for (int index = 0; index < this.seatCount; ++index) {
            double x = centerX;
            if (this == GALLEY) {
                x += index % 2 == 0 ? -0.75 : 0.75;
            }
            double z = VesselVariant.interpolate(localBounds.field_1321, localBounds.field_1324, zFractions[Math.min(index, zFractions.length - 1)]);
            seats.add(new ShipModelData.SeatAnchor("seat" + (index + 1), new class_243(x, seatY, z)));
        }
        return seats;
    }

    private static double interpolate(double min, double max, double t) {
        return min + (max - min) * t;
    }
}

