/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 */
package trep.got.client.character;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import trep.got.character.CharacterSelectionOption;
import trep.got.registry.GotCharacterOptions;

@Environment(value=EnvType.CLIENT)
public final class CharacterOptionLoader {
    private CharacterOptionLoader() {
    }

    public static List<CharacterSelectionOption> load() {
        return GotCharacterOptions.getPrimaryOptions();
    }

    public static List<CharacterSelectionOption> loadForKingdom(class_2960 kingdomId) {
        return GotCharacterOptions.getByKingdom(kingdomId);
    }
}

