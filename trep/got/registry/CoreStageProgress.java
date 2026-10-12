/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

record CoreStageProgress(boolean completed, boolean failed) {
    static CoreStageProgress inProgress() {
        return new CoreStageProgress(false, false);
    }

    static CoreStageProgress done() {
        return new CoreStageProgress(true, false);
    }

    static CoreStageProgress error() {
        return new CoreStageProgress(false, true);
    }

    boolean isCompleted() {
        return this.completed;
    }

    boolean hasFailed() {
        return this.failed;
    }
}

