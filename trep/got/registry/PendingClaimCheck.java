/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_5321
 */
package trep.got.registry;

import java.util.UUID;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_5321;

record PendingClaimCheck(class_5321<class_1937> dimension, class_2338 bannerPos, UUID playerUuid) {
}

