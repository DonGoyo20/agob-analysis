/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1703
 *  net.minecraft.class_1735
 *  net.minecraft.class_2371
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package trep.got.mixin;

import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1703.class})
public interface ScreenHandlerAccessor {
    @Accessor(value="field_7761")
    public class_2371<class_1735> getSlots();
}

