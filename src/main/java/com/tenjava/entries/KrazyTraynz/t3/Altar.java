package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class Altar implements Listener{

    private TenJava tj;

    public Altar(TenJava tj){
        this.tj = tj;
    }

    @EventHandler
    public void registerAltar(BlockPlaceEvent e){
        World w;
        if(e.getPlayer() != null){
            w = e.getPlayer().getWorld();
        }else{
            return;
        }

        Location under = e.getBlockPlaced().getLocation().subtract(0, 1, 0);
        if(e.getBlockPlaced().getType() == Material.CHEST){
            if(w.getBlockAt(under).getType() == Material.QUARTZ_BLOCK){
                if(tj.u.isLightAltar(under)){

                }else if(tj.u.isDarkAltar(under)){

                }else if(tj.u.isEndAltar(under)){

                }
            }
        }
    }
}
