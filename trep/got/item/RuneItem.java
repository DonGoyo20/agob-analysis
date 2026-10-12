/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1772
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1887
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_5321
 *  net.minecraft.class_6880
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_7924
 *  net.minecraft.class_9304
 *  net.minecraft.class_9304$class_9305
 *  net.minecraft.class_9334
 */
package trep.got.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1772;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1887;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7225;
import net.minecraft.class_7924;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

public final class RuneItem
extends class_1772 {
    private final class_5321<class_1887> enchantmentKey;
    private final int maxLevel;
    private final String loreLine;

    public RuneItem(class_5321<class_1887> enchantmentKey, int maxLevel, String loreLine, class_1792.class_1793 settings) {
        super(settings);
        this.enchantmentKey = enchantmentKey;
        this.maxLevel = Math.max(1, maxLevel);
        this.loreLine = loreLine;
    }

    public class_1799 createStack(class_7225.class_7874 lookup, int level) {
        class_1799 stack = new class_1799((class_1935)this);
        int clampedLevel = Math.max(1, Math.min(this.maxLevel, level));
        class_6880<class_1887> enchantment = this.getEnchantmentEntry(lookup);
        class_9304.class_9305 builder = new class_9304.class_9305(class_9304.field_49385);
        builder.method_57547(enchantment, clampedLevel);
        stack.method_57379(class_9334.field_49643, (Object)builder.method_57549());
        return stack;
    }

    public int getStoredLevel(class_1799 stack) {
        if (!stack.method_31574((class_1792)this)) {
            return 0;
        }
        class_9304 stored = (class_9304)stack.method_57825(class_9334.field_49643, (Object)class_9304.field_49385);
        for (Object2IntMap.Entry entry : stored.method_57539()) {
            if (!((class_6880)entry.getKey()).method_40225(this.enchantmentKey)) continue;
            return entry.getIntValue();
        }
        return stack.method_31574((class_1792)this) ? 1 : 0;
    }

    public int getAppliedLevel(class_1799 stack) {
        if (!stack.method_31574((class_1792)this)) {
            return 0;
        }
        return Math.max(this.getStoredLevel(stack), 1);
    }

    public int getMaxRuneLevel() {
        return this.maxLevel;
    }

    public class_6880<class_1887> getEnchantmentEntry(class_7225.class_7874 lookup) {
        return lookup.method_46762(class_7924.field_41265).method_46747(this.enchantmentKey);
    }

    public class_2561 method_7864(class_1799 stack) {
        return super.method_7864(stack).method_27661().method_27692(class_124.field_1076);
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        super.method_7851(stack, context, tooltip, type);
        tooltip.add((class_2561)class_2561.method_43470((String)("\"" + this.loreLine + "\"")).method_27695(new class_124[]{class_124.field_1080, class_124.field_1056}));
    }

    public void method_7888(class_1799 stack, class_1937 world, class_1297 entity, int slot, boolean selected) {
        super.method_7888(stack, world, entity, slot, selected);
        if (!stack.method_31574((class_1792)this) || stack.method_57826(class_9334.field_49643)) {
            return;
        }
        class_9304.class_9305 builder = new class_9304.class_9305(class_9304.field_49385);
        builder.method_57547(this.getEnchantmentEntry((class_7225.class_7874)world.method_30349()), 1);
        stack.method_57379(class_9334.field_49643, (Object)builder.method_57549());
    }
}

