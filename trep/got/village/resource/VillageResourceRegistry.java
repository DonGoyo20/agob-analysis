/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package trep.got.village.resource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.class_2960;
import trep.got.village.resource.VillageResource;

public final class VillageResourceRegistry {
    private final Map<class_2960, VillageResource> resources = new LinkedHashMap<class_2960, VillageResource>();

    public VillageResource register(VillageResource resource) {
        this.resources.put(resource.getId(), resource);
        return resource;
    }

    public VillageResource get(class_2960 id) {
        return this.resources.get(id);
    }

    public Collection<VillageResource> getAll() {
        return this.resources.values();
    }
}

