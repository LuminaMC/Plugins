package fr.megacretin.duel;

import org.bukkit.plugin.java.*;
import org.bukkit.entity.*;
import fr.megacretin.arena.*;
import java.util.*;
import fr.megacretin.duel.arenas.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.*;
import net.md_5.bungee.api.chat.*;

public class Main extends JavaPlugin
{
    private Map<Player, Player> players;
    private ArenaManager arenaManager;
    public static boolean isStarted;
    
    static {
        Main.isStarted = false;
    }
    
    public Main() {
        this.players = new HashMap<Player, Player>();
        this.arenaManager = new ArenaManager();
    }
    
    public void onEnable() {
        this.getCommand("duel").setExecutor((CommandExecutor)this);
        this.getServer().getPluginManager().registerEvents((Listener)new ArenaListener(this), (Plugin)this);
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (label.equalsIgnoreCase("duel") && sender instanceof Player) {
            final Player player = (Player)sender;
            if (args.length == 0) {
                player.sendMessage("§5§lDUEL §8»§7§lInfo:");
                player.sendMessage("§7La commande pour d\u00e9fier est: §e/duel <player>");
                return true;
            }
            if (args.length == 1) {
                final String targetName = args[0];
                if (args[0].equalsIgnoreCase("accept")) {
                    if (this.players.containsKey(player) && !Main.isStarted) {
                        player.sendMessage("§5§lDUEL §8» §fPr\u00e9pare toi ! Le duel va commencer !");
                        final Player firstPlayerTarget = this.players.get(player);
                        firstPlayerTarget.sendMessage("§5§lDUEL §8» §fPr\u00e9pare toi ! Le duel va commencer !");
                        this.players.remove(player);
                        player.setAllowFlight(false);
                        firstPlayerTarget.setAllowFlight(false);
                        ArenaManager.addArenaPlayer(player);
                        ArenaManager.addArenaPlayer(firstPlayerTarget);
                        player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 110.0, 49.5, -180.0f, 0.0f));
                        firstPlayerTarget.teleport(new Location(Bukkit.getWorld("world"), 6.5, 110.0, 27.5, 0.0f, 0.0f));
                        Main.isStarted = true;
                    }
                    else {
                        player.sendMessage("§5§lDUEL §8» §cUn combat est d\u00e9j\u00e0 en cours !!");
                    }
                    this.players.remove(player);
                }
                else if (args[0].equalsIgnoreCase("deny")) {
                    if (this.players.containsKey(player)) {
                        player.sendMessage("§5§lDUEL §8» §cVous avez refus\u00e9 le duel");
                        final Player firtPlayerTarget = this.players.get(player);
                        firtPlayerTarget.sendMessage("§5§lDUEL §8» §cLe joueur §4" + player.getName() + "§c a refus\u00e9 le duel !");
                        this.players.remove(player);
                    }
                }
                else if (Bukkit.getPlayer(targetName) != null) {
                    final Player target = Bukkit.getPlayer(targetName);
                    if (this.players.containsKey(target)) {
                        player.sendMessage("§5§lDUEL §8» §cAttention, ce joueur a d\u00e9j\u00e0 une demande de d\u00e9fi en cours");
                        return true;
                    }
                    if (Main.isStarted) {
                        player.sendMessage("§5§lDUEL §8» §cUn duel est \u00e9j\u00e0 en cours");
                    }
                    this.players.put(target, player);
                    player.sendMessage("§5§lDUEL §8» §7Vous venez de d\u00e9fier §a" + targetName);
                    final TextComponent demande = new TextComponent("§5§lDUEL §8» §7Vous \u00eates d\u00e9fi\u00e9 par §a" + player.getName() + "\n \n");
                    final TextComponent accept = new TextComponent("§a1. Accepter le duel\n");
                    final TextComponent deny = new TextComponent("§c2. Refuser le duel");
                    demande.setHoverEvent((HoverEvent)null);
                    demande.setClickEvent((ClickEvent)null);
                    accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aAccepter le duel contre " + player.getName()).create()));
                    accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel accept"));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel deny"));
                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aRefuser le duel contre " + player.getName()).create()));
                    demande.addExtra((BaseComponent)accept);
                    accept.addExtra((BaseComponent)deny);
                    target.spigot().sendMessage((BaseComponent)demande);
                }
                else {
                    player.sendMessage("§5§lDUEL §8» §cLe joueur n'est pas connect\u00e9");
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean isStarded() {
        return Main.isStarted;
    }
    
    public ArenaManager getArenaManager() {
        return this.arenaManager;
    }
    
    public void setArenaManager(final ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }
}
