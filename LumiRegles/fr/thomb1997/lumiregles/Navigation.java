package fr.thomb1997.lumiregles;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class Navigation implements Listener
{
    Main main;
    OpenMenu openmenu;
    
    public Navigation(final Main main) {
        this.main = main;
        this.openmenu = new OpenMenu(main);
    }
    
    @EventHandler
    public void CatChoose(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (p.getOpenInventory().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Title"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.ARROW) {
                this.openmenu.Menu(p);
            }
            else {
                for (final String section : this.main.getConfig().getConfigurationSection("Main.Items").getKeys(false)) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() == this.main.getConfig().getString("Main.Items." + section + ".Name")) {
                        final String section2 = this.main.getConfig().getString("Main.Items." + section + ".Categorie");
                        final int size = this.main.getConfig().getInt("Categories." + section2 + ".Size");
                        final String inventoryname = this.main.getConfig().getString("Title");
                        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, size, ChatColor.translateAlternateColorCodes('&', inventoryname));
                        for (final String key : this.main.getConfig().getConfigurationSection("Categories." + section2 + ".Items").getKeys(false)) {
                            final Material material1 = Material.valueOf(this.main.getConfig().getString("Categories." + section2 + ".Items." + key + ".Material"));
                            final String name = this.main.getConfig().getString("Categories." + section2 + ".Items." + key + ".Name");
                            final ItemStack itemstack = new ItemStack(material1, 1);
                            final ItemMeta itemstackM = itemstack.getItemMeta();
                            itemstackM.setDisplayName(name);
                            if (this.main.getConfig().contains("Categories." + section2 + ".Items." + key + ".Lore")) {
                                final List<String> lore = (List<String>)this.main.getConfig().getStringList("Categories." + section2 + ".Items." + key + ".Lore");
                                itemstackM.setLore((List)lore);
                            }
                            itemstack.setItemMeta(itemstackM);
                            final int position = this.main.getConfig().getInt("Categories." + section2 + ".Items." + key + ".Position") - 1;
                            inv.setItem(position, itemstack);
                        }
                        p.openInventory(inv);
                        break;
                    }
                }
            }
        }
    }
}
