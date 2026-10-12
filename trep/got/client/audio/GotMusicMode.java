/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2561
 */
package trep.got.client.audio;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;

@Environment(value=EnvType.CLIENT)
public enum GotMusicMode {
    MIXED("mixed", "options.agob.music_mode.mixed"),
    AGOB("agob", "options.agob.music_mode.agob"),
    VANILLA("vanilla", "options.agob.music_mode.vanilla");

    private final String id;
    private final String translationKey;

    private GotMusicMode(String id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    public String getId() {
        return this.id;
    }

    public class_2561 getDisplayText() {
        return class_2561.method_43471((String)this.translationKey);
    }

    public static GotMusicMode byId(String id) {
        for (GotMusicMode value : GotMusicMode.values()) {
            if (!value.id.equalsIgnoreCase(id)) continue;
            return value;
        }
        return MIXED;
    }
}

