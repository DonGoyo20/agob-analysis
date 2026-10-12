/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2350$class_2353
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_243
 *  net.minecraft.class_2470
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2754
 *  net.minecraft.class_2769
 *  net.minecraft.class_3542
 *  net.minecraft.class_3726
 *  net.minecraft.class_3965
 *  net.minecraft.class_4538
 *  net.minecraft.class_4970$class_2251
 *  net.minecraft.class_9275
 *  net.minecraft.class_9334
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_243;
import net.minecraft.class_2470;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3542;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_4970;
import net.minecraft.class_9275;
import net.minecraft.class_9334;
import org.jetbrains.annotations.Nullable;
import trep.got.block.ChairWoodType;
import trep.got.entity.SeatEntity;

public final class SeatFurnitureBlock
extends class_2383 {
    public static final MapCodec<SeatFurnitureBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group((App)SeatFurnitureBlock.method_54096(), (App)Kind.CODEC.fieldOf("kind").forGetter(block -> block.kind)).apply((Applicative)instance, SeatFurnitureBlock::new));
    public static final class_2754<ChairWoodType> WOOD = class_2754.method_11850((String)"wood", ChairWoodType.class);
    private static final class_265[] CHAIR_SHAPES = SeatFurnitureBlock.createRotatedShapes(Kind.CHAIR);
    private static final class_265[] THRONE_SHAPES = SeatFurnitureBlock.createRotatedShapes(Kind.THRONE);
    private final Kind kind;

    public SeatFurnitureBlock(class_4970.class_2251 settings, Kind kind) {
        super(settings);
        this.kind = kind;
        this.method_9590((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)field_11177, (Comparable)class_2350.field_11043)).method_11657(WOOD, (Comparable)((Object)ChairWoodType.DARK_OAK)));
    }

    protected MapCodec<? extends class_2383> method_53969() {
        return CODEC;
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{class_2741.field_12481, WOOD});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)field_11177, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)field_11177, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)field_11177)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return this.shapeFor(state);
    }

    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return this.shapeFor(state);
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (player.method_5715()) {
            return class_1269.field_5811;
        }
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        SeatEntity existing = SeatFurnitureBlock.findSeat(world, pos);
        if (existing != null && existing.method_5782()) {
            return class_1269.field_21466;
        }
        if (existing != null) {
            existing.method_31472();
        }
        SeatEntity seat = new SeatEntity(world, pos, SeatFurnitureBlock.seatPosition(pos, (class_2350)state.method_11654((class_2769)field_11177)), (class_2350)state.method_11654((class_2769)field_11177));
        world.method_8649((class_1297)seat);
        if (!player.method_5873((class_1297)seat, true)) {
            seat.method_31472();
        }
        return class_1269.field_21466;
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        SeatEntity seat;
        if (!state.method_27852(newState.method_26204()) && (seat = SeatFurnitureBlock.findSeat(world, pos)) != null) {
            seat.method_31472();
        }
        super.method_9536(state, world, pos, newState, moved);
    }

    public class_1799 method_9574(class_4538 world, class_2338 pos, class_2680 state) {
        class_1799 stack = new class_1799((class_1935)this);
        stack.method_57379(class_9334.field_49623, (Object)class_9275.field_49284.method_57420(WOOD, (Comparable)((Object)((ChairWoodType)((Object)state.method_11654(WOOD))))));
        return stack;
    }

    private class_265 shapeFor(class_2680 state) {
        return (this.kind == Kind.THRONE ? THRONE_SHAPES : CHAIR_SHAPES)[((class_2350)state.method_11654((class_2769)field_11177)).method_10161()];
    }

    @Nullable
    private static SeatEntity findSeat(class_1937 world, class_2338 pos) {
        class_238 box = class_238.method_30048((class_243)class_243.method_24953((class_2382)pos), (double)1.5, (double)1.5, (double)1.5);
        List seats = world.method_8390(SeatEntity.class, box, seat -> pos.equals((Object)seat.getSeatBlockPos()));
        return seats.isEmpty() ? null : (SeatEntity)((Object)seats.getFirst());
    }

    private static class_243 seatPosition(class_2338 pos, class_2350 facing) {
        class_243 center = class_243.method_24953((class_2382)pos);
        class_243 offset = new class_243((double)facing.method_10148(), 0.0, (double)facing.method_10165()).method_1021(0.06);
        return new class_243(center.field_1352 + offset.field_1352, (double)pos.method_10264() + 0.64, center.field_1350 + offset.field_1350);
    }

    public static float yawFor(class_2350 facing) {
        return switch (facing) {
            case class_2350.field_11035 -> 0.0f;
            case class_2350.field_11039 -> 90.0f;
            case class_2350.field_11043 -> 180.0f;
            case class_2350.field_11034 -> 270.0f;
            default -> 0.0f;
        };
    }

    private static class_265[] createRotatedShapes(Kind kind) {
        class_265 north = kind == Kind.THRONE ? SeatFurnitureBlock.createThroneShape() : SeatFurnitureBlock.createChairShape();
        class_265[] shapes = new class_265[4];
        for (class_2350 direction : class_2350.class_2353.field_11062) {
            shapes[direction.method_10161()] = SeatFurnitureBlock.rotateShape(north, direction);
        }
        return shapes;
    }

    private static class_265 createChairShape() {
        return class_259.method_17786((class_265)class_2248.method_9541((double)1.0, (double)0.0, (double)0.0, (double)3.0, (double)8.0, (double)2.0), (class_265[])new class_265[]{class_2248.method_9541((double)13.0, (double)0.0, (double)0.0, (double)15.0, (double)8.0, (double)2.0), class_2248.method_9541((double)1.0, (double)0.0, (double)14.0, (double)3.0, (double)8.0, (double)16.0), class_2248.method_9541((double)13.0, (double)0.0, (double)14.0, (double)15.0, (double)8.0, (double)16.0), class_2248.method_9541((double)0.0, (double)8.0, (double)0.0, (double)16.0, (double)10.0, (double)16.0), class_2248.method_9541((double)0.0, (double)10.0, (double)14.0, (double)16.0, (double)25.0, (double)16.0), class_2248.method_9541((double)0.0, (double)12.0, (double)0.0, (double)3.0, (double)15.0, (double)16.0), class_2248.method_9541((double)13.0, (double)12.0, (double)0.0, (double)16.0, (double)15.0, (double)16.0)});
    }

    private static class_265 createThroneShape() {
        return class_259.method_17786((class_265)class_2248.method_9541((double)1.0, (double)0.0, (double)0.0, (double)3.0, (double)8.0, (double)2.0), (class_265[])new class_265[]{class_2248.method_9541((double)13.0, (double)0.0, (double)0.0, (double)15.0, (double)8.0, (double)2.0), class_2248.method_9541((double)1.0, (double)0.0, (double)14.0, (double)3.0, (double)8.0, (double)16.0), class_2248.method_9541((double)13.0, (double)0.0, (double)14.0, (double)15.0, (double)8.0, (double)16.0), class_2248.method_9541((double)3.0, (double)0.0, (double)2.0, (double)13.0, (double)10.0, (double)14.0), class_2248.method_9541((double)0.0, (double)8.0, (double)0.0, (double)16.0, (double)10.0, (double)16.0), class_2248.method_9541((double)0.0, (double)10.0, (double)14.0, (double)16.0, (double)27.0, (double)16.0), class_2248.method_9541((double)0.0, (double)12.0, (double)0.0, (double)3.0, (double)15.0, (double)16.0), class_2248.method_9541((double)13.0, (double)12.0, (double)0.0, (double)16.0, (double)15.0, (double)16.0), class_2248.method_9541((double)2.0, (double)9.0, (double)2.0, (double)3.0, (double)12.0, (double)14.0), class_2248.method_9541((double)13.0, (double)9.0, (double)2.0, (double)14.0, (double)12.0, (double)14.0)});
    }

    private static class_265 rotateShape(class_265 shape, class_2350 direction) {
        if (direction == class_2350.field_11043) {
            return shape;
        }
        class_265 rotated = class_259.method_1073();
        for (class_238 box : shape.method_1090()) {
            rotated = class_259.method_1084((class_265)rotated, (class_265)SeatFurnitureBlock.rotateBox(box, direction));
        }
        return rotated.method_1097();
    }

    private static class_265 rotateBox(class_238 box, class_2350 direction) {
        double minX = box.field_1323 * 16.0;
        double minY = box.field_1322 * 16.0;
        double minZ = box.field_1321 * 16.0;
        double maxX = box.field_1320 * 16.0;
        double maxY = box.field_1325 * 16.0;
        double maxZ = box.field_1324 * 16.0;
        return switch (direction) {
            case class_2350.field_11034 -> class_2248.method_9541((double)(16.0 - maxZ), (double)minY, (double)minX, (double)(16.0 - minZ), (double)maxY, (double)maxX);
            case class_2350.field_11035 -> class_2248.method_9541((double)(16.0 - maxX), (double)minY, (double)(16.0 - maxZ), (double)(16.0 - minX), (double)maxY, (double)(16.0 - minZ));
            case class_2350.field_11039 -> class_2248.method_9541((double)minZ, (double)minY, (double)(16.0 - maxX), (double)maxZ, (double)maxY, (double)(16.0 - minX));
            default -> class_2248.method_9541((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
        };
    }

    public static enum Kind implements class_3542
    {
        CHAIR("chair"),
        THRONE("throne");

        public static final Codec<Kind> CODEC;
        private final String name;

        private Kind(String name) {
            this.name = name;
        }

        public String method_15434() {
            return this.name;
        }

        static {
            CODEC = class_3542.method_28140(Kind::values);
        }
    }
}

