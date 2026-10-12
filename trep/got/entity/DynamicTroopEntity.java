/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopLoadoutApplier;
import trep.got.troop.DynamicTroopRegistry;
import trep.got.troop.TroopStatResolver;

public class DynamicTroopEntity
extends AbstractDialogueHumanoidEntity {
    private static final class_2940<String> TROOP_ID = class_2945.method_12791(DynamicTroopEntity.class, (class_2941)class_2943.field_13326);
    private static final String TROOP_ID_KEY = "gotmod.troop_id";
    private DynamicTroopDefinition cachedDefinition;

    public DynamicTroopEntity(class_1299<? extends DynamicTroopEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(TROOP_ID, (Object)"");
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(TROOP_ID_KEY, this.getTroopId());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(TROOP_ID_KEY)) {
            this.setTroopId(nbt.method_10558(TROOP_ID_KEY));
        }
    }

    @Override
    public void method_5674(class_2940<?> data) {
        super.method_5674(data);
        if (TROOP_ID.equals(data)) {
            this.cachedDefinition = null;
            this.applyTroopPresentation();
        }
    }

    public String getTroopId() {
        return (String)this.field_6011.method_12789(TROOP_ID);
    }

    public void setTroopId(String troopId) {
        this.field_6011.method_12778(TROOP_ID, (Object)(troopId == null ? "" : troopId));
        this.cachedDefinition = null;
        this.applyTroopPresentation();
    }

    public DynamicTroopDefinition getTroopDefinition() {
        if (this.cachedDefinition == null) {
            this.cachedDefinition = DynamicTroopRegistry.get(this.getTroopId());
        }
        return this.cachedDefinition;
    }

    public boolean canUpgrade() {
        DynamicTroopDefinition definition = this.getTroopDefinition();
        return definition != null && !definition.upgradeTargets().isEmpty();
    }

    public class_2960 getUpgradeTargetId() {
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition == null || definition.upgradeTargets().isEmpty()) {
            return null;
        }
        return definition.upgradeTargets().getFirst();
    }

    @Override
    public class_2960 getKingdomId() {
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition != null) {
            return definition.kingdomId();
        }
        return class_2960.method_60655((String)"agob", (String)"the_crownlands");
    }

    private void applyTroopPresentation() {
        if (this.method_37908().method_8608()) {
            return;
        }
        DynamicTroopDefinition definition = this.getTroopDefinition();
        if (definition == null) {
            return;
        }
        this.method_5665((class_2561)class_2561.method_43470((String)definition.displayName()));
        this.method_5952(false);
        TroopStatResolver.applyTroopAttributes((class_1309)this, definition);
        DynamicTroopLoadoutApplier.applyEquipment((class_1309)this, definition);
        for (class_1304 slot : class_1304.values()) {
            this.method_5946(slot, 0.0f);
        }
    }
}

