package net.bytobyx.lumievent;


import net.bytobyx.lumievent.messages.MessCompleted;
import net.bytobyx.lumievent.messages.MessRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin {
    public static Boolean Messageon = Boolean.valueOf(false);
    public static String goodmess = "";

    public void onEnable() {
        saveConfig();

        MessRunnable task = new MessRunnable(this);
        task.runTaskTimerAsynchronously(this, 0L, 12000L);

        Bukkit.getPluginManager().registerEvents(new MessCompleted(this), this);
    }

    public void onDisable() {
    }
}
