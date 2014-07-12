package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class Altar implements Listener{

    private TenJava tj;

    public Altar(TenJava tj){
        this.tj = tj;
    }

    @EventHandler
    public void registerAltar(InventoryClickEvent e){
        Block b = null;
        Player p = null;

        if(e.getWhoClicked() != null && e.getWhoClicked().getType() == EntityType.PLAYER){
            p = (Player)e.getWhoClicked();
        }else{
            return;
        }

        if(e.getInventory().getHolder() instanceof Chest){
           Chest c = (Chest)e.getInventory().getHolder();
           b = c.getBlock();
        }else{
            return;
        }
        Location under = b.getLocation().subtract(0, 1, 0);
        if(b.getType() == Material.CHEST){
            if(b.getWorld().getBlockAt(under).getType() == Material.QUARTZ_BLOCK){
                if(tj.u.isLightAltar(under)){
                    if(e.getAction() == InventoryAction.PLACE_ONE || e.getAction() == InventoryAction.PLACE_ALL || e.getAction() == InventoryAction.PLACE_SOME){
                        if(e.getCurrentItem().getType() == Material.DIAMOND){
                            e.getCurrentItem().setAmount(e.getCurrentItem().getAmount() - 1);
                            b.getWorld().strikeLightning(b.getLocation().add(0, 0.1, 0));
                            tj.u.writeFollower("Light", p.getName());
                            p.sendMessage(ChatColor.BLUE + "You have become a follower of " + tj.getConfig().getString("Gods.Light"));
                            tj.gr.wait.put(p.getName(), System.currentTimeMillis());
                        }
                    }
                }else if(tj.u.isDarkAltar(under)){
                    if(e.getAction() == InventoryAction.PLACE_ONE || e.getAction() == InventoryAction.PLACE_ALL || e.getAction() == InventoryAction.PLACE_SOME){
                        if(e.getCurrentItem().getType() == Material.BLAZE_ROD){
                            e.getCurrentItem().setAmount(e.getCurrentItem().getAmount() - 1);
                            b.getWorld().strikeLightning(b.getLocation().add(0, 0.1, 0));
                            tj.u.writeFollower("Dark", p.getName());
                            p.sendMessage(ChatColor.DARK_RED + "You have become a follower of " + tj.getConfig().getString("Gods.Dark.Name"));
                            tj.gr.wait.put(p.getName(), System.currentTimeMillis());
                        }
                    }
                }else if(tj.u.isEndAltar(under)){
                    if(e.getAction() == InventoryAction.PLACE_ONE || e.getAction() == InventoryAction.PLACE_ALL || e.getAction() == InventoryAction.PLACE_SOME){
                        if(e.getCurrentItem().getType() == Material.EYE_OF_ENDER){
                            e.getCurrentItem().setAmount(e.getCurrentItem().getAmount() - 1);
                            b.getWorld().strikeLightning(b.getLocation().add(0, 0.1, 0));
                            tj.u.writeFollower("End", p.getName());
                            p.sendMessage(ChatColor.LIGHT_PURPLE + "You have become a follower of " + tj.getConfig().getString("Gods.End.Name"));
                            tj.gr.wait.put(p.getName(), System.currentTimeMillis());
                        }
                    }
                }
            }
        }
    }
}