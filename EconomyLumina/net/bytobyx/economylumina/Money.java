package net.bytobyx.economylumina;

public class Money {

    public void addLumines(String player, float lumines) {
        Main.economy.depositPlayer(player, lumines);
    }

    public void removeLumines(String player, float lumines) {
        Main.economy.withdrawPlayer(player, lumines);
    }

    public double getLumines(String Player) { return Main.economy.getBalance(Player); }

}
