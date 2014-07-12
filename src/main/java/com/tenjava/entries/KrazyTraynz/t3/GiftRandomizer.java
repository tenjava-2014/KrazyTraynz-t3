package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class GiftRandomizer {

    HashMap<String, Long> wait = new HashMap<>();

    TenJava tj;
    public GiftRandomizer(TenJava tj){
        this.tj = tj;
    }

    public int secFromTick(int i){
        return i*20;
    }

    public void gift(){
        Random r = new Random();
        for(Player p : Bukkit.getServer().getOnlinePlayers()){

        }
    }
}
