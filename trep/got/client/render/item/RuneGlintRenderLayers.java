/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1921
 *  net.minecraft.class_1921$class_4688
 *  net.minecraft.class_1921$class_4688$class_4689
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_4668$class_4678
 *  net.minecraft.class_4668$class_4683
 *  net.minecraft.class_4668$class_4684
 *  net.minecraft.class_4668$class_5939
 *  net.minecraft.class_4668$class_5942
 */
package trep.got.client.render.item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_4668;
import trep.got.mixin.client.RenderLayerAccessor;
import trep.got.mixin.client.RenderPhaseAccessor;

@Environment(value=EnvType.CLIENT)
public final class RuneGlintRenderLayers {
    private static final Map<class_2960, class_1921> ARMOR_ENTITY_GLINT = new ConcurrentHashMap<class_2960, class_1921>();
    private static final Map<class_2960, class_1921> GLINT_TRANSLUCENT = new ConcurrentHashMap<class_2960, class_1921>();
    private static final Map<class_2960, class_1921> GLINT = new ConcurrentHashMap<class_2960, class_1921>();
    private static final Map<class_2960, class_1921> ENTITY_GLINT = new ConcurrentHashMap<class_2960, class_1921>();
    private static final Map<class_2960, class_1921> DIRECT_ENTITY_GLINT = new ConcurrentHashMap<class_2960, class_1921>();

    private RuneGlintRenderLayers() {
    }

    public static class_1921 getArmorEntityGlint(class_2960 texture) {
        return ARMOR_ENTITY_GLINT.computeIfAbsent(texture, id -> RuneGlintRenderLayers.create("agob_rune_armor_entity_glint", RenderPhaseAccessor.agob$getArmorEntityGlintProgram(), id, RenderPhaseAccessor.agob$getEntityGlintTexturing(), true, false, true));
    }

    public static class_1921 getGlintTranslucent(class_2960 texture) {
        return GLINT_TRANSLUCENT.computeIfAbsent(texture, id -> RuneGlintRenderLayers.createWithTarget("agob_rune_glint_translucent", RenderPhaseAccessor.agob$getTranslucentGlintProgram(), id, RenderPhaseAccessor.agob$getGlintTexturing(), RenderPhaseAccessor.agob$getItemEntityTarget()));
    }

    public static class_1921 getGlint(class_2960 texture) {
        return GLINT.computeIfAbsent(texture, id -> RuneGlintRenderLayers.create("agob_rune_glint", RenderPhaseAccessor.agob$getGlintProgram(), id, RenderPhaseAccessor.agob$getGlintTexturing(), false, false, false));
    }

    public static class_1921 getEntityGlint(class_2960 texture) {
        return ENTITY_GLINT.computeIfAbsent(texture, id -> RuneGlintRenderLayers.createWithTarget("agob_rune_entity_glint", RenderPhaseAccessor.agob$getEntityGlintProgram(), id, RenderPhaseAccessor.agob$getEntityGlintTexturing(), RenderPhaseAccessor.agob$getItemEntityTarget()));
    }

    public static class_1921 getDirectEntityGlint(class_2960 texture) {
        return DIRECT_ENTITY_GLINT.computeIfAbsent(texture, id -> RuneGlintRenderLayers.create("agob_rune_entity_glint_direct", RenderPhaseAccessor.agob$getDirectEntityGlintProgram(), id, RenderPhaseAccessor.agob$getEntityGlintTexturing(), false, false, false));
    }

    private static class_1921 create(String name, class_4668.class_5942 program, class_2960 texture, class_4668.class_4684 texturing, boolean viewOffsetLayering, boolean targetItemEntity, boolean armorLike) {
        class_1921.class_4688.class_4689 builder = class_1921.class_4688.method_23598().method_34578(program).method_34577((class_4668.class_5939)new class_4668.class_4683(texture, true, false)).method_23616(RenderPhaseAccessor.agob$getColorMask()).method_23603(RenderPhaseAccessor.agob$getDisableCulling()).method_23604(RenderPhaseAccessor.agob$getEqualDepthTest()).method_23615(RenderPhaseAccessor.agob$getGlintTransparency()).method_23614(texturing);
        if (viewOffsetLayering) {
            builder.method_23607(RenderPhaseAccessor.agob$getViewOffsetZLayering());
        }
        if (targetItemEntity) {
            builder.method_23610(RenderPhaseAccessor.agob$getItemEntityTarget());
        }
        return RenderLayerAccessor.agob$invokeOf(name + ":" + String.valueOf(texture), class_290.field_1585, class_293.class_5596.field_27382, 1536, builder.method_23617(false));
    }

    private static class_1921 createWithTarget(String name, class_4668.class_5942 program, class_2960 texture, class_4668.class_4684 texturing, class_4668.class_4678 target) {
        class_1921.class_4688 parameters = class_1921.class_4688.method_23598().method_34578(program).method_34577((class_4668.class_5939)new class_4668.class_4683(texture, true, false)).method_23616(RenderPhaseAccessor.agob$getColorMask()).method_23603(RenderPhaseAccessor.agob$getDisableCulling()).method_23604(RenderPhaseAccessor.agob$getEqualDepthTest()).method_23615(RenderPhaseAccessor.agob$getGlintTransparency()).method_23614(texturing).method_23610(target).method_23617(false);
        return RenderLayerAccessor.agob$invokeOf(name + ":" + String.valueOf(texture), class_290.field_1585, class_293.class_5596.field_27382, 1536, parameters);
    }
}

