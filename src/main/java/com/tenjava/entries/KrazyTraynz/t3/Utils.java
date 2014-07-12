package com.tenjava.entries.KrazyTraynz.t3;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.*;

public class Utils {
    private TenJava tj;

    public Utils(TenJava tj){
        this.tj = tj;
    }

    /**
     * Generates files if they don't exist.
     */
    public void genFiles(){
        File mainDir = new File("plugins/Altars/Gods");
        mainDir.mkdirs();

        File lightDir = new File(mainDir + "/Light");
        lightDir.mkdirs();
        File lightFollowers = new File(lightDir + "/Followers.txt");
        File lightAltars = new File(lightDir + "/Blocks.txt");
        File lightFaith = new File(lightDir + "/FaithLevels.yml");

        File darkDir = new File(mainDir + "/Dark");
        darkDir.mkdirs();
        File darkFollowers = new File(darkDir + "/Followers.txt");
        File darkAltars = new File(darkDir + "/Blocks.txt");
        File darkFaith = new File(darkDir + "/FaithLevels.yml");

        File endDir = new File(mainDir + "/End");
        endDir.mkdirs();
        File endFollowers = new File(endDir + "/Followers.txt");
        File endAltars = new File(endDir + "/Blocks.txt");
        File endFaith = new File(endDir + "/FaithLevels.yml");

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

    /**
     * Registers player as a follower.
     * @param god god they decide to follow
     * @param player player to register
     */
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

    /**
     * Changes a player's amount of faith.
     * @param god god they follow
     * @param player player who's faith is being changed
     * @param change amount it's being changed by
     * @param add whether or not it's adding or subtracting
     */
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

    /**
     * Get god player is following.
     * @param p follower
     * @return
     */
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

    /**
     * whether or not a block at a given location is part of an altar.
     * @param l location of block to check
     * @return
     */
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

    /**
     * Get amount of faith a player has.
     * @param p player who's faith is being checked
     * @return
     */
    public int getFaith(Player p){
        File f = new File("plugins/Altars/Gods/" + getPlayerGod(p) + "/FaithLevels.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(f);
        return yml.getInt(p.getName() + ".FaithLevel");
    }

    /**
     * Whether or not blocks around a location are part of a light altar.
     * @param b location to check around
     * @return
     */
    public boolean isLightAltar(Block b){
        if(checkPrimaryBlocks(b.getLocation(), Material.GOLD_BLOCK) + checkSecondaryBlocks(b.getLocation()) == 8){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Whether or not blocks around a location are part of a dark altar.
     * @param b Block with location to check around
     * @return
     */
    public boolean isDarkAltar(Block b){
        if(checkPrimaryBlocks(b.getLocation(), Material.OBSIDIAN) + checkSecondaryBlocks(b.getLocation()) == 8){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Whether or not blocks around a location are part of an end altar.
     * @param b Block with location to check around
     * @return
     */
    public boolean isEndAltar(Block b){
        if(checkPrimaryBlocks(b.getLocation(), Material.ENDER_STONE) + checkSecondaryBlocks(b.getLocation()) == 8){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Checks for blocks unique to altar around a location.
     * @param l location to check around
     * @param m material to check for
     * @return
     */
    public int checkPrimaryBlocks(Location l, Material m){
        int i = 0;
        l.subtract(0, 1, 0);
        Location p1 = l.add(1, 0, 0);
        Location p2 = l.subtract(2, 0, 0);
        Location p3 = l.add(1, 0, 1);
        Location p4 = l.subtract(0, 0, 2);

        if(p1.getBlock().getType() == m){
            i++;
        }
        if(p2.getBlock().getType() == m){
            i++;
        }
        if(p3.getBlock().getType() == m){
            i++;
        }
        if(p4.getBlock().getType() == m){
            i++;
        }
        return i;
    }

    /**
     * Checks for quartz around a location.
     * @param l location to check around
     * @return
     */
    public int checkSecondaryBlocks(Location l){
        int i = 0;
        l.subtract(0, 1, 0);
        Location s1 = l.add(-1, 0, 1);
        Location s2 = l.subtract(-2, 0, 2);
        Location s3 = l.add(-2, 0, 0);
        Location s4 = l.add(2, 0, 2);

        if(s1.getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
        }
        if(s2.getBlock().getType() == Material.QUARTZ_BLOCK) {
            i++;
        }
        if(s3.getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
        }
        if(s4.getBlock().getType() == Material.QUARTZ_BLOCK){
            i++;
        }
        return i;
    }

    /**
     * Gets FaithEnum matching given EntityType.
     * @param e EntityType to check for
     * @return
     */
    public FaithEnum getType(EntityType e){
        for(FaithEnum fe : FaithEnum.values()){
            if(fe.getEntity() == e){
                return fe;
            }
        }
        return null;
    }
}
