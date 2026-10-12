/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.skill;

import java.util.List;
import net.minecraft.class_2960;

public enum SkillAttributeType {
    HEALTH("health", "Health", "textures/ui/attributes_health.png"),
    INTELLIGENCE("intelligence", "Intelligence", "textures/ui/attributes_intelligence.png"),
    CHARISMA("charisma", "Charisma", "textures/ui/attributes_charisma.png"),
    LUCK("luck", "Luck", "textures/ui/attributes_luck.png"),
    TRADING("trading", "Trading", "textures/ui/attributes_trading.png"),
    SMITHING("smithing", "Smithing", "textures/ui/attributes_smithing.png"),
    RIDING("riding", "Riding", "textures/ui/attributes_riding.png");

    public static final int MIN_POINTS = 0;
    public static final int MAX_POINTS = 100;
    public static final List<SkillAttributeType> ALL;
    private final String id;
    private final String displayName;
    private final class_2960 iconTexture;

    private SkillAttributeType(String id, String displayName, String iconTexturePath) {
        this.id = id;
        this.displayName = displayName;
        this.iconTexture = class_2960.method_60655((String)"agob", (String)iconTexturePath);
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public class_2960 getIconTexture() {
        return this.iconTexture;
    }

    public static SkillAttributeType byId(String id) {
        for (SkillAttributeType type : ALL) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return null;
    }

    static {
        ALL = List.of(SkillAttributeType.values());
    }
}

