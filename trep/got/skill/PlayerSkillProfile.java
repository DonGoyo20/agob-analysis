/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 */
package trep.got.skill;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import trep.got.skill.SkillAttributeState;
import trep.got.skill.SkillAttributeType;

public final class PlayerSkillProfile {
    private static final String BLACKSMITH_RECIPES_KEY = "crafted_blacksmith_recipes";
    private static final String BLACKSMITH_UNLOCKED_KINGDOMS_KEY = "unlocked_blacksmith_kingdom_ids";
    private static final String BLACKSMITH_TALKED_KINGDOMS_KEY = "talked_blacksmith_kingdom_ids";
    private static final String CHARISMA_INTERACTION_COOLDOWNS_KEY = "charisma_interaction_cooldowns";
    private static final int MAX_CHARISMA_COOLDOWNS = 256;
    private final EnumMap<SkillAttributeType, SkillAttributeState> attributes = new EnumMap(SkillAttributeType.class);
    private final Set<String> craftedBlacksmithRecipes = new LinkedHashSet<String>();
    private final Set<String> unlockedBlacksmithKingdomIds = new LinkedHashSet<String>();
    private final Set<String> talkedBlacksmithKingdomIds = new LinkedHashSet<String>();
    private final LinkedHashMap<UUID, Long> charismaInteractionCooldowns = new LinkedHashMap();

