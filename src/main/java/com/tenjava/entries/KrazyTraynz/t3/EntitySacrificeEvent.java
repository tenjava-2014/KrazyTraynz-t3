package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class EntitySacrificeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Entity sacrifice;

    public EntitySacrificeEvent(Entity e) {
        this.sacrifice = e;
    }

    public Entity getEntity() {
        return sacrifice;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }


    public boolean isElligible(){
        return false;
    }
}
