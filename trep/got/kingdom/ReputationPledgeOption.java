/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import trep.got.castle.CastleInvasionMode;
import trep.got.kingdom.ReputationPledgeRequirement;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationTitle;

public record ReputationPledgeOption(String id, String label, ReputationTitle title, CastleInvasionMode objectiveMode, ReputationPledgeRequirement requirement) {
    public String actionId() {
        return "pledge_route:" + this.id;
    }

    public boolean isAvailableFor(ReputationProfile profile) {
        return this.requirement.isMet(profile);
    }
}