    public PlayerSkillProfile() {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            this.attributes.put(type, new SkillAttributeState());
        }
    }

    public SkillAttributeState get(SkillAttributeType type) {
        return this.attributes.get((Object)type);
    }

    public Map<SkillAttributeType, SkillAttributeState> asMap() {
        return Map.copyOf(this.attributes);
    }

    public PlayerSkillProfile copy() {
        PlayerSkillProfile copy = new PlayerSkillProfile();
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            copy.attributes.put(type, this.get(type).copy());
        }
        copy.craftedBlacksmithRecipes.addAll(this.craftedBlacksmithRecipes);
        copy.unlockedBlacksmithKingdomIds.addAll(this.unlockedBlacksmithKingdomIds);
        copy.talkedBlacksmithKingdomIds.addAll(this.talkedBlacksmithKingdomIds);
        copy.charismaInteractionCooldowns.putAll(this.charismaInteractionCooldowns);
        return copy;
    }

    public void copyFrom(PlayerSkillProfile other) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            this.attributes.put(type, other.get(type).copy());
        }
        this.craftedBlacksmithRecipes.clear();
        this.craftedBlacksmithRecipes.addAll(other.craftedBlacksmithRecipes);
        this.unlockedBlacksmithKingdomIds.clear();
        this.unlockedBlacksmithKingdomIds.addAll(other.unlockedBlacksmithKingdomIds);
        this.talkedBlacksmithKingdomIds.clear();
        this.talkedBlacksmithKingdomIds.addAll(other.talkedBlacksmithKingdomIds);
        this.charismaInteractionCooldowns.clear();
        this.charismaInteractionCooldowns.putAll(other.charismaInteractionCooldowns);
    }

    public void applyStartingPoints(Map<SkillAttributeType, Integer> startingPoints) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            SkillAttributeState state = this.get(type);
            state.setPoints(startingPoints.getOrDefault((Object)type, 0));
            state.setLevel(0);
            state.setExperience(0);
        }
        this.craftedBlacksmithRecipes.clear();
        this.unlockedBlacksmithKingdomIds.clear();
        this.talkedBlacksmithKingdomIds.clear();
        this.charismaInteractionCooldowns.clear();
    }

    public int getMaxPoints(SkillAttributeType type) {
        return this.get(type).getMaxPoints();
    }

    public int getMaxPointsBonus(SkillAttributeType type) {
        return this.get(type).getMaxPointsBonus();
    }

    public Map<SkillAttributeType, Integer> getMaxPointBonuses() {
        EnumMap<SkillAttributeType, Integer> bonuses = new EnumMap<SkillAttributeType, Integer>(SkillAttributeType.class);
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            bonuses.put(type, this.get(type).getMaxPointsBonus());
        }
        return Map.copyOf(bonuses);
    }

    public boolean hasAnyMaxPointBonuses() {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            if (this.get(type).getMaxPointsBonus() == 0) continue;
            return true;
        }
        return false;
    }

    public void addMaxPointBonuses(Map<SkillAttributeType, Integer> bonuses) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            int bonus = bonuses.getOrDefault((Object)type, 0);
            if (bonus == 0) continue;
            this.get(type).addMaxPointsBonus(bonus);
        }
    }

    public void setMaxPointBonuses(Map<SkillAttributeType, Integer> bonuses) {
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            this.get(type).setMaxPointsBonus(bonuses.getOrDefault((Object)type, 0));
        }
    }

    public boolean markBlacksmithRecipeCrafted(class_2960 recipeId) {
        if (recipeId == null) {
            return false;
        }
        return this.craftedBlacksmithRecipes.add(recipeId.toString());
    }

    public Set<String> getUnlockedBlacksmithKingdomIds() {
        return Set.copyOf(this.unlockedBlacksmithKingdomIds);
    }

    public boolean hasUnlockedBlacksmithKingdom(class_2960 kingdomId) {
        return kingdomId != null && this.unlockedBlacksmithKingdomIds.contains(kingdomId.toString());
    }

    public boolean unlockBlacksmithKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return false;
        }
        return this.unlockedBlacksmithKingdomIds.add(kingdomId.toString());
    }

    public Set<String> getTalkedBlacksmithKingdomIds() {
        return Set.copyOf(this.talkedBlacksmithKingdomIds);
    }

    public boolean hasTalkedToBlacksmithKingdom(class_2960 kingdomId) {
        return kingdomId != null && this.talkedBlacksmithKingdomIds.contains(kingdomId.toString());
    }

    public boolean markTalkedToBlacksmithKingdom(class_2960 kingdomId) {
        if (kingdomId == null) {
            return false;
        }
        return this.talkedBlacksmithKingdomIds.add(kingdomId.toString());
    }

    public boolean canTriggerCharismaInteraction(UUID npcUuid, long worldTime) {
        if (npcUuid == null) {
            return false;
        }
        Long cooldownEnd = this.charismaInteractionCooldowns.get(npcUuid);
        return cooldownEnd == null || cooldownEnd <= worldTime;
    }

    public void setCharismaInteractionCooldown(UUID npcUuid, long worldTime, long cooldownTicks) {
        if (npcUuid == null) {
            return;
        }
        this.charismaInteractionCooldowns.put(npcUuid, worldTime + Math.max(0L, cooldownTicks));
        while (this.charismaInteractionCooldowns.size() > 256) {
            this.charismaInteractionCooldowns.remove(this.charismaInteractionCooldowns.firstEntry().getKey());
        }
    }

    public class_2487 toNbt() {
        class_2487 root = new class_2487();
        for (SkillAttributeType skillAttributeType : SkillAttributeType.ALL) {
            root.method_10566(skillAttributeType.getId(), (class_2520)this.get(skillAttributeType).toNbt());
        }
        class_2499 craftedRecipes = new class_2499();
        for (String string : this.craftedBlacksmithRecipes) {
            craftedRecipes.add((Object)class_2519.method_23256((String)string));
        }
        root.method_10566(BLACKSMITH_RECIPES_KEY, (class_2520)craftedRecipes);
        class_2499 class_24992 = new class_2499();
        for (String string : this.unlockedBlacksmithKingdomIds) {
            class_24992.add((Object)class_2519.method_23256((String)string));
        }
        root.method_10566(BLACKSMITH_UNLOCKED_KINGDOMS_KEY, (class_2520)class_24992);
        class_2499 class_24993 = new class_2499();
        for (String kingdomId : this.talkedBlacksmithKingdomIds) {
            class_24993.add((Object)class_2519.method_23256((String)kingdomId));
        }
        root.method_10566(BLACKSMITH_TALKED_KINGDOMS_KEY, (class_2520)class_24993);
        class_2499 class_24994 = new class_2499();
        for (Map.Entry<UUID, Long> entry : this.charismaInteractionCooldowns.entrySet()) {
            class_2487 cooldown = new class_2487();
            cooldown.method_25927("npc_uuid", entry.getKey());
            cooldown.method_10544("cooldown_end", entry.getValue().longValue());
            class_24994.add((Object)cooldown);
        }
        root.method_10566(CHARISMA_INTERACTION_COOLDOWNS_KEY, (class_2520)class_24994);
        return root;
    }

    public static PlayerSkillProfile fromNbt(class_2487 root) {
        PlayerSkillProfile profile = new PlayerSkillProfile();
        for (SkillAttributeType type : SkillAttributeType.ALL) {
            if (!root.method_10545(type.getId())) continue;
            profile.attributes.put(type, SkillAttributeState.fromNbt(root.method_10562(type.getId())));
        }
        class_2499 craftedRecipes = root.method_10554(BLACKSMITH_RECIPES_KEY, 8);
        for (int index = 0; index < craftedRecipes.size(); ++index) {
            String recipeId = craftedRecipes.method_10608(index);
            if (recipeId.isBlank()) continue;
            profile.craftedBlacksmithRecipes.add(recipeId);
        }
        class_2499 unlockedKingdoms = root.method_10554(BLACKSMITH_UNLOCKED_KINGDOMS_KEY, 8);
        for (int index = 0; index < unlockedKingdoms.size(); ++index) {
            String kingdomId = unlockedKingdoms.method_10608(index);
            if (kingdomId.isBlank()) continue;
            profile.unlockedBlacksmithKingdomIds.add(kingdomId);
        }
        class_2499 talkedKingdoms = root.method_10554(BLACKSMITH_TALKED_KINGDOMS_KEY, 8);
        for (int index = 0; index < talkedKingdoms.size(); ++index) {
            String kingdomId = talkedKingdoms.method_10608(index);
            if (kingdomId.isBlank()) continue;
            profile.talkedBlacksmithKingdomIds.add(kingdomId);
        }
        class_2499 charismaCooldowns = root.method_10554(CHARISMA_INTERACTION_COOLDOWNS_KEY, 10);
        for (int index = 0; index < charismaCooldowns.size(); ++index) {
            class_2487 cooldown = charismaCooldowns.method_10602(index);
            if (!cooldown.method_25928("npc_uuid")) continue;
            profile.charismaInteractionCooldowns.put(cooldown.method_25926("npc_uuid"), cooldown.method_10537("cooldown_end"));
        }
        return profile;
    }
}

