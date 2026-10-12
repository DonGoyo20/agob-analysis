/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_18
 *  net.minecraft.class_18$class_8645
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2520
 *  net.minecraft.class_3218
 *  net.minecraft.class_4284
 *  net.minecraft.class_7225$class_7874
 */
package trep.got.entity.dragon;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1297;
import net.minecraft.class_18;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_3218;
import net.minecraft.class_4284;
import net.minecraft.class_7225;
import trep.got.entity.DragonEntity;

public final class DragonBondWorldState
extends class_18 {
    public static final String STATE_ID = "gotmod.dragon_bonds";
    private static final class_18.class_8645<DragonBondWorldState> TYPE = new class_18.class_8645(DragonBondWorldState::new, DragonBondWorldState::fromNbt, class_4284.field_45077);
    private final Map<UUID, UUID> dragonsByPlayer = new LinkedHashMap<UUID, UUID>();

    public static DragonBondWorldState get(class_3218 world) {
        return (DragonBondWorldState)world.method_8503().method_30002().method_17983().method_17924(TYPE, STATE_ID);
    }

    public boolean hasLivingDragon(class_3218 world, UUID playerUuid) {
        UUID dragonUuid = this.dragonsByPlayer.get(playerUuid);
        if (dragonUuid == null) {
            return false;
        }
        for (class_3218 serverWorld : world.method_8503().method_3738()) {
            DragonEntity dragon;
            class_1297 entity = serverWorld.method_14190(dragonUuid);
            if (!(entity instanceof DragonEntity) || !(dragon = (DragonEntity)entity).method_5805()) continue;
            return true;
        }
        this.dragonsByPlayer.remove(playerUuid);
        this.method_80();
        return false;
    }

    public void bind(UUID playerUuid, DragonEntity dragon) {
        this.dragonsByPlayer.put(playerUuid, dragon.method_5667());
        this.method_80();
    }

    public void unbind(UUID playerUuid, UUID dragonUuid) {
        if (playerUuid == null) {
            return;
        }
        UUID boundDragonUuid = this.dragonsByPlayer.get(playerUuid);
        if (boundDragonUuid == null || dragonUuid != null && !boundDragonUuid.equals(dragonUuid)) {
            return;
        }
        this.dragonsByPlayer.remove(playerUuid);
        this.method_80();
    }

    public class_2487 method_75(class_2487 nbt, class_7225.class_7874 registries) {
        class_2499 bonds = new class_2499();
        for (Map.Entry<UUID, UUID> entry : this.dragonsByPlayer.entrySet()) {
            class_2487 bond = new class_2487();
            bond.method_25927("player", entry.getKey());
            bond.method_25927("dragon", entry.getValue());
            bonds.add((Object)bond);
        }
        nbt.method_10566("bonds", (class_2520)bonds);
        return nbt;
    }

    private static DragonBondWorldState fromNbt(class_2487 nbt, class_7225.class_7874 registries) {
        DragonBondWorldState state = new DragonBondWorldState();
        class_2499 bonds = nbt.method_10554("bonds", 10);
        for (int index = 0; index < bonds.size(); ++index) {
            class_2487 bond = bonds.method_10602(index);
            if (!bond.method_25928("player") || !bond.method_25928("dragon")) continue;
            state.dragonsByPlayer.put(bond.method_25926("player"), bond.method_25926("dragon"));
        }
        return state;
    }
}

