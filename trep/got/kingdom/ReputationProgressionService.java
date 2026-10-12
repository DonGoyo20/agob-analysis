/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.kingdom;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import trep.got.kingdom.ReputationPledgeOption;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationRulesRegistry;
import trep.got.kingdom.ReputationTitle;

public final class ReputationProgressionService {
    private ReputationProgressionService() {
    }

    public static boolean hasReachedSoldierCrossroads(ReputationProfile profile) {
        return profile != null && profile.getTitle() == ReputationTitle.SOLDIER && profile.getExperience() >= ReputationTitle.SOLDIER.totalExperienceRequired() + ReputationTitle.SOLDIER.getExperienceToNextTitle();
    }

    public static List<ReputationPledgeOption> availablePledgeOptions(@Nullable String kingdomId, ReputationProfile profile) {
        if (!ReputationProgressionService.hasReachedSoldierCrossroads(profile)) {
            return List.of();
        }
        return ReputationRulesRegistry.availablePledgeOptions(kingdomId, profile);
    }

    public static List<ReputationPledgeOption> sharedPledgeOptions(@Nullable String currentKingdomId, @Nullable String targetKingdomId, ReputationProfile profile) {
        if (!ReputationProgressionService.hasReachedSoldierCrossroads(profile)) {
            return List.of();
        }
        return ReputationRulesRegistry.sharedPledgeOptions(currentKingdomId, targetKingdomId, profile);
    }
}

