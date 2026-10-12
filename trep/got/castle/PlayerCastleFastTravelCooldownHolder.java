/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public interface PlayerCastleFastTravelCooldownHolder {
    public long got$getFastTravelCooldownEndsAtMs();

    public void got$setFastTravelCooldownEndsAtMs(long var1);

    default public boolean got$hasFastTravelCooldown(long nowMs) {
        return this.got$getFastTravelCooldownEndsAtMs() > nowMs;
    }
}

