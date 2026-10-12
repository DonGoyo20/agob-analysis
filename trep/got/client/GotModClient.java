/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
 *  net.minecraft.class_124
 *  net.minecraft.class_1297
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_1935
 *  net.minecraft.class_2248
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_3675$class_306
 *  net.minecraft.class_3675$class_307
 *  net.minecraft.class_3929
 *  net.minecraft.class_437
 *  net.minecraft.class_5272
 *  net.minecraft.class_5616
 *  net.minecraft.class_8710
 *  net.minecraft.class_9282
 *  net.minecraft.class_9307
 *  net.minecraft.class_9334
 *  org.lwjgl.glfw.GLFW
 */
package trep.got.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_3929;
import net.minecraft.class_437;
import net.minecraft.class_5272;
import net.minecraft.class_5616;
import net.minecraft.class_8710;
import net.minecraft.class_9282;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import org.lwjgl.glfw.GLFW;
import trep.got.block.entity.CupBlockEntity;
import trep.got.client.announcement.GotInvasionAnnouncementSettings;
import trep.got.client.audio.GotMusicSettings;
import trep.got.client.blacksmith.BlacksmithSmithingHud;
import trep.got.client.castle.CastleInvasionClientState;
import trep.got.client.character.CharacterSelectionClientState;
import trep.got.client.claim.ClaimOutlineClientState;
import trep.got.client.commander.CommanderClientState;
import trep.got.client.dialogue.DialogueSceneClientState;
import trep.got.client.dragon.DragonMountClientController;
import trep.got.client.entity.CartEntityRenderer;
import trep.got.client.entity.CatapultEntityRenderer;
import trep.got.client.entity.DeerEntityRenderer;
import trep.got.client.entity.DirewolfEntityRenderer;
import trep.got.client.entity.DragonEntityRenderer;
import trep.got.client.entity.ScorpionCrossbowEntityRenderer;
import trep.got.client.entity.ShipEntityRenderer;
import trep.got.client.entity.TentEntityRenderer;
import trep.got.client.entity.TwoHorseCartEntityRenderer;
import trep.got.client.gui.CharacterMenuClientState;
import trep.got.client.house.PlayerHouseClientState;
import trep.got.client.house.PlayerSigilTextureManager;
import trep.got.client.item.RangedWeaponTooltipHelper;
import trep.got.client.kingdom.KingdomEventLogClientState;
import trep.got.client.mainquest.MainQuestClientState;
import trep.got.client.map.CastleFastTravelClientState;
import trep.got.client.map.CastleMapClientState;
import trep.got.client.map.KingdomAreaClientState;
import trep.got.client.map.KingdomTerritoryClientState;
import trep.got.client.map.VillageMapClientState;
import trep.got.client.particle.DragonFireParticle;
import trep.got.client.physics.ClientPhysicsRegistry;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.client.registry.GotEntityRenderers;
import trep.got.client.registry.GotKeyBindings;
import trep.got.client.relations.KingdomRelationsClientState;
import trep.got.client.render.armor.HouseArmorRenderer;
import trep.got.client.render.block.BlacksmithTableSessionRenderer;
import trep.got.client.render.block.DragonCradleBlockEntityRenderer;
import trep.got.client.render.block.KitchenwareBlockEntityRenderer;
import trep.got.client.render.block.MarketDisplayBlockEntityRenderer;
import trep.got.client.screen.AgingBarrelScreen;
import trep.got.client.screen.BlacksmithFurnaceScreen;
import trep.got.client.screen.BrewerScreen;
import trep.got.client.screen.CastleScreen;
import trep.got.client.screen.DragonStatsScreen;
import trep.got.client.screen.GuidebookScreen;
import trep.got.client.screen.JournalScreen;
import trep.got.client.screen.KingdomCreationScreen;
import trep.got.client.screen.KingdomScreen;
import trep.got.client.screen.LockpickingScreen;
import trep.got.client.screen.MainHubScreen;
import trep.got.client.screen.MarketDisplayScreen;
import trep.got.client.screen.PlayerHouseSigilScreen;
import trep.got.client.screen.PotCookingScreen;
import trep.got.client.screen.VillageScreen;
import trep.got.client.screen.WorldMapScreen;
import trep.got.client.tailor.TailorClientState;
import trep.got.client.toast.ModAdvancementClient;
import trep.got.client.toast.WorldAnnouncementToastHud;
import trep.got.client.troop.TroopRosterClientState;
import trep.got.client.ui.GotClientUiSettings;
import trep.got.client.village.VillageRaidClientState;
import trep.got.client.voyage.VoyageClientState;
import trep.got.entity.ScorpionCrossbowEntity;
import trep.got.item.BowCombatHelper;
import trep.got.mixin.client.KeyBindingAccessor;
import trep.got.network.BanditCampNegotiationResultPayload;
import trep.got.network.BlacksmithSessionClearedPayload;
import trep.got.network.CastleInvasionStatePayload;
import trep.got.network.CastleMapPayload;
import trep.got.network.CharacterSelectionStatePayload;
import trep.got.network.CharacterSpawnSearchPayload;
import trep.got.network.ClaimOutlinePayload;
import trep.got.network.CloseNpcDialoguePayload;
import trep.got.network.FastTravelCooldownPayload;
import trep.got.network.KingdomEventLogPayload;
import trep.got.network.KingdomRelationsPayload;
import trep.got.network.MainQuestStatePayload;
import trep.got.network.ModAdvancementPayload;
import trep.got.network.NpcChatResultPayload;
import trep.got.network.NpcDialogueRelationPayload;
import trep.got.network.OpenCastleScreenPayload;
import trep.got.network.OpenCreateHouseScreenPayload;
import trep.got.network.OpenCreateKingdomScreenPayload;
import trep.got.network.OpenGateWarningPayload;
import trep.got.network.OpenLockpickingCaughtPayload;
import trep.got.network.OpenLockpickingPayload;
import trep.got.network.OpenNpcDialoguePayload;
import trep.got.network.OpenVillageScreenPayload;
import trep.got.network.PlayerHouseStatePayload;
import trep.got.network.QuestJournalStatePayload;
import trep.got.network.RequestScorpionCrossbowActionPayload;
import trep.got.network.SkillPointGainPayload;
import trep.got.network.TailorStatePayload;
import trep.got.network.TroopRosterPayload;
import trep.got.network.VillageMapPayload;
import trep.got.network.VillageRaidStatePayload;
import trep.got.network.VoyageStatePayload;
import trep.got.quest.QuestDebugLogBuffer;
import trep.got.quest.ShipmentQuestLogic;
import trep.got.registry.GotBlockEntities;
import trep.got.registry.GotBlocks;
import trep.got.registry.GotClientAccess;
import trep.got.registry.GotItems;
import trep.got.registry.GotReputationTitles;
import trep.got.registry.GotScreenHandlers;

