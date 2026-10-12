/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_2338
 *  net.minecraft.class_2791
 *  net.minecraft.class_3233
 *  net.minecraft.class_4076
 *  net.minecraft.class_9770
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2791;
import net.minecraft.class_3233;
import net.minecraft.class_4076;
import net.minecraft.class_9770;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_3233.class})
public abstract class ChunkRegionSetBlockMixin {
    @Shadow
    @Final
    private class_2791 field_28557;
    @Shadow
    @Final
    private class_9770 field_51876;

    @Inject(method={"method_37368"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$rejectFarChunkWritesWithoutError(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
        class_1923 centerChunk = this.field_28557.method_12004();
        int chunkX = class_4076.method_18675((int)pos.method_10263());
        int chunkZ = class_4076.method_18675((int)pos.method_10260());
        int writeRadius = this.field_51876.comp_2806();
        if (Math.abs(centerChunk.field_9181 - chunkX) > writeRadius || Math.abs(centerChunk.field_9180 - chunkZ) > writeRadius) {
            cir.setReturnValue((Object)false);
        }
    }
}

