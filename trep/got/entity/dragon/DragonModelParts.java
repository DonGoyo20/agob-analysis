/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity.dragon;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;
import net.minecraft.class_2487;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonModelPartDefinition;
import trep.got.entity.dragon.DragonModelPartGroup;
import trep.got.entity.dragon.DragonModelPartRegistry;
import trep.got.entity.dragon.DragonOrigin;

public final class DragonModelParts {
    public static final DragonModelParts DEFAULT = new DragonModelParts(new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class));
    private final EnumMap<DragonModelPartGroup, DragonModelPartDefinition> parts;

    private DragonModelParts(EnumMap<DragonModelPartGroup, DragonModelPartDefinition> parts) {
        this.parts = parts;
    }

    public static DragonModelParts of(Map<DragonModelPartGroup, DragonModelPartDefinition> parts) {
        EnumMap<DragonModelPartGroup, DragonModelPartDefinition> resolved = new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class);
        for (Map.Entry<DragonModelPartGroup, DragonModelPartDefinition> entry : parts.entrySet()) {
            if (!entry.getKey().isModular()) continue;
            resolved.put(entry.getKey(), entry.getValue());
        }
        return resolved.isEmpty() ? DEFAULT : new DragonModelParts(resolved);
    }

    public static DragonModelParts fromNbt(class_2487 nbt) {
        return DragonModelParts.fromNbt(nbt, DragonOrigin.DEFAULT);
    }

    public static DragonModelParts fromNbt(class_2487 nbt, DragonOrigin origin) {
        EnumMap<DragonModelPartGroup, DragonModelPartDefinition> parts = new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class);
        for (String key : nbt.method_10541()) {
            DragonModelPartGroup group = DragonModelPartGroup.byId(key);
            if (group == null || !group.isModular()) continue;
            parts.put(group, DragonModelPartRegistry.byId(group, nbt.method_10558(key), origin));
        }
        return DragonModelParts.of(parts);
    }

    public static DragonModelParts decode(@Nullable String encoded) {
        return DragonModelParts.decode(encoded, DragonOrigin.DEFAULT);
    }

    public static DragonModelParts decode(@Nullable String encoded, DragonOrigin origin) {
        if (encoded == null || encoded.isBlank()) {
            return DEFAULT;
        }
        EnumMap<DragonModelPartGroup, DragonModelPartDefinition> parts = new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class);
        for (String entry : encoded.split("[,;]")) {
            DragonModelPartGroup group;
            String[] pair = entry.split("=", 2);
            if (pair.length != 2 || (group = DragonModelPartGroup.byId(pair[0])) == null || !group.isModular()) continue;
            parts.put(group, DragonModelPartRegistry.byId(group, pair[1], origin));
        }
        return DragonModelParts.of(parts);
    }

    public DragonModelPartDefinition part(DragonModelPartGroup group, DragonOrigin origin) {
        DragonModelPartDefinition definition = this.parts.get((Object)group);
        if (definition != null && definition.origin() == (origin == null ? DragonOrigin.DEFAULT : origin)) {
            return definition;
        }
        return DragonModelPartRegistry.defaultPart(group, origin);
    }

    public DragonModelType sourceModelType(DragonModelPartGroup group, DragonOrigin origin) {
        return this.part(group, origin).sourceModelType();
    }

    public DragonModelParts normalizeForOrigin(DragonOrigin origin) {
        DragonOrigin resolvedOrigin;
        DragonOrigin dragonOrigin = resolvedOrigin = origin == null ? DragonOrigin.DEFAULT : origin;
        if (this.parts.isEmpty()) {
            return this;
        }
        EnumMap<DragonModelPartGroup, DragonModelPartDefinition> filtered = new EnumMap<DragonModelPartGroup, DragonModelPartDefinition>(DragonModelPartGroup.class);
        for (Map.Entry<DragonModelPartGroup, DragonModelPartDefinition> entry : this.parts.entrySet()) {
            if (entry.getValue().origin() != resolvedOrigin) continue;
            filtered.put(entry.getKey(), entry.getValue());
        }
        return DragonModelParts.of(filtered);
    }

    public boolean isDefault() {
        return this.parts.isEmpty();
    }

    public String encode() {
        if (this.parts.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(";");
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            DragonModelPartDefinition definition = this.parts.get((Object)group);
            if (definition == null) continue;
            joiner.add(group.id() + "=" + definition.id().toLowerCase(Locale.ROOT));
        }
        return joiner.toString();
    }

    public class_2487 toNbt() {
        class_2487 nbt = new class_2487();
        for (DragonModelPartGroup group : DragonModelPartGroup.values()) {
            DragonModelPartDefinition definition = this.parts.get((Object)group);
            if (definition == null) continue;
            nbt.method_10582(group.id(), definition.id());
        }
        return nbt;
    }
}

