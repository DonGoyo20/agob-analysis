/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2960;
import trep.got.api.internal.AgobAddonRegistry;
import trep.got.api.kingdom.KingdomCategory;
import trep.got.kingdom.Kingdom;
import trep.got.kingdom.essos.AstaporKingdom;
import trep.got.kingdom.essos.BlackfyreKingdom;
import trep.got.kingdom.essos.BraavosKingdom;
import trep.got.kingdom.essos.GoldenCompanyKingdom;
import trep.got.kingdom.essos.MeereenKingdom;
import trep.got.kingdom.essos.MyrKingdom;
import trep.got.kingdom.essos.PentosKingdom;
import trep.got.kingdom.essos.QarthKingdom;
import trep.got.kingdom.essos.TargaryenKingdom;
import trep.got.kingdom.essos.TyroshKingdom;
import trep.got.kingdom.essos.VolantisKingdom;
import trep.got.kingdom.essos.YunkaiKingdom;
import trep.got.kingdom.westeros.CrownlandsKingdom;
import trep.got.kingdom.westeros.DorneKingdom;
import trep.got.kingdom.westeros.IronIslandsKingdom;
import trep.got.kingdom.westeros.NightsWatchKingdom;
import trep.got.kingdom.westeros.NorthKingdom;
import trep.got.kingdom.westeros.ReachKingdom;
import trep.got.kingdom.westeros.RiverlandsKingdom;
import trep.got.kingdom.westeros.StormlandsKingdom;
import trep.got.kingdom.westeros.ValeKingdom;
import trep.got.kingdom.westeros.WesterlandsKingdom;
import trep.got.kingdom.westeros.WildlingKingdom;

public final class GotKingdoms {
    public static final Kingdom THE_NORTH = new NorthKingdom();
    public static final Kingdom THE_NIGHTS_WATCH = new NightsWatchKingdom();
    public static final Kingdom WILDLING = new WildlingKingdom();
    public static final Kingdom THE_VALE = new ValeKingdom();
    public static final Kingdom THE_RIVERLANDS = new RiverlandsKingdom();
    public static final Kingdom THE_WESTERLANDS = new WesterlandsKingdom();
    public static final Kingdom THE_IRON_ISLANDS = new IronIslandsKingdom();
    public static final Kingdom THE_REACH = new ReachKingdom();
    public static final Kingdom THE_STORMLANDS = new StormlandsKingdom();
    public static final Kingdom DORNE = new DorneKingdom();
    public static final Kingdom THE_CROWNLANDS = new CrownlandsKingdom();
    public static final Kingdom TARGARYEN = new TargaryenKingdom();
    public static final Kingdom BRAAVOS = new BraavosKingdom();
    public static final Kingdom PENTOS = new PentosKingdom();
    public static final Kingdom VOLANTIS = new VolantisKingdom();
    public static final Kingdom TYROSH = new TyroshKingdom();
    public static final Kingdom MYR = new MyrKingdom();
    public static final Kingdom BLACKFYRE = new BlackfyreKingdom();
    public static final Kingdom GOLDEN_COMPANY = new GoldenCompanyKingdom();
    public static final Kingdom MEEREEN = new MeereenKingdom();
    public static final Kingdom YUNKAI = new YunkaiKingdom();
    public static final Kingdom ASTAPOR = new AstaporKingdom();
    public static final Kingdom QARTH = new QarthKingdom();
    public static List<Kingdom> WESTEROSI_KINGDOMS = List.of();
    public static List<Kingdom> CASTLE_KINGDOMS = List.of();
    public static List<Kingdom> CASTLELESS_CONQUEROR_KINGDOMS = List.of();
    public static List<Kingdom> PASSIVE_KINGDOMS = List.of();
    public static List<Kingdom> CASTLELESS_ORIGIN_KINGDOMS = List.of();
    public static List<Kingdom> CHARACTER_OPTION_KINGDOMS = List.of();
    public static List<Kingdom> AI_WAR_KINGDOMS = List.of();
    private static List<Kingdom> allKingdoms = List.of();
    private static Map<class_2960, Kingdom> byId = Map.of();
    private static Map<class_2960, class_2960> dynamicParentById = Map.of();
    private static boolean initialized;

