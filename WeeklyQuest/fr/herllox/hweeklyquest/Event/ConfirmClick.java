package fr.herllox.hweeklyquest.Event;

import fr.herllox.hweeklyquest.DataBase.BDD;
/*    */ import fr.herllox.hweeklyquest.Main;
/*    */ import fr.herllox.hweeklyquest.UtilMoney;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Calendar;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;

public class ConfirmClick
        implements org.bukkit.event.Listener {
    private final Main main;

    public ConfirmClick(Main main) {
        this.main = main;
    }

    private int amount = 0;

    @org.bukkit.event.EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        Calendar cal = Calendar.getInstance();

        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Menu.Confirmation").replace("&", "§"))) {
            e.setCancelled(true);
            if ((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().getType() == null) || (!e.getCurrentItem().hasItemMeta())) {
                p.closeInventory();
                return;
            }

            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                if (e.getCurrentItem().getDurability() == 5) {
                    if (p.getInventory().contains(Material.getMaterial(this.main.getConfig().getString("WeekItems")))) {
                        for (int n1 = 0; n1 <= 36; n1++) {
                            if ((p.getInventory().getItem(n1) != null) &&
                                    (p.getInventory().getItem(n1).getType() == Material.getMaterial(this.main.getConfig().getString("WeekItems")))) {
                                this.amount += p.getInventory().getItem(n1).getAmount();
                            }
                        }
                        try {
                            if (!BDD.playerExist(p)) {
                                ItemStack ui = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")));
                                ItemStack is = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")), this.amount);
                                float price = UtilMoney.getPriceSellUnity(ui) * ui.getAmount();
                                float endprice = price * this.amount;
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + p.getName() + " " + endprice);
                                BDD.addPlayer(p);
                                BDD.addItem(p, this.amount);
                                p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Messages.AddBDD").replace("&", "§").replace("%oldnumber%", new StringBuilder().append("").append(this.amount).toString()).replace("%newnumber%", new StringBuilder().append("").append(this.amount).toString()).replace("%money%", new StringBuilder().append("").append(endprice).toString()));
                                p.getInventory().removeItem(new ItemStack[]{is});
                                this.amount = 0;
                            } else {
                                ItemStack ui = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")));
                                int nbr = BDD.checkNbr(p).intValue();
                                int end = this.amount + nbr;
                                ItemStack is = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")), end);
                                float price = UtilMoney.getPriceSellUnity(ui) * ui.getAmount();
                                float endprice = price * this.amount;
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + p.getName() + " " + endprice);
                                BDD.addItem(p, end);
                                p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Messages.AddBDD").replace("&", "§").replace("%oldnumber%", new StringBuilder().append("").append(this.amount).toString()).replace("%newnumber%", new StringBuilder().append("").append(end).toString()).replace("%money%", new StringBuilder().append("").append(endprice).toString()));
                                p.getInventory().removeItem(new ItemStack[]{is});
                                this.amount = 0;
                            }
                        } catch (SQLException ev) {
                            ev.printStackTrace();
                        }
                        p.closeInventory();
                    } else {
                        p.closeInventory();
                        p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Messages.NothingInv").replace("&", "§"));
                    }

                }
            }
            if (e.getCurrentItem().getDurability() == 14) {
                p.closeInventory();
                Bukkit.dispatchCommand(p, "wq");
                return;
            } else {
                return;
            }


        }
    }
}



