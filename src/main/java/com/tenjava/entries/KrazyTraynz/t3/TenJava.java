package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin implements Listener{

    public Utils u;
    public Altar a;
    public PrayerBooks pb;
    public GodListener gl;
    public GiftRandomizer gr;

    public void onEnable(){
        loadConfig();

        pb = new PrayerBooks(this);
        u = new Utils(this);
        a = new Altar(this);
        gl = new GodListener(this);
        gr = new GiftRandomizer(this);

        u.genFiles();

        pb.darkBook();
        pb.endBook();
        pb.lightBook();

        gr.gift();

        Bukkit.getServer().getPluginManager().registerEvents(a, this);
        Bukkit.getServer().getPluginManager().registerEvents(gl, this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Altars has been enabled!");
    }

    public void onDisable(){
        getLogger().warning("Altars has been disabled!");
    }

    public void loadConfig(){
        System.out.println(getConfig().getString("Gods.Light.Name"));
        if(getConfig().getString("Gods.Light.Name") == null) {
            getConfig().set("Gods.Light.Name", "Lumus");
        }
        if(getConfig().getString("Gods.Dark.Name") == null) {
            getConfig().set("Gods.Dark.Name", "Tarnen");
        }
        if(getConfig().getString("Gods.End.Name") == null) {
            getConfig().set("Gods.End.Name", "Finus");
        }
    }
}
