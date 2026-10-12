/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
 *  net.minecraft.class_2960
 *  net.minecraft.class_910
 */
package trep.got.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_2960;
import net.minecraft.class_910;
import trep.got.client.entity.CartEntityRenderer;
import trep.got.client.entity.CatapultEntityRenderer;
import trep.got.client.entity.CatapultMunitionEntityRenderer;
import trep.got.client.entity.DeerEntityRenderer;
import trep.got.client.entity.DirewolfEntityRenderer;
import trep.got.client.entity.DragonEntityRenderer;
import trep.got.client.entity.DynamicTroopRenderer;
import trep.got.client.entity.DynastyNpcRenderer;
import trep.got.client.entity.GotDonkeyRenderer;
import trep.got.client.entity.InvisibleEntityRenderer;
import trep.got.client.entity.KingdomNpcRenderer;
import trep.got.client.entity.PackhorseRenderer;
import trep.got.client.entity.ScorpionCrossbowEntityRenderer;
import trep.got.client.entity.SettlerNpcRenderer;
import trep.got.client.entity.ShipEntityRenderer;
import trep.got.client.entity.TentEntityRenderer;
import trep.got.client.entity.TwoHorseCartEntityRenderer;
import trep.got.client.entity.WarPlaceholderRenderer;
import trep.got.client.entity.WorldEventNpcRenderer;
import trep.got.registry.GotEntities;

@Environment(value=EnvType.CLIENT)
public final class GotEntityRenderers {
    private GotEntityRenderers() {
    }

    public static void initialize() {
        EntityRendererRegistry.register(GotEntities.DYNAMIC_TROOP, DynamicTroopRenderer::new);
        EntityRendererRegistry.register(GotEntities.HIREABLE_UNIT, DynamicTroopRenderer::new);
        EntityRendererRegistry.register(GotEntities.SETTLER_NPC, SettlerNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.PARTY_TROOP, DynamicTroopRenderer::new);
        EntityRendererRegistry.register(GotEntities.PARTY_HORSE, class_910::new);
        EntityRendererRegistry.register(GotEntities.SUMPTER, class_910::new);
        EntityRendererRegistry.register(GotEntities.ROUNCEY, class_910::new);
        EntityRendererRegistry.register(GotEntities.DESTRIER, class_910::new);
        EntityRendererRegistry.register(GotEntities.GOT_DONKEY, GotDonkeyRenderer::new);
        EntityRendererRegistry.register(GotEntities.PACKHORSE, PackhorseRenderer::new);
        EntityRendererRegistry.register(GotEntities.DEER, DeerEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.DIREWOLF, DirewolfEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.DRAGON, DragonEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.DRAGON_FIRE, InvisibleEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.SEAT, InvisibleEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.WAR_PLACEHOLDER, WarPlaceholderRenderer::new);
        EntityRendererRegistry.register(GotEntities.TENT, TentEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.CART, CartEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.CART_2HORSE, TwoHorseCartEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.CATAPULT, CatapultEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.CATAPULT_MUNITION, CatapultMunitionEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.SCORPION_CROSSBOW, ScorpionCrossbowEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.SHIP, ShipEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.GALLEY, ShipEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.LONGSHIP, ShipEntityRenderer::new);
        EntityRendererRegistry.register(GotEntities.NORTH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NIGHTS_WATCH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_nights_watch")));
        EntityRendererRegistry.register(GotEntities.NORTH_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NIGHTS_WATCH_GUARD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_nights_watch")));
        EntityRendererRegistry.register(GotEntities.NORTH_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NIGHTS_WATCH_LORD_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_nights_watch")));
        EntityRendererRegistry.register(GotEntities.NORTH_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NIGHTS_WATCH_VILLAGE_ELDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_nights_watch")));
        EntityRendererRegistry.register(GotEntities.NORTH_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_BLACKSMITH_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NORTH_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_FISHERMAN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NORTH_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        EntityRendererRegistry.register(GotEntities.NIGHTS_WATCH_CART_RIDER_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_nights_watch")));
        EntityRendererRegistry.register(GotEntities.NORTH_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_north")));
        EntityRendererRegistry.register(GotEntities.VALE_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_vale")));
        EntityRendererRegistry.register(GotEntities.RIVERLANDS_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_riverlands")));
        EntityRendererRegistry.register(GotEntities.WESTERLANDS_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_westerlands")));
        EntityRendererRegistry.register(GotEntities.IRON_ISLANDS_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"iron_islands")));
        EntityRendererRegistry.register(GotEntities.REACH_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_reach")));
        EntityRendererRegistry.register(GotEntities.STORMLANDS_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_stormlands")));
        EntityRendererRegistry.register(GotEntities.DORNE_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"dorne")));
        EntityRendererRegistry.register(GotEntities.CROWNLANDS_CAPTAIN_NPC, context -> new KingdomNpcRenderer(context, class_2960.method_60655((String)"agob", (String)"the_crownlands")));
        for (GotEntities.KingdomNpcEntityRegistration registration : GotEntities.configuredKingdomNpcRegistrations()) {
            GotEntityRenderers.registerKingdomNpcRenderer(registration);
        }
        EntityRendererRegistry.register(GotEntities.IRON_BORN_RAIDERS_NPC, WorldEventNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.ROBBER_NPC, WorldEventNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.WILDLING_RAIDER_NPC, WorldEventNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.HOSTAGE_NPC, WorldEventNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.TOURNEY_NPC, WorldEventNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.CHILD_NPC, DynastyNpcRenderer::new);
        EntityRendererRegistry.register(GotEntities.DYNASTY_NPC, DynastyNpcRenderer::new);
    }

    private static void registerKingdomNpcRenderer(GotEntities.KingdomNpcEntityRegistration registration) {
        EntityRendererRegistry.register(registration.entityType(), context -> new KingdomNpcRenderer(context, registration.kingdomId()));
    }
}

