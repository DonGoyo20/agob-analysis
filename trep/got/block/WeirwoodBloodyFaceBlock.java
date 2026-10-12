/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1269
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_3222
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import trep.got.block.WeirwoodFaceBlock;
import trep.got.registry.GotCastles;

public final class WeirwoodBloodyFaceBlock
extends WeirwoodFaceBlock {
    public static final MapCodec<WeirwoodBloodyFaceBlock> CODEC = WeirwoodBloodyFaceBlock.method_54094(WeirwoodBloodyFaceBlock::new);

    public WeirwoodBloodyFaceBlock(class_4970.class_2251 settings) {
        super(settings);
    }

    protected MapCodec<? extends WeirwoodFaceBlock> method_53969() {
        return CODEC;
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        if (!(player instanceof class_3222)) {
            return class_1269.field_21466;
        }
        class_3222 serverPlayer = (class_3222)player;
        return GotCastles.handleWeirwoodBloodyFaceUse(serverPlayer, pos);
    }
}

