/*     */ package fr.herllox.hweeklyquest.Event;
/*     */ 
/*     */ import fr.herllox.hweeklyquest.DataBase.BDD;
/*     */ import fr.herllox.hweeklyquest.Main;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.time.LocalTime;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ 
/*     */ public class Runnable extends org.bukkit.scheduler.BukkitRunnable
/*     */ {
/*     */   private final Main main;
/*     */   
/*     */   public Runnable(Main main)
/*     */   {
/*  20 */     this.main = main;
/*     */   }
/*     */   
/*     */ 
/*     */   public void run()
/*     */   {
/*  26 */     int hour = LocalTime.now().getHour();
/*  27 */     int min = LocalTime.now().getMinute();
/*  28 */     Date now = new Date();
/*  29 */     SimpleDateFormat format = new SimpleDateFormat("EEEE");
/*  30 */     String day = format.format(now);
/*     */     
/*  32 */     if ((day.equalsIgnoreCase("Sunday")) && (hour == 17) && (min == 0)) {
/*     */       try
/*     */       {
/*  35 */         java.util.List op = BDD.getTop5();
/*  36 */         String[] s = op.toString().replace("[", "").replace(" ", "").replace("]", "").split(",");
/*  37 */         Bukkit.broadcastMessage("§m§l                                                               ");
/*  38 */         Bukkit.broadcastMessage("§6§lLes vainqueurs des quêtes hebdomadaires sont :");
/*  39 */         Bukkit.broadcastMessage("§7#1: §f" + s[0]);
/*  40 */         Bukkit.broadcastMessage("§7#2: §f" + s[1]);
/*  41 */         Bukkit.broadcastMessage("§7#3: §f" + s[2]);
/*  42 */         Bukkit.broadcastMessage("§7#4: §f" + s[3]);
/*  43 */         Bukkit.broadcastMessage("§7#5: §f" + s[4]);
/*  44 */         Bukkit.broadcastMessage("§m§l                                                               ");
/*     */         
/*  46 */         this.main.getConfig().set("Winner.One.player", s[0]);
/*  47 */         this.main.getConfig().set("Winner.One.recup", Boolean.valueOf(true));
/*  48 */         this.main.getConfig().set("Winner.Two.player", "" + s[1]);
/*  49 */         this.main.getConfig().set("Winner.Two.recup", Boolean.valueOf(true));
/*  50 */         this.main.getConfig().set("Winner.Three.player", "" + s[2]);
/*  51 */         this.main.getConfig().set("Winner.Three.recup", Boolean.valueOf(true));
/*  52 */         this.main.getConfig().set("Winner.Four.player", "" + s[3]);
/*  53 */         this.main.getConfig().set("Winner.Four.recup", Boolean.valueOf(true));
/*  54 */         this.main.getConfig().set("Winner.Five.player", "" + s[4]);
/*  55 */         this.main.getConfig().set("Winner.Five.recup", Boolean.valueOf(true));
/*  56 */         this.main.saveConfig();
/*     */         
/*     */ 
/*  59 */         BDD.deleteBDD();
/*     */         
/*     */ 
/*  62 */         Material material = aleaItem();
/*  63 */         String item = material.name();
/*     */         
/*  65 */         this.main.getConfig().set("WeekItems", item);
/*  66 */         this.main.saveConfig();
/*     */       }
/*     */       catch (SQLException e)
/*     */       {
/*  70 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Material aleaItem()
/*     */   {
/*  87 */     Random r = new Random();
/*  88 */     int choix = 2;
/*  89 */     int alea = 1 + r.nextInt(choix - 1);
/*  90 */     switch (alea) {
/*     */     case 1: 
/*  92 */       return Material.CACTUS;
/*     */     case 2: 
/*  94 */       return Material.SUGAR_CANE;
/*     */     case 3: 
/*  96 */       return Material.COBBLESTONE;
/*     */     case 4: 
/*  98 */       return Material.IRON_BLOCK;
/*     */     case 5: 
/* 100 */       return Material.LOG;
/*     */     case 6: 
/* 102 */       return Material.BRICK;
/*     */     case 7: 
/* 104 */       return Material.STONE;
/*     */     case 8: 
/* 106 */       return Material.LEAVES;
/*     */     case 9: 
/* 108 */       return Material.LOG;
/*     */     case 10: 
/* 110 */       return Material.EGG;
/*     */     case 11: 
/* 112 */       return Material.WOOL;
/*     */     case 12: 
/* 114 */       return Material.FISHING_ROD;
/*     */     case 13: 
/* 116 */       return Material.COOKED_FISH;
/*     */     case 14: 
/* 118 */       return Material.COOKED_BEEF;
/*     */     case 15: 
/* 120 */       return Material.COOKED_CHICKEN;
/*     */     case 16: 
/* 122 */       return Material.CARROT;
/*     */     case 17: 
/* 124 */       return Material.POTATO;
/*     */     }
/*     */     
/*     */     
/* 128 */     return Material.CACTUS;
/*     */   }
/*     */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Event\Runnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */