/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.kingdom;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleInvasionMode;
import trep.got.kingdom.ReputationPrerequisite;
import trep.got.kingdom.ReputationProfile;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationRulesRegistry;
import trep.got.kingdom.ReputationTitleDefinition;

public enum ReputationTitle {
    PEASANT("peasant", "Peasant", 100, ReputationRoute.NONE, 0),
    SOLDIER("soldier", "Soldier", 500, ReputationRoute.NONE, 1),
    KNIGHT("knight", "Knight", 1500, ReputationRoute.ROYAL, 2),
    LORD("lord", "Lord", 5000, ReputationRoute.ROYAL, 3),
    KING("king", "King", 0, ReputationRoute.ROYAL, 4),
    HEDGE_KNIGHT("hedge_knight", "Hedge Knight", 1500, ReputationRoute.MERCENARY, 2),
    WARLORD("warlord", "Warlord", 5000, ReputationRoute.MERCENARY, 3),
    MAGISTER("magister", "Magister", 0, ReputationRoute.MERCENARY, 4),
    PRIEST("priest", "Priest", 1500, ReputationRoute.RELIGIOUS, 2),
    BISHOP("bishop", "Bishop", 5000, ReputationRoute.RELIGIOUS, 3),
    PROPHET("prophet", "Prophet", 0, ReputationRoute.RELIGIOUS, 4),
    SELLSWORD("sellsword", "Sellsword", 1500, ReputationRoute.GOLDEN_COMPANY, 2),
    CAPTAIN("captain", "Captain", 5000, ReputationRoute.GOLDEN_COMPANY, 3),
    CAPTAIN_GENERAL("captain_general", "Captain-General", 0, ReputationRoute.GOLDEN_COMPANY, 4),
    SWORN_BROTHER("sworn_brother", "Sworn Brother", 1500, ReputationRoute.NIGHTS_WATCH, 2),
    FIRST_RANGER("first_ranger", "First Ranger", 5000, ReputationRoute.NIGHTS_WATCH, 3),
    LORD_COMMANDER("lord_commander", "Lord Commander", 0, ReputationRoute.NIGHTS_WATCH, 4),
    RAIDER("raider", "Raider", 1500, ReputationRoute.WILDLING, 2),
    CHIEFTAIN("chieftain", "Chieftain", 5000, ReputationRoute.WILDLING, 3),
    KING_BEYOND_THE_WALL("king_beyond_the_wall", "King-Beyond-the-Wall", 0, ReputationRoute.WILDLING, 4);

    public static final List<ReputationTitle> ALL;
    private final String id;
    private final String fallbackDisplayName;
    private final int fallbackExperienceToNextTitle;
    private final ReputationRoute fallbackRoute;
    private final int fallbackTier;

    private ReputationTitle(String id, String fallbackDisplayName, int fallbackExperienceToNextTitle, ReputationRoute fallbackRoute, int fallbackTier) {
        this.id = id;
        this.fallbackDisplayName = fallbackDisplayName;
        this.fallbackExperienceToNextTitle = Math.max(0, fallbackExperienceToNextTitle);
        this.fallbackRoute = fallbackRoute;
        this.fallbackTier = Math.max(0, fallbackTier);
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.definitionOrNull() == null ? this.fallbackDisplayName : this.definition().displayName();
    }

    public int getExperienceToNextTitle() {
        return this.definitionOrNull() == null ? this.fallbackExperienceToNextTitle : this.definition().experienceToNextTitle();
    }

    public ReputationRoute getRoute() {
        return this.definitionOrNull() == null ? this.fallbackRoute : this.definition().route();
    }

    public int getTier() {
        return this.definitionOrNull() == null ? this.fallbackTier : this.definition().tier();
    }

    public boolean isAtLeast(ReputationTitle other) {
        return other != null && this.getTier() >= other.getTier();
    }

    public boolean isTier(int tier) {
        return this.getTier() == tier;
    }

    public boolean isRouteTierAtLeast(ReputationRoute route, int tier) {
        return route != null && this.getRoute() == route && this.getTier() >= tier;
    }

    public boolean canPerformInvasion(CastleInvasionMode mode) {
        ReputationTitleDefinition definition = this.definitionOrNull();
        return definition != null ? definition.canPerformInvasion(mode) : false;
    }

    public boolean isMaxRank() {
        return ReputationRulesRegistry.nextTitle(this) == null;
    }

    public List<ProgressionOption> getNextTitleOptions() {
        ArrayList<ProgressionOption> options = new ArrayList<ProgressionOption>();
        ReputationTitle nextTitle = ReputationRulesRegistry.nextTitle(this);
        if (nextTitle != null) {
            options.add(new ProgressionOption(nextTitle, null));
        }
        return List.copyOf(options);
    }

    public int totalExperienceRequired() {
        if (this == PEASANT) {
            return 0;
        }
        ReputationTitle previous = this.previousTitleOnSameTrack();
        if (previous == null) {
            return 0;
        }
        return previous.totalExperienceRequired() + previous.getExperienceToNextTitle();
    }

    @Nullable
    public ProgressionOption getAdvancableOption(ReputationProfile profile) {
        List<ProgressionOption> options = this.getNextTitleOptions();
        if (options.isEmpty()) {
            return null;
        }
        if (profile.getExperience() < this.totalExperienceRequired() + this.getExperienceToNextTitle()) {
            return null;
        }
        for (ProgressionOption option : options) {
            if (option.prerequisite() != null && !option.prerequisite().meetsPrerequisite(profile)) continue;
            return option;
        }
        return null;
    }

    @Nullable
    private ReputationTitle previousTitleOnSameTrack() {
        if (this == SOLDIER) {
            return PEASANT;
        }
        if (this.getTier() == 2 && this.getRoute() != ReputationRoute.NONE) {
            return SOLDIER;
        }
        for (ReputationTitle title : ALL) {
            if (ReputationRulesRegistry.nextTitle(title) != this) continue;
            return title;
        }
        return null;
    }

    private ReputationTitleDefinition definition() {
        return ReputationRulesRegistry.titleDefinition(this);
    }

    @Nullable
    private ReputationTitleDefinition definitionOrNull() {
        try {
            return ReputationRulesRegistry.titleDefinition(this);
        }
        catch (IllegalStateException ignored) {
            return null;
        }
    }

    public static ReputationTitle byId(String id) {
        for (ReputationTitle title : ALL) {
            if (!title.id.equals(id)) continue;
            return title;
        }
        return PEASANT;
    }

    static {
        ALL = List.of(ReputationTitle.values());
    }

    public record ProgressionOption(ReputationTitle nextTitle, @Nullable ReputationPrerequisite prerequisite) {
    }
}

