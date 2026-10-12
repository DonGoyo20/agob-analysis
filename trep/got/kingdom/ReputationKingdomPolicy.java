/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import java.util.List;
import java.util.Set;
import trep.got.kingdom.ReputationPledgeOption;

public record ReputationKingdomPolicy(String id, Set<String> kingdomIds, boolean knighthoodCeremonyAvailable, List<ReputationPledgeOption> pledgeOptions) {
    public ReputationKingdomPolicy {
        kingdomIds = Set.copyOf(kingdomIds);
        pledgeOptions = List.copyOf(pledgeOptions);
    }

    public boolean appliesTo(String kingdomId) {
        return kingdomId != null && this.kingdomIds.contains(kingdomId);
    }
}

