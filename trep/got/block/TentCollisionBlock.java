/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2464
 *  net.minecraft.class_2586
 *  net.minecraft.class_259
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2758
 *  net.minecraft.class_2769
 *  net.minecraft.class_3218
 *  net.minecraft.class_3726
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3726;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.block.AbstractMultiBlockMachineBlock;
import trep.got.block.entity.TentCollisionBlockEntity;
import trep.got.entity.TentEntity;
import trep.got.registry.GotItems;

public final class TentCollisionBlock
extends AbstractMultiBlockMachineBlock {
    private static final class_2758 TENT_PART_X = class_2758.method_11867((String)"part_x", (int)0, (int)1);
    private static final class_2758 TENT_PART_Z = class_2758.method_11867((String)"part_z", (int)0, (int)2);
    private static final class_2758 TENT_PART_Y = class_2758.method_11867((String)"part_y", (int)0, (int)2);
    public static final int WIDTH = 2;
    public static final int DEPTH = 3;
    public static final int HEIGHT = 3;
    private static final class_265[][][] PART_SHAPES = TentCollisionBlock.buildPartShapes();

    public TentCollisionBlock(class_4970.class_2251 settings) {
        super(settings, 2, 3, 3);
    }

    @Override
    @Nullable
    protected class_2586 createOriginBlockEntity(class_2338 pos, class_2680 state) {
        return new TentCollisionBlockEntity(pos, state);
    }

    @Override
    protected class_265 getPartShape(class_2680 state, boolean collision) {
        class_265 shape = PART_SHAPES[(Integer)state.method_11654((class_2769)TENT_PART_X)][(Integer)state.method_11654((class_2769)TENT_PART_Y)][(Integer)state.method_11654((class_2769)TENT_PART_Z)];
        return this.rotateShape(shape, (class_2350)state.method_11654((class_2769)FACING));
    }

    @Override
    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return class_259.method_1073();
    }

    protected class_2464 method_9604(class_2680 state) {
        return class_2464.field_11455;
    }

    protected class_265 method_26159(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return class_259.method_1073();
    }

    @Override
    protected class_265 method_9549(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return class_259.method_1073();
    }

    public boolean canPlaceStructure(class_1937 world, class_2338 origin, class_2350 facing) {
        for (int partY = 0; partY < 3; ++partY) {
            for (int partZ = 0; partZ < 3; ++partZ) {
                for (int partX = 0; partX < 2; ++partX) {
                    class_2338 partPos = this.offset(origin, facing, partX, partZ, partY);
                    class_2680 partState = world.method_8320(partPos);
                    if (partState.method_26215() || partState.method_45474()) continue;
                    return false;
                }
            }
        }
        return true;
    }

    public void placeStructure(class_3218 world, class_2338 origin, class_2350 facing, UUID tentUuid) {
        class_2680 baseState = (class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)facing);
        for (int partY = 0; partY < 3; ++partY) {
            for (int partZ = 0; partZ < 3; ++partZ) {
                for (int partX = 0; partX < 2; ++partX) {
                    class_2338 partPos = this.offset(origin, facing, partX, partZ, partY);
                    class_2680 partState = (class_2680)((class_2680)((class_2680)baseState.method_11657((class_2769)TENT_PART_X, (Comparable)Integer.valueOf(partX))).method_11657((class_2769)TENT_PART_Z, (Comparable)Integer.valueOf(partZ))).method_11657((class_2769)TENT_PART_Y, (Comparable)Integer.valueOf(partY));
                    world.method_8652(partPos, partState, 3);
                }
            }
        }
        class_2586 blockEntity = world.method_8321(origin);
        if (blockEntity instanceof TentCollisionBlockEntity) {
            TentCollisionBlockEntity tentBlockEntity = (TentCollisionBlockEntity)blockEntity;
            tentBlockEntity.setTentEntityUuid(tentUuid);
        }
    }

    public void destroyStructure(class_1937 world, class_2338 pos, class_2680 state, boolean dropItem) {
        class_2338 origin = this.getOriginPos(pos, state);
        class_2586 blockEntity = world.method_8321(origin);
        if (blockEntity instanceof TentCollisionBlockEntity) {
            TentCollisionBlockEntity tentBlockEntity = (TentCollisionBlockEntity)blockEntity;
            if (world instanceof class_3218) {
                class_1297 entity;
                class_3218 serverWorld = (class_3218)world;
                UUID tentUuid = tentBlockEntity.getTentEntityUuid();
                if (tentUuid != null && (entity = serverWorld.method_14190(tentUuid)) instanceof TentEntity) {
                    TentEntity tentEntity = (TentEntity)entity;
                    tentEntity.method_31472();
                }
            }
        }
        this.breakStructure(world, pos, state, false);
        if (dropItem && world instanceof class_3218) {
            class_3218 serverWorld = (class_3218)world;
            class_2248.method_9577((class_1937)serverWorld, (class_2338)origin, (class_1799)new class_1799((class_1935)GotItems.TENT));
        }
    }

    public Vec3 centerForEntity(class_2338 origin, class_2350 facing) {
        class_2350 right = facing.method_10170();
        class_2350 back = facing.method_10153();
        double x = (double)origin.method_10263() + 0.5 + (double)right.method_10148() * 0.5 + (double)back.method_10148() * 1.0;
        double y = origin.method_10264();
        double z = (double)origin.method_10260() + 0.5 + (double)right.method_10165() * 0.5 + (double)back.method_10165() * 1.0;
        return new Vec3(x, y, z);
    }

    @Override
    protected class_2758 getPartXProperty() {
        return TENT_PART_X;
    }

    @Override
    protected class_2758 getPartZProperty() {
        return TENT_PART_Z;
    }

    @Override
    protected class_2758 getPartYProperty() {
        return TENT_PART_Y;
    }

    private static class_265[][][] buildPartShapes() {
        double y1;
        double y0;
        double z1;
        int slice;
        class_265[][][] shapes = new class_265[2][3][3];
        for (int partX = 0; partX < 2; ++partX) {
            for (int partY = 0; partY < 3; ++partY) {
                for (int partZ = 0; partZ < 3; ++partZ) {
                    shapes[partX][partY][partZ] = class_259.method_1073();
                }
            }
        }
        for (slice = 0; slice < 24; ++slice) {
            double z0 = (double)slice / 16.0;
            z1 = (double)(slice + 1) / 16.0;
            y0 = (double)slice / 8.0;
            y1 = y0 + 0.125;
            TentCollisionBlock.addGlobalBox(shapes, 0.0, y0, z0, 2.0, y1, z1);
        }
        for (slice = 0; slice < 24; ++slice) {
            double z0 = 1.5 + (double)slice / 16.0;
            z1 = 1.5 + (double)(slice + 1) / 16.0;
            y0 = (double)(23 - slice) / 8.0;
            y1 = y0 + 0.125;
            TentCollisionBlock.addGlobalBox(shapes, 0.0, y0, z0, 2.0, y1, z1);
        }
        TentCollisionBlock.addGlobalBox(shapes, 0.0, 2.95, 1.40625, 2.0, 3.0, 1.59375);
        TentCollisionBlock.addGlobalBox(shapes, 0.875, 0.0, 1.40625, 1.125, 3.0, 1.59375);
        TentCollisionBlock.addGlobalBox(shapes, 0.8125, 2.8125, 1.34375, 1.1875, 3.0, 1.65625);
        return shapes;
    }

    private static void addGlobalBox(class_265[][][] shapes, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        for (int partX = 0; partX < 2; ++partX) {
            double clippedMaxX;
            double clippedMinX = Math.max(minX, (double)partX);
            if (clippedMinX >= (clippedMaxX = Math.min(maxX, (double)partX + 1.0))) continue;
            for (int partY = 0; partY < 3; ++partY) {
                double clippedMaxY;
                double clippedMinY = Math.max(minY, (double)partY);
                if (clippedMinY >= (clippedMaxY = Math.min(maxY, (double)partY + 1.0))) continue;
                for (int partZ = 0; partZ < 3; ++partZ) {
                    double clippedMaxZ;
                    double clippedMinZ = Math.max(minZ, (double)partZ);
                    if (clippedMinZ >= (clippedMaxZ = Math.min(maxZ, (double)partZ + 1.0))) continue;
                    class_265 piece = class_259.method_1081((double)(clippedMinX - (double)partX), (double)(clippedMinY - (double)partY), (double)(clippedMinZ - (double)partZ), (double)(clippedMaxX - (double)partX), (double)(clippedMaxY - (double)partY), (double)(clippedMaxZ - (double)partZ));
                    shapes[partX][partY][partZ] = class_259.method_1084((class_265)shapes[partX][partY][partZ], (class_265)piece).method_1097();
                }
            }
        }
    }

    public record Vec3(double x, double y, double z) {
    }
}

