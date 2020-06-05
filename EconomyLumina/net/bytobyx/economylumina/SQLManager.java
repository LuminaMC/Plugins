/*
 * Decompiled with CFR 0_125.
 *
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package net.bytobyx.economylumina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

public class SQLManager {
    private static String hostname;
    private static String port;
    private static String database;
    private static String user;
    private static String password;
    private static Connection connection;
    private static ResultSet resultSet;
    public static Map<String, Double> accountsList;

    static {
        connection = null;
        resultSet = null;
        accountsList = new HashMap<String, Double>();
    }

    public static void setup(String host, String db, String p, String u, String pass) {
        hostname = host;
        port = p;
        database = db;
        user = u;
        password = pass;
    }

    public static boolean exists(String playerName) {
        boolean exists;
        playerName = Main.onlineMode ? Bukkit.getOfflinePlayer((String)playerName).getUniqueId().toString() : playerName.toLowerCase();
        exists = false;
        try {
            Throwable throwable = null;
            Object var3_5 = null;
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM `Economy` where `Name` = ?");
                try {
                    ps.setString(1, playerName);
                    Throwable throwable2 = null;
                    Object var6_10 = null;
                    try {
                        ResultSet rs = ps.executeQuery();
                        try {
                            if (rs.next()) {
                                exists = true;
                            }
                        }
                        finally {
                            if (rs != null) {
                                rs.close();
                            }
                        }
                    }
                    catch (Throwable throwable3) {
                        if (throwable2 == null) {
                            throwable2 = throwable3;
                        } else if (throwable2 != throwable3) {
                            throwable2.addSuppressed(throwable3);
                        }
                        throw throwable2;
                    }
                }
                finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            }
            catch (Throwable throwable4) {
                if (throwable == null) {
                    throwable = throwable4;
                } else if (throwable != throwable4) {
                    throwable.addSuppressed(throwable4);
                }
                throw throwable;
            }
        }
        catch (Throwable e1) {
            e1.printStackTrace();
        }
        return exists;
    }

    public static void addBalance(String playerName, double amount) {
        playerName = Main.onlineMode ? Bukkit.getOfflinePlayer((String)playerName).getUniqueId().toString() : playerName.toLowerCase();
        try {
            resultSet = SQLManager.executeQuery("SELECT `Balance` FROM `Economy` WHERE Name = '" + playerName + "' LIMIT 1;");
            double sta = 0.0;
            if (resultSet.next()) {
                sta = resultSet.getDouble(1);
                SQLManager.execute("INSERT INTO `Economy` (`name`, `Balance`) VALUES ('" + playerName + "', '" + 0 + "' ) ON DUPLICATE KEY UPDATE `Balance` = '" + Main.getMoneyFormat(sta + amount) + "';");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUsers() {
        try {
            Throwable throwable = null;
            Object var1_3 = null;
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM `Economy`");
                try {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        accountsList.put(rs.getString("Name"), rs.getDouble("Balance"));
                    }
                }
                finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            }
            catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable2;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setBalance(String playerName, double amount) {
        playerName = Main.onlineMode ? Bukkit.getOfflinePlayer((String)playerName).getUniqueId().toString() : playerName.toLowerCase();
        try {
            resultSet = SQLManager.executeQuery("SELECT `Balance` FROM `Economy` WHERE Name = '" + playerName + "' LIMIT 1;");
            if (resultSet.next()) {
                SQLManager.execute("INSERT INTO `Economy` (`name`, `Balance`) VALUES ('" + playerName + "', '" + 0 + "' ) ON DUPLICATE KEY UPDATE `Balance` = '" + Main.getMoneyFormat(amount) + "';");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getBalance(String playerName) {
        playerName = Main.onlineMode ? Bukkit.getOfflinePlayer((String)playerName).getUniqueId().toString() : playerName.toLowerCase();
        try {
            resultSet = SQLManager.executeQuery("SELECT `Balance` FROM `Economy` WHERE Name = '" + playerName + "' LIMIT 1;");
            if (resultSet.next()) {
                return Main.getMoneyFormat(resultSet.getDouble(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static void createAccount(String playerName) {
        playerName = Main.onlineMode ? Bukkit.getOfflinePlayer((String)playerName).getUniqueId().toString() : playerName.toLowerCase();
        try {
            resultSet = SQLManager.executeQuery("SELECT EXISTS(SELECT 1 FROM `Economy` WHERE Name = '" + playerName + "' LIMIT 1);");
            if (resultSet.next()) {
                SQLManager.execute("INSERT INTO `Economy` (`name`, `Balance`) VALUES ('" + playerName + "', '" + Main.getMoneyFormat(Main.startingBalance) + "');");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) {
        if (!SQLManager.hasConnected()) {
            SQLManager.connect();
        }
        try {
            return connection.createStatement().executeQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, user, password);
            SQLManager.execute("CREATE TABLE IF NOT EXISTS `Economy` (`Name` VARCHAR(50) PRIMARY KEY, `Balance` DOUBLE)");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasConnected() {
        try {
            return !connection.isClosed();
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static void execute(String query) {
        if (!SQLManager.hasConnected()) {
            SQLManager.connect();
        }
        try {
            connection.createStatement().executeUpdate(query);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

