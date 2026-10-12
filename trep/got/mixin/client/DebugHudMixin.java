/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2338
 *  net.minecraft.class_310
 *  net.minecraft.class_340
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package trep.got.mixin.client;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import net.minecraft.class_340;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import trep.got.client.map.KingdomAreaClientState;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_340.class})
public class DebugHudMixin {
    @Inject(method={"method_1835"}, at={@At(value="RETURN")}, cancellable=true)
    private void agob$appendKingdomAreaLines(CallbackInfoReturnable<List<String>> cir) {
        ArrayList<CallSite> lines = new ArrayList<CallSite>((Collection)cir.getReturnValue());
        KingdomAreaClientState.AreaEntry area = KingdomAreaClientState.getAreaAt(DebugHudMixin.currentPlayerPos());
        lines.add((CallSite)((Object)("kingdom:" + (area == null ? "none" : area.kingdomName()))));
        lines.add((CallSite)((Object)("castle:" + (area == null ? "none" : area.castleName()))));
        cir.setReturnValue(lines);
    }

    @Nullable
    private static class_2338 currentPlayerPos() {
        class_310 client = class_310.method_1551();
        return client.field_1724 == null ? null : client.field_1724.method_24515();
    }
}

