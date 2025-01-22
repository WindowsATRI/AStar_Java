package Plan;
import java.util.HashSet;
import java.util.Vector;
import static Plan.config.*;

public class Map {
    public int col;
    public int row;
    public Vector<node> grid = new Vector<>(); // 地图节点
    public HashSet<node> ObsSet = new HashSet<>();    // 障碍物节点
    public Map(int y,int x){
        MapInit(y,x);
    }
    private void MapInit(int y,int x){
        row = y;
        col = x;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                node temp = new node();
                temp.setX(j);
                temp.setY(i);
                grid.add(temp);
            }
        }
    }
    // 输出结果
    public void Print(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if(grid.get(i*col+j).type==pass){
                    System.out.print("-"+" ");
                }else{
                    System.out.print(grid.get(i*col+j).type+" ");
                }
            }
            System.out.println();
        }
    }



    // 判断越界
    public boolean outof(int x,int y){
        return x < 0 || x >= col || y < 0 || y >= row;
    }
    // 设置障碍物
    public void SetObs(int y,int x,int len,int dir){
        int endX = 0,endY = 0;
        if (outof(x,y)){
            System.out.println("坐标越界");
            return;
        }
        len = len - 1;
        switch (dir){
           case DIR_UP:
               endY =y - len;
               if (outof(endX,endY)){
                   System.out.println("坐标越界");
                   return;
               }
               for (int i = 0;i<=len;i++){
                   grid.get((y-i)*col+x).type = obstacle;
                   ObsSet.add(grid.get((y-i)*col+x));
               }
               break;
            case DIR_DOWN:
                endY = y + len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get((y+i)*col+x).type = obstacle;
                    ObsSet.add(grid.get((y+i)*col+x));
                }
                break;
            case DIR_LEFT:
                endX = x - len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get(y*col+x-i).type = obstacle;
                    ObsSet.add(grid.get(y*col+x-i));
                }
                break;
            case DIR_RIGHT:
                endX = x + len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get(y*col+x+i).type = obstacle;
                    ObsSet.add(grid.get(y*col+x+i));
                }
                break;
            case DIR_UP_RIGHT:
                endX = x + len;
                endY = y - len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get((y-i)*col+x+i).type = obstacle;
                    ObsSet.add(grid.get((y-i)*col+x+i));
                }
                break;
            case DIR_UP_LEFT:
                endX = x - len;
                endY = y - len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get((y-i)*col+x-i).type = obstacle;
                    ObsSet.add(grid.get((y-i)*col+x-i));
                }
                break;
            case DIR_DOWN_LEFT:
                endX = x - len;
                endY = y + len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get((y+i)*col+x-i).type = obstacle;
                    ObsSet.add(grid.get((y+i)*col+x-i));
                }
                break;
            case DIR_DOWN_RIGHT:
                endX = x + len;
                endY = y + len;
                if (outof(endX,endY)){
                    System.out.println("坐标越界");
                    return;
                }
                for (int i = 0;i<=len;i++){
                    grid.get((y+i)*col+x+i).type = obstacle;
                    ObsSet.add(grid.get((y+i)*col+x+i));
                }
                break;
        }
    }
}
