package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class Altar implements Listener {

    private TenJava tj;

    public Altar(TenJava tj) {
        this.tj = tj;
    }

    /**
     * Checks if a player has created an altar, and registers it and assigns their faith.
     * @param e
     */
    @EventHandler
    public void registerAltar(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        Player p;
        if (e.getPlayer() != null) {
            p = e.getPlayer();
        } else {
            return;
        }
        Location under = b.getLocation().subtract(0, 1, 0);
        if (b.getType() == Material.DIAMOND_BLOCK) {
            if (b.getWorld().getBlockAt(under).getType() == Material.QUARTZ_BLOCK) {
                if (tj.u.isLightAltar(b)) {
                    tj.u.writeFollower("Light", p.getName());
                    p.sendMessage(ChatColor.BLUE + "You have become a follower of " + tj.getConfig().getString("Gods.Light.Name") + "!");
                    b.getWorld().strikeLightning(b.getLocation().add(0, 2.0, 0));
                    tj.gr.since.put(p.getName(), System.currentTimeMillis());
                    tj.gr.time.put(p.getName(), 0L);
                } else if (tj.u.isDarkAltar(b)) {
                    b.getWorld().strikeLightning(b.getLocation().add(0, 2.0, 0));
                    tj.u.writeFollower("Dark", p.getName());
                    p.sendMessage(ChatColor.DARK_RED + "You have become a follower of " + tj.getConfig().getString("Gods.Dark.Name") + "!");
                    tj.gr.since.put(p.getName(), System.currentTimeMillis());
                    tj.gr.time.put(p.getName(), 0L);
                } else if (tj.u.isEndAltar(b)) {
                    b.getWorld().strikeLightning(b.getLocation().add(0, 2.0, 0));
                    tj.u.writeFollower("End", p.getName());
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "You have become a follower of " + tj.getConfig().getString("Gods.End.Name") + "!");
                    tj.gr.since.put(p.getName(), System.currentTimeMillis());
                    tj.gr.time.put(p.getName(), 0L);
                }
            }
        }
    }
}