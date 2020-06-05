package fr.megacretin.arena;

public class Arena
{
    public static boolean isStarted;
    
    static {
        Arena.isStarted = false;
    }
    
    public void setStarted() {
        Arena.isStarted = true;
    }
    
    public boolean isStarded() {
        return Arena.isStarted;
    }
}
