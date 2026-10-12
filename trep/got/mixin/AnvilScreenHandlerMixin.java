/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1661
 *  net.minecraft.class_1706
 *  net.minecraft.class_1735
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1890
 *  net.minecraft.class_3914
 *  net.minecraft.class_3915
 *  net.minecraft.class_3917
 *  net.minecraft.class_4861
 *  net.minecraft.class_6880
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_9304
 *  net.minecraft.class_9304$class_9305
 *  net.minecraft.class_9334
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import net.minecraft.class_1661;
import net.minecraft.class_1706;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_3914;
import net.minecraft.class_3915;
import net.minecraft.class_3917;
import net.minecraft.class_4861;
import net.minecraft.class_6880;
import net.minecraft.class_7225;
import net.minecraft.class_9304;
import net.minecraft.class_9334;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.item.BlacksmithHammerRules;
import trep.got.item.RuneItem;
import trep.got.world.dimension.GotDimensionKeys;

@Mixin(value={class_1706.class})
abstract class AnvilScreenHandlerMixin
extends class_4861 {
    @Shadow
    @Final
    private class_3915 field_7770;
    @Shadow
    private int field_7776;

    protected AnvilScreenHandlerMixin(class_3917<?> type, int syncId, class_1661 playerInventory, class_3914 context) {
        super(type, syncId, playerInventory, context);
    }

    @Redirect(method={"method_24928"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_1799;method_31574(Lnet/minecraft/class_1792;)Z", ordinal=1))
    private boolean got$allowRuneItemsInBookSlot(class_1799 stack, class_1792 item) {
        return stack.method_31574(item) || item == class_1802.field_8598 && stack.method_7909() instanceof RuneItem;
    }

    @Inject(method={"method_24928"}, at={@At(value="RETURN")})
    private void got$restrictHammerEnchantments(CallbackInfo ci) {
        class_1706 self = (class_1706)this;
        class_1735 inputSlot = self.method_7611(0);
        class_1735 materialSlot = self.method_7611(1);
        class_1735 outputSlot = self.method_7611(2);
        class_1799 inputStack = inputSlot.method_7677();
        class_1799 materialStack = materialSlot.method_7677();
        class_1799 outputStack = outputSlot.method_7677();
        class_1792 class_17922 = materialStack.method_7909();
        if (class_17922 instanceof RuneItem) {
            RuneItem runeItem = (RuneItem)class_17922;
            class_1799 runeResult = this.got$buildRuneAnvilResult(inputStack, materialStack, runeItem);
            if (!runeResult.method_7960()) {
                outputSlot.method_7673(runeResult);
                this.field_7770.method_17404(this.got$getRuneAnvilCost(materialStack, runeItem));
                this.field_7776 = 1;
                self.method_7623();
                return;
            }
            outputSlot.method_7673(class_1799.field_8037);
            this.field_7770.method_17404(0);
            this.field_7776 = 0;
            self.method_7623();
            return;
        }
        if (!BlacksmithHammerRules.isHammer(inputStack) || outputStack.method_7960()) {
            if (this.field_22482.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE) && materialStack.method_31574(class_1802.field_8598)) {
                outputSlot.method_7673(class_1799.field_8037);
                this.field_7770.method_17404(0);
                self.method_7623();
            }
            return;
        }
        if (!(!BlacksmithHammerRules.hasOnlyAllowedEnchantments(outputStack) || this.field_22482.method_37908().method_27983().equals(GotDimensionKeys.ICEANDFIRE) && materialStack.method_31574(class_1802.field_8598))) {
            return;
        }
        outputSlot.method_7673(class_1799.field_8037);
        this.field_7770.method_17404(0);
        self.method_7623();
    }

    private class_1799 got$buildRuneAnvilResult(class_1799 inputStack, class_1799 runeStack, RuneItem runeItem) {
        int incomingLevel;
        if (inputStack.method_7960() || inputStack.method_31574(class_1802.field_8598) || !class_1890.method_57529((class_1799)inputStack)) {
            return class_1799.field_8037;
        }
        class_6880<class_1887> runeEnchantment = runeItem.getEnchantmentEntry((class_7225.class_7874)this.field_22482.method_37908().method_30349());
        int currentLevel = class_1890.method_8225(runeEnchantment, (class_1799)inputStack);
        int targetLevel = currentLevel == (incomingLevel = runeItem.getAppliedLevel(runeStack)) ? incomingLevel + 1 : Math.max(currentLevel, incomingLevel);
        targetLevel = Math.min(targetLevel, ((class_1887)runeEnchantment.comp_349()).method_8183());
        boolean acceptable = ((class_1887)runeEnchantment.comp_349()).method_8192(inputStack);
        if (!acceptable) {
            return class_1799.field_8037;
        }
        class_9304 existing = class_1890.method_57532((class_1799)inputStack);
        for (class_6880 existingEnchantment : existing.method_57534()) {
            if (existingEnchantment.equals(runeEnchantment) || class_1887.method_60033(runeEnchantment, (class_6880)existingEnchantment)) continue;
            return class_1799.field_8037;
        }
        class_1799 result = inputStack.method_7972();
        class_9304.class_9305 builder = new class_9304.class_9305(existing);
        builder.method_57547(runeEnchantment, targetLevel);
        result.method_57379(class_9334.field_49633, (Object)builder.method_57549());
        return result;
    }

    private int got$getRuneAnvilCost(class_1799 runeStack, RuneItem runeItem) {
        int level = Math.max(1, runeItem.getAppliedLevel(runeStack));
        return Math.max(1, ((class_1887)runeItem.getEnchantmentEntry((class_7225.class_7874)this.field_22482.method_37908().method_30349()).comp_349()).method_58446() * level);
    }
}

