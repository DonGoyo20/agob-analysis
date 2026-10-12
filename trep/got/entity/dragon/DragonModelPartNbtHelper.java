/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 */
package trep.got.entity.dragon;

import java.util.Optional;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import trep.got.entity.dragon.DragonModelParts;

public final class DragonModelPartNbtHelper {
    public static final String PARTS_KEY = "agob.dragon_model_parts";
    public static final String PARTS_ALIAS_KEY = "DragonParts";

    private DragonModelPartNbtHelper() {
    }

    public static Optional<DragonModelParts> read(class_2487 nbt) {
        if (nbt.method_10573(PARTS_KEY, 10)) {
            return Optional.of(DragonModelParts.fromNbt(nbt.method_10562(PARTS_KEY)));
        }
        if (nbt.method_10573(PARTS_ALIAS_KEY, 10)) {
            return Optional.of(DragonModelParts.fromNbt(nbt.method_10562(PARTS_ALIAS_KEY)));
        }
        if (nbt.method_10573(PARTS_KEY, 8)) {
            return Optional.of(DragonModelParts.decode(nbt.method_10558(PARTS_KEY)));
        }
        if (nbt.method_10573(PARTS_ALIAS_KEY, 8)) {
            return Optional.of(DragonModelParts.decode(nbt.method_10558(PARTS_ALIAS_KEY)));
        }
        return Optional.empty();
    }

    public static void write(class_2487 nbt, DragonModelParts parts) {
        if (parts == null || parts.isDefault()) {
            return;
        }
        class_2487 partsNbt = parts.toNbt();
        nbt.method_10566(PARTS_KEY, (class_2520)partsNbt.method_10553());
        nbt.method_10566(PARTS_ALIAS_KEY, (class_2520)partsNbt.method_10553());
    }
}

