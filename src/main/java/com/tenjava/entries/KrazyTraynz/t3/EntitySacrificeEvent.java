package com.tenjava.entries.KrazyTraynz.t3;

import net.minecraft.server.v1_7_R3.Material;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        try {
            Method m = Utils.class.getDeclaredMethod("isAltarBlock", Location.class);
            boolean altar = (Boolean)m.invoke(sacrifice.getLocation().subtract(0, 1, 0));
            if(altar){
                return true;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
