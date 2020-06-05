package net.bytobyx.economylumina;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&6&lMONEY &8» &cUniquement pour les joueurs !".replace("&", "\u00a7"));
            return true;
        }
        Player p = (Player) sender;

        if (args.length == 2 && args[1].matches("[0-9.]+")) {
            if (Main.economy.hasAccount(args[0])) {
                if (args[1].equalsIgnoreCase(p.getName())) {
                    p.sendMessage("&6&lMONEY &8» &7Vous avez transféré l'argent d'une de vos poches à une autre...".replace("&", "\u00a7"));
                    return true;
                }
                if (Main.economy.getBalance(p.getName()) >= Double.valueOf(args[1])) {
                    if(args[1].contains(".") || args[1].contains(",")){
                        p.sendMessage("§6§lMONEY §8» §7Vous êtes sérieux avec vos nombres à virgules !?");
                        return false;
                    }
                    Main.economy.depositPlayer(args[0], Double.valueOf(args[1]).doubleValue());
                    Main.economy.withdrawPlayer(p.getName(), Double.valueOf(args[1]).doubleValue());
                    p.sendMessage("&6&lMONEY &8» &dVous avez envoyé &f%summ% &7Lumines à &f%receiving%".replace("&", "\u00a7").replace("%summ%", Main.getMoneyFormat(String.valueOf(Double.valueOf(args[1])))).replace("%receiving%", args[0]));
                    if (Bukkit.getOnlinePlayers().contains((Object) Bukkit.getPlayer((String) args[0]))) {
                        Bukkit.getPlayer((String) args[0]).sendMessage("&6&lMONEY &8» &7Vous avez reçu &f%summ% &7Lumines de &f%sender%.".replace("&", "\u00a7").replace("%summ%", Main.getMoneyFormat(String.valueOf(Double.valueOf(args[1])))).replace("%sender%", p.getName()));
                    }
                    return true;
                }
                p.sendMessage("§6§lMONEY §8» §cVous n'avez pas assez d'argent");
                return true;
            }
            p.sendMessage("§6§lMONEY §8» §f%player% §7n'a pas de compte".replace("%player%", args[1]));
            return true;
        }
        p.sendMessage("§6§lMONEY §8» §cLa commande est /pay [joueur] [montant]");
        return true;

    }
}
