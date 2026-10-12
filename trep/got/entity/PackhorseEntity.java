/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1299
 *  net.minecraft.class_1496
 *  net.minecraft.class_1500
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2561
 */
package trep.got.entity;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1299;
import net.minecraft.class_1496;
import net.minecraft.class_1500;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import trep.got.entity.GotMountStats;
import trep.got.skill.PlayerSkillHolder;
import trep.got.skill.SkillAttributeType;

public final class PackhorseEntity
extends class_1500 {
    private static final String STATS_INITIALIZED_KEY = "gotmod.mount_stats_initialized";
    private static final GotMountStats.StatRange STATS = new GotMountStats.StatRange(0.15, 0.21, 0.5, 0.75, 22.0, 32.0);
    private boolean got$statsInitialized;

    public PackhorseEntity(class_1299<? extends PackhorseEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public class_1269 method_5992(class_1657 player, class_1268 hand) {
        if (this.got$lacksRequiredRidingSkill(player, hand)) {
            if (!this.method_37908().method_8608()) {
                player.method_7353((class_2561)class_2561.method_43470((String)"Requires Riding 20"), true);
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

    void got$initializeRandomStats() {
        GotMountStats.applyRandomizedStats((class_1496)this, STATS);
        this.got$statsInitialized = true;
    }

    void got$initializeBredStats(class_1496 firstParent, class_1496 secondParent) {
        GotMountStats.applyBredStats((class_1496)this, firstParent, secondParent, STATS);
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

    private boolean got$lacksRequiredRidingSkill(class_1657 player, class_1268 hand) {
        if (player.method_21823() || this.method_6109() || this.method_6481(player.method_5998(hand))) {
            return false;
        }
        if (!(player instanceof PlayerSkillHolder)) {
            return true;
        }
        PlayerSkillHolder skillHolder = (PlayerSkillHolder)player;
        int ridingPoints = skillHolder.got$getSkillProfile().get(SkillAttributeType.RIDING).getPoints();
        return ridingPoints < 20;
    }
}

