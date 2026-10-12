/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 */
package trep.got.client.religion;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;

@Environment(value=EnvType.CLIENT)
public final class ReligionOptionLoader {
    private ReligionOptionLoader() {
    }

    public static List<ReligionSelectionOption> load() {
        return GotReligionOptions.getAll();
    }
}

