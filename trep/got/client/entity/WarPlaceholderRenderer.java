/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1068
 *  net.minecraft.class_1921
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_5602
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_583
 *  net.minecraft.class_591
 *  net.minecraft.class_8685
 *  net.minecraft.class_922
 */
package trep.got.client.entity;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1068;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_591;
import net.minecraft.class_8685;
import net.minecraft.class_922;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.entity.WarPlaceholderEntity;

@Environment(value=EnvType.CLIENT)
public final class WarPlaceholderRenderer
extends class_922<WarPlaceholderEntity, class_591<WarPlaceholderEntity>> {
    private static final class_2960 TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/war_placeholder.png");
    private static final class_2960 INVADE_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/war_placeholder_invade.png");
    private static final class_2960 RAID_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/npc/war_placeholder_raid.png");
    private final class_8685 fallbackSkin;

    public WarPlaceholderRenderer(class_5617.class_5618 context) {
        super(context, (class_583)new class_591(context.method_32167(class_5602.field_27577), false), 0.0f);
        class_310 client = class_310.method_1551();
        this.fallbackSkin = client.field_1724 != null ? client.field_1724.method_52814() : class_1068.method_4648((UUID)UUID.nameUUIDFromBytes("war_placeholder".getBytes(StandardCharsets.UTF_8)));
    }

    public class_2960 getTexture(WarPlaceholderEntity entity) {
        class_2960 texture = switch (entity.getPreviewMode()) {
            default -> throw new MatchException(null, null);
            case WarPlaceholderEntity.PreviewMode.INVADE -> INVADE_TEXTURE;
            case WarPlaceholderEntity.PreviewMode.RAID -> RAID_TEXTURE;
            case WarPlaceholderEntity.PreviewMode.NORMAL -> TEXTURE;
        };
        return CustomPlayerSkinResolver.resolve(class_310.method_1551(), texture, false, this.fallbackSkin, "war_placeholder").comp_1626();
    }

    protected class_1921 getRenderLayer(WarPlaceholderEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return class_1921.method_23580((class_2960)this.getTexture(entity));
    }

    protected boolean hasLabel(WarPlaceholderEntity entity) {
        return false;
    }
}

