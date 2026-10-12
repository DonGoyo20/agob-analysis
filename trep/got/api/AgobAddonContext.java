/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.api;

import java.util.Set;
import net.minecraft.class_2960;
import trep.got.api.kingdom.KingdomCategory;
import trep.got.character.CharacterSelectionOption;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.Religion;
import trep.got.quest.QuestTemplate;
import trep.got.religion.ReligionSelectionOption;

public interface AgobAddonContext {
    public void registerTroopResourceDirectory(String var1);

    public void registerQuestTemplateResource(String var1);

    public void registerQuestTemplate(QuestTemplate var1);

    public void registerKingdom(Kingdom var1, Set<KingdomCategory> var2);

    public Religion registerReligion(String var1, String var2);

    public void registerReligionOption(ReligionSelectionOption var1);

    public void registerCharacterOption(CharacterSelectionOption var1);

    public void registerBlacksmithKingdomTab(class_2960 var1);

    default public class_2960 id(String namespace, String path) {
        return class_2960.method_60655((String)namespace, (String)path);
    }
}

