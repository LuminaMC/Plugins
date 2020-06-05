package net.bytobyx.lumievent.messages;

import net.bytobyx.lumievent.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MessRunnable extends BukkitRunnable {
    private final Main main;

    public MessRunnable(Main main) {
        this.main = main;
    }

    public void run() {
        Random r = new Random();
        int max = this.main.getConfig().getInt("Messages.Nbr_de_mots");
        int random = r.nextInt(max);

        String word = this.main.getConfig().getString("Messages.List");
        String[] words = word.split(",");
        String[] endword = words[random].split("/");


        Bukkit.broadcastMessage("§8§l§m                                                               ");
        Bukkit.broadcastMessage("§e§lEVENT§8§l » §7Le premier trouvant le mot comportant ces lettres gagnera 1000 Lumines :§a " + shuffleString(endword[0]).replace(" ",""));
        Bukkit.broadcastMessage("§8§l§m                                                               ");



        Main.goodmess = endword[1];
        Main.Messageon = Boolean.valueOf(true);
    }

    public static String shuffleString(String string) {
        List<String> letters = Arrays.asList(string.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;
    }

}
