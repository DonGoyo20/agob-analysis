/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.village.resource;

import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;

public abstract class VillageResource {
    private final class_2960 id;
    private final class_2561 displayName;

    public VillageResource(class_2960 id, class_2561 displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public class_2960 getId() {
        return this.id;
    }

    public class_2561 getDisplayName() {
        return this.displayName;
    }

    public abstract class_1799 getIconStack();

    @Nullable
    public class_2960 getIconTexture() {
        return null;
    }

    public boolean isHorse() {
        return false;
    }
}

