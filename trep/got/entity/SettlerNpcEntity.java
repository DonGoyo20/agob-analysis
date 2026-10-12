/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3222
 */
package trep.got.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import trep.got.castle.SettlerProfession;
import trep.got.castle.SettlerSkillTier;
import trep.got.character.CharacterSelectionOption;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.KingdomNpcRole;
import trep.got.registry.GotCharacterOptions;

public final class SettlerNpcEntity
extends AbstractKingdomNpcEntity {
    private static final class_2940<String> OPTION_ID = class_2945.method_12791(SettlerNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> SETTLER_ID = class_2945.method_12791(SettlerNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> PROFESSION_ID = class_2945.method_12791(SettlerNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> SKILL_TIER_ID = class_2945.method_12791(SettlerNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Boolean> ACCEPTED = class_2945.method_12791(SettlerNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final String OPTION_KEY = "gotmod.settler_option_id";
    private static final String SETTLER_KEY = "gotmod.settler_id";
    private static final String PROFESSION_KEY = "gotmod.settler_profession";
    private static final String SKILL_TIER_KEY = "gotmod.settler_skill_tier";
    private static final String ACCEPTED_KEY = "gotmod.settler_accepted";

    public SettlerNpcEntity(class_1299<? extends SettlerNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(OPTION_ID, (Object)"");
        builder.method_56912(SETTLER_ID, (Object)"");
        builder.method_56912(PROFESSION_ID, (Object)SettlerProfession.FARMER.getId());
        builder.method_56912(SKILL_TIER_ID, (Object)SettlerSkillTier.WOOD.getId());
        builder.method_56912(ACCEPTED, (Object)false);
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public boolean beginDialogue(class_3222 player) {
        return player != null && super.beginDialogue(player);
    }

    @Override
    public class_1799 getIndicatorStack() {
        return this.isAcceptedSettler() ? class_1799.field_8037 : new class_1799((class_1935)class_1802.field_8687);
    }

    @Override
    public KingdomNpcRole getNpcRole() {
        return KingdomNpcRole.CIVILIAN;
    }

    @Override
    public class_2960 getKingdomId() {
        CharacterSelectionOption option = this.getCharacterOption();
        return option != null ? option.kingdomId() : class_2960.method_60655((String)"agob", (String)"the_north");
    }

    public CharacterSelectionOption getCharacterOption() {
        return GotCharacterOptions.get(this.getSettlerOptionId());
    }

    public String getSettlerOptionId() {
        return (String)this.field_6011.method_12789(OPTION_ID);
    }

    public void setSettlerOptionId(String optionId) {
        this.field_6011.method_12778(OPTION_ID, (Object)(optionId == null ? "" : optionId));
    }

    public String getSettlerId() {
        return (String)this.field_6011.method_12789(SETTLER_ID);
    }

    public void setSettlerId(String settlerId) {
        this.field_6011.method_12778(SETTLER_ID, (Object)(settlerId == null ? "" : settlerId));
        this.setCastleNpcId(settlerId);
    }

    public SettlerProfession getProfession() {
        return SettlerProfession.byId((String)this.field_6011.method_12789(PROFESSION_ID));
    }

    public void setProfession(SettlerProfession profession) {
        this.field_6011.method_12778(PROFESSION_ID, (Object)(profession == null ? SettlerProfession.FARMER.getId() : profession.getId()));
    }

    public SettlerSkillTier getSkillTier() {
        return SettlerSkillTier.byId((String)this.field_6011.method_12789(SKILL_TIER_ID));
    }

    public void setSkillTier(SettlerSkillTier tier) {
        this.field_6011.method_12778(SKILL_TIER_ID, (Object)(tier == null ? SettlerSkillTier.WOOD.getId() : tier.getId()));
    }

    public boolean isAcceptedSettler() {
        return (Boolean)this.field_6011.method_12789(ACCEPTED);
    }

    public void setAcceptedSettler(boolean accepted) {
        this.field_6011.method_12778(ACCEPTED, (Object)accepted);
    }

    public class_1799 getProfessionIndicatorStack() {
        if (this.isIndependentMarriageNpc()) {
            return class_1799.field_8037;
        }
        return this.getProfession().indicatorFor(this.getSkillTier());
    }

    @Override
    public String getPersistentNpcId() {
        return this.getSettlerId();
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(OPTION_KEY, this.getSettlerOptionId());
        nbt.method_10582(SETTLER_KEY, this.getSettlerId());
        nbt.method_10582(PROFESSION_KEY, this.getProfession().getId());
        nbt.method_10582(SKILL_TIER_KEY, this.getSkillTier().getId());
        nbt.method_10556(ACCEPTED_KEY, this.isAcceptedSettler());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(OPTION_KEY)) {
            this.setSettlerOptionId(nbt.method_10558(OPTION_KEY));
        }
        if (nbt.method_10545(SETTLER_KEY)) {
            this.setSettlerId(nbt.method_10558(SETTLER_KEY));
        }
        if (nbt.method_10545(PROFESSION_KEY)) {
            this.setProfession(SettlerProfession.byId(nbt.method_10558(PROFESSION_KEY)));
        }
        if (nbt.method_10545(SKILL_TIER_KEY)) {
            this.setSkillTier(SettlerSkillTier.byId(nbt.method_10558(SKILL_TIER_KEY)));
        }
        if (nbt.method_10545(ACCEPTED_KEY)) {
            this.setAcceptedSettler(nbt.method_10577(ACCEPTED_KEY));
        }
    }
}

