/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 */
package trep.got.character;

import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import trep.got.character.PlayerSkinBuilderProfile;
import trep.got.character.PlayerTailorProfile;

public final class PlayerCharacterProfile {
    private String selectedReligionId = "";
    private String selectedKingdomId = "";
    private String selectedOptionId = "";
    private String selectedGeneticTraitId = "";
    private String originalSelectedKingdomId = "";
    private String originalSelectedOptionId = "";
    private String activeCastleTitleId = "";
    private PlayerSkinBuilderProfile skinBuilderProfile = PlayerSkinBuilderProfile.DEFAULT;
    private PlayerTailorProfile tailorProfile = new PlayerTailorProfile();
    private final Set<String> grantedCastleTitleIds = new LinkedHashSet<String>();
    private final Set<String> selectedPersonalityTraitIds = new LinkedHashSet<String>();

    public String getSelectedReligionId() {
        return this.selectedReligionId;
    }

    public void setSelectedReligionId(String selectedReligionId) {
        this.selectedReligionId = selectedReligionId == null ? "" : selectedReligionId;
    }

    public String getSelectedKingdomId() {
        return this.selectedKingdomId;
    }

    public void setSelectedKingdomId(String selectedKingdomId) {
        this.selectedKingdomId = selectedKingdomId == null ? "" : selectedKingdomId;
    }

    public String getSelectedOptionId() {
        return this.selectedOptionId;
    }

    public void setSelectedOptionId(String selectedOptionId) {
        this.selectedOptionId = selectedOptionId == null ? "" : selectedOptionId;
    }

    public String getSelectedGeneticTraitId() {
        return this.selectedGeneticTraitId;
    }

    public void setSelectedGeneticTraitId(String selectedGeneticTraitId) {
        this.selectedGeneticTraitId = selectedGeneticTraitId == null ? "" : selectedGeneticTraitId;
    }

    public Set<String> getSelectedPersonalityTraitIds() {
        return Set.copyOf(this.selectedPersonalityTraitIds);
    }

    public void setSelectedPersonalityTraitIds(Set<String> selectedPersonalityTraitIds) {
        this.selectedPersonalityTraitIds.clear();
        if (selectedPersonalityTraitIds != null) {
            for (String traitId : selectedPersonalityTraitIds) {
                if (traitId == null || traitId.isBlank()) continue;
                this.selectedPersonalityTraitIds.add(traitId);
            }
        }
    }

    public PlayerSkinBuilderProfile getSkinBuilderProfile() {
        return this.skinBuilderProfile;
    }

    public void setSkinBuilderProfile(PlayerSkinBuilderProfile skinBuilderProfile) {
        this.skinBuilderProfile = skinBuilderProfile == null ? PlayerSkinBuilderProfile.DEFAULT : skinBuilderProfile;
    }

    public PlayerTailorProfile getTailorProfile() {
        return this.tailorProfile;
    }

    public String getOriginalSelectedKingdomId() {
        return this.originalSelectedKingdomId;
    }

    public void setOriginalSelectedKingdomId(String originalSelectedKingdomId) {
        this.originalSelectedKingdomId = originalSelectedKingdomId == null ? "" : originalSelectedKingdomId;
    }

    public String getOriginalSelectedOptionId() {
        return this.originalSelectedOptionId;
    }

    public void setOriginalSelectedOptionId(String originalSelectedOptionId) {
        this.originalSelectedOptionId = originalSelectedOptionId == null ? "" : originalSelectedOptionId;
    }

    public void initializeOriginalSelection(String kingdomId, String optionId) {
        if (this.originalSelectedKingdomId.isBlank()) {
            String string = this.originalSelectedKingdomId = kingdomId == null ? "" : kingdomId;
        }
        if (this.originalSelectedOptionId.isBlank()) {
            this.originalSelectedOptionId = optionId == null ? "" : optionId;
        }
    }

    public void resetCurrentSelectionToOriginal() {
        this.selectedKingdomId = this.originalSelectedKingdomId;
        this.selectedOptionId = this.originalSelectedOptionId;
    }

    public boolean hasSelection() {
        return !this.selectedReligionId.isBlank() && !this.selectedKingdomId.isBlank();
    }

    public String getActiveCastleTitleId() {
        return this.activeCastleTitleId;
    }

    public void setActiveCastleTitleId(String activeCastleTitleId) {
        this.activeCastleTitleId = activeCastleTitleId == null ? "" : activeCastleTitleId;
    }

    public Set<String> getGrantedCastleTitleIds() {
        return Set.copyOf(this.grantedCastleTitleIds);
    }

    public boolean hasCastleTitle(String titleId) {
        return titleId != null && this.grantedCastleTitleIds.contains(titleId);
    }

    public void grantCastleTitle(String titleId) {
        if (titleId == null || titleId.isBlank()) {
            return;
        }
        this.grantedCastleTitleIds.add(titleId);
        if (this.activeCastleTitleId.isBlank()) {
            this.activeCastleTitleId = titleId;
        }
    }

    public void revokeCastleTitle(String titleId) {
        if (titleId == null || titleId.isBlank()) {
            return;
        }
        this.grantedCastleTitleIds.remove(titleId);
        if (this.activeCastleTitleId.equals(titleId)) {
            this.activeCastleTitleId = this.grantedCastleTitleIds.stream().findFirst().orElse("");
        }
    }

