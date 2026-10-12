/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_3532
 */
package trep.got.kingdom;

import net.minecraft.class_2487;
import net.minecraft.class_3532;
import trep.got.kingdom.ReputationRoute;
import trep.got.kingdom.ReputationTitle;

public final class ReputationProfile {
    private ReputationTitle title = ReputationTitle.PEASANT;
    private int experience;
    private boolean knighthoodCeremonyCompleted;
    private boolean sellswordContractCompleted;
    private boolean baptised;
    private ReputationRoute currentRoute = ReputationRoute.NONE;

    public ReputationTitle getTitle() {
        return this.title;
    }

    public void setTitle(ReputationTitle title) {
        ReputationTitle reputationTitle = this.title = title == null ? ReputationTitle.PEASANT : title;
        if (this.title.getRoute() != ReputationRoute.NONE) {
            this.currentRoute = this.title.getRoute();
        }
        this.reconcileTitle();
    }

    public void setExactTitle(ReputationTitle title) {
        this.title = title == null ? ReputationTitle.PEASANT : title;
        this.experience = this.title.totalExperienceRequired();
        switch (this.title.getRoute()) {
            case ROYAL: {
                this.currentRoute = ReputationRoute.ROYAL;
                this.knighthoodCeremonyCompleted = true;
                this.sellswordContractCompleted = false;
                this.baptised = false;
                break;
            }
            case MERCENARY: {
                this.currentRoute = ReputationRoute.MERCENARY;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = true;
                this.baptised = false;
                break;
            }
            case GOLDEN_COMPANY: {
                this.currentRoute = ReputationRoute.GOLDEN_COMPANY;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = true;
                this.baptised = false;
                break;
            }
            case RELIGIOUS: {
                this.currentRoute = ReputationRoute.RELIGIOUS;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = false;
                this.baptised = true;
                break;
            }
            case NIGHTS_WATCH: {
                this.currentRoute = ReputationRoute.NIGHTS_WATCH;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = false;
                this.baptised = false;
                break;
            }
            case WILDLING: {
                this.currentRoute = ReputationRoute.WILDLING;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = false;
                this.baptised = false;
                break;
            }
            case NONE: {
                this.currentRoute = ReputationRoute.NONE;
                this.knighthoodCeremonyCompleted = false;
                this.sellswordContractCompleted = false;
                this.baptised = false;
            }
        }
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
        this.reconcileTitle();
    }

    public boolean hasKnighthoodCeremonyCompleted() {
        return this.knighthoodCeremonyCompleted;
    }

    public void setKnighthoodCeremonyCompleted(boolean knighthoodCeremonyCompleted) {
        this.knighthoodCeremonyCompleted = knighthoodCeremonyCompleted;
        this.reconcileTitle();
    }

    public boolean hasSellswordContractCompleted() {
        return this.sellswordContractCompleted;
    }

    public void setSellswordContractCompleted(boolean sellswordContractCompleted) {
        this.sellswordContractCompleted = sellswordContractCompleted;
        this.reconcileTitle();
    }

    public boolean isBaptised() {
        return this.baptised;
    }

    public void setBaptised(boolean baptised) {
        this.baptised = baptised;
        this.reconcileTitle();
    }

    public ReputationRoute getCurrentRoute() {
        return this.currentRoute;
    }

    public void setCurrentRoute(ReputationRoute currentRoute) {
        this.currentRoute = currentRoute;
        this.reconcileTitle();
    }

    public void addExperience(int experience) {
        if (experience <= 0) {
            return;
        }
        this.experience += experience;
        this.reconcileTitle();
    }

    public int getProgressExperience() {
        if (this.title.isMaxRank()) {
            return this.title.getExperienceToNextTitle();
        }
        int currentFloor = this.title.totalExperienceRequired();
        return class_3532.method_15340((int)(this.experience - currentFloor), (int)0, (int)this.getExperienceForNextTitle());
    }

    public int getExperienceForNextTitle() {
        return this.title.getExperienceToNextTitle();
    }

    public float getProgressRatio() {
        if (this.title.isMaxRank()) {
            return 1.0f;
        }
        int required = this.getExperienceForNextTitle();
        if (required <= 0) {
            return 1.0f;
        }
        return class_3532.method_15363((float)((float)this.getProgressExperience() / (float)required), (float)0.0f, (float)1.0f);
    }

    public void copyFrom(ReputationProfile other) {
        this.title = other.title;
        this.experience = other.experience;
        this.knighthoodCeremonyCompleted = other.knighthoodCeremonyCompleted;
        this.sellswordContractCompleted = other.sellswordContractCompleted;
        this.baptised = other.baptised;
        this.currentRoute = other.currentRoute;
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("title", this.title.getId());
        nbt.method_10569("experience", this.experience);
        nbt.method_10556("knighthood_ceremony_completed", this.knighthoodCeremonyCompleted);
        nbt.method_10556("sellsword_contract_completed", this.sellswordContractCompleted);
        nbt.method_10556("baptised", this.baptised);
        nbt.method_10582("route", this.currentRoute.name());
        return nbt;
    }

    public static ReputationProfile fromNbt(class_2487 nbt) {
        ReputationProfile profile = new ReputationProfile();
        if (nbt.method_10545("title")) {
            profile.title = ReputationTitle.byId(nbt.method_10558("title"));
        }
        if (nbt.method_10545("experience")) {
            profile.experience = Math.max(0, nbt.method_10550("experience"));
        }
        if (nbt.method_10545("knighthood_ceremony_completed")) {
            profile.knighthoodCeremonyCompleted = nbt.method_10577("knighthood_ceremony_completed");
        }
        if (nbt.method_10545("sellsword_contract_completed")) {
            profile.sellswordContractCompleted = nbt.method_10577("sellsword_contract_completed");
        }
        if (nbt.method_10545("baptised")) {
            profile.baptised = nbt.method_10577("baptised");
        }
        if (nbt.method_10545("route")) {
            try {
                profile.currentRoute = ReputationRoute.valueOf(nbt.method_10558("route"));
            }
            catch (IllegalArgumentException ignored) {
                profile.currentRoute = ReputationRoute.NONE;
            }
        }
        profile.reconcileTitle();
        return profile;
    }

    private void reconcileTitle() {
        ReputationTitle.ProgressionOption nextOption;
        if (this.title == ReputationTitle.SOLDIER && this.currentRoute == ReputationRoute.NONE) {
            return;
        }
        while ((nextOption = this.title.getAdvancableOption(this)) != null) {
            this.title = nextOption.nextTitle();
            if (this.currentRoute != ReputationRoute.NONE || this.title.getRoute() == ReputationRoute.NONE) continue;
            this.currentRoute = this.title.getRoute();
        }
    }
}

