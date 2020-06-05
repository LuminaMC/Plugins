/*    */ package fr.herllox.hweeklyquest.Event;
/*    */ 
/*    */ import fr.herllox.hweeklyquest.Main;
/*    */ import java.util.Arrays;
/*    */ import java.util.Calendar;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.PlayerInventory;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ public class ClickPlayerHead
/*    */   implements org.bukkit.event.Listener
/*    */ {
/*    */   private final Main main;
/*    */   
/* 23 */   public ClickPlayerHead(Main main) { this.main = main; }
/*    */   
/* 25 */   private int amount = 0;
/*    */   
/*    */   @EventHandler
/*    */   public void onClick(InventoryClickEvent e)
/*    */   {
/* 30 */     Player p = (Player)e.getWhoClicked();
/*    */     
/* 32 */     Calendar cal = Calendar.getInstance();
/*    */     
/* 34 */     if (e.getClickedInventory() == null) {
/* 35 */       e.setCancelled(true);
/*    */     }
/* 37 */     if (e.getView().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Menu.Name").replace("&", "§"))) {
/* 38 */       e.setCancelled(true);
/* 39 */       if ((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().getType() == null) || (!e.getCurrentItem().hasItemMeta())) {
/* 40 */         p.closeInventory();
/* 41 */         return;
/*    */       }
/*    */       
/* 44 */       switch (e.getCurrentItem().getType()) {
            /*    */       case SKULL_ITEM:
/* 46 */         ItemStack is = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")));
/* 47 */         if (p.getInventory().contains(Material.getMaterial(this.main.getConfig().getString("WeekItems")))) {
/* 48 */           for (int n1 = 0; n1 <= 36; n1++) {
/* 49 */             if ((p.getInventory().getItem(n1) != null) && 
/* 50 */               (p.getInventory().getItem(n1).getType() == Material.getMaterial(this.main.getConfig().getString("WeekItems")))) {
/* 51 */               this.amount += p.getInventory().getItem(n1).getAmount();
/*    */             }
/*    */           }
/*    */           
/*    */ 
/*    */ 
/* 57 */            float price = fr.herllox.hweeklyquest.UtilMoney.getPriceSellUnity(is)*is.getAmount();
/* 58 */           float endprice = price * this.amount;
/*    */           
/*    */ 
/* 61 */           Inventory inv = org.bukkit.Bukkit.createInventory(null, 27, this.main.getConfig().getString("Menu.Confirmation").replace("&", "§"));
/*    */           
/*    */ 
/*    */ 
/* 65 */           ItemStack conf = new ItemStack(Material.STAINED_GLASS_PANE, 1 ,(byte) 5);
/* 66 */           ItemMeta confx = conf.getItemMeta();
/* 67 */           confx.setDisplayName("§aConfirmation");
/* 68 */           confx.setLore(Arrays.asList(new String[] { "", " §7• Gains: §f" + endprice + " §fLumines", " §7• Nombre: §f" + this.amount + "§f Items", " ", "§7En cliquant ici, tu confirmes vouloir", "§7transférer tes items pour cette quêtes." }));
/* 69 */           conf.setItemMeta(confx);
/* 70 */           inv.setItem(11, conf);
/*    */           
/*    */ 
/* 73 */           ItemStack canc = new ItemStack(Material.STAINED_GLASS_PANE, 1 ,(byte)14);
/* 74 */           ItemMeta cancx = conf.getItemMeta();
/* 75 */           cancx.setDisplayName("§cRetour");
/* 76 */           cancx.setLore(Arrays.asList(new String[] { "", "§7En cliquant ici, tu annules le transfert", "§7des items pour la quêtes." }));
/* 77 */           canc.setItemMeta(cancx);
/* 78 */           inv.setItem(15, canc);
/*    */           
/* 80 */           p.closeInventory();
/* 81 */           p.openInventory(inv);
/* 82 */           this.amount = 0;
/*    */         }
/*    */         else {
/* 85 */           p.closeInventory();
/* 86 */           p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Messages.NeedItem").replace("&", "§")); return;
/*    */         }
/*    */         break;
/*    */       case BEACON: 
/*    */         break;
/*    */       case STAINED_GLASS_PANE:
/*    */         break;
/*    */       }
/*    */     }
/*    */   }
/*    */ }

