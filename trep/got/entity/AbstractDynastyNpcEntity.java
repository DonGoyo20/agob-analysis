/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1299
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.entity;

import java.util.UUID;
import net.minecraft.class_1282;
import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotNpcRelations;
import trep.got.relations.PlayerNpcRelationsHolder;

public abstract class AbstractDynastyNpcEntity
extends AbstractKingdomNpcEntity {
    private static final class_2940<String> OWNER_PLAYER_UUID = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> OWNER_PLAYER_NAME = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> OTHER_PARENT_NAME = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> HOUSE_ID = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<String> FAMILY_STAGE = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final class_2940<Long> BORN_DAY = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_39965);
    private static final class_2940<Boolean> OWNER_SMALL_ARMS = class_2945.method_12791(AbstractDynastyNpcEntity.class, (class_2941)class_2943.field_13323);
    private static final String OWNER_PLAYER_UUID_KEY = "gotmod.dynasty_owner_player_uuid";
    private static final String OWNER_PLAYER_NAME_KEY = "gotmod.dynasty_owner_player_name";
    private static final String OTHER_PARENT_NAME_KEY = "gotmod.dynasty_other_parent_name";
    private static final String HOUSE_ID_KEY = "gotmod.dynasty_house_id";
    private static final String FAMILY_STAGE_KEY = "gotmod.dynasty_family_stage";
    private static final String BORN_DAY_KEY = "gotmod.dynasty_born_day";
    private static final String OWNER_SMALL_ARMS_KEY = "gotmod.dynasty_owner_small_arms";
    private class_2960 kingdomId = class_2960.method_60655((String)"minecraft", (String)"plains");

    protected AbstractDynastyNpcEntity(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.method_5952(false);
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(OWNER_PLAYER_UUID, (Object)"");
        builder.method_56912(OWNER_PLAYER_NAME, (Object)"");
        builder.method_56912(OTHER_PARENT_NAME, (Object)"");
        builder.method_56912(HOUSE_ID, (Object)"");
        builder.method_56912(FAMILY_STAGE, (Object)GotDynastyNpcs.FamilyStage.CHILD.id());
        builder.method_56912(BORN_DAY, (Object)0L);
        builder.method_56912(OWNER_SMALL_ARMS, (Object)false);
    }

    @Override
    public class_2960 getKingdomId() {
        return this.kingdomId;
    }

    public void setDynastyKingdomId(class_2960 kingdomId) {
        this.kingdomId = kingdomId == null ? class_2960.method_60655((String)"minecraft", (String)"plains") : kingdomId;
    }

    public String getOwnerPlayerUuid() {
        return (String)this.field_6011.method_12789(OWNER_PLAYER_UUID);
    }

    public void setOwnerPlayerUuid(String ownerPlayerUuid) {
        this.field_6011.method_12778(OWNER_PLAYER_UUID, (Object)(ownerPlayerUuid == null ? "" : ownerPlayerUuid));
    }

    public String getOwnerPlayerName() {
        return (String)this.field_6011.method_12789(OWNER_PLAYER_NAME);
    }

    public void setOwnerPlayerName(String ownerPlayerName) {
        this.field_6011.method_12778(OWNER_PLAYER_NAME, (Object)(ownerPlayerName == null ? "" : ownerPlayerName));
    }

    public String getOtherParentName() {
        return (String)this.field_6011.method_12789(OTHER_PARENT_NAME);
    }

    public void setOtherParentName(String otherParentName) {
        this.field_6011.method_12778(OTHER_PARENT_NAME, (Object)(otherParentName == null ? "" : otherParentName));
    }

    public String getDynastyHouseId() {
        return (String)this.field_6011.method_12789(HOUSE_ID);
    }

    public void setDynastyHouseId(String houseId) {
        this.field_6011.method_12778(HOUSE_ID, (Object)(houseId == null ? "" : houseId));
    }

    public GotDynastyNpcs.FamilyStage getFamilyStage() {
        return GotDynastyNpcs.FamilyStage.byId((String)this.field_6011.method_12789(FAMILY_STAGE));
    }

    public void setFamilyStage(GotDynastyNpcs.FamilyStage stage) {
        this.field_6011.method_12778(FAMILY_STAGE, (Object)(stage == null ? GotDynastyNpcs.FamilyStage.CHILD.id() : stage.id()));
    }

    public long getBornDay() {
        return (Long)this.field_6011.method_12789(BORN_DAY);
    }

    public void setBornDay(long bornDay) {
        this.field_6011.method_12778(BORN_DAY, (Object)Math.max(0L, bornDay));
    }

    public boolean isOwnerSmallArms() {
        return (Boolean)this.field_6011.method_12789(OWNER_SMALL_ARMS);
    }

    public void setOwnerSmallArms(boolean ownerSmallArms) {
        this.field_6011.method_12778(OWNER_SMALL_ARMS, (Object)ownerSmallArms);
    }

    public int getAgeDays(class_3218 world) {
        return Math.max(0, (int)(GotDynastyNpcs.currentTrackedDay(world) - this.getBornDay()));
    }

    public class_3222 resolveOwner(class_3218 world) {
        if (world == null || this.getOwnerPlayerUuid().isBlank()) {
            return null;
        }
        try {
            return world.method_8503().method_3760().method_14602(UUID.fromString(this.getOwnerPlayerUuid()));
        }
        catch (IllegalArgumentException exception) {
            return null;
        }
    }

    @Override
    public void method_5773() {
        class_3218 world;
        block3: {
            block2: {
                class_1937 class_19372;
                super.method_5773();
                if (this.method_37908().method_8608() || !((class_19372 = this.method_37908()) instanceof class_3218)) break block2;
                world = (class_3218)class_19372;
                if (this.field_6012 % 20 == 0) break block3;
            }
            return;
        }
        GotDynastyNpcs.syncEntityFromWorldState(world, this);
    }

    @Override
    public void method_6078(class_1282 damageSource) {
        class_1937 class_19372;
        if (!this.method_37908().method_8608() && (class_19372 = this.method_37908()) instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            GotDynastyNpcs.onFamilyNpcKilled(world, this);
        }
        super.method_6078(damageSource);
    }

    @Override
    public boolean supportsDialogueInteraction() {
        return true;
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10582(OWNER_PLAYER_UUID_KEY, this.getOwnerPlayerUuid());
        nbt.method_10582(OWNER_PLAYER_NAME_KEY, this.getOwnerPlayerName());
        nbt.method_10582(OTHER_PARENT_NAME_KEY, this.getOtherParentName());
        nbt.method_10582(HOUSE_ID_KEY, this.getDynastyHouseId());
        nbt.method_10582(FAMILY_STAGE_KEY, this.getFamilyStage().id());
        nbt.method_10544(BORN_DAY_KEY, this.getBornDay());
        nbt.method_10556(OWNER_SMALL_ARMS_KEY, this.isOwnerSmallArms());
        nbt.method_10582("gotmod.dynasty_kingdom_id", this.kingdomId.toString());
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.setOwnerPlayerUuid(nbt.method_10558(OWNER_PLAYER_UUID_KEY));
        this.setOwnerPlayerName(nbt.method_10558(OWNER_PLAYER_NAME_KEY));
        this.setOtherParentName(nbt.method_10558(OTHER_PARENT_NAME_KEY));
        this.setDynastyHouseId(nbt.method_10558(HOUSE_ID_KEY));
        this.setFamilyStage(GotDynastyNpcs.FamilyStage.byId(nbt.method_10558(FAMILY_STAGE_KEY)));
        if (nbt.method_10573(BORN_DAY_KEY, 99)) {
            this.setBornDay(nbt.method_10537(BORN_DAY_KEY));
        }
        this.setOwnerSmallArms(nbt.method_10577(OWNER_SMALL_ARMS_KEY));
        class_2960 parsed = class_2960.method_12829((String)nbt.method_10558("gotmod.dynasty_kingdom_id"));
        if (parsed != null) {
            this.setDynastyKingdomId(parsed);
        }
    }

    public void ensureDefaultRelation(class_3222 player) {
        if (player == null || this.getPersistentNpcId().isBlank()) {
            return;
        }
        if (GotNpcRelations.getRelation(player, this) == 0) {
            ((PlayerNpcRelationsHolder)player).got$getNpcRelationsProfile().setRelation(this.getPersistentNpcId(), 20);
        }
    }
}

