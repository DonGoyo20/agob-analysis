/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1661
 *  net.minecraft.class_1703
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_481
 *  net.minecraft.class_481$class_483
 *  net.minecraft.class_485
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_481;
import net.minecraft.class_485;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.client.gui.CharacterMenuClientState;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_481.class})
public abstract class CreativeInventoryScreenMixin
extends class_485<class_481.class_483> {
    private CreativeInventoryScreenMixin(class_481.class_483 handler, class_1661 inventory, class_2561 title) {
        super((class_1703)handler, inventory, title);
    }

    @Inject(method={"method_25426"}, at={@At(value="TAIL")})
    private void got$closeCreativeCharacterMenu(CallbackInfo ci) {
        CharacterMenuClientState.setCreativeOpen(this.field_22787, false);
        CharacterMenuClientState.setPartyOpen(this.field_22787, false);
    }

    @Inject(method={"method_25394"}, at={@At(value="HEAD")})
    private void got$closeCreativeCharacterMenuOnRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (CharacterMenuClientState.isCreativeOpen() || CharacterMenuClientState.isPartyOpen()) {
            CharacterMenuClientState.setCreativeOpen(this.field_22787, false);
            CharacterMenuClientState.setPartyOpen(this.field_22787, false);
        }
    }
}

