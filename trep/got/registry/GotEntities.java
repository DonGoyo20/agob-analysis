/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.biome.v1.BiomeModifications
 *  net.fabricmc.fabric.api.biome.v1.BiomeSelectors
 *  net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry
 *  net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder
 *  net.minecraft.class_1299
 *  net.minecraft.class_1299$class_4049
 *  net.minecraft.class_1308
 *  net.minecraft.class_1311
 *  net.minecraft.class_1317
 *  net.minecraft.class_1495
 *  net.minecraft.class_1498
 *  net.minecraft.class_1500
 *  net.minecraft.class_1936
 *  net.minecraft.class_1959
 *  net.minecraft.class_2338
 *  net.minecraft.class_2378
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2902$class_2903
 *  net.minecraft.class_2960
 *  net.minecraft.class_3730
 *  net.minecraft.class_4048
 *  net.minecraft.class_5132$class_5133
 *  net.minecraft.class_5321
 *  net.minecraft.class_5425
 *  net.minecraft.class_5819
 *  net.minecraft.class_7923
 *  net.minecraft.class_9168
 *  net.minecraft.class_9169
 */
package trep.got.registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1311;
import net.minecraft.class_1317;
import net.minecraft.class_1495;
import net.minecraft.class_1498;
import net.minecraft.class_1500;
import net.minecraft.class_1936;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2902;
import net.minecraft.class_2960;
import net.minecraft.class_3730;
import net.minecraft.class_4048;
import net.minecraft.class_5132;
import net.minecraft.class_5321;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_7923;
import net.minecraft.class_9168;
import net.minecraft.class_9169;
import trep.got.castle.CastleInstance;
import trep.got.castle.CastleWorldState;
import trep.got.entity.AbstractKingdomArenaMasterNpcEntity;
import trep.got.entity.AbstractKingdomBlacksmithNpcEntity;
import trep.got.entity.AbstractKingdomCaptainNpcEntity;
import trep.got.entity.AbstractKingdomCartRiderNpcEntity;
import trep.got.entity.AbstractKingdomFishermanNpcEntity;
import trep.got.entity.AbstractKingdomGuardNpcEntity;
import trep.got.entity.AbstractKingdomHumanoidEntity;
import trep.got.entity.AbstractKingdomLordNpcEntity;
import trep.got.entity.AbstractKingdomNpcEntity;
import trep.got.entity.AbstractKingdomVillageElderNpcEntity;
import trep.got.entity.AbstractTourneyNpcEntity;
import trep.got.entity.AbstractWorldEventNpcEntity;
import trep.got.entity.CartEntity;
import trep.got.entity.CatapultEntity;
import trep.got.entity.CatapultMunitionEntity;
import trep.got.entity.ChildNpcEntity;
import trep.got.entity.ConfigurableKingdomArenaMasterNpcEntity;
import trep.got.entity.ConfigurableKingdomBlacksmithNpcEntity;
import trep.got.entity.ConfigurableKingdomCaptainNpcEntity;
import trep.got.entity.ConfigurableKingdomCartRiderNpcEntity;
import trep.got.entity.ConfigurableKingdomFishermanNpcEntity;
import trep.got.entity.ConfigurableKingdomGuardNpcEntity;
import trep.got.entity.ConfigurableKingdomLordNpcEntity;
import trep.got.entity.ConfigurableKingdomNpcEntity;
import trep.got.entity.ConfigurableKingdomVillageElderNpcEntity;
import trep.got.entity.CrownlandsBlacksmithNpcEntity;
import trep.got.entity.CrownlandsCaptainNpcEntity;
import trep.got.entity.CrownlandsCartRiderNpcEntity;
import trep.got.entity.CrownlandsFishermanNpcEntity;
import trep.got.entity.CrownlandsGuardNpcEntity;
import trep.got.entity.CrownlandsLordNpcEntity;
import trep.got.entity.CrownlandsNpcEntity;
import trep.got.entity.CrownlandsVillageElderNpcEntity;
import trep.got.entity.DeerEntity;
import trep.got.entity.DestrierEntity;
import trep.got.entity.DirewolfEntity;
import trep.got.entity.DorneBlacksmithNpcEntity;
import trep.got.entity.DorneCaptainNpcEntity;
import trep.got.entity.DorneCartRiderNpcEntity;
import trep.got.entity.DorneFishermanNpcEntity;
import trep.got.entity.DorneGuardNpcEntity;
import trep.got.entity.DorneLordNpcEntity;
import trep.got.entity.DorneNpcEntity;
import trep.got.entity.DorneVillageElderNpcEntity;
import trep.got.entity.DragonEntity;
import trep.got.entity.DragonFireEntity;
import trep.got.entity.DynamicTroopEntity;
import trep.got.entity.DynastyNpcEntity;
import trep.got.entity.GotDonkeyEntity;
import trep.got.entity.HireableUnitEntity;
import trep.got.entity.HostageNpcEntity;
import trep.got.entity.IronBornRaidersNpcEntity;
import trep.got.entity.IronIslandsBlacksmithNpcEntity;
import trep.got.entity.IronIslandsCaptainNpcEntity;
import trep.got.entity.IronIslandsCartRiderNpcEntity;
import trep.got.entity.IronIslandsFishermanNpcEntity;
import trep.got.entity.IronIslandsGuardNpcEntity;
import trep.got.entity.IronIslandsLordNpcEntity;
import trep.got.entity.IronIslandsNpcEntity;
import trep.got.entity.IronIslandsVillageElderNpcEntity;
import trep.got.entity.NightsWatchCartRiderNpcEntity;
import trep.got.entity.NightsWatchGuardNpcEntity;
import trep.got.entity.NightsWatchLordNpcEntity;
import trep.got.entity.NightsWatchNpcEntity;
import trep.got.entity.NightsWatchVillageElderNpcEntity;
import trep.got.entity.NorthBlacksmithNpcEntity;
import trep.got.entity.NorthCaptainNpcEntity;
import trep.got.entity.NorthCartRiderNpcEntity;
import trep.got.entity.NorthFishermanNpcEntity;
import trep.got.entity.NorthGuardNpcEntity;
import trep.got.entity.NorthLordNpcEntity;
import trep.got.entity.NorthNpcEntity;
import trep.got.entity.NorthVillageElderNpcEntity;
import trep.got.entity.PackhorseEntity;
import trep.got.entity.PartyHorseEntity;
import trep.got.entity.PartyTroopEntity;
import trep.got.entity.ReachBlacksmithNpcEntity;
import trep.got.entity.ReachCaptainNpcEntity;
import trep.got.entity.ReachCartRiderNpcEntity;
import trep.got.entity.ReachFishermanNpcEntity;
import trep.got.entity.ReachGuardNpcEntity;
import trep.got.entity.ReachLordNpcEntity;
import trep.got.entity.ReachNpcEntity;
import trep.got.entity.ReachVillageElderNpcEntity;
import trep.got.entity.RiverlandsBlacksmithNpcEntity;
import trep.got.entity.RiverlandsCaptainNpcEntity;
import trep.got.entity.RiverlandsCartRiderNpcEntity;
import trep.got.entity.RiverlandsFishermanNpcEntity;
import trep.got.entity.RiverlandsGuardNpcEntity;
import trep.got.entity.RiverlandsLordNpcEntity;
import trep.got.entity.RiverlandsNpcEntity;
import trep.got.entity.RiverlandsVillageElderNpcEntity;
import trep.got.entity.RobberNpcEntity;
import trep.got.entity.RounceyEntity;
import trep.got.entity.ScorpionCrossbowEntity;
import trep.got.entity.SeatEntity;
import trep.got.entity.SettlerNpcEntity;
import trep.got.entity.ShipCollisionPartEntity;
import trep.got.entity.ShipEntity;
import trep.got.entity.StormlandsBlacksmithNpcEntity;
import trep.got.entity.StormlandsCaptainNpcEntity;
import trep.got.entity.StormlandsCartRiderNpcEntity;
import trep.got.entity.StormlandsFishermanNpcEntity;
import trep.got.entity.StormlandsGuardNpcEntity;
import trep.got.entity.StormlandsLordNpcEntity;
import trep.got.entity.StormlandsNpcEntity;
import trep.got.entity.StormlandsVillageElderNpcEntity;
import trep.got.entity.SumpterEntity;
import trep.got.entity.TentEntity;
import trep.got.entity.TwoHorseCartEntity;
import trep.got.entity.ValeBlacksmithNpcEntity;
import trep.got.entity.ValeCaptainNpcEntity;
import trep.got.entity.ValeCartRiderNpcEntity;
import trep.got.entity.ValeFishermanNpcEntity;
import trep.got.entity.ValeGuardNpcEntity;
import trep.got.entity.ValeLordNpcEntity;
import trep.got.entity.ValeNpcEntity;
import trep.got.entity.ValeVillageElderNpcEntity;
import trep.got.entity.WarPlaceholderEntity;
import trep.got.entity.WesterlandsBlacksmithNpcEntity;
import trep.got.entity.WesterlandsCaptainNpcEntity;
import trep.got.entity.WesterlandsCartRiderNpcEntity;
import trep.got.entity.WesterlandsFishermanNpcEntity;
import trep.got.entity.WesterlandsGuardNpcEntity;
import trep.got.entity.WesterlandsLordNpcEntity;
import trep.got.entity.WesterlandsNpcEntity;
import trep.got.entity.WesterlandsVillageElderNpcEntity;
import trep.got.entity.WildlingRaiderNpcEntity;
import trep.got.kingdom.Kingdom;
import trep.got.registry.GotKingdoms;
import trep.got.world.biome.GotBiomeKeys;
import trep.got.world.spawn.PassiveSpawnDefinitions;
import trep.got.world.spawn.PassiveSpawnRules;

