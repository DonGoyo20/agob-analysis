/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1259$class_1260
 *  net.minecraft.class_1259$class_1261
 *  net.minecraft.class_2561
 *  net.minecraft.class_3213
 *  net.minecraft.class_3222
 *  net.minecraft.class_3532
 */
package trep.got.registry;

import java.util.List;
import net.minecraft.class_1259;
import net.minecraft.class_2561;
import net.minecraft.class_3213;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import trep.got.registry.GotCastles;

final class ActiveKingdomAiInvasion {
    private final String castleId;
    private final class_3213 bossBar;

    ActiveKingdomAiInvasion(String castleId) {
        this.castleId = castleId;
        this.bossBar = new class_3213((class_2561)class_2561.method_43470((String)""), class_1259.class_1260.field_5782, class_1259.class_1261.field_5795);
    }

    class_3213 bossBar() {
        return this.bossBar;
    }

    void syncPlayers(List<class_3222> players) {
        this.bossBar.method_14094();
        for (class_3222 player : players) {
            this.bossBar.method_14088(player);
        }
    }

    void updateIncomingBossBar(long remainingTicks, long durationTicks, String modeName) {
        this.bossBar.method_5413((class_2561)class_2561.method_43470((String)(modeName + " Incoming | " + GotCastles.formatRemainingTime(remainingTicks))));
        float percent = durationTicks <= 0L ? 0.0f : class_3532.method_15363((float)((float)remainingTicks / (float)durationTicks), (float)0.0f, (float)1.0f);
        this.bossBar.method_5408(percent);
    }

    void updateInvasionBossBar(int attackersRemaining, int attackersTotal, String modeName) {
        this.bossBar.method_5413((class_2561)class_2561.method_43470((String)(modeName + " Assault | " + attackersRemaining + " remaining")));
        float percent = attackersTotal <= 0 ? 0.0f : class_3532.method_15363((float)((float)attackersRemaining / (float)attackersTotal), (float)0.0f, (float)1.0f);
        this.bossBar.method_5408(percent);
    }
}

