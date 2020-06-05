package fr.herllox.hguide.Events;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class LeaveBook implements Listener
{
    @EventHandler
    public void onLeaveBook(final PlayerEditBookEvent e) {
        final Player p = e.getPlayer();
        if (e.isCancelled()) {
            p.sendMessage("close de book");
        }
    }
}
