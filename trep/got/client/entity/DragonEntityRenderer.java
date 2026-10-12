/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
 *  net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl
 *  net.minecraft.class_1058
 *  net.minecraft.class_1059
 *  net.minecraft.class_1092
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1738
 *  net.minecraft.class_1741
 *  net.minecraft.class_1741$class_9196
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1921
 *  net.minecraft.class_2350
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_3489
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_4722
 *  net.minecraft.class_5253$class_5254
 *  net.minecraft.class_5601
 *  net.minecraft.class_5602
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_572
 *  net.minecraft.class_591
 *  net.minecraft.class_6880
 *  net.minecraft.class_742
 *  net.minecraft.class_7833
 *  net.minecraft.class_8053
 *  net.minecraft.class_8056
 *  net.minecraft.class_8685$class_7920
 *  net.minecraft.class_897
 *  net.minecraft.class_9282
 *  net.minecraft.class_9334
 *  org.joml.Vector3f
 */
package trep.got.client.entity;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.class_1058;
import net.minecraft.class_1059;
import net.minecraft.class_1092;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1738;
import net.minecraft.class_1741;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3489;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_4722;
import net.minecraft.class_5253;
import net.minecraft.class_5601;
import net.minecraft.class_5602;
import net.minecraft.class_5617;
import net.minecraft.class_572;
import net.minecraft.class_591;
import net.minecraft.class_6880;
import net.minecraft.class_742;
import net.minecraft.class_7833;
import net.minecraft.class_8053;
import net.minecraft.class_8056;
import net.minecraft.class_8685;
import net.minecraft.class_897;
import net.minecraft.class_9282;
import net.minecraft.class_9334;
import org.joml.Vector3f;
import trep.got.GotMod;
import trep.got.client.entity.DragonMountedPlayerModel;
import trep.got.client.entity.EntityRenderGeometry;
import trep.got.entity.DragonEntity;
import trep.got.entity.DragonModelData;
import trep.got.entity.DragonModelType;
import trep.got.entity.dragon.DragonOrigin;

