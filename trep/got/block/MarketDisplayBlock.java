/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.MapCodec
 *  net.minecraft.class_1263
 *  net.minecraft.class_1264
 *  net.minecraft.class_1269
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1750
 *  net.minecraft.class_1799
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2237
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2383
 *  net.minecraft.class_2415
 *  net.minecraft.class_2464
 *  net.minecraft.class_2470
 *  net.minecraft.class_2561
 *  net.minecraft.class_2586
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 *  net.minecraft.class_2689$class_2690
 *  net.minecraft.class_2753
 *  net.minecraft.class_2769
 *  net.minecraft.class_3222
 *  net.minecraft.class_3726
 *  net.minecraft.class_3908
 *  net.minecraft.class_3965
 *  net.minecraft.class_4970$class_2251
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.block;

import com.mojang.serialization.MapCodec;
import java.util.OptionalInt;
import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3222;
import net.minecraft.class_3726;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_4970;
import org.jetbrains.annotations.Nullable;
import trep.got.block.MarketDisplayTradeService;
import trep.got.block.entity.MarketDisplayBlockEntity;

public final class MarketDisplayBlock
extends class_2237 {
    public static final MapCodec<MarketDisplayBlock> CODEC = MarketDisplayBlock.method_54094(MarketDisplayBlock::new);
    public static final class_2753 FACING = class_2383.field_11177;
    private static final class_265 SHAPE = class_2248.method_9541((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)15.0, (double)16.0);

    public MarketDisplayBlock(class_4970.class_2251 settings) {
        super(settings);
        this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657((class_2769)FACING, (Comparable)class_2350.field_11043));
    }

    protected MapCodec<? extends class_2237> method_53969() {
        return CODEC;
    }

    @Nullable
    public class_2586 method_10123(class_2338 pos, class_2680 state) {
        return new MarketDisplayBlockEntity(pos, state);
    }

    protected void method_9515(class_2689.class_2690<class_2248, class_2680> builder) {
        builder.method_11667(new class_2769[]{FACING});
    }

    @Nullable
    public class_2680 method_9605(class_1750 ctx) {
        return (class_2680)this.method_9564().method_11657((class_2769)FACING, (Comparable)ctx.method_8042().method_10153());
    }

    protected class_2680 method_9598(class_2680 state, class_2470 rotation) {
        return (class_2680)state.method_11657((class_2769)FACING, (Comparable)rotation.method_10503((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_2680 method_9569(class_2680 state, class_2415 mirror) {
        return state.method_26186(mirror.method_10345((class_2350)state.method_11654((class_2769)FACING)));
    }

    protected class_265 method_9530(class_2680 state, class_1922 world, class_2338 pos, class_3726 context) {
        return SHAPE;
    }

    protected class_2464 method_9604(class_2680 state) {
        return class_2464.field_11458;
    }

    public void method_9567(class_1937 world, class_2338 pos, class_2680 state, @Nullable class_1309 placer, class_1799 itemStack) {
        super.method_9567(world, pos, state, placer, itemStack);
        if (!world.field_9236 && placer instanceof class_1657) {
            class_1657 player = (class_1657)placer;
            class_2586 class_25862 = world.method_8321(pos);
            if (class_25862 instanceof MarketDisplayBlockEntity) {
                MarketDisplayBlockEntity marketDisplay = (MarketDisplayBlockEntity)class_25862;
                marketDisplay.setOwnerUuid(player.method_5667());
            }
        }
    }

    protected class_1269 method_55766(class_2680 state, class_1937 world, class_2338 pos, class_1657 player, class_3965 hit) {
        if (world.field_9236) {
            return class_1269.field_5812;
        }
        if (!(player instanceof class_3222)) {
            return class_1269.field_21466;
        }
        class_3222 serverPlayer = (class_3222)player;
        class_2586 class_25862 = world.method_8321(pos);
        if (!(class_25862 instanceof MarketDisplayBlockEntity)) {
            return class_1269.field_21466;
        }
        MarketDisplayBlockEntity marketDisplay = (MarketDisplayBlockEntity)class_25862;
        marketDisplay.ensureGeneratedStockForToday();
        if (hit.method_17780() != ((class_2350)state.method_11654((class_2769)FACING)).method_10153()) {
            if (!marketDisplay.hasMarketSetup() && !marketDisplay.hasAnyConfiguredTrade()) {
                serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This market display has no trades configured."), true);
                return class_1269.field_21466;
            }
            MarketDisplayTradeService.open(serverPlayer, marketDisplay);
            return class_1269.field_21466;
        }
        if (!marketDisplay.canEdit(serverPlayer)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)(marketDisplay.isGeneratedMarketSetup() ? "Only operators can edit generated market displays." : "You do not own this market display.")), true);
            return class_1269.field_21466;
        }
        if (!marketDisplay.tryBeginEditing(serverPlayer)) {
            serverPlayer.method_7353((class_2561)class_2561.method_43470((String)"This market display is already in use."), true);
            return class_1269.field_21466;
        }
        MarketDisplayBlockEntity factory = marketDisplay;
        OptionalInt syncId = serverPlayer.method_17355((class_3908)factory);
        if (syncId.isEmpty()) {
            marketDisplay.endEditing((class_1657)serverPlayer);
        }
        return class_1269.field_21466;
    }

    protected void method_9536(class_2680 state, class_1937 world, class_2338 pos, class_2680 newState, boolean moved) {
        if (!state.method_27852(newState.method_26204())) {
            class_2586 blockEntity = world.method_8321(pos);
            if (blockEntity instanceof MarketDisplayBlockEntity) {
                MarketDisplayBlockEntity marketDisplay = (MarketDisplayBlockEntity)blockEntity;
                class_1264.method_5451((class_1937)world, (class_2338)pos, (class_1263)marketDisplay);
            }
            super.method_9536(state, world, pos, newState, moved);
        }
    }
}

