/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_3532
 */
package trep.got.skill;

import net.minecraft.class_2487;
import net.minecraft.class_3532;

public final class SkillAttributeState {
    private int points;
    private int level;
    private int experience;
    private int maxPointsBonus;

    public SkillAttributeState() {
        this(0, 0, 0, 0);
    }

    public SkillAttributeState(int points, int level, int experience) {
        this(points, level, experience, 0);
    }

    public SkillAttributeState(int points, int level, int experience, int maxPointsBonus) {
        this.maxPointsBonus = SkillAttributeState.clampMaxPointsBonus(maxPointsBonus);
        this.points = this.clampPoints(points);
        this.level = Math.max(0, level);
        this.experience = Math.max(0, experience);
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = this.clampPoints(points);
    }

    public int getMaxPoints() {
        return 100 + this.maxPointsBonus;
    }

    public int getMaxPointsBonus() {
        return this.maxPointsBonus;
    }

    public void setMaxPointsBonus(int maxPointsBonus) {
        this.maxPointsBonus = SkillAttributeState.clampMaxPointsBonus(maxPointsBonus);
        this.points = this.clampPoints(this.points);
    }

    public void addMaxPointsBonus(int maxPointsBonus) {
        this.setMaxPointsBonus(this.maxPointsBonus + maxPointsBonus);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = Math.max(0, level);
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
    }

    public SkillAttributeState copy() {
        return new SkillAttributeState(this.points, this.level, this.experience, this.maxPointsBonus);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10569("points", this.points);
        nbt.method_10569("level", this.level);
        nbt.method_10569("experience", this.experience);
        nbt.method_10569("max_points_bonus", this.maxPointsBonus);
        return nbt;
    }

    public static SkillAttributeState fromNbt(class_2487 nbt) {
        return new SkillAttributeState(nbt.method_10545("points") ? nbt.method_10550("points") : 0, nbt.method_10545("level") ? nbt.method_10550("level") : 0, nbt.method_10545("experience") ? nbt.method_10550("experience") : 0, nbt.method_10545("max_points_bonus") ? nbt.method_10550("max_points_bonus") : 0);
    }

    private int clampPoints(int points) {
        return class_3532.method_15340((int)points, (int)0, (int)this.getMaxPoints());
    }

    private static int clampMaxPointsBonus(int maxPointsBonus) {
        return class_3532.method_15340((int)maxPointsBonus, (int)-100, (int)100);
    }
}

