/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1271
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_1792$class_1793
 *  net.minecraft.class_1792$class_9635
 *  net.minecraft.class_1799
 *  net.minecraft.class_1836
 *  net.minecraft.class_1838
 *  net.minecraft.class_1839
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  net.minecraft.class_3468
 *  net.minecraft.class_4174
 *  net.minecraft.class_4174$class_9423
 *  net.minecraft.class_5328
 *  net.minecraft.class_9279
 *  net.minecraft.class_9334
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.item;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1271;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.class_1839;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3468;
import net.minecraft.class_4174;
import net.minecraft.class_5328;
import net.minecraft.class_9279;
import net.minecraft.class_9334;
import org.jetbrains.annotations.Nullable;
import trep.got.item.CupPlacementHelper;

public class BrewItem
extends class_1792 {
    private static final String AGE_DAYS_KEY = "AgeDays";
    private final class_4174 foodComponent;
    private final List<class_2561> lore;
    @Nullable
    private final class_1792 emptyCupItem;

    public BrewItem(class_1792.class_1793 settings, class_4174 foodComponent, @Nullable class_1792 emptyCupItem, class_2561 ... lore) {
        super(settings.method_19265(foodComponent));
        this.foodComponent = foodComponent;
        this.emptyCupItem = emptyCupItem;
        this.lore = List.of(lore);
    }

    public class_1271<class_1799> method_7836(class_1937 world, class_1657 user, class_1268 hand) {
        return class_5328.method_29282((class_1937)world, (class_1657)user, (class_1268)hand);
    }

    public class_1269 method_7884(class_1838 context) {
        return CupPlacementHelper.placeCup(context);
    }

    public class_1839 method_7853(class_1799 stack) {
        return class_1839.field_8946;
    }

    public int method_7881(class_1799 stack, class_1309 user) {
        return 32;
    }

    public class_3414 method_21831() {
        return class_3417.field_20613;
    }

    public class_3414 method_21830() {
        return class_3417.field_20613;
    }

    public void method_7851(class_1799 stack, class_1792.class_9635 context, List<class_2561> tooltip, class_1836 type) {
        for (class_2561 line : this.lore) {
            tooltip.add((class_2561)line.method_27661().method_27692(class_124.field_1080));
        }
        int ageDays = BrewItem.getAgeDays(stack);
        if (ageDays > 0) {
            tooltip.add((class_2561)class_2561.method_43470((String)("Aged " + ageDays + " days")).method_27692(class_124.field_1077));
        }
    }

    public class_1799 method_7861(class_1799 stack, class_1937 world, class_1309 user) {
        class_1799 result = super.method_7861(stack, world, user);
        if (!world.field_9236) {
            BrewItem.applyAgeBonus(user, stack);
        }
        if (user instanceof class_1657) {
            class_1657 player = (class_1657)user;
            player.method_7259(class_3468.field_15372.method_14956((Object)this));
            if (this.emptyCupItem != null && !player.method_31549().field_7477) {
                class_1799 emptyCup = new class_1799((class_1935)this.emptyCupItem);
                if (result.method_7960()) {
                    return emptyCup;
                }
                if (!player.method_31548().method_7394(emptyCup)) {
                    player.method_7328(emptyCup, false);
                }
            }
        }
        return result;
    }

    public class_1799 consumePlacedCup(class_1937 world, class_1657 player, class_1799 stack) {
        player.method_7344().method_7585(this.foodComponent.comp_2491(), this.foodComponent.comp_2492());
        for (class_4174.class_9423 effectEntry : this.foodComponent.comp_2495()) {
            if (!(world.field_9229.method_43057() <= effectEntry.comp_2497())) continue;
            player.method_6092(new class_1293(effectEntry.comp_2496()));
        }
        if (!world.field_9236) {
            BrewItem.applyAgeBonus((class_1309)player, stack);
        }
        player.method_7259(class_3468.field_15372.method_14956((Object)this));
        world.method_43128(null, player.method_23317(), player.method_23318(), player.method_23321(), this.method_21831(), player.method_5634(), 1.0f, 1.0f);
        return this.emptyCupItem == null ? class_1799.field_8037 : new class_1799((class_1935)this.emptyCupItem);
    }

    public class_4174 getFoodComponentDefinition() {
        return this.foodComponent;
    }

    @Nullable
    public class_1792 getEmptyCupItem() {
        return this.emptyCupItem;
    }

    public static int getAgeDays(class_1799 stack) {
        class_9279 customData = (class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302);
        if (customData.method_57458()) {
            return 0;
        }
        return customData.method_57461().method_10550(AGE_DAYS_KEY);
    }

    public static void setAgeDays(class_1799 stack, int ageDays) {
        class_2487 customData = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        customData.method_10569(AGE_DAYS_KEY, Math.max(0, ageDays));
        stack.method_57379(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)customData));
    }

    public static class_1799 withAge(class_1799 stack, int ageDays) {
        class_1799 aged = stack.method_7972();
        BrewItem.setAgeDays(aged, ageDays);
        return aged;
    }

    public static class_2561 itemLoreLine(String text) {
        return class_2561.method_43470((String)text);
    }

    private static void applyAgeBonus(class_1309 user, class_1799 stack) {
        int ageDays = BrewItem.getAgeDays(stack);
        if (ageDays >= 2) {
            user.method_6092(new class_1293(class_1294.field_5922, 200, Math.max(0, ageDays / 2 - 1)));
        }
    }
}

