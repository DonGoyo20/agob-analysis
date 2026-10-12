/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 *  net.minecraft.server.MinecraftServer
 */
package trep.got.quest;

import java.util.Optional;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.server.MinecraftServer;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.DragonEntity;
import trep.got.entity.dragon.DragonHatchlingFactory;
import trep.got.quest.ValyrianDragonGiftWorldState;

public final class ValyrianDragonGiftService {
    private ValyrianDragonGiftService() {
    }

    public static void initialize() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof AbstractKingdomLordNpcEntity) {
                AbstractKingdomLordNpcEntity lord = (AbstractKingdomLordNpcEntity)entity;
                ValyrianDragonGiftService.checkAndSpawnDragonForLord(world, lord);
            }
        });
    }

    private static void checkAndSpawnDragonForLord(class_3218 world, AbstractKingdomLordNpcEntity lord) {
        MinecraftServer server = world.method_8503();
        ValyrianDragonGiftWorldState state = ValyrianDragonGiftWorldState.get(server.method_30002());
        Optional<ValyrianDragonGiftWorldState.GiftRecord> recordOpt = state.gifts().stream().filter(r -> r.lordNpcId().equals(lord.getPersistentNpcId())).findFirst();
        if (recordOpt.isEmpty()) {
            return;
        }
        ValyrianDragonGiftWorldState.GiftRecord record = recordOpt.get();
        DragonEntity dragon = ValyrianDragonGiftService.findDragonByUuid(server, record);
        if (dragon == null || !dragon.method_5805()) {
            dragon = ValyrianDragonGiftService.spawnDragon(world, lord);
            if (dragon != null) {
                state.updateDragon(record.lordKey(), dragon.method_5667(), lord.method_5667());
            }
        } else {
            dragon.setBoundNpcUuid(lord.method_5667());
        }
    }

    private static DragonEntity spawnDragon(class_3218 world, AbstractKingdomLordNpcEntity lord) {
        class_2338 pos = lord.method_24515();
        DragonEntity dragon = DragonHatchlingFactory.create(world, pos, null);
        if (dragon == null) {
            return null;
        }
        dragon.method_5808(lord.method_23317() + 1.5, lord.method_23318(), lord.method_23321() + 1.5, lord.method_36454(), 0.0f);
        dragon.setBoundNpcUuid(lord.method_5667());
        world.method_8649((class_1297)dragon);
        return dragon;
    }

    private static DragonEntity findDragonByUuid(MinecraftServer server, ValyrianDragonGiftWorldState.GiftRecord record) {
        if (record.dragonUuid() == null) {
            return null;
        }
        for (class_3218 world : server.method_3738()) {
            class_1297 entity = world.method_14190(record.dragonUuid());
            if (!(entity instanceof DragonEntity)) continue;
            DragonEntity dragon = (DragonEntity)entity;
            return dragon;
        }
        return null;
    }
}

