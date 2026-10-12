/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2960
 */
package trep.got.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.class_2960;
import trep.got.GotMod;
import trep.got.entity.CastleOwnerNamePool;
import trep.got.kingdom.Kingdom;

public final class GotCastleOwnerNames {
    private static final String RESOURCE_ROOT = "data/agob/npc/names/";
    private static final List<String> KINGDOM_IDS = List.of("agob:the_north", "agob:the_vale", "agob:the_riverlands", "agob:the_westerlands", "agob:iron_islands", "agob:the_reach", "agob:the_stormlands", "agob:dorne", "agob:the_crownlands", "agob:the_nights_watch", "agob:wildling", "agob:targaryen", "agob:braavos", "agob:pentos", "agob:volantis", "agob:tyrosh", "agob:myr", "agob:blackfyre", "agob:golden_company", "agob:meereen", "agob:yunkai", "agob:astapor", "agob:qarth");
    private static Map<String, CastleOwnerNamePool> byKingdomId = Map.of();

    private GotCastleOwnerNames() {
    }

    public static void initialize() {
        byKingdomId = Map.copyOf(GotCastleOwnerNames.load());
        GotMod.LOGGER.info("Loaded {} kingdom name pools", (Object)byKingdomId.size());
    }

    public static String createFullName(Kingdom kingdom, long seed) {
        if (kingdom == null) {
            return "Unknown Lord";
        }
        CastleOwnerNamePool pool = byKingdomId.get(kingdom.getId().toString());
        if (pool == null || pool.firstNames().isEmpty() || pool.commonerLastNames().isEmpty()) {
            return kingdom.getName() + " Lord";
        }
        Random random = new Random(seed ^ (long)kingdom.getId().toString().hashCode());
        return pool.firstNames().get(random.nextInt(pool.firstNames().size())) + " " + pool.commonerLastNames().get(random.nextInt(pool.commonerLastNames().size()));
    }

    public static String createFullName(Kingdom kingdom, String lastName, long seed) {
        String normalizedLastName;
        if (kingdom == null) {
            return "Unknown Lord";
        }
        String string = normalizedLastName = lastName == null ? "" : lastName.trim();
        if (normalizedLastName.isBlank()) {
            return GotCastleOwnerNames.createFullName(kingdom, seed);
        }
        CastleOwnerNamePool pool = byKingdomId.get(kingdom.getId().toString());
        if (pool == null || pool.firstNames().isEmpty()) {
            return kingdom.getName() + " " + normalizedLastName;
        }
        Random random = new Random(seed ^ (long)kingdom.getId().toString().hashCode());
        return pool.firstNames().get(random.nextInt(pool.firstNames().size())) + " " + normalizedLastName;
    }

    public static String createHouseLordName(Kingdom kingdom, String lastName, String uniqueKey, Set<String> usedNames) {
        String normalizedLastName;
        if (kingdom == null) {
            return "Unknown Lord";
        }
        String string = normalizedLastName = lastName == null ? "" : lastName.trim();
        if (normalizedLastName.isBlank()) {
            return GotCastleOwnerNames.createCommonerName(kingdom, uniqueKey, usedNames);
        }
        CastleOwnerNamePool pool = byKingdomId.get(kingdom.getId().toString());
        if (pool == null || pool.firstNames().isEmpty()) {
            return kingdom.getName() + " " + normalizedLastName;
        }
        LinkedHashSet<String> blocked = usedNames == null ? Set.of() : new LinkedHashSet<String>(usedNames);
        long baseSeed = uniqueKey == null ? 0L : (long)uniqueKey.hashCode();
        int firstSize = pool.firstNames().size();
        int firstStep = GotCastleOwnerNames.coprimeStep(firstSize, 7);
        int startFirst = Math.floorMod((int)baseSeed, firstSize);
        String fallback = pool.firstNames().get(startFirst) + " " + normalizedLastName;
        for (int attempt = 0; attempt < firstSize; ++attempt) {
            String candidate = pool.firstNames().get(Math.floorMod(startFirst + attempt * firstStep, firstSize)) + " " + normalizedLastName;
            if (!blocked.contains(candidate)) {
                return candidate;
            }
            fallback = candidate;
        }
        return fallback;
    }

    public static String createCommonerName(class_2960 kingdomId, String uniqueKey, Set<String> usedNames) {
        String key = kingdomId == null ? "" : kingdomId.toString();
        CastleOwnerNamePool pool = byKingdomId.get(key);
        if (pool == null) {
            return GotCastleOwnerNames.createFallbackCommonerName(uniqueKey, usedNames);
        }
        return GotCastleOwnerNames.createCommonerName(pool, uniqueKey, usedNames);
    }

    public static String createCommonerName(Kingdom kingdom, String uniqueKey, Set<String> usedNames) {
        if (kingdom == null) {
            return GotCastleOwnerNames.createFallbackCommonerName(uniqueKey, usedNames);
        }
        CastleOwnerNamePool pool = byKingdomId.get(kingdom.getId().toString());
        if (pool == null) {
            return GotCastleOwnerNames.createFallbackCommonerName(uniqueKey, usedNames);
        }
        return GotCastleOwnerNames.createCommonerName(pool, uniqueKey, usedNames);
    }

