/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_2960
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.blacksmith;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.minecraft.class_7225;

public final class BlacksmithCraftSession {
    private final class_2960 recipeId;
    private final UUID smithUuid;
    private final class_1799 result;
    private final List<class_1799> reservedStacks;
    private final float speed;
    private final long startedTick;
    private int completedHits;
    private int badHits;
    private int okayHits;
    private int perfectHits;
    private int perfectCenterHits;

    public BlacksmithCraftSession(class_2960 recipeId, UUID smithUuid, class_1799 result, List<class_1799> reservedStacks, float speed, long startedTick) {
        this.recipeId = recipeId;
        this.smithUuid = smithUuid;
        this.result = result.method_7972();
        this.reservedStacks = reservedStacks.stream().map(class_1799::method_7972).toList();
        this.speed = speed;
        this.startedTick = startedTick;
    }

    public class_2960 recipeId() {
        return this.recipeId;
    }

    public UUID smithUuid() {
        return this.smithUuid;
    }

    public class_1799 result() {
        return this.result.method_7972();
    }

    public List<class_1799> reservedStacks() {
        return this.reservedStacks.stream().map(class_1799::method_7972).toList();
    }

    public float speed() {
        return this.speed;
    }

    public long startedTick() {
        return this.startedTick;
    }

    public int completedHits() {
        return this.completedHits;
    }

    public int badHits() {
        return this.badHits;
    }

    public int okayHits() {
        return this.okayHits;
    }

    public int perfectHits() {
        return this.perfectHits;
    }

    public int perfectCenterHits() {
        return this.perfectCenterHits;
    }

    public boolean isComplete() {
        return this.completedHits >= 3;
    }

    public HitBand evaluateHit(long worldTick) {
        int indicator = this.indicatorPosition(worldTick);
        if (indicator <= 21 || indicator >= 90) {
            return HitBand.BAD;
        }
        if (indicator >= 54 && indicator <= 56) {
            return HitBand.PERFECT_CENTER;
        }
        if (indicator >= 51 && indicator <= 60) {
            return HitBand.PERFECT;
        }
        return HitBand.OKAY;
    }

    public HitBand recordHit(long worldTick) {
        HitBand band = this.evaluateHit(worldTick);
        ++this.completedHits;
        switch (band.ordinal()) {
            case 0: {
                ++this.badHits;
                break;
            }
            case 1: {
                ++this.okayHits;
                break;
            }
            case 2: {
                ++this.perfectHits;
                break;
            }
            case 3: {
                ++this.perfectCenterHits;
            }
        }
        return band;
    }

    public int qualityScore() {
        return this.okayHits + this.perfectHits * 2 + this.perfectCenterHits * 3;
    }

    public int indicatorPosition(long worldTick) {
        return Math.round(this.indicatorProgress(worldTick, 0.0f) * 110.0f);
    }

    public int indicatorPosition(long worldTick, float tickDelta) {
        return Math.round(this.indicatorProgress(worldTick, tickDelta) * 110.0f);
    }

    public float indicatorProgress(long worldTick, float tickDelta) {
        int cycleTicks = BlacksmithCraftSession.cycleTicks(this.speed);
        if (cycleTicks <= 1) {
            return 0.5f;
        }
        double cycle = cycleTicks;
        double phase = (float)Math.floorMod(worldTick - this.startedTick, cycleTicks) + tickDelta;
        double normalized = phase / cycle;
        double triangle = normalized <= 0.5 ? normalized * 2.0 : (1.0 - normalized) * 2.0;
        return (float)Math.max(0.0, Math.min(1.0, triangle));
    }

    public class_2487 toNbt(class_7225.class_7874 registries) {
        class_2487 nbt = new class_2487();
        nbt.method_10582("recipe_id", this.recipeId.toString());
        nbt.method_25927("smith_uuid", this.smithUuid);
        nbt.method_10566("result", this.result.method_57358(registries));
        nbt.method_10548("speed", this.speed);
        nbt.method_10544("started_tick", this.startedTick);
        nbt.method_10569("completed_hits", this.completedHits);
        nbt.method_10569("bad_hits", this.badHits);
        nbt.method_10569("okay_hits", this.okayHits);
        nbt.method_10569("perfect_hits", this.perfectHits);
        nbt.method_10569("perfect_center_hits", this.perfectCenterHits);
        class_2499 stacks = new class_2499();
        for (class_1799 stack : this.reservedStacks) {
            stacks.add((Object)stack.method_57358(registries));
        }
        nbt.method_10566("reserved_stacks", (class_2520)stacks);
        return nbt;
    }

    public static BlacksmithCraftSession fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        class_2960 recipeId = class_2960.method_12829((String)nbt.method_10558("recipe_id"));
        if (recipeId == null || !nbt.method_25928("smith_uuid")) {
            return null;
        }
        class_1799 result = class_1799.method_57359((class_7225.class_7874)registries, (class_2487)nbt.method_10562("result"));
        class_2499 stacksNbt = nbt.method_10554("reserved_stacks", 10);
        ArrayList<class_1799> reservedStacks = new ArrayList<class_1799>(stacksNbt.size());
        for (int index = 0; index < stacksNbt.size(); ++index) {
            reservedStacks.add(class_1799.method_57359((class_7225.class_7874)registries, (class_2487)stacksNbt.method_10602(index)));
        }
        BlacksmithCraftSession session = new BlacksmithCraftSession(recipeId, nbt.method_25926("smith_uuid"), result, List.copyOf(reservedStacks), nbt.method_10545("speed") ? nbt.method_10583("speed") : 0.6f, nbt.method_10537("started_tick"));
        session.completedHits = nbt.method_10550("completed_hits");
        session.badHits = nbt.method_10550("bad_hits");
        session.okayHits = nbt.method_10550("okay_hits");
        session.perfectHits = nbt.method_10550("perfect_hits");
        session.perfectCenterHits = nbt.method_10550("perfect_center_hits");
        return session;
    }

    public static int cycleTicks(float speed) {
        float clamped = Math.max(0.1f, Math.min(1.0f, speed));
        float normalized = (clamped - 0.1f) / 0.9f;
        return Math.max(4, Math.round(40.0f - normalized * 36.0f));
    }

    public static enum HitBand {
        BAD,
        OKAY,
        PERFECT,
        PERFECT_CENTER;

    }
}

