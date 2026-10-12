/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 */
package trep.got.client.gui.character;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public record CharacterMenuRenderState(class_310 client, class_746 player) {
    public static CharacterMenuRenderState create(class_310 client) {
        class_746 player = client.field_1724;
        if (player == null) {
            return new CharacterMenuRenderState(client, null);
        }
        return new CharacterMenuRenderState(client, player);
    }

    public boolean hasPlayer() {
        return this.player != null;
    }
}

