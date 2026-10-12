/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.minecraft.class_124
 *  net.minecraft.class_2168
 *  net.minecraft.class_2170
 *  net.minecraft.class_2170$class_5364
 *  net.minecraft.class_2186
 *  net.minecraft.class_2561
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_7157
 */
package trep.got.settlement;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Comparator;
import java.util.List;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2186;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_7157;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.registry.GotCastles;

public final class TrustCommands {
    private TrustCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register(TrustCommands::registerCommands);
    }

    private static void registerCommands(CommandDispatcher<class_2168> dispatcher, class_7157 registries, class_2170.class_5364 environment) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247((String)"trust").then(class_2170.method_9247((String)"remove").then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).executes(context -> TrustCommands.remove((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player")))))).then(class_2170.method_9244((String)"player", (ArgumentType)class_2186.method_9305()).executes(context -> TrustCommands.add((CommandContext<class_2168>)context, class_2186.method_9315((CommandContext)context, (String)"player")))));
    }

    private static int add(CommandContext<class_2168> context, class_3222 target) {
        class_3222 owner = TrustCommands.requirePlayer(context);
        if (owner == null) {
            return 0;
        }
        if (owner.method_5667().equals(target.method_5667())) {
            owner.method_7353((class_2561)class_2561.method_43470((String)"You already own this settlement.").method_27692(class_124.field_1054), true);
            return 0;
        }
        class_3218 world = owner.method_51469();
        CastleInstance settlement = TrustCommands.resolveOwnedSettlement(owner);
        if (settlement == null) {
            return 0;
        }
        if (settlement.getMemberUuids().contains(target.method_5667())) {
            owner.method_7353((class_2561)class_2561.method_43470((String)(target.method_5477().getString() + " is already trusted here.")).method_27692(class_124.field_1054), true);
            return 0;
        }
        settlement.addMember(target.method_5667());
        GotCastles.getWorldState(world).method_80();
        GotCastles.syncExternalClaimOutlines(world);
        owner.method_7353((class_2561)class_2561.method_43470((String)("Trusted " + target.method_5477().getString() + " in this settlement.")).method_27692(class_124.field_1060), true);
        target.method_7353((class_2561)class_2561.method_43470((String)(owner.method_5477().getString() + " trusted you to build in their settlement.")).method_27692(class_124.field_1060), true);
        return 1;
    }

    private static int remove(CommandContext<class_2168> context, class_3222 target) {
        class_3222 owner = TrustCommands.requirePlayer(context);
        if (owner == null) {
            return 0;
        }
        class_3218 world = owner.method_51469();
        CastleInstance settlement = TrustCommands.resolveOwnedSettlement(owner);
        if (settlement == null) {
            return 0;
        }
        if (!settlement.getMemberUuids().contains(target.method_5667())) {
            owner.method_7353((class_2561)class_2561.method_43470((String)(target.method_5477().getString() + " is not trusted here.")).method_27692(class_124.field_1061), true);
            return 0;
        }
        settlement.removeMember(target.method_5667());
        GotCastles.getWorldState(world).method_80();
        GotCastles.syncExternalClaimOutlines(world);
        owner.method_7353((class_2561)class_2561.method_43470((String)("Removed " + target.method_5477().getString() + " from this settlement's trust list.")).method_27692(class_124.field_1054), true);
        target.method_7353((class_2561)class_2561.method_43470((String)(owner.method_5477().getString() + " removed your settlement build trust.")).method_27692(class_124.field_1061), true);
        return 1;
    }

    private static class_3222 requirePlayer(CommandContext<class_2168> context) {
        try {
            return ((class_2168)context.getSource()).method_9207();
        }
        catch (Exception exception) {
            ((class_2168)context.getSource()).method_9213((class_2561)class_2561.method_43470((String)"Only players can use this command."));
            return null;
        }
    }

    private static CastleInstance resolveOwnedSettlement(class_3222 owner) {
        class_3218 world = owner.method_51469();
        CastleWorldState state = GotCastles.getWorldState(world);
        CastleInstance current = state.findClaimAt(owner.method_24515());
        if (TrustCommands.isOwnedPlayerSettlement(current, owner)) {
            return current;
        }
        List<CastleInstance> ownedSettlements = state.getAll().stream().filter(claim -> TrustCommands.isOwnedPlayerSettlement(claim, owner)).sorted(Comparator.comparing(CastleInstance::getId)).toList();
        if (ownedSettlements.isEmpty()) {
            owner.method_7353((class_2561)class_2561.method_43470((String)"You do not own a player settlement.").method_27692(class_124.field_1061), true);
            return null;
        }
        if (ownedSettlements.size() > 1) {
            owner.method_7353((class_2561)class_2561.method_43470((String)"Stand inside the settlement you want to update.").method_27692(class_124.field_1061), true);
            return null;
        }
        return ownedSettlements.getFirst();
    }

    private static boolean isOwnedPlayerSettlement(CastleInstance claim, class_3222 owner) {
        return claim != null && "player_claim".equals(claim.getDefinitionId()) && owner.method_5667().equals(claim.getOwnerPlayerUuid());
    }
}

