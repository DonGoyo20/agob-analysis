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
import trep.got.client.map.DenseOwnerEncoding;
import trep.got.client.map.RunLengthOwnerEncoding;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
sealed interface OwnerEncoding
permits DenseOwnerEncoding, RunLengthOwnerEncoding {
    public short ownerAt(int var1);

    public long retainedBytes();

    public void paintKingdom(class_1011 var1, int var2, List<CastleMapPayload.Entry> var3, int[] var4, String var5, int var6);

    public static OwnerEncoding compact(short[] values, short[] primaryValues, OwnerEncoding primaryEncoding) {
        if (Arrays.equals(values, primaryValues)) {
            return primaryEncoding;
        }
        int runCount = values.length == 0 ? 0 : 1;
        for (int index = 1; index < values.length; ++index) {
            if (values[index] == values[index - 1]) continue;
            ++runCount;
        }
        long runLengthBytes = (long)runCount * 6L;
        long denseBytes = (long)values.length * 2L;
        if (runLengthBytes >= denseBytes) {
            return new DenseOwnerEncoding(values);
        }
        int[] runEnds = new int[runCount];
        short[] runOwners = new short[runCount];
        int runIndex = 0;
        short owner = values.length == 0 ? (short)-1 : values[0];
        for (int index = 1; index <= values.length; ++index) {
            if (index != values.length && values[index] == owner) continue;
            runEnds[runIndex] = index;
            runOwners[runIndex] = owner;
            ++runIndex;
            if (index >= values.length) continue;
            owner = values[index];
        }
        return new RunLengthOwnerEncoding(values.length, runEnds, runOwners);
    }
}

