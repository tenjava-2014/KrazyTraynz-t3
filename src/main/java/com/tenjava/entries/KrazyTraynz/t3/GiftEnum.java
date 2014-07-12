package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Material;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public enum GiftEnum {

    LEATHER_HELMET(Material.LEATHER_HELMET, 72000, 0),
    LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, 144000, 1),
    LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, 96000, 2),
    LEATHER_BOOTS(Material.LEATHER_BOOTS, 48000, 3),
    GOLD_HELMET(Material.GOLD_HELMET, 86000, 4),
    GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 192000, 5),
    GOLD_LEGGINGS(Material.GOLD_LEGGINGS, 120000, 6),
    GOLD_BOOTS(Material.GOLD_BOOTS, 96000, 7),
    IRON_HELMET(Material.IRON_HELMET, 168000, 8),
    IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 216000, 9),
    IRON_LEGGINGS(Material.IRON_LEGGINGS, 192000, 10),
    IRON_BOOTS(Material.IRON_BOOTS, 144000, 11),
    DIAMOND_HELMET(Material.DIAMOND_HELMET, 240000, 12),
    DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 312000, 13),
    DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 288000, 14),
    DIAMOND_BOOTS(Material.DIAMOND_BOOTS, 228000, 15);

    Material m;
    int time;
    int value;

    GiftEnum(Material m, int time, int value){
        this.m = m;
        this.time = time;
        this.value = value;
    }

    public int getTime(){
        return time;
    }

    public Material getMaterial(){
        return m;
    }

    public GiftEnum fromValue(int i){
        for(GiftEnum ge : values()){
            if(ge.value == i){
                return ge;
            }
        }
        return null;
    }
}
