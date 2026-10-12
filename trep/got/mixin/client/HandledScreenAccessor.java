/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_465
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_465.class})
public interface HandledScreenAccessor {
    @Accessor(value="field_2776")
    public int agob$getX();

    @Accessor(value="field_2800")
    public int agob$getY();

    @Accessor(value="field_2792")
    public int agob$getBackgroundWidth();

    @Accessor(value="field_2779")
    public int agob$getBackgroundHeight();
}

