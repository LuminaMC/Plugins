/*
 * Decompiled with CFR 0_125.
 *
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 */
package net.bytobyx.economylumina;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Commands
        implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.inst.getConfig().getString("Messages.OnlyForPlayers").replace("&", "\u00a7"));
                return true;
            }
            Player p = (Player) sender;

            p.sendMessage(Main.inst.getConfig().getString("Messages.YourBalance").replace("&", "\u00a7").replace("%balance%", Main.getMoneyFormat(String.valueOf(Main.economy.getBalance(p.getName())))));
            return true;
        }
        /*if (args[0].equalsIgnoreCase("pay")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.inst.getConfig().getString("Messages.OnlyForPlayers").replace("&", "\u00a7"));
                return true;
            }
            Player p = (Player) sender;

            if (args.length == 3 && args[2].matches("[0-9.]+")) {
                if (Main.economy.hasAccount(args[1])) {
                    if (args[1].equalsIgnoreCase(p.getName())) {
                        p.sendMessage(Main.inst.getConfig().getString("Messages.PayToMyself").replace("&", "\u00a7"));
                        return true;
                    }
                    if (Main.economy.getBalance(p.getName()) >= Double.valueOf(args[2])) {
                        Main.economy.depositPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                        Main.economy.withdrawPlayer(p.getName(), Double.valueOf(args[2]).doubleValue());
                        p.sendMessage(Main.inst.getConfig().getString("Messages.PaySend").replace("&", "\u00a7").replace("%summ%", Main.getMoneyFormat(String.valueOf(Double.valueOf(args[2])))).replace("%receiving%", args[1]));
                        if (Bukkit.getOnlinePlayers().contains((Object) Bukkit.getPlayer((String) args[1]))) {
                            Bukkit.getPlayer((String) args[1]).sendMessage(Main.inst.getConfig().getString("Messages.PayReceive").replace("&", "\u00a7").replace("%summ%", Main.getMoneyFormat(String.valueOf(Double.valueOf(args[2])))).replace("%sender%", p.getName()));
                        }
                        return true;
                    }
                    p.sendMessage(Main.inst.getConfig().getString("Messages.NotEnoughMoney").replace("&", "\u00a7"));
                    return true;
                }
                p.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(Main.inst.getConfig().getString("Messages.PayUsage").replace("&", "\u00a7"));
            return true;
        }*/
        if (args[0].equalsIgnoreCase("check")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.inst.getConfig().getString("Messages.OnlyForPlayers").replace("&", "\u00a7"));
                return true;
            }
            Player p = (Player) sender;

            if (args.length == 2) {
                if (Main.economy.hasAccount(args[1])) {
                    if (args[1].equalsIgnoreCase(p.getName())) {
                        p.sendMessage(Main.inst.getConfig().getString("Messages.YourBalance").replace("&", "\u00a7").replace("%balance%", Main.getMoneyFormat(String.valueOf(Main.economy.getBalance(p.getName())))));
                        return true;
                    }
                    p.sendMessage(Main.inst.getConfig().getString("Messages.OtherBalance").replace("&", "\u00a7").replace("%other%", args[1]).replace("%balance%", Main.getMoneyFormat(String.valueOf(Main.economy.getBalance(args[1])))));
                    return true;
                }
                p.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                return true;
            }
            p.sendMessage(Main.inst.getConfig().getString("Messages.CheckUsage").replace("&", "\u00a7"));
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.inst.getConfig().getString("Messages.OnlyForPlayers").replace("&", "\u00a7"));
                return true;
            }
            Player p = (Player) sender;
            if (p.hasPermission("economy.admin") || p.isOp()) {
                for (String message : Main.inst.getConfig().getStringList("Messages.HelpAdmin")) {
                    p.sendMessage(message.replace("&", "\u00a7"));
                }
                return true;
            }
            for (String message : Main.inst.getConfig().getStringList("Messages.HelpPlayer")) {
                p.sendMessage(message.replace("&", "\u00a7"));
            }
            return true;
        }
        /*if (args[0].equalsIgnoreCase("top")) {
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
                ItemStack skullStack = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
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
            int i = 0;
            //while (i < Main.topSize) {
                String[] s = Main.topUsers.get(i).split(";");
                p.sendMessage(Main.inst.getConfig().getString("Messages.TopStructure").replace("&", "\u00a7").replace("%place%", "" + (i + 1)).replace("%player%", s[0]).replace("%balance%", s[1]));
                ++i;
            }//
            return true;
        }*/
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("economy.admin") || p.isOp()) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (args.length == 3 && args[2].matches("[0-9.]+")) {
                        if (Main.economy.hasAccount(args[1])) {
                            Main.economy.depositPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                            p.sendMessage(Main.inst.getConfig().getString("Messages.GiveSuccessfully").replace("&", "\u00a7"));
                            return true;
                        }
                        p.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                        return true;
                    }
                    p.sendMessage(Main.inst.getConfig().getString("Messages.GiveUsage").replace("&", "\u00a7"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("take")) {
                    if (args.length == 3 && args[2].matches("[0-9.]+")) {
                        if (Main.economy.hasAccount(args[1])) {
                            if (Main.economy.getBalance(args[1]) < Double.valueOf(args[2])) {
                                p.sendMessage(Main.inst.getConfig().getString("Messages.TakeNotEnoughMoney").replace("&", "\u00a7"));
                                return true;
                            }
                            Main.economy.withdrawPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                            p.sendMessage(Main.inst.getConfig().getString("Messages.TakeSuccessfully").replace("&", "\u00a7"));
                            return true;
                        }
                        p.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                        return true;
                    }
                    p.sendMessage(Main.inst.getConfig().getString("Messages.TakeUsage").replace("&", "\u00a7"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 3 && args[2].matches("[0-9.]+")) {
                        if (Main.economy.hasAccount(args[1])) {
                            Main.economy.withdrawPlayer(args[1], Main.economy.getBalance(args[1]));
                            Main.economy.depositPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                            p.sendMessage(Main.inst.getConfig().getString("Messages.SetSuccessfully").replace("&", "\u00a7"));
                            return true;
                        }
                        p.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                        return true;
                    }
                    p.sendMessage(Main.inst.getConfig().getString("Messages.SetUsage").replace("&", "\u00a7"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Main.inst.reloadConfig();
                    Main.inst.reloadEconomy();
                    p.sendMessage(Main.inst.getConfig().getString("Messages.ReloadedSuccessfully").replace("&", "\u00a7"));
                    return true;
                }
                for (String message : Main.inst.getConfig().getStringList("Messages.HelpAdmin")) {
                    p.sendMessage(message.replace("&", "\u00a7"));
                }
                return true;
            }
            for (String message : Main.inst.getConfig().getStringList("Messages.HelpPlayer")) {
                p.sendMessage(message.replace("&", "\u00a7"));
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 3 && args[2].matches("[0-9.]+")) {
                if (Main.economy.hasAccount(args[1])) {
                    Main.economy.depositPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                    sender.sendMessage(Main.inst.getConfig().getString("Messages.GiveSuccessfully").replace("&", "\u00a7"));
                    return true;
                }
                sender.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                return true;
            }
            sender.sendMessage(Main.inst.getConfig().getString("Messages.GiveUsage").replace("&", "\u00a7"));
            return true;
        }
        if (args[0].equalsIgnoreCase("take")) {
            if (args.length == 3 && args[2].matches("[0-9.]+")) {
                if (Main.economy.hasAccount(args[1])) {
                    Main.economy.withdrawPlayer(args[1], Double.valueOf(args[2]).doubleValue());
                    sender.sendMessage(Main.inst.getConfig().getString("Messages.TakeSuccessfully").replace("&", "\u00a7"));
                    return true;
                }
                sender.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                return true;
            }
            sender.sendMessage(Main.inst.getConfig().getString("Messages.TakeUsage").replace("&", "\u00a7"));
            return true;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length == 3 && args[2].matches("[0-9.]+")) {
                if (Main.economy.hasAccount(args[1])) {
                    if (Main.sql) {
                        SQLManager.setBalance(args[1], Double.valueOf(args[2]));
                    } else {
                        File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
                        YamlConfiguration data = YamlConfiguration.loadConfiguration((File) playerdata);
                        data.set(args[1].toLowerCase(), (Object) Main.getMoneyFormat(Double.valueOf(args[2])));
                        try {
                            data.save(playerdata);
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    }
                    sender.sendMessage(Main.inst.getConfig().getString("Messages.SetSuccessfully").replace("&", "\u00a7"));
                    return true;
                }
                sender.sendMessage(Main.inst.getConfig().getString("Messages.NoAccount").replace("&", "\u00a7").replace("%player%", args[1]));
                return true;
            }
            sender.sendMessage(Main.inst.getConfig().getString("Messages.SetUsage").replace("&", "\u00a7"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            Main.inst.reloadConfig();
            Main.inst.reloadEconomy();
            sender.sendMessage(Main.inst.getConfig().getString("Messages.ReloadedSuccessfully").replace("&", "\u00a7"));
            return true;
        }
        for (String message : Main.inst.getConfig().getStringList("Messages.HelpAdmin")) {
            sender.sendMessage(message.replace("&", "\u00a7"));
        }
        return true;
    }

    public static String returnSplitSold(float f) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(f).replace("$", "").replace(".00", "");
    }
}

