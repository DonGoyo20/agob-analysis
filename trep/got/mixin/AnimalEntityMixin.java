/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1296
 *  net.minecraft.class_1429
 *  net.minecraft.class_1496
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1296;
import net.minecraft.class_1429;
import net.minecraft.class_1496;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.skill.source.RidingSkillExperienceSource;

@Mixin(value={class_1429.class})
public abstract class AnimalEntityMixin {
    @Inject(method={"method_49794"}, at={@At(value="TAIL")})
    private void got$grantRidingExperienceForHorseBreeding(class_3218 world, class_1429 other, class_1296 baby, CallbackInfo ci) {
        class_1496 firstParent;
        class_1429 self;
        block6: {
            block5: {
                self = (class_1429)this;
                if (!(self instanceof class_1496)) break block5;
                firstParent = (class_1496)self;
                if (other instanceof class_1496) break block6;
            }
            return;
        }
        class_1496 secondParent = (class_1496)other;
        class_3222 player = self.method_6478();
        if (player == null) {
            player = other.method_6478();
        }
        if (player != null) {
            RidingSkillExperienceSource.grantHorseBreedingExperience(player, firstParent, secondParent);
        }
    }
}

