/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
 *  net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
 *  net.minecraft.class_1921
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_761
 */
package trep.got.client.claim;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.class_1921;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3675;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_761;
import trep.got.castle.CastleTier;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.house.PlayerHouseRenderHelper;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.util.ClientTextureHelper;
import trep.got.kingdom.Kingdom;
import trep.got.network.CastleMapPayload;
import trep.got.network.ClaimOutlinePayload;
import trep.got.registry.GotCharacterOptions;
import trep.got.registry.GotKingdoms;
import trep.got.registry.GotReligionOptions;
import trep.got.religion.ReligionSelectionOption;

@Environment(value=EnvType.CLIENT)
public final class ClaimOutlineClientState {
    private static final class_304 TOGGLE_KEY = KeyBindingHelper.registerKeyBinding((class_304)new class_304("key.agob.toggle_claim_outlines", class_3675.class_307.field_1668, 90, "category.agob"));
    private static final int ENTRY_DISPLAY_TICKS = 80;
    private static final int CLAIM_CHECK_INTERVAL_TICKS = 10;
    private static final float TITLE_SCALE = 1.75f;
    private static final float SUBLINE_SCALE = 0.875f;
    private static final float TIER_SCALE = 0.52500004f;
    private static final int SUBLINE_ICON_SIZE = 10;
    private static boolean enabled;
    private static List<ClaimOutlinePayload.Entry> claims;
    private static List<PreparedClaim> preparedClaims;
    private static Map<String, ClaimOutlinePayload.Entry> claimsById;
    private static Map<Long, ClaimOutlinePayload.Entry> claimsByChunk;
    private static String currentClaimId;
    private static ClaimOutlinePayload.Entry activeEntry;
    private static int activeEntryTicks;
    private static int nextClaimCheckTick;

    private ClaimOutlineClientState() {
    }

