package fr.megacretin.duel.arenas;

import fr.megacretin.duel.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import fr.megacretin.arena.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class ArenaListener implements Listener
{
    private Main duel;
    
    public ArenaListener(final Main main) {
        this.duel = main;
    }
    
    @EventHandler
    public void onKill(final PlayerDeathEvent event) {
        System.out.println("Event kill detect");
        if (event.getEntity().getKiller() instanceof Player) {
            final Player victim = event.getEntity();
            final Player killer = victim.getKiller();
            System.out.println("au dessus");
            if (ArenaManager.checkPlayerInList()) {
                final Location spawn = new Location(Bukkit.getWorld("world"), 6.5, 121.5, -7.5, 0.0f, 0.0f);
                victim.teleport(spawn);
                killer.teleport(spawn);
                killer.sendMessage("§5§lDUEL §8» §aTu as gagn\u00e9 ton duel");
                victim.sendMessage("§5§lDUEL §8» §cTu as perdu le duel");
                ArenaManager.clearList();
                Main.isStarted = false;
            }
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player leaver = event.getPlayer();
        if (ArenaManager.checkContainPlayer(leaver)) {
            ArenaManager.removePlayer(leaver);
            for (int i = 0; i < ArenaManager.getList().size(); ++i) {
                final Player player = ArenaManager.getList().get(i);
                final Location spawn = new Location(Bukkit.getWorld("world"), 6.5, 121.5, -7.5, 0.0f, 0.0f);
                player.teleport(spawn);
                Main.isStarted = false;
                player.sendMessage("§5§lDUEL §8» §aTu as gagn\u00e9 car ton adversaire \u00e0 abandonn\u00e9 !!");
            }
        }
    }
    
    @EventHandler
    public void onCommand(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (ArenaManager.getList().contains(p) && ((p.getWorld().equals(Bukkit.getWorld("world")) && !p.isOp()) || e.getMessage().equalsIgnoreCase("/spawn") || e.getMessage().equalsIgnoreCase("/feed") || e.getMessage().equalsIgnoreCase("/heal") || e.getMessage().contains("/home") || e.getMessage().equalsIgnoreCase("/ec") || e.getMessage().contains("/is warp") || e.getMessage().equalsIgnoreCase("/spawn") || e.getMessage().equalsIgnoreCase("/is") || e.getMessage().equalsIgnoreCase("/is go") || e.getMessage().equalsIgnoreCase("/shop") || e.getMessage().equalsIgnoreCase("/tpa") || e.getMessage().equalsIgnoreCase("/tpyes") || e.getMessage().equalsIgnoreCase("/tpaccept") || e.getMessage().equalsIgnoreCase("/back") || e.getMessage().equalsIgnoreCase("/hub") || e.getMessage().equalsIgnoreCase("/lobby") || e.getMessage().equalsIgnoreCase("/vote") || e.getMessage().equalsIgnoreCase("/fly"))) {
            e.setCancelled(true);
        }
    }
}
