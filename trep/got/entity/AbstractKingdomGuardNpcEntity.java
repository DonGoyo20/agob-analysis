/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1314
 *  net.minecraft.class_1352
 *  net.minecraft.class_1366
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_9282
 *  net.minecraft.class_9334
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1314;
import net.minecraft.class_1352;
import net.minecraft.class_1366;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_9282;
import net.minecraft.class_9334;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomGuardPatrolGoal;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotCastles;

public abstract class AbstractKingdomGuardNpcEntity
extends AbstractKingdomNpcEntity {
    private static final class_2940<String> GUARD_ROLE = class_2945.method_12791(AbstractKingdomGuardNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final String GUARD_ROLE_KEY = "gotmod.guard_role";

    protected AbstractKingdomGuardNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.equipGuardLoadout();
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(GUARD_ROLE, (Object)KingdomNpcRole.GUARD.name());
    }

    @Override
    protected void method_5959() {
        super.method_5959();
        this.field_6201.method_6277(2, (class_1352)new class_1366((class_1314)this, 1.1, false));
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return this.getGuardRole();
    }

    public KingdomNpcRole getGuardRole() {
        try {
            KingdomNpcRole role = KingdomNpcRole.valueOf((String)this.field_6011.method_12789(GUARD_ROLE));
            return role.isGuardDuty() ? role : KingdomNpcRole.GUARD;
        }
        catch (IllegalArgumentException ignored) {
            return KingdomNpcRole.GUARD;
        }
    }

    public void setGuardRole(KingdomNpcRole role) {
        this.field_6011.method_12778(GUARD_ROLE, (Object)(role != null && role.isGuardDuty() ? role.name() : KingdomNpcRole.GUARD.name()));
    }

    @Override
    protected class_1352 createPoiWanderGoal() {
        return new KingdomGuardPatrolGoal(this);
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(GUARD_ROLE_KEY, this.getGuardRole().name());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(GUARD_ROLE_KEY)) {
            try {
                this.setGuardRole(KingdomNpcRole.valueOf(nbt.method_10558(GUARD_ROLE_KEY)));
            }
            catch (IllegalArgumentException ignored) {
                this.setGuardRole(KingdomNpcRole.GUARD);
            }
        }
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)class_1802.field_8371);
    }

    protected void equipGuardLoadout() {
        class_1792[] kingdomArmor;
        if (this.method_6118(class_1304.field_6173).method_7960()) {
            this.method_5673(class_1304.field_6173, new class_1799((class_1935)class_1802.field_8371));
        }
        if (this.method_6118(class_1304.field_6171).method_7960()) {
            this.method_5673(class_1304.field_6171, new class_1799((class_1935)class_1802.field_8255));
        }
        if ((kingdomArmor = this.kingdomArmor()) != null) {
            if (this.shouldEquipHelmet()) {
                this.equipArmorIfEmpty(class_1304.field_6169, this.maybeDyedArmor(class_1304.field_6169, new class_1799((class_1935)kingdomArmor[0])));
            }
            this.equipArmorIfEmpty(class_1304.field_6174, this.maybeDyedArmor(class_1304.field_6174, new class_1799((class_1935)kingdomArmor[1])));
            this.equipArmorIfEmpty(class_1304.field_6172, this.maybeDyedArmor(class_1304.field_6172, new class_1799((class_1935)kingdomArmor[2])));
            this.equipArmorIfEmpty(class_1304.field_6166, this.maybeDyedArmor(class_1304.field_6166, new class_1799((class_1935)kingdomArmor[3])));
        } else {
            if (this.shouldEquipHelmet()) {
                this.equipArmorIfEmpty(class_1304.field_6169, new class_1799((class_1935)class_1802.field_8743));
            }
            this.equipArmorIfEmpty(class_1304.field_6174, new class_1799((class_1935)class_1802.field_8523));
            this.equipArmorIfEmpty(class_1304.field_6172, new class_1799((class_1935)class_1802.field_8396));
            this.equipArmorIfEmpty(class_1304.field_6166, new class_1799((class_1935)class_1802.field_8660));
        }
        for (class_1304 slot : class_1304.values()) {
            this.method_5946(slot, 0.0f);
        }
        this.method_5952(false);
        this.afterGuardLoadoutApplied();
    }

    @Override
    public void got$setCastle(String castleId) {
        super.got$setCastle(castleId);
        if (!this.method_37908().method_8608()) {
            this.refreshCastleArmorLoadout();
        }
    }

    protected boolean shouldEquipHelmet() {
        return true;
    }

    protected void afterGuardLoadoutApplied() {
    }

    private void equipArmorIfEmpty(class_1304 slot, class_1799 stack) {
        if (this.method_6118(slot).method_7960()) {
            this.method_5673(slot, stack);
        }
    }

    private class_1799 maybeDyedArmor(class_1304 slot, class_1799 stack) {
        if (!"the_nights_watch".equals(this.getKingdomId().method_12832())) {
            return stack;
        }
        if (slot == class_1304.field_6169) {
            return stack;
        }
        stack.method_57379(class_9334.field_49644, (Object)new class_9282(0x111111, false));
        return stack;
    }

    private class_1792[] kingdomArmor() {
        class_3218 serverWorld;
        class_1937 class_19372 = this.method_37908();
        class_3218 world = class_19372 instanceof class_3218 ? (serverWorld = (class_3218)class_19372) : null;
        return GotCastles.resolveArmorForCastle(world, this.got$getCastle(), this.getKingdomId());
    }

    private void refreshCastleArmorLoadout() {
        class_1792[] kingdomArmor = this.kingdomArmor();
        if (kingdomArmor == null) {
            return;
        }
        if (this.shouldEquipHelmet()) {
            this.method_5673(class_1304.field_6169, this.maybeDyedArmor(class_1304.field_6169, new class_1799((class_1935)kingdomArmor[0])));
        }
        this.method_5673(class_1304.field_6174, this.maybeDyedArmor(class_1304.field_6174, new class_1799((class_1935)kingdomArmor[1])));
        this.method_5673(class_1304.field_6172, this.maybeDyedArmor(class_1304.field_6172, new class_1799((class_1935)kingdomArmor[2])));
        this.method_5673(class_1304.field_6166, this.maybeDyedArmor(class_1304.field_6166, new class_1799((class_1935)kingdomArmor[3])));
        this.afterGuardLoadoutApplied();
    }
}

