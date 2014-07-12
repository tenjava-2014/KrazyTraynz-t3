package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public enum FaithEnum {

    CHICKEN(EntityType.CHICKEN, 3),
    HORSE(EntityType.HORSE, 8),
    COW(EntityType.COW, 6),
    PIG(EntityType.PIG, 4),
    SHEEP(EntityType.SHEEP, 5);

    Material m;
    EntityType et;
    int level;

    FaithEnum(Material m, int level){
        this.m = m;
        this.level = level;
    }

    FaithEnum(EntityType et, int level){
        this.et = et;
        this.level = level;
    }

    public EntityType getEntity(){
        return et;
    }

    public int getLevel(){
        return level;
    }
}
