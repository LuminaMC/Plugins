/*    */ package fr.herllox.hweeklyquest.Event;
/*    */ 
/*    */ import fr.herllox.hweeklyquest.Main;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ReturnClick implements org.bukkit.event.Listener
/*    */ {
/*    */   private final Main main;
/*    */   
/*    */   public ReturnClick(Main main)
/*    */   {
/* 15 */     this.main = main;
/*    */   }
/*    */   
/*    */   @org.bukkit.event.EventHandler
/*    */   public void onReturn(InventoryClickEvent e)
/*    */   {
/* 21 */     Player p = (Player)e.getWhoClicked();
/*    */     
/* 23 */     if (e.getClickedInventory() == null) {
/* 24 */       e.setCancelled(true);
/*    */     }
/* 26 */     if (e.getView().getTitle().equalsIgnoreCase(this.main.getConfig().getString("Menu.Beacon").replace("&", "§"))) {
/* 27 */       e.setCancelled(true);
/* 28 */       if ((e.getInventory() == null) || (e.getCurrentItem() == null) || (e.getCurrentItem().getType() == null) || (!e.getCurrentItem().hasItemMeta())) {
/* 29 */         p.closeInventory();
/* 30 */         return;
/*    */       }
/*    */       
/* 33 */       switch (e.getCurrentItem().getType()) {
/*    */       case PAPER: 
/* 35 */         org.bukkit.Bukkit.dispatchCommand(p, "wq");
/* 36 */         p.closeInventory();
/* 37 */         break;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Event\ReturnClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */