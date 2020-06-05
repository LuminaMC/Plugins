package fr.herllox.hguide.Events;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class DirtClick implements Listener
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
                case GRASS_BLOCK: {
                    final ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta bookMeta = (BookMeta)writtenBook.getItemMeta();
                    bookMeta.setTitle("§b§lGuide§e§lMenu");
                    bookMeta.setAuthor("Herllox");
                    final ArrayList<String> pages = new ArrayList<String>();
                    pages.add("§6§l§m     [§b§l LUMINA §6§m§l]     §r\n\nVous pouvez cr\u00e9er votre \u00eele en entrant la commande §9/is create§r  vous pourrez ensuite s\u00e9lectionner votre type d'\u00eele parmi la liste dans le menu.");
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
