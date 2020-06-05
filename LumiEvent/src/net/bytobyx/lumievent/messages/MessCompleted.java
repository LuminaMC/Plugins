package net.bytobyx.lumievent.messages;

import fr.lumina.money.database.Money;
import net.bytobyx.lumievent.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class MessCompleted implements Listener {
    private final Main main;

    public MessCompleted(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (Main.Messageon.booleanValue() == true) {
            if (e.getMessage().equalsIgnoreCase(Main.goodmess)) {
                e.setCancelled(true);
                Bukkit.broadcastMessage("§e§lEVENT§8§l » §7Bravo à §a" + p.getName() + "§7 qui a retrouvé le mot :§a " + Main.goodmess);

                Random r = new Random();
                int choix = 10;
                int alea = 1 + r.nextInt(choix - 1);

                if(alea == 1){
                    ItemStack keyComm = new ItemStack(Material.TRIPWIRE_HOOK, 1);
                    ItemMeta keyComM = keyComm.getItemMeta();
                    keyComM.setDisplayName("§fClé §e§lORDINAIRE");
                    keyComM.setLore(Arrays.asList(" ", "§7Faites /spawn et utilisez votre clé sur le coffre",
                            "§7afin de recevoir une récompense aléatoire !", " ", "§f§lSHOP.LUMINAMC.FR"));
                    keyComM.addEnchant(Enchantment.MENDING, 1, false);
                    keyComM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                    keyComM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_PLACED_ON });
                    keyComm.setItemMeta(keyComM);

                    p.getInventory().addItem(new ItemStack[]{keyComm});
                    p.sendMessage("§6§lINFO §8§l» §7Tu as gagné §fune clé ordinaire");
                    Main.Messageon = Boolean.valueOf(false);
                    return;
                }if(alea == 2 || alea == 3){
                    ItemStack sellstick = new ItemStack(Material.BLAZE_ROD);
                    ItemMeta sellstickM = sellstick.getItemMeta();
                    sellstickM.setDisplayName("§6§lSell Stick");
                    sellstickM.setLore(Arrays.asList(" ", "§7Ce fabuleux item vous permet de vendre vos",
                            "§7coffres, attention son utilisation est parfois limitée", " ", "   §7▪ Nombre d'utilisations: §610",
                            " ", "§f§lSHOP.LUMINAMC.FR"));
                    sellstickM.setUnbreakable(true);
                    sellstickM.addEnchant(Enchantment.MENDING, 1, true);
                    sellstickM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    sellstickM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    sellstickM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    sellstick.setItemMeta(sellstickM);
                    p.getInventory().addItem(sellstick);
                    p.sendMessage("§6§lINFO §8§l» §7Tu as gagné §fun sellstick 10 utilisations");
                    Main.Messageon = Boolean.valueOf(false);
                    return;
                }if(alea == 4 || alea == 5){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "doublevente "+p.getName() + " 300");
                    p.sendMessage("§6§lINFO §8§l» §7Tu as gagné §fun double vente de 5 minutes");
                    Main.Messageon = Boolean.valueOf(false);
                    return;
                } else{
                    new Money().addLumines(p.getName(), 2000);
                    p.sendMessage("§6§lINFO §8§l» §7Tu as gagné §f2,000 Lumines");
                    Main.Messageon = Boolean.valueOf(false);
                    return;
                }

            }
        } else {
        }
    }
}
