/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1262
 *  net.minecraft.class_1263
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_2371
 */
package trep.got.inventory;

import java.util.List;
import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2371;

public interface ImplementedInventory
extends class_1263 {
    public class_2371<class_1799> getItems();

    public static ImplementedInventory of(final class_2371<class_1799> items) {
        return new ImplementedInventory(){

            @Override
            public class_2371<class_1799> getItems() {
                return items;
            }

            public void method_5431() {
            }
        };
    }

    default public int method_5439() {
        return this.getItems().size();
    }

    default public boolean method_5442() {
        for (class_1799 stack : this.getItems()) {
            if (stack.method_7960()) continue;
            return false;
        }
        return true;
    }

    default public class_1799 method_5438(int slot) {
        return (class_1799)this.getItems().get(slot);
    }

    default public class_1799 method_5434(int slot, int count) {
        class_1799 result = class_1262.method_5430(this.getItems(), (int)slot, (int)count);
        if (!result.method_7960()) {
            this.method_5431();
        }
        return result;
    }

    default public class_1799 method_5441(int slot) {
        return class_1262.method_5428(this.getItems(), (int)slot);
    }

    default public void method_5447(int slot, class_1799 stack) {
        this.getItems().set(slot, (Object)stack);
        int maxCount = this.method_5444();
        if (!stack.method_7960() && stack.method_7947() > maxCount) {
            stack.method_7939(maxCount);
        }
        this.method_5431();
    }

    default public void method_5448() {
        this.getItems().clear();
        this.method_5431();
    }

    default public boolean method_5443(class_1657 player) {
        return true;
    }

    default public List<class_1799> asList() {
        return this.getItems();
    }
}

