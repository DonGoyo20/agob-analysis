/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.class_1297
 *  net.minecraft.class_1536
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_3218
 *  net.minecraft.class_52
 *  net.minecraft.class_8567
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1297;
import net.minecraft.class_1536;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_52;
import net.minecraft.class_8567;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.entity.ShipEntity;
import trep.got.registry.GotItems;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.dimension.GotDimensionKeys;

@Mixin(value={class_1536.class})
public abstract class FishingBobberEntityMixin {
    @Unique
    private boolean agob$hasSettledInWater;
    @Unique
    private class_243 agob$settledOwnerOffset;

    @Inject(method={"method_5773"}, at={@At(value="TAIL")})
    private void agob$followMountedVessel(CallbackInfo ci) {
        boolean inWater;
        class_1536 bobber = (class_1536)this;
        class_1657 owner = bobber.method_6947();
        if (owner == null) {
            this.agob$resetMountedFollow();
            return;
        }
        class_1297 vehicle = owner.method_5854();
        if (!(vehicle instanceof ShipEntity)) {
            this.agob$resetMountedFollow();
            return;
        }
        boolean bl = inWater = bobber.method_5799() || bobber.method_5869();
        if (!this.agob$hasSettledInWater) {
            if (!inWater) {
                return;
            }
            this.agob$hasSettledInWater = true;
            this.agob$settledOwnerOffset = bobber.method_19538().method_1020(owner.method_19538());
            return;
        }
        if (this.agob$settledOwnerOffset == null) {
            this.agob$settledOwnerOffset = bobber.method_19538().method_1020(owner.method_19538());
        }
        class_243 desiredPos = owner.method_19538().method_1019(this.agob$settledOwnerOffset);
        class_243 delta = desiredPos.method_1020(bobber.method_19538());
        class_243 followDelta = new class_243(delta.field_1352 * 0.2, delta.field_1351 * 0.1, delta.field_1350 * 0.2);
        if (followDelta.method_1027() > 1.0E-6) {
            bobber.method_33574(bobber.method_19538().method_1019(followDelta));
        }
    }

    @Unique
    private void agob$resetMountedFollow() {
        this.agob$hasSettledInWater = false;
        this.agob$settledOwnerOffset = null;
    }

    @Redirect(method={"method_6957"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_52;method_51878(Lnet/minecraft/class_8567;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
    private ObjectArrayList<class_1799> agob$replaceEnchantedBookFishingLoot(class_52 lootTable, class_8567 parameters) {
        class_3218 world;
        ObjectArrayList generatedLoot = lootTable.method_51878(parameters);
        class_1536 bobber = (class_1536)this;
        class_1937 class_19372 = bobber.method_37908();
        if (!(class_19372 instanceof class_3218) || !(world = (class_3218)class_19372).method_27983().equals(GotDimensionKeys.ICEANDFIRE)) {
            return generatedLoot;
        }
        boolean isSeaBiome = world.method_23753(bobber.method_24515()).method_40225(GotBiomeKeys.SEA);
        for (int i = 0; i < generatedLoot.size(); ++i) {
            class_1799 stack = (class_1799)generatedLoot.get(i);
            if (!stack.method_31574(class_1802.field_8598)) continue;
            generatedLoot.set(i, (Object)(isSeaBiome ? new class_1799((class_1935)GotItems.RUNE_FRAGMENT, stack.method_7947()) : class_1799.field_8037));
        }
        generatedLoot.removeIf(class_1799::method_7960);
        return generatedLoot;
    }
}

