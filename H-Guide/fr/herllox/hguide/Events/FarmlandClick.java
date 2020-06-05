package fr.herllox.hguide.Events;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class FarmlandClick implements Listener
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
                case FARMLAND: {
                    final ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta bookMeta = (BookMeta)writtenBook.getItemMeta();
                    bookMeta.setTitle("§b§lGuide§e§lMenu");
                    bookMeta.setAuthor("Herllox");
                    final ArrayList<String> pages = new ArrayList<String>();
                    pages.add("§6§l§m     [§b§l LUMINA §6§m§l]     §r\n\nFaire une usine \u00e0 mobs est d\u00e9conseiller \u00e0 cause de leur faible rentabilit\u00e9 en revanche des spawners reste disponible dans les zone de farm ou bien \u00e0 l'achat au §9/shop§r.");
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
