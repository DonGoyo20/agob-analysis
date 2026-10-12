/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry.castle.overlay;

import java.util.Map;
import trep.got.registry.castle.overlay.OverlayPresetReference;

public record OverlayPresetReferences(Map<String, OverlayPresetReference> byLegacyId, Map<String, OverlayPresetReference> byCastleId) {
}

