/*
 * Decompiled with CFR 0.152.
 */
package trep.got.kingdom;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Religion {
    private static final Map<String, Religion> BY_ID = new LinkedHashMap<String, Religion>();
    public static final Religion OLD_GODS_OF_THE_FOREST = Religion.registerBuiltin("the_old_gods", "The Old Gods");
    public static final Religion FAITH_OF_THE_SEVEN = Religion.registerBuiltin("faith_of_the_seven", "Faith of the Seven");
    public static final Religion DROWNED_GOD = Religion.registerBuiltin("faith_of_the_drowned", "Faith of the Drowned");
    public static final Religion LORD_OF_LIGHT = Religion.registerBuiltin("rhllor", "R'hllor");
    public static final Religion GREAT_STALLION = Religion.registerBuiltin("great_stallion", "Great Stallion");
    private final String id;
    private final String displayName;

    private Religion(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static Religion register(String id, String displayName) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Religion id cannot be blank");
        }
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("Religion display name cannot be blank");
        }
        Religion existing = BY_ID.get(id);
        if (existing != null) {
            if (!existing.displayName.equals(displayName)) {
                throw new IllegalStateException("Religion " + id + " is already registered with a different display name");
            }
            return existing;
        }
        Religion religion = new Religion(id, displayName);
        BY_ID.put(id, religion);
        return religion;
    }

    public static Religion byId(String id) {
        return id == null || id.isBlank() ? null : BY_ID.get(id);
    }

    public static Collection<Religion> getAll() {
        return List.copyOf(BY_ID.values());
    }

    private static Religion registerBuiltin(String id, String displayName) {
        return Religion.register(id, displayName);
    }
}

