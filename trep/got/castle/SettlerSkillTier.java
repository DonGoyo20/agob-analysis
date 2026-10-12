/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 */
package trep.got.castle;

import java.util.Locale;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import trep.got.registry.GotItems;

public enum SettlerSkillTier {
    WOOD("wooden"),
    STONE("stone"),
    COPPER("copper"),
    IRON("iron"),
    STEEL("steel");

    private final String texturePrefix;

    private SettlerSkillTier(String texturePrefix) {
        this.texturePrefix = texturePrefix;
    }

    public String getId() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String getDisplayName() {
        String lower = this.getId();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    public String texturePrefix() {
        return this.texturePrefix;
    }

    public class_1792 pickaxeItem() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> class_1802.field_8647;
            case 1 -> class_1802.field_8387;
            case 2 -> GotItems.COPPER_PICKAXE;
            case 3 -> class_1802.field_8403;
            case 4 -> GotItems.STEEL_PICKAXE;
        };
    }

    public class_1792 axeItem() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> class_1802.field_8406;
            case 1 -> class_1802.field_8062;
            case 2 -> GotItems.COPPER_AXE;
            case 3 -> class_1802.field_8475;
            case 4 -> GotItems.STEEL_AXE;
        };
    }

    public class_1792 hoeItem() {
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> class_1802.field_8167;
            case 1 -> class_1802.field_8431;
            case 2 -> GotItems.COPPER_HOE;
            case 3 -> class_1802.field_8609;
            case 4 -> GotItems.STEEL_HOE;
        };
    }

    public static SettlerSkillTier byId(String id) {
        for (SettlerSkillTier tier : SettlerSkillTier.values()) {
            if (!tier.getId().equalsIgnoreCase(id)) continue;
            return tier;
        }
        return WOOD;
    }
}

