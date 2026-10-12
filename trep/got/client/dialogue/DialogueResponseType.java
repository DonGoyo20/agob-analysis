/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.dialogue;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(value=EnvType.CLIENT)
public enum DialogueResponseType {
    NEGATIVE("negative"),
    NEUTRAL("neutral"),
    POSITIVE("positive");

    private final String id;

    private DialogueResponseType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static DialogueResponseType byId(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        for (DialogueResponseType type : DialogueResponseType.values()) {
            if (!type.id.equals(id)) continue;
            return type;
        }
        return null;
    }
}

