package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class PrayerBooks {

    private TenJava tj;
    public PrayerBooks(TenJava tj){
        this.tj = tj;
    }

    /**
     * Recipe for Light god book.
     */
    public void lightBook(){
        ItemStack i = new ItemStack(Material.BOOK);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Book of " + tj.getConfig().getString("Gods.Light.Name"));
        i.setItemMeta(meta);

        ShapedRecipe sr = new ShapedRecipe(i);
        sr.shape("CGC", "GBG", "CGC");
        sr.setIngredient('C', Material.GLASS);
        sr.setIngredient('G', Material.GLOWSTONE);
        sr.setIngredient('B', Material.BOOK);
        Bukkit.getServer().addRecipe(sr);
    }

    /**
     * Recipe for Dark god book.
     */
    public void darkBook(){
        ItemStack i = new ItemStack(Material.BOOK);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Book of " + tj.getConfig().getString("Gods.Dark.Name"));
        i.setItemMeta(meta);

        ShapedRecipe sr = new ShapedRecipe(i);
        sr.shape("RNR", "NBN", "RNR");
        sr.setIngredient('R', Material.BLAZE_ROD);
        sr.setIngredient('N', Material.NETHERRACK);
        sr.setIngredient('B', Material.BOOK);
        Bukkit.getServer().addRecipe(sr);
    }

    /**
     * Recipe for End god book.
     */
    public void endBook(){
        ItemStack i = new ItemStack(Material.BOOK);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Book of " + tj.getConfig().getString("Gods.End.Name"));
        i.setItemMeta(meta);

        ShapedRecipe sr = new ShapedRecipe(i);
        sr.shape("ESE", "SBS", "ESE");
        sr.setIngredient('E', Material.ENDER_PEARL);
        sr.setIngredient('S', Material.ENDER_STONE);
        sr.setIngredient('B', Material.BOOK);
        Bukkit.getServer().addRecipe(sr);
    }
}
