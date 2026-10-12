/*
 * Decompiled with CFR 0.152.
 */
package trep.got.house;

public final class PlayerHouseNameValidator {
    public static final int MAX_LENGTH = 15;

    private PlayerHouseNameValidator() {
    }

    public static String sanitize(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder(value.length());
        boolean previousWasSpace = false;
        for (int index = 0; index < value.length(); ++index) {
            char character = value.charAt(index);
            if (Character.isLetter(character)) {
                builder.append(character);
                previousWasSpace = false;
                continue;
            }
            if (character != ' ' || previousWasSpace) continue;
            builder.append(character);
            previousWasSpace = true;
        }
        String sanitized = builder.toString().trim().replaceAll(" +", " ");
        return sanitized.length() > 15 ? sanitized.substring(0, 15).trim() : sanitized;
    }

    public static boolean isValid(String value) {
        if (value == null || value.isBlank() || value.length() > 15) {
            return false;
        }
        for (int index = 0; index < value.length(); ++index) {
            char character = value.charAt(index);
            if (Character.isLetter(character) || character == ' ') continue;
            return false;
        }
        return value.trim().equals(value) && !value.contains("  ");
    }
}

