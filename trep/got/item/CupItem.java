/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1838
 */
package trep.got.item;

import net.minecraft.class_1269;
import net.minecraft.class_1792;
import net.minecraft.class_1838;
import trep.got.block.CupBlock;
import trep.got.item.CupPlacementHelper;

public final class CupItem
extends class_1792 {
    private final CupBlock cupBlock;

    public CupItem(CupBlock cupBlock, class_1792.class_1793 settings) {
        super(settings);
        this.cupBlock = cupBlock;
    }

    public class_1269 method_7884(class_1838 context) {
        return CupPlacementHelper.placeCup(context, this.cupBlock, false, -1);
    }
}

