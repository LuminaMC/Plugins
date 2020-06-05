package fr.herllox.hstaff;

import org.bukkit.plugin.java.*;
import fr.herllox.hstaff.Commands.*;
import org.bukkit.command.*;

public class Main extends JavaPlugin
{
    public void onEnable() {
        this.getCommand("mod").setExecutor((CommandExecutor)new OpenMenu(this));
    }
    
    public void onDisable() {
    }
}
