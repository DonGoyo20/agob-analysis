/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3300
 */
package trep.got.client.character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import trep.got.client.character.PlayerSkinBuilderOptions;

@Environment(value=EnvType.CLIENT)
public final class PlayerSkinBuilderClothingRegistry {
    private static final String SHIRT_FOLDER = "textures/entity/player/clothes/shirt";
    private static final String PANTS_FOLDER = "textures/entity/player/clothes/pants";

    private PlayerSkinBuilderClothingRegistry() {
    }

    public static List<ClothingOption> shirts() {
        return PlayerSkinBuilderClothingRegistry.load().shirts();
    }

    public static List<ClothingOption> pants() {
        return PlayerSkinBuilderClothingRegistry.load().pants();
    }

    public static List<ClothingSet> sets() {
        return PlayerSkinBuilderClothingRegistry.load().sets();
    }

    public static ClothingRegistrySnapshot load() {
        class_3300 manager = class_310.method_1551().method_1478();
        Set<String> shirtIds = PlayerSkinBuilderClothingRegistry.loadIds(manager, SHIRT_FOLDER);
        Set<String> pantsIds = PlayerSkinBuilderClothingRegistry.loadIds(manager, PANTS_FOLDER);
        List<ClothingSet> sets = shirtIds.stream().filter(pantsIds::contains).map(PlayerSkinBuilderClothingRegistry::createSet).sorted(Comparator.comparing(ClothingSet::displayName).thenComparing(ClothingSet::id)).toList();
        List<ClothingOption> shirts = shirtIds.stream().map(id -> PlayerSkinBuilderClothingRegistry.createOption(id, ClothingSlot.SHIRT, pantsIds.contains(id))).sorted(Comparator.comparing(ClothingOption::displayName).thenComparing(ClothingOption::id)).toList();
        List<ClothingOption> pants = pantsIds.stream().map(id -> PlayerSkinBuilderClothingRegistry.createOption(id, ClothingSlot.PANTS, shirtIds.contains(id))).sorted(Comparator.comparing(ClothingOption::displayName).thenComparing(ClothingOption::id)).toList();
        return new ClothingRegistrySnapshot(shirts, pants, sets);
    }

    private static ClothingOption createOption(String id, ClothingSlot slot, boolean hasMatchingPiece) {
        ClothingKind kind = hasMatchingPiece ? ClothingKind.SET : (slot == ClothingSlot.SHIRT ? ClothingKind.SHIRT : ClothingKind.PANTS);
        String name = PlayerSkinBuilderClothingRegistry.displayName(id, kind, slot);
        String folder = slot == ClothingSlot.SHIRT ? "clothes/shirt/" : "clothes/pants/";
        return new ClothingOption(id, name, slot, kind, PlayerSkinBuilderOptions.texture(folder + id), hasMatchingPiece ? id : "", true);
    }

    private static ClothingSet createSet(String id) {
        return new ClothingSet(id, PlayerSkinBuilderClothingRegistry.displayName(id, ClothingKind.SET, ClothingSlot.SHIRT), PlayerSkinBuilderOptions.texture("clothes/shirt/" + id), PlayerSkinBuilderOptions.texture("clothes/pants/" + id), true);
    }

    private static Set<String> loadIds(class_3300 manager, String folder) {
        HashSet<String> ids = new HashSet<String>();
        manager.method_14488(folder, id -> id.method_12832().endsWith(".png")).keySet().forEach(texture -> ids.add(PlayerSkinBuilderClothingRegistry.fileId(texture)));
        return ids;
    }

    private static String fileId(class_2960 texture) {
        String path = texture.method_12832();
        String fileName = path.substring(path.lastIndexOf(47) + 1);
        return fileName.substring(0, fileName.length() - ".png".length());
    }

    private static String displayName(String id, ClothingKind kind, ClothingSlot slot) {
        String baseName = PlayerSkinBuilderClothingRegistry.labelFromId(id);
        if (kind == ClothingKind.SET) {
            return baseName;
        }
        return slot == ClothingSlot.SHIRT ? baseName + " Tunic" : baseName + " Trousers";
    }

    private static String labelFromId(String id) {
        StringBuilder builder = new StringBuilder();
        for (String part : id.split("_")) {
            if (part.isBlank()) continue;
            if (!builder.isEmpty()) {
                builder.append(' ');
            }
            builder.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() <= 1) continue;
            builder.append(part.substring(1).toLowerCase(Locale.ROOT));
        }
        return builder.isEmpty() ? id : builder.toString();
    }

    @Environment(value=EnvType.CLIENT)
    public record ClothingRegistrySnapshot(List<ClothingOption> shirts, List<ClothingOption> pants, List<ClothingSet> sets) {
        public ClothingRegistrySnapshot {
            shirts = List.copyOf(new ArrayList<ClothingOption>(shirts));
            pants = List.copyOf(new ArrayList<ClothingOption>(pants));
            sets = List.copyOf(new ArrayList<ClothingSet>(sets));
        }
    }

    @Environment(value=EnvType.CLIENT)
    public static enum ClothingKind {
        SHIRT,
        PANTS,
        SET;

    }

    @Environment(value=EnvType.CLIENT)
    public static enum ClothingSlot {
        SHIRT,
        PANTS;

    }

    @Environment(value=EnvType.CLIENT)
    public record ClothingOption(String id, String displayName, ClothingSlot slot, ClothingKind kind, class_2960 texture, String matchingPieceId, boolean unlockedByDefault) {
        public boolean isSetPiece() {
            return this.kind == ClothingKind.SET;
        }
    }

    @Environment(value=EnvType.CLIENT)
    public record ClothingSet(String id, String displayName, class_2960 shirtTexture, class_2960 pantsTexture, boolean unlockedByDefault) {
    }
}

