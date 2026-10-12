/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1792
 *  net.minecraft.class_1802
 *  net.minecraft.class_5819
 *  net.minecraft.class_7923
 */
package trep.got.market;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_5819;
import net.minecraft.class_7923;

public record MarketDisplaySetupDefinition(int columnCount, List<Entry> entries) {
    public static final Codec<MarketDisplaySetupDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.intRange((int)1, (int)9).optionalFieldOf("column_count", (Object)9).forGetter(MarketDisplaySetupDefinition::columnCount), (App)Entry.CODEC.listOf().fieldOf("entries").forGetter(MarketDisplaySetupDefinition::entries)).apply((Applicative)instance, MarketDisplaySetupDefinition::new));

    public MarketDisplaySetupDefinition {
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("Market setup must define at least one entry");
        }
        entries = List.copyOf(entries);
    }

    public List<Entry> chooseEntries(class_5819 random) {
        int rolls = Math.min(9, Math.min(this.columnCount, this.entries.size()));
        ArrayList<Entry> pool = new ArrayList<Entry>(this.entries);
        ArrayList<Entry> chosen = new ArrayList<Entry>(rolls);
        block0: for (int roll = 0; roll < rolls && !pool.isEmpty(); ++roll) {
            int totalWeight = 0;
            for (Entry entry : pool) {
                totalWeight += entry.weight();
            }
            int target = random.method_43048(totalWeight);
            int running = 0;
            for (int index = 0; index < pool.size(); ++index) {
                Entry entry = (Entry)pool.get(index);
                if (target >= (running += entry.weight())) continue;
                chosen.add(entry);
                pool.remove(index);
                continue block0;
            }
        }
        return List.copyOf(chosen);
    }

    public record Entry(class_1792 sellItem, AmountRange sellCount, AmountRange saleAmount, class_1792 buyItem, AmountRange buyAmount, int weight) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)class_7923.field_41178.method_39673().fieldOf("sell_item").forGetter(Entry::sellItem), (App)AmountRange.CODEC.fieldOf("sell_count").forGetter(Entry::sellCount), (App)AmountRange.CODEC.fieldOf("sale_amount").forGetter(Entry::saleAmount), (App)class_7923.field_41178.method_39673().fieldOf("buy_item").forGetter(Entry::buyItem), (App)AmountRange.CODEC.fieldOf("buy_amount").forGetter(Entry::buyAmount), (App)Codec.intRange((int)1, (int)Integer.MAX_VALUE).optionalFieldOf("weight", (Object)1).forGetter(Entry::weight)).apply((Applicative)instance, Entry::new));

        public Entry {
            if (sellItem == class_1802.field_8162) {
                throw new IllegalArgumentException("sell_item cannot be air");
            }
            if (buyItem == class_1802.field_8162) {
                throw new IllegalArgumentException("buy_item cannot be air");
            }
        }
    }

    public record AmountRange(int min, int max) {
        public static final Codec<AmountRange> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.intRange((int)1, (int)64).fieldOf("min").forGetter(AmountRange::min), (App)Codec.intRange((int)1, (int)64).fieldOf("max").forGetter(AmountRange::max)).apply((Applicative)instance, AmountRange::new));

        public AmountRange {
            if (max < min) {
                throw new IllegalArgumentException("Amount range max cannot be lower than min");
            }
        }

        public int roll(class_5819 random) {
            return this.min == this.max ? this.min : random.method_39332(this.min, this.max);
        }
    }
}

