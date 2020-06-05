/*     */ package fr.herllox.hweeklyquest.Event;
/*     */
/*     */ import fr.herllox.hweeklyquest.DataBase.BDD;
/*     */ import fr.herllox.hweeklyquest.Main;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */
/*     */ public class ClickBeaconEvent implements Listener
/*     */ {
/*     */   private final Main main;
/*     */
/*     */   public ClickBeaconEvent(Main main)
/*     */   {
/*  29 */     this.main = main;
/*     */   }
/*     */
/*     */   @EventHandler
/*     */   @Deprecated
/*     */   public void onTop(InventoryClickEvent e)
/*     */     throws SQLException
/*     */   {
/*  37 */     final Player p = (Player)e.getWhoClicked();
/*     */
/*  39 */     if (e.getClickedInventory() == null) {
/*  40 */       e.setCancelled(true);
/*     */     }
            if(e.getView().getTitle().equalsIgnoreCase("Chargement")){
                e.setCancelled(true);
                }
/*  42 */     if (e.getView().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Menu.Name").replace("&", "§"))) {
/*  43 */       e.setCancelled(true);
/*  44 */       if ((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().getType() == null) || (!e.getCurrentItem().hasItemMeta())) {
/*  45 */         p.closeInventory();
/*  46 */         return;
/*     */       }
/*     */
/*  49 */       switch (e.getCurrentItem().getType())
/*     */       {
/*     */       case BEACON:
/*  52 */         final Inventory load = Bukkit.createInventory(null, 27, "Chargement");
/*     */
/*  54 */         ItemStack it = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
/*  55 */         ItemMeta itm = it.getItemMeta();
/*  56 */         itm.setDisplayName("§fChargement ...");
/*  57 */         itm.setLore(Collections.singletonList(" "));
/*  58 */         it.setItemMeta(itm);
/*     */
/*     */
/*  61 */         final ItemStack it2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 5);
/*  62 */         ItemMeta itm2 = it2.getItemMeta();
/*  63 */         itm2.setDisplayName("§fChargement ...");
/*  64 */         itm2.setLore(Collections.singletonList(" "));
/*  65 */         it2.setItemMeta(itm2);
/*     */
/*     */
/*  68 */         load.setItem(11, it);
/*  69 */         load.setItem(13, it);
/*  70 */         load.setItem(15, it);
/*     */
/*  72 */         p.openInventory(load);
/*     */
/*     */
/*  75 */         final Inventory inv = Bukkit.createInventory(null, 54, this.main.getConfig().getString("Menu.Beacon").replace("&", "§"));
/*     */
/*     */
/*     */
/*  79 */         List op = BDD.getTop10();
/*  80 */         String[] s = op.toString().replace("[", "").replace(" ", "").replace("]", "").split(",");
/*  81 */         List<Integer> slot = Arrays.asList(new Integer[] { Integer.valueOf(13), Integer.valueOf(21), Integer.valueOf(23), Integer.valueOf(28), Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(31), Integer.valueOf(32), Integer.valueOf(33), Integer.valueOf(34) });
/*     */         int place = 0;
/*     */
/*  84 */         for (int i = 0; i < s.length; i++)
/*     */         {
                    if (i == 0) {
                        place = 13;
                    }
                    if (i == 1) {
                        place = 21;
                    }
                    if (i == 2) {
                        place = 23;
                    }
                    if(i >= 3){
                        place = 28 + (i-3);
                    }
/*  86 */           if (!s[i].isEmpty())
/*     */           {

/*  88 */             ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
/*  89 */             SkullMeta meta = (SkullMeta)head.getItemMeta();
/*  90 */             meta.setOwningPlayer(Bukkit.getOfflinePlayer(s[i]));
/*  91 */             Player pp = Bukkit.getPlayer(s[i]);
/*  92 */             meta.setDisplayName("§6§l" + s[i] + " §f(#"+(i+1)+"§f)");

                        meta.setLore(Arrays.asList(" ", " §7• Items: §f"+BDD.checkNbrStr(s[i])," "));

/*  93 */             head.setItemMeta(meta);
/*     */
/*  95 */             inv.setItem(place, head);
/*     */           }
/*     */         }
/*     */
/*     */
/* 100 */         ItemStack ret = new ItemStack(Material.PAPER);
/* 101 */         ItemMeta retx = ret.getItemMeta();
/* 102 */         retx.setDisplayName("§8§l» §cRetour §8§l«");
/* 103 */         ret.setItemMeta(retx);
/* 104 */         inv.setItem(45, ret);
/*     */
/* 106 */         new BukkitRunnable() {
/*     */           public void run() {
/* 108 */             load.setItem(11, it2);
/* 109 */             p.updateInventory();
/* 110 */             new BukkitRunnable() {
/*     */               public void run() {
/* 112 */                 load.setItem(13, it2);
/* 113 */                 p.updateInventory();
/* 114 */                 new BukkitRunnable() {
/*     */                   public void run() {
/* 116 */                     load.setItem(15, it2);
/* 117 */                     p.updateInventory();
/* 118 */                     new BukkitRunnable() {
/*     */                       public void run() {
/* 120 */                         p.openInventory(inv);
/*     */                       }
/* 122 */                     }.runTaskLater(ClickBeaconEvent.this.main, 5L);
/*     */                   }
/* 124 */                 }.runTaskLater(ClickBeaconEvent.this.main, 10L);
/*     */               }
/* 126 */             }.runTaskLater(ClickBeaconEvent.this.main, 10L); } }
/*     */
/* 128 */           .runTaskLater(this.main, 10L);
/*     */
/*     */
/*     */
/*     */
/* 133 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Event\ClickBeaconEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */