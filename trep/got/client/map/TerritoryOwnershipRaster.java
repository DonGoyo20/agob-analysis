/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 *  net.minecraft.class_2338
 *  net.minecraft.class_3532
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.map;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import net.minecraft.class_2338;
import net.minecraft.class_3532;
import org.jetbrains.annotations.Nullable;
import trep.got.client.map.OwnerEncoding;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
final class TerritoryOwnershipRaster {
    private static final short NO_OWNER = -1;
    private final int width;
    private final int height;
    private final List<CastleMapPayload.Entry> palette;
    private final OwnerEncoding castleOwners;
    private final OwnerEncoding areaOwners;
    private final OwnerEncoding kingdomOwners;
    private final int[] castleColors;
    private final int[] kingdomColors;

    TerritoryOwnershipRaster(int width, int height, List<CastleMapPayload.Entry> palette, OwnerEncoding castleOwners, OwnerEncoding areaOwners, OwnerEncoding kingdomOwners, int[] castleColors, int[] kingdomColors) {
        this.width = width;
        this.height = height;
        this.palette = palette;
        this.castleOwners = castleOwners;
        this.areaOwners = areaOwners;
        this.kingdomOwners = kingdomOwners;
        this.castleColors = castleColors;
        this.kingdomColors = kingdomColors;
    }

    @Nullable
    CastleMapPayload.Entry findAreaAt(class_2338 pos) {
        if (pos == null) {
            return null;
        }
        int pixelX = TerritoryOwnershipRaster.worldToPixel(pos.method_10263(), this.width);
        int pixelZ = TerritoryOwnershipRaster.worldToPixel(pos.method_10260(), this.height);
        short owner = this.areaOwners.ownerAt(pixelZ * this.width + pixelX);
        return this.entry(owner);
    }

    class_1011 createCastleOverlay(int alpha) {
        class_1011 overlay = new class_1011(this.width, this.height, true);
        for (int index = 0; index < this.width * this.height; ++index) {
            short owner = this.castleOwners.ownerAt(index);
            if (owner == -1) continue;
            overlay.method_4305(index % this.width, index / this.width, TerritoryOwnershipRaster.withAlpha(this.castleColors[owner], alpha));
        }
        return overlay;
    }

    class_1011 createKingdomOverlay(String selectedKingdomId, int alpha) {
        class_1011 overlay = new class_1011(this.width, this.height, true);
        this.kingdomOwners.paintKingdom(overlay, this.width, this.palette, this.kingdomColors, selectedKingdomId, alpha);
        return overlay;
    }

    long retainedIndexBytes() {
        long bytes = this.castleOwners.retainedBytes();
        if (this.areaOwners != this.castleOwners) {
            bytes += this.areaOwners.retainedBytes();
        }
        if (this.kingdomOwners != this.castleOwners && this.kingdomOwners != this.areaOwners) {
            bytes += this.kingdomOwners.retainedBytes();
        }
        return bytes;
    }

    @Nullable
    private CastleMapPayload.Entry entry(short owner) {
        return owner < 0 || owner >= this.palette.size() ? null : this.palette.get(owner);
    }

    private static int worldToPixel(int worldCoordinate, int imageSize) {
        double mapPixel = (double)(worldCoordinate + 48000) / 32.0;
        double scaled = mapPixel * ((double)imageSize / 3000.0);
        return class_3532.method_15340((int)((int)Math.floor(scaled)), (int)0, (int)(imageSize - 1));
    }

    private static int withAlpha(int color, int alpha) {
        return alpha << 24 | color & 0xFFFFFF;
    }
}

