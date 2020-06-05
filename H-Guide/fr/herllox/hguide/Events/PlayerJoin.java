package fr.herllox.hguide.Events;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerJoin implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!p.hasPlayedBefore()) {
            p.sendMessage("§b§lLumina §8§l» §aBienvenue \u00e0 toi, si tu as besoin d'aide fait §e/guide§a.");
        }
    }
}
