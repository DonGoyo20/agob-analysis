/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.gui.character;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.client.gui.character.CharacterMenuSection;
import trep.got.client.gui.character.KingdomCharacterMenuSection;
import trep.got.client.gui.character.ReligionCharacterMenuSection;
import trep.got.client.gui.character.SkillsCharacterMenuSection;

@Environment(value=EnvType.CLIENT)
public final class CharacterMenuSectionRegistry {
    private static final List<CharacterMenuSection> SECTIONS = new ArrayList<CharacterMenuSection>();

    private CharacterMenuSectionRegistry() {
    }

    public static void register(CharacterMenuSection section) {
        SECTIONS.add(section);
    }

    public static List<CharacterMenuSection> getSections() {
        return List.copyOf(SECTIONS);
    }

    static {
        CharacterMenuSectionRegistry.register(new KingdomCharacterMenuSection());
        CharacterMenuSectionRegistry.register(new ReligionCharacterMenuSection());
        CharacterMenuSectionRegistry.register(new SkillsCharacterMenuSection());
    }
}

