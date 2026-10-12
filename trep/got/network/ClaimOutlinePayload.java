/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record ClaimOutlinePayload(List<Entry> claims) implements class_8710
{
    public static final class_8710.class_9154<ClaimOutlinePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"claim_outlines"));
    public static final class_9139<class_9129, ClaimOutlinePayload> CODEC = class_8710.method_56484((value, buf) -> {
        buf.method_10804(value.claims.size());
        for (Entry entry : value.claims) {
            buf.method_10814(entry.id());
            buf.method_10807(entry.bannerPos());
            buf.method_10804(entry.claimRadiusChunks());
            buf.method_10814(entry.name());
            buf.method_10814(entry.kingdomId());
            buf.method_10814(entry.houseId());
            buf.method_10814(entry.religionId());
            buf.method_10814(entry.tier());
            buf.method_10804(entry.cooldownSeconds());
            buf.method_10804(entry.structures().size());
            for (StructureEntry structure : entry.structures()) {
                buf.method_10814(structure.id());
                buf.method_10807(structure.minPos());
                buf.method_10807(structure.maxPos());
                buf.method_10814(structure.structurePath());
                buf.method_10814(structure.ownerProfileId());
                buf.method_10814(structure.ownerUuid());
                buf.method_10814(structure.ownerName());
            }
        }
    }, buf -> {
        int size = buf.method_10816();
        ArrayList<Entry> claims = new ArrayList<Entry>(size);
        for (int i = 0; i < size; ++i) {
            String id = buf.method_19772();
            class_2338 bannerPos = buf.method_10811();
            int claimRadiusChunks = buf.method_10816();
            String name = buf.method_19772();
            String kingdomId = buf.method_19772();
            String houseId = buf.method_19772();
            String religionId = buf.method_19772();
            String tier = buf.method_19772();
            int cooldownSeconds = buf.method_10816();
            int structureCount = buf.method_10816();
            ArrayList<StructureEntry> structures = new ArrayList<StructureEntry>(structureCount);
            for (int structureIndex = 0; structureIndex < structureCount; ++structureIndex) {
                structures.add(new StructureEntry(buf.method_19772(), buf.method_10811(), buf.method_10811(), buf.method_19772(), buf.method_19772(), buf.method_19772(), buf.method_19772()));
            }
            claims.add(new Entry(id, bannerPos, claimRadiusChunks, name, kingdomId, houseId, religionId, tier, cooldownSeconds, List.copyOf(structures)));
        }
        return new ClaimOutlinePayload(List.copyOf(claims));
    });

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    public record StructureEntry(String id, class_2338 minPos, class_2338 maxPos, String structurePath, String ownerProfileId, String ownerUuid, String ownerName) {
    }

    public record Entry(String id, class_2338 bannerPos, int claimRadiusChunks, String name, String kingdomId, String houseId, String religionId, String tier, int cooldownSeconds, List<StructureEntry> structures) {
        public Entry {
            structures = structures == null ? List.of() : List.copyOf(structures);
        }
    }
}

