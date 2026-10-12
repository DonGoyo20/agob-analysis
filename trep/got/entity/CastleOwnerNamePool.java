/*
 * Decompiled with CFR 0.152.
 */
package trep.got.entity;

import java.util.List;

public record CastleOwnerNamePool(List<String> firstNames, List<String> commonerLastNames, List<String> bastardLastNames) {
    public CastleOwnerNamePool {
        firstNames = List.copyOf(firstNames);
        commonerLastNames = List.copyOf(commonerLastNames);
        bastardLastNames = List.copyOf(bastardLastNames);
    }
}

