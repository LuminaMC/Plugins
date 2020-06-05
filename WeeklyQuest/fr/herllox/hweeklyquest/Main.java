/*    */ package fr.herllox.hweeklyquest;
/*    */ 
/*    */ import fr.herllox.hweeklyquest.Event.ClickBeaconEvent;
/*    */ import fr.herllox.hweeklyquest.Event.ConfirmClick;
/*    */ import fr.herllox.hweeklyquest.Event.GoldBlockClick;
/*    */ import fr.herllox.hweeklyquest.Event.OpenMenu;
/*    */ import fr.herllox.hweeklyquest.Event.Runnable;
/*    */ import fr.herllox.hweeklyquest.WeekFile.WeekFile;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ public class Main extends org.bukkit.plugin.java.JavaPlugin
/*    */ {
/*    */   public static FileConfiguration WeekConf;
/*    */   
/*    */   public void onEnable()
/*    */   {
/* 23 */     saveDefaultConfig();
/*    */     
/* 25 */     Runnable task = new Runnable(this);
/* 26 */     task.runTaskTimerAsynchronously(this, 0L, 1200L);
/*    */     
/* 28 */     getCommand("weeklyquest").setExecutor(new OpenMenu(this));
/*    */     
/* 30 */     Bukkit.getPluginManager().registerEvents(new fr.herllox.hweeklyquest.Event.ClickPlayerHead(this), this);
/* 31 */     Bukkit.getPluginManager().registerEvents(new ConfirmClick(this), this);
/* 32 */     Bukkit.getPluginManager().registerEvents(new GoldBlockClick(this), this);
/* 33 */     Bukkit.getPluginManager().registerEvents(new ClickBeaconEvent(this), this);
/* 34 */     Bukkit.getPluginManager().registerEvents(new fr.herllox.hweeklyquest.Event.ReturnClick(this), this);
/*    */     
/*    */ 
/* 37 */     File file = new File(getDataFolder(), "WeekConfig.yml");
/* 38 */     WeekConf = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
/* 39 */     if (!file.exists()) {
/*    */       try {
/* 41 */         file.createNewFile();
/* 42 */         FileWriter fileWriter = new FileWriter(file);
/* 43 */         BufferedWriter bw = new BufferedWriter(fileWriter);
/* 44 */         bw.write(WeekFile.WeekFileConfig);
/* 45 */         bw.close();
/* 46 */         fileWriter.close();
/*    */       } catch (IOException e) {
/* 48 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */