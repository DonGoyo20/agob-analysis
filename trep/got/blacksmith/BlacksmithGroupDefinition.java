/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_2960
 *  net.minecraft.class_7923
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.blacksmith;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record BlacksmithGroupDefinition(class_2960 kingdomId, String id, String displayName, class_2960 iconItemId, int order) {
    public static final Codec<BlacksmithGroupDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)class_2960.field_25139.fieldOf("kingdom").forGetter(BlacksmithGroupDefinition::kingdomId), (App)Codec.STRING.fieldOf("id").forGetter(BlacksmithGroupDefinition::id), (App)Codec.STRING.fieldOf("display_name").forGetter(BlacksmithGroupDefinition::displayName), (App)class_2960.field_25139.fieldOf("icon").forGetter(BlacksmithGroupDefinition::iconItemId), (App)Codec.INT.optionalFieldOf("order", (Object)0).forGetter(BlacksmithGroupDefinition::order)).apply((Applicative)instance, BlacksmithGroupDefinition::new));
    public static final class_9139<class_9129, BlacksmithGroupDefinition> PACKET_CODEC = class_9139.method_56438((value, buf) -> {
        class_2960.field_48267.encode(buf, (Object)value.kingdomId);
        buf.method_10814(value.id);
        buf.method_10814(value.displayName);
        class_2960.field_48267.encode(buf, (Object)value.iconItemId);
        buf.method_10804(value.order);
    }, buf -> new BlacksmithGroupDefinition((class_2960)class_2960.field_48267.decode(buf), buf.method_19772(), buf.method_19772(), (class_2960)class_2960.field_48267.decode(buf), buf.method_10816()));

    public class_1799 createIconStack() {
        class_1792 item = (class_1792)class_7923.field_41178.method_10223(this.iconItemId);
        return item == null ? class_1799.field_8037 : new class_1799((class_1935)item);
    }
}

