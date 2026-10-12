/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package trep.got.world.event;

import org.jetbrains.annotations.Nullable;
import trep.got.world.event.WorldEventOccurrence;
import trep.got.world.event.context.WorldEventPlayerSnapshot;

public interface WorldEventTrigger {
    public String id();

    @Nullable
    public WorldEventOccurrence evaluate(@Nullable WorldEventPlayerSnapshot var1, WorldEventPlayerSnapshot var2);
}

