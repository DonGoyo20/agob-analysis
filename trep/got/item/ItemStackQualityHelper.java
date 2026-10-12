/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1304
 *  net.minecraft.class_1322
 *  net.minecraft.class_1322$class_1323
 *  net.minecraft.class_1738
 *  net.minecraft.class_1741
 *  net.minecraft.class_1766
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1811
 *  net.minecraft.class_1819
 *  net.minecraft.class_1829
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_2960
 *  net.minecraft.class_5134
 *  net.minecraft.class_9274
 *  net.minecraft.class_9279
 *  net.minecraft.class_9285
 *  net.minecraft.class_9285$class_9287
 *  net.minecraft.class_9290
 *  net.minecraft.class_9334
 *  net.minecraft.class_9335
 *  net.minecraft.class_9362
 */
package trep.got.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_124;
import net.minecraft.class_1304;
import net.minecraft.class_1322;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1766;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1811;
import net.minecraft.class_1819;
import net.minecraft.class_1829;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_5134;
import net.minecraft.class_9274;
import net.minecraft.class_9279;
import net.minecraft.class_9285;
import net.minecraft.class_9290;
import net.minecraft.class_9334;
import net.minecraft.class_9335;
import net.minecraft.class_9362;
import trep.got.item.BlacksmithHammerItem;
import trep.got.item.SpearItem;
import trep.got.item.VariantShieldItem;

