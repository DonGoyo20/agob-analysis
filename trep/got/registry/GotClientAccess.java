/*
 * Decompiled with CFR 0.152.
 */
package trep.got.registry;

public final class GotClientAccess {
    private static Runnable worldMapOpener = () -> {};
    private static Runnable journalOpener = () -> {};
    private static Runnable guidebookOpener = () -> {};

    private GotClientAccess() {
    }

    public static void registerWorldMapOpener(Runnable opener) {
        worldMapOpener = opener;
    }

    public static void openWorldMap() {
        worldMapOpener.run();
    }

    public static void registerJournalOpener(Runnable opener) {
        journalOpener = opener;
    }

    public static void openJournal() {
        journalOpener.run();
    }

    public static void registerGuidebookOpener(Runnable opener) {
        guidebookOpener = opener;
    }

    public static void openGuidebook() {
        guidebookOpener.run();
    }
}

