package fr.herllox.hguide;

import org.bukkit.plugin.java.*;
import fr.herllox.hguide.Commands.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import fr.herllox.hguide.Events.*;

public class Main extends JavaPlugin
{
    public void onEnable() {
        this.getCommand("guide").setExecutor((CommandExecutor)new GuideMenu());
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new DirtClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new OakSignClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ChestMinecartClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new CobblestoneClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new EmeraldClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new EnchantingTableClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new FarmlandClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new GoldIngotClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new NullClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerHeadClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new RedStainedGlassClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new RoseBushClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ZombieHeadClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new FeatherClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new EnderEyeClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BottleExpClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ChestClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new LeaveBook(), (Plugin)this);
    }
}
