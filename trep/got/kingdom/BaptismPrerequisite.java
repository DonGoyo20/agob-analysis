/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import trep.got.kingdom.ReputationPrerequisite;
import trep.got.kingdom.ReputationProfile;

public class BaptismPrerequisite
implements ReputationPrerequisite {
    @Override
    public boolean meetsPrerequisite(ReputationProfile profile) {
        return profile.isBaptised();
    }
}

