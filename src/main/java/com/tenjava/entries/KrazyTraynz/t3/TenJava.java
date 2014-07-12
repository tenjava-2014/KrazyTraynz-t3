package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.plugin.java.JavaPlugin;

<<<<<<< HEAD
public class TenJava extends JavaPlugin {

    public Utils u;

    public void onEnable(){
        u = new Utils(this);
        u.genFiles();
        getLogger().info("Altars has been enabled!");
    }

    public void onDisable(){
        getLogger().warning("Altars has been disabled!");
    }
}
=======
public class TenJava extends JavaPlugin {}
>>>>>>> origin/master
