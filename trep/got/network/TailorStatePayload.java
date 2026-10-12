/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package trep.got.network;

import java.util.Arrays;
import net.minecraft.class_2960;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import trep.got.character.PlayerSkinBuilderProfile;

public record TailorStatePayload(PlayerSkinBuilderProfile skinBuilderProfile, String[] discoveredClothing, String[] unlockedClothing, String[] unlockedDyeClothing, String[] clothingAttributes, boolean openScreen) implements class_8710
{
    public static final class_8710.class_9154<TailorStatePayload> ID = new class_8710.class_9154(class_2960.method_60655((String)"agob", (String)"tailor_state"));
    public static final class_9139<class_9129, TailorStatePayload> CODEC = class_8710.method_56484((value, buf) -> {
        value.skinBuilderProfile.write((class_9129)buf);
        TailorStatePayload.writeStrings(buf, value.discoveredClothing);
        TailorStatePayload.writeStrings(buf, value.unlockedClothing);
        TailorStatePayload.writeStrings(buf, value.unlockedDyeClothing);
        TailorStatePayload.writeStrings(buf, value.clothingAttributes);
        buf.method_52964(value.openScreen);
    }, buf -> new TailorStatePayload(PlayerSkinBuilderProfile.read(buf), TailorStatePayload.readStrings(buf), TailorStatePayload.readStrings(buf), TailorStatePayload.readStrings(buf), TailorStatePayload.readStrings(buf), buf.readBoolean()));

    public TailorStatePayload {
        skinBuilderProfile = skinBuilderProfile == null ? PlayerSkinBuilderProfile.DEFAULT : skinBuilderProfile;
        discoveredClothing = Arrays.copyOf(discoveredClothing, discoveredClothing.length);
        unlockedClothing = Arrays.copyOf(unlockedClothing, unlockedClothing.length);
        unlockedDyeClothing = Arrays.copyOf(unlockedDyeClothing, unlockedDyeClothing.length);
        clothingAttributes = Arrays.copyOf(clothingAttributes, clothingAttributes.length);
    }

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }

    private static void writeStrings(class_9129 buf, String[] values) {
        buf.method_10804(values.length);
        for (String value : values) {
            buf.method_10814(value == null ? "" : value);
        }
    }

    private static String[] readStrings(class_9129 buf) {
        int size = buf.method_10816();
        String[] values = new String[size];
        for (int index = 0; index < size; ++index) {
            values[index] = buf.method_19772();
        }
        return values;
    }
}

