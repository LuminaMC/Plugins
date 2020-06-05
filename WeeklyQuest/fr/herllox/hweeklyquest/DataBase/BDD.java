/*     */ package fr.herllox.hweeklyquest.DataBase;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class BDD
/*     */ {
/*  12 */   public static Connection connection = null;
/*     */   
/*     */   public static Connection connect()
/*     */   {
/*  16 */     String url = "jdbc:mysql://localhost:3306/weeklyquest?autoReconnect=true";
/*  17 */     String login = "weeklyquest";
/*  18 */     String pass = "HfCQYtjBd657s68";
/*     */     try {
/*  20 */       Class.forName("com.mysql.jdbc.Driver");
/*  21 */       connection = java.sql.DriverManager.getConnection(url, login, pass);
/*     */     } catch (SQLException e) {
/*  23 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/*  25 */       e.printStackTrace();
/*     */     }
/*  27 */     return connection;
/*     */   }
/*     */   
/*     */   public static void addPlayer(Player p)
/*     */     throws SQLException
/*     */   {
/*  33 */     Connection cn = connect();
/*  34 */     String query = "INSERT INTO WeeklyQuest (Pseudo, Nbr, User) VALUES (?, ?, ?)";
/*  35 */     PreparedStatement ps = cn.prepareStatement(query);
/*  36 */     ps.setString(1, p.getName());
/*  37 */     ps.setInt(2, 0);
/*  38 */     ps.setBoolean(3, true);
/*  39 */     ps.executeUpdate();
/*  40 */     ps.close();
/*  41 */     cn.close();
/*     */   }
/*     */   
/*     */   public static boolean playerExist(Player p)
/*     */     throws SQLException
/*     */   {
/*  47 */     Connection cn = connect();
/*  48 */     String query = "SELECT * FROM WeeklyQuest WHERE Pseudo = ?";
/*  49 */     PreparedStatement ps = cn.prepareStatement(query);
/*  50 */     ps.setString(1, p.getName());
/*  51 */     ResultSet rs = ps.executeQuery();
/*     */     
/*  53 */     if (rs.next()) {
/*  54 */       String bddUUID = rs.getString("Pseudo");
/*  55 */       String currentUUID = p.getName();
/*  56 */       if (bddUUID.equals(currentUUID)) { return true;
/*     */       }
/*     */     }
/*  59 */     ps.close();
/*  60 */     cn.close();
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public static void addItem(Player p, int nbr) throws SQLException
/*     */   {
/*  66 */     Connection cn = connect();
/*  67 */     String query = "SELECT * FROM WeeklyQuest WHERE Pseudo = ?";
/*  68 */     PreparedStatement ps = cn.prepareStatement(query);
/*  69 */     ps.setString(1, p.getName());
/*  70 */     ResultSet rs = ps.executeQuery();
/*  71 */     if (rs.next()) {
/*  72 */       String bddPseudo = rs.getString("Pseudo");
/*  73 */       String currentPseudo = p.getName();
/*  74 */       if (bddPseudo.equals(currentPseudo)) {
/*  75 */         ps = cn.prepareStatement("UPDATE WeeklyQuest SET Nbr = ? WHERE Pseudo = ?");
/*  76 */         ps.setInt(1, nbr);
/*  77 */         ps.setString(2, p.getName());
/*  78 */         ps.executeUpdate();
/*     */       }
/*     */     }
/*     */     
/*  82 */     ps.close();
/*  83 */     cn.close();
/*     */   }
/*     */   
/*     */   public static Integer checkNbr(Player p)
/*     */     throws SQLException
/*     */   {
/*  89 */     Connection cn = connect();
/*  90 */     String query = "SELECT * FROM WeeklyQuest WHERE Pseudo = ?";
/*  91 */     PreparedStatement ps = cn.prepareStatement(query);
/*  92 */     ps.setString(1, p.getName());
/*  93 */     ResultSet rs = ps.executeQuery();
/*     */     
/*  95 */     if (rs.next()) {
/*  96 */       String bddPseudo = rs.getString("Pseudo");
/*  97 */       String currentPseudo = p.getName();
/*  98 */       if (bddPseudo.equals(currentPseudo))
/*     */       {
/* 100 */         Integer i = Integer.valueOf(rs.getInt("Nbr"));
/* 101 */         if (i == null) i = Integer.valueOf(0);
/* 102 */         return i;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 109 */     ps.close();
/* 110 */     cn.close();
/* 111 */     return Integer.valueOf(0);
/*     */   }

    /*     */   public static Integer checkNbrStr(String st)
    /*     */     throws SQLException
    /*     */   {
        /*  89 */     Connection cn = connect();
        /*  90 */     String query = "SELECT * FROM WeeklyQuest WHERE Pseudo = ?";
        /*  91 */     PreparedStatement ps = cn.prepareStatement(query);
        /*  92 */     ps.setString(1, st);
        /*  93 */     ResultSet rs = ps.executeQuery();
        /*     */
        /*  95 */     if (rs.next()) {
            /*  96 */       String bddPseudo = rs.getString("Pseudo");
            /*  97 */       String currentPseudo = st;
            /*  98 */       if (bddPseudo.equals(currentPseudo))
                /*     */       {
                /* 100 */         Integer i = Integer.valueOf(rs.getInt("Nbr"));
                /* 101 */         if (i == null) i = Integer.valueOf(0);
                /* 102 */         return i;
                /*     */       }
            /*     */     }
        /*     */
        /*     */
        /*     */
        /*     */
        /* 109 */     ps.close();
        /* 110 */     cn.close();
        /* 111 */     return Integer.valueOf(0);
        /*     */   }
/*     */   
/*     */   public static java.util.List getTop()
/*     */     throws SQLException
/*     */   {
/* 117 */     Connection cn = connect();
/* 118 */     String query = "SELECT Pseudo,Nbr FROM WeeklyQuest ORDER BY Nbr DESC LIMIT 10";
/* 119 */     Statement st = cn.createStatement();
/* 120 */     ResultSet rs = st.executeQuery(query);
/*     */     
/* 122 */     java.util.List list = new java.util.ArrayList();
/*     */     
/* 124 */     int i = 1;
/* 125 */     while (rs.next()) {
/* 126 */       list.add("    §aTop §6" + i + ":§e " + rs.getString("Pseudo") + "  §e(§a" + rs.getInt("Nbr") + "§e)");
/* 127 */       i++;
/*     */     }
/* 129 */     st.close();
/* 130 */     cn.close();
/* 131 */     return list;
/*     */   }
/*     */   
/*     */   public static java.util.List getTop10() throws SQLException
/*     */   {
/* 136 */     Connection cn = connect();
/* 137 */     String query = "SELECT Pseudo,Nbr FROM WeeklyQuest ORDER BY Nbr DESC LIMIT 10";
/* 138 */     Statement st = cn.createStatement();
/* 139 */     ResultSet rs = st.executeQuery(query);
/*     */     
/* 141 */     java.util.List list = new java.util.ArrayList();
/*     */     
/* 143 */     int i = 0;
/* 144 */     while (rs.next()) {
/* 145 */       list.add(rs.getString("Pseudo"));
/* 146 */       i++;
/*     */     }
/* 148 */     if (i < 10) {
/* 149 */       while (i != 10) {
/* 150 */         list.add("Aucun...");
/* 151 */         i++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 156 */     st.close();
/* 157 */     cn.close();
/* 158 */     return list;
/*     */   }
/*     */   
/*     */   public static java.util.List getTop5() throws SQLException
/*     */   {
/* 163 */     Connection cn = connect();
/* 164 */     String query = "SELECT Pseudo,Nbr FROM WeeklyQuest ORDER BY Nbr DESC LIMIT 5";
/* 165 */     Statement st = cn.createStatement();
/* 166 */     ResultSet rs = st.executeQuery(query);
/*     */     
/* 168 */     java.util.List list = new java.util.ArrayList();
/*     */     
/* 170 */     int i = 1;
/* 171 */     while (rs.next()) {
/* 172 */       list.add(rs.getString("Pseudo"));
/* 173 */       i++;
/*     */     }
/* 175 */     st.close();
/* 176 */     cn.close();
/* 177 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void deleteBDD()
/*     */     throws SQLException
/*     */   {
/* 184 */     Connection cn = connect();
/* 185 */     String query = "DELETE FROM WeeklyQuest WHERE User = 1";
/* 186 */     Statement st = cn.createStatement();
/* 187 */     st.executeUpdate(query);
/* 188 */     st.close();
/* 189 */     cn.close();
/*     */   }
/*     */ }


/* Location:              F:\@SAUVEGARDE TOUR\Users\Etienne\Downloads\H-WeeklyQuest.jar!\fr\herllox\hweeklyquest\DataBase\BDD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */