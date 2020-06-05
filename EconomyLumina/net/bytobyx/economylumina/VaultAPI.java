/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.AbstractEconomy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  net.milkbowl.vault.economy.EconomyResponse$ResponseType
 *  org.bukkit.Bukkit
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package net.bytobyx.economylumina;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class VaultAPI
extends AbstractEconomy {
    public boolean hasAccount(String name) {
        if (Main.sql) {
            return SQLManager.exists(name);
        }
        File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
        if (Main.onlineMode) {
            if (data.getStringList("AConomyPlayerList").contains(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString())) {
                return true;
            }
            return false;
        }
        if (data.getStringList("AConomyPlayerList").contains(name.toLowerCase())) {
            return true;
        }
        return false;
    }

    public double getBalance(String name) {
        if (Main.sql) {
            return Main.getMoneyFormat(SQLManager.getBalance(name));
        }
        File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
        if (Main.onlineMode) {
            return Main.getMoneyFormat(data.getDouble(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString()));
        }
        return Main.getMoneyFormat(data.getDouble(name.toLowerCase()));
    }

    public boolean has(String name, double amount) {
        if (this.getBalance(name) < Main.getMoneyFormat(amount)) {
            return false;
        }
        return true;
    }

    public EconomyResponse withdrawPlayer(String name, double amount) {
        if (!this.hasAccount(name)) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "The player does not have an account!");
        }
        double balance = this.getBalance(name);
        if (this.getBalance(name) < Main.getMoneyFormat(amount)) {
            return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        balance -= Main.getMoneyFormat(amount);
        if (Main.sql) {
            SQLManager.setBalance(name, Main.getMoneyFormat(balance));
        } else {
            File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
            YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
            if (Main.onlineMode) {
                data.set(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString(), (Object)Main.getMoneyFormat(balance));
                try {
                    data.save(playerdata);
                }
                catch (IOException p) {
                    p.printStackTrace();
                }
            } else {
                data.set(name.toLowerCase(), (Object)Main.getMoneyFormat(balance));
                try {
                    data.save(playerdata);
                }
                catch (IOException p) {
                    p.printStackTrace();
                }
            }
        }
        return new EconomyResponse(Main.getMoneyFormat(amount), Main.getMoneyFormat(balance), EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(String name, double amount) {
        if (!this.hasAccount(name)) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "The player does not have an account!");
        }
        if (Main.getMoneyFormat(amount) < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Value is less than zero!");
        }
        if (Main.sql) {
            SQLManager.addBalance(name, Main.getMoneyFormat(amount));
        } else {
            File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
            YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
            if (Main.onlineMode) {
                data.set(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString(), (Object)(Main.getMoneyFormat(data.getDouble(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString())) + Main.getMoneyFormat(amount)));
                try {
                    data.save(playerdata);
                }
                catch (IOException p) {
                    p.printStackTrace();
                }
            } else {
                data.set(name.toLowerCase(), (Object)Main.getMoneyFormat(data.getDouble(name.toLowerCase()) + Main.getMoneyFormat(amount)));
                try {
                    data.save(playerdata);
                }
                catch (IOException p) {
                    p.printStackTrace();
                }
            }
        }
        return new EconomyResponse(Main.getMoneyFormat(amount), 0.0, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public boolean createPlayerAccount(String name) {
        if (!this.hasAccount(name)) {
            if (Main.sql) {
                SQLManager.createAccount(name);
                return true;
            }
            File playerdata = new File(Main.inst.getDataFolder() + File.separator + "playerdata.yml");
            YamlConfiguration data = YamlConfiguration.loadConfiguration((File)playerdata);
            if (Main.onlineMode) {
                Main.inst.accounts.add(Bukkit.getPlayer((String)name).getUniqueId().toString());
                data.set("AConomyPlayerList", Main.inst.accounts);
                data.set(Bukkit.getOfflinePlayer((String)name).getUniqueId().toString(), (Object)Main.getMoneyFormat(Main.startingBalance));
                try {
                    data.save(playerdata);
                }
                catch (IOException p) {
                    p.printStackTrace();
                }
                return true;
            }
            Main.inst.accounts.add(name.toLowerCase());
            data.set("AConomyPlayerList", Main.inst.accounts);
            data.set(name.toLowerCase(), (Object)Main.getMoneyFormat(Main.startingBalance));
            try {
                data.save(playerdata);
            }
            catch (IOException p) {
                p.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public String format(double summ) {
        return String.valueOf(summ);
    }

    public boolean hasAccount(String name, String world) {
        return this.hasAccount(name);
    }

    public boolean has(String name, String world, double amount) {
        return this.has(name, amount);
    }

    public double getBalance(String name, String world) {
        return this.getBalance(name);
    }

    public EconomyResponse withdrawPlayer(String name, String world, double amount) {
        return this.withdrawPlayer(name, amount);
    }

    public EconomyResponse depositPlayer(String name, String world, double amount) {
        return this.depositPlayer(name, amount);
    }

    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    public EconomyResponse deleteBank(String s) {
        return null;
    }

    public EconomyResponse bankBalance(String s) {
        return null;
    }

    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    public boolean createPlayerAccount(String name, String world) {
        return this.createPlayerAccount(name);
    }

    public List<String> getBanks() {
        return null;
    }

    public boolean hasBankSupport() {
        return false;
    }

    public boolean isEnabled() {
        if (Main.inst != null) {
            return true;
        }
        return false;
    }

    public String getName() {
        return "AConomy";
    }

    public int fractionalDigits() {
        return -1;
    }

    public String currencyNamePlural() {
        return "";
    }

    public String currencyNameSingular() {
        return "";
    }
}

