/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package trep.got.voyage;

import java.util.List;
import net.minecraft.class_243;

public record VoyagePath(String fromId, String toId, List<String> harbourStops, List<class_243> worldPoints, List<class_243> keypoints, double totalLengthBlocks) {
}