    private GotKingdoms() {
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        ArrayList<RegisteredKingdom> registrations = new ArrayList<RegisteredKingdom>();
        GotKingdoms.registerBuiltins(registrations);
        for (AgobAddonRegistry.RegisteredKingdom registered : AgobAddonRegistry.kingdoms()) {
            registrations.add(new RegisteredKingdom(registered.kingdom(), registered.categories()));
        }
        LinkedHashMap<class_2960, Kingdom> loadedById = new LinkedHashMap<class_2960, Kingdom>();
        ArrayList<Kingdom> westerosi = new ArrayList<Kingdom>();
        ArrayList<Kingdom> castle = new ArrayList<Kingdom>();
        ArrayList<Kingdom> castlelessConqueror = new ArrayList<Kingdom>();
        ArrayList<Kingdom> passive = new ArrayList<Kingdom>();
        ArrayList<Kingdom> castlelessOrigin = new ArrayList<Kingdom>();
        ArrayList<Kingdom> characterOption = new ArrayList<Kingdom>();
        ArrayList<Kingdom> aiWar = new ArrayList<Kingdom>();
        ArrayList<Kingdom> loadedAll = new ArrayList<Kingdom>();
        for (RegisteredKingdom registration : registrations) {
            Kingdom kingdom = registration.kingdom();
            Kingdom previous = loadedById.putIfAbsent(kingdom.getId(), kingdom);
            if (previous != null) {
                throw new IllegalStateException("Duplicate kingdom id: " + String.valueOf(kingdom.getId()));
            }
            loadedAll.add(kingdom);
            if (registration.categories().contains((Object)KingdomCategory.WESTEROSI)) {
                westerosi.add(kingdom);
            }
            if (registration.categories().contains((Object)KingdomCategory.CASTLE)) {
                castle.add(kingdom);
            }
            if (registration.categories().contains((Object)KingdomCategory.CASTLELESS_CONQUEROR)) {
                castlelessConqueror.add(kingdom);
            }
            if (registration.categories().contains((Object)KingdomCategory.PASSIVE)) {
                passive.add(kingdom);
            }
            if (registration.categories().contains((Object)KingdomCategory.CASTLELESS_ORIGIN)) {
                castlelessOrigin.add(kingdom);
            }
            if (registration.categories().contains((Object)KingdomCategory.CHARACTER_OPTION)) {
                characterOption.add(kingdom);
            }
            if (!registration.categories().contains((Object)KingdomCategory.AI_WAR)) continue;
            aiWar.add(kingdom);
        }
        WESTEROSI_KINGDOMS = List.copyOf(westerosi);
        CASTLE_KINGDOMS = List.copyOf(castle);
        CASTLELESS_CONQUEROR_KINGDOMS = List.copyOf(castlelessConqueror);
        PASSIVE_KINGDOMS = List.copyOf(passive);
        CASTLELESS_ORIGIN_KINGDOMS = List.copyOf(castlelessOrigin);
        CHARACTER_OPTION_KINGDOMS = List.copyOf(characterOption);
        AI_WAR_KINGDOMS = List.copyOf(aiWar);
        allKingdoms = List.copyOf(loadedAll);
        byId = Map.copyOf(loadedById);
    }

    public static Kingdom get(class_2960 id) {
        return GotKingdoms.BY_ID().get(id);
    }

    public static boolean hasKingdomId(class_2960 id) {
        return id != null && GotKingdoms.BY_ID().containsKey(id);
    }

    public static boolean isDynamicKingdom(class_2960 id) {
        return id != null && dynamicParentById.containsKey(id);
    }

    public static class_2960 parentKingdomId(class_2960 id) {
        return id == null ? null : dynamicParentById.get(id);
    }

    public static class_2960 cultureKingdomId(class_2960 id) {
        return id == null ? null : dynamicParentById.getOrDefault(id, id);
    }

    public static boolean registerDynamicKingdom(class_2960 id, String name, class_2960 parentKingdomId) {
        if (id == null || name == null || name.isBlank() || parentKingdomId == null || GotKingdoms.BY_ID().containsKey(id)) {
            return false;
        }
        Kingdom parent = GotKingdoms.get(parentKingdomId);
        if (parent == null) {
            return false;
        }
        Kingdom dynamic = new Kingdom(name, id, parent.getReligion(), parent.getCulture());
        LinkedHashMap<class_2960, Kingdom> nextById = new LinkedHashMap<class_2960, Kingdom>(GotKingdoms.BY_ID());
        nextById.put(id, dynamic);
        byId = Map.copyOf(nextById);
        ArrayList<Kingdom> nextAll = new ArrayList<Kingdom>(allKingdoms);
        nextAll.add(dynamic);
        allKingdoms = List.copyOf(nextAll);
        CASTLE_KINGDOMS = GotKingdoms.appendKingdom(CASTLE_KINGDOMS, dynamic);
        CHARACTER_OPTION_KINGDOMS = GotKingdoms.appendKingdom(CHARACTER_OPTION_KINGDOMS, dynamic);
        AI_WAR_KINGDOMS = GotKingdoms.appendKingdom(AI_WAR_KINGDOMS, dynamic);
        if (GotKingdoms.isWesterosiKingdom(parentKingdomId.toString())) {
            WESTEROSI_KINGDOMS = GotKingdoms.appendKingdom(WESTEROSI_KINGDOMS, dynamic);
        }
        LinkedHashMap<class_2960, class_2960> nextParents = new LinkedHashMap<class_2960, class_2960>(dynamicParentById);
        nextParents.put(id, parentKingdomId);
        dynamicParentById = Map.copyOf(nextParents);
        return true;
    }

