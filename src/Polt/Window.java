package Polt;

import Plan.Map;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Plan.config.TIME_RESULT;

public class Window extends JFrame  implements Runnable{

    public Window(Panel pt){
        init(pt);
        initEventListener();
        //启动用于刷新窗口的线程
        new Thread(this).start();
    }
    @Override
    public void run() {
        while(true){
            repaint();//在此调用repaint,回调update
            try {
                Thread.sleep(TIME_RESULT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void init(Panel pt){
        this.add(pt);
        setTitle("路径规划");
        setSize(1280,720);
        setLocationRelativeTo(null);//这种方法是设定一个窗口的相对于另外一个窗口的位置
        setResizable(false);	//窗口是否可以改变大小
        setVisible(true); //显示窗口
        repaint();
    }
    // 设置窗口关闭事件
    private void initEventListener(){
        addWindowListener(new WindowAdapter() {
            // 点击关闭按钮的时候，方法会被自动调用
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
