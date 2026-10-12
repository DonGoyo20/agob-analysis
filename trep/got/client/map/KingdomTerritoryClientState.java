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
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.map;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.jetbrains.annotations.Nullable;
import trep.got.client.map.TerritoryOwnershipClientCache;
import trep.got.client.map.TerritoryOwnershipRaster;
import trep.got.network.CastleMapPayload;

@Environment(value=EnvType.CLIENT)
public final class KingdomTerritoryClientState {
    private static final int OVERLAY_ALPHA = 210;
    private static TerritoryOwnershipRaster cachedRaster;
    private static String cachedKingdomId;
    private static class_2960 registeredTextureId;

    private KingdomTerritoryClientState() {
    }

    @Nullable
    public static class_2960 getTexture(List<CastleMapPayload.Entry> castles, String kingdomId, class_310 client) {
        if (client == null) {
            return null;
        }
        if (castles == null || castles.isEmpty() || kingdomId == null || kingdomId.isBlank()) {
            KingdomTerritoryClientState.clear(client);
            return null;
        }
        TerritoryOwnershipRaster raster = TerritoryOwnershipClientCache.getOrBuild(client, castles);
        if (raster == null) {
            KingdomTerritoryClientState.clear(client);
            return null;
        }
        if (registeredTextureId != null && raster == cachedRaster && kingdomId.equals(cachedKingdomId)) {
            return registeredTextureId;
        }
        KingdomTerritoryClientState.destroyRegisteredTexture(client);
        class_2960 textureId = class_2960.method_60655((String)"agob", (String)("dynamic/kingdom_territory/" + kingdomId.replace(':', '_')));
        class_1043 texture = new class_1043(raster.createKingdomOverlay(kingdomId, 210));
        try {
            client.method_1531().method_4616(textureId, (class_1044)texture);
            registeredTextureId = textureId;
            cachedKingdomId = kingdomId;
            cachedRaster = raster;
            return textureId;
        }
        catch (RuntimeException exception) {
            texture.close();
            throw exception;
        }
    }

    public static void clear(class_310 client) {
        KingdomTerritoryClientState.destroyRegisteredTexture(client);
        cachedRaster = null;
        cachedKingdomId = null;
    }

    private static void destroyRegisteredTexture(class_310 client) {
        class_310 activeClient;
        if (registeredTextureId == null) {
            return;
        }
        class_310 class_3102 = activeClient = client != null ? client : class_310.method_1551();
        if (activeClient != null) {
            activeClient.method_1531().method_4615(registeredTextureId);
        }
        registeredTextureId = null;
    }
}

