/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.mainquest;

import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import org.jetbrains.annotations.Nullable;
import trep.got.castle.CastleInstance;
import trep.got.character.PlayerCharacterHolder;
import trep.got.character.PlayerCharacterProfile;
import trep.got.kingdom.PlayerReputationHolder;
import trep.got.kingdom.ReputationTitle;
import trep.got.registry.GotCastles;

public record MainQuestContext(class_3222 player, class_3218 world, class_2338 pos, @Nullable CastleInstance castleClaim, ReputationTitle reputationTitle, PlayerCharacterProfile characterProfile) {
    public static MainQuestContext of(class_3222 player) {
        class_3218 world = player.method_51469();
        class_2338 pos = player.method_24515();
        CastleInstance claim = GotCastles.getWorldState(world).findClaimAt(pos);
        return new MainQuestContext(player, world, pos, claim, ((PlayerReputationHolder)player).got$getReputationProfile().getTitle(), ((PlayerCharacterHolder)player).got$getCharacterProfile());
    }
}

