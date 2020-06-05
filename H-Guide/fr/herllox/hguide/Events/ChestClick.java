package fr.herllox.hguide.Events;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class ChestClick implements Listener
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
                case CHEST: {
                    final ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    final BookMeta bookMeta = (BookMeta)writtenBook.getItemMeta();
                    bookMeta.setTitle("§b§lGuide§e§lMenu");
                    bookMeta.setAuthor("Herllox");
                    final ArrayList<String> pages = new ArrayList<String>();
                    pages.add("§6§l§m     [§b§l LUMINA §6§m§l]     §r\n\nPour cr\u00e9er un ChestShop il faut remplir un coffre ou un tonneau de la ressource que vous voulez vendre. Il faut ensuite poser un panneau dessus et remplir les lignes de cette fa\u00e7on\n ----> ");
                    pages.add("Laisser la premi\u00e8re ligne vide\n\nSur la 2\u00e8me ligne choisir le nombre d'objets vendu ou acheter par action\n\nSur la 3\u00e8me ligne B + prix pour vendre : S + pour acheter (Les \":\" sont obligatoires !)\n\n ---->");
                    pages.add("Et mettre un \"?\" sur la 4\u00e8me ligne");
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
