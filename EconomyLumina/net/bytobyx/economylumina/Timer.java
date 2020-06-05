/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.scheduler.BukkitRunnable
 */
package net.bytobyx.economylumina;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer
extends BukkitRunnable {
    public static int min;
    public Map<String, Double> accountsList = new HashMap<String, Double>();

    public Timer(int min) {
        Timer.min = min;
    }

    public void run() {
        if (min == Main.topUpdate) {
            if (!Main.sql) {
                File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
                YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
                for (String s : data.getStringList("AConomyPlayerList")) {
                    this.accountsList.put(s, data.getDouble(s));
                }
                if (this.accountsList.size() < Main.topSize) {
                    Bukkit.getConsoleSender().sendMessage(Main.inst.getConfig().getString("Messages.NotEnoughForTop").replace("&", "\u00a7"));
                    return;
                }
                List<Map.Entry<String, Double>> top = MapSorting.sortedValues(this.accountsList);
                Main.topUsers.clear();
                int i = 1;
                while (i <= Main.topSize) {
                    if (Main.onlineMode) {
                        Main.topUsers.add(String.valueOf(Bukkit.getOfflinePlayer((UUID)UUID.fromString(top.get(top.size() - i).getKey())).getName()) + ";" + top.get(top.size() - i).getValue());
                    } else {
                        Main.topUsers.add(String.valueOf(top.get(top.size() - i).getKey()) + ";" + top.get(top.size() - i).getValue());
                    }
                    ++i;
                }
            } else {
                SQLManager.updateUsers();
                if (SQLManager.accountsList.size() < Main.topSize) {
                    Bukkit.getConsoleSender().sendMessage(Main.inst.getConfig().getString("Messages.NotEnoughForTop").replace("&", "\u00a7"));
                    return;
                }
                List<Map.Entry<String, Double>> top = MapSorting.sortedValues(SQLManager.accountsList);
                Main.topUsers.clear();
                int i = 1;
                while (i <= Main.topSize) {
                    if (Main.onlineMode) {
                        Main.topUsers.add(String.valueOf(Bukkit.getOfflinePlayer((UUID)UUID.fromString(top.get(top.size() - i).getKey())).getName()) + ";" + top.get(top.size() - i).getValue());
                    } else {
                        Main.topUsers.add(String.valueOf(top.get(top.size() - i).getKey()) + ";" + top.get(top.size() - i).getValue());
                    }
                    ++i;
                }
            }
            this.accountsList.clear();
            SQLManager.accountsList.clear();
            min = 0;
        }
        ++min;
    }
}