    public static void initialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_KEY.method_1436()) {
                enabled = !enabled;
            }
            ClaimOutlineClientState.updateClaimEntry(client);
        });
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ClaimOutlineClientState::render);
        HudRenderCallback.EVENT.register((context, tickDelta) -> ClaimOutlineClientState.renderClaimEntry(context));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClaimOutlineClientState.clear());
    }

    public static void apply(ClaimOutlinePayload payload, class_310 client) {
        client.execute(() -> {
            ClaimOutlineClientState.rebuildClaimCache(payload.claims());
            if (!currentClaimId.isBlank()) {
                activeEntry = claimsById.getOrDefault(currentClaimId, activeEntry);
            }
        });
    }

    public static void clear() {
        claims = List.of();
        preparedClaims = List.of();
        claimsById = Map.of();
        claimsByChunk = Map.of();
        enabled = false;
        currentClaimId = "";
        activeEntry = null;
        activeEntryTicks = 0;
        nextClaimCheckTick = 0;
    }

    public static List<ClaimOutlinePayload.Entry> getClaims() {
        return List.copyOf(claims);
    }

    public static ClaimOutlinePayload.Entry getClaimAt(class_2338 pos) {
        return ClaimOutlineClientState.findClaimAt(pos);
    }

    public static ClaimOutlinePayload.Entry getClaimById(String claimId) {
        if (claimId == null || claimId.isBlank()) {
            return null;
        }
        return claimsById.get(claimId);
    }

    private static void render(WorldRenderContext context) {
        class_310 client = class_310.method_1551();
        if (!enabled || client.field_1724 == null || client.field_1687 == null || claims.isEmpty()) {
            return;
        }
        class_4587 matrices = context.matrixStack();
        if (matrices == null) {
            return;
        }
        class_4597.class_4598 consumers = client.method_22940().method_23000();
        class_4588 vertexConsumer = consumers.getBuffer(class_1921.method_23594());
        class_2338 playerPos = client.field_1724.method_24515();
        matrices.method_22903();
        matrices.method_22904(-context.camera().method_19326().field_1352, -context.camera().method_19326().field_1351, -context.camera().method_19326().field_1350);
        for (PreparedClaim prepared : preparedClaims) {
            double dz;
            double dx = prepared.centerX() - (double)playerPos.method_10263();
            if (dx * dx + (dz = prepared.centerZ() - (double)playerPos.method_10260()) * dz > 262144.0) continue;
            class_238 box = new class_238((double)prepared.minX(), (double)client.field_1687.method_31607(), (double)prepared.minZ(), (double)prepared.maxX(), (double)client.field_1687.method_31600(), (double)prepared.maxZ());
            class_761.method_22982((class_4587)matrices, (class_4588)vertexConsumer, (class_238)box, (float)0.15f, (float)0.95f, (float)0.25f, (float)0.95f);
            for (PreparedStructure structure : prepared.structures()) {
                class_238 structureBox = new class_238((double)structure.minX(), (double)structure.minY(), (double)structure.minZ(), (double)(structure.maxX() + 1), (double)(structure.maxY() + 1), (double)(structure.maxZ() + 1));
                class_761.method_22982((class_4587)matrices, (class_4588)vertexConsumer, (class_238)structureBox, (float)0.95f, (float)0.72f, (float)0.18f, (float)0.95f);
            }
        }
        matrices.method_22909();
        consumers.method_22993();
    }

    private static void updateClaimEntry(class_310 client) {
        String claimId;
        if (client.field_1724 == null || client.field_1687 == null || claims.isEmpty()) {
            currentClaimId = "";
            if (activeEntryTicks > 0) {
                --activeEntryTicks;
            }
            return;
        }
        if (activeEntryTicks > 0) {
            --activeEntryTicks;
        }
        if (client.field_1724.field_6012 < nextClaimCheckTick) {
            return;
        }
        nextClaimCheckTick = client.field_1724.field_6012 + 10;
        class_2338 pos = client.field_1724.method_24515();
        ClaimOutlinePayload.Entry claim = ClaimOutlineClientState.findClaimAt(pos);
        String string = claimId = claim != null ? claim.id() : "";
        if (!claimId.equals(currentClaimId)) {
            currentClaimId = claimId;
            if (claim != null) {
                activeEntry = claim;
                activeEntryTicks = 80;
            }
        } else {
            activeEntry = claim;
        }
    }

    private static ClaimOutlinePayload.Entry findClaimAt(class_2338 pos) {
        return claimsByChunk.get(ClaimOutlineClientState.chunkKey(pos.method_10263() >> 4, pos.method_10260() >> 4));
    }

    private static void renderClaimEntry(class_332 context) {
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || client.field_1687 == null || activeEntry == null || activeEntryTicks <= 0) {
            return;
        }
        CharacterSelectionOption kingdomOption = ClaimOutlineClientState.resolveKingdomOption(activeEntry.kingdomId());
        class_2960 houseTexture = PlayerHouseRenderHelper.resolveTexture(activeEntry.houseId(), activeEntry.kingdomId());
        ReligionSelectionOption religionOption = ClaimOutlineClientState.resolveReligionOption(activeEntry.religionId());
        String kingdomLabel = ClaimOutlineClientState.kingdomDisplayName(activeEntry.kingdomId(), kingdomOption);
        String houseLabel = PlayerHouseRenderHelper.resolveDisplayName(activeEntry.houseId(), activeEntry.kingdomId());
        String religionLabel = religionOption != null ? religionOption.displayName() : activeEntry.religionId();
        String tierLabel = ClaimOutlineClientState.formatTier(activeEntry.tier());
        int centerX = client.method_22683().method_4486() / 2;
        int baseY = client.method_22683().method_4502() / 4;
        ClaimOutlineClientState.drawCenteredScaledText(context, activeEntry.name(), centerX, baseY, -1, 1.75f);
        int lineY = baseY + 26;
        if (!kingdomLabel.isBlank()) {
            ClaimOutlineClientState.drawCenteredLineWithIcon(context, ClaimOutlineClientState.kingdomIconFor(activeEntry, kingdomOption), kingdomLabel, centerX, lineY, 0.875f);
            lineY += 14;
        }
        if (!houseLabel.isBlank()) {
            ClaimOutlineClientState.drawCenteredLineWithIcon(context, houseTexture, houseLabel, centerX, lineY, 0.875f);
            lineY += 14;
        }
        if (!religionLabel.isBlank()) {
            ClaimOutlineClientState.drawCenteredLineWithIcon(context, religionOption != null ? religionOption.texture() : null, religionLabel, centerX, lineY, 0.875f);
            lineY += 16;
        }
        ClaimOutlineClientState.drawCenteredScaledText(context, tierLabel, centerX, lineY, -1, 0.52500004f);
    }

    private static void drawCenteredLineWithIcon(class_332 context, class_2960 texture, String text, int centerX, int y, float scale) {
        class_310 client = class_310.method_1551();
        int textWidth = client.field_1772.method_1727(text);
        int iconWidth = texture != null ? Math.round(10.0f * scale) : 0;
        int gap = texture != null ? 4 : 0;
        int totalWidth = iconWidth + gap + Math.round((float)textWidth * scale);
        int startX = centerX - totalWidth / 2;
        if (texture != null) {
            ClaimOutlineClientState.drawScaledFullTexture(context, texture, startX, y, iconWidth, iconWidth);
        }
        ClaimOutlineClientState.drawScaledText(context, text, startX + iconWidth + gap, y + 1, -1, scale);
    }

    private static void drawCenteredScaledText(class_332 context, String text, int centerX, int y, int color, float scale) {
        class_310 client = class_310.method_1551();
        int width = Math.round((float)client.field_1772.method_1727(text) * scale);
        ClaimOutlineClientState.drawScaledText(context, text, centerX - width / 2, y, color, scale);
    }

    private static void drawScaledText(class_332 context, String text, int x, int y, int color, float scale) {
        class_310 client = class_310.method_1551();
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905(scale, scale, 1.0f);
        context.method_27535(client.field_1772, (class_2561)class_2561.method_43470((String)text), 0, 0, color);
        context.method_51448().method_22909();
    }

    private static void drawScaledFullTexture(class_332 context, class_2960 texture, int x, int y, int drawWidth, int drawHeight) {
        ClientTextureHelper.TextureSize size = ClientTextureHelper.resolveTextureSize(class_310.method_1551(), texture);
        context.method_51448().method_22903();
        context.method_51448().method_46416((float)x, (float)y, 0.0f);
        context.method_51448().method_22905((float)drawWidth / (float)size.width(), (float)drawHeight / (float)size.height(), 1.0f);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        context.method_25291(texture, 0, 0, 0, 0.0f, 0.0f, size.width(), size.height(), size.width(), size.height());
        context.method_51448().method_22909();
    }

    private static CharacterSelectionOption resolveKingdomOption(String kingdomId) {
        class_2960 id = class_2960.method_12829((String)kingdomId);
        if (id == null) {
            return null;
        }
        return GotCharacterOptions.getByKingdomId(id);
    }

    private static class_2960 kingdomIconFor(ClaimOutlinePayload.Entry claim, CharacterSelectionOption fallbackOption) {
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(claim.kingdomId());
        if (dynamicKingdom != null) {
            class_2960 houseTexture;
            String houseId;
            String string = houseId = !claim.houseId().isBlank() ? claim.houseId() : dynamicKingdom.houseId();
            if (!houseId.isBlank() && (houseTexture = PlayerHouseRenderHelper.resolveTexture(houseId, claim.kingdomId())) != null) {
                return houseTexture;
            }
        }
        return fallbackOption == null ? null : fallbackOption.texture();
    }

    private static String kingdomDisplayName(String kingdomId, CharacterSelectionOption fallbackOption) {
        CastleMapPayload.DynamicKingdomEntry dynamicKingdom = CastleMapClientState.getDynamicKingdom(kingdomId);
        if (dynamicKingdom != null && !dynamicKingdom.displayName().isBlank()) {
            return dynamicKingdom.displayName();
        }
        if (fallbackOption != null && !fallbackOption.displayName().isBlank()) {
            return fallbackOption.displayName();
        }
        class_2960 id = class_2960.method_12829((String)kingdomId);
        Kingdom kingdom = id == null ? null : GotKingdoms.get(id);
        return kingdom == null || kingdom.getName().isBlank() ? kingdomId : kingdom.getName();
    }

    private static ReligionSelectionOption resolveReligionOption(String religionId) {
        return religionId == null || religionId.isBlank() ? null : GotReligionOptions.get(religionId);
    }

    private static String formatTier(String tier) {
        if ("village".equalsIgnoreCase(tier)) {
            return "Village";
        }
        if ("encampment".equalsIgnoreCase(tier)) {
            return "Encampment";
        }
        if (tier != null && !tier.isBlank()) {
            return CastleTier.byId(tier).getDisplayName();
        }
        return "Settlement";
    }

    private static void rebuildClaimCache(List<ClaimOutlinePayload.Entry> nextClaims) {
        claims = List.copyOf(nextClaims);
        ArrayList<PreparedClaim> nextPreparedClaims = new ArrayList<PreparedClaim>(claims.size());
        LinkedHashMap<String, ClaimOutlinePayload.Entry> nextClaimsById = new LinkedHashMap<String, ClaimOutlinePayload.Entry>();
        LinkedHashMap<Long, ClaimOutlinePayload.Entry> nextClaimsByChunk = new LinkedHashMap<Long, ClaimOutlinePayload.Entry>();
        for (ClaimOutlinePayload.Entry entry : claims) {
            PreparedClaim prepared = PreparedClaim.from(entry);
            nextPreparedClaims.add(prepared);
            nextClaimsById.put(entry.id(), entry);
            for (int chunkX = prepared.minChunkX(); chunkX <= prepared.maxChunkX(); ++chunkX) {
                for (int chunkZ = prepared.minChunkZ(); chunkZ <= prepared.maxChunkZ(); ++chunkZ) {
                    nextClaimsByChunk.putIfAbsent(ClaimOutlineClientState.chunkKey(chunkX, chunkZ), entry);
                }
            }
        }
        preparedClaims = List.copyOf(nextPreparedClaims);
        claimsById = Map.copyOf(nextClaimsById);
        claimsByChunk = Map.copyOf(nextClaimsByChunk);
    }

    private static long chunkKey(int chunkX, int chunkZ) {
        return (long)chunkX << 32 ^ (long)chunkZ & 0xFFFFFFFFL;
    }

    static {
        claims = List.of();
        preparedClaims = List.of();
        claimsById = Map.of();
        claimsByChunk = Map.of();
        currentClaimId = "";
    }

    @Environment(value=EnvType.CLIENT)
    private record PreparedClaim(int minChunkX, int maxChunkX, int minChunkZ, int maxChunkZ, int minX, int maxX, int minZ, int maxZ, double centerX, double centerZ, List<PreparedStructure> structures) {
        private static PreparedClaim from(ClaimOutlinePayload.Entry entry) {
            int centerChunkX = entry.bannerPos().method_10263() >> 4;
            int centerChunkZ = entry.bannerPos().method_10260() >> 4;
            int radius = entry.claimRadiusChunks();
            int minChunkX = centerChunkX - radius;
            int maxChunkX = centerChunkX + radius;
            int minChunkZ = centerChunkZ - radius;
            int maxChunkZ = centerChunkZ + radius;
            int minX = minChunkX << 4;
            int minZ = minChunkZ << 4;
            int maxX = maxChunkX + 1 << 4;
            int maxZ = maxChunkZ + 1 << 4;
            List<PreparedStructure> structures = entry.structures().stream().map(PreparedStructure::from).toList();
            return new PreparedClaim(minChunkX, maxChunkX, minChunkZ, maxChunkZ, minX, maxX, minZ, maxZ, (double)(minX + maxX) * 0.5, (double)(minZ + maxZ) * 0.5, List.copyOf(structures));
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record PreparedStructure(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        private static PreparedStructure from(ClaimOutlinePayload.StructureEntry entry) {
            return new PreparedStructure(Math.min(entry.minPos().method_10263(), entry.maxPos().method_10263()), Math.min(entry.minPos().method_10264(), entry.maxPos().method_10264()), Math.min(entry.minPos().method_10260(), entry.maxPos().method_10260()), Math.max(entry.minPos().method_10263(), entry.maxPos().method_10263()), Math.max(entry.minPos().method_10264(), entry.maxPos().method_10264()), Math.max(entry.minPos().method_10260(), entry.maxPos().method_10260()));
        }
    }
}

