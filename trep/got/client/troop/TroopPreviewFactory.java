/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1498
 *  net.minecraft.class_1937
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_638
 *  net.minecraft.class_8685
 */
package trep.got.client.troop;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1498;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_638;
import net.minecraft.class_8685;
import trep.got.character.CharacterSelectionOption;
import trep.got.client.character.CharacterPreviewPlayerEntity;
import trep.got.client.character.CustomPlayerSkinResolver;
import trep.got.registry.GotCharacterOptions;
import trep.got.troop.DynamicTroopDefinition;
import trep.got.troop.DynamicTroopLoadoutApplier;
import trep.got.troop.DynamicTroopUnitType;
import trep.got.troop.TroopStatResolver;

@Environment(value=EnvType.CLIENT)
public final class TroopPreviewFactory {
    private TroopPreviewFactory() {
    }

    public static TroopPreview createPreview(DynamicTroopDefinition definition) {
        class_2960 skinTexture;
        class_638 world;
        class_310 client;
        block5: {
            block4: {
                class_638 class_6382;
                client = class_310.method_1551();
                if (client.field_1724 == null || !((class_6382 = client.field_1687) instanceof class_638)) break block4;
                world = class_6382;
                if (definition != null) break block5;
            }
            return null;
        }
        CharacterSelectionOption option = GotCharacterOptions.getByKingdomId(definition.kingdomId());
        Object object = definition.skinTexture() != null ? definition.skinTexture() : (skinTexture = option != null ? option.previewSkinTexture() : null);
        class_8685 skinTextures = skinTexture != null ? CustomPlayerSkinResolver.resolve(client, skinTexture, option != null && option.smallArms(), client.field_1724.method_52814(), "unit_tree_preview") : client.field_1724.method_52814();
        CharacterPreviewPlayerEntity preview = new CharacterPreviewPlayerEntity(world, skinTextures, client.field_1724);
        TroopStatResolver.applyTroopAttributes((class_1309)preview, definition);
        TroopPreviewFactory.applyLoadout((class_1309)preview, definition);
        if (definition.unitType() != DynamicTroopUnitType.CAVALRY) {
            return new TroopPreview((class_1309)preview, null, (class_1309)preview, 1.0f);
        }
        class_1498 horse = new class_1498(class_1299.field_6139, (class_1937)world);
        horse.method_5808(0.0, 0.0, 0.0, 0.0f, 0.0f);
        DynamicTroopLoadoutApplier.applyMountEquipment(horse, definition);
        preview.method_5808(0.0, 0.0, 0.0, 0.0f, 0.0f);
        preview.method_5873((class_1297)horse, true);
        horse.method_24201((class_1297)preview);
        return new TroopPreview((class_1309)preview, horse, (class_1309)preview, 0.75f);
    }

    public static void applyLoadout(class_1309 entity, DynamicTroopDefinition definition) {
        DynamicTroopLoadoutApplier.applyEquipment(entity, definition);
    }

    @Environment(value=EnvType.CLIENT)
    public record TroopPreview(class_1309 renderEntity, class_1498 mountEntity, class_1309 equipmentEntity, float scaleMultiplier) {
    }
}

