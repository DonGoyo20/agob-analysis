/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1921
 *  net.minecraft.class_1921$class_4687
 *  net.minecraft.class_1921$class_4688
 *  net.minecraft.class_293
 *  net.minecraft.class_293$class_5596
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_293;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_1921.class})
public interface RenderLayerAccessor {
    @Invoker(value="method_24048")
    public static class_1921.class_4687 agob$invokeOf(String name, class_293 format, class_293.class_5596 drawMode, int expectedBufferSize, class_1921.class_4688 parameters) {
        throw new AssertionError();
    }
}

