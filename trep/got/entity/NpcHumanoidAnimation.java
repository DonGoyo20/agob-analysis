/*
 * Decompiled with CFR 0.152.
 */
package trep.got.entity;

public final class NpcHumanoidAnimation {
    public static final int NONE = 0;
    public static final int TALK = 1;
    public static final int TALK2 = 2;
    public static final int BOW = 3;
    public static final int GREETING = 4;

    private NpcHumanoidAnimation() {
    }

    public static int durationTicks(int animationId) {
        return switch (animationId) {
            case 1, 2 -> 15;
            case 3 -> 20;
            case 4 -> 15;
            default -> 0;
        };
    }
}

