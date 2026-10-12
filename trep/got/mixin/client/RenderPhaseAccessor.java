/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_4668
 *  net.minecraft.class_4668$class_4671
 *  net.minecraft.class_4668$class_4672
 *  net.minecraft.class_4668$class_4675
 *  net.minecraft.class_4668$class_4678
 *  net.minecraft.class_4668$class_4684
 *  net.minecraft.class_4668$class_4685
 *  net.minecraft.class_4668$class_4686
 *  net.minecraft.class_4668$class_5942
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_4668.class})
public interface RenderPhaseAccessor {
    @Accessor(value="field_29420")
    public static class_4668.class_5942 agob$getArmorEntityGlintProgram() {
        throw new AssertionError();
    }

    @Accessor(value="field_29421")
    public static class_4668.class_5942 agob$getTranslucentGlintProgram() {
        throw new AssertionError();
    }

    @Accessor(value="field_29422")
    public static class_4668.class_5942 agob$getGlintProgram() {
        throw new AssertionError();
    }

    @Accessor(value="field_29424")
    public static class_4668.class_5942 agob$getEntityGlintProgram() {
        throw new AssertionError();
    }

    @Accessor(value="field_29425")
    public static class_4668.class_5942 agob$getDirectEntityGlintProgram() {
        throw new AssertionError();
    }

    @Accessor(value="field_21368")
    public static class_4668.class_4685 agob$getGlintTransparency() {
        throw new AssertionError();
    }

    @Accessor(value="field_21381")
    public static class_4668.class_4684 agob$getGlintTexturing() {
        throw new AssertionError();
    }

    @Accessor(value="field_21382")
    public static class_4668.class_4684 agob$getEntityGlintTexturing() {
        throw new AssertionError();
    }

    @Accessor(value="field_21350")
    public static class_4668.class_4686 agob$getColorMask() {
        throw new AssertionError();
    }

    @Accessor(value="field_21345")
    public static class_4668.class_4671 agob$getDisableCulling() {
        throw new AssertionError();
    }

    @Accessor(value="field_21347")
    public static class_4668.class_4672 agob$getEqualDepthTest() {
        throw new AssertionError();
    }

    @Accessor(value="field_22241")
    public static class_4668.class_4675 agob$getViewOffsetZLayering() {
        throw new AssertionError();
    }

    @Accessor(value="field_25643")
    public static class_4668.class_4678 agob$getItemEntityTarget() {
        throw new AssertionError();
    }
}

