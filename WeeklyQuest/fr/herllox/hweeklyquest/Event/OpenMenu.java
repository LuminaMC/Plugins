/*     */ package fr.herllox.hweeklyquest.Event;
/*     */ 
/*     */ import fr.herllox.hweeklyquest.Main;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ public class OpenMenu implements Listener, CommandExecutor
/*     */ {
/*     */   private final Main main;
/*     */   
/*     */   public OpenMenu(Main main)
/*     */   {
/*  25 */     this.main = main;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
/*     */   {
/*  31 */     Player p = (Player)sender;
/*     */     
/*  33 */     Inventory inv = org.bukkit.Bukkit.createInventory(null, 45, this.main.getConfig().getString("Menu.Name").replace("&", "§"));
/*     */     
/*  35 */     Calendar cal = Calendar.getInstance();
/*     */     
/*  37 */     ItemStack i1 = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
/*  38 */     SkullMeta sm = (SkullMeta)i1.getItemMeta();
/*  39 */     sm.setOwningPlayer(p);
/*  40 */     sm.setDisplayName("§b§lSTATS §F" + p.getName());
/*     */     try {
/*  42 */       sm.setLore(Arrays.asList(new String[] { " ", "  §f∙ Nombre d'items : §a" + fr.herllox.hweeklyquest.DataBase.BDD.checkNbr(p), " ", "§8Cliquez ici pour ajouter les items que tu", "§8possèdes dans ton inventaire" }));
/*     */     } catch (SQLException e) {
/*  44 */       e.printStackTrace();
/*     */     }
/*  46 */     i1.setItemMeta(sm);
/*  47 */     inv.setItem(20, i1);
/*     */     
/*  49 */     ItemStack item = new ItemStack(Material.getMaterial(this.main.getConfig().getString("WeekItems")));
/*  50 */     ItemMeta itemx = item.getItemMeta();
/*  51 */     itemx.setDisplayName("§6§lITEMS §f " + this.main.getConfig().getString("WeekItems"));
/*  52 */     itemx.setLore(Arrays.asList(new String[] { " ", "§7Voici l'item que vous devrez produire", "§7le maximum durant cette semaine", "§7De grandes récompenses sont", "§7a gagner, alors je compte", "§7sur vous un maximum pour", "§7exploser le classement !", " " }));
/*  53 */     item.setItemMeta(itemx);
/*  54 */     inv.setItem(22, item);
/*     */     
/*  56 */     ItemStack paper = new ItemStack(Material.PAPER);
/*  57 */     ItemMeta paperx = paper.getItemMeta();
/*  58 */     paperx.setDisplayName("§5§lAIDE §f§lQuêtes hebdomadaires");
/*  59 */     paperx.setLore(Arrays.asList(new String[] { " ", "§f§lQu'est ce que les quêtes hebdomadaires ?", "§7Les quêtes hebdomadaires ", "§7sont des quêtes durant une semaine.", "§7Il y a un item tirée toute les", "§7semaines aléatoirement parmis le shop", "§7et cet item devra être ", "§7farmé pendant toute la semaine.", "§7A la fin de la semaine, un classement", "§7est effectué, les 5 meilleurs sont récompensés", " ", "§f§lNous ne gagnons rien si nous ne", "§7sommes pas dans le classement ?", "§7Si, à chaque fois que vous", "§7déposez des items, vous", "§7obtenez l'équivalent du prix de l'item au shop.", "§7Cela revient donc au même que", "§7si vous vendiez au shop", "§7sauf que si vous farmez bien, vous gagnerez", "§7peut être une récompense supplémentaire", " ", "§f§lA quel moment l'item change ?", "§7L'item change tout les dimanches à 17h00.", "§7Pour les 5 premiers du classements,", "§7vous avez jusqu'à", "§7la semaine d'après pour récupérer", "§7votre récompense" }));
/*  60 */     paper.setItemMeta(paperx);
/*  61 */     inv.setItem(13, paper);
/*     */     
/*     */ 
/*  64 */     ItemStack i2 = new ItemStack(Material.BEACON);
/*  65 */     ItemMeta i2x = i2.getItemMeta();
/*  66 */     i2x.setDisplayName("§b§lCLASSEMENT");
/*  67 */     i2x.setLore(Arrays.asList(new String[] { "", "§7Cliquez ici pour afficher le classement", "§7des 10 premiers joueurs.", "§7Attention, ce sont seulement les 5", "§7premiers qui remportent une récompense", "" }));
/*  68 */     i2.setItemMeta(i2x);
/*  69 */     inv.setItem(24, i2);
/*     */     
/*  71 */     if (p.getName().equalsIgnoreCase(this.main.getConfig().getString("Winner.One.player"))) {
/*  72 */       if (this.main.getConfig().getBoolean("Winner.One.recup") == true) {
/*  73 */         ItemStack gift = new ItemStack(Material.GOLD_BLOCK);
/*  74 */         ItemMeta giftx = gift.getItemMeta();
/*  75 */         giftx.setDisplayName("§e§lRECOMPENSES ");
/*  76 */         giftx.setLore(Arrays.asList(new String[] { " ", "§fVous avez participer aux ", "quêtes hebdomadaires et vous", "§favez finis dans les ", "premiers, plus particulièrement", "§fà la §61 ère§f place.", "Félicitation !", " ", "§7Cliquez ici pour récupérer", "votre récompense" }));
/*  77 */         gift.setItemMeta(giftx);
/*  78 */         inv.setItem(31, gift);
/*     */       }
/*  80 */     } else if (p.getName().equalsIgnoreCase(this.main.getConfig().getString("Winner.Two.player"))) {
/*  81 */       if (this.main.getConfig().getBoolean("Winner.Two.recup") == true) {
/*  82 */         ItemStack gift = new ItemStack(Material.GOLD_BLOCK);
/*  83 */         ItemMeta giftx = gift.getItemMeta();
/*  84 */         giftx.setDisplayName("§e§lRECOMPENSES ");
/*  85 */         giftx.setLore(Arrays.asList(new String[] { " ", "§fVous avez participer aux quêtes hebdomadaires et vous", "§favez finis dans les premiers, plus particulièrement", "§fà la §62 èmes§f place. Félicitation !", " ", "§7Cliquez ici pour récupérer votre récompense" }));
/*  86 */         gift.setItemMeta(giftx);
/*  87 */         inv.setItem(31, gift);
/*     */       }
/*  89 */     } else if (p.getName().equalsIgnoreCase(this.main.getConfig().getString("Winner.Three.player"))) {
/*  90 */       if (this.main.getConfig().getBoolean("Winner.Three.recup") == true) {
/*  91 */         ItemStack gift = new ItemStack(Material.GOLD_BLOCK);
/*  92 */         ItemMeta giftx = gift.getItemMeta();
/*  93 */         giftx.setDisplayName("§e§lRECOMPENSES ");
/*  94 */         giftx.setLore(Arrays.asList(new String[] { " ", "§fVous avez participer aux quêtes hebdomadaires et vous", "§favez finis dans les premiers, plus particulièrement", "§fà la §63 èmes§f place. Félicitation !", " ", "§7Cliquez ici pour récupérer votre récompense" }));
/*  95 */         gift.setItemMeta(giftx);
/*  96 */         inv.setItem(31, gift);
/*     */       }
/*  98 */     } else if (p.getName().equalsIgnoreCase(this.main.getConfig().getString("Winner.Four.player"))) {
/*  99 */       if (this.main.getConfig().getBoolean("Winner.Four.recup") == true) {
/* 100 */         ItemStack gift = new ItemStack(Material.GOLD_BLOCK);
/* 101 */         ItemMeta giftx = gift.getItemMeta();
/* 102 */         giftx.setDisplayName("§e§lRECOMPENSES ");
/* 103 */         giftx.setLore(Arrays.asList(new String[] { " ", "§fVous avez participer aux quêtes hebdomadaires et vous", "§favez finis dans les premiers, plus particulièrement", "§fà la §64 èmes§f place. Félicitation !", " ", "§7Cliquez ici pour récupérer votre récompense" }));
/* 104 */         gift.setItemMeta(giftx);
/* 105 */         inv.setItem(31, gift);
/*     */       }
/* 107 */     } else if ((p.getName().equalsIgnoreCase(this.main.getConfig().getString("Winner.Five.player"))) && 
/* 108 */       (this.main.getConfig().getBoolean("Winner.Five.recup") == true)) {
/* 109 */       ItemStack gift = new ItemStack(Material.GOLD_BLOCK);
/* 110 */       ItemMeta giftx = gift.getItemMeta();
/* 111 */       giftx.setDisplayName("§e§lRECOMPENSES ");
/* 112 */       giftx.setLore(Arrays.asList(new String[] { " ", "§fVous avez participer aux quêtes hebdomadaires et vous", "§favez finis dans les premiers, plus particulièrement", "§fà la §65 èmes§f place. Félicitation !", " ", "§7Cliquez ici pour récupérer votre récompense" }));
/* 113 */       gift.setItemMeta(giftx);
/* 114 */       inv.setItem(31, gift);
/*     */     }
/*     */     
/*     */ 
/* 118 */     p.openInventory(inv);
/*     */     
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\Event\OpenMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */