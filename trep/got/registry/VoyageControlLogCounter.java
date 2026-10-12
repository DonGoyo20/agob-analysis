/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

final class VoyageControlLogCounter {
    final String reason;
    int count;
    String sampleVoyageId = "n/a";
    String sampleSourceCastle = "n/a";
    String sampleDestinationCastle = "n/a";
    String sampleController = "n/a";
    String sampleControllerUuid = "n/a";
    String samplePassengers = "n/a";
    String sampleDetail = "n/a";

    VoyageControlLogCounter(String reason) {
        this.reason = reason;
    }
}

