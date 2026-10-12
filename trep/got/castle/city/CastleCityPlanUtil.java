/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5819
 */
package trep.got.castle.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.class_5819;
import trep.got.castle.city.CastleStructureResidentPlan;
import trep.got.entity.KingdomNpcRole;

public final class CastleCityPlanUtil {
    private CastleCityPlanUtil() {
    }

    public static List<String> pickDistinct(List<String> paths, int count, class_5819 random) {
        if (count <= 0 || paths.isEmpty()) {
            return List.of();
        }
        ArrayList<String> shuffled = new ArrayList<String>(paths);
        Collections.shuffle(shuffled, new Random(random.method_43055()));
        return List.copyOf(shuffled.subList(0, Math.min(count, shuffled.size())));
    }

    public static List<String> pickRepeating(List<String> paths, int count, class_5819 random) {
        if (count <= 0 || paths.isEmpty()) {
            return List.of();
        }
        ArrayList<String> shuffled = new ArrayList<String>(paths);
        Collections.shuffle(shuffled, new Random(random.method_43055()));
        ArrayList<String> results = new ArrayList<String>(count);
        int index = 0;
        for (int placed = 0; placed < count; ++placed) {
            results.add((String)shuffled.get(index));
            if (++index < shuffled.size()) continue;
            Collections.shuffle(shuffled, new Random(random.method_43055()));
            index = 0;
        }
        return List.copyOf(results);
    }

    public static List<CastleStructureResidentPlan> expand(String structurePath, KingdomNpcRole residentRole, int count, int poiRadius) {
        if (count <= 0 || structurePath == null || structurePath.isBlank()) {
            return List.of();
        }
        ArrayList<CastleStructureResidentPlan> results = new ArrayList<CastleStructureResidentPlan>(count);
        for (int index = 0; index < count; ++index) {
            results.add(new CastleStructureResidentPlan(structurePath, residentRole, poiRadius));
        }
        return List.copyOf(results);
    }

    public static List<CastleStructureResidentPlan> expand(List<String> structurePaths, KingdomNpcRole residentRole, int poiRadius) {
        if (structurePaths.isEmpty()) {
            return List.of();
        }
        ArrayList<CastleStructureResidentPlan> results = new ArrayList<CastleStructureResidentPlan>(structurePaths.size());
        for (String structurePath : structurePaths) {
            results.add(new CastleStructureResidentPlan(structurePath, residentRole, poiRadius));
        }
        return List.copyOf(results);
    }
}

