package fr.thomb1997.lumiregles;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.configuration.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

public class OpenMenu
{
    Main main;
    
    public OpenMenu(final Main main) {
        this.main = main;
    }
    
    public void Menu(final Player p) {
        final int size = this.main.getConfig().getInt("Main.Size");
        final String inventoryname = this.main.getConfig().getString("Title");
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, size, ChatColor.translateAlternateColorCodes('&', inventoryname));
        final ConfigurationSection section = this.main.getConfig().getConfigurationSection("Main.Items");
        for (final String key : section.getKeys(false)) {
            final Material material1 = Material.valueOf(this.main.getConfig().getString("Main.Items." + key + ".Material"));
            final String name = this.main.getConfig().getString("Main.Items." + key + ".Name");
            final ItemStack itemstack = new ItemStack(material1, 1);
            final ItemMeta itemstackM = itemstack.getItemMeta();
            itemstackM.setDisplayName(name);
            if (this.main.getConfig().contains("Main.Items." + key + ".Lore")) {
                final List<String> lore = (List<String>)this.main.getConfig().getStringList("Main.Items." + key + ".Lore");
                itemstackM.setLore((List)lore);
            }
            itemstack.setItemMeta(itemstackM);
            final int position = this.main.getConfig().getInt("Main.Items." + key + ".Position") - 1;
            inv.setItem(position, itemstack);
        }
        p.openInventory(inv);
    }
}
