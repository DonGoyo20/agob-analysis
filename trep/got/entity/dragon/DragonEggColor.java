/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3542
 *  net.minecraft.class_5819
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity.dragon;

import java.util.List;
import java.util.Locale;
import net.minecraft.class_3542;
import net.minecraft.class_5819;
import org.jetbrains.annotations.Nullable;

public enum DragonEggColor implements class_3542
{
    BLACK("black", 1),
    BLUE("blue", 2),
    GRAY("gray", 3),
    GREEN("green", 4),
    LIGHT_RED("light_red", 5),
    ORANGE("orange", 6),
    RED("red", 7),
    WHITE("white", 8),
    YELLOW("yellow", 9);

    public static final String NBT_KEY = "DragonEggColor";
    public static final DragonEggColor DEFAULT;
    public static final List<DragonEggColor> VALUES;
    private final String id;
    private final int customModelData;

    private DragonEggColor(String id, int customModelData) {
        this.id = id;
        this.customModelData = customModelData;
    }

    public String id() {
        return this.id;
    }

    public String method_15434() {
        return this.id;
    }

    public int customModelData() {
        return this.customModelData;
    }

    public static DragonEggColor byId(@Nullable String id) {
        if (id == null || id.isBlank()) {
            return DEFAULT;
        }
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (DragonEggColor color : DragonEggColor.values()) {
            if (!color.id.equals(normalized)) continue;
            return color;
        }
        return DEFAULT;
    }

    public static DragonEggColor byCustomModelData(int customModelData) {
        for (DragonEggColor color : DragonEggColor.values()) {
            if (color.customModelData != customModelData) continue;
            return color;
        }
        return DEFAULT;
    }

    public static DragonEggColor random(class_5819 random) {
        return VALUES.get(random.method_43048(VALUES.size()));
    }

    static {
        DEFAULT = RED;
        VALUES = List.of(DragonEggColor.values());
    }
}

