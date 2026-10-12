/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 */
package trep.got.quest;

import net.minecraft.class_2487;
import trep.got.kingdom.ReputationTitle;
import trep.got.quest.QuestRewardType;
import trep.got.skill.SkillAttributeType;

public record QuestReward(QuestRewardType type, int minAmount, int maxAmount, SkillAttributeType skillType, boolean perItem, int chancePercent, int peasantBonus) {
    public int roll(long seed, int requiredCount, ReputationTitle title) {
        int chanceRoll;
        if (this.chancePercent > 0 && (chanceRoll = Math.floorMod((int)(seed * 31L ^ seed >>> 28), 100)) >= this.chancePercent) {
            return 0;
        }
        if (this.maxAmount <= this.minAmount) {
            return this.scaleAmount(Math.max(0, this.minAmount), requiredCount, title);
        }
        int bound = this.maxAmount - this.minAmount + 1;
        int amount = this.minAmount + Math.floorMod((int)(seed ^ seed >>> 32), bound);
        return this.scaleAmount(amount, requiredCount, title);
    }

    private int scaleAmount(int amount, int requiredCount, ReputationTitle title) {
        int scaled;
        int n = scaled = this.perItem ? amount * Math.max(1, requiredCount) : amount;
        if (title == ReputationTitle.PEASANT) {
            scaled += this.peasantBonus;
        }
        return Math.max(0, scaled);
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        nbt.method_10582("type", this.type.getId());
        nbt.method_10569("min_amount", this.minAmount);
        nbt.method_10569("max_amount", this.maxAmount);
        nbt.method_10582("skill_type", this.skillType == null ? "" : this.skillType.getId());
        nbt.method_10556("per_item", this.perItem);
        nbt.method_10569("chance_percent", this.chancePercent);
        nbt.method_10569("peasant_bonus", this.peasantBonus);
        return nbt;
    }

    public static QuestReward fromNbt(class_2487 nbt) {
        return new QuestReward(QuestRewardType.byId(nbt.method_10558("type")), nbt.method_10550("min_amount"), nbt.method_10550("max_amount"), SkillAttributeType.byId(nbt.method_10558("skill_type")), nbt.method_10577("per_item"), nbt.method_10550("chance_percent"), nbt.method_10550("peasant_bonus"));
    }
}

