package fr.herllox.hguide.Events;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class RoseBushClick implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§b§lGuide§e§lMenu")) {
            e.setCancelled(true);
            if (e.getInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                p.closeInventory();
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case ROSE_BUSH: {
                    final ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta bookMeta = (BookMeta)writtenBook.getItemMeta();
                    bookMeta.setTitle("§b§lGuide§e§lMenu");
                    bookMeta.setAuthor("Herllox");
                    final ArrayList<String> pages = new ArrayList<String>();
                    pages.add("§6§l§m     [§b§l LUMINA §6§m§l]     §r\n\nFaire une belle \u00eele est quelque chose de tr\u00e8s motivant pour jouer, n'h\u00e9sitez pas \u00e0 faire de la d\u00e9corations. Vous pourrez aussi la faire visiter aux autres joueurs en cr\u00e9ant un warp sur votre \u00eele.");
                    bookMeta.setPages((List)pages);
                    writtenBook.setItemMeta((ItemMeta)bookMeta);
                    p.closeInventory();
                    p.openBook(writtenBook);
                    break;
                }
            }
        }
    }
}
