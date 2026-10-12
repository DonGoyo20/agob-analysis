/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1262
 *  net.minecraft.class_1263
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.castle;

import net.minecraft.class_1262;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.registry.GotCastles;

public final class CastleStorageInventory
implements class_1263 {
    private final class_3218 world;
    private final CastleInstance castle;

    public CastleStorageInventory(class_3218 world, CastleInstance castle) {
        this.world = world;
        this.castle = castle;
    }

    public int method_5439() {
        return this.castle.getStorageSize();
    }

    public boolean method_5442() {
        for (class_1799 stack : this.castle.getStorageItems()) {
            if (stack.method_7960()) continue;
            return false;
        }
        return true;
    }

    public class_1799 method_5438(int slot) {
        return (class_1799)this.castle.getStorageItems().get(slot);
    }

    public class_1799 method_5434(int slot, int amount) {
        class_1799 removed = class_1262.method_5430(this.castle.getStorageItems(), (int)slot, (int)amount);
        if (!removed.method_7960()) {
            this.method_5431();
        }
        return removed;
    }

    public class_1799 method_5441(int slot) {
        class_1799 removed = class_1262.method_5428(this.castle.getStorageItems(), (int)slot);
        if (!removed.method_7960()) {
            this.method_5431();
        }
        return removed;
    }

    public void method_5447(int slot, class_1799 stack) {
        this.castle.getStorageItems().set(slot, (Object)stack);
        if (!stack.method_7960() && stack.method_7947() > this.method_5444()) {
            stack.method_7939(this.method_5444());
        }
        this.method_5431();
    }

    public void method_5431() {
        CastleWorldState.get(this.world).method_80();
    }

    public boolean method_5443(class_1657 player) {
        class_3222 serverPlayer;
        return player instanceof class_3222 && GotCastles.canAccessCastleStorage(serverPlayer = (class_3222)player, this.world, this.castle);
    }

    public void method_5448() {
        this.castle.getStorageItems().clear();
        this.method_5431();
    }
}

