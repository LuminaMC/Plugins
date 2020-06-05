
package fr.herllox.hweeklyquest;


import java.io.File;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class UtilMoney {
    static File folder = new File("plugins/CoreLuminaV5/Shop");
    static File shopfile = new File(folder, "ShopConfig.yml");
    static FileConfiguration shopconfig = YamlConfiguration.loadConfiguration(shopfile);



    public static Float getPriceSellUnity(ItemStack itemStack){
        Material m = itemStack.getType();
        short data = itemStack.getDurability();
        float price = (float) 0;
        for (String st : shopconfig.getConfigurationSection("agriculture.").getKeys(true)) {

            String it = shopconfig.getString("agriculture." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        for (String st : shopconfig.getConfigurationSection("hostile.").getKeys(true)) {

            String it = shopconfig.getString("hostile." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        for (String st : shopconfig.getConfigurationSection("passif.").getKeys(true)) {

            String it = shopconfig.getString("passif." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        for (String st : shopconfig.getConfigurationSection("blocs.").getKeys(true)) {

            String it = shopconfig.getString("blocs." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        for (String st : shopconfig.getConfigurationSection("minerais.").getKeys(true)) {

            String it = shopconfig.getString("minerais." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        for (String st : shopconfig.getConfigurationSection("divers.").getKeys(true)) {

            String it = shopconfig.getString("divers." + st);
            String[] itsplit = it.split("!");
            if (Material.getMaterial(itsplit[0].toUpperCase()) == m && data == Short.parseShort(itsplit[1])) {
                price = price + Float.parseFloat(itsplit[3]);
            }
        }
        return price;
    }
}

