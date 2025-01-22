package Plan;

public class config {
    //节点类型标识
    public final static int pass = 0;
    public final static int start = 1;
    public final static int end = 2;
    public final static int obstacle = 3;
    public final static int road = 4;
    public final static int think = 5;
    public final static int think1 = 6;
    //障碍物方向标识
    public final static int DIR_UP = -1;
    public final static int DIR_DOWN = -2;
    public final static int DIR_LEFT = -3;
    public final static int DIR_RIGHT = -4;
    public final static int DIR_UP_RIGHT = -5;
    public final static int DIR_UP_LEFT = -6;
    public final static int DIR_DOWN_RIGHT = -7;
    public final static int DIR_DOWN_LEFT = -8;

    // 栅格属性
    public final static int RECT_EDGE = 20;
    public final static int RECT_DIAGONAL = 28;

    //设置时间间隔
    public final static int TIME_RESULT = 30; //路径结果
    public final static int TIME_DRAW = 5;

    //

}
