/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 */
package trep.got.client.gui.character;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_327;
import net.minecraft.class_332;
import trep.got.client.gui.character.CharacterMenuRenderState;

@Environment(value=EnvType.CLIENT)
public interface CharacterMenuSection {
    public int getHeight(class_327 var1, CharacterMenuRenderState var2, int var3);

    public void render(class_332 var1, class_327 var2, CharacterMenuRenderState var3, int var4, int var5, int var6, int var7, int var8);
}

