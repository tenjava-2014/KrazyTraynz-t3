package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Material;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public enum GiftEnum {

    LEATHER_HELMET(Material.LEATHER_HELMET, 30, 72000),
    LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, 47, 144000),
    LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, 41, 96000),
    LEATHER_BOOTS(Material.LEATHER_BOOTS, 24, 48000),
    GOLD_HELMET(Material.GOLD_HELMET, 90, 86000),
    GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 135, 192000),
    GOLD_LEGGINGS(Material.GOLD_LEGGINGS, 109, 120000),
    GOLD_BOOTS(Material.GOLD_BOOTS, 70, 96000),
    IRON_HELMET(Material.IRON_HELMET, 120, 168000),
    IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 180, 216000),
    IRON_LEGGINGS(Material.IRON_LEGGINGS, 156, 192000),
    IRON_BOOTS(Material.IRON_BOOTS, 98, 144000),
    DIAMOND_HELMET(Material.DIAMOND_HELMET, 300, 240000),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 380, 312000),
    DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 322, 288000),
    DIAMOND_BOOTS(Material.DIAMOND_BOOTS, 290, 228000);

    Material m;
    int level;
    int time;

    GiftEnum(Material m, int level, int time){
        this.m = m;
        this.level = level;
        this.time = time;
    }

    public int getTime(GiftEnum g){
        return g.time;
    }

    public int getLevel(GiftEnum g){
        return g.level;
    }

    public Material getMaterial(GiftEnum g){
        return g.m;
    }
}