    public static boolean unregisterDynamicKingdom(class_2960 id) {
        if (id == null || !dynamicParentById.containsKey(id)) {
            return false;
        }
        LinkedHashMap<class_2960, Kingdom> nextById = new LinkedHashMap<class_2960, Kingdom>(GotKingdoms.BY_ID());
        if (nextById.remove(id) == null) {
            return false;
        }
        byId = Map.copyOf(nextById);
        allKingdoms = GotKingdoms.removeKingdom(allKingdoms, id);
        CASTLE_KINGDOMS = GotKingdoms.removeKingdom(CASTLE_KINGDOMS, id);
        CHARACTER_OPTION_KINGDOMS = GotKingdoms.removeKingdom(CHARACTER_OPTION_KINGDOMS, id);
        AI_WAR_KINGDOMS = GotKingdoms.removeKingdom(AI_WAR_KINGDOMS, id);
        WESTEROSI_KINGDOMS = GotKingdoms.removeKingdom(WESTEROSI_KINGDOMS, id);
        LinkedHashMap<class_2960, class_2960> nextParents = new LinkedHashMap<class_2960, class_2960>(dynamicParentById);
        nextParents.remove(id);
        dynamicParentById = Map.copyOf(nextParents);
        return true;
    }

    public static List<Kingdom> getAll() {
        return allKingdoms;
    }

