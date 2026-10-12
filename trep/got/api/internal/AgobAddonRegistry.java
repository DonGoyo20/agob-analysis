/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.class_2960
 */
package trep.got.api.internal;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2960;
import trep.got.api.AgobAddonContext;
import trep.got.api.AgobAddonEntrypoint;
import trep.got.api.kingdom.KingdomCategory;
import trep.got.character.CharacterSelectionOption;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.Religion;
import trep.got.quest.QuestTemplate;
import trep.got.religion.ReligionSelectionOption;

public final class AgobAddonRegistry {
    private static final List<String> TROOP_RESOURCE_DIRECTORIES = new ArrayList<String>();
    private static final List<String> QUEST_TEMPLATE_RESOURCES = new ArrayList<String>();
    private static final List<QuestTemplate> QUEST_TEMPLATES = new ArrayList<QuestTemplate>();
    private static final List<RegisteredKingdom> KINGDOMS = new ArrayList<RegisteredKingdom>();
    private static final List<ReligionSelectionOption> RELIGION_OPTIONS = new ArrayList<ReligionSelectionOption>();
    private static final List<CharacterSelectionOption> CHARACTER_OPTIONS = new ArrayList<CharacterSelectionOption>();
    private static final List<class_2960> BLACKSMITH_KINGDOM_TABS = new ArrayList<class_2960>();
    private static boolean bootstrapped;
    private static final AgobAddonContext CONTEXT;

    private AgobAddonRegistry() {
    }

    public static void bootstrap() {
        if (bootstrapped) {
            return;
        }
        bootstrapped = true;
        for (AgobAddonEntrypoint entrypoint : FabricLoader.getInstance().getEntrypoints("agob-addon", AgobAddonEntrypoint.class)) {
            entrypoint.register(CONTEXT);
        }
    }

    public static List<String> troopResourceDirectories() {
        return List.copyOf(TROOP_RESOURCE_DIRECTORIES);
    }

    public static List<String> questTemplateResources() {
        return List.copyOf(QUEST_TEMPLATE_RESOURCES);
    }

    public static List<QuestTemplate> questTemplates() {
        return List.copyOf(QUEST_TEMPLATES);
    }

    public static List<RegisteredKingdom> kingdoms() {
        return List.copyOf(KINGDOMS);
    }

    public static List<ReligionSelectionOption> religionOptions() {
        return List.copyOf(RELIGION_OPTIONS);
    }

    public static List<CharacterSelectionOption> characterOptions() {
        return List.copyOf(CHARACTER_OPTIONS);
    }

    public static List<class_2960> blacksmithKingdomTabs() {
        return List.copyOf(BLACKSMITH_KINGDOM_TABS);
    }

    private static String requireNonBlank(String value, String label) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(label + " cannot be blank");
        }
        return value;
    }

    static {
        CONTEXT = new AgobAddonContext(){

            @Override
            public void registerTroopResourceDirectory(String resourceDirectory) {
                TROOP_RESOURCE_DIRECTORIES.add(AgobAddonRegistry.requireNonBlank(resourceDirectory, "Troop resource directory"));
            }

            @Override
            public void registerQuestTemplateResource(String resourcePath) {
                QUEST_TEMPLATE_RESOURCES.add(AgobAddonRegistry.requireNonBlank(resourcePath, "Quest template resource path"));
            }

            @Override
            public void registerQuestTemplate(QuestTemplate template) {
                QUEST_TEMPLATES.add(Objects.requireNonNull(template, "template"));
            }

            @Override
            public void registerKingdom(Kingdom kingdom, Set<KingdomCategory> categories) {
                KINGDOMS.add(new RegisteredKingdom(Objects.requireNonNull(kingdom, "kingdom"), categories == null ? Set.of() : Set.copyOf(new LinkedHashSet<KingdomCategory>(categories))));
            }

            @Override
            public Religion registerReligion(String id, String displayName) {
                return Religion.register(id, displayName);
            }

            @Override
            public void registerReligionOption(ReligionSelectionOption option) {
                RELIGION_OPTIONS.add(Objects.requireNonNull(option, "option"));
            }

            @Override
            public void registerCharacterOption(CharacterSelectionOption option) {
                CHARACTER_OPTIONS.add(Objects.requireNonNull(option, "option"));
            }

            @Override
            public void registerBlacksmithKingdomTab(class_2960 kingdomId) {
                BLACKSMITH_KINGDOM_TABS.add(Objects.requireNonNull(kingdomId, "kingdomId"));
            }
        };
    }

    public record RegisteredKingdom(Kingdom kingdom, Set<KingdomCategory> categories) {
    }
}

