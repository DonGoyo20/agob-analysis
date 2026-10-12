/*
 * Decompiled with CFR 0.152.
 */
package trep.got.entity;

public enum KingdomNpcRole {
    CIVILIAN,
    GUARD,
    PATROL,
    GATE_WATCHER,
    LORD,
    ELDER,
    BLACKSMITH,
    ARENA_MASTER,
    FISHERMAN,
    CART_RIDER;


    public boolean isGuardDuty() {
        return this == GUARD || this == PATROL || this == GATE_WATCHER;
    }
}

