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
import java.util.List;
import java.util.Locale;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import trep.got.client.character.PlayerSkinBuilderClothingRegistry;

@Environment(value=EnvType.CLIENT)
public final class PlayerSkinBuilderOptions {
    public static final LayerOption NONE = new LayerOption("", "None", null);

    private PlayerSkinBuilderOptions() {
    }

    public static List<LayerOption> skinTones() {
        return PlayerSkinBuilderOptions.loadFolder("skin", false);
    }

    public static List<LayerOption> faces(String gender) {
        String folder = "Female".equals(gender) ? "face/female" : "face/male";
        return PlayerSkinBuilderOptions.loadFolder(folder, false);
    }

    public static List<LayerOption> hair(String gender, boolean includeNone) {
        String folder = "Female".equals(gender) ? "hair/female" : "hair/male";
        return PlayerSkinBuilderOptions.loadFolder(folder, includeNone);
    }

    public static List<LayerOption> shirts(boolean includeNone) {
        return PlayerSkinBuilderOptions.clothingOptions(PlayerSkinBuilderClothingRegistry.shirts(), includeNone);
    }

    public static List<LayerOption> pants(boolean includeNone) {
        return PlayerSkinBuilderOptions.clothingOptions(PlayerSkinBuilderClothingRegistry.pants(), includeNone);
    }

    public static class_2960 texture(String relativePath) {
        return class_2960.method_60655((String)"agob", (String)("textures/entity/player/" + relativePath + ".png"));
    }

    private static List<LayerOption> loadFolder(String folder, boolean includeNone) {
        class_3300 manager = class_310.method_1551().method_1478();
        String prefix = "textures/entity/player/" + folder;
        List<LayerOption> options = manager.method_14488(prefix, id -> id.method_12832().endsWith(".png")).keySet().stream().map(PlayerSkinBuilderOptions::toOption).sorted(Comparator.comparingInt(PlayerSkinBuilderOptions::numericSuffix).thenComparing(LayerOption::id)).toList();
        if (!includeNone) {
            return options;
        }
        ArrayList<LayerOption> withNone = new ArrayList<LayerOption>();
        withNone.add(NONE);
        withNone.addAll(options);
        return List.copyOf(withNone);
    }

    private static List<LayerOption> clothingOptions(List<PlayerSkinBuilderClothingRegistry.ClothingOption> clothing, boolean includeNone) {
        ArrayList<LayerOption> options = new ArrayList<LayerOption>();
        if (includeNone) {
            options.add(NONE);
        }
        for (PlayerSkinBuilderClothingRegistry.ClothingOption option : clothing) {
            options.add(new LayerOption(option.id(), option.displayName(), option.texture()));
        }
        return List.copyOf(options);
    }

    private static LayerOption toOption(class_2960 texture) {
        String path = texture.method_12832();
        String fileName = path.substring(path.lastIndexOf(47) + 1);
        String id = fileName.substring(0, fileName.length() - ".png".length());
        return new LayerOption(id, PlayerSkinBuilderOptions.labelFromFileName(id), texture);
    }

    private static int numericSuffix(LayerOption option) {
        int index;
        String id = option.id();
        for (index = id.length() - 1; index >= 0 && Character.isDigit(id.charAt(index)); --index) {
        }
        if (index == id.length() - 1) {
            return Integer.MAX_VALUE;
        }
        try {
            return Integer.parseInt(id.substring(index + 1));
        }
        catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private static String labelFromFileName(String fileName) {
        StringBuilder builder = new StringBuilder();
        for (String part : fileName.split("_")) {
            if (part.isBlank()) continue;
            if (!builder.isEmpty()) {
                builder.append(' ');
            }
            builder.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() <= 1) continue;
            builder.append(part.substring(1).toLowerCase(Locale.ROOT));
        }
        return builder.isEmpty() ? fileName : builder.toString();
    }

    @Environment(value=EnvType.CLIENT)
    public record LayerOption(String id, String label, class_2960 texture) {
    }
}

