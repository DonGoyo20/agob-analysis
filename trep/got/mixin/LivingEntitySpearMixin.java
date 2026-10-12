/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1792
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.compat.BetterCombatCompat;
import trep.got.item.SpearCombat;
import trep.got.item.SpearItem;
import trep.got.item.SpearUser;

@Mixin(value={class_1309.class})
public class LivingEntitySpearMixin
implements SpearUser {
    @Unique
    private final HashMap<Integer, Long> agob$spearPiercingTimes = new HashMap();

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void agob$cleanupSpearTimes(CallbackInfo ci) {
        class_1792 class_17922;
        class_1309 self = (class_1309)this;
        long now = self.method_37908().method_8510();
        Iterator<Map.Entry<Integer, Long>> iterator = this.agob$spearPiercingTimes.entrySet().iterator();
        while (iterator.hasNext()) {
            if (now - iterator.next().getValue() <= 40L) continue;
            iterator.remove();
        }
        if (!self.method_37908().method_8608() && BetterCombatCompat.shouldUseNativePlayerSpearControls(self) && (class_17922 = self.method_6118(class_1304.field_6173).method_7909()) instanceof SpearItem) {
            SpearItem spear = (SpearItem)class_17922;
            if (SpearCombat.performMountedLanceTick(self, spear)) {
                self.method_6047().method_7970(1, self, class_1304.field_6173);
            }
            if (SpearCombat.performBraceTick(self, spear)) {
                self.method_6047().method_7970(1, self, class_1304.field_6173);
            }
        }
    }

    @Override
    public boolean agob$isInSpearPiercingCooldown(class_1297 target, int cooldownTicks) {
        class_1309 self = (class_1309)this;
        if (target == null) {
            return false;
        }
        Long last = this.agob$spearPiercingTimes.get(target.method_5628());
        return last != null && self.method_37908().method_8510() - last < (long)cooldownTicks;
    }

    @Override
    public void agob$startSpearPiercingCooldown(class_1297 target) {
        class_1309 self = (class_1309)this;
        if (target != null) {
            this.agob$spearPiercingTimes.put(target.method_5628(), self.method_37908().method_8510());
        }
    }

    @Override
    public void agob$markSpearChargeHit() {
    }

    @Override
    public void agob$markSpearBraceHit() {
    }
}

