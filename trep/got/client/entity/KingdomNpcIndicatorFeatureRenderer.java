/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3883
 *  net.minecraft.class_3887
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_591
 *  net.minecraft.class_638
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  net.minecraft.class_898
 *  net.minecraft.class_918
 */
package trep.got.client.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_591;
import net.minecraft.class_638;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_898;
import net.minecraft.class_918;
import trep.got.client.quest.QuestJournalClientState;
import trep.got.entity.AbstractDialogueHumanoidEntity;
import trep.got.entity.AbstractDynastyNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.SettlerNpcEntity;
import trep.got.quest.QuestInstance;
import trep.got.quest.QuestTemplateRegistry;
import trep.got.registry.GotDynastyNpcs;
import trep.got.registry.GotItems;

@Environment(value=EnvType.CLIENT)
public final class KingdomNpcIndicatorFeatureRenderer<T extends AbstractDialogueHumanoidEntity>
extends class_3887<T, class_591<T>> {
    private static final class_2960 MARRIAGE_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/wedding.png");
    private static final class_2960 DNA_ICON = class_2960.method_60655((String)"agob", (String)"textures/ui/dna.png");
    private static final long QUEST_PREVIEW_CACHE_WINDOW_TICKS = 10L;
    private static final Map<UUID, QuestPreviewCacheEntry> QUEST_PREVIEW_CACHE = new HashMap<UUID, QuestPreviewCacheEntry>();
    private final class_918 itemRenderer;

    public KingdomNpcIndicatorFeatureRenderer(class_3883<T, class_591<T>> context, class_918 itemRenderer, class_898 renderDispatcher) {
        super(context);
        this.itemRenderer = itemRenderer;
    }

    public void render(class_4587 matrices, class_4597 vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        List<IndicatorRenderData> indicators = this.resolveIndicators(entity);
        if (indicators.isEmpty()) {
            return;
        }
        float spin = ((float)((AbstractDialogueHumanoidEntity)entity).field_6012 + tickDelta) * 4.0f;
        matrices.method_22903();
        matrices.method_22904(0.0, (double)entity.method_17682() - 2.9, 0.0);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(180.0f));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(16.0f));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(spin));
        matrices.method_22905(0.75f, 0.75f, 0.75f);
        float spacing = 0.7f;
        float startX = -((float)(indicators.size() - 1) * spacing) / 2.0f;
        for (int index = 0; index < indicators.size(); ++index) {
            matrices.method_22903();
            matrices.method_22904((double)(startX + (float)index * spacing), 0.0, 0.0);
            IndicatorRenderData indicator = indicators.get(index);
            if (indicator.itemStack() != null) {
                this.itemRenderer.method_23178(indicator.itemStack(), class_811.field_4319, light, class_4608.field_21444, matrices, vertexConsumers, entity.method_37908(), entity.method_5628() + index);
            } else if (indicator.texture() != null) {
                this.renderTextureIndicator(matrices, vertexConsumers, light, indicator.texture());
            }
            matrices.method_22909();
        }
        matrices.method_22909();
    }

    private List<IndicatorRenderData> resolveIndicators(T entity) {
        int activeQuestIndex;
        class_1937 class_19372;
        class_1799 professionIndicator;
        SettlerNpcEntity settler;
        class_1799 roleIndicator;
        AbstractKingdomNpcEntity kingdomNpcForRole;
        ArrayList<IndicatorRenderData> indicators = new ArrayList<IndicatorRenderData>();
        boolean hideRoleIndicators = entity instanceof AbstractKingdomNpcEntity && (kingdomNpcForRole = (AbstractKingdomNpcEntity)entity).isNobleSpouse();
        class_1799 class_17992 = roleIndicator = hideRoleIndicators ? class_1799.field_8037 : ((AbstractDialogueHumanoidEntity)entity).getIndicatorStack();
        if (!roleIndicator.method_7960()) {
            indicators.add(IndicatorRenderData.item(roleIndicator));
        }
        if (entity instanceof SettlerNpcEntity && !(settler = (SettlerNpcEntity)entity).isIndependentMarriageNpc() && !(professionIndicator = settler.getProfessionIndicatorStack()).method_7960()) {
            indicators.add(IndicatorRenderData.item(professionIndicator));
        }
        class_310 client = class_310.method_1551();
        if (client.field_1724 == null || !((class_19372 = entity.method_37908()) instanceof class_638)) {
            return indicators;
        }
        class_638 world = (class_638)class_19372;
        if (!(entity instanceof AbstractKingdomNpcEntity)) {
            return indicators;
        }
        AbstractKingdomNpcEntity kingdomNpc = (AbstractKingdomNpcEntity)entity;
        if (client.field_1724.method_5845().equals(kingdomNpc.getMarriedPlayerUuid())) {
            indicators.add(0, IndicatorRenderData.texture(MARRIAGE_ICON));
        }
        if (kingdomNpc instanceof AbstractDynastyNpcEntity) {
            AbstractDynastyNpcEntity dynastyNpc = (AbstractDynastyNpcEntity)kingdomNpc;
            if ((GotDynastyNpcs.isChildNpc(kingdomNpc) || GotDynastyNpcs.isDynastyNpc(kingdomNpc)) && client.field_1724.method_5845().equals(dynastyNpc.getOwnerPlayerUuid())) {
                indicators.add(0, IndicatorRenderData.texture(DNA_ICON));
            }
        }
        if ((activeQuestIndex = QuestJournalClientState.findQuestIndexByNpc(kingdomNpc)) >= 0) {
            indicators.add(IndicatorRenderData.item(QuestJournalClientState.getQuestMarkerStack(activeQuestIndex)));
            return indicators;
        }
        if (KingdomNpcIndicatorFeatureRenderer.shouldShowQuestOfferIndicator(client, world, kingdomNpc)) {
            indicators.add(IndicatorRenderData.item(new class_1799((class_1935)GotItems.GUIDEBOOK)));
        }
        return indicators;
    }

    private static boolean shouldShowQuestOfferIndicator(class_310 client, class_638 world, AbstractKingdomNpcEntity kingdomNpc) {
        if (QuestJournalClientState.getNextQuestSlotIndex() < 0) {
            return false;
        }
        UUID npcUuid = kingdomNpc.method_5667();
        UUID playerUuid = client.field_1724.method_5667();
        long worldTime = world.method_8510();
        long cacheWindow = worldTime / 10L;
        int journalVersion = QuestJournalClientState.getVersion();
        QuestPreviewCacheEntry cached = QUEST_PREVIEW_CACHE.get(npcUuid);
        if (cached != null && cached.playerUuid().equals(playerUuid) && cached.offerCycle() == QuestJournalClientState.getOfferCycle() && cached.cacheWindow() == cacheWindow && cached.journalVersion() == journalVersion) {
            return cached.showIndicator();
        }
        QuestInstance preview = QuestTemplateRegistry.previewForPlayer(playerUuid, kingdomNpc, QuestJournalClientState.getOfferCycle(), worldTime);
        boolean showIndicator = preview != null && !QuestJournalClientState.hasCompletedOffer(preview.offerKey()) && !QuestJournalClientState.isNpcOnCooldown(npcUuid, worldTime) && !QuestJournalClientState.isTemplateNpcOnCooldown(npcUuid, preview.templateId(), worldTime);
        QUEST_PREVIEW_CACHE.put(npcUuid, new QuestPreviewCacheEntry(playerUuid, QuestJournalClientState.getOfferCycle(), cacheWindow, journalVersion, showIndicator));
        return showIndicator;
    }

    private void renderTextureIndicator(class_4587 matrices, class_4597 vertexConsumers, int light, class_2960 texture) {
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)texture));
        class_4587.class_4665 entry = matrices.method_23760();
        float min = -0.3f;
        float max = 0.3f;
        consumer.method_22918(entry.method_23761(), min, max, 0.0f).method_1336(255, 255, 255, 255).method_22913(0.0f, 0.0f).method_22922(class_4608.field_21444).method_60803(light).method_60831(entry, 0.0f, 0.0f, 1.0f);
        consumer.method_22918(entry.method_23761(), min, min, 0.0f).method_1336(255, 255, 255, 255).method_22913(0.0f, 1.0f).method_22922(class_4608.field_21444).method_60803(light).method_60831(entry, 0.0f, 0.0f, 1.0f);
        consumer.method_22918(entry.method_23761(), max, min, 0.0f).method_1336(255, 255, 255, 255).method_22913(1.0f, 1.0f).method_22922(class_4608.field_21444).method_60803(light).method_60831(entry, 0.0f, 0.0f, 1.0f);
        consumer.method_22918(entry.method_23761(), max, max, 0.0f).method_1336(255, 255, 255, 255).method_22913(1.0f, 0.0f).method_22922(class_4608.field_21444).method_60803(light).method_60831(entry, 0.0f, 0.0f, 1.0f);
    }

    @Environment(value=EnvType.CLIENT)
    private record IndicatorRenderData(class_1799 itemStack, class_2960 texture) {
        private static IndicatorRenderData item(class_1799 itemStack) {
            return new IndicatorRenderData(itemStack, null);
        }

        private static IndicatorRenderData texture(class_2960 texture) {
            return new IndicatorRenderData(null, texture);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private record QuestPreviewCacheEntry(UUID playerUuid, long offerCycle, long cacheWindow, int journalVersion, boolean showIndicator) {
    }
}

