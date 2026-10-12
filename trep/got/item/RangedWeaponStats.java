/*
 * Decompiled with CFR 0.152.
 */
package trep.got.item;

public record RangedWeaponStats(int attackDamage, int reloadTicks) {
    public RangedWeaponStats {
        attackDamage = Math.max(0, attackDamage);
        reloadTicks = Math.max(0, reloadTicks);
    }

    public double reloadSeconds() {
        return (double)this.reloadTicks / 20.0;
    }
}

