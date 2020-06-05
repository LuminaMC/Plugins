package fr.herllox.hstaff.Commands;

import org.bukkit.event.*;
import fr.herllox.hstaff.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;
import java.util.*;
import org.bukkit.inventory.*;

public class OpenMenu implements CommandExecutor, Listener
{
    private final Main main;
    
    public OpenMenu(final Main main) {
        this.main = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String msg, final String[] args) {
        final Player p = (Player)sender;
        if (args.length == 0) {
            final Random r = new Random();
            final int randomPlayerNumber = r.nextInt(Bukkit.getServer().getOnlinePlayers().size());
            final List<Player> players = new ArrayList<Player>();
            for (final Player online : Bukkit.getOnlinePlayers()) {
                final int randomPlayer = new Random().nextInt(players.size());
                final Player player = players.get(randomPlayer);
                p.sendMessage("p" + player);
            }
            final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, "§cStaff");
            final ItemStack i1 = new ItemStack(Material.PLAYER_HEAD);
            final SkullMeta i1x = (SkullMeta)i1.getItemMeta();
            i1x.setDisplayName("§aRandom TP");
            i1x.setLore((List)Collections.singletonList("§7Clique pour tp random"));
            i1.setItemMeta((ItemMeta)i1x);
            new BukkitRunnable() {
                public void run() {
                }
            }.runTaskTimer((Plugin)this.main, 100L, 100L);
            return false;
        }
        return false;
    }
}
