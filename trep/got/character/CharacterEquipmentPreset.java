/*
 * Decompiled with CFR 0.152.
 */
package trep.got.character;

public record CharacterEquipmentPreset(String head, String chest, String legs, String feet, String mainHand, String offHand) {
    public static final CharacterEquipmentPreset EMPTY = new CharacterEquipmentPreset("", "", "", "", "", "");
}

