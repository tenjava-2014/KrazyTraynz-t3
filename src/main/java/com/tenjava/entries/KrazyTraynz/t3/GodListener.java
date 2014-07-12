package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class GodListener implements Listener {

    List<String> damaged = new ArrayList<>();
    HashMap<String, String> killed = new HashMap<>();
    private TenJava tj;


    public GodListener(TenJava tj){
        this.tj = tj;
    }

    @EventHandler
    public void pvpFaith(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            Player p = (Player)e.getDamager();
            Player d = (Player)e.getEntity();
            if(!damaged.contains(p.getName()) && !killed.get(d).equals(p.getName())){
                if(tj.u.getPlayerGod(p).equals("Light")){
                    tj.u.changeFaith("Light", p.getName(), 7, false);
                    p.sendMessage(ChatColor.RED + "You have attacked unprovoked, angering " + ChatColor.BLUE + tj.getConfig().getString("Gods.Light.Name") + ChatColor.RED + "!");
                    damaged.add(d.getName());
                }
            }
        }
    }

    @EventHandler
    public void killFaith(PlayerDeathEvent e){
        if(e.getEntity().getKiller() != null){
            Player p = e.getEntity().getKiller();
            if(tj.u.getPlayerGod(p).equals("Dark")){
                tj.u.changeFaith("Dark", p.getName(), 8, true);
                p.sendMessage(ChatColor.DARK_GREEN + "You have killed a player, pleasing " + ChatColor.DARK_RED + tj.getConfig().getString("Gods.Dark.Name") + ChatColor.DARK_GREEN + "!");
                killed.put(p.getName(), e.getEntity().getName());
            }
        }
    }

    @EventHandler
    public void sacrificeFaith(PlayerInteractEntityEvent e){

    }
}