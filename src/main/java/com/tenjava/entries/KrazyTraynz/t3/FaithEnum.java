package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public enum FaithEnum {

    COAL(Material.COAL, 3),
    DIAMOND(Material.DIAMOND, 6),
    EMERALD(Material.EMERALD, 6),
    IRON_INGOT(Material.IRON_INGOT, 4),
    GOLD_INGOT(Material.GOLD_INGOT, 5),
    CHICKEN(EntityType.CHICKEN, 3),
    HORSE(EntityType.HORSE, 8),
    COW(EntityType.COW, 6),
    IRON_GOLEM(EntityType.IRON_GOLEM, 12),
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
}
