/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 */
package trep.got.village.resource;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import trep.got.village.resource.VillageResource;

public class ItemVillageResource
extends VillageResource {
    private final class_1792 item;

    public ItemVillageResource(class_2960 id, class_2561 displayName, class_1792 item) {
        super(id, displayName);
        this.item = item;
    }

    @Override
    public class_1799 getIconStack() {
        return new class_1799((class_1935)this.item);
    }
}

