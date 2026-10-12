/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1296
 *  net.minecraft.class_1299
 *  net.minecraft.class_1495
 *  net.minecraft.class_1496
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 */
package trep.got.entity;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1296;
import net.minecraft.class_1299;
import net.minecraft.class_1495;
import net.minecraft.class_1496;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import trep.got.entity.GotDonkeyEntity;
import trep.got.entity.GotMountStats;
import trep.got.entity.PackhorseEntity;
import trep.got.registry.GotEntities;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;

public abstract class AbstractTieredDonkeyEntity
extends class_1495 {
    private static final String STATS_INITIALIZED_KEY = "gotmod.mount_stats_initialized";
    private boolean got$statsInitialized;

    protected AbstractTieredDonkeyEntity(class_1299<? extends class_1495> entityType, class_1937 world) {
        super(entityType, world);
    }

    protected abstract GotMountStats.StatRange got$getStatRange();

    protected int got$getRidingRequirement() {
        return 10;
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        if (this.got$lacksRequiredRidingSkill(player, hand)) {
            if (!this.method_37908().method_8608()) {
                player.method_7353((class_2561)class_2561.method_43470((String)("Requires Riding " + this.got$getRidingRequirement())), true);
            }
            return class_1269.field_5812;
        }
        return super.method_5992(player, hand);
    }

    public void method_5773() {
        super.method_5773();
        if (!this.method_37908().method_8608() && !this.got$statsInitialized) {
            this.got$initializeRandomStats();
        }
    }

    protected void got$initializeRandomStats() {
        GotMountStats.applyRandomizedStats((class_1496)this, this.got$getStatRange());
        this.got$statsInitialized = true;
    }

    protected void got$initializeBredStats(class_1496 firstParent, class_1496 secondParent) {
        GotMountStats.applyBredStats((class_1496)this, firstParent, secondParent, this.got$getStatRange());
        this.got$statsInitialized = true;
    }

    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        nbt.method_10556(STATS_INITIALIZED_KEY, this.got$statsInitialized);
    }

    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        this.got$statsInitialized = nbt.method_10577(STATS_INITIALIZED_KEY);
    }

    public class_1296 method_5613(class_3218 world, class_1296 entity) {
        GotDonkeyEntity child;
        if (entity instanceof class_1496) {
            class_1496 horse = (class_1496)entity;
            if (!(entity instanceof class_1495)) {
                PackhorseEntity child2 = (PackhorseEntity)GotEntities.PACKHORSE.method_5883((class_1937)world);
                if (child2 != null) {
                    child2.got$initializeBredStats((class_1496)this, horse);
                }
                return child2;
            }
        }
        if ((child = (GotDonkeyEntity)GotEntities.GOT_DONKEY.method_5883((class_1937)world)) != null) {
            AbstractTieredDonkeyEntity abstractTieredDonkeyEntity;
            if (entity instanceof class_1496) {
                class_1496 horse = (class_1496)entity;
                abstractTieredDonkeyEntity = horse;
            } else {
                abstractTieredDonkeyEntity = this;
            }
            child.got$initializeBredStats((class_1496)this, (class_1496)abstractTieredDonkeyEntity);
        }
        return child;
    }

    private boolean got$lacksRequiredRidingSkill(class_1657 player, class_1268 hand) {
        if (player.method_21823() || this.method_6109() || this.method_6481(player.method_5998(hand))) {
            return false;
        }
        if (!(player instanceof PlayerSkillHolder)) {
            return this.got$getRidingRequirement() > 0;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        int ridingPoints = skillHolder.got$getSkillProfile().get(SkillAttributeType.RIDING).getPoints();
        return ridingPoints < this.got$getRidingRequirement();
    }
}

