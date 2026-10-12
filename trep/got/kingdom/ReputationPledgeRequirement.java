/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import trep.got.kingdom.ReputationProfile;

public enum ReputationPledgeRequirement {
    NONE("none"),
    KNIGHTHOOD_CEREMONY("knighthood_ceremony"),
    SELLSWORD_CONTRACT("sellsword_contract"),
    BAPTISED("baptised");

    private final String id;

    private ReputationPledgeRequirement(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public boolean isMet(ReputationProfile profile) {
        if (profile == null) {
            return false;
        }
        return switch (this.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> true;
            case 1 -> profile.hasKnighthoodCeremonyCompleted();
            case 2 -> profile.hasSellswordContractCompleted();
            case 3 -> profile.isBaptised();
        };
    }

    public static ReputationPledgeRequirement byId(String id) {
        if (id == null || id.isBlank()) {
            return NONE;
        }
        for (ReputationPledgeRequirement requirement : ReputationPledgeRequirement.values()) {
            if (!requirement.id.equals(id)) continue;
            return requirement;
        }
        return NONE;
    }
}

