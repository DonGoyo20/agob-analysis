/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1282
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 */
package trep.got.entity;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import trep.got.GotMod;
import trep.got.block.TentCollisionBlock;
import trep.got.registry.GotCastles;
import trep.got.registry.GotItems;

public final class TentEntity
extends class_1297 {
    private static final class_2940<Integer> HORIZONTAL_FACING = class_2945.method_12791(TentEntity.class, (class_2941)class_2943.field_13327);
    private static final class_2940<class_2338> ORIGIN_POS = class_2945.method_12791(TentEntity.class, (class_2941)class_2943.field_13324);
    private static final class_2940<String> QUEST_ID = class_2945.method_12791(TentEntity.class, (class_2941)class_2943.field_13326);

    public TentEntity(class_1299<? extends TentEntity> entityType, class_1937 world) {
        super(entityType, world);
        this.field_5960 = true;
    }

    protected void method_5693(class_2945.class_9222 builder) {
        builder.method_56912(HORIZONTAL_FACING, (Object)class_2350.field_11043.method_10161());
        builder.method_56912(ORIGIN_POS, (Object)class_2338.field_10980);
        builder.method_56912(QUEST_ID, (Object)"");
    }

    protected void method_5749(class_2487 nbt) {
        if (nbt.method_10545("origin_x")) {
            this.setOriginPos(new class_2338(nbt.method_10550("origin_x"), nbt.method_10550("origin_y"), nbt.method_10550("origin_z")));
        }
        this.setFacing(class_2350.method_10139((int)nbt.method_10550("horizontal_facing")));
        this.setQuestId(nbt.method_10558("quest_id"));
    }

    protected void method_5652(class_2487 nbt) {
        class_2338 origin = this.getOriginPos();
        nbt.method_10569("origin_x", origin.method_10263());
        nbt.method_10569("origin_y", origin.method_10264());
        nbt.method_10569("origin_z", origin.method_10260());
        nbt.method_10569("horizontal_facing", this.method_58149().method_10161());
        nbt.method_10582("quest_id", this.getQuestId());
    }

    public void method_5773() {
        super.method_5773();
        this.field_5960 = true;
        this.method_18800(0.0, 0.0, 0.0);
        this.method_5857(this.method_33332());
    }

    public boolean method_5732() {
        return true;
    }

    public boolean method_5863() {
        return true;
    }

    public boolean method_5810() {
        return false;
    }

    protected class_238 method_33332() {
        class_2338 origin = this.getOriginPos();
        if (origin == null) {
            return super.method_33332();
        }
        return new class_238((double)(origin.method_10263() - 1), (double)origin.method_10264(), (double)(origin.method_10260() - 1), (double)(origin.method_10263() + 2), (double)(origin.method_10264() + 3), (double)(origin.method_10260() + 2));
    }

    public float method_5871() {
        return 0.1f;
    }

    public boolean method_5643(class_1282 source, float amount) {
        if (this.method_37908().field_9236) {
            return true;
        }
        class_2338 origin = this.getOriginPos();
        class_1297 class_12972 = source.method_5529();
        if (!(class_12972 instanceof class_1657)) {
            GotMod.LOGGER.info("[tent-break-debug] entity-hit ignored origin={} reason=non_player_attacker", (Object)origin.method_23854());
            return false;
        }
        class_1657 player = (class_1657)class_12972;
        GotMod.LOGGER.info("[tent-break-debug] entity-hit player={} origin={} amount={}", new Object[]{player.method_5477().getString(), origin.method_23854(), Float.valueOf(amount)});
        if (player instanceof class_3222) {
            class_3222 serverPlayer = (class_3222)player;
            class_1937 class_19372 = this.method_37908();
            if (class_19372 instanceof class_3218) {
                class_3218 serverWorld = (class_3218)class_19372;
                if (!GotCastles.tryBreakSettlementTent(serverWorld, serverPlayer, origin)) {
                    GotMod.LOGGER.info("[tent-break-debug] entity-hit blocked origin={} reason=claim_protection", (Object)origin.method_23854());
                    return false;
                }
                class_2248 class_22482 = serverWorld.method_8320(origin).method_26204();
                if (class_22482 instanceof TentCollisionBlock) {
                    TentCollisionBlock tentBlock = (TentCollisionBlock)class_22482;
                    GotMod.LOGGER.info("[tent-break-debug] entity-hit destroying structure origin={}", (Object)origin.method_23854());
                    tentBlock.destroyStructure((class_1937)serverWorld, origin, serverWorld.method_8320(origin), false);
                } else {
                    GotMod.LOGGER.info("[tent-break-debug] entity-hit no_collision_block origin={} block={}", (Object)origin.method_23854(), (Object)serverWorld.method_8320(origin).method_26204());
                }
            }
        }
        if (!player.method_7337() && this.getQuestId().isBlank()) {
            this.method_5775(new class_1799((class_1935)GotItems.TENT));
        }
        GotMod.LOGGER.info("[tent-break-debug] entity-hit discarding visual origin={}", (Object)origin.method_23854());
        this.method_31472();
        return true;
    }

    public class_1269 method_5688(class_1657 player, class_1268 hand) {
        class_1937 class_19372;
        class_3222 serverPlayer;
        block5: {
            block4: {
                if (!(player instanceof class_3222)) break block4;
                serverPlayer = (class_3222)player;
                class_19372 = this.method_37908();
                if (class_19372 instanceof class_3218) break block5;
            }
            return class_1269.field_5812;
        }
        class_3218 serverWorld = (class_3218)class_19372;
        class_2338 origin = this.getOriginPos();
        if (GotCastles.getWorldState(serverWorld).findByBannerPos(origin) != null) {
            GotCastles.openCastleScreen(serverPlayer, GotCastles.getWorldState(serverWorld).findByBannerPos(origin).getId());
            return class_1269.field_5812;
        }
        return class_1269.field_5811;
    }

    public void setOriginPos(class_2338 originPos) {
        this.field_6011.method_12778(ORIGIN_POS, (Object)originPos.method_10062());
        this.method_5857(this.method_33332());
    }

    public class_2338 getOriginPos() {
        return (class_2338)this.field_6011.method_12789(ORIGIN_POS);
    }

    public class_2350 method_58149() {
        return class_2350.method_10139((int)((Integer)this.field_6011.method_12789(HORIZONTAL_FACING)));
    }

    public String getQuestId() {
        return (String)this.field_6011.method_12789(QUEST_ID);
    }

    public void setQuestId(String questId) {
        this.field_6011.method_12778(QUEST_ID, (Object)(questId == null ? "" : questId));
    }

    public void setFacing(class_2350 facing) {
        class_2350 horizontalFacing = facing.method_10166().method_10179() ? facing : class_2350.field_11043;
        this.field_6011.method_12778(HORIZONTAL_FACING, (Object)horizontalFacing.method_10161());
        this.method_36456(horizontalFacing.method_10144());
        this.field_5982 = this.method_36454();
        this.method_5857(this.method_33332());
    }
}

