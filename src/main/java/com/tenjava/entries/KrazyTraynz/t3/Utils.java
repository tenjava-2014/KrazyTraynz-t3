package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    List<Location> blockls = new ArrayList<>();

    private TenJava tj;

    public Utils(TenJava tj){
        this.tj = tj;
    }

    public void genFiles(){
        File mainDir = new File("plugins/Altars/Gods");
        mainDir.mkdirs();

        File lightDir = new File(mainDir + "/Light/");
        lightDir.mkdirs();
        File lightFollowers = new File(lightDir + "Followers.txt");
        File lightAltars = new File(lightDir + "Blocks.txt");
        File lightFaith = new File(lightDir + "FaithLevels.yml");

        File darkDir = new File(mainDir + "/Dark/");
        darkDir.mkdirs();
        File darkFollowers = new File(darkDir + "Followers.txt");
        File darkAltars = new File(darkDir + "Blocks.txt");
        File darkFaith = new File(darkDir + "FaithLevels.yml");

        File endDir = new File(mainDir + "/End/");
        endDir.mkdirs();
        File endFollowers = new File(endDir + "Followers.txt");
        File endAltars = new File(endDir + "Blocks.txt");
        File endFaith = new File(endDir + "FaithLevels.yml");

        File[] registries = {lightFollowers, lightAltars, lightFaith, darkFollowers, darkAltars, darkFaith, endFollowers, endAltars, endFaith};
        for(File f : registries){
            if(!f.exists()){
                try{
                    f.createNewFile();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeFollower(String god, String player){
        File lightF = new File("plugins/Altars/Gods/Light/Followers.txt");
        File darkF = new File("plugins/Altars/Gods/Dark/Followers.txt");
        File endF = new File("plugins/Altars/Gods/End/Followers.txt");

        File[] followers = {lightF, darkF, endF};
        for(File f : followers){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String[] players = br.readLine().split(";");
                for(String s : players){
                    if(player.equals(s)){
                        br.close();
                        return;
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File f = new File("plugins/Altars/Gods/" + god + "/Followers.txt");
        try {
            FileWriter fr = new FileWriter(f);
            fr.write(player + ";");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAltar(String god){
        for(Location l : blockls){
            File f = new File("plugins/Altars/Gods/" + god + "/Blocks.txt");
            try{
                BufferedWriter br = new BufferedWriter(new FileWriter(f));
                br.write(l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ() + "|");
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void changeFaith(String god, String player, int change, boolean add){
        String path = player + ".FaithLevel";
        File f = new File("plugins/Altars/Gods/" + god + "/FaithLevels.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(f);
        if(add) {
            yml.set(path, yml.getInt(path) + change);
        }else{
            yml.set(path, yml.getInt(path) - change);
        }
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerGod(Player p){
        File lightFollowers = new File("plugins/Altars/Gods/Light/Followers.txt");
        File darkFollowers = new File("plugins/Altars/Gods/Dark/Followers.txt");
        File endFollowers = new File("plugins/Altars/Gods/End/Followers.txt");

        File[] followers = {lightFollowers, darkFollowers, endFollowers};
        for(File f : followers){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String[] players = br.readLine().split(";");
                for(String s : players){
                    if(s.equals(p.getName())){
                        if(f.getPath().contains("Light")){
                            return "Light";
                        }else if(f.getPath().contains("Dark")){
                            return "Dark";
                        }else if(f.getPath().contains("End")){
                            return "End";
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean isAltarBlock(Location l){
        File lightBlocks = new File("plugins/Altars/Gods/Light/Blocks.txt");
        File darkBlocks = new File("plugins/Altars/Gods/Dark/Blocks.txt");
        File endBlocks = new File("plugins/Altars/Gods/End/Blocks.txt");
        File[] altars = {lightBlocks, darkBlocks, endBlocks};

        for(File f : altars){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String[] locs = br.readLine().split("|");
                for(String loc : locs){
                    String[] info = loc.split(",");
                    World w = Bukkit.getServer().getWorld(info[0]);
                    double x = Double.parseDouble(info[1]);
                    double y = Double.parseDouble(info[2]);
                    double z = Double.parseDouble(info[3]);
                    Location fin = new Location(w, x, y, z);
                    if(fin == l){
                        return true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public FaithEnum getType(EntityType et){
        for(FaithEnum fe : FaithEnum.values()){
            return fe.getFromType(et);
        }
        return null;
    }

    public int getFaith(Player p){
        File f = new File("plugins/Altars/Gods/" + getPlayerGod(p) + "/FaithLevels.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt(p.getName() + ".FaithLevel");
    }

    public boolean isLightAltar(Location l){
        int i = 0;
        if(l.add(1, 0 ,0).getBlock().getType() == Material.GOLD_BLOCK){
            i++;
            blockls.add(l.add(1, 0 ,0));
        }
        if(l.subtract(1, 0, 0).getBlock().getType() == Material.GOLD_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 0));
        }
        if(l.add(0, 0, 1).getBlock().getType() == Material.GOLD_BLOCK){
            i++;
            blockls.add(l.add(0, 0, 1));
        }
        if(l.subtract(0, 0, 1).getBlock().getType() == Material.GOLD_BLOCK){
            i++;
            blockls.add(l.subtract(0, 0, 1));
        }
        if(l.add(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.add(1, 0, 1));
        }
        if(l.subtract(0, 0, 1).add(1, 0, 0).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(0, 0, 1).add(1, 0, 0));
        }
        if(l.subtract(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 1));
        }
        if(l.subtract(1, 0, 0).add(0, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 0).add(0, 0, 1));
        }
        if(i == 8){
            writeAltar("Light");
            blockls.clear();
            return true;
        }else {
            blockls.clear();
            return false;
        }
    }

    public boolean isDarkAltar(Location l){
        int i = 0;
        if(l.add(1, 0 ,0).getBlock().getType() == Material.OBSIDIAN){
            i++;
            blockls.add(l.add(1, 0 ,0));
        }
        if(l.subtract(1, 0, 0).getBlock().getType() == Material.OBSIDIAN){
            i++;
            blockls.add(l.subtract(1, 0, 0));
        }
        if(l.add(0, 0, 1).getBlock().getType() == Material.OBSIDIAN){
            i++;
            blockls.add(l.add(0, 0, 1));
        }
        if(l.subtract(0, 0, 1).getBlock().getType() == Material.OBSIDIAN){
            i++;
            blockls.add(l.subtract(0, 0, 1));
        }
        if(l.add(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.add(1, 0, 1));
        }
        if(l.subtract(0, 0, 1).add(1, 0, 0).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(0, 0, 1).add(1, 0, 0));
        }
        if(l.subtract(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 1));
        }
        if(l.subtract(1, 0, 0).add(0, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 0).add(0, 0, 1));
        }
        if(i == 8){
            writeAltar("Dark");
            blockls.clear();
            return true;
        }else {
            blockls.clear();
            return false;
        }
    }

    public boolean isEndAltar(Location l){
        int i = 0;
        if(l.add(1, 0 ,0).getBlock().getType() == Material.ENDER_STONE){
            i++;
            blockls.add(l.add(1, 0 ,0));
        }
        if(l.subtract(1, 0, 0).getBlock().getType() == Material.ENDER_STONE){
            i++;
            blockls.add(l.subtract(1, 0, 0));
        }
        if(l.add(0, 0, 1).getBlock().getType() == Material.ENDER_STONE){
            i++;
            blockls.add(l.add(0, 0, 1));
        }
        if(l.subtract(0, 0, 1).getBlock().getType() == Material.ENDER_STONE){
            i++;
            blockls.add(l.subtract(0, 0, 1));
        }
        if(l.add(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.add(1, 0, 1));
        }
        if(l.subtract(0, 0, 1).add(1, 0, 0).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(0, 0, 1).add(1, 0, 0));
        }
        if(l.subtract(1, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 1));
        }
        if(l.subtract(1, 0, 0).add(0, 0, 1).getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
            blockls.add(l.subtract(1, 0, 0).add(0, 0, 1));
        }
        if(i == 8){
            writeAltar("End");
            blockls.clear();
            return true;
        }else {
            blockls.clear();
            return false;
        }
    }
}
