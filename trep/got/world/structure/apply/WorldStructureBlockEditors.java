/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1923
 *  net.minecraft.class_1959
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_3218
 *  net.minecraft.class_3233
 *  net.minecraft.class_6880
 */
package trep.got.world.structure.apply;

import net.minecraft.class_1923;
import net.minecraft.class_1959;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_3218;
import net.minecraft.class_3233;
import net.minecraft.class_6880;
import trep.got.world.structure.apply.StructureBlockEditor;

public final class WorldStructureBlockEditors {
    private WorldStructureBlockEditors() {
    }

    public static StructureBlockEditor forWorld(final class_3218 world) {
        return new StructureBlockEditor(){

            @Override
            public int getTopY(class_2902.class_2903 heightmap, int x, int z) {
                return world.method_8624(heightmap, x, z);
            }

            @Override
            public class_2680 getBlockState(class_2338 pos) {
                return world.method_8320(pos);
            }

            @Override
            public void setBlockState(class_2338 pos, class_2680 state, int flags) {
                world.method_8652(pos, state, flags);
            }

            @Override
            public class_6880<class_1959> getBiome(class_2338 pos) {
                return world.method_23753(pos);
            }

            @Override
            public int getBottomY() {
                return world.method_31607();
            }

            @Override
            public int getSeaLevel() {
                return world.method_8615();
            }

            @Override
            public boolean canEdit(class_2338 pos) {
                return true;
            }
        };
    }

    public static StructureBlockEditor forRegion(class_3233 region) {
        return WorldStructureBlockEditors.forRegion(region, region.method_33561());
    }

    public static StructureBlockEditor forRegion(final class_3233 region, final class_1923 editChunk) {
        return new StructureBlockEditor(){

            @Override
            public int getTopY(class_2902.class_2903 heightmap, int x, int z) {
                return region.method_8624(heightmap, x, z);
            }

            @Override
            public class_2680 getBlockState(class_2338 pos) {
                if (!this.canEdit(pos)) {
                    return class_2246.field_10124.method_9564();
                }
                return region.method_8320(pos);
            }

            @Override
            public void setBlockState(class_2338 pos, class_2680 state, int flags) {
                if (this.canEdit(pos)) {
                    region.method_8652(pos, state, flags);
                }
            }

            @Override
            public class_6880<class_1959> getBiome(class_2338 pos) {
                return region.method_23753(pos);
            }

            @Override
            public int getBottomY() {
                return region.method_31607();
            }

            @Override
            public int getSeaLevel() {
                return region.method_8410().method_8615();
            }

            @Override
            public boolean canEdit(class_2338 pos) {
                if (editChunk != null && !this.isInsideChunk(pos, editChunk)) {
                    return false;
                }
                return pos.method_10264() >= region.method_31607() && pos.method_10264() < region.method_31600();
            }

            private boolean isInsideChunk(class_2338 pos, class_1923 chunkPos) {
                return pos.method_10263() >= chunkPos.method_8326() && pos.method_10263() <= chunkPos.method_8327() && pos.method_10260() >= chunkPos.method_8328() && pos.method_10260() <= chunkPos.method_8329();
            }
        };
    }
}

