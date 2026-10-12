/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1043
 *  net.minecraft.class_1044
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 */
package trep.got.client.map;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import trep.got.client.map.TerritoryOwnershipClientCache;
import trep.got.client.map.TerritoryOwnershipRaster;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
public final class CastleTerritoryClientState {
    private static final class_2960 TERRITORY_TEXTURE = class_2960.method_60655((String)"agob", (String)"dynamic/castle_territories");
    private static final int OVERLAY_ALPHA = 112;
    private static TerritoryOwnershipRaster cachedRaster;
    private static boolean textureRegistered;

    private CastleTerritoryClientState() {
    }

    public static void apply(List<CastleMapPayload.Entry> castles, class_310 client) {
        if (client == null) {
            return;
        }
        client.execute(() -> CastleTerritoryClientState.rebuildIfNeeded(client, castles));
    }

    public static class_2960 getTexture() {
        return textureRegistered ? TERRITORY_TEXTURE : null;
    }

    public static void clear(class_310 client) {
        CastleTerritoryClientState.destroyRegisteredTexture(client);
        cachedRaster = null;
    }

    private static void rebuildIfNeeded(class_310 client, List<CastleMapPayload.Entry> castles) {
        TerritoryOwnershipRaster raster = TerritoryOwnershipClientCache.getOrBuild(client, castles);
        if (raster == null) {
            CastleTerritoryClientState.clear(client);
            return;
        }
        if (textureRegistered && cachedRaster == raster) {
            return;
        }
        CastleTerritoryClientState.destroyRegisteredTexture(client);
        class_1043 texture = new class_1043(raster.createCastleOverlay(112));
        try {
            client.method_1531().method_4616(TERRITORY_TEXTURE, (class_1044)texture);
            textureRegistered = true;
            cachedRaster = raster;
        }
        catch (RuntimeException exception) {
            texture.close();
            throw exception;
        }
    }

    private static void destroyRegisteredTexture(class_310 client) {
        class_310 activeClient;
        if (!textureRegistered) {
            return;
        }
        class_310 class_3102 = activeClient = client != null ? client : class_310.method_1551();
        if (activeClient != null) {
            activeClient.method_1531().method_4615(TERRITORY_TEXTURE);
        }
        textureRegistered = false;
    }
}

