/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1282
 *  net.minecraft.class_1299
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1320
 *  net.minecraft.class_1324
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2487
 *  net.minecraft.class_2940
 *  net.minecraft.class_2941
 *  net.minecraft.class_2943
 *  net.minecraft.class_2945
 *  net.minecraft.class_2945$class_9222
 *  net.minecraft.class_2960
 *  net.minecraft.class_3218
 *  net.minecraft.class_3222
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5134
 *  net.minecraft.class_6880
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.entity;

import java.util.UUID;
import net.minecraft.class_1282;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1320;
import net.minecraft.class_1324;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2941;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5132;
import net.minecraft.class_5134;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;
import trep.got.entity.AbstractHostileWorldEventNpcEntity;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.registry.GotCastles;

public class AbstractTourneyNpcEntity
extends AbstractHostileWorldEventNpcEntity {
    private static final class_2940<String> TEXTURE_ID = class_2945.method_12791(AbstractTourneyNpcEntity.class, (class_2941)class_2943.field_13326);
    private static final String TARGET_PLAYER_KEY = "gotmod.tourney_target_player";
    private static final String TEXTURE_ID_KEY = "gotmod.tourney_texture_id";
    @Nullable
    private UUID targetPlayerUuid;

    public AbstractTourneyNpcEntity(class_1299<? extends AbstractWorldEventNpcEntity> entityType, class_1937 world) {
        super(entityType, world);
    }

    public static class_5132.class_5133 createTourneyAttributes() {
        return AbstractWorldEventNpcEntity.createWorldEventNpcAttributes().method_26868(class_5134.field_23719, 0.1);
    }

    @Override
    protected void method_5693(class_2945.class_9222 builder) {
        super.method_5693(builder);
        builder.method_56912(TEXTURE_ID, (Object)class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_1.png").toString());
    }

    @Override
    public void method_5652(class_2487 nbt) {
        super.method_5652(nbt);
        if (this.targetPlayerUuid != null) {
            nbt.method_25927(TARGET_PLAYER_KEY, this.targetPlayerUuid);
        }
        nbt.method_10582(TEXTURE_ID_KEY, (String)this.field_6011.method_12789(TEXTURE_ID));
    }

    @Override
    public void method_5749(class_2487 nbt) {
        super.method_5749(nbt);
        if (nbt.method_25928(TARGET_PLAYER_KEY)) {
            this.targetPlayerUuid = nbt.method_25926(TARGET_PLAYER_KEY);
        }
        if (nbt.method_10545(TEXTURE_ID_KEY)) {
            this.field_6011.method_12778(TEXTURE_ID, (Object)nbt.method_10558(TEXTURE_ID_KEY));
        }
    }

    @Override
    public void method_5773() {
        class_3222 player;
        super.method_5773();
        if (this.method_37908().method_8608() || this.targetPlayerUuid == null || !this.method_5805()) {
            return;
        }
        class_3222 class_32222 = player = this.method_5682() == null ? null : this.method_5682().method_3760().method_14602(this.targetPlayerUuid);
        if (player == null || !player.method_5805() || player.method_37908() != this.method_37908()) {
            this.method_5980(null);
            return;
        }
        if (this.method_5968() == null || !this.targetPlayerUuid.equals(this.method_5968().method_5667())) {
            this.method_5980((class_1309)player);
        }
    }

    @Override
    protected boolean isHostileToPlayers() {
        return false;
    }

    @Override
    public class_2960 getTextureId() {
        class_2960 id = class_2960.method_12829((String)((String)this.field_6011.method_12789(TEXTURE_ID)));
        return id == null ? class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_1.png") : id;
    }

    @Override
    protected void applyWorldEventLoadout() {
    }

    public void configureForTourney(class_3222 target, class_2960 texture, double health, double speed, class_1799 mainhand, class_1799 helmet, class_1799 chestplate, class_1799 leggings, class_1799 boots) {
        this.targetPlayerUuid = target == null ? null : target.method_5667();
        this.field_6011.method_12778(TEXTURE_ID, (Object)(texture == null ? class_2960.method_60655((String)"agob", (String)"textures/entity/npc/event/peasant_1.png").toString() : texture.toString()));
        this.setAttribute((class_6880<class_1320>)class_5134.field_23716, health);
        this.method_6033((float)health);
        this.setAttribute((class_6880<class_1320>)class_5134.field_23719, speed);
        this.method_5673(class_1304.field_6173, mainhand);
        this.method_5673(class_1304.field_6169, helmet);
        this.method_5673(class_1304.field_6174, chestplate);
        this.method_5673(class_1304.field_6172, leggings);
        this.method_5673(class_1304.field_6166, boots);
        this.setEventPresentationReady(true);
    }

    @Override
    public void method_6078(class_1282 damageSource) {
        class_1937 class_19372;
        super.method_6078(damageSource);
        if (!this.method_37908().method_8608() && (class_19372 = this.method_37908()) instanceof class_3218) {
            class_3218 world = (class_3218)class_19372;
            GotCastles.onTourneyNpcDefeated(world, this);
        }
    }

    private void setAttribute(class_6880<class_1320> attribute, double value) {
        class_1324 instance = this.method_5996(attribute);
        if (instance != null) {
            instance.method_6192(value);
        }
    }
}

