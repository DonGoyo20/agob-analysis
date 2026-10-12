/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2382
 *  net.minecraft.class_2470
 */
package trep.got.registry;

import java.util.List;
import net.minecraft.class_2382;
import net.minecraft.class_2470;
import trep.got.registry.CastleKeepPiece;

record CastleKeepTemplate(String folder, List<CastleKeepPiece> pieces, class_2382 minOffset, class_2382 size, class_2470 rotation) {
}

