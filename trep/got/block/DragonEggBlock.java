/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2464
 *  net.minecraft.class_2586
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2754
 *  net.minecraft.class_2769
 *  net.minecraft.class_3726
 *  net.minecraft.class_4538
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.block.entity.DragonEggBlockEntity;
import trep.got.entity.dragon.DragonEggColor;
import trep.got.item.DragonEggItem;
import trep.got.registry.GotBlocks;

public final class DragonEggBlock
extends class_2237 {
    public static final MapCodec<DragonEggBlock> CODEC = DragonEggBlock.method_54094(DragonEggBlock::new);
    public static final class_2754<DragonEggColor> COLOR = class_2754.method_11850((String)"color", DragonEggColor.class);
    private static final class_265 SHAPE = class_2248.method_9541((double)2.0, (double)0.0, (double)2.0, (double)14.0, (double)18.0, (double)14.0);

    public DragonEggBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(COLOR, (Comparable)((Object)DragonEggColor.DEFAULT)));
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new DragonEggBlockEntity(pos, state);
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{COLOR});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657(COLOR, (Comparable)((Object)DragonEggItem.getColor(ctx.method_8041())));
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        class_2586 class_25862 = world.method_8321(pos);
        if (class_25862 instanceof DragonEggBlockEntity) {
            DragonEggBlockEntity egg = (DragonEggBlockEntity)class_25862;
            DragonEggColor color = itemStack.method_7960() ? (DragonEggColor)((Object)state.method_11654(COLOR)) : DragonEggItem.getColor(itemStack);
            egg.setColor(color);
        }
    }

    public void method_9556(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, @Nullable class_2586 blockEntity, class_1799 tool) {
        if (!world.field_9236 && !player.method_7337()) {
            DragonEggColor dragonEggColor;
            if (blockEntity instanceof DragonEggBlockEntity) {
                DragonEggBlockEntity egg = (DragonEggBlockEntity)blockEntity;
                dragonEggColor = egg.getColor();
            } else {
                dragonEggColor = (DragonEggColor)((Object)state.method_11654(COLOR));
            }
            DragonEggColor color = dragonEggColor;
            class_2248.method_9577((class_1937)world, (class_2338)pos, (class_1799)DragonEggItem.createStack(GotBlocks.DRAGON_EGG, color));
        }
        super.method_9556(world, player, pos, state, blockEntity, tool);
    }

    public class_1799 method_9574(class_4538 world, class_2338 pos, class_2680 state) {
        class_2586 class_25862 = world.method_8321(pos);
        if (class_25862 instanceof DragonEggBlockEntity) {
            DragonEggBlockEntity egg = (DragonEggBlockEntity)class_25862;
            return DragonEggItem.createStack(GotBlocks.DRAGON_EGG, egg.getColor());
        }
        return DragonEggItem.createStack(GotBlocks.DRAGON_EGG, (DragonEggColor)((Object)state.method_11654(COLOR)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected class_2464 method_9604(class_2680 state) {
        return class_2464.field_11458;
    }
}

