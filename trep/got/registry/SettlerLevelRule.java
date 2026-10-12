/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.registry;

import org.jetbrains.annotations.Nullable;
import trep.got.castle.PlayerSettlementTier;

record SettlerLevelRule(int minDailyOffers, int maxDailyOffers, int maxAcceptedSettlers, int requiredSettlers, int requiredMiners, int requiredLumberjacks, int requiredFarmers, @Nullable PlayerSettlementTier nextTier) {
}