@Environment(value=EnvType.CLIENT)
public class GotModClient
implements ClientModInitializer {
    private boolean previousScorpionFirePressed;

    public void onInitializeClient() {
        CharacterMenuClientState.initialize(class_310.method_1551());
        GotInvasionAnnouncementSettings.initialize(class_310.method_1551());
        GotMusicSettings.initialize(class_310.method_1551());
        GotClientUiSettings.initialize(class_310.method_1551());
        GotKeyBindings.initialize();
        CastleInvasionClientState.initialize();
        VillageRaidClientState.initialize();
        CommanderClientState.initialize();
        ClaimOutlineClientState.initialize();
        KingdomAreaClientState.initialize();
        DialogueSceneClientState.initialize();
        ClientPhysicsRegistry.initialize();
        DragonMountClientController.initialize();
        DragonFireParticle.initialize();
        BlacksmithSmithingHud.initialize();
        WorldAnnouncementToastHud.initialize();
        RangedWeaponTooltipHelper.initialize();
        HouseArmorRenderer.initialize();
        TentEntityRenderer.initialize();
        CartEntityRenderer.initialize();
        TwoHorseCartEntityRenderer.initialize();
        CatapultEntityRenderer.initialize();
        ScorpionCrossbowEntityRenderer.initialize();
        DragonEntityRenderer.initialize();
        ShipEntityRenderer.initialize();
        DeerEntityRenderer.initialize();
        DirewolfEntityRenderer.initialize();
        GotEntityRenderers.initialize();
        GotModClient.registerSpearModelPredicates();
        GotModClient.registerShieldModelPredicates();
        GotModClient.registerLongbowModelPredicates();
        GotReputationTitles.initialize();
        class_3929.method_17542(GotScreenHandlers.BREWER, BrewerScreen::new);
        class_3929.method_17542(GotScreenHandlers.AGING_BARREL, AgingBarrelScreen::new);
        class_3929.method_17542(GotScreenHandlers.BLACKSMITH_FURNACE, BlacksmithFurnaceScreen::new);
        class_3929.method_17542(GotScreenHandlers.DRAGON_STATS, DragonStatsScreen::new);
        class_3929.method_17542(GotScreenHandlers.MARKET_DISPLAY, MarketDisplayScreen::new);
        class_3929.method_17542(GotScreenHandlers.POT_COOKING, PotCookingScreen::new);
        class_5616.method_32144(GotBlockEntities.BLACKSMITH_TABLE, BlacksmithTableSessionRenderer::new);
        class_5616.method_32144(GotBlockEntities.DRAGON_CRADLE, DragonCradleBlockEntityRenderer::new);
        class_5616.method_32144(GotBlockEntities.MARKET_DISPLAY, MarketDisplayBlockEntityRenderer::new);
        class_5616.method_32144(GotBlockEntities.KITCHENWARE, KitchenwareBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.BLACKSMITH_TABLE, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.MARKET_DISPLAY, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.MARKET_DISPLAY_REINFORCED, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.DRAGON_EGG, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.DRAGON_CRADLE, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.FLAX_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.HOP_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.GRAPE_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.TOMATO_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.ORANGE_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.TURNIP_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.CHAMPIGNON_CROP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.WILD_TOMATO, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.WILD_ORANGE, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.WILD_TURNIP, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.WILD_CHAMPIGNON, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.CUP_BLOCK, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.SILVER_CUP_BLOCK, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.PLATE, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.CUTTING_BOARD, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.POT, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.PAN, class_1921.method_23581());
        BlockRenderLayerMap.INSTANCE.putBlock(GotBlocks.WEIRWOOD_LEAVES, class_1921.method_23581());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int n;
            if (tintIndex != 0 || world == null || pos == null) {
                return -1;
            }
            class_2586 patt0$temp = world.method_8321(pos);
            if (patt0$temp instanceof CupBlockEntity) {
                CupBlockEntity cup = (CupBlockEntity)patt0$temp;
                n = cup.getLiquidColor();
            } else {
                n = 8375295;
            }
            return n;
        }, (Object[])new class_2248[]{GotBlocks.CUP_BLOCK, GotBlocks.SILVER_CUP_BLOCK});
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? -8401921 : -1, (Object[])new class_1935[]{GotItems.QUEST_MARKER_ONE});
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? -727670 : -1, (Object[])new class_1935[]{GotItems.QUEST_MARKER_TWO});
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? -6756965 : -1, (Object[])new class_1935[]{GotItems.QUEST_MARKER_THREE});
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0 ? class_9282.method_57470((class_1799)stack, (int)-6265536) : -1, (Object[])new class_1935[]{GotItems.GAMBESON_HELMET, GotItems.GAMBESON_CHESTPLATE, GotItems.GAMBESON_LEGGINGS, GotItems.GAMBESON_BOOTS});
        ShipmentQuestLogic.setClientCastleTargetsProvider(world -> CastleMapClientState.getCastles().stream().map(castle -> new ShipmentQuestLogic.CastleTarget(castle.id(), castle.name(), castle.lordName(), castle.worldX(), castle.worldZ())).toList());
        GotClientAccess.registerWorldMapOpener(WorldMapScreen::open);
        GotClientAccess.registerJournalOpener(JournalScreen::open);
        GotClientAccess.registerGuidebookOpener(GuidebookScreen::open);
        ClientPlayNetworking.registerGlobalReceiver(CharacterSelectionStatePayload.ID, (payload, context) -> CharacterSelectionClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(CharacterSpawnSearchPayload.ID, (payload, context) -> CharacterSelectionClientState.setSpawnSearchInProgress(payload.searching(), context.client()));
        ClientPlayNetworking.registerGlobalReceiver(CastleMapPayload.ID, (payload, context) -> CastleMapClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(VillageMapPayload.ID, (payload, context) -> VillageMapClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(CastleInvasionStatePayload.ID, (payload, context) -> CastleInvasionClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(VillageRaidStatePayload.ID, (payload, context) -> VillageRaidClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(OpenCastleScreenPayload.ID, (payload, context) -> context.client().execute(() -> {
            WorldMapScreen map;
            class_437 patt0$temp = context.client().field_1755;
            CastleScreen.open(payload, patt0$temp instanceof WorldMapScreen ? (map = (WorldMapScreen)patt0$temp) : null);
        }));
        ClientPlayNetworking.registerGlobalReceiver(OpenCreateHouseScreenPayload.ID, (payload, context) -> context.client().execute(() -> PlayerHouseSigilScreen.open(-1, false)));
        ClientPlayNetworking.registerGlobalReceiver(OpenCreateKingdomScreenPayload.ID, (payload, context) -> context.client().execute(() -> KingdomCreationScreen.openStandalone(context.client().field_1755)));
        ClientPlayNetworking.registerGlobalReceiver(OpenVillageScreenPayload.ID, (payload, context) -> context.client().execute(() -> {
            WorldMapScreen map;
            class_437 patt0$temp = context.client().field_1755;
            VillageScreen.open(payload, patt0$temp instanceof WorldMapScreen ? (map = (WorldMapScreen)patt0$temp) : null);
        }));
        ClientPlayNetworking.registerGlobalReceiver(OpenLockpickingPayload.ID, (payload, context) -> LockpickingScreen.open(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(OpenLockpickingCaughtPayload.ID, (payload, context) -> LockpickingScreen.interruptForCaught(payload.ownerEntityId(), context.client()));
        ClientPlayNetworking.registerGlobalReceiver(OpenGateWarningPayload.ID, (payload, context) -> DialogueSceneClientState.openGateWarning(payload.guardEntityId(), context.client()));
        ClientPlayNetworking.registerGlobalReceiver(ClaimOutlinePayload.ID, (payload, context) -> ClaimOutlineClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(BlacksmithSessionClearedPayload.ID, (payload, context) -> context.client().execute(() -> BlacksmithSmithingHud.markFinished(payload.furnaceOrigin(), payload.reason())));
        ClientPlayNetworking.registerGlobalReceiver(FastTravelCooldownPayload.ID, (payload, context) -> CastleFastTravelClientState.applyCooldown(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(KingdomEventLogPayload.ID, (payload, context) -> KingdomEventLogClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(KingdomRelationsPayload.ID, (payload, context) -> KingdomRelationsClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(MainQuestStatePayload.ID, (payload, context) -> MainQuestClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(QuestJournalStatePayload.ID, (payload, context) -> QuestJournalClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(ModAdvancementPayload.ID, (payload, context) -> ModAdvancementClient.show(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(SkillPointGainPayload.ID, (payload, context) -> CharacterSelectionClientState.applySkillPointGain(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(TailorStatePayload.ID, (payload, context) -> TailorClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(TroopRosterPayload.ID, (payload, context) -> context.client().execute(() -> {
            TroopRosterClientState.apply(payload);
            DialogueSceneClientState.onTroopRosterUpdated(context.client());
        }));
        ClientPlayNetworking.registerGlobalReceiver(VoyageStatePayload.ID, (payload, context) -> VoyageClientState.apply(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(OpenNpcDialoguePayload.ID, (payload, context) -> DialogueSceneClientState.openFromServer(payload.targetEntityId(), payload.attributes(), context.client()));
        ClientPlayNetworking.registerGlobalReceiver(CloseNpcDialoguePayload.ID, (payload, context) -> DialogueSceneClientState.closeFromServer(payload.targetEntityId(), context.client()));
        ClientPlayNetworking.registerGlobalReceiver(NpcChatResultPayload.ID, (payload, context) -> DialogueSceneClientState.applyNpcChatResult(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(NpcDialogueRelationPayload.ID, (payload, context) -> DialogueSceneClientState.applyNpcRelation(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(BanditCampNegotiationResultPayload.ID, (payload, context) -> DialogueSceneClientState.applyBanditCampNegotiationResult(payload, context.client()));
        ClientPlayNetworking.registerGlobalReceiver(PlayerHouseStatePayload.ID, (payload, context) -> PlayerHouseClientState.apply(payload, context.client()));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ScorpionCrossbowEntity scorpionCrossbow;
            class_1297 patt2$temp;
            class_1297 patt1$temp;
            ScorpionCrossbowEntity scorpionCrossbow2;
            class_1297 patt0$temp;
            while (GotKeyBindings.MAIN_MENU.method_1436()) {
                if (!GotClientUiSettings.isDefaultMainModMenuEnabled()) continue;
                if (client.field_1755 == null) {
                    MainHubScreen.open();
                    continue;
                }
                if (!(client.field_1755 instanceof MainHubScreen)) continue;
                client.method_1507(null);
            }
            while (GotKeyBindings.KINGDOM_SCREEN.method_1436()) {
                if (client.field_1755 == null) {
                    KingdomScreen.open();
                    continue;
                }
                if (!(client.field_1755 instanceof KingdomScreen)) continue;
                client.method_1507(null);
            }
            if (client.field_1724 != null && client.field_1755 == null && (patt0$temp = client.field_1724.method_5854()) instanceof ScorpionCrossbowEntity) {
                scorpionCrossbow2 = (ScorpionCrossbowEntity)patt0$temp;
                boolean sentFireAction = false;
                while (GotKeyBindings.DRAGON_DRACARYS.method_1436()) {
                    ClientPlayNetworking.send((class_8710)new RequestScorpionCrossbowActionPayload(scorpionCrossbow2.method_5628()));
                    sentFireAction = true;
                }
                boolean firePressed = GotModClient.isHeld(client, GotKeyBindings.DRAGON_DRACARYS);
                if (!sentFireAction && firePressed && !this.previousScorpionFirePressed) {
                    ClientPlayNetworking.send((class_8710)new RequestScorpionCrossbowActionPayload(scorpionCrossbow2.method_5628()));
                }
                this.previousScorpionFirePressed = firePressed;
            } else {
                this.previousScorpionFirePressed = false;
            }
            CharacterSelectionClientState.enforceScreen(client);
            if (client.field_1724 != null && (patt1$temp = client.field_1724.method_5854()) instanceof ScorpionCrossbowEntity && !(scorpionCrossbow2 = (ScorpionCrossbowEntity)patt1$temp).isBusy() && client.field_1755 == null) {
                client.field_1705.method_1758((class_2561)class_2561.method_43470((String)"Press \"").method_10852(GotKeyBindings.DRAGON_DRACARYS.method_16007()).method_27693(scorpionCrossbow2.isLoaded() ? "\" to Fire:" : "\" to Reload:").method_27692(class_124.field_1068), false);
            } else if (client.field_1724 != null && (patt2$temp = client.field_1724.method_5854()) instanceof ScorpionCrossbowEntity && (scorpionCrossbow = (ScorpionCrossbowEntity)patt2$temp).isReloading() && client.field_1755 == null) {
                client.field_1705.method_1758((class_2561)class_2561.method_43470((String)"Reloading...").method_27692(class_124.field_1068), false);
            }
            CastleFastTravelClientState.tick(client);
            VoyageClientState.tick(client);
        });
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            QuestDebugLogBuffer.flushNow();
            CharacterSelectionClientState.clear();
            CastleInvasionClientState.clear();
            VillageRaidClientState.clear();
            CastleFastTravelClientState.clear(client);
            CastleMapClientState.clear();
            VillageMapClientState.clear();
            KingdomTerritoryClientState.clear(client);
            KingdomEventLogClientState.clear();
            KingdomRelationsClientState.clear();
            ClaimOutlineClientState.clear();
            MainQuestClientState.clear();
            QuestJournalClientState.clear();
            TroopRosterClientState.clear();
            VoyageClientState.clear();
            PlayerHouseClientState.clear();
            PlayerSigilTextureManager.clear();
        });
    }

    private static boolean isHeld(class_310 client, class_304 binding) {
        if (binding.method_1415()) {
            return false;
        }
        class_3675.class_306 boundKey = ((KeyBindingAccessor)binding).agob$getBoundKey();
        long windowHandle = client.method_22683().method_4490();
        return switch (boundKey.method_1442()) {
            default -> throw new MatchException(null, null);
            case class_3675.class_307.field_1668 -> class_3675.method_15987((long)windowHandle, (int)boundKey.method_1444());
            case class_3675.class_307.field_1671 -> {
                if (GLFW.glfwGetKey((long)windowHandle, (int)boundKey.method_1444()) == 1) {
                    yield true;
                }
                yield false;
            }
            case class_3675.class_307.field_1672 -> GLFW.glfwGetMouseButton((long)windowHandle, (int)boundKey.method_1444()) == 1;
        };
    }

    private static void registerSpearModelPredicates() {
        class_2960 id = class_2960.method_60655((String)"agob", (String)"in_gui");
        class_5272.method_27879((class_1792)GotItems.WOODEN_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.STONE_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.COPPER_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.GOLDEN_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.IRON_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.DIAMOND_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.WOODEN_HALBERD, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.STONE_HALBERD, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.COPPER_HALBERD, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.IRON_HALBERD, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.STEEL_HALBERD, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
        class_5272.method_27879((class_1792)GotItems.NETHERITE_SPEAR, (class_2960)id, (stack, world, entity, seed) -> stack.method_27319() != null && stack.method_7945() == null ? 0.0f : 1.0f);
    }

    private static void registerShieldModelPredicates() {
        class_2960 id = class_2960.method_60655((String)"agob", (String)"decorated");
        class_5272.method_27879((class_1792)GotItems.ROUND_SHIELD, (class_2960)id, (stack, world, entity, seed) -> GotModClient.isDecoratedShield(stack) ? 1.0f : 0.0f);
        class_5272.method_27879((class_1792)GotItems.SQUARE_SHIELD, (class_2960)id, (stack, world, entity, seed) -> GotModClient.isDecoratedShield(stack) ? 1.0f : 0.0f);
        class_5272.method_27879((class_1792)GotItems.KITE_SHIELD, (class_2960)id, (stack, world, entity, seed) -> GotModClient.isDecoratedShield(stack) ? 1.0f : 0.0f);
    }

    private static void registerLongbowModelPredicates() {
        class_5272.method_27879((class_1792)GotItems.LONGBOW, (class_2960)class_2960.method_60656((String)"pull"), (stack, world, entity, seed) -> {
            if (entity == null || entity.method_6030() != stack) {
                return 0.0f;
            }
            return BowCombatHelper.getPullProgress(stack, stack.method_7935(entity) - entity.method_6014());
        });
        class_5272.method_27879((class_1792)GotItems.LONGBOW, (class_2960)class_2960.method_60656((String)"pulling"), (stack, world, entity, seed) -> entity != null && entity.method_6115() && entity.method_6030() == stack ? 1.0f : 0.0f);
    }

    private static boolean isDecoratedShield(class_1799 stack) {
        class_9307 patterns = (class_9307)stack.method_57825(class_9334.field_49619, (Object)class_9307.field_49404);
        return stack.method_57824(class_9334.field_49620) != null || !patterns.comp_2428().isEmpty();
    }
}

