/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public interface CastleHolder {
    public String got$getCastle();

    public void got$setCastle(String var1);

    default public boolean got$hasCastle() {
        return !this.got$getCastle().isBlank();
    }
}

