/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1308
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin;

import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.item.SpearItem;

@Mixin(value={class_1308.class})
public class MobEntitySpearMixin {
    @Inject(method={"method_42150"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$extendSpearReach(class_1309 target, CallbackInfoReturnable<Boolean> cir) {
        class_1308 self = (class_1308)this;
        class_1792 class_17922 = self.method_6047().method_7909();
        if (!(class_17922 instanceof SpearItem)) {
            return;
        }
        SpearItem spear = (SpearItem)class_17922;
        double distance = self.method_5739((class_1297)target);
        boolean facingTarget = self.method_5631(self.method_36455(), self.method_43078()).method_1026(target.method_19538().method_1020(self.method_19538()).method_1029()) > (double)spear.hitboxMargin();
        cir.setReturnValue((Object)(distance >= (double)spear.minReach() && distance <= (double)spear.maxReach() && facingTarget ? 1 : 0));
    }

    @Inject(method={"method_6121"}, at={@At(value="HEAD")}, cancellable=true)
    private void agob$attackWithSpear(class_1297 target, CallbackInfoReturnable<Boolean> cir) {
        class_1308 class_13082;
        class_1308 self = (class_1308)this;
        class_1792 class_17922 = self.method_6047().method_7909();
        if (!(class_17922 instanceof SpearItem)) {
            return;
        }
        SpearItem spear = (SpearItem)class_17922;
        if (target instanceof class_1309) {
            class_1309 living = (class_1309)target;
            class_13082 = living;
        } else {
            class_13082 = self;
        }
        self.method_5951((class_1297)class_13082, 30.0f, 30.0f);
        self.method_5636(self.method_5791());
        cir.setReturnValue((Object)spear.stab((class_1309)self, class_1268.field_5808));
    }
}

