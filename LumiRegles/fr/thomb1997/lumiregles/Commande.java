package fr.thomb1997.lumiregles;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class Commande implements CommandExecutor
{
    Main main;
    public OpenMenu openmenu;
    
    public Commande(final Main main) {
        this.openmenu = new OpenMenu(this.main);
        this.main = main;
        this.openmenu = new OpenMenu(main);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("reglement")) {
                this.openmenu.Menu(p);
            }
        }
        return false;
    }
}
