/*
 * Decompiled with CFR 0.152.
 */
package trep.got.skill;

public record SkillLevelCurve(int minLevel, int maxLevel, int baseExperience, double exponent) {
    public SkillLevelCurve {
        if (minLevel < 0) {
            throw new IllegalArgumentException("minLevel must be at least 0");
        }
        if (maxLevel < minLevel) {
            throw new IllegalArgumentException("maxLevel must be >= minLevel");
        }
        if (baseExperience < 0) {
            throw new IllegalArgumentException("Experience requirements must be non-negative");
        }
        if (exponent < 1.0) {
            throw new IllegalArgumentException("exponent must be at least 1.0");
        }
    }

    public int experienceForLevel(int level) {
        if (level <= this.minLevel) {
            return 0;
        }
        int relativeLevel = Math.min(level, this.maxLevel) - this.minLevel;
        return (int)Math.round((double)this.baseExperience * Math.pow(relativeLevel, this.exponent));
    }
}

