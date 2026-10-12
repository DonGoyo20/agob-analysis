/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
 *  net.minecraft.class_124
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_310
 *  net.minecraft.class_5455
 */
package trep.got.client.item;

import java.util.List;
import java.util.Locale;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.class_124;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5455;
import trep.got.item.RangedWeaponStatHelper;
import trep.got.item.RangedWeaponStats;

@Environment(value=EnvType.CLIENT)
public final class RangedWeaponTooltipHelper {
    private static final class_2583 HEADER_STYLE = class_2583.field_24360.method_10977(class_124.field_1080).method_10978(Boolean.valueOf(false));
    private static final class_2583 VALUE_STYLE = class_2583.field_24360.method_10977(class_124.field_1077).method_10978(Boolean.valueOf(false));

    private RangedWeaponTooltipHelper() {
    }

    public static void initialize() {
        ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> RangedWeaponTooltipHelper.appendTooltip(stack, tooltip));
    }

    private static void appendTooltip(class_1799 stack, List<class_2561> tooltip) {
        class_310 client = class_310.method_1551();
        class_5455 registryManager = client.field_1687 == null ? null : client.field_1687.method_30349();
        RangedWeaponStats stats = RangedWeaponStatHelper.resolve(stack, (class_1309)client.field_1724, registryManager);
        if (stats == null) {
            return;
        }
        tooltip.add((class_2561)class_2561.method_43473());
        tooltip.add((class_2561)class_2561.method_43470((String)"When firing:").method_10862(HEADER_STYLE));
        tooltip.add((class_2561)class_2561.method_43470((String)("Attack Damage " + stats.attackDamage())).method_10862(VALUE_STYLE));
        tooltip.add((class_2561)class_2561.method_43470((String)("Reload Speed " + RangedWeaponTooltipHelper.formatReload(stats))).method_10862(VALUE_STYLE));
    }

    private static String formatReload(RangedWeaponStats stats) {
        return String.format(Locale.ROOT, "%.2fs", stats.reloadSeconds());
    }
}

