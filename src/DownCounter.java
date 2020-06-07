//指向awt包
import java.awt.*;


public class DownCounter implements Shape,Runnable {
    @Override
    /**
     * 重写drawMyself()方法
     * 画出计时数的方块和计时数
     */
    public void drawMyself(Graphics g) {
        g.drawRect(this.x,this.y,this.w,this.h);
        g.drawString(" "+ this.counter,this.x-2, this.y+10);
    }
    /**
     *定义延迟的方法delaySleep()
     * @param time 休眠规定的时间
     */
    public static void delaySleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    /**
     * 重写run()方法
     * 设置一个循环100次的for循环
     * 让计时数逐次减一，并休眠一秒，达到一秒一减的效果，并刷新指示数
     */
    public void run() {
        for(int number = 100;number>0;number--) {
            this.counter--;
            this.delaySleep(1000);
            Commons.drawingPanelForArray.repaint();
        }
    }
    //声明私有的实例变量横坐标x，赋值425
    private int x = 425;
    //声明私有的实例变量纵坐标y，赋值10
    private int y = 10;
    //声明私有的实例变量宽度w，赋值25
    private int w = 25;
    //声明私有的实例变量高度h，赋值25
    private int h = 25;
    //声明私有的实例变量时间counter，赋值100
    private int counter = 100;
}
