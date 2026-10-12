/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2586
 *  net.minecraft.class_2680
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.block.entity;

import java.util.UUID;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_7225;
import trep.got.registry.GotBlockEntities;

public final class TentCollisionBlockEntity
extends class_2586 {
    private UUID tentEntityUuid;

    public TentCollisionBlockEntity(class_2338 pos, class_2680 state) {
        super(GotBlockEntities.TENT_COLLISION, pos, state);
    }

    public UUID getTentEntityUuid() {
        return this.tentEntityUuid;
    }

    public void setTentEntityUuid(UUID tentEntityUuid) {
        this.tentEntityUuid = tentEntityUuid;
        this.method_5431();
    }

    protected void method_11007(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11007(nbt, registries);
        if (this.tentEntityUuid != null) {
            nbt.method_25927("tent_entity_uuid", this.tentEntityUuid);
        }
    }

    protected void method_11014(class_2487 nbt, class_7225.class_7874 registries) {
        super.method_11014(nbt, registries);
        this.tentEntityUuid = nbt.method_25928("tent_entity_uuid") ? nbt.method_25926("tent_entity_uuid") : null;
    }
}

