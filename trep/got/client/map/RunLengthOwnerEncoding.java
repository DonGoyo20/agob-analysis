/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 */
package trep.got.client.map;

import java.util.Arrays;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import trep.got.client.map.OwnerEncoding;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
final class RunLengthOwnerEncoding
implements OwnerEncoding {
    private final int pixelCount;
    private final int[] runEnds;
    private final short[] runOwners;

    RunLengthOwnerEncoding(int pixelCount, int[] runEnds, short[] runOwners) {
        this.pixelCount = pixelCount;
        this.runEnds = runEnds;
        this.runOwners = runOwners;
    }

    @Override
    public short ownerAt(int pixelIndex) {
        if (pixelIndex < 0 || pixelIndex >= this.pixelCount) {
            return -1;
        }
        int runIndex = Arrays.binarySearch(this.runEnds, pixelIndex + 1);
        if (runIndex < 0) {
            runIndex = -runIndex - 1;
        }
        return this.runOwners[runIndex];
    }

    @Override
    public long retainedBytes() {
        return (long)this.runEnds.length * 4L + (long)this.runOwners.length * 2L;
    }

    @Override
    public void paintKingdom(class_1011 overlay, int width, List<CastleMapPayload.Entry> palette, int[] colors, String selectedKingdomId, int alpha) {
        int start = 0;
        for (int run = 0; run < this.runEnds.length; ++run) {
            int end = this.runEnds[run];
            short owner = this.runOwners[run];
            if (owner >= 0 && selectedKingdomId.equals(palette.get(owner).kingdomId())) {
                int color = alpha << 24 | colors[owner] & 0xFFFFFF;
                for (int index = start; index < end; ++index) {
                    overlay.method_4305(index % width, index / width, color);
                }
            }
            start = end;
        }
    }
}

