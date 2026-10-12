/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 */
package trep.got.mainquest;

import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

public record MainQuestReward(class_2960 itemId, int count) {
    public class_1792 item() {
        return (class_1792)class_7923.field_41178.method_10223(this.itemId);
    }
}

