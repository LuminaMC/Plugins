/*     */ package fr.herllox.hweeklyquest.Event;
/*     */ 
/*     */ import fr.herllox.hweeklyquest.Main;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class GoldBlockClick implements org.bukkit.event.Listener
/*     */ {
/*     */   private final Main main;
/*     */   
/*     */   public GoldBlockClick(Main main)
/*     */   {
/*  18 */     this.main = main;
/*     */   }
/*     */   
/*     */   @org.bukkit.event.EventHandler
/*     */   public void onClick(InventoryClickEvent e)
/*     */   {
/*  24 */     Player p = (Player)e.getWhoClicked();
/*     */     
/*  26 */     if (e.getClickedInventory() == null) {
/*  27 */       e.setCancelled(true);
/*     */     }
/*  29 */     if (e.getView().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Menu.Name").replace("&", "§"))) {
/*  30 */       e.setCancelled(true);
/*  31 */       if ((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().getType() == null) || (!e.getCurrentItem().hasItemMeta())) {
/*  32 */         p.closeInventory();
/*  33 */         return;
/*     */       }
/*     */       
/*  36 */       switch (e.getCurrentItem().getType())
/*     */       {
/*     */       case GOLD_BLOCK: 
/*  39 */         if (e.getCurrentItem().getItemMeta().getLore().toString().contains("la §61 ère")) {
/*  40 */           if (this.main.getConfig().getBoolean("Winner.One.recup") == true) {
/*  41 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + this.main.getConfig().getString("Winner.One.player") + " " + Float.valueOf(this.main.getConfig().getString("Reward.One.Money")));
/*  42 */             p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Reward.One.Message"));
/*  43 */             if (this.main.getConfig().getBoolean("Reward.One.Commands") == true) {
/*  44 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.One.Command1").replace("%player%", this.main.getConfig().getString("Winner.One.player")));
/*  45 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.One.Command2").replace("%player%", this.main.getConfig().getString("Winner.One.player")));
/*  46 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.One.Command3").replace("%player%", this.main.getConfig().getString("Winner.One.player")));
/*     */             }
/*  48 */             this.main.getConfig().set("Winner.One.recup", Boolean.valueOf(false));
/*     */           }
/*     */           
/*     */         }
/*  52 */         else if (e.getCurrentItem().getItemMeta().getLore().toString().contains("la §62 èmes")) {
/*  53 */           if (this.main.getConfig().getBoolean("Winner.Two.recup") == true) {
/*  54 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + this.main.getConfig().getString("Winner.Two.player") + " " + Float.valueOf(this.main.getConfig().getString("Reward.Three.Money")));
/*  55 */             p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Reward.Two.Message"));
/*  56 */             if (this.main.getConfig().getBoolean("Reward.Two.Commands") == true) {
/*  57 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Three.Command1").replace("%player%", this.main.getConfig().getString("Winner.Two.player")));
/*  58 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Three.Command2").replace("%player%", this.main.getConfig().getString("Winner.Two.player")));
/*     */             }
/*  60 */             this.main.getConfig().set("Winner.Two.recup", Boolean.valueOf(false));
/*     */           }
/*     */           
/*     */         }
/*  64 */         else if (e.getCurrentItem().getItemMeta().getLore().toString().contains("la §63 èmes")) {
/*  65 */           if (this.main.getConfig().getBoolean("Winner.Three.recup") == true) {
/*  66 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + this.main.getConfig().getString("Winner.Two.player") + " " + Float.valueOf(this.main.getConfig().getString("Reward.Two.Money")));
/*  67 */             p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Reward.Two.Message"));
/*  68 */             if (this.main.getConfig().getBoolean("Reward.Three.Commands") == true) {
/*  69 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Three.Command1").replace("%player%", this.main.getConfig().getString("Winner.Three.player")));
/*  70 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Three.Command2").replace("%player%", this.main.getConfig().getString("Winner.Three.player")));
/*     */             }
/*  72 */             this.main.getConfig().set("Winner.Three.recup", Boolean.valueOf(false));
/*     */           }
/*     */           
/*     */         }
/*  76 */         else if (e.getCurrentItem().getItemMeta().getLore().toString().contains("la §64 èmes")) {
/*  77 */           if (this.main.getConfig().getBoolean("Winner.Four.recup") == true) {
/*  78 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + this.main.getConfig().getString("Winner.Four.player") + " " + Float.valueOf(this.main.getConfig().getString("Reward.Four.Money")));
/*  79 */             p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Reward.Four.Message"));
/*  80 */             if (this.main.getConfig().getBoolean("Reward.Four.Commands") == true) {
/*  81 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Four.Command1").replace("%player%", this.main.getConfig().getString("Winner.Four.player")));
/*  82 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Four.Command2").replace("%player%", this.main.getConfig().getString("Winner.Four.player")));
/*     */             }
/*  84 */             this.main.getConfig().set("Winner.Four.recup", Boolean.valueOf(false));
/*     */           }
/*     */           
/*     */         }
/*  88 */         else if (e.getCurrentItem().getItemMeta().getLore().toString().contains("la §65 èmes")) {
/*  89 */           if (this.main.getConfig().getBoolean("Winner.Five.recup") == true) {
/*  90 */             Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money give " + this.main.getConfig().getString("Winner.Five.player") + " " + Float.valueOf(this.main.getConfig().getString("Reward.Five.Money")));
/*  91 */             p.sendMessage(this.main.getConfig().getString("Messages.Prefix").replace("&", "§") + this.main.getConfig().getString("Reward.Five.Message"));
/*  92 */             if (this.main.getConfig().getBoolean("Reward.Five.Commands") == true) {
/*  93 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Five.Command1").replace("%player%", this.main.getConfig().getString("Winner.Five.player")));
/*  94 */               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.main.getConfig().getString("Reward.Five.Command2").replace("%player%", this.main.getConfig().getString("Winner.Five.player")));
/*     */             }
/*  96 */             this.main.getConfig().set("Winner.Five.recup", Boolean.valueOf(false));
/*     */           } else {
/*  98 */             return;
/*     */           }
/*     */         }
/* 101 */         this.main.saveConfig();
/* 102 */         p.closeInventory();
/* 103 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Event\GoldBlockClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */