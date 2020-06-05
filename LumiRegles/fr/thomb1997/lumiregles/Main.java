package fr.thomb1997.lumiregles;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    Main main;
    
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((Listener)new Navigation(this), (Plugin)this);
        this.getCommand("reglement").setExecutor((CommandExecutor)new Commande(this));
        this.saveDefaultConfig();
    }
}
