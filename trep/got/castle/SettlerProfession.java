/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 */
package trep.got.castle;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import trep.got.castle.SettlerSkillTier;

public enum SettlerProfession {
    MINER("miner", "Miner"),
    LUMBERJACK("lumberjack", "Lumberjack"),
    FARMER("farmer", "Farmer");

    private final String id;
    private final String displayName;

    private SettlerProfession(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public class_1799 indicatorFor(SettlerSkillTier tier) {
        class_1792 item = switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> tier.pickaxeItem();
            case 1 -> tier.axeItem();
            case 2 -> tier.hoeItem();
        };
        return new class_1799((class_1935)(item == null ? class_1802.field_8600 : item));
    }

    public class_2960 iconTextureId(SettlerSkillTier tier) {
        return class_2960.method_60655((String)"agob", (String)("textures/item/" + (switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> tier.texturePrefix() + "_pickaxe";
            case 1 -> tier.texturePrefix() + "_axe";
            case 2 -> tier.texturePrefix() + "_hoe";
        }) + ".png"));
    }

    public static SettlerProfession byId(String id) {
        for (SettlerProfession profession : SettlerProfession.values()) {
            if (!profession.id.equalsIgnoreCase(id)) continue;
            return profession;
        }
        return FARMER;
    }
}

