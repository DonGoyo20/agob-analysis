/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

enum AttributeOperation {
    ADD("Added"),
    SET("Set"),
    REMOVE("Removed");

    final String label;

    private AttributeOperation(String label) {
        this.label = label;
    }
}