@Environment(value=EnvType.CLIENT)
public final class DragonEntityRenderer
extends class_897<DragonEntity> {
    private static final class_2960 DEFAULT_WESTEROSI_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/red.png");
    private static final class_2960 DEFAULT_VALYRIAN_TEXTURE = class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/red.png");
    private static final class_5601 MOUNTED_PLAYER_MODEL_LAYER = new class_5601(class_2960.method_60655((String)"agob", (String)"dragon_mounted_player"), "main");
    private static final String HORIZONTALLY_FLIPPED_UV_CUBE = "wing_left_membrane_2";
    private static final float MOUNTED_PLAYER_CONTEXT_SCALE = 2.0f;
    private static final float MOUNTED_PLAYER_FORWARD_OFFSET = 1.5f;
    private static final float MOUNTED_PLAYER_HEAD_SCALE = 1.5f;
    private static final float MOUNTED_PLAYER_HEAD_BACK_OFFSET = 0.5f;
    private static final Vector3f MODEL_ORIGIN = new Vector3f();
    private static final class_1304[] MOUNTED_ARMOR_SLOTS = new class_1304[]{class_1304.field_6174, class_1304.field_6172, class_1304.field_6166, class_1304.field_6169};
    private static final Set<String> LOGGED_MOUNTED_VISUALS = ConcurrentHashMap.newKeySet();
    private static final Map<String, class_2960> WESTEROSI_VARIANT_TEXTURES = Map.ofEntries(Map.entry("black", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/black.png")), Map.entry("blue", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/blue.png")), Map.entry("gray", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/gray.png")), Map.entry("green", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/green.png")), Map.entry("light_red", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/light_red.png")), Map.entry("orange", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/orange.png")), Map.entry("red", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/red.png")), Map.entry("yellow", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/yellow.png")), Map.entry("bloody", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/old/dragons_bloody.png")), Map.entry("dark", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/old/dragons_dark.png")), Map.entry("gold", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/old/dragons_gold.png")), Map.entry("snowy", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/old/dragons_snowy.png")), Map.entry("white", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_westerosi/white.png")));
    private static final Map<String, class_2960> VALYRIAN_VARIANT_TEXTURES = Map.ofEntries(Map.entry("black", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/black.png")), Map.entry("blue", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/blue.png")), Map.entry("gray", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/gray.png")), Map.entry("green", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/green.png")), Map.entry("light_red", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/light_red.png")), Map.entry("orange", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/orange.png")), Map.entry("red", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/red.png")), Map.entry("yellow", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/yellow.png")), Map.entry("white", class_2960.method_60655((String)"agob", (String)"textures/entity/dragon_valyria/white.png")));
    private final DragonMountedPlayerModel mountedPlayerModel;
    private final class_591<class_742> wideRiderModel;
    private final class_591<class_742> slimRiderModel;
    private final class_572<class_742> wideArmorContextModel;
    private final class_572<class_742> slimArmorContextModel;
    private final class_572<class_742> wideInnerArmorModel;
    private final class_572<class_742> wideOuterArmorModel;
    private final class_572<class_742> slimInnerArmorModel;
    private final class_572<class_742> slimOuterArmorModel;
    private final class_1059 armorTrimsAtlas;

    public DragonEntityRenderer(class_5617.class_5618 context) {
        super(context);
        this.mountedPlayerModel = new DragonMountedPlayerModel(context.method_32167(MOUNTED_PLAYER_MODEL_LAYER));
        this.wideRiderModel = new class_591(context.method_32167(class_5602.field_27577), false);
        this.slimRiderModel = new class_591(context.method_32167(class_5602.field_27581), true);
        this.wideArmorContextModel = new class_572(context.method_32167(class_5602.field_27577));
        this.slimArmorContextModel = new class_572(context.method_32167(class_5602.field_27581));
        this.wideInnerArmorModel = new class_572(context.method_32167(class_5602.field_27579));
        this.wideOuterArmorModel = new class_572(context.method_32167(class_5602.field_27580));
        this.slimInnerArmorModel = new class_572(context.method_32167(class_5602.field_27582));
        this.slimOuterArmorModel = new class_572(context.method_32167(class_5602.field_27583));
        class_1092 modelManager = context.method_48481();
        this.armorTrimsAtlas = modelManager.method_24153(class_4722.field_42071);
        this.field_4673 = 2.8f;
    }

    public static void initialize() {
        EntityModelLayerRegistry.registerModelLayer((class_5601)MOUNTED_PLAYER_MODEL_LAYER, DragonMountedPlayerModel::getTexturedModelData);
        for (DragonModelType type : DragonModelType.values()) {
            DragonModelData.get(type);
        }
    }

    public void render(DragonEntity entity, float yaw, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light) {
        DragonModelData model = DragonModelData.get(entity);
        float dragonScale = entity.getModelScale();
        this.field_4673 = 2.8f * dragonScale;
        matrices.method_22903();
        class_1309 rider = entity.method_5642();
        if (entity.isFlightActive() && rider != null) {
            class_243 correction = entity.getMountedFlightRenderRootPos((class_1297)rider, tickDelta).method_1020(entity.method_30950(tickDelta));
            matrices.method_22904(correction.field_1352, correction.field_1351, correction.field_1350);
        }
        float renderYaw = class_3532.method_17821((float)tickDelta, (float)entity.field_5982, (float)entity.method_36454());
        float renderPitch = class_3532.method_16439((float)tickDelta, (float)entity.field_6004, (float)entity.method_36455());
        float renderRoll = entity.getVisualFlightRoll(tickDelta);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(180.0f - renderYaw));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(-renderPitch));
        matrices.method_22907(class_7833.field_40718.rotationDegrees(renderRoll));
        matrices.method_46416(0.0f, model.groundOffset() * dragonScale, 0.0f);
        matrices.method_22905(dragonScale, dragonScale, dragonScale);
        class_4588 consumer = vertexConsumers.getBuffer(class_1921.method_23578((class_2960)this.getTexture(entity)));
        RenderState state = new RenderState(entity.getWalkAnimationTime(tickDelta), entity.getFlyAnimationTime(tickDelta), entity.getDiveAnimationTime(tickDelta), entity.getIdleAnimationTime(tickDelta), entity.getWalkAnimationWeight(), entity.getFlyAnimationWeight(), entity.getDiveAnimationWeight(), entity.getFireBreathAnimationProgress(tickDelta));
        DragonEntityRenderer.renderGroup(model, model.root(), matrices, consumer, light, class_4608.field_21444, MODEL_ORIGIN, state, tickDelta);
        if (rider instanceof class_742) {
            class_742 player = (class_742)rider;
            this.renderMountedPlayerProxy(model, entity, player, dragonScale, matrices, vertexConsumers, light, state, tickDelta);
        }
        matrices.method_22909();
        super.method_3936((class_1297)entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private void renderMountedPlayerProxy(DragonModelData model, DragonEntity dragon, class_742 rider, float dragonScale, class_4587 matrices, class_4597 vertexConsumers, int light, RenderState state, float tickDelta) {
        String logKey = dragon.method_5628() + ":" + rider.method_5628();
        if (LOGGED_MOUNTED_VISUALS.add(logKey)) {
            GotMod.LOGGER.info("Dragon mounted rider visual active dragonId={} rider={} source=dragon_model_player_context", (Object)dragon.method_5628(), (Object)rider.method_5477().getString());
        }
        this.renderMountedPlayerContext(model, model.root(), rider, dragonScale, matrices, vertexConsumers, light, MODEL_ORIGIN, state, tickDelta);
    }

    private boolean renderMountedPlayerContext(DragonModelData model, DragonModelData.GroupNode group, class_742 rider, float dragonScale, class_4587 matrices, class_4597 vertexConsumers, int light, Vector3f parentOrigin, RenderState state, float tickDelta) {
        matrices.method_22903();
        DragonModelData.PoseSample pose = DragonEntityRenderer.resolvePose(model, group, state);
        matrices.method_46416((pose.position().x() - parentOrigin.x()) / 16.0f, (pose.position().y() - parentOrigin.y()) / 16.0f, (pose.position().z() - parentOrigin.z()) / 16.0f);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(pose.rotation().z()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(pose.rotation().y()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(pose.rotation().x()));
        if ("player".equals(group.name())) {
            float inverseDragonScale = dragonScale == 0.0f ? 1.0f : 1.0f / dragonScale;
            matrices.method_22905(inverseDragonScale, inverseDragonScale, inverseDragonScale);
            this.renderMountedPlayerAtContext(rider, matrices, vertexConsumers, light);
            matrices.method_22909();
            return true;
        }
        for (DragonModelData.RenderNode child : group.children()) {
            DragonModelData.GroupNode childGroup;
            if (!(child instanceof DragonModelData.GroupNode) || !this.renderMountedPlayerContext(model, childGroup = (DragonModelData.GroupNode)child, rider, dragonScale, matrices, vertexConsumers, light, pose.position(), state, tickDelta)) continue;
            matrices.method_22909();
            return true;
        }
        matrices.method_22909();
        return false;
    }

    private void renderMountedPlayerAtContext(class_742 rider, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        matrices.method_46416(0.0f, 1.5f, 0.0f);
        matrices.method_22905(2.0f, -2.0f, 2.0f);
        class_4588 riderConsumer = vertexConsumers.getBuffer(class_1921.method_23580((class_2960)rider.method_52814().comp_1626()));
        boolean slim = rider.method_52814().comp_1629() == class_8685.class_7920.field_41122;
        class_591<class_742> riderModel = slim ? this.slimRiderModel : this.wideRiderModel;
        this.mountedPlayerModel.applyTo(riderModel, (class_1309)rider);
        DragonEntityRenderer.scaleMountedHeadToContext(riderModel);
        riderModel.method_2828(matrices, riderConsumer, light, class_4608.field_21444, -1);
        this.renderMountedArmor(rider, slim, riderModel, matrices, vertexConsumers, light);
        matrices.method_22909();
    }

    private static void scaleMountedHeadToContext(class_591<?> model) {
        float headScale;
        model.field_3398.field_37938 = headScale = 0.75f;
        model.field_3398.field_37939 = headScale;
        model.field_3398.field_37940 = headScale;
        model.field_3398.field_3656 += 4.0f;
        model.field_3394.field_37938 = headScale;
        model.field_3394.field_37939 = headScale;
        model.field_3394.field_37940 = headScale;
        model.field_3394.field_3656 += 4.0f;
    }

    private void renderMountedArmor(class_742 rider, boolean slim, class_591<class_742> riderModel, class_4587 matrices, class_4597 vertexConsumers, int light) {
        matrices.method_22903();
        class_572<class_742> contextModel = slim ? this.slimArmorContextModel : this.wideArmorContextModel;
        DragonEntityRenderer.copyBipedTransforms(riderModel, contextModel);
        for (class_1304 slot : MOUNTED_ARMOR_SLOTS) {
            class_1799 stack = rider.method_6118(slot);
            if (stack.method_7960()) continue;
            ArmorRenderer customRenderer = ArmorRendererRegistryImpl.get((class_1792)stack.method_7909());
            if (customRenderer != null) {
                customRenderer.render(matrices, vertexConsumers, stack, (class_1309)rider, slot, light, DragonEntityRenderer.castLivingModel(contextModel));
                continue;
            }
            this.renderVanillaMountedArmorSlot(rider, stack, slot, slim, contextModel, matrices, vertexConsumers, light);
        }
        matrices.method_22909();
    }

    private static class_572<class_1309> castLivingModel(class_572<?> model) {
        return model;
    }

    private void renderVanillaMountedArmorSlot(class_742 rider, class_1799 stack, class_1304 slot, boolean slim, class_572<class_742> contextModel, class_4587 matrices, class_4597 vertexConsumers, int light) {
        class_1738 armorItem;
        class_1792 class_17922 = stack.method_7909();
        if (!(class_17922 instanceof class_1738) || (armorItem = (class_1738)class_17922).method_7685() != slot) {
            return;
        }
        class_572<class_742> model = this.getArmorModel(slim, slot);
        DragonEntityRenderer.copyBipedTransforms(contextModel, model);
        DragonEntityRenderer.setArmorVisibility(model, slot);
        boolean innerModel = DragonEntityRenderer.usesInnerArmorModel(slot);
        class_1741 armorMaterial = (class_1741)armorItem.method_7686().comp_349();
        int dyedColor = stack.method_31573(class_3489.field_48803) ? class_5253.class_5254.method_57174((int)class_9282.method_57470((class_1799)stack, (int)-6265536)) : -1;
        for (class_1741.class_9196 layer : armorMaterial.comp_2302()) {
            int color = layer.method_56692() ? dyedColor : -1;
            class_4588 vertexConsumer = vertexConsumers.getBuffer(class_1921.method_25448((class_2960)layer.method_56693(innerModel)));
            model.method_2828(matrices, vertexConsumer, light, class_4608.field_21444, color);
        }
        class_8053 armorTrim = (class_8053)stack.method_57824(class_9334.field_49607);
        if (armorTrim != null) {
            this.renderArmorTrim((class_6880<class_1741>)armorItem.method_7686(), matrices, vertexConsumers, light, armorTrim, model, innerModel);
        }
        if (stack.method_7958()) {
            model.method_60879(matrices, vertexConsumers.getBuffer(class_1921.method_27949()), light, class_4608.field_21444);
        }
    }

    private void renderArmorTrim(class_6880<class_1741> armorMaterial, class_4587 matrices, class_4597 vertexConsumers, int light, class_8053 trim, class_572<class_742> model, boolean leggings) {
        class_1058 sprite = this.armorTrimsAtlas.method_4608(leggings ? trim.method_48434(armorMaterial) : trim.method_48436(armorMaterial));
        class_4588 vertexConsumer = sprite.method_24108(vertexConsumers.getBuffer(class_4722.method_48480((boolean)((class_8056)trim.method_48424().comp_349()).comp_1905())));
        model.method_60879(matrices, vertexConsumer, light, class_4608.field_21444);
    }

    private class_572<class_742> getArmorModel(boolean slim, class_1304 slot) {
        boolean inner = DragonEntityRenderer.usesInnerArmorModel(slot);
        if (slim) {
            return inner ? this.slimInnerArmorModel : this.slimOuterArmorModel;
        }
        return inner ? this.wideInnerArmorModel : this.wideOuterArmorModel;
    }

    private static boolean usesInnerArmorModel(class_1304 slot) {
        return slot == class_1304.field_6172;
    }

    private static void copyBipedTransforms(class_572<?> source, class_572<?> target) {
        target.field_3398.method_17138(source.field_3398);
        target.field_3391.method_17138(source.field_3391);
        target.field_3401.method_17138(source.field_3401);
        target.field_27433.method_17138(source.field_27433);
        target.field_3392.method_17138(source.field_3392);
        target.field_3397.method_17138(source.field_3397);
        if (target.field_3394 != null && source.field_3394 != null) {
            target.field_3394.method_17138(source.field_3394);
        }
    }

    private static void setArmorVisibility(class_572<?> model, class_1304 slot) {
        model.method_2805(false);
        switch (slot) {
            case field_6169: {
                model.field_3398.field_3665 = true;
                model.field_3394.field_3665 = true;
                break;
            }
            case field_6174: {
                model.field_3391.field_3665 = true;
                model.field_3401.field_3665 = true;
                model.field_27433.field_3665 = true;
                break;
            }
            case field_6172: {
                model.field_3391.field_3665 = true;
                model.field_3392.field_3665 = true;
                model.field_3397.field_3665 = true;
                break;
            }
            case field_6166: {
                model.field_3392.field_3665 = true;
                model.field_3397.field_3665 = true;
            }
        }
    }

    private static void renderGroup(DragonModelData model, DragonModelData.GroupNode group, class_4587 matrices, class_4588 consumer, int light, int overlay, Vector3f parentOrigin, RenderState state, float tickDelta) {
        if ("player".equals(group.name())) {
            return;
        }
        matrices.method_22903();
        DragonModelData.PoseSample pose = DragonEntityRenderer.resolvePose(model, group, state);
        matrices.method_46416((pose.position().x() - parentOrigin.x()) / 16.0f, (pose.position().y() - parentOrigin.y()) / 16.0f, (pose.position().z() - parentOrigin.z()) / 16.0f);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(pose.rotation().z()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(pose.rotation().y()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(pose.rotation().x()));
        for (DragonModelData.RenderNode child : group.children()) {
            if (child instanceof DragonModelData.GroupNode) {
                DragonModelData.GroupNode childGroup = (DragonModelData.GroupNode)child;
                DragonEntityRenderer.renderGroup(model, childGroup, matrices, consumer, light, overlay, pose.position(), state, tickDelta);
                continue;
            }
            if (!(child instanceof DragonModelData.CubeNode)) continue;
            DragonModelData.CubeNode cube = (DragonModelData.CubeNode)child;
            DragonEntityRenderer.renderCube(model, cube, matrices, consumer, light, overlay, pose.position());
        }
        matrices.method_22909();
    }

    private static DragonModelData.PoseSample resolvePose(DragonModelData model, DragonModelData.GroupNode group, RenderState state) {
        return model.poseForGroup(group, state.walkAnimationTime(), state.flyAnimationTime(), state.diveAnimationTime(), state.idleAnimationTime(), state.walkWeight(), state.flyWeight(), state.diveWeight(), state.fireBreathProgress());
    }

    private static void renderCube(DragonModelData model, DragonModelData.CubeNode cube, class_4587 matrices, class_4588 consumer, int light, int overlay, Vector3f parentOrigin) {
        matrices.method_22903();
        matrices.method_46416((cube.origin().x() - parentOrigin.x()) / 16.0f, (cube.origin().y() - parentOrigin.y()) / 16.0f, (cube.origin().z() - parentOrigin.z()) / 16.0f);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(cube.rotation().z()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(cube.rotation().y()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(cube.rotation().x()));
        float x0 = (cube.from().x() - cube.origin().x()) / 16.0f;
        float y0 = (cube.from().y() - cube.origin().y()) / 16.0f;
        float z0 = (cube.from().z() - cube.origin().z()) / 16.0f;
        float x1 = (cube.to().x() - cube.origin().x()) / 16.0f;
        float y1 = (cube.to().y() - cube.origin().y()) / 16.0f;
        float z1 = (cube.to().z() - cube.origin().z()) / 16.0f;
        for (Map.Entry<class_2350, DragonModelData.FaceUv> entry : cube.faces().entrySet()) {
            float swap;
            DragonModelData.FaceUv uv = entry.getValue();
            float minU = uv.u0() / (float)model.textureWidth();
            float minV = uv.v0() / (float)model.textureHeight();
            float maxU = uv.u1() / (float)model.textureWidth();
            float maxV = uv.v1() / (float)model.textureHeight();
            if (cube.mirrorUv() && !DragonEntityRenderer.isFlatWingPlane(cube)) {
                swap = minU;
                minU = maxU;
                maxU = swap;
            }
            if (HORIZONTALLY_FLIPPED_UV_CUBE.equals(cube.name())) {
                swap = minU;
                minU = maxU;
                maxU = swap;
            }
            switch (entry.getKey()) {
                case field_11043: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, -1.0f, x1, y0, z0, maxU, maxV, x0, y0, z0, minU, maxV, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV);
                    break;
                }
                case field_11035: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 0.0f, 1.0f, x0, y0, z1, minU, maxV, x1, y0, z1, maxU, maxV, x1, y1, z1, maxU, minV, x0, y1, z1, minU, minV);
                    break;
                }
                case field_11039: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, -1.0f, 0.0f, 0.0f, x0, y0, z0, minU, maxV, x0, y0, z1, maxU, maxV, x0, y1, z1, maxU, minV, x0, y1, z0, minU, minV);
                    break;
                }
                case field_11034: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 1.0f, 0.0f, 0.0f, x1, y0, z1, minU, maxV, x1, y0, z0, maxU, maxV, x1, y1, z0, maxU, minV, x1, y1, z1, minU, minV);
                    break;
                }
                case field_11036: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, 1.0f, 0.0f, x0, y1, z0, minU, minV, x1, y1, z0, maxU, minV, x1, y1, z1, maxU, maxV, x0, y1, z1, minU, maxV);
                    break;
                }
                case field_11033: {
                    EntityRenderGeometry.emitQuad(consumer, matrices, light, overlay, 0.0f, -1.0f, 0.0f, x0, y0, z1, minU, minV, x1, y0, z1, maxU, minV, x1, y0, z0, maxU, maxV, x0, y0, z0, minU, maxV);
                }
            }
        }
        matrices.method_22909();
    }

    private static boolean isFlatWingPlane(DragonModelData.CubeNode cube) {
        boolean flat;
        boolean bl = flat = Math.abs(cube.from().x() - cube.to().x()) < 1.0E-4f || Math.abs(cube.from().y() - cube.to().y()) < 1.0E-4f || Math.abs(cube.from().z() - cube.to().z()) < 1.0E-4f;
        if (!flat) {
            return false;
        }
        String name = cube.name().toLowerCase(Locale.ROOT);
        return name.startsWith("armleft") || name.startsWith("armright") || name.contains("wing");
    }

    public class_2960 getTexture(DragonEntity entity) {
        String variant = entity.getVariant().toLowerCase(Locale.ROOT);
        if (entity.getOrigin() == DragonOrigin.VALYRIAN) {
            return VALYRIAN_VARIANT_TEXTURES.getOrDefault(variant, DEFAULT_VALYRIAN_TEXTURE);
        }
        return WESTEROSI_VARIANT_TEXTURES.getOrDefault(variant, DEFAULT_WESTEROSI_TEXTURE);
    }

    @Environment(value=EnvType.CLIENT)
    private record RenderState(float walkAnimationTime, float flyAnimationTime, float diveAnimationTime, float idleAnimationTime, float walkWeight, float flyWeight, float diveWeight, float fireBreathProgress) {
    }
}