public final class ItemStackQualityHelper {
    private static final String QUALITY_KEY = "Quality";
    private static final String TIER_PREFIX = "Tier: ";
    private static final class_2583 TIER_STYLE = class_2583.field_24360.method_10977(class_124.field_1080).method_10978(Boolean.valueOf(false));
    private static final class_2960 QUALITY_ATTACK_DAMAGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"quality_attack_damage");
    private static final class_2960 QUALITY_ATTACK_SPEED_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"quality_attack_speed");
    private static final class_2960 QUALITY_ENTITY_RANGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"quality_entity_range");
    private static final class_2960 QUALITY_BLOCK_RANGE_MODIFIER_ID = class_2960.method_60655((String)"agob", (String)"quality_block_range");

    private ItemStackQualityHelper() {
    }

    public static void synchronize(class_1799 stack, class_9335 components) {
        if (stack.method_7960()) {
            return;
        }
        if (!ItemStackQualityHelper.isApplicable(stack.method_7909())) {
            return;
        }
        Quality quality = ItemStackQualityHelper.getOrCreateQuality(components);
        ItemStackQualityHelper.synchronizeLore(stack, components, quality);
        ItemStackQualityHelper.synchronizeDurability(stack, components, quality);
        ItemStackQualityHelper.synchronizeAttributes(stack, components, quality);
    }

    public static Quality getQuality(class_1799 stack) {
        class_9279 customData = (class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302);
        if (customData.method_57458()) {
            return Quality.DECENT;
        }
        return Quality.fromString(customData.method_57461().method_10558(QUALITY_KEY));
    }

    public static float getRangedSpeedMultiplier(class_1799 stack) {
        if (!ItemStackQualityHelper.isApplicable(stack.method_7909())) {
            return 1.0f;
        }
        return 1.0f + (float)ItemStackQualityHelper.getQuality(stack).durabilitySteps() * 0.05f;
    }

    public static boolean isQualityApplicable(class_1799 stack) {
        return stack != null && !stack.method_7960() && ItemStackQualityHelper.isApplicable(stack.method_7909());
    }

    public static void setQuality(class_1799 stack, Quality quality) {
        if (!ItemStackQualityHelper.isQualityApplicable(stack) || quality == null) {
            return;
        }
        class_2487 customData = ((class_9279)stack.method_57825(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        customData.method_10582(QUALITY_KEY, quality.id());
        stack.method_57379(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)customData));
    }

    private static boolean isApplicable(class_1792 item) {
        return item instanceof class_1738 || item instanceof class_1829 || item instanceof class_9362 || item instanceof class_1766 || item instanceof SpearItem || item instanceof BlacksmithHammerItem || item instanceof class_1811 || item instanceof class_1819 || item instanceof VariantShieldItem;
    }

    private static Quality getOrCreateQuality(class_9335 components) {
        class_2487 customData = ((class_9279)components.method_57830(class_9334.field_49628, (Object)class_9279.field_49302)).method_57461();
        Quality quality = Quality.fromString(customData.method_10558(QUALITY_KEY));
        if (!customData.method_10545(QUALITY_KEY) || !quality.id().equals(customData.method_10558(QUALITY_KEY).toLowerCase(Locale.ROOT))) {
            customData.method_10582(QUALITY_KEY, quality.id());
            components.method_57938(class_9334.field_49628, (Object)class_9279.method_57456((class_2487)customData));
        }
        return quality;
    }

    private static void synchronizeLore(class_1799 stack, class_9335 components, Quality quality) {
        class_9290 currentLore = (class_9290)components.method_57830(class_9334.field_49632, (Object)class_9290.field_49340);
        ArrayList<class_2561> lines = new ArrayList<class_2561>(currentLore.comp_2400().size() + 1);
        lines.add(ItemStackQualityHelper.tierLine(quality));
        for (class_2561 line : currentLore.comp_2400()) {
            if (line.getString().startsWith(TIER_PREFIX)) continue;
            lines.add(line);
        }
        components.method_57938(class_9334.field_49632, (Object)new class_9290(List.copyOf(lines)));
    }

    private static void synchronizeDurability(class_1799 stack, class_9335 components, Quality quality) {
        int baseMaxDamage = (Integer)stack.method_58658().method_57830(class_9334.field_50072, (Object)0);
        if (baseMaxDamage <= 0) {
            return;
        }
        int currentMaxDamage = (Integer)components.method_57830(class_9334.field_50072, (Object)baseMaxDamage);
        int currentDamage = (Integer)components.method_57830(class_9334.field_49629, (Object)0);
        int adjustedMaxDamage = Math.max(1, Math.round((float)baseMaxDamage * (1.0f + (float)quality.durabilitySteps() * 0.1f)));
        int adjustedDamage = currentMaxDamage > 0 ? Math.round((float)currentDamage / (float)currentMaxDamage * (float)adjustedMaxDamage) : currentDamage;
        components.method_57938(class_9334.field_50072, (Object)adjustedMaxDamage);
        components.method_57938(class_9334.field_49629, (Object)Math.max(0, Math.min(adjustedDamage, adjustedMaxDamage)));
    }

    private static void synchronizeAttributes(class_1799 stack, class_9335 components, Quality quality) {
        class_1792 item = stack.method_7909();
        class_9285 baseComponent = ItemStackQualityHelper.getBaseAttributeModifiers(stack, components);
        ArrayList<class_9285.class_9287> modifiers = new ArrayList<class_9285.class_9287>(baseComponent.comp_2393());
        boolean changed = false;
        if (item instanceof class_1738) {
            float knockbackResistance;
            class_1738 armorItem = (class_1738)item;
            class_9274 slot = class_9274.method_59524((class_1304)armorItem.method_7685());
            ArrayList<class_9285.class_9287> armorModifiers = new ArrayList<class_9285.class_9287>();
            armorModifiers.add(new class_9285.class_9287(class_5134.field_23724, new class_1322(ItemStackQualityHelper.qualityArmorModifierId(slot), (double)armorItem.method_7687() + quality.powerBonus(), class_1322.class_1323.field_6328), slot));
            if (armorItem.method_26353() != 0.0f) {
                armorModifiers.add(new class_9285.class_9287(class_5134.field_23725, new class_1322(ItemStackQualityHelper.qualityArmorToughnessModifierId(slot), (double)armorItem.method_26353(), class_1322.class_1323.field_6328), slot));
            }
            if ((knockbackResistance = ((class_1741)armorItem.method_7686().comp_349()).comp_2304()) != 0.0f) {
                armorModifiers.add(new class_9285.class_9287(class_5134.field_23718, new class_1322(ItemStackQualityHelper.qualityArmorKnockbackModifierId(slot), (double)knockbackResistance, class_1322.class_1323.field_6328), slot));
            }
            components.method_57938(class_9334.field_49636, (Object)new class_9285(List.copyOf(armorModifiers), true));
            return;
        }
        if (ItemStackQualityHelper.isMainHandWeapon(item)) {
            modifiers.add(new class_9285.class_9287(class_5134.field_23721, new class_1322(QUALITY_ATTACK_DAMAGE_MODIFIER_ID, quality.powerBonus(), class_1322.class_1323.field_6328), class_9274.field_49217));
            modifiers.add(new class_9285.class_9287(class_5134.field_23723, new class_1322(QUALITY_ATTACK_SPEED_MODIFIER_ID, quality.attackSpeedBonus(), class_1322.class_1323.field_6328), class_9274.field_49217));
            changed = true;
        }
        if (item instanceof class_1829 || item instanceof SpearItem) {
            modifiers.add(new class_9285.class_9287(class_5134.field_47759, new class_1322(QUALITY_ENTITY_RANGE_MODIFIER_ID, quality.entityRangeBonus(), class_1322.class_1323.field_6328), class_9274.field_49217));
            changed = true;
        }
        if (item instanceof class_1766) {
            modifiers.add(new class_9285.class_9287(class_5134.field_47758, new class_1322(QUALITY_BLOCK_RANGE_MODIFIER_ID, quality.blockRangeBonus(), class_1322.class_1323.field_6328), class_9274.field_49217));
            changed = true;
        }
        if (changed || !baseComponent.comp_2393().isEmpty()) {
            components.method_57938(class_9334.field_49636, (Object)new class_9285(List.copyOf(modifiers), true));
        }
    }

    private static class_9285 getDefaultAttributeModifiers(class_1799 stack) {
        class_9285 defaultModifiers = (class_9285)stack.method_58658().method_57830(class_9334.field_49636, (Object)class_9285.field_49326);
        if (!defaultModifiers.comp_2393().isEmpty()) {
            return defaultModifiers;
        }
        return stack.method_7909().method_7844();
    }

    private static class_9285 getBaseAttributeModifiers(class_1799 stack, class_9335 components) {
        class_9285 current = (class_9285)components.method_57830(class_9334.field_49636, (Object)class_9285.field_49326);
        List<class_9285.class_9287> filtered = current.comp_2393().stream().filter(entry -> !ItemStackQualityHelper.isQualityModifier(entry.comp_2396().comp_2447())).toList();
        if (!filtered.isEmpty()) {
            return new class_9285(filtered, current.comp_2394());
        }
        return ItemStackQualityHelper.getDefaultAttributeModifiers(stack);
    }

    private static boolean isMainHandWeapon(class_1792 item) {
        return item instanceof class_1829 || item instanceof class_1766 || item instanceof SpearItem || item instanceof BlacksmithHammerItem;
    }

    private static boolean isQualityModifier(class_2960 id) {
        return id.equals((Object)QUALITY_ATTACK_DAMAGE_MODIFIER_ID) || id.equals((Object)QUALITY_ATTACK_SPEED_MODIFIER_ID) || id.method_12836().equals("agob") && id.method_12832().startsWith("quality_armor_") || id.equals((Object)QUALITY_ENTITY_RANGE_MODIFIER_ID) || id.equals((Object)QUALITY_BLOCK_RANGE_MODIFIER_ID);
    }

    private static class_2960 qualityArmorModifierId(class_9274 slot) {
        return class_2960.method_60655((String)"agob", (String)("quality_armor_" + slot.method_15434()));
    }

    private static class_2960 qualityArmorToughnessModifierId(class_9274 slot) {
        return class_2960.method_60655((String)"agob", (String)("quality_armor_toughness_" + slot.method_15434()));
    }

    private static class_2960 qualityArmorKnockbackModifierId(class_9274 slot) {
        return class_2960.method_60655((String)"agob", (String)("quality_armor_knockback_" + slot.method_15434()));
    }

    private static class_2561 tierLine(Quality quality) {
        return class_2561.method_43470((String)(TIER_PREFIX + quality.displayName())).method_10862(TIER_STYLE);
    }

    public static enum Quality {
        TERRIBLE("terrible", "Terrible", -2, -1.5),
        BAD("bad", "Bad", -1, -1.0),
        DECENT("decent", "Decent", 0, 0.0),
        GOOD("good", "Good", 1, 0.5),
        PERFECT("perfect", "Perfect", 2, 1.0),
        LEGENDARY("legendary", "Legendary", 3, 1.5);

        private final String id;
        private final String displayName;
        private final int durabilitySteps;
        private final double powerBonus;

        private Quality(String id, String displayName, int durabilitySteps, double powerBonus) {
            this.id = id;
            this.displayName = displayName;
            this.durabilitySteps = durabilitySteps;
            this.powerBonus = powerBonus;
        }

        public String id() {
            return this.id;
        }

        public String displayName() {
            return this.displayName;
        }

        public int durabilitySteps() {
            return this.durabilitySteps;
        }

        public double powerBonus() {
            return this.powerBonus;
        }

        public double attackSpeedBonus() {
            return (double)this.durabilitySteps * 0.1;
        }

        public double entityRangeBonus() {
            return (double)this.durabilitySteps * 0.15;
        }

        public double blockRangeBonus() {
            return (double)this.durabilitySteps * 0.1;
        }

        public static Quality fromString(String value) {
            if (value != null) {
                String normalized = value.trim().toLowerCase(Locale.ROOT);
                for (Quality quality : Quality.values()) {
                    if (!quality.id.equals(normalized)) continue;
                    return quality;
                }
            }
            return DECENT;
        }
    }
}

