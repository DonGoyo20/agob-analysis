/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1429
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2960
 *  net.minecraft.class_3730
 *  net.minecraft.class_5321
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_6862
 *  net.minecraft.class_7924
 */
package trep.got.world.spawn;

import net.minecraft.class_1299;
import net.minecraft.class_1429;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_3730;
import net.minecraft.class_5321;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public final class PassiveSpawnRules {
    public static final class_6862<class_2248> MOUNTS_SPAWNABLE_ON = PassiveSpawnRules.blockTag("mounts_spawnable_on");
    public static final class_6862<class_2248> DEER_SPAWNABLE_ON = PassiveSpawnRules.blockTag("deer_spawnable_on");
    public static final class_6862<class_2248> DIREWOLVES_SPAWNABLE_ON = PassiveSpawnRules.blockTag("direwolves_spawnable_on");

    private PassiveSpawnRules() {
    }

    public static <T extends class_1429> boolean canMountSpawn(class_1299<T> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        return PassiveSpawnRules.canSpawnOn(world, spawnReason, pos, MOUNTS_SPAWNABLE_ON);
    }

    public static <T extends class_1429> boolean canDeerSpawn(class_1299<T> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        return PassiveSpawnRules.canSpawnOn(world, spawnReason, pos, DEER_SPAWNABLE_ON);
    }

    public static <T extends class_1429> boolean canDirewolfSpawn(class_1299<T> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        return PassiveSpawnRules.canSpawnOn(world, spawnReason, pos, DIREWOLVES_SPAWNABLE_ON);
    }

    private static boolean canSpawnOn(class_5425 world, class_3730 spawnReason, class_2338 pos, class_6862<class_2248> surfaces) {
        boolean validLight = class_3730.method_54987((class_3730)spawnReason) || world.method_22335(pos, 0) > 8;
        return validLight && world.method_8320(pos.method_10074()).method_26164(surfaces);
    }

    private static class_6862<class_2248> blockTag(String path) {
        return class_6862.method_40092((class_5321)class_7924.field_41254, (class_2960)class_2960.method_60655((String)"agob", (String)path));
    }
}

