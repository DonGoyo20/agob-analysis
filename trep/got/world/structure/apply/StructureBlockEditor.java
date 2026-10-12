/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1959
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_6880
 */
package trep.got.world.structure.apply;

import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2902;
import net.minecraft.class_6880;

public interface StructureBlockEditor {
    public int getTopY(class_2902.class_2903 var1, int var2, int var3);

    public class_2680 getBlockState(class_2338 var1);

    public void setBlockState(class_2338 var1, class_2680 var2, int var3);

    public class_6880<class_1959> getBiome(class_2338 var1);

    public int getBottomY();

    public int getSeaLevel();

    public boolean canEdit(class_2338 var1);
}

