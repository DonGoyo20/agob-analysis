/*
 * Decompiled with CFR 0.152.
 */
package trep.got.castle;

public enum CastleRecaptureStage {
    DORMANT("dormant"),
    INCOMING("incoming"),
    INVASION("invasion");

    private final String id;

    private CastleRecaptureStage(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static CastleRecaptureStage byId(String id) {
        for (CastleRecaptureStage stage : CastleRecaptureStage.values()) {
            if (!stage.id.equalsIgnoreCase(id)) continue;
            return stage;
        }
        return DORMANT;
    }
}

