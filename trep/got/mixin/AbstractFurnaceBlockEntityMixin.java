/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.class_1799
 *  net.minecraft.class_1874
 *  net.minecraft.class_2609
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_3956
 *  net.minecraft.class_7225$class_7874
 *  net.minecraft.class_8786
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package trep.got.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1799;
import net.minecraft.class_1874;
import net.minecraft.class_2609;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3956;
import net.minecraft.class_7225;
import net.minecraft.class_8786;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import trep.got.skill.source.SmithingSkillExperienceSource;

@Mixin(value={class_2609.class})
public abstract class AbstractFurnaceBlockEntityMixin {
    @Shadow
    private Object2IntOpenHashMap<class_2960> field_11986;

    @Inject(method={"method_17763"}, at={@At(value="HEAD")})
    private void got$grantSmithingExperienceForSmelting(class_3222 player, CallbackInfo ci) {
        if (player.method_51469() == null || this.field_11986.isEmpty()) {
            return;
        }
        Map<class_2960, class_1799> resultsByRecipeId = AbstractFurnaceBlockEntityMixin.got$buildCookingResults(player.method_51469());
        for (Object2IntMap.Entry entry : this.field_11986.object2IntEntrySet()) {
            class_1799 result = resultsByRecipeId.get(entry.getKey());
            if (result == null) continue;
            SmithingSkillExperienceSource.grantSmeltingExperience(player, result, entry.getIntValue());
        }
    }

    @Unique
    private static Map<class_2960, class_1799> got$buildCookingResults(class_3218 world) {
        HashMap<class_2960, class_1799> results = new HashMap<class_2960, class_1799>();
        AbstractFurnaceBlockEntityMixin.got$collectRecipeResults(world, (class_3956<? extends class_1874>)class_3956.field_17546, results);
        AbstractFurnaceBlockEntityMixin.got$collectRecipeResults(world, (class_3956<? extends class_1874>)class_3956.field_17547, results);
        return results;
    }

    @Unique
    private static void got$collectRecipeResults(class_3218 world, class_3956<? extends class_1874> recipeType, Map<class_2960, class_1799> results) {
        for (class_8786 entry : world.method_8433().method_30027(recipeType)) {
            results.put(entry.comp_1932(), ((class_1874)entry.comp_1933()).method_8110((class_7225.class_7874)world.method_30349()));
        }
    }
}

