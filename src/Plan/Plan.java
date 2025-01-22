package Plan;
import GameTool.TimeDelay;
import java.util.*;
import static Plan.config.*;
import static java.lang.Math.abs;

public class Plan {
    private Map map;
    private node startNode;
    private node endNode;
    public int len;
    //设置比较器
    private final Comparator<node> OpenComp =  (n1, n2) -> Integer.compare(n1.f, n2.f);  // 降序比较
    PriorityQueue<node> openQueue = new PriorityQueue<>(OpenComp);
    HashSet<node> openSet = new HashSet<>();
    HashSet<node> closeSet = new HashSet<>();
    Vector<node> result = new Vector<>();
    public Plan(int StartX,int StartY,int EndX,int EndY,Map mp){
        Set(StartX,StartY,EndX,EndY,mp);
    }
    // 设置起点和终点
    private void Set(int StartX,int StartY,int EndX,int EndY,Map mp){
        map = mp;
        if (mp.outof(StartX,StartY)||mp.outof(EndX,EndY)) {
            System.out.println("坐标越界");
            return;
        }
        mp.grid.get(StartY * mp.col + StartX).type = start;
        startNode = mp.grid.get(StartY * mp.col + StartX);
        mp.grid.get(EndY * mp.col + EndX).type = end;
        endNode = mp.grid.get(EndY * mp.col + EndX);
    }
    // 求解两个节点坐标距离
    public int distance(node n1,node n2){
        return abs(n1.getX()-n2.getX())*RECT_EDGE+ abs(n1.getY()-n2.getY())*RECT_EDGE;
    }
    //计算节点的f、g、h值
    private void cost(node now,node next,int step){
        next.h = distance(next,endNode);
        next.g = now.g + step;
        next.f =  next.h + next.g;
    }
    public void plan() {
        int step;
        node temp = startNode;
        class point {
            int x;
            int y;
            point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        java.util.Map<Integer, point> dirMove = new HashMap<>();
        dirMove.put(0, new point(0, 1));
        dirMove.put(1, new point(0, -1));
        dirMove.put(2, new point(1, 0));
        dirMove.put(3, new point(-1, 0));
        dirMove.put(4, new point(1, 1));
        dirMove.put(5, new point(1, -1));
        dirMove.put(6, new point(-1, 1));
        dirMove.put(7, new point(-1, -1));
        openSet.add(temp);
        openQueue.add(temp);
        while (!openSet.isEmpty()) {
                node now = openQueue.peek();
                openQueue.poll();
                openSet.remove(now);
                closeSet.add(now);
                if (now.type != obstacle && now.type != end && now.type!=start) {
                    TimeDelay.delay(TIME_DRAW);
                    now.type = think;
                }
                // 当到达终点时
                if (now == endNode) {
                    len = now.g;
                    while (now.pre != null) {
                        result.add(now);
                        now = now.pre;
                    }
                    Collections.reverse(result);
                    int cont1 = 0;
                    do {
                        result.get(cont1).type = road;
                        startNode.type = start;
                        endNode.type = end;
                        System.out.println("ok");
                        cont1++;
                    } while (cont1 != result.size());
                    System.out.println("发现最优路径");
                    return;
                }
                // 跳到下一个节点
                for (int i = 0; i < dirMove.size(); i++) {
                    // 确定移动步长
                    if (abs(dirMove.get(i).y) == abs(dirMove.get(i).x)) {
                        step = RECT_DIAGONAL;
                    } else {
                        step = RECT_EDGE;
                    }
                    if (map.outof(now.getX() + dirMove.get(i).x, now.getY() + dirMove.get(i).y)) {
                        continue;
                    }
                    node next = map.grid.get((now.getY() + dirMove.get(i).y) * map.col + now.getX() + dirMove.get(i).x);

                    //如果是障碍物或者在 close 表中，进行下一次循环
                    if (next.type == obstacle || closeSet.contains(next)) {
                        continue;
                    } else if (openSet.contains(next)) {
                        TimeDelay.delay(TIME_DRAW);
                        next.type = think1;
                        if (now.g + step < next.g) {
                            cost(now, next, step);
                            next.pre = now;
                        }
                        continue;
                    } else {
                        TimeDelay.delay(TIME_DRAW);
                        next.type = think1;
                        cost(now, next, step);
                        next.pre = now;
                        openSet.add(next);
                        openQueue.add(next);
                    }

                }
        }
        System.out.println("没有最优路径");
    }
}
