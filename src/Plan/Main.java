package Plan;
import Polt.Panel;
import Polt.Window;
import Polt.Rect;
import static Plan.config.*;
public class Main {
    public static void main(String[] args) {
        Rect rt = new Rect(45,4); // 设置绘制方块的边长、方块框的边长
        Map mp = new Map(10,30);      // 设置地图
        Panel pl = new Panel(15,25,10,30,rt,mp);// 设置地图显示位置
        new Window(pl);
        //设置障碍物
        mp.SetObs(1,7,4,DIR_RIGHT);
        mp.SetObs(1,11,8,DIR_DOWN);
        mp.SetObs(8,7,4,DIR_RIGHT);
        Plan pn = new Plan(4,3,13,5,mp); //   创建 A*
        pn.plan();                                               //   启动 A*

    }
}
