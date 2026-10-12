/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10
 *  net.minecraft.class_1264
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2343
 *  net.minecraft.class_2350
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2741
 *  net.minecraft.class_2753
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import net.minecraft.class_10;
import net.minecraft.class_1264;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2741;
import net.minecraft.class_2753;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMultiBlockMachineBlock
extends class_2248
implements class_2343 {
    public static final class_2753 FACING = class_2741.field_12481;
    public static final class_2758 PART_X = class_2758.method_11867((String)"part_x", (int)0, (int)1);
    public static final class_2758 PART_Z = class_2758.method_11867((String)"part_z", (int)0, (int)1);
    public static final class_2758 PART_Y = class_2758.method_11867((String)"part_y", (int)0, (int)1);
    private final int width;
    private final int depth;
    private final int height;

    protected AbstractMultiBlockMachineBlock(class_4970.class_2251 settings, int width, int depth, int height) {
        super(settings);
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.method_9590((class_2680)((class_2680)((class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043)).method_11657((class_2769)this.getPartXProperty(), (Comparable)Integer.valueOf(0))).method_11657((class_2769)this.getPartZProperty(), (Comparable)Integer.valueOf(0))).method_11657((class_2769)this.getPartYProperty(), (Comparable)Integer.valueOf(0)));
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING, this.getPartXProperty(), this.getPartZProperty(), this.getPartYProperty()});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        class_2350 facing = ctx.method_8042().method_10153();
        class_2338 origin = ctx.method_8037();
        class_1937 world = ctx.method_8045();
        for (int partY = 0; partY < this.height; ++partY) {
            for (int partZ = 0; partZ < this.depth; ++partZ) {
                for (int partX = 0; partX < this.width; ++partX) {
                    class_2338 partPos = this.offset(origin, facing, partX, partZ, partY);
                    if (world.method_8320(partPos).method_26166(ctx)) continue;
                    return null;
                }
            }
        }
        return (class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)facing);
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        if (world.field_9236) {
            return;
        }
        class_2350 facing = (class_2350)state.method_11654((class_2769)FACING);
        for (int partY = 0; partY < this.height; ++partY) {
            for (int partZ = 0; partZ < this.depth; ++partZ) {
                for (int partX = 0; partX < this.width; ++partX) {
                    class_2338 partPos = this.offset(pos, facing, partX, partZ, partY);
                    class_2680 partState = (class_2680)((class_2680)((class_2680)state.method_11657((class_2769)this.getPartXProperty(), (Comparable)Integer.valueOf(partX))).method_11657((class_2769)this.getPartZProperty(), (Comparable)Integer.valueOf(partZ))).method_11657((class_2769)this.getPartYProperty(), (Comparable)Integer.valueOf(partY));
                    world.method_8652(partPos, partState, 3);
                }
            }
        }
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return this.getPartShape(state, false);
    }

    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return this.getPartShape(state, true);
    }

    public class_2680 method_9576(class_1937 world, class_2338 pos, class_2680 state, class_1657 player) {
        if (!world.field_9236) {
            this.breakStructure(world, pos, state, !player.method_7337());
        }
        return super.method_9576(world, pos, state, player);
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return this.isOrigin(state) ? this.createOriginBlockEntity(pos, state) : null;
    }

    @Nullable
    protected abstract class_2586 createOriginBlockEntity(class_2338 var1, class_2680 var2);

    protected abstract class_265 getPartShape(class_2680 var1, boolean var2);

    protected void breakStructure(class_1937 world, class_2338 pos, class_2680 state, boolean dropItem) {
        class_2338 origin = this.getOriginPos(pos, state);
        class_2680 originState = world.method_8320(origin);
        if (!originState.method_27852((class_2248)this)) {
            return;
        }
        if (dropItem && world instanceof class_3218) {
            class_3218 serverWorld = (class_3218)world;
            class_1264.method_5449((class_1937)serverWorld, (double)origin.method_10263(), (double)origin.method_10264(), (double)origin.method_10260(), (class_1799)new class_1799((class_1935)this.method_8389()));
        }
        class_2350 facing = (class_2350)originState.method_11654((class_2769)FACING);
        for (int partY = 0; partY < this.height; ++partY) {
            for (int partZ = 0; partZ < this.depth; ++partZ) {
                for (int partX = 0; partX < this.width; ++partX) {
                    class_2338 partPos = this.offset(origin, facing, partX, partZ, partY);
                    class_2680 partState = world.method_8320(partPos);
                    if (!partState.method_27852((class_2248)this)) continue;
                    world.method_8650(partPos, false);
                }
            }
        }
    }

    protected boolean isOrigin(class_2680 state) {
        return (Integer)state.method_11654((class_2769)this.getPartXProperty()) == 0 && (Integer)state.method_11654((class_2769)this.getPartZProperty()) == 0 && (Integer)state.method_11654((class_2769)this.getPartYProperty()) == 0;
    }

    protected class_2338 getOriginPos(class_2338 pos, class_2680 state) {
        return this.offset(pos, (class_2350)state.method_11654((class_2769)FACING), -((Integer)state.method_11654((class_2769)this.getPartXProperty())).intValue(), -((Integer)state.method_11654((class_2769)this.getPartZProperty())).intValue(), -((Integer)state.method_11654((class_2769)this.getPartYProperty())).intValue());
    }

    public class_2338 getOriginForScreen(class_2338 pos, class_2680 state) {
        return this.getOriginPos(pos, state);
    }

    protected class_2338 offset(class_2338 origin, class_2350 facing, int partX, int partZ, int partY) {
        class_2350 right = facing.method_10170();
        class_2350 back = facing.method_10153();
        return origin.method_10079(right, partX).method_10079(back, partZ).method_10086(partY);
    }

    protected class_265 rotateShape(class_265 shape, class_2350 facing) {
        if (facing == class_2350.field_11043) {
            return shape;
        }
        class_265[] rotated = new class_265[]{class_259.method_1073()};
        shape.method_1089((minX, minY, minZ, maxX, maxY, maxZ) -> {
            class_265 box = switch (facing) {
                case class_2350.field_11034 -> class_259.method_1081((double)(1.0 - maxZ), (double)minY, (double)minX, (double)(1.0 - minZ), (double)maxY, (double)maxX);
                case class_2350.field_11035 -> class_259.method_1081((double)(1.0 - maxX), (double)minY, (double)(1.0 - maxZ), (double)(1.0 - minX), (double)maxY, (double)(1.0 - minZ));
                case class_2350.field_11039 -> class_259.method_1081((double)minZ, (double)minY, (double)(1.0 - maxX), (double)maxZ, (double)maxY, (double)(1.0 - minX));
                default -> class_259.method_1081((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
            };
            rotated[0] = class_259.method_1084((class_265)rotated[0], (class_265)box);
        });
        return rotated[0].method_1097();
    }

    protected boolean method_9516(class_2680 state, class_10 type) {
        return false;
    }

    protected class_2758 getPartXProperty() {
        return PART_X;
    }

    protected class_2758 getPartZProperty() {
        return PART_Z;
    }

    protected class_2758 getPartYProperty() {
        return PART_Y;
    }
}

