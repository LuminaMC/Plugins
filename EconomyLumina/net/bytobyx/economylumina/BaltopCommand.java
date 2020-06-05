package net.bytobyx.economylumina;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class BaltopCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.inst.getConfig().getString("Messages.OnlyForPlayers").replace("&", "\u00a7"));
            return true;
        }
        Player p = (Player) sender;

        if (Main.topUsers.size() == 0 || Main.topUsers == null) {
            p.sendMessage(Main.inst.getConfig().getString("Messages.TopNull").replace("&", "\u00a7"));
            return true;
        }

        Inventory inv = Bukkit.createInventory(null, 27, "§6§lMONEY §7Joueurs riches");
        int place = 4;

        for (int i = 0; i < Main.topUsers.size(); i++) {
            String[] s = Main.topUsers.get(i).split(";");
            if (i == 0) {
                place = 4;
            }
            if (i == 1) {
                place = 12;
            }
            if (i == 2) {
                place = 14;
            }
            if (i >= 3) {
                place = 19 + (i - 3);
            }
            ItemStack skullStack = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) skullStack.getItemMeta();
            //System.out.println(ar.get(i));
            meta.setDisplayName("§6§l" + Main.topUsers.get(i).split(";")[0] + " §r§7(#" + (i + 1) + ")");
            String pseudo = (String) Main.topUsers.get(i).split(";")[0];

            meta.setLore(
                    Arrays.asList(" ", "  §f∙ " + returnSplitSold(Float.parseFloat(Main.topUsers.get(i).split(";")[1])) + " Lumines  ", " "));


            meta.setOwner(Main.topUsers.get(i).split(";")[0]);
            skullStack.setItemMeta(meta);
            inv.setItem(place, skullStack);

        }
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        player.openInventory(inv);
            /*int i = 0;
            while (i < Main.topSize) {
                String[] s = Main.topUsers.get(i).split(";");
                p.sendMessage(Main.inst.getConfig().getString("Messages.TopStructure").replace("&", "\u00a7").replace("%place%", "" + (i + 1)).replace("%player%", s[0]).replace("%balance%", s[1]));
                ++i;
            }*/
        return true;
    }

    public static String returnSplitSold(float f) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(f).replace("$", "").replace(".00", "");
    }
}