public final class GotEntities {
    public static final class_1299<DynamicTroopEntity> DYNAMIC_TROOP = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"dynamic_troop"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DynamicTroopEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<HireableUnitEntity> HIREABLE_UNIT = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"hireable_unit"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(HireableUnitEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<SettlerNpcEntity> SETTLER_NPC = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"settler_npc"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(SettlerNpcEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<PartyTroopEntity> PARTY_TROOP = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"party_troop"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(PartyTroopEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<PartyHorseEntity> PARTY_HORSE = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"party_horse"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(PartyHorseEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.6f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<SumpterEntity> SUMPTER = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"sumpter"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(SumpterEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.6f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<RounceyEntity> ROUNCEY = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"rouncey"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(RounceyEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.6f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<DestrierEntity> DESTRIER = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"destrier"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DestrierEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.6f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<GotDonkeyEntity> GOT_DONKEY = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"got_donkey"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(GotDonkeyEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.5f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<PackhorseEntity> PACKHORSE = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"packhorse"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(PackhorseEntity::new).dimensions(class_4048.method_18385((float)1.3964844f, (float)1.6f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<DeerEntity> DEER = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"deer"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DeerEntity::new).dimensions(class_4048.method_18385((float)0.9f, (float)1.35f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<DirewolfEntity> DIREWOLF = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"direwolf"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DirewolfEntity::new).dimensions(class_4048.method_18385((float)0.85f, (float)1.1f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<DragonEntity> DRAGON = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"dragon"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DragonEntity::new).dimensions(class_4048.method_18385((float)4.75f, (float)3.2f)).trackRangeBlocks(96).trackedUpdateRate(2).build());
    public static final class_1299<DragonFireEntity> DRAGON_FIRE = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"dragon_fire"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, DragonFireEntity::new).dimensions(class_4048.method_18385((float)0.2f, (float)0.2f)).trackRangeBlocks(64).trackedUpdateRate(1).build());
    public static final class_1299<WarPlaceholderEntity> WAR_PLACEHOLDER = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"war_placeholder"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_17715).entityFactory(WarPlaceholderEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(32).trackedUpdateRate(10).build());
    public static final class_1299<TentEntity> TENT = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"tent"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, TentEntity::new).dimensions(class_4048.method_18385((float)2.0f, (float)3.0f)).trackRangeBlocks(64).trackedUpdateRate(1).build());
    public static final class_1299<CartEntity> CART = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"cart"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, CartEntity::new).dimensions(class_4048.method_18385((float)2.2f, (float)1.8f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<TwoHorseCartEntity> CART_2HORSE = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"cart_2horse"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, TwoHorseCartEntity::new).dimensions(class_4048.method_18385((float)2.2f, (float)1.8f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<CatapultEntity> CATAPULT = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"catapult"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, CatapultEntity::new).dimensions(class_4048.method_18385((float)2.2f, (float)1.8f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<CatapultMunitionEntity> CATAPULT_MUNITION = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"catapult_munition"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, CatapultMunitionEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)0.6f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<ScorpionCrossbowEntity> SCORPION_CROSSBOW = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"scorpion_crossbow"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, ScorpionCrossbowEntity::new).dimensions(class_4048.method_18385((float)2.2f, (float)1.8f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<ShipEntity> SHIP = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"ship"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, ShipEntity::new).dimensions(class_4048.method_18385((float)2.2f, (float)3.0f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<ShipEntity> GALLEY = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"galley"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, ShipEntity::new).dimensions(class_4048.method_18385((float)2.6f, (float)3.0f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<ShipEntity> LONGSHIP = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"longship"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, ShipEntity::new).dimensions(class_4048.method_18385((float)3.4f, (float)5.0f)).trackRangeBlocks(128).trackedUpdateRate(1).build());
    public static final class_1299<ShipCollisionPartEntity> SHIP_COLLISION_PART = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"ship_collision_part"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, ShipCollisionPartEntity::new).dimensions(class_4048.method_18385((float)0.1f, (float)0.1f)).trackRangeBlocks(96).trackedUpdateRate(1).build());
    public static final class_1299<SeatEntity> SEAT = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"seat"), (Object)FabricEntityTypeBuilder.create((class_1311)class_1311.field_17715, SeatEntity::new).dimensions(class_4048.method_18385((float)0.01f, (float)0.01f)).trackRangeBlocks(16).trackedUpdateRate(10).build());
    public static final class_1299<NorthNpcEntity> NORTH_NPC = GotEntities.register("north_npc", NorthNpcEntity::new);
    public static final class_1299<ValeNpcEntity> VALE_NPC = GotEntities.register("vale_npc", ValeNpcEntity::new);
    public static final class_1299<RiverlandsNpcEntity> RIVERLANDS_NPC = GotEntities.register("riverlands_npc", RiverlandsNpcEntity::new);
    public static final class_1299<WesterlandsNpcEntity> WESTERLANDS_NPC = GotEntities.register("westerlands_npc", WesterlandsNpcEntity::new);
    public static final class_1299<IronIslandsNpcEntity> IRON_ISLANDS_NPC = GotEntities.register("iron_islands_npc", IronIslandsNpcEntity::new);
    public static final class_1299<ReachNpcEntity> REACH_NPC = GotEntities.register("reach_npc", ReachNpcEntity::new);
    public static final class_1299<StormlandsNpcEntity> STORMLANDS_NPC = GotEntities.register("stormlands_npc", StormlandsNpcEntity::new);
    public static final class_1299<DorneNpcEntity> DORNE_NPC = GotEntities.register("dorne_npc", DorneNpcEntity::new);
    public static final class_1299<CrownlandsNpcEntity> CROWNLANDS_NPC = GotEntities.register("crownlands_npc", CrownlandsNpcEntity::new);
    public static final class_1299<NightsWatchNpcEntity> NIGHTS_WATCH_NPC = GotEntities.register("nights_watch_npc", NightsWatchNpcEntity::new);
    public static final class_1299<NorthGuardNpcEntity> NORTH_GUARD_NPC = GotEntities.register("north_guard_npc", NorthGuardNpcEntity::new);
    public static final class_1299<ValeGuardNpcEntity> VALE_GUARD_NPC = GotEntities.register("vale_guard_npc", ValeGuardNpcEntity::new);
    public static final class_1299<RiverlandsGuardNpcEntity> RIVERLANDS_GUARD_NPC = GotEntities.register("riverlands_guard_npc", RiverlandsGuardNpcEntity::new);
    public static final class_1299<WesterlandsGuardNpcEntity> WESTERLANDS_GUARD_NPC = GotEntities.register("westerlands_guard_npc", WesterlandsGuardNpcEntity::new);
    public static final class_1299<IronIslandsGuardNpcEntity> IRON_ISLANDS_GUARD_NPC = GotEntities.register("iron_islands_guard_npc", IronIslandsGuardNpcEntity::new);
    public static final class_1299<ReachGuardNpcEntity> REACH_GUARD_NPC = GotEntities.register("reach_guard_npc", ReachGuardNpcEntity::new);
    public static final class_1299<StormlandsGuardNpcEntity> STORMLANDS_GUARD_NPC = GotEntities.register("stormlands_guard_npc", StormlandsGuardNpcEntity::new);
    public static final class_1299<DorneGuardNpcEntity> DORNE_GUARD_NPC = GotEntities.register("dorne_guard_npc", DorneGuardNpcEntity::new);
    public static final class_1299<CrownlandsGuardNpcEntity> CROWNLANDS_GUARD_NPC = GotEntities.register("crownlands_guard_npc", CrownlandsGuardNpcEntity::new);
    public static final class_1299<NightsWatchGuardNpcEntity> NIGHTS_WATCH_GUARD_NPC = GotEntities.register("nights_watch_guard_npc", NightsWatchGuardNpcEntity::new);
    public static final class_1299<NorthLordNpcEntity> NORTH_LORD_NPC = GotEntities.register("north_lord_npc", NorthLordNpcEntity::new);
    public static final class_1299<ValeLordNpcEntity> VALE_LORD_NPC = GotEntities.register("vale_lord_npc", ValeLordNpcEntity::new);
    public static final class_1299<RiverlandsLordNpcEntity> RIVERLANDS_LORD_NPC = GotEntities.register("riverlands_lord_npc", RiverlandsLordNpcEntity::new);
    public static final class_1299<WesterlandsLordNpcEntity> WESTERLANDS_LORD_NPC = GotEntities.register("westerlands_lord_npc", WesterlandsLordNpcEntity::new);
    public static final class_1299<IronIslandsLordNpcEntity> IRON_ISLANDS_LORD_NPC = GotEntities.register("iron_islands_lord_npc", IronIslandsLordNpcEntity::new);
    public static final class_1299<ReachLordNpcEntity> REACH_LORD_NPC = GotEntities.register("reach_lord_npc", ReachLordNpcEntity::new);
    public static final class_1299<StormlandsLordNpcEntity> STORMLANDS_LORD_NPC = GotEntities.register("stormlands_lord_npc", StormlandsLordNpcEntity::new);
    public static final class_1299<DorneLordNpcEntity> DORNE_LORD_NPC = GotEntities.register("dorne_lord_npc", DorneLordNpcEntity::new);
    public static final class_1299<CrownlandsLordNpcEntity> CROWNLANDS_LORD_NPC = GotEntities.register("crownlands_lord_npc", CrownlandsLordNpcEntity::new);
    public static final class_1299<NightsWatchLordNpcEntity> NIGHTS_WATCH_LORD_NPC = GotEntities.register("nights_watch_lord_npc", NightsWatchLordNpcEntity::new);
    public static final class_1299<NorthVillageElderNpcEntity> NORTH_VILLAGE_ELDER_NPC = GotEntities.register("north_village_elder_npc", NorthVillageElderNpcEntity::new);
    public static final class_1299<ValeVillageElderNpcEntity> VALE_VILLAGE_ELDER_NPC = GotEntities.register("vale_village_elder_npc", ValeVillageElderNpcEntity::new);
    public static final class_1299<RiverlandsVillageElderNpcEntity> RIVERLANDS_VILLAGE_ELDER_NPC = GotEntities.register("riverlands_village_elder_npc", RiverlandsVillageElderNpcEntity::new);
    public static final class_1299<WesterlandsVillageElderNpcEntity> WESTERLANDS_VILLAGE_ELDER_NPC = GotEntities.register("westerlands_village_elder_npc", WesterlandsVillageElderNpcEntity::new);
    public static final class_1299<IronIslandsVillageElderNpcEntity> IRON_ISLANDS_VILLAGE_ELDER_NPC = GotEntities.register("iron_islands_village_elder_npc", IronIslandsVillageElderNpcEntity::new);
    public static final class_1299<ReachVillageElderNpcEntity> REACH_VILLAGE_ELDER_NPC = GotEntities.register("reach_village_elder_npc", ReachVillageElderNpcEntity::new);
    public static final class_1299<StormlandsVillageElderNpcEntity> STORMLANDS_VILLAGE_ELDER_NPC = GotEntities.register("stormlands_village_elder_npc", StormlandsVillageElderNpcEntity::new);
    public static final class_1299<DorneVillageElderNpcEntity> DORNE_VILLAGE_ELDER_NPC = GotEntities.register("dorne_village_elder_npc", DorneVillageElderNpcEntity::new);
    public static final class_1299<CrownlandsVillageElderNpcEntity> CROWNLANDS_VILLAGE_ELDER_NPC = GotEntities.register("crownlands_village_elder_npc", CrownlandsVillageElderNpcEntity::new);
    public static final class_1299<NightsWatchVillageElderNpcEntity> NIGHTS_WATCH_VILLAGE_ELDER_NPC = GotEntities.register("nights_watch_village_elder_npc", NightsWatchVillageElderNpcEntity::new);
    public static final class_1299<NorthBlacksmithNpcEntity> NORTH_BLACKSMITH_NPC = GotEntities.register("north_blacksmith_npc", NorthBlacksmithNpcEntity::new);
    public static final class_1299<ValeBlacksmithNpcEntity> VALE_BLACKSMITH_NPC = GotEntities.register("vale_blacksmith_npc", ValeBlacksmithNpcEntity::new);
    public static final class_1299<RiverlandsBlacksmithNpcEntity> RIVERLANDS_BLACKSMITH_NPC = GotEntities.register("riverlands_blacksmith_npc", RiverlandsBlacksmithNpcEntity::new);
    public static final class_1299<WesterlandsBlacksmithNpcEntity> WESTERLANDS_BLACKSMITH_NPC = GotEntities.register("westerlands_blacksmith_npc", WesterlandsBlacksmithNpcEntity::new);
    public static final class_1299<IronIslandsBlacksmithNpcEntity> IRON_ISLANDS_BLACKSMITH_NPC = GotEntities.register("iron_islands_blacksmith_npc", IronIslandsBlacksmithNpcEntity::new);
    public static final class_1299<ReachBlacksmithNpcEntity> REACH_BLACKSMITH_NPC = GotEntities.register("reach_blacksmith_npc", ReachBlacksmithNpcEntity::new);
    public static final class_1299<StormlandsBlacksmithNpcEntity> STORMLANDS_BLACKSMITH_NPC = GotEntities.register("stormlands_blacksmith_npc", StormlandsBlacksmithNpcEntity::new);
    public static final class_1299<DorneBlacksmithNpcEntity> DORNE_BLACKSMITH_NPC = GotEntities.register("dorne_blacksmith_npc", DorneBlacksmithNpcEntity::new);
    public static final class_1299<CrownlandsBlacksmithNpcEntity> CROWNLANDS_BLACKSMITH_NPC = GotEntities.register("crownlands_blacksmith_npc", CrownlandsBlacksmithNpcEntity::new);
    public static final class_1299<NorthFishermanNpcEntity> NORTH_FISHERMAN_NPC = GotEntities.register("north_fisherman_npc", NorthFishermanNpcEntity::new);
    public static final class_1299<ValeFishermanNpcEntity> VALE_FISHERMAN_NPC = GotEntities.register("vale_fisherman_npc", ValeFishermanNpcEntity::new);
    public static final class_1299<RiverlandsFishermanNpcEntity> RIVERLANDS_FISHERMAN_NPC = GotEntities.register("riverlands_fisherman_npc", RiverlandsFishermanNpcEntity::new);
    public static final class_1299<WesterlandsFishermanNpcEntity> WESTERLANDS_FISHERMAN_NPC = GotEntities.register("westerlands_fisherman_npc", WesterlandsFishermanNpcEntity::new);
    public static final class_1299<IronIslandsFishermanNpcEntity> IRON_ISLANDS_FISHERMAN_NPC = GotEntities.register("iron_islands_fisherman_npc", IronIslandsFishermanNpcEntity::new);
    public static final class_1299<ReachFishermanNpcEntity> REACH_FISHERMAN_NPC = GotEntities.register("reach_fisherman_npc", ReachFishermanNpcEntity::new);
    public static final class_1299<StormlandsFishermanNpcEntity> STORMLANDS_FISHERMAN_NPC = GotEntities.register("stormlands_fisherman_npc", StormlandsFishermanNpcEntity::new);
    public static final class_1299<DorneFishermanNpcEntity> DORNE_FISHERMAN_NPC = GotEntities.register("dorne_fisherman_npc", DorneFishermanNpcEntity::new);
    public static final class_1299<CrownlandsFishermanNpcEntity> CROWNLANDS_FISHERMAN_NPC = GotEntities.register("crownlands_fisherman_npc", CrownlandsFishermanNpcEntity::new);
    public static final class_1299<NorthCartRiderNpcEntity> NORTH_CART_RIDER_NPC = GotEntities.register("north_cart_rider_npc", NorthCartRiderNpcEntity::new);
    public static final class_1299<ValeCartRiderNpcEntity> VALE_CART_RIDER_NPC = GotEntities.register("vale_cart_rider_npc", ValeCartRiderNpcEntity::new);
    public static final class_1299<RiverlandsCartRiderNpcEntity> RIVERLANDS_CART_RIDER_NPC = GotEntities.register("riverlands_cart_rider_npc", RiverlandsCartRiderNpcEntity::new);
    public static final class_1299<WesterlandsCartRiderNpcEntity> WESTERLANDS_CART_RIDER_NPC = GotEntities.register("westerlands_cart_rider_npc", WesterlandsCartRiderNpcEntity::new);
    public static final class_1299<IronIslandsCartRiderNpcEntity> IRON_ISLANDS_CART_RIDER_NPC = GotEntities.register("iron_islands_cart_rider_npc", IronIslandsCartRiderNpcEntity::new);
    public static final class_1299<ReachCartRiderNpcEntity> REACH_CART_RIDER_NPC = GotEntities.register("reach_cart_rider_npc", ReachCartRiderNpcEntity::new);
    public static final class_1299<StormlandsCartRiderNpcEntity> STORMLANDS_CART_RIDER_NPC = GotEntities.register("stormlands_cart_rider_npc", StormlandsCartRiderNpcEntity::new);
    public static final class_1299<DorneCartRiderNpcEntity> DORNE_CART_RIDER_NPC = GotEntities.register("dorne_cart_rider_npc", DorneCartRiderNpcEntity::new);
    public static final class_1299<CrownlandsCartRiderNpcEntity> CROWNLANDS_CART_RIDER_NPC = GotEntities.register("crownlands_cart_rider_npc", CrownlandsCartRiderNpcEntity::new);
    public static final class_1299<NightsWatchCartRiderNpcEntity> NIGHTS_WATCH_CART_RIDER_NPC = GotEntities.register("nights_watch_cart_rider_npc", NightsWatchCartRiderNpcEntity::new);
    public static final class_1299<NorthCaptainNpcEntity> NORTH_CAPTAIN_NPC = GotEntities.register("north_captain_npc", NorthCaptainNpcEntity::new);
    public static final class_1299<ValeCaptainNpcEntity> VALE_CAPTAIN_NPC = GotEntities.register("vale_captain_npc", ValeCaptainNpcEntity::new);
    public static final class_1299<RiverlandsCaptainNpcEntity> RIVERLANDS_CAPTAIN_NPC = GotEntities.register("riverlands_captain_npc", RiverlandsCaptainNpcEntity::new);
    public static final class_1299<WesterlandsCaptainNpcEntity> WESTERLANDS_CAPTAIN_NPC = GotEntities.register("westerlands_captain_npc", WesterlandsCaptainNpcEntity::new);
    public static final class_1299<IronIslandsCaptainNpcEntity> IRON_ISLANDS_CAPTAIN_NPC = GotEntities.register("iron_islands_captain_npc", IronIslandsCaptainNpcEntity::new);
    public static final class_1299<ReachCaptainNpcEntity> REACH_CAPTAIN_NPC = GotEntities.register("reach_captain_npc", ReachCaptainNpcEntity::new);
    public static final class_1299<StormlandsCaptainNpcEntity> STORMLANDS_CAPTAIN_NPC = GotEntities.register("stormlands_captain_npc", StormlandsCaptainNpcEntity::new);
    public static final class_1299<DorneCaptainNpcEntity> DORNE_CAPTAIN_NPC = GotEntities.register("dorne_captain_npc", DorneCaptainNpcEntity::new);
    public static final class_1299<CrownlandsCaptainNpcEntity> CROWNLANDS_CAPTAIN_NPC = GotEntities.register("crownlands_captain_npc", CrownlandsCaptainNpcEntity::new);
    public static final class_1299<IronBornRaidersNpcEntity> IRON_BORN_RAIDERS_NPC = GotEntities.registerWorldEventNpc("iron_born_raiders_npc", IronBornRaidersNpcEntity::new);
    public static final class_1299<RobberNpcEntity> ROBBER_NPC = GotEntities.registerHostileWorldEventNpc("robber_npc", RobberNpcEntity::new);
    public static final class_1299<WildlingRaiderNpcEntity> WILDLING_RAIDER_NPC = GotEntities.registerHostileWorldEventNpc("wildling_raider_npc", WildlingRaiderNpcEntity::new);
    public static final class_1299<HostageNpcEntity> HOSTAGE_NPC = GotEntities.registerWorldEventNpc("hostage_npc", HostageNpcEntity::new);
    public static final class_1299<AbstractTourneyNpcEntity> TOURNEY_NPC = GotEntities.registerHostileWorldEventNpc("tourney_npc", AbstractTourneyNpcEntity::new);
    public static final class_1299<ChildNpcEntity> CHILD_NPC = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"child_npc"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(ChildNpcEntity::new).dimensions(class_4048.method_18385((float)0.39f, (float)1.17f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    public static final class_1299<DynastyNpcEntity> DYNASTY_NPC = (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)"dynasty_npc"), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(DynastyNpcEntity::new).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    private static final Map<String, class_1299<? extends AbstractKingdomNpcEntity>> CONFIGURED_CIVILIAN_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomGuardNpcEntity>> CONFIGURED_GUARD_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomGuardNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomLordNpcEntity>> CONFIGURED_LORD_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomLordNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomVillageElderNpcEntity>> CONFIGURED_ELDER_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomVillageElderNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomBlacksmithNpcEntity>> CONFIGURED_BLACKSMITH_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomBlacksmithNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomArenaMasterNpcEntity>> CONFIGURED_ARENA_MASTER_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomArenaMasterNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomFishermanNpcEntity>> CONFIGURED_FISHERMAN_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomFishermanNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomCartRiderNpcEntity>> CONFIGURED_CART_RIDER_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomCartRiderNpcEntity>>();
    private static final Map<String, class_1299<? extends AbstractKingdomCaptainNpcEntity>> CONFIGURED_CAPTAIN_TYPES = new LinkedHashMap<String, class_1299<? extends AbstractKingdomCaptainNpcEntity>>();

    private GotEntities() {
    }

    public static List<KingdomNpcEntityRegistration> configuredKingdomNpcRegistrations() {
        ArrayList<KingdomNpcEntityRegistration> registrations = new ArrayList<KingdomNpcEntityRegistration>();
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_CIVILIAN_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_GUARD_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_LORD_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_ELDER_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_BLACKSMITH_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_ARENA_MASTER_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_FISHERMAN_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_CART_RIDER_TYPES);
        GotEntities.addConfiguredKingdomNpcRegistrations(registrations, CONFIGURED_CAPTAIN_TYPES);
        return List.copyOf(registrations);
    }

    private static void addConfiguredKingdomNpcRegistrations(List<KingdomNpcEntityRegistration> registrations, Map<String, ? extends class_1299<? extends AbstractKingdomNpcEntity>> types) {
        for (Map.Entry<String, ? extends class_1299<? extends AbstractKingdomNpcEntity>> entry : types.entrySet()) {
            registrations.add(new KingdomNpcEntityRegistration(entry.getValue(), class_2960.method_60655((String)"agob", (String)entry.getKey())));
        }
    }

    private static void registerConfiguredKingdomNpcSet(String kingdomPath) {
        class_2960 kingdomId = class_2960.method_60655((String)"agob", (String)kingdomPath);
        CONFIGURED_CIVILIAN_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_npc", (type, world) -> new ConfigurableKingdomNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_GUARD_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_guard_npc", (type, world) -> new ConfigurableKingdomGuardNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_LORD_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_lord_npc", (type, world) -> new ConfigurableKingdomLordNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_ELDER_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_village_elder_npc", (type, world) -> new ConfigurableKingdomVillageElderNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_BLACKSMITH_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_blacksmith_npc", (type, world) -> new ConfigurableKingdomBlacksmithNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_FISHERMAN_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_fisherman_npc", (type, world) -> new ConfigurableKingdomFishermanNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_CART_RIDER_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_cart_rider_npc", (type, world) -> new ConfigurableKingdomCartRiderNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
        CONFIGURED_CAPTAIN_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_captain_npc", (type, world) -> new ConfigurableKingdomCaptainNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
    }

    private static void registerConfiguredArenaMasterNpcSet(String kingdomPath) {
        class_2960 kingdomId = class_2960.method_60655((String)"agob", (String)kingdomPath);
        CONFIGURED_ARENA_MASTER_TYPES.put(kingdomPath, GotEntities.register(kingdomPath + "_arena_master_npc", (type, world) -> new ConfigurableKingdomArenaMasterNpcEntity((class_1299<? extends AbstractKingdomNpcEntity>)type, world, kingdomId)));
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(DYNAMIC_TROOP, (class_5132.class_5133)AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes());
        FabricDefaultAttributeRegistry.register(HIREABLE_UNIT, (class_5132.class_5133)AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes());
        FabricDefaultAttributeRegistry.register(SETTLER_NPC, (class_5132.class_5133)AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes());
        FabricDefaultAttributeRegistry.register(PARTY_TROOP, (class_5132.class_5133)AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes());
        FabricDefaultAttributeRegistry.register(PARTY_HORSE, (class_5132.class_5133)PartyHorseEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(SUMPTER, (class_5132.class_5133)class_1498.method_26899());
        FabricDefaultAttributeRegistry.register(ROUNCEY, (class_5132.class_5133)class_1498.method_26899());
        FabricDefaultAttributeRegistry.register(DESTRIER, (class_5132.class_5133)class_1498.method_26899());
        FabricDefaultAttributeRegistry.register(GOT_DONKEY, (class_5132.class_5133)class_1495.method_26899());
        FabricDefaultAttributeRegistry.register(PACKHORSE, (class_5132.class_5133)class_1500.method_26899());
        FabricDefaultAttributeRegistry.register(DEER, (class_5132.class_5133)DeerEntity.createDeerAttributes());
        FabricDefaultAttributeRegistry.register(DIREWOLF, (class_5132.class_5133)DirewolfEntity.createDirewolfAttributes());
        FabricDefaultAttributeRegistry.register(DRAGON, (class_5132.class_5133)DragonEntity.createDragonAttributes());
        FabricDefaultAttributeRegistry.register(WAR_PLACEHOLDER, (class_5132.class_5133)AbstractKingdomHumanoidEntity.createKingdomHumanoidAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTS_WATCH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTS_WATCH_GUARD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTS_WATCH_LORD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTS_WATCH_VILLAGE_ELDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_BLACKSMITH_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_FISHERMAN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NIGHTS_WATCH_CART_RIDER_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(NORTH_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(VALE_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(RIVERLANDS_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(WESTERLANDS_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(IRON_ISLANDS_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(REACH_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(STORMLANDS_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DORNE_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(CROWNLANDS_CAPTAIN_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        GotEntities.registerConfiguredKingdomNpcAttributes();
        FabricDefaultAttributeRegistry.register(IRON_BORN_RAIDERS_NPC, (class_5132.class_5133)AbstractWorldEventNpcEntity.createWorldEventNpcAttributes());
        FabricDefaultAttributeRegistry.register(ROBBER_NPC, (class_5132.class_5133)RobberNpcEntity.createRobberAttributes());
        FabricDefaultAttributeRegistry.register(WILDLING_RAIDER_NPC, (class_5132.class_5133)WildlingRaiderNpcEntity.createWildlingRaiderAttributes());
        FabricDefaultAttributeRegistry.register(HOSTAGE_NPC, (class_5132.class_5133)AbstractWorldEventNpcEntity.createWorldEventNpcAttributes());
        FabricDefaultAttributeRegistry.register(TOURNEY_NPC, (class_5132.class_5133)AbstractTourneyNpcEntity.createTourneyAttributes());
        FabricDefaultAttributeRegistry.register(CHILD_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        FabricDefaultAttributeRegistry.register(DYNASTY_NPC, (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        GotEntities.registerPassiveSpawnRestrictions();
        GotEntities.registerHostileNpcSpawnRestrictions();
        PassiveSpawnDefinitions.register(SUMPTER, ROUNCEY, DESTRIER, GOT_DONKEY, PACKHORSE, DEER, DIREWOLF);
        GotEntities.registerHostileNpcSpawns();
    }

    private static void registerConfiguredKingdomNpcAttributes() {
        for (KingdomNpcEntityRegistration registration : GotEntities.configuredKingdomNpcRegistrations()) {
            FabricDefaultAttributeRegistry.register(registration.entityType(), (class_5132.class_5133)AbstractKingdomNpcEntity.createKingdomNpcAttributes());
        }
    }

    private static void registerPassiveSpawnRestrictions() {
        class_1317.method_20637(SUMPTER, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canMountSpawn);
        class_1317.method_20637(ROUNCEY, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canMountSpawn);
        class_1317.method_20637(DESTRIER, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canMountSpawn);
        class_1317.method_20637(GOT_DONKEY, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canMountSpawn);
        class_1317.method_20637(PACKHORSE, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canMountSpawn);
        class_1317.method_20637(DEER, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canDeerSpawn);
        class_1317.method_20637(DIREWOLF, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, PassiveSpawnRules::canDirewolfSpawn);
    }

    private static void registerHostileNpcSpawnRestrictions() {
        class_1317.method_20637(ROBBER_NPC, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, GotEntities::canRobberSpawn);
        class_1317.method_20637(WILDLING_RAIDER_NPC, (class_9168)class_9169.field_48745, (class_2902.class_2903)class_2902.class_2903.field_13203, GotEntities::canWildlingRaiderSpawn);
    }

    private static void registerHostileNpcSpawns() {
        class_5321[] banditBiomes = (class_5321[])GotBiomeKeys.ALL.stream().filter(key -> key != GotBiomeKeys.SEA && key != GotBiomeKeys.THE_WALL).filter(key -> !GotEntities.isWildlingRaiderSpawnBiome((class_5321<class_1959>)key)).toArray(class_5321[]::new);
        BiomeModifications.addSpawn((Predicate)BiomeSelectors.includeByKey((class_5321[])banditBiomes), (class_1311)class_1311.field_6302, ROBBER_NPC, (int)1, (int)1, (int)2);
        BiomeModifications.addSpawn((Predicate)BiomeSelectors.includeByKey(GotEntities.wildlingRaiderBiomes()), (class_1311)class_1311.field_6302, WILDLING_RAIDER_NPC, (int)2, (int)1, (int)3);
    }

    private static boolean canRobberSpawn(class_1299<RobberNpcEntity> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        if (!class_1308.method_20636(entityType, (class_1936)world, (class_3730)spawnReason, (class_2338)pos, (class_5819)random)) {
            return false;
        }
        if (GotEntities.isWildlingRaiderSpawnBiome(world, pos)) {
            return false;
        }
        if (random.method_43048(12) != 0) {
            return false;
        }
        class_238 searchBox = new class_238(pos).method_1014(32.0);
        if (!world.method_8410().method_8390(RobberNpcEntity.class, searchBox, robber -> robber.method_5805()).isEmpty()) {
            return false;
        }
        if (world.method_22339(pos) > 7 || world.method_8410().method_8530()) {
            return false;
        }
        if (CastleWorldState.get(world.method_8410()).findClaimAt(pos) != null) {
            return false;
        }
        for (CastleInstance castle : CastleWorldState.get(world.method_8410()).getAll()) {
            int radius = Math.max(96, castle.getAreaRadiusBlocks());
            if (!(castle.getBannerPos().method_10262((class_2382)pos) <= (double)radius * (double)radius)) continue;
            return false;
        }
        return true;
    }

    private static boolean canWildlingRaiderSpawn(class_1299<WildlingRaiderNpcEntity> entityType, class_5425 world, class_3730 spawnReason, class_2338 pos, class_5819 random) {
        if (!class_1308.method_20636(entityType, (class_1936)world, (class_3730)spawnReason, (class_2338)pos, (class_5819)random)) {
            return false;
        }
        if (!GotEntities.isWildlingRaiderSpawnBiome(world, pos)) {
            return false;
        }
        if (random.method_43048(10) != 0) {
            return false;
        }
        class_238 searchBox = new class_238(pos).method_1014(32.0);
        if (!world.method_8410().method_8390(WildlingRaiderNpcEntity.class, searchBox, raider -> raider.method_5805()).isEmpty()) {
            return false;
        }
        if (world.method_22339(pos) > 7 || world.method_8410().method_8530()) {
            return false;
        }
        if (CastleWorldState.get(world.method_8410()).findClaimAt(pos) != null) {
            return false;
        }
        for (CastleInstance castle : CastleWorldState.get(world.method_8410()).getAll()) {
            int radius = Math.max(96, castle.getAreaRadiusBlocks());
            if (!(castle.getBannerPos().method_10262((class_2382)pos) <= (double)radius * (double)radius)) continue;
            return false;
        }
        return true;
    }

    private static class_5321<class_1959>[] wildlingRaiderBiomes() {
        return GotEntities.biomeArray(GotBiomeKeys.THE_GIFT, GotBiomeKeys.ALWAYS_WINTER, GotBiomeKeys.ALWAYS_WINTER_FOREST, GotBiomeKeys.ALWAYS_WINTER_HILLS);
    }

    private static boolean isWildlingRaiderSpawnBiome(class_5425 world, class_2338 pos) {
        return world.method_23753(pos).method_40230().map(GotEntities::isWildlingRaiderSpawnBiome).orElse(false);
    }

    private static boolean isWildlingRaiderSpawnBiome(class_5321<class_1959> key) {
        return key.equals(GotBiomeKeys.THE_GIFT) || key.equals(GotBiomeKeys.ALWAYS_WINTER) || key.equals(GotBiomeKeys.ALWAYS_WINTER_FOREST) || key.equals(GotBiomeKeys.ALWAYS_WINTER_HILLS);
    }

    @SafeVarargs
    private static class_5321<class_1959>[] biomeArray(class_5321<class_1959> ... keys) {
        return keys;
    }

    public static class_1299<? extends AbstractKingdomNpcEntity> civilianTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_NPC;
            case "the_vale" -> VALE_NPC;
            case "the_riverlands" -> RIVERLANDS_NPC;
            case "the_westerlands" -> WESTERLANDS_NPC;
            case "iron_islands" -> IRON_ISLANDS_NPC;
            case "the_reach" -> REACH_NPC;
            case "the_stormlands" -> STORMLANDS_NPC;
            case "dorne" -> DORNE_NPC;
            case "the_crownlands" -> CROWNLANDS_NPC;
            case "the_nights_watch" -> NIGHTS_WATCH_NPC;
            default -> CONFIGURED_CIVILIAN_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomGuardNpcEntity> guardTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_GUARD_NPC;
            case "the_vale" -> VALE_GUARD_NPC;
            case "the_riverlands" -> RIVERLANDS_GUARD_NPC;
            case "the_westerlands" -> WESTERLANDS_GUARD_NPC;
            case "iron_islands" -> IRON_ISLANDS_GUARD_NPC;
            case "the_reach" -> REACH_GUARD_NPC;
            case "the_stormlands" -> STORMLANDS_GUARD_NPC;
            case "dorne" -> DORNE_GUARD_NPC;
            case "the_crownlands" -> CROWNLANDS_GUARD_NPC;
            case "the_nights_watch" -> NIGHTS_WATCH_GUARD_NPC;
            default -> CONFIGURED_GUARD_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomLordNpcEntity> lordTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_LORD_NPC;
            case "the_vale" -> VALE_LORD_NPC;
            case "the_riverlands" -> RIVERLANDS_LORD_NPC;
            case "the_westerlands" -> WESTERLANDS_LORD_NPC;
            case "iron_islands" -> IRON_ISLANDS_LORD_NPC;
            case "the_reach" -> REACH_LORD_NPC;
            case "the_stormlands" -> STORMLANDS_LORD_NPC;
            case "dorne" -> DORNE_LORD_NPC;
            case "the_crownlands" -> CROWNLANDS_LORD_NPC;
            case "the_nights_watch" -> NIGHTS_WATCH_LORD_NPC;
            default -> CONFIGURED_LORD_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomVillageElderNpcEntity> elderTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_VILLAGE_ELDER_NPC;
            case "the_vale" -> VALE_VILLAGE_ELDER_NPC;
            case "the_riverlands" -> RIVERLANDS_VILLAGE_ELDER_NPC;
            case "the_westerlands" -> WESTERLANDS_VILLAGE_ELDER_NPC;
            case "iron_islands" -> IRON_ISLANDS_VILLAGE_ELDER_NPC;
            case "the_reach" -> REACH_VILLAGE_ELDER_NPC;
            case "the_stormlands" -> STORMLANDS_VILLAGE_ELDER_NPC;
            case "dorne" -> DORNE_VILLAGE_ELDER_NPC;
            case "the_crownlands" -> CROWNLANDS_VILLAGE_ELDER_NPC;
            case "the_nights_watch" -> NIGHTS_WATCH_VILLAGE_ELDER_NPC;
            default -> CONFIGURED_ELDER_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomBlacksmithNpcEntity> blacksmithTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_BLACKSMITH_NPC;
            case "the_vale" -> VALE_BLACKSMITH_NPC;
            case "the_riverlands" -> RIVERLANDS_BLACKSMITH_NPC;
            case "the_westerlands" -> WESTERLANDS_BLACKSMITH_NPC;
            case "iron_islands" -> IRON_ISLANDS_BLACKSMITH_NPC;
            case "the_reach" -> REACH_BLACKSMITH_NPC;
            case "the_stormlands" -> STORMLANDS_BLACKSMITH_NPC;
            case "dorne" -> DORNE_BLACKSMITH_NPC;
            case "the_crownlands" -> CROWNLANDS_BLACKSMITH_NPC;
            default -> CONFIGURED_BLACKSMITH_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomArenaMasterNpcEntity> arenaMasterTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return CONFIGURED_ARENA_MASTER_TYPES.get(kingdom.getId().method_12832());
    }

    public static class_1299<? extends AbstractKingdomFishermanNpcEntity> fishermanTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_FISHERMAN_NPC;
            case "the_vale" -> VALE_FISHERMAN_NPC;
            case "the_riverlands" -> RIVERLANDS_FISHERMAN_NPC;
            case "the_westerlands" -> WESTERLANDS_FISHERMAN_NPC;
            case "iron_islands" -> IRON_ISLANDS_FISHERMAN_NPC;
            case "the_reach" -> REACH_FISHERMAN_NPC;
            case "the_stormlands" -> STORMLANDS_FISHERMAN_NPC;
            case "dorne" -> DORNE_FISHERMAN_NPC;
            case "the_crownlands" -> CROWNLANDS_FISHERMAN_NPC;
            default -> CONFIGURED_FISHERMAN_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomCartRiderNpcEntity> cartRiderTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_CART_RIDER_NPC;
            case "the_vale" -> VALE_CART_RIDER_NPC;
            case "the_riverlands" -> RIVERLANDS_CART_RIDER_NPC;
            case "the_westerlands" -> WESTERLANDS_CART_RIDER_NPC;
            case "iron_islands" -> IRON_ISLANDS_CART_RIDER_NPC;
            case "the_reach" -> REACH_CART_RIDER_NPC;
            case "the_stormlands" -> STORMLANDS_CART_RIDER_NPC;
            case "dorne" -> DORNE_CART_RIDER_NPC;
            case "the_crownlands" -> CROWNLANDS_CART_RIDER_NPC;
            case "the_nights_watch" -> NIGHTS_WATCH_CART_RIDER_NPC;
            default -> CONFIGURED_CART_RIDER_TYPES.get(kingdom.getId().method_12832());
        };
    }

    public static class_1299<? extends AbstractKingdomCaptainNpcEntity> captainTypeForKingdom(Kingdom kingdom) {
        if (kingdom == null) {
            return null;
        }
        kingdom = GotEntities.cultureKingdom(kingdom);
        return switch (kingdom.getId().method_12832()) {
            case "the_north" -> NORTH_CAPTAIN_NPC;
            case "the_vale" -> VALE_CAPTAIN_NPC;
            case "the_riverlands" -> RIVERLANDS_CAPTAIN_NPC;
            case "the_westerlands" -> WESTERLANDS_CAPTAIN_NPC;
            case "iron_islands" -> IRON_ISLANDS_CAPTAIN_NPC;
            case "the_reach" -> REACH_CAPTAIN_NPC;
            case "the_stormlands" -> STORMLANDS_CAPTAIN_NPC;
            case "dorne" -> DORNE_CAPTAIN_NPC;
            case "the_crownlands" -> CROWNLANDS_CAPTAIN_NPC;
            default -> CONFIGURED_CAPTAIN_TYPES.get(kingdom.getId().method_12832());
        };
    }

    private static Kingdom cultureKingdom(Kingdom kingdom) {
        class_2960 cultureKingdomId = GotKingdoms.cultureKingdomId(kingdom.getId());
        Kingdom cultureKingdom = cultureKingdomId == null ? null : GotKingdoms.get(cultureKingdomId);
        return cultureKingdom == null ? kingdom : cultureKingdom;
    }

    private static <T extends AbstractKingdomNpcEntity> class_1299<T> register(String path, class_1299.class_4049<T> factory) {
        return (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)path), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(factory).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    }

    private static <T extends AbstractWorldEventNpcEntity> class_1299<T> registerWorldEventNpc(String path, class_1299.class_4049<T> factory) {
        return (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)path), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6294).entityFactory(factory).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    }

    private static <T extends AbstractWorldEventNpcEntity> class_1299<T> registerHostileWorldEventNpc(String path, class_1299.class_4049<T> factory) {
        return (class_1299)class_2378.method_10230((class_2378)class_7923.field_41177, (class_2960)class_2960.method_60655((String)"agob", (String)path), (Object)FabricEntityTypeBuilder.createMob().spawnGroup(class_1311.field_6302).entityFactory(factory).dimensions(class_4048.method_18385((float)0.6f, (float)1.8f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    }

    static {
        GotEntities.registerConfiguredArenaMasterNpcSet("the_north");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_nights_watch");
        GotEntities.registerConfiguredArenaMasterNpcSet("wildling");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_vale");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_riverlands");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_westerlands");
        GotEntities.registerConfiguredArenaMasterNpcSet("iron_islands");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_reach");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_stormlands");
        GotEntities.registerConfiguredArenaMasterNpcSet("dorne");
        GotEntities.registerConfiguredArenaMasterNpcSet("the_crownlands");
        GotEntities.registerConfiguredArenaMasterNpcSet("targaryen");
        GotEntities.registerConfiguredArenaMasterNpcSet("braavos");
        GotEntities.registerConfiguredArenaMasterNpcSet("pentos");
        GotEntities.registerConfiguredArenaMasterNpcSet("volantis");
        GotEntities.registerConfiguredArenaMasterNpcSet("tyrosh");
        GotEntities.registerConfiguredArenaMasterNpcSet("myr");
        GotEntities.registerConfiguredArenaMasterNpcSet("blackfyre");
        GotEntities.registerConfiguredArenaMasterNpcSet("golden_company");
        GotEntities.registerConfiguredArenaMasterNpcSet("meereen");
        GotEntities.registerConfiguredArenaMasterNpcSet("yunkai");
        GotEntities.registerConfiguredArenaMasterNpcSet("astapor");
        GotEntities.registerConfiguredArenaMasterNpcSet("qarth");
        GotEntities.registerConfiguredKingdomNpcSet("targaryen");
        GotEntities.registerConfiguredKingdomNpcSet("braavos");
        GotEntities.registerConfiguredKingdomNpcSet("pentos");
        GotEntities.registerConfiguredKingdomNpcSet("volantis");
        GotEntities.registerConfiguredKingdomNpcSet("tyrosh");
        GotEntities.registerConfiguredKingdomNpcSet("myr");
        GotEntities.registerConfiguredKingdomNpcSet("blackfyre");
        GotEntities.registerConfiguredKingdomNpcSet("golden_company");
        GotEntities.registerConfiguredKingdomNpcSet("meereen");
        GotEntities.registerConfiguredKingdomNpcSet("yunkai");
        GotEntities.registerConfiguredKingdomNpcSet("astapor");
        GotEntities.registerConfiguredKingdomNpcSet("qarth");
        GotEntities.registerConfiguredKingdomNpcSet("wildling");
    }

    public record KingdomNpcEntityRegistration(class_1299<? extends AbstractKingdomNpcEntity> entityType, class_2960 kingdomId) {
    }
}

