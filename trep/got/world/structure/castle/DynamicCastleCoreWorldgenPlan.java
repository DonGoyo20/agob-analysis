/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_2415
 *  net.minecraft.class_2470
 *  net.minecraft.class_2960
 *  net.minecraft.class_3341
 *  net.minecraft.class_3485
 *  net.minecraft.class_3499
 */
package trep.got.world.structure.castle;

import java.util.List;
import java.util.Optional;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_2415;
import net.minecraft.class_2470;
import net.minecraft.class_2960;
import net.minecraft.class_3341;
import net.minecraft.class_3485;
import net.minecraft.class_3499;
import trep.got.world.structure.castle.DynamicCastleKeepPiecePlan;

public record DynamicCastleCoreWorldgenPlan(boolean usesCastleKeep, class_2338 searchOrigin, class_2382 searchSize, class_2338 templateOrigin, class_2382 templateSize, List<DynamicCastleKeepPiecePlan> keepPieces, class_2960 claimTemplateId, class_2338 fallbackBannerPos) {
    public class_3341 blockBounds() {
        return new class_3341(this.searchOrigin.method_10263(), this.searchOrigin.method_10264(), this.searchOrigin.method_10260(), this.searchOrigin.method_10263() + this.searchSize.method_10263() - 1, this.searchOrigin.method_10264() + this.searchSize.method_10264() - 1, this.searchOrigin.method_10260() + this.searchSize.method_10260() - 1);
    }

    public Optional<class_2960> singleTemplateId() {
        return Optional.ofNullable(this.claimTemplateId);
    }

    public class_3341 structureBounds(class_3485 templateManager) {
        class_3499 template;
        if (templateManager == null) {
            return this.blockBounds();
        }
        if (this.usesCastleKeep && this.keepPieces != null && !this.keepPieces.isEmpty()) {
            class_3341 bounds = null;
            for (DynamicCastleKeepPiecePlan piece : this.keepPieces) {
                class_3499 template2;
                if (piece == null || piece.structureId() == null || piece.offset() == null || (template2 = (class_3499)templateManager.method_15094(piece.structureId()).orElse(null)) == null) continue;
                class_2338 origin = this.templateOrigin.method_10081(piece.offset());
                bounds = DynamicCastleCoreWorldgenPlan.mergeBounds(bounds, template2.method_27267(origin, class_2470.field_11467, class_2338.field_10980, class_2415.field_11302));
            }
            return bounds == null ? this.blockBounds() : bounds;
        }
        if (this.claimTemplateId != null && (template = (class_3499)templateManager.method_15094(this.claimTemplateId).orElse(null)) != null) {
            return template.method_27267(this.templateOrigin, class_2470.field_11467, class_2338.field_10980, class_2415.field_11302);
        }
        return this.blockBounds();
    }

    private static class_3341 mergeBounds(class_3341 first, class_3341 second) {
        if (first == null) {
            return second;
        }
        return new class_3341(Math.min(first.method_35415(), second.method_35415()), Math.min(first.method_35416(), second.method_35416()), Math.min(first.method_35417(), second.method_35417()), Math.max(first.method_35418(), second.method_35418()), Math.max(first.method_35419(), second.method_35419()), Math.max(first.method_35420(), second.method_35420()));
    }
}

