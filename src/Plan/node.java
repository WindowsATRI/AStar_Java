package Plan;

public class node {
    private int x;
    private int y;
    public int f=0;
    public int g=0;
    public int h=0;
    public int type = config.pass;
    public node pre = null;
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
