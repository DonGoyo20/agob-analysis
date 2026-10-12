/*
 * Decompiled with CFR 0.152.
 */
package trep.got.lockpicking;

import java.util.Set;

public interface PlayerLockpickingHolder {
    public Set<String> got$getUnlockedLockpickTargets();

    public boolean got$hasUnlockedLockpickTarget(String var1);

    public boolean got$unlockLockpickTarget(String var1);

    public void got$copyUnlockedLockpickTargetsFrom(PlayerLockpickingHolder var1);
}