    public static boolean isWesterosiKingdom(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return false;
        }
        class_2960 cultureId = GotKingdoms.cultureKingdomId(id);
        return WESTEROSI_KINGDOMS.stream().anyMatch(kingdom -> kingdom.getId().equals((Object)id) || kingdom.getId().equals((Object)cultureId)) || THE_NIGHTS_WATCH.getId().equals((Object)id);
    }

    public static boolean isEssosKingdom(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        class_2960 id = class_2960.method_12829((String)kingdomId);
        return id != null && GotKingdoms.BY_ID().containsKey(id) && !GotKingdoms.isWesterosiKingdom(kingdomId);
    }

    public static boolean areKingdomsOnSameContinent(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || firstKingdomId.isBlank() || secondKingdomId == null || secondKingdomId.isBlank()) {
            return false;
        }
        boolean firstWesteros = GotKingdoms.isWesterosiKingdom(firstKingdomId);
        boolean secondWesteros = GotKingdoms.isWesterosiKingdom(secondKingdomId);
        if (firstWesteros || secondWesteros) {
            return firstWesteros == secondWesteros;
        }
        return GotKingdoms.isEssosKingdom(firstKingdomId) && GotKingdoms.isEssosKingdom(secondKingdomId);
    }

    public static boolean areKingdomsOnDifferentContinents(String firstKingdomId, String secondKingdomId) {
        if (firstKingdomId == null || firstKingdomId.isBlank() || secondKingdomId == null || secondKingdomId.isBlank()) {
            return false;
        }
        return !(GotKingdoms.areKingdomsOnSameContinent(firstKingdomId, secondKingdomId) || !GotKingdoms.isWesterosiKingdom(firstKingdomId) && !GotKingdoms.isEssosKingdom(firstKingdomId) || !GotKingdoms.isWesterosiKingdom(secondKingdomId) && !GotKingdoms.isEssosKingdom(secondKingdomId));
    }

    public static boolean isCastlelessConqueror(String kingdomId) {
        return GotKingdoms.isCastlelessOriginKingdom(kingdomId);
    }

    public static boolean isCastlelessOriginKingdom(String kingdomId) {
        if (kingdomId == null || kingdomId.isBlank()) {
            return false;
        }
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return false;
        }
        return CASTLELESS_ORIGIN_KINGDOMS.stream().anyMatch(kingdom -> kingdom.getId().equals((Object)id));
    }

    public static Kingdom byBiomePath(String biomePath) {
        if (biomePath == null || biomePath.isBlank()) {
            return null;
        }
        if (biomePath.contains("north") || biomePath.contains("winter")) {
            return THE_NORTH;
        }
        if (biomePath.contains("gift") || biomePath.contains("the_wall") || biomePath.contains("the-wall") || biomePath.contains("nights_watch")) {
            return THE_NIGHTS_WATCH;
        }
        if (biomePath.contains("always_winter") || biomePath.contains("wildling")) {
            return WILDLING;
        }
        if (biomePath.contains("vale")) {
            return THE_VALE;
        }
        if (biomePath.contains("riverlands")) {
            return THE_RIVERLANDS;
        }
        if (biomePath.contains("westerlands")) {
            return THE_WESTERLANDS;
        }
        if (biomePath.contains("iron_islands") || biomePath.contains("pyke")) {
            return THE_IRON_ISLANDS;
        }
        if (biomePath.contains("reach")) {
            return THE_REACH;
        }
        if (biomePath.contains("stormlands")) {
            return THE_STORMLANDS;
        }
        if (biomePath.contains("dorne")) {
            return DORNE;
        }
        if (biomePath.contains("crownlands") || biomePath.contains("dragonstone") || biomePath.contains("kings_landing")) {
            return THE_CROWNLANDS;
        }
        if (biomePath.contains("targaryen")) {
            return TARGARYEN;
        }
        if (biomePath.contains("braavos")) {
            return BRAAVOS;
        }
        if (biomePath.contains("pentos")) {
            return PENTOS;
        }
        if (biomePath.contains("volantis")) {
            return VOLANTIS;
        }
        if (biomePath.contains("tyrosh")) {
            return TYROSH;
        }
        if (biomePath.contains("myr")) {
            return MYR;
        }
        return null;
    }

    private static Map<class_2960, Kingdom> BY_ID() {
        if (!initialized) {
            GotKingdoms.initialize();
        }
        return byId;
    }

    private static void registerBuiltins(List<RegisteredKingdom> registrations) {
        registrations.add(new RegisteredKingdom(THE_NORTH, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_NIGHTS_WATCH, GotKingdoms.categories(KingdomCategory.CHARACTER_OPTION)));
        registrations.add(new RegisteredKingdom(WILDLING, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_VALE, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_RIVERLANDS, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_WESTERLANDS, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_IRON_ISLANDS, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_REACH, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_STORMLANDS, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(DORNE, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(THE_CROWNLANDS, GotKingdoms.categories(KingdomCategory.WESTEROSI, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(TARGARYEN, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(BRAAVOS, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(PENTOS, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(VOLANTIS, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(TYROSH, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(MYR, GotKingdoms.categories(KingdomCategory.CASTLE, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(BLACKFYRE, GotKingdoms.categories(KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(GOLDEN_COMPANY, GotKingdoms.categories(KingdomCategory.CASTLELESS_CONQUEROR, KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(MEEREEN, GotKingdoms.categories(KingdomCategory.PASSIVE, KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(YUNKAI, GotKingdoms.categories(KingdomCategory.PASSIVE, KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(ASTAPOR, GotKingdoms.categories(KingdomCategory.PASSIVE, KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
        registrations.add(new RegisteredKingdom(QARTH, GotKingdoms.categories(KingdomCategory.PASSIVE, KingdomCategory.CASTLELESS_ORIGIN, KingdomCategory.CHARACTER_OPTION, KingdomCategory.AI_WAR)));
    }

    private static EnumSet<KingdomCategory> categories(KingdomCategory ... categories) {
        if (categories.length == 0) {
            return EnumSet.noneOf(KingdomCategory.class);
        }
        EnumSet<KingdomCategory> values = EnumSet.of(categories[0]);
        for (int index = 1; index < categories.length; ++index) {
            values.add(categories[index]);
        }
        return values;
    }

    private static List<Kingdom> appendKingdom(List<Kingdom> kingdoms, Kingdom kingdom) {
        if (kingdoms.stream().anyMatch(existing -> existing.getId().equals((Object)kingdom.getId()))) {
            return kingdoms;
        }
        ArrayList<Kingdom> next = new ArrayList<Kingdom>(kingdoms);
        next.add(kingdom);
        return List.copyOf(next);
    }

    private static List<Kingdom> removeKingdom(List<Kingdom> kingdoms, class_2960 id) {
        if (kingdoms.stream().noneMatch(existing -> existing.getId().equals((Object)id))) {
            return kingdoms;
        }
        return kingdoms.stream().filter(existing -> !existing.getId().equals((Object)id)).toList();
    }

    private record RegisteredKingdom(Kingdom kingdom, EnumSet<KingdomCategory> categories) {
        RegisteredKingdom(Kingdom kingdom, Set<KingdomCategory> categories) {
            this(kingdom, categories.isEmpty() ? EnumSet.noneOf(KingdomCategory.class) : EnumSet.copyOf(categories));
        }
    }
}

