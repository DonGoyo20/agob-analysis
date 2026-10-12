/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2168
 *  net.minecraft.class_3149
 *  net.minecraft.class_3218
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_2168;
import net.minecraft.class_3149;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.day.DailyTaskRegistry;
import trep.got.registry.GotCastles;
import trep.got.relations.KingdomWarService;
import trep.got.world.dimension.GotDimensionKeys;

@Mixin(value={class_3149.class})
public abstract class TimeCommandMixin {
    @Inject(method={"method_13784"}, at={@At(value="RETURN")})
    private static void agob$queueKingdomWarDayOnTimeSet(class_2168 source, int time, CallbackInfoReturnable<Integer> cir) {
        if (source != null && source.method_9211() != null) {
            class_3218 world = source.method_9211().method_3847(GotDimensionKeys.ICEANDFIRE);
            DailyTaskRegistry.queueManualDay(world);
            GotCastles.onExplicitTimeSet(world);
        }
        KingdomWarService.onExplicitTimeSet(source);
    }
}

