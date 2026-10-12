/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2470
 */
package trep.got.registry;

import net.minecraft.class_2470;
import trep.got.registry.CastleKeepArrangement;

record CastleKeepLayout(CastleKeepArrangement arrangement, class_2470 rotation) {
    static CastleKeepLayout of(CastleKeepArrangement arrangement) {
        return new CastleKeepLayout(arrangement, class_2470.field_11467);
    }
}

