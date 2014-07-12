package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class GiftRandomizer {

    HashMap<String, Long> since = new HashMap<>(); //Millis
    HashMap<String, Long> time = new HashMap<>(); //Wait after gift

    TenJava tj;
    public GiftRandomizer(TenJava tj){
        this.tj = tj;
    }

    /**
     * Converts ticks to seconds.
     * @param i ticks to convert
     * @return amount of seconds
     */
    public long secFromTick(long i){
        return i*20;
    }


    /**
     * Gives a player a random gift with the value closest to the random value reached with their current faith.
     */
    public void gift(){
        final Random r = new Random();
        tj.getServer().getScheduler().scheduleSyncDelayedTask(tj, new Runnable(){
            public void run(){
                for(Player p : Bukkit.getServer().getOnlinePlayers()){
                    if(since.get(p.getName()) - System.currentTimeMillis()*1000 < secFromTick(time.get(p.getName()))) {
                        double i = ((tj.u.getFaith(p) * .33) / 15) * r.nextDouble();
                        int value = Math.round((float) i);
                        for (GiftEnum ge : GiftEnum.values()) {
                            if (ge.value == value) {
                                ItemStack is = new ItemStack(ge.getMaterial());
                                p.getInventory().addItem(is);
                                time.put(p.getName(), (long) ge.getTime());
                                since.put(p.getName(), System.currentTimeMillis());
                                if (tj.u.getPlayerGod(p).equals("Light")) {
                                    p.sendMessage(ChatColor.BLUE + tj.getConfig().getString("Gods.Light.Name") + " appreciates your devotion, and has decided to reward you!");
                                } else if (tj.u.getPlayerGod(p).equals("Dark")) {
                                    p.sendMessage(ChatColor.DARK_RED + tj.getConfig().getString("Gods.Dark.Name") + " appreciates your devotion, and has decided to reward you!");
                                } else if (tj.u.getPlayerGod(p).equals("End")) {
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + tj.getConfig().getString("Gods.End.Name") + " appreciates your devotion, and has decided to reward you!");
                                }
                            }
                        }
                    }
                }
            }
        }, 6000L);
    }
}