/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_9695
 */
package trep.got.recipe;

import net.minecraft.class_1799;
import net.minecraft.class_9695;

public record BrewerRecipeInput(class_1799[] stacks) implements class_9695
{
    public BrewerRecipeInput {
        if (stacks.length != 5) {
            throw new IllegalArgumentException("Brewer recipes require exactly 5 inputs");
        }
    }

    public class_1799 method_59984(int slot) {
        return this.stacks[slot];
    }

    public int method_59983() {
        return this.stacks.length;
    }
}

