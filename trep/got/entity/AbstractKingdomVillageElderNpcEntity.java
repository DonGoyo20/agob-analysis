/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1914
 *  net.minecraft.class_1915
 *  net.minecraft.class_1916
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_3414
 *  net.minecraft.class_3417
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_1915;
import net.minecraft.class_1916;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotItems;

public abstract class AbstractKingdomVillageElderNpcEntity
extends AbstractKingdomNpcEntity
implements class_1915 {
    @Nullable
    private class_1657 tradeCustomer;
    private class_1916 tradeOffers = new class_1916();
    private int tradeExperience;
    @Nullable
    private VillageTradeSession tradeSession;

    protected AbstractKingdomVillageElderNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.ELDER;
    }

    @Override
    public class_1799 getIndicatorStack() {
        return new class_1799((class_1935)GotItems.COPPER_HOE);
    }

    public void setVillageTradeSession(@Nullable VillageTradeSession tradeSession) {
        this.tradeSession = tradeSession;
        if (tradeSession == null) {
            this.tradeOffers = new class_1916();
            this.tradeExperience = 0;
        }
    }

    public void method_8259(@Nullable class_1657 customer) {
        this.tradeCustomer = customer;
        if (customer == null) {
            this.setVillageTradeSession(null);
        }
    }

    @Nullable
    public class_1657 method_8257() {
        return this.tradeCustomer;
    }

    public class_1916 method_8264() {
        return this.tradeOffers;
    }

    public void method_8261(class_1916 offers) {
        this.tradeOffers = offers;
    }

    public void method_8262(class_1914 offer) {
        if (this.tradeSession != null) {
            this.tradeSession.trade(offer);
            return;
        }
        offer.method_8244();
    }

    public void method_8258(class_1799 stack) {
        if (this.tradeSession != null) {
            this.tradeSession.onSellingItem(stack);
        }
    }

    public int method_19269() {
        return this.tradeExperience;
    }

    public void method_19271(int experience) {
        this.tradeExperience = experience;
    }

    public boolean method_19270() {
        return true;
    }

    public class_3414 method_18010() {
        return class_3417.field_14815;
    }

    public boolean method_38069() {
        return this.method_37908().method_8608();
    }

    public static interface VillageTradeSession {
        public void trade(class_1914 var1);

        default public void onSellingItem(class_1799 stack) {
        }
    }
}

