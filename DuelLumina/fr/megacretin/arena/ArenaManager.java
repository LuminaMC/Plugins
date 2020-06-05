package fr.megacretin.arena;

import java.util.*;
import org.bukkit.entity.*;

public class ArenaManager
{
    static ArrayList<Player> arena;
    
    static {
        ArenaManager.arena = new ArrayList<Player>();
    }
    
    public static void addArenaPlayer(final Player p) {
        ArenaManager.arena.add(p);
        System.out.println(ArenaManager.arena);
    }
    
    public static boolean checkPlayerInList() {
        return !ArenaManager.arena.isEmpty();
    }
    
    public static ArrayList<Player> getList() {
        return ArenaManager.arena;
    }
    
    public static boolean checkContainPlayer(final Player p) {
        return ArenaManager.arena.contains(p);
    }
    
    public static void clearList() {
        ArenaManager.arena.clear();
    }
    
    public static void removePlayer(final Player p) {
        ArenaManager.arena.remove(p);
    }
}
