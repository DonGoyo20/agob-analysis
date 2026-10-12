/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1011
 */
package trep.got.client.map;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1011;
import trep.got.client.map.OwnerEncoding;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
final class DenseOwnerEncoding
implements OwnerEncoding {
    private final short[] owners;

    DenseOwnerEncoding(short[] owners) {
        this.owners = owners;
    }

    @Override
    public short ownerAt(int pixelIndex) {
        return this.owners[pixelIndex];
    }

    @Override
    public long retainedBytes() {
        return (long)this.owners.length * 2L;
    }

    @Override
    public void paintKingdom(class_1011 overlay, int width, List<CastleMapPayload.Entry> palette, int[] colors, String selectedKingdomId, int alpha) {
        for (int index = 0; index < this.owners.length; ++index) {
            short owner = this.owners[index];
            if (owner < 0 || !selectedKingdomId.equals(palette.get(owner).kingdomId())) continue;
            overlay.method_4305(index % width, index / width, alpha << 24 | colors[owner] & 0xFFFFFF);
        }
    }
}

