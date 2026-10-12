/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2185
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2680
 */
package trep.got.world.structure.castle;

import java.util.Optional;
import net.minecraft.class_2185;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import trep.got.world.structure.apply.StructureBlockEditor;
import trep.got.world.structure.castle.DynamicCastleCoreWorldgenPlan;

final class DynamicCastleCoreSearch {
    private DynamicCastleCoreSearch() {
    }

    static Optional<class_2338> findBanner(StructureBlockEditor editor, DynamicCastleCoreWorldgenPlan plan) {
        Optional<class_2338> validBanner = DynamicCastleCoreSearch.findValidClaimBanner(editor, plan);
        if (validBanner.isPresent()) {
            return validBanner;
        }
        return DynamicCastleCoreSearch.findAnyBanner(editor, plan);
    }

    private static Optional<class_2338> findValidClaimBanner(StructureBlockEditor editor, DynamicCastleCoreWorldgenPlan plan) {
        class_2338 origin = plan.searchOrigin();
        for (int y = origin.method_10264() + plan.searchSize().method_10264() + 2; y >= origin.method_10264() - 4; --y) {
            for (int x = origin.method_10263(); x < origin.method_10263() + plan.searchSize().method_10263(); ++x) {
                for (int z = origin.method_10260(); z < origin.method_10260() + plan.searchSize().method_10260(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    if (!DynamicCastleCoreSearch.isValidClaimBanner(editor, pos)) continue;
                    return Optional.of(pos);
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<class_2338> findAnyBanner(StructureBlockEditor editor, DynamicCastleCoreWorldgenPlan plan) {
        class_2338 origin = plan.searchOrigin();
        for (int y = origin.method_10264() + plan.searchSize().method_10264() + 2; y >= origin.method_10264() - 4; --y) {
            for (int x = origin.method_10263(); x < origin.method_10263() + plan.searchSize().method_10263(); ++x) {
                for (int z = origin.method_10260(); z < origin.method_10260() + plan.searchSize().method_10260(); ++z) {
                    class_2338 pos = new class_2338(x, y, z);
                    if (!(editor.getBlockState(pos).method_26204() instanceof class_2185)) continue;
                    return Optional.of(pos);
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isValidClaimBanner(StructureBlockEditor editor, class_2338 bannerPos) {
        class_2680 bannerState = editor.getBlockState(bannerPos);
        if (!(bannerState.method_26204() instanceof class_2185)) {
            return false;
        }
        int baseY = bannerPos.method_10264() - 1;
        class_2338 basePos = new class_2338(bannerPos.method_10263(), baseY, bannerPos.method_10260());
        if (editor.getBlockState(basePos).method_27852(class_2246.field_10205)) {
            return true;
        }
        for (int x = bannerPos.method_10263() - 1; x <= bannerPos.method_10263() + 1; ++x) {
            for (int z = bannerPos.method_10260() - 1; z <= bannerPos.method_10260() + 1; ++z) {
                if (editor.getBlockState(new class_2338(x, baseY, z)).method_27852(class_2246.field_10205)) continue;
                return false;
            }
        }
        return true;
    }
}

