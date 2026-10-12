/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record ModAdvancementPayload(String typeId, String title, String description, String iconItemId, int titleColor, int descriptionColor, String topTextureId, String bottomTextureId) implements class_8710
{
    public static final class_8710.class_9154<ModAdvancementPayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"mod_advancement"));
    public static final class_9139<class_9129, ModAdvancementPayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10814(value.typeId);
        buf.method_10814(value.title);
        buf.method_10814(value.description);
        buf.method_10814(value.iconItemId);
        buf.method_53002(value.titleColor);
        buf.method_53002(value.descriptionColor);
        buf.method_10814(value.topTextureId == null ? "" : value.topTextureId);
        buf.method_10814(value.bottomTextureId == null ? "" : value.bottomTextureId);
    }, buf -> new ModAdvancementPayload(buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.readInt(), buf.readInt(), buf.method_19772(), buf.method_19772()));

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}