    public void clearCastleTitles() {
        this.activeCastleTitleId = "";
        this.grantedCastleTitleIds.clear();
    }

    public void copyFrom(PlayerCharacterProfile other) {
        this.selectedReligionId = other.selectedReligionId;
        this.selectedKingdomId = other.selectedKingdomId;
        this.selectedOptionId = other.selectedOptionId;
        this.selectedGeneticTraitId = other.selectedGeneticTraitId;
        this.originalSelectedKingdomId = other.originalSelectedKingdomId;
        this.originalSelectedOptionId = other.originalSelectedOptionId;
        this.activeCastleTitleId = other.activeCastleTitleId;
        this.skinBuilderProfile = other.skinBuilderProfile;
        this.tailorProfile.copyFrom(other.tailorProfile);
        this.grantedCastleTitleIds.clear();
        this.grantedCastleTitleIds.addAll(other.grantedCastleTitleIds);
        this.selectedPersonalityTraitIds.clear();
        this.selectedPersonalityTraitIds.addAll(other.selectedPersonalityTraitIds);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("selected_religion_id", this.selectedReligionId);
        nbt.method_10582("selected_kingdom_id", this.selectedKingdomId);
        nbt.method_10582("selected_option_id", this.selectedOptionId);
        nbt.method_10582("selected_genetic_trait_id", this.selectedGeneticTraitId);
        nbt.method_10582("original_selected_kingdom_id", this.originalSelectedKingdomId);
        nbt.method_10582("original_selected_option_id", this.originalSelectedOptionId);
        nbt.method_10582("active_castle_title_id", this.activeCastleTitleId);
        nbt.method_10566("skin_builder_profile", (class_2520)this.skinBuilderProfile.toNbt());
        nbt.method_10566("tailor_profile", (class_2520)this.tailorProfile.toNbt());
        class_2499 grantedTitles = new class_2499();
        for (String titleId : this.grantedCastleTitleIds) {
            grantedTitles.add((Object)class_2519.method_23256((String)titleId));
        }
        nbt.method_10566("granted_castle_title_ids", (class_2520)grantedTitles);
        class_2499 selectedPersonalityTraits = new class_2499();
        for (String traitId : this.selectedPersonalityTraitIds) {
            selectedPersonalityTraits.add((Object)class_2519.method_23256((String)traitId));
        }
        nbt.method_10566("selected_personality_trait_ids", (class_2520)selectedPersonalityTraits);
        return nbt;
    }

    public static PlayerCharacterProfile fromNbt(class_2487 nbt) {
        int index;
        PlayerCharacterProfile profile = new PlayerCharacterProfile();
        if (nbt.method_10545("selected_religion_id")) {
            profile.setSelectedReligionId(nbt.method_10558("selected_religion_id"));
        }
        if (nbt.method_10545("selected_kingdom_id")) {
            profile.setSelectedKingdomId(nbt.method_10558("selected_kingdom_id"));
        }
        if (nbt.method_10545("selected_option_id")) {
            profile.setSelectedOptionId(nbt.method_10558("selected_option_id"));
        }
        if (nbt.method_10545("selected_genetic_trait_id")) {
            profile.setSelectedGeneticTraitId(nbt.method_10558("selected_genetic_trait_id"));
        }
        if (nbt.method_10545("original_selected_kingdom_id")) {
            profile.setOriginalSelectedKingdomId(nbt.method_10558("original_selected_kingdom_id"));
        }
        if (nbt.method_10545("original_selected_option_id")) {
            profile.setOriginalSelectedOptionId(nbt.method_10558("original_selected_option_id"));
        }
        if (nbt.method_10545("active_castle_title_id")) {
            profile.setActiveCastleTitleId(nbt.method_10558("active_castle_title_id"));
        }
        if (nbt.method_10573("skin_builder_profile", 10)) {
            profile.setSkinBuilderProfile(PlayerSkinBuilderProfile.fromNbt(nbt.method_10562("skin_builder_profile")));
        }
        if (nbt.method_10573("tailor_profile", 10)) {
            profile.tailorProfile = PlayerTailorProfile.fromNbt(nbt.method_10562("tailor_profile"));
        }
        if (nbt.method_10545("granted_castle_title_ids")) {
            class_2499 grantedTitles = nbt.method_10554("granted_castle_title_ids", 8);
            for (index = 0; index < grantedTitles.size(); ++index) {
                profile.grantCastleTitle(grantedTitles.method_10608(index));
            }
        }
        if (nbt.method_10545("selected_personality_trait_ids")) {
            class_2499 selectedPersonalityTraits = nbt.method_10554("selected_personality_trait_ids", 8);
            for (index = 0; index < selectedPersonalityTraits.size(); ++index) {
                String traitId = selectedPersonalityTraits.method_10608(index);
                if (traitId.isBlank()) continue;
                profile.selectedPersonalityTraitIds.add(traitId);
            }
        }
        profile.initializeOriginalSelection(profile.selectedKingdomId, profile.selectedOptionId);
        return profile;
    }
}

