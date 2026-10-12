/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_2338
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.client.map;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import org.jetbrains.annotations.Nullable;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.map.TerritoryOwnershipClientCache;
import trep.got.client.map.TerritoryOwnershipRaster;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.Kingdom;
import trep.got.network.CastleMapPayload;
import trep.got.registry.GotKingdoms;

@Environment(value=EnvType.CLIENT)
public final class KingdomAreaClientState {
    private static final int AREA_CHECK_INTERVAL_TICKS = 10;
    private static final int ENTRY_DISPLAY_TICKS = 80;
    private static final float TITLE_SCALE = 2.0f;
    private static final int ICON_SIZE = 18;
    private static final int TITLE_Y_OFFSET = 20;
    private static final Map<String, class_2960> SIGILS = Map.ofEntries(Map.entry("agob:the_north", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_north.png")), Map.entry("agob:the_nights_watch", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_nights_watch.png")), Map.entry("agob:wildling", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blank.png")), Map.entry("agob:the_vale", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_vale.png")), Map.entry("agob:the_riverlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_riverlands.png")), Map.entry("agob:the_westerlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_westerlands.png")), Map.entry("agob:iron_islands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_ironborn.png")), Map.entry("agob:the_reach", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_reach.png")), Map.entry("agob:the_stormlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_stormlands.png")), Map.entry("agob:dorne", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_dorne.png")), Map.entry("agob:the_crownlands", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_the_crownlands.png")), Map.entry("agob:targaryen", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_targaryen.png")), Map.entry("agob:braavos", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_braavos.png")), Map.entry("agob:pentos", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_pentos.png")), Map.entry("agob:volantis", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_volantis.png")), Map.entry("agob:tyrosh", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_tyrosh.png")), Map.entry("agob:myr", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_pahl.png")), Map.entry("agob:blackfyre", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_blackfyre.png")), Map.entry("agob:golden_company", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_goldencompany.png")), Map.entry("agob:meereen", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_meereen.png")), Map.entry("agob:yunkai", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_yunkai.png")), Map.entry("agob:astapor", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_astapor.png")), Map.entry("agob:qarth", class_2960.method_60655((String)"agob", (String)"textures/ui/sigil/sigil_qarth.png")));
    private static String currentKingdomId = "";
    private static String currentCastleId = "";
    private static AreaEntry activeEntry;
    private static int activeEntryTicks;
    private static int nextAreaCheckTick;

    private KingdomAreaClientState() {
    }

    public static void initialize() {
        TerritoryOwnershipClientCache.initialize();
        ClientTickEvents.END_CLIENT_TICK.register(KingdomAreaClientState::tick);
        HudRenderCallback.EVENT.register((context, tickDelta) -> KingdomAreaClientState.renderAnnouncement(context));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> KingdomAreaClientState.clear());
    }

    @Nullable
    public static AreaEntry getAreaAt(class_2338 pos) {
        class_310 client = class_310.method_1551();
        TerritoryOwnershipRaster ownership = TerritoryOwnershipClientCache.getOrBuild(client, CastleMapClientState.getCastles());
        if (ownership == null || pos == null) {
            return null;
        }
        CastleMapPayload.Entry castle = ownership.findAreaAt(pos);
        if (castle == null) {
            return null;
        }
        String kingdomId = castle.kingdomId();
        class_2960 parsedKingdomId = class_2960.method_12829((String)kingdomId);
        Kingdom kingdom = parsedKingdomId == null ? null : GotKingdoms.get(parsedKingdomId);
        String kingdomName = kingdom == null ? kingdomId : kingdom.getName();
        return new AreaEntry(kingdomId, kingdomName, castle.id(), castle.name(), castle.houseId());
    }

    @Nullable
    public static String getCurrentKingdomName() {
        AreaEntry entry = KingdomAreaClientState.getAreaAt(KingdomAreaClientState.currentPlayerPos());
        return entry == null ? null : entry.kingdomName();
    }

    @Nullable
    public static String getCurrentCastleName() {
        AreaEntry entry = KingdomAreaClientState.getAreaAt(KingdomAreaClientState.currentPlayerPos());
        return entry == null ? null : entry.castleName();
    }

    public static void clear() {
        TerritoryOwnershipClientCache.clear();
        currentKingdomId = "";
        currentCastleId = "";
        activeEntry = null;
        activeEntryTicks = 0;
        nextAreaCheckTick = 0;
    }

    private static void tick(class_310 client) {
        String castleId;
        if (client.field_1724 == null || client.field_1687 == null) {
            currentKingdomId = "";
            currentCastleId = "";
            if (activeEntryTicks > 0) {
                --activeEntryTicks;
            }
            return;
        }
        if (activeEntryTicks > 0) {
            --activeEntryTicks;
        }
        if (client.field_1724.field_6012 < nextAreaCheckTick) {
            return;
        }
        nextAreaCheckTick = client.field_1724.field_6012 + 10;
        AreaEntry entry = KingdomAreaClientState.getAreaAt(client.field_1724.method_24515());
        String kingdomId = entry == null ? "" : entry.kingdomId();
        String string = castleId = entry == null ? "" : entry.castleId();
        if (!kingdomId.equals(currentKingdomId)) {
            currentKingdomId = kingdomId;
            if (entry != null) {
                activeEntry = entry;
                activeEntryTicks = 80;
            }
        }
        currentCastleId = castleId;
    }

    private static void renderAnnouncement(class_332 context) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || client.field_1687 == null || activeEntry == null || activeEntryTicks <= 0) {
            return;
        }
        String kingdomName = activeEntry.kingdomName();
        class_2960 sigil = KingdomAreaClientState.sigilFor(activeEntry);
        int centerX = client.method_22683().method_4486() / 2;
        int y = Math.max(8, client.method_22683().method_4502() / 4 - 20);
        int textWidth = Math.round((float)client.field_1772.method_1727(kingdomName) * 2.0f);
        int totalWidth = textWidth + (sigil != null ? 24 : 0);
        int startX = centerX - totalWidth / 2;
        if (sigil != null) {
            KingdomAreaClientState.drawScaledFullTexture(context, sigil, startX, y + 1, 18, 18);
            startX += 24;
        }
        KingdomAreaClientState.drawScaledText(context, kingdomName, startX, y, 2.0f);
    }

    private static void drawScaledText(class_332 context, String text, int x, int y, float scale) {
        class_310 client = class_310.method_1551();
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)text), 0, 0, -1);
        context.method_51448().method_22909();
    }

    @Nullable
    private static class_2960 sigilFor(AreaEntry entry) {
        class_2960 houseTexture;
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(entry.kingdomId());
        if (dynamicKingdom != null && !dynamicKingdom.houseId().isBlank() && (houseTexture = PlayerHouseRenderHelper.resolveTexture(dynamicKingdom.houseId(), entry.kingdomId())) != null) {
            return houseTexture;
        }
        return SIGILS.get(entry.kingdomId());
    }

    private static void drawScaledFullTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        float scale = Math.min((float)drawWidth / (float)size.width(), (float)drawHeight / (float)size.height());
        int offsetX = Math.max(0, Math.round(((float)drawWidth - (float)size.width() * scale) / 2.0f));
        int offsetY = Math.max(0, Math.round(((float)drawHeight - (float)size.height() * scale) / 2.0f));
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)(x + offsetX), (float)(y + offsetY), 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    @Nullable
    private static class_2338 currentPlayerPos() {
        class_310 client = class_310.method_1551();
        return client.field_1724 == null ? null : client.field_1724.method_24515();
    }

    @Environment(value=EnvType.CLIENT)
    public record AreaEntry(String kingdomId, String kingdomName, String castleId, String castleName, String houseId) {
    }
}

