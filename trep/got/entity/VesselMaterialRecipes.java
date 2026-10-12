/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_9694
 */
package trep.got.entity;

import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_9694;
import trep.got.entity.VesselMaterialProfile;
import trep.got.registry.GotItems;

public final class VesselMaterialRecipes {
    private VesselMaterialRecipes() {
    }

    public static VesselMaterialProfile matchShip(class_9694 input) {
        if (input.method_59991() != 3 || input.method_59992() != 3) {
            return null;
        }
        VesselMaterialProfile.WoodFamily plankWood = VesselMaterialRecipes.matchWoodFromFence(input.method_59985(1, 0));
        VesselMaterialProfile.WoodFamily logWood = VesselMaterialRecipes.matchWoodFromStripped(input.method_59985(1, 2));
        VesselMaterialProfile.WoolFamily wool = VesselMaterialRecipes.matchWool(input.method_59985(2, 0));
        if (plankWood == null || logWood == null || wool == null) {
            return null;
        }
        return VesselMaterialRecipes.require(input, new Expected(1, 0, plankWood.fenceItem(), null), new Expected(2, 0, wool.block().method_8389(), null), new Expected(1, 1, plankWood.fenceItem(), null), new Expected(2, 1, wool.block().method_8389(), null), new Expected(0, 2, plankWood.plankItem(), null), new Expected(1, 2, logWood.strippedLogItem(), null), new Expected(2, 2, plankWood.plankItem(), null)) ? new VesselMaterialProfile(plankWood, logWood, wool) : null;
    }

    public static VesselMaterialProfile matchGalley(class_9694 input) {
        if (input.method_59991() != 3 || input.method_59992() != 3) {
            return null;
        }
        VesselMaterialProfile.WoodFamily logWood = VesselMaterialRecipes.matchWoodFromStripped(input.method_59985(1, 0));
        VesselMaterialProfile.WoodFamily plankWood = VesselMaterialRecipes.matchWoodFromPlank(input.method_59985(0, 2));
        VesselMaterialProfile.WoolFamily wool = VesselMaterialRecipes.matchWool(input.method_59985(2, 0));
        class_1799 prior = input.method_59985(1, 2);
        if (plankWood == null || logWood == null || wool == null || !prior.method_31574(GotItems.SHIP)) {
            return null;
        }
        VesselMaterialProfile priorProfile = VesselMaterialProfile.fromStack(prior);
        if (priorProfile.plankWoodFamily() != plankWood || priorProfile.logWoodFamily() != logWood || priorProfile.woolFamily() != wool) {
            return null;
        }
        return VesselMaterialRecipes.require(input, new Expected(1, 0, logWood.strippedLogItem(), null), new Expected(2, 0, wool.block().method_8389(), null), new Expected(1, 1, logWood.strippedLogItem(), null), new Expected(2, 1, wool.block().method_8389(), null), new Expected(0, 2, plankWood.plankItem(), null), new Expected(1, 2, GotItems.SHIP, priorProfile), new Expected(2, 2, plankWood.plankItem(), null)) ? new VesselMaterialProfile(plankWood, logWood, wool) : null;
    }

    public static VesselMaterialProfile matchLongship(class_9694 input) {
        if (input.method_59991() != 3 || input.method_59992() != 3) {
            return null;
        }
        VesselMaterialProfile.WoodFamily logWood = VesselMaterialRecipes.matchWoodFromLog(input.method_59985(1, 0));
        VesselMaterialProfile.WoolFamily wool = VesselMaterialRecipes.matchWool(input.method_59985(2, 0));
        VesselMaterialProfile.WoodFamily plankWood = VesselMaterialRecipes.matchWoodFromStackProfile(input.method_59985(1, 2));
        class_1799 prior = input.method_59985(1, 2);
        if (plankWood == null || logWood == null || wool == null || !prior.method_31574(GotItems.GALLEY)) {
            return null;
        }
        VesselMaterialProfile priorProfile = VesselMaterialProfile.fromStack(prior);
        if (priorProfile.plankWoodFamily() != plankWood || priorProfile.logWoodFamily() != logWood || priorProfile.woolFamily() != wool) {
            return null;
        }
        return VesselMaterialRecipes.require(input, new Expected(1, 0, logWood.logItem(), null), new Expected(2, 0, wool.block().method_8389(), null), new Expected(1, 1, logWood.logItem(), null), new Expected(2, 1, wool.block().method_8389(), null), new Expected(0, 2, logWood.strippedLogItem(), null), new Expected(1, 2, GotItems.GALLEY, priorProfile), new Expected(2, 2, logWood.strippedLogItem(), null)) ? new VesselMaterialProfile(plankWood, logWood, wool) : null;
    }

    private static boolean require(class_9694 input, Expected ... expected) {
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                class_1799 stack = input.method_59985(x, y);
                Expected match = null;
                for (Expected candidate : expected) {
                    if (candidate.x != x || candidate.y != y) continue;
                    match = candidate;
                    break;
                }
                if (match == null) {
                    if (stack.method_7960()) continue;
                    return false;
                }
                if (!stack.method_31574(match.item)) {
                    return false;
                }
                if (match.profile == null || VesselMaterialProfile.fromStack(stack).equals(match.profile)) continue;
                return false;
            }
        }
        return true;
    }

    private static VesselMaterialProfile.WoodFamily matchWoodFromFence(class_1799 stack) {
        for (VesselMaterialProfile.WoodFamily family : VesselMaterialProfile.WoodFamily.values()) {
            if (!stack.method_31574(family.fenceItem())) continue;
            return family;
        }
        return null;
    }

    private static VesselMaterialProfile.WoodFamily matchWoodFromPlank(class_1799 stack) {
        for (VesselMaterialProfile.WoodFamily family : VesselMaterialProfile.WoodFamily.values()) {
            if (!stack.method_31574(family.plankItem())) continue;
            return family;
        }
        return null;
    }

    private static VesselMaterialProfile.WoodFamily matchWoodFromStripped(class_1799 stack) {
        for (VesselMaterialProfile.WoodFamily family : VesselMaterialProfile.WoodFamily.values()) {
            if (!stack.method_31574(family.strippedLogItem())) continue;
            return family;
        }
        return null;
    }

    private static VesselMaterialProfile.WoodFamily matchWoodFromLog(class_1799 stack) {
        for (VesselMaterialProfile.WoodFamily family : VesselMaterialProfile.WoodFamily.values()) {
            if (!stack.method_31574(family.logItem())) continue;
            return family;
        }
        return null;
    }

    private static VesselMaterialProfile.WoolFamily matchWool(class_1799 stack) {
        for (VesselMaterialProfile.WoolFamily family : VesselMaterialProfile.WoolFamily.values()) {
            if (!stack.method_31574(family.block().method_8389())) continue;
            return family;
        }
        return null;
    }

    private static VesselMaterialProfile.WoodFamily matchWoodFromStackProfile(class_1799 stack) {
        if (stack.method_7960()) {
            return null;
        }
        return VesselMaterialProfile.fromStack(stack).plankWoodFamily();
    }

    private record Expected(int x, int y, class_1792 item, VesselMaterialProfile profile) {
    }
}