    public static String createFullNameFromAnyPool(long seed) {
        Random random;
        if (byKingdomId.isEmpty()) {
            return "Unknown Lord";
        }
        List entries = byKingdomId.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.naturalOrder())).toList();
        CastleOwnerNamePool pool = (CastleOwnerNamePool)entries.get((random = new Random(seed)).nextInt(entries.size())).getValue();
        if (pool.firstNames().isEmpty() || pool.commonerLastNames().isEmpty()) {
            return "Unknown Lord";
        }
        return pool.firstNames().get(random.nextInt(pool.firstNames().size())) + " " + pool.commonerLastNames().get(random.nextInt(pool.commonerLastNames().size()));
    }

    private static String createCommonerName(CastleOwnerNamePool pool, String uniqueKey, Set<String> usedNames) {
        if (pool.firstNames().isEmpty() || pool.commonerLastNames().isEmpty()) {
            return GotCastleOwnerNames.createFallbackCommonerName(uniqueKey, usedNames);
        }
        LinkedHashSet<String> blocked = usedNames == null ? Set.of() : new LinkedHashSet<String>(usedNames);
        long baseSeed = uniqueKey == null ? 0L : (long)uniqueKey.hashCode();
        int firstSize = pool.firstNames().size();
        List<String> surnamePool = GotCastleOwnerNames.shouldUseBastardSurname(baseSeed, pool) ? pool.bastardLastNames() : pool.commonerLastNames();
        int lastSize = surnamePool.size();
        int firstStep = GotCastleOwnerNames.coprimeStep(firstSize, 7);
        int lastStep = GotCastleOwnerNames.coprimeStep(lastSize, 11);
        int startFirst = Math.floorMod((int)baseSeed, firstSize);
        int startLast = Math.floorMod((int)(baseSeed * 31L), lastSize);
        int attempts = Math.max(1, firstSize * lastSize);
        String fallback = pool.firstNames().get(startFirst) + " " + surnamePool.get(startLast);
        for (int attempt = 0; attempt < attempts; ++attempt) {
            String candidate = pool.firstNames().get(Math.floorMod(startFirst + attempt * firstStep, firstSize)) + " " + surnamePool.get(Math.floorMod(startLast + attempt * lastStep, lastSize));
            if (!blocked.contains(candidate)) {
                return candidate;
            }
            fallback = candidate;
        }
        return fallback;
    }

    private static boolean shouldUseBastardSurname(long seed, CastleOwnerNamePool pool) {
        return !pool.bastardLastNames().isEmpty() && Math.floorMod((int)(seed ^ seed >>> 16), 100) < 50;
    }

    private static int coprimeStep(int size, int preferred) {
        if (size <= 1) {
            return 1;
        }
        int step = Math.max(1, preferred % size);
        while (GotCastleOwnerNames.gcd(step, size) != 1) {
            if (++step < size) continue;
            step = 1;
        }
        return step;
    }

    private static int gcd(int left, int right) {
        int a = Math.abs(left);
        int b = Math.abs(right);
        while (b != 0) {
            int next = a % b;
            a = b;
            b = next;
        }
        return a == 0 ? 1 : a;
    }

    private static String createFallbackCommonerName(String uniqueKey, Set<String> usedNames) {
        String candidate = GotCastleOwnerNames.createFullNameFromAnyPool(uniqueKey == null ? 0L : (long)uniqueKey.hashCode());
        if (usedNames == null || !usedNames.contains(candidate)) {
            return candidate;
        }
        return candidate + " " + Math.abs(uniqueKey == null ? 0 : uniqueKey.hashCode() % 97);
    }

    private static Map<String, CastleOwnerNamePool> load() {
        HashMap<String, CastleOwnerNamePool> loaded = new HashMap<String, CastleOwnerNamePool>();
        for (String kingdomId : KINGDOM_IDS) {
            class_2960 id = class_2960.method_12829((String)kingdomId);
            if (id == null) continue;
            String resourcePath = RESOURCE_ROOT + id.method_12832() + ".json";
            try {
                InputStream stream = GotCastleOwnerNames.class.getClassLoader().getResourceAsStream(resourcePath);
                try {
                    if (stream == null) {
                        throw new IllegalStateException("Missing kingdom name pool resource " + resourcePath);
                    }
                    JsonObject entry = JsonParser.parseReader((Reader)new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                    List<String> firstNames = GotCastleOwnerNames.parseStringArray(entry.getAsJsonArray("first_names"));
                    List<String> commonerLastNames = GotCastleOwnerNames.parseStringArray(entry.getAsJsonArray("commoner_last_names"));
                    List<String> bastardLastNames = GotCastleOwnerNames.parseStringArray(entry.getAsJsonArray("bastard_last_names"));
                    if (firstNames.isEmpty() || commonerLastNames.isEmpty() || bastardLastNames.isEmpty()) {
                        throw new IllegalStateException("Kingdom name pool cannot be empty for " + kingdomId);
                    }
                    loaded.put(kingdomId, new CastleOwnerNamePool(firstNames, commonerLastNames, bastardLastNames));
                }
                finally {
                    if (stream == null) continue;
                    stream.close();
                }
            }
            catch (IOException e) {
                throw new IllegalStateException("Failed to read kingdom name pool resource " + resourcePath, e);
            }
        }
        return loaded;
    }

    private static List<String> parseStringArray(JsonArray array) {
        ArrayList<String> values = new ArrayList<String>();
        for (int index = 0; index < array.size(); ++index) {
            values.add(array.get(index).getAsString());
        }
        return values;
    }
}

