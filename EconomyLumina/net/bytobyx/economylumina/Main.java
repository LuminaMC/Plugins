package net.bytobyx.economylumina;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main
extends JavaPlugin
implements Listener {
    public static Economy economy;
    public static Main inst;
    public List<String> accounts;
    public static double startingBalance;
    public static boolean sql;
    public static boolean onlineMode;
    public static int moneyFormat;
    public static int topUpdate;
    public static int topSize;
    public static List<String> topUsers;
    private BukkitRunnable runnable;

    static {
        topUsers = new ArrayList<String>();
    }

    public void onEnable() {
        File config = new File(this.getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            this.getConfig().options().copyDefaults(true);
        }
        this.saveDefaultConfig();
        inst = this;
        this.setupEconomy();
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.getCommand("money").setExecutor((CommandExecutor)new Commands());
        this.getCommand("pay").setExecutor(new PayCommand());
        this.getCommand("baltop").setExecutor(new BaltopCommand());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (economy.hasAccount(p.getName())) continue;
            economy.createPlayerAccount(p.getName());
        }
        this.runnable = new Timer(topUpdate);
        this.runnable.runTaskTimer((Plugin)inst, 1200L, 1200L);

    }

    public void onDisable() {

        if (sql) {
            SQLManager.disconnect();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!economy.hasAccount(p.getName())) {
            economy.createPlayerAccount(p.getName());
        }
    }

    public static String getMoneyFormat(String balance) {
        if (moneyFormat == 1) {
            return String.valueOf(Double.valueOf(balance));
        }
        if (moneyFormat == 2) {
            return String.valueOf(Double.valueOf(balance));
        }
        if (moneyFormat == 3) {
            return String.valueOf(Double.valueOf(balance));
        }
        if (moneyFormat != 3 || moneyFormat != 2 || moneyFormat != 1) {
            return balance;
        }
        return balance;
    }

    public static double getMoneyFormat(double balance) {
        if (moneyFormat == 1) {
            return Math.floor(balance);
        }
        if (moneyFormat == 2) {
            return Double.valueOf(balance);
        }
        if (moneyFormat == 3) {
            return Double.valueOf(balance);
        }
        if (moneyFormat != 3 || moneyFormat != 2 || moneyFormat != 1) {
            return balance;
        }
        return balance;
    }

    public void reloadEconomy() {
        onlineMode = this.getConfig().getBoolean("OnlineMode");
        startingBalance = this.getConfig().getDouble("StartingBalance");
        moneyFormat = this.getConfig().getInt("MoneyFormat");
        topUpdate = this.getConfig().getInt("TopUpdate");
        topSize = this.getConfig().getInt("TopSize");
    }

    public void setupEconomy() {
        onlineMode = this.getConfig().getBoolean("OnlineMode");
        startingBalance = this.getConfig().getDouble("StartingBalance");
        moneyFormat = this.getConfig().getInt("MoneyFormat");
        topUpdate = this.getConfig().getInt("TopUpdate");
        topSize = this.getConfig().getInt("TopSize");
        if (this.getConfig().getBoolean("Mysql.Enabled")) {
            sql = true;
            SQLManager.setup(this.getConfig().getString("Mysql.Host"), this.getConfig().getString("Mysql.Database"), this.getConfig().getString("Mysql.Port"), this.getConfig().getString("Mysql.Username"), this.getConfig().getString("Mysql.Password"));
            SQLManager.connect();
            new BukkitRunnable(){

                public void run() {
                    if (Main.economy.hasAccount("abcik")) {
                        return;
                    }
                }
            }.runTaskTimer((Plugin)this, 6000L, 6000L);
        } else {
            sql = false;
            File playerdata = new File(this.getDataFolder() + File.separator + "playerdata.yml");
            YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
            if (onlineMode) {
                if (!playerdata.exists()) {
                    try {
                        playerdata.createNewFile();
                    }
                    catch (IOException p) {
                        p.printStackTrace();
                    }
                    ArrayList<String> players = new ArrayList<String>();
                    players.add("14555508-6819-4434-aa6a-e5ce1509ea35");
                    data.set("AConomyPlayerList", players);
                    data.set("14555508-6819-4434-aa6a-e5ce1509ea35", (Object)startingBalance);
                    try {
                        data.save(playerdata);
                    }
                    catch (IOException p) {
                        p.printStackTrace();
                    }
                }
                this.accounts = data.getStringList("AConomyPlayerList");
            } else {
                if (!playerdata.exists()) {
                    try {
                        playerdata.createNewFile();
                    }
                    catch (IOException p) {
                        p.printStackTrace();
                    }
                    ArrayList<String> players = new ArrayList<String>();
                    players.add("abcik");
                    data.set("AConomyPlayerList", players);
                    data.set("abcik", (Object)this.getConfig().getDouble("StartingBalance"));
                    try {
                        data.save(playerdata);
                    }
                    catch (IOException p) {
                        p.printStackTrace();
                    }
                }
                this.accounts = data.getStringList("AConomyPlayerList");
            }
        }
        this.getServer().getServicesManager().register(Economy.class, new VaultAPI(), (Plugin)this, ServicePriority.Normal);
        economy = new VaultAPI();
    }

}

