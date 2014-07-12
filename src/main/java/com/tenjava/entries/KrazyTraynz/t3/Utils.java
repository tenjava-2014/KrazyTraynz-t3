package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carter Milch on Jul 12, 2014.
 */
public class Utils {

    List<Location> blockls = new ArrayList<Location>();

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
                FileWriter fr = new FileWriter(f);
                fr.write(l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ());
                fr.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void boostFaith(String god, String player, int boost){
        String path = player + ".FaithLevel";
        File f = new File("plugins/Altars/Gods/" + god + "/FaithLevels.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(f);
        yml.set(path, yml.getInt(path) + boost);
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
