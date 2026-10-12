/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

record DirectionChoice(int dx, int dz, int score) {
    DirectionChoice(int dx, int dz) {
        this(dx, dz, 0);
    }
}

