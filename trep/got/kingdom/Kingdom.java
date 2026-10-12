/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.kingdom;

import net.minecraft.class_2960;
import trep.got.kingdom.Culture;
import trep.got.kingdom.Religion;

public class Kingdom {
    private final String name;
    private final class_2960 id;
    private final Religion religion;
    private final Culture culture;

    public Kingdom(String name, class_2960 id, Religion religion, Culture culture) {
        this.name = name;
        this.id = id;
        this.religion = religion;
        this.culture = culture;
    }

    public String getName() {
        return this.name;
    }

    public class_2960 getId() {
        return this.id;
    }

    public Religion getReligion() {
        return this.religion;
    }

    public Culture getCulture() {
        return this.culture;
    }
}

