package GameTool;

public class TimeDelay {
    public static void delay(int time){
        long now = System.currentTimeMillis();
        while (time > System.currentTimeMillis()-now){
            continue;
        }
    }
}
