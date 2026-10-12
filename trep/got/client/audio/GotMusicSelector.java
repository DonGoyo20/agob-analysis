/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1143
 *  net.minecraft.class_3414
 *  net.minecraft.class_5195
 *  net.minecraft.class_6880
 *  net.minecraft.class_7923
 */
package trep.got.client.audio;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1143;
import net.minecraft.class_3414;
import net.minecraft.class_5195;
import net.minecraft.class_6880;
import net.minecraft.class_7923;
import trep.got.client.audio.GotMusicMode;
import trep.got.client.audio.GotMusicSettings;
import trep.got.registry.GotSounds;

@Environment(value=EnvType.CLIENT)
public final class GotMusicSelector {
    private static final List<class_6880<class_3414>> AGOB_GAME_TRACKS = List.of(class_7923.field_41172.method_47983((Object)GotSounds.SOUNDTRACK_1), class_7923.field_41172.method_47983((Object)GotSounds.SOUNDTRACK_2), class_7923.field_41172.method_47983((Object)GotSounds.SOUNDTRACK_3), class_7923.field_41172.method_47983((Object)GotSounds.SOUNDTRACK_4));
    private static GotMusicMode cachedMode;
    private static class_5195 cachedOriginal;
    private static class_5195 cachedSelection;

    private GotMusicSelector() {
    }

    public static synchronized class_5195 pickMusicVariant(class_5195 original) {
        if (original == null || AGOB_GAME_TRACKS.isEmpty()) {
            return original;
        }
        GotMusicMode mode = GotMusicSettings.getMusicMode();
        if (mode == GotMusicMode.VANILLA) {
            return original;
        }
        if (!GotMusicSelector.agob$isReplaceableMusicType(original)) {
            return original;
        }
        if (cachedSelection != null && cachedMode == mode && cachedOriginal == original) {
            return cachedSelection;
        }
        if (mode == GotMusicMode.AGOB) {
            int index = ThreadLocalRandom.current().nextInt(AGOB_GAME_TRACKS.size());
            class_6880<class_3414> selectedTrack = AGOB_GAME_TRACKS.get(index);
            class_5195 selectedMusic = new class_5195(selectedTrack, 0, 0, true);
            cachedMode = mode;
            cachedOriginal = original;
            cachedSelection = selectedMusic;
            return selectedMusic;
        }
        int totalChoices = AGOB_GAME_TRACKS.size() + 1;
        int mixedIndex = ThreadLocalRandom.current().nextInt(totalChoices);
        if (mixedIndex == 0) {
            cachedMode = mode;
            cachedOriginal = original;
            cachedSelection = original;
            return original;
        }
        int index = mixedIndex - 1;
        class_6880<class_3414> selectedTrack = AGOB_GAME_TRACKS.get(index);
        class_5195 selectedMusic = new class_5195(selectedTrack, original.method_27280(), original.method_27281(), original.method_27282());
        cachedMode = mode;
        cachedOriginal = original;
        cachedSelection = selectedMusic;
        return selectedMusic;
    }

    public static synchronized void resetSelection() {
        cachedMode = null;
        cachedOriginal = null;
        cachedSelection = null;
    }

    private static boolean agob$isReplaceableMusicType(class_5195 original) {
        return original == class_1143.field_5586 || original == class_1143.field_5585 || original == class_1143.field_5581;
    }
}

