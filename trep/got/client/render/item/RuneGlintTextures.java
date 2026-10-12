/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_1893
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4720
 *  net.minecraft.class_6880
 *  net.minecraft.class_9304
 *  net.minecraft.class_9334
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.render.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_1893;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4720;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import org.jetbrains.annotations.Nullable;
import trep.got.client.render.item.RuneGlintRenderLayers;

@Environment(value=EnvType.CLIENT)
public final class RuneGlintTextures {
    private static final class_2960 UNBREAKING_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/rune_glint/rune_unbreaking.png");
    private static final class_2960 POWER_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/rune_glint/rune_power_glint.png");
    private static final class_2960 FIRE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/rune_glint/rune_fire_glint.png");
    private static final class_2960 PROTECTION_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/item/rune_glint/rune_protection.png");
    private static final ThreadLocal<class_1799> CURRENT_STACK = new ThreadLocal();

    private RuneGlintTextures() {
    }

    public static void pushCurrentStack(@Nullable class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            CURRENT_STACK.remove();
            return;
        }
        CURRENT_STACK.set(stack);
    }

    public static void clearCurrentStack() {
        CURRENT_STACK.remove();
    }

    public static boolean hasCurrentRuneGlint() {
        return RuneGlintTextures.runeMask(CURRENT_STACK.get()) != 0;
    }

    public static class_4588 unionWithRuneGlint(class_4597 provider, class_4588 baseConsumer, RuneGlintLayerType layerType) {
        int runeMask = RuneGlintTextures.runeMask(CURRENT_STACK.get());
        if (runeMask == 0) {
            return baseConsumer;
        }
        class_4588 combined = baseConsumer;
        for (class_2960 texture : RuneGlintTextures.texturesForMask(runeMask)) {
            combined = class_4720.method_24037((class_4588)provider.getBuffer(layerType.layer(texture)), (class_4588)combined);
        }
        return combined;
    }

    private static int runeMask(@Nullable class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return 0;
        }
        class_9304 enchantments = (class_9304)stack.method_57825(class_9334.field_49633, (Object)class_9304.field_49385);
        class_9304 storedEnchantments = (class_9304)stack.method_57825(class_9334.field_49643, (Object)class_9304.field_49385);
        return RuneGlintTextures.runeMask(enchantments) | RuneGlintTextures.runeMask(storedEnchantments);
    }

    private static int runeMask(class_9304 enchantments) {
        int mask = 0;
        for (class_6880 enchantment : enchantments.method_57534()) {
            if (enchantment.method_40225(class_1893.field_9119)) {
                mask |= 1;
                continue;
            }
            if (enchantment.method_40225(class_1893.field_9103)) {
                mask |= 2;
                continue;
            }
            if (enchantment.method_40225(class_1893.field_9124)) {
                mask |= 4;
                continue;
            }
            if (!enchantment.method_40225(class_1893.field_9111)) continue;
            mask |= 8;
        }
        return mask;
    }

    private static class_2960[] texturesForMask(int runeMask) {
        class_2960[] textures = new class_2960[Integer.bitCount(runeMask)];
        int index = 0;
        if ((runeMask & 1) != 0) {
            textures[index++] = UNBREAKING_TEXTURE;
        }
        if ((runeMask & 2) != 0) {
            textures[index++] = POWER_TEXTURE;
        }
        if ((runeMask & 4) != 0) {
            textures[index++] = FIRE_TEXTURE;
        }
        if ((runeMask & 8) != 0) {
            textures[index] = PROTECTION_TEXTURE;
        }
        return textures;
    }

    @Environment(value=EnvType.CLIENT)
    public static enum RuneGlintLayerType {
        ARMOR{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getArmorEntityGlint(texture);
            }
        }
        ,
        GUI_DYNAMIC{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getGlint(texture);
            }
        }
        ,
        ITEM{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getGlint(texture);
            }
        }
        ,
        ENTITY{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getEntityGlint(texture);
            }
        }
        ,
        DIRECT_ENTITY{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getDirectEntityGlint(texture);
            }
        }
        ,
        TRANSLUCENT{

            @Override
            class_1921 layer(class_2960 texture) {
                return RuneGlintRenderLayers.getGlintTranslucent(texture);
            }
        };


        abstract class_1921 layer(class_2960 var1);
    }
}

