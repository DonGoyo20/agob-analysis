/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1959
 *  net.minecraft.class_6880
 */
package trep.got.castle;

import java.util.Set;
import net.minecraft.class_1959;
import net.minecraft.class_6880;
import trep.got.castle.CastleDefinition;
import trep.got.castle.CastleTemplateKey;
import trep.got.castle.CastleTier;
import trep.got.castle.CastleWoodType;

public final class CastleTemplateResolver {
    private CastleTemplateResolver() {
    }

    public static CastleTemplateKey resolve(CastleDefinition definition, class_6880<class_1959> biome) {
        if (definition.tier() == CastleTier.LORDSHIP || definition.tier() == CastleTier.CAPITAL) {
            return new CastleTemplateKey(definition.tier(), CastleWoodType.OAK);
        }
        CastleWoodType preferred = CastleTemplateResolver.suggestedWoodType(biome);
        Set<CastleWoodType> allowed = definition.preferredWoodTypes();
        if (allowed.isEmpty() || allowed.contains((Object)preferred)) {
            return new CastleTemplateKey(CastleTier.SETTLEMENT, preferred);
        }
        CastleWoodType fallback = allowed.iterator().next();
        return new CastleTemplateKey(CastleTier.SETTLEMENT, fallback);
    }

    private static CastleWoodType suggestedWoodType(class_6880<class_1959> biome) {
        String path = biome.method_40230().map(key -> key.method_29177().method_12832()).orElse("");
        if (path.contains("north") || path.contains("winter") || path.contains("stormlands") || path.contains("iron_islands")) {
            return CastleWoodType.SPRUCE;
        }
        if (path.contains("vale") || path.contains("braavos")) {
            return CastleWoodType.BIRCH;
        }
        if (path.contains("dorne") || path.contains("slavers_bay") || path.contains("step_stones") || path.contains("volantis")) {
            return CastleWoodType.ACACIA;
        }
        if (path.contains("reach")) {
            return CastleWoodType.DARK_OAK;
        }
        return CastleWoodType.OAK;
    }
}

