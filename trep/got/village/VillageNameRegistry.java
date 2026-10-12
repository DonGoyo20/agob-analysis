/*
 * Decompiled with CFR 0.152.
 */
package trep.got.village;

import java.util.Map;

public final class VillageNameRegistry {
    private static final Map<String, String> CANONICAL_NAMES = Map.ofEntries(Map.entry("andalos", "Hugor's Hill"), Map.entry("ashemark", "Ashemark Market"), Map.entry("ashford", "Ashford Meadow"), Map.entry("bandallon", "Manderford"), Map.entry("barrowton", "Barrowton"), Map.entry("bhorash", "Bhorash Bazaar"), Map.entry("bitterbridge", "Bridgefair"), Map.entry("blackcrown", "Blackcrown Quay"), Map.entry("blackhaven", "Havenmarket"), Map.entry("blackwater_cross", "Blackwater Cross"), Map.entry("blackwater_keep", "Blackwater Market"), Map.entry("bloodstone", "Bloodstone Anchorage"), Map.entry("bloody_gate", "Gatehold"), Map.entry("braavos", "Ragman's Harbor"), Map.entry("brightwater_keep", "Brightwater Town"), Map.entry("bronzegate", "Bronzegate Hamlet"), Map.entry("casterly_rock", "Casterly Village"), Map.entry("castle_black", "Mole's Town"), Map.entry("clegane_s_keep", "Clegane Kennels"), Map.entry("coldwater", "Coldwater Burn"), Map.entry("cornfield", "Cornfield"), Map.entry("crakehall", "Crakehall Market"), Map.entry("deepwood_motte", "Deepwood Village"), Map.entry("dragonroad", "Dragonroad Market"), Map.entry("dragonstone", "Dragonmont Village"), Map.entry("duskendale", "Duskendale"), Map.entry("eelwood", "Eelwood"), Map.entry("evenfall_hall", "Morlane"), Map.entry("eyrie", "Gates of the Moon"), Map.entry("godsgrace", "Greenblood Ferry"), Map.entry("goldengrove", "Goldengrove Orchard"), Map.entry("grey_gallows", "Gallows Haven"), Map.entry("griffin_s_roost", "Griffin's Roost Town"), Map.entry("gulltown", "Gulltown"), Map.entry("harlaw", "Harlaw Hall Town"), Map.entry("harrenhall", "Harrentown"), Map.entry("haystack_hall", "Haystack"), Map.entry("hellholt", "Hellholt Wells"), Map.entry("highgarden", "Garden Town"), Map.entry("honeyholt", "Honeywine Hamlet"), Map.entry("hornhill", "Horn Hill Village"), Map.entry("karhold", "Karhold Village"), Map.entry("kayce", "Kayce Harbor"), Map.entry("king_s_landing", "Flea Bottom"), Map.entry("lannisport", "Lannis Town"), Map.entry("last_hearth", "Last River"), Map.entry("longbow_hall", "Longbow Village"), Map.entry("lorath", "Maze Harbor"), Map.entry("lord_harroway_s_town", "Harroway's Town"), Map.entry("maidenpool", "Maidenpool"), Map.entry("mistwood", "Mistwood Hamlet"), Map.entry("moat_calin", "Causeway Camp"), Map.entry("myr", "Myrish Glassmarket"), Map.entry("new_ghis", "Ghiscar Wharf"), Map.entry("new_valyria", "Dragonstone Landing"), Map.entry("old_anchor", "Old Anchor"), Map.entry("old_castle", "Oldcastle Village"), Map.entry("oldstones", "Oldstones Ferry"), Map.entry("oldtown", "Oldtown"), Map.entry("pentos", "Prince's Harbor"), Map.entry("pinkmaiden", "Pinkmaiden Town"), Map.entry("pyke", "Lordsport"), Map.entry("red_fort", "Old Landing"), Map.entry("riverrun", "Rivertown"), Map.entry("rook_s_rest", "Rookery"), Map.entry("runestone", "Runeport"), Map.entry("salthook", "Salthook"), Map.entry("sandstone", "Sandstone Wells"), Map.entry("skyreach", "Skyreach Pass"), Map.entry("snakewoods", "Snakewood"), Map.entry("starfall", "Starfall Harbor"), Map.entry("storm_s_end", "Stormtown"), Map.entry("strongsong", "Strong Song"), Map.entry("summer_hall", "Summerhall Town"), Map.entry("sunhouse", "Sunhouse Market"), Map.entry("sunspear", "Shadow City"), Map.entry("the_crag", "Cragshore"), Map.entry("the_dreadfort", "Weepwater"), Map.entry("the_twins", "Crossing Market"), Map.entry("three_towers", "Three Towers Quay"), Map.entry("torrhen_s_square", "Torrhen's Square"), Map.entry("torturer_s_deep", "Deepwater Camp"), Map.entry("tumbleton", "Tumbleton"), Map.entry("tyrosh", "Bleeding Tower Market"), Map.entry("vaith", "Vaith Wells"), Map.entry("white_harbour", "Seal Rock"), Map.entry("winterfell", "Winter Town"), Map.entry("yronwood", "Yronwood Town"), Map.entry("crasters_keep", "Crasters Outpost"));

    private VillageNameRegistry() {
    }

    public static String resolve(String castleId, String castleDisplayName) {
        String canonical = CANONICAL_NAMES.get(castleId);
        if (canonical != null && !canonical.isBlank()) {
            return canonical;
        }
        if (castleDisplayName == null || castleDisplayName.isBlank()) {
            return "Village";
        }
        return castleDisplayName + " Village";
    }
}

