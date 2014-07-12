package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    public Utils u;
    public Altar a;
    public PrayerBooks pb;
    public GodListener gl;
    public GiftRandomizer gr;

    public void onEnable(){
        pb = new PrayerBooks(this);
        u = new Utils(this);
        a = new Altar(this);
        gl = new GodListener(this);
        gr = new GiftRandomizer(this);

        u.genFiles();

        pb.darkBook();
        pb.endBook();
        pb.lightBook();

        Bukkit.getServer().getPluginManager().registerEvents(a, this);
        Bukkit.getServer().getPluginManager().registerEvents(gl, this);
        getLogger().info("Altars has been enabled!");
    }

    public void onDisable(){
        getLogger().warning("Altars has been disabled!");
    }
}
