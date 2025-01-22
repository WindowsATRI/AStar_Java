package Polt;

import Plan.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Plan.config.*;

public class Panel extends JPanel{
    int StartX;
    int StartY;
    private static Rect rect;
    int col;
    int row;
    public static int ColorMoveX;
    public static int ColorMoveY;
    public static Color ColorSet;
    private BufferedImage bufImg = new BufferedImage(1280,720,BufferedImage.TYPE_4BYTE_ABGR);
    Map mp;
    private static Graphics gs;
    public Panel(int StartX,int StartY,int row,int col,Rect rect,Map mp){
        this.StartX = StartX;
        this.StartY = StartY;
        this.row = row;
        this.col = col;
        Panel.rect = rect;
        this.mp = mp;
        initRect();
        gs = bufImg.getGraphics(); // 得到图片的画笔
    }
    public void initRect(){
        Panel.rect.RectX = StartX;
        Panel.rect.RectY = StartY;
        Panel.rect.FillRectX = Panel.rect.RectX + Panel.rect.RectWith;
        Panel.rect.FillRectY = Panel.rect.RectY + Panel.rect.RectWith;
        Panel.rect.FillRectLen = Panel.rect.RectLen - 2*Panel.rect.RectWith;
    }
    public void PlotMap(){
        int cont = col*row;
        int x;
        int y;
        for(int i = 0; i < cont; i++){
            x = i - (i/col)*col;
            y = i/col;
            switch (mp.grid.get(i).type){
                case obstacle:
                    drawNode(x,y,new Color(190, 104, 14));
                    break;
                case start:
                    drawNode(x,y,new Color(0, 154, 255));
                    break;
                case end:
                    drawNode(x,y,new Color(255, 0, 0));
                    break;
                case pass:
                    drawNode(x,y,new Color(255, 255, 255));
                    break;
                case road:
                    drawNode(x,y,new Color(115, 255, 0));
                    break;
                case think:
                    drawNode(x,y,new Color(102, 0, 255));
                    break;
                case think1:
                    drawNode(x,y,new Color(195, 0, 253));
                    break;
            }
        }
    }
    public static void drawNode(int x,int y,Color color){
        ColorMoveX = x;
        ColorMoveY = y;
        ColorSet = color;
        gs.setColor(Color.DARK_GRAY);
        gs.fillRect(rect.RectX+x*(rect.RectLen - rect.RectWith),
                    rect.RectY+y*(rect.RectLen - rect.RectWith),
                       rect.RectLen,rect.RectLen);
        gs.setColor(color);
        gs.fillRect(rect.FillRectX+x*(rect.RectLen - rect.RectWith),
                rect.FillRectY+y*(rect.RectLen - rect.RectWith),
                rect.FillRectLen,rect.FillRectLen);
    }

    public void paint(Graphics g) {
        super.paint(g);
        PlotMap();
        g.drawImage(bufImg,0,0,null);
    }
}



