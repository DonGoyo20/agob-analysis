/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_1266
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1296
 *  net.minecraft.class_1299
 *  net.minecraft.class_1308
 *  net.minecraft.class_1315
 *  net.minecraft.class_1320
 *  net.minecraft.class_1324
 *  net.minecraft.class_1429
 *  net.minecraft.class_1493
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1936
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3532
 *  net.minecraft.class_3730
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_6880
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import net.minecraft.class_124;
import net.minecraft.class_1266;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1296;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1315;
import net.minecraft.class_1320;
import net.minecraft.class_1324;
import net.minecraft.class_1429;
import net.minecraft.class_1493;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3532;
import net.minecraft.class_3730;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.registry.GotEntities;

public class DirewolfEntity
extends class_1493 {
    private static final class_2940<Integer> VARIANT = class_2945.method_12791(DirewolfEntity.class, (class_2941)class_2943.field_13327);
    private static final String VARIANT_KEY = "DirewolfVariant";
    private static final String STARK_OPTION_ID = "the_north_stark";
    private static final int VARIANT_COUNT = 4;
    private static final double WILD_HEALTH = 12.0;
    private static final double TAMED_HEALTH = 28.0;
    private static final double WILD_ATTACK_DAMAGE = 5.0;
    private static final double TAMED_ATTACK_DAMAGE = 6.0;

    public DirewolfEntity(class_1299<? extends DirewolfEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createDirewolfAttributes() {
        return class_1308.method_26828().method_26868(class_5134.field_23716, 12.0).method_26868(class_5134.field_23719, 0.36).method_26868(class_5134.field_23721, 5.0).method_26868(class_5134.field_23717, 24.0);
    }

    public static boolean canSpawn(class_1299<DirewolfEntity> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        return class_1429.method_20663(entityType, (class_1936)world, (class_3730)spawnReason, (class_2338)pos, (class_5819)random);
    }

    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(VARIANT, (Object)1);
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        class_1799 stack = player.method_5998(hand);
        if (!this.method_6181() && stack.method_31574(class_1802.field_8606) && !DirewolfEntity.isOriginHouseStark(player)) {
            if (!this.method_37908().field_9236) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Only those of House Stark can tame a direwolf.").method_27692(class_124.field_1080), true);
            }
            return class_1269.field_5812;
        }
        return super.method_5992(player, hand);
    }

    public void method_6173(boolean tamed, boolean updateAttributes) {
        super.method_6173(tamed, updateAttributes);
        if (updateAttributes) {
            this.method_56996();
        }
    }

    protected void method_56996() {
        this.setBaseAttribute((class_6880<class_1320>)class_5134.field_23716, this.method_6181() ? 28.0 : 12.0);
        this.setBaseAttribute((class_6880<class_1320>)class_5134.field_23721, this.method_6181() ? 6.0 : 5.0);
    }

    private void setBaseAttribute(class_6880<class_1320> attribute, double value) {
        class_1324 instance = this.method_5996(attribute);
        if (instance != null) {
            instance.method_6192(value);
        }
    }

    public boolean method_6474(class_1429 other) {
        return other instanceof DirewolfEntity && super.method_6474(other);
    }

    @Nullable
    public DirewolfEntity createChild(class_3218 world, class_1296 entity) {
        DirewolfEntity child = (DirewolfEntity)GotEntities.DIREWOLF.method_5883((class_1937)world);
        if (child != null) {
            int n;
            if (this.field_5974.method_43056() || !(entity instanceof DirewolfEntity)) {
                n = this.getDirewolfVariant();
            } else {
                DirewolfEntity other = (DirewolfEntity)entity;
                n = other.getDirewolfVariant();
            }
            child.setDirewolfVariant(n);
            if (this.method_6181()) {
                child.method_6174(this.method_6139());
                child.method_6173(true, true);
            }
        }
        return child;
    }

    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10569(VARIANT_KEY, this.getDirewolfVariant());
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_10545(VARIANT_KEY)) {
            this.setDirewolfVariant(nbt.method_10550(VARIANT_KEY));
        }
        this.method_56996();
    }

    @Nullable
    public class_1315 method_5943(class_5425 world, class_1266 difficulty, class_3730 spawnReason, @Nullable class_1315 entityData) {
        this.setDirewolfVariant(DirewolfEntity.randomVariant(world.method_8409()));
        return super.method_5943(world, difficulty, spawnReason, entityData);
    }

    public int getDirewolfVariant() {
        return class_3532.method_15340((int)((Integer)this.field_6011.method_12789(VARIANT)), (int)1, (int)4);
    }

    public void setDirewolfVariant(int variant) {
        this.field_6011.method_12778(VARIANT, (Object)class_3532.method_15340((int)variant, (int)1, (int)4));
    }

    private static int randomVariant(class_5819 random) {
        return 1 + random.method_43048(4);
    }

    private static boolean isOriginHouseStark(class_1657 player) {
        if (!(player instanceof PlayerCharacterHolder)) {
            return false;
        }
        PlayerCharacterHolder holder = (PlayerCharacterHolder)player;
        PlayerCharacterProfile profile = holder.got$getCharacterProfile();
        String originOptionId = profile.getOriginalSelectedOptionId();
        if (originOptionId != null && !originOptionId.isBlank()) {
            return DirewolfEntity.isStarkOption(originOptionId);
        }
        return DirewolfEntity.isStarkOption(profile.getSelectedOptionId());
    }

    private static boolean isStarkOption(String optionId) {
        if (optionId == null || optionId.isBlank()) {
            return false;
        }
        int separator = optionId.indexOf(58);
        String path = separator >= 0 ? optionId.substring(separator + 1) : optionId;
        return STARK_OPTION_ID.equals(path);
    }
}

