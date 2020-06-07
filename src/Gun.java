import java.awt.*;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Gun implements Shape,Runnable {
    //定义Fuel的构造器，并对其属性进行赋值指向
    public Gun(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    @Override
    /**
     * 定义方法drawMyself，用以画出自己
     * 声明Image类型变量image，并通过调用方法，得到图片
     * 规定image的属性并画出
     * 在图片上方输出自己油量，x，y的值
     */
    public void  drawMyself(Graphics g){
        g.drawRect(this.x,this.y,this.w,this.h);
        Image image = ImgHelper.getImage("src/gun.png");
        g.drawImage(image, this.x, this.y, this.w, this.h, null);
        g.drawString("炮台" ,this.x,this.y);
    }

    //声明私有的实例变量横坐标x
    private int x;
    //声明私有的实例变量纵坐标y
    private int y;
    //声明私有的实例变量宽度w
    private int w;
    //声明私有的实例变量高度h
    private int h;
    //声明私有的集合字段whereIamIn，元素为Collection类型
    private Collection<Collection> whereIamIn;
    //声明私有的集合字段overlappablesIt，元素为Overlappables类型
    private Collection<OverlapSensitive> overlappablesIt;

    /**
     * 定义setWhereIamIn()方法
     * @param whereIamIn 可使字段whereIamIn关联指定对象
     */
    public void setWhereIamIn(Collection<Collection> whereIamIn) {
        this.whereIamIn = whereIamIn;
    }
    /**
     * 定义setOverlappablesIt()方法
     * @param overlappablesIt 可使字段overlappablesIt关联指定对象
     */
    public void setOverlappablesIt(Collection<OverlapSensitive> overlappablesIt) {
        this.overlappablesIt = overlappablesIt;
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
    //可以直接使用本对象中执行任务类对象
    public final static ExecutorService executor = Executors.newCachedThreadPool();
    @Override
    public void run() {
        delaySleep(2000);
        //创建导弹对象x坐标随机数,y=400表示初始位置在窗口下方
        Shell shell = new Shell(this.x,this.y, 200, 700,10,
                this.whereIamIn,Commons.toOverlaps,Commons.canBeAttackeds);
        //导弹对象添加到Commons.shapes字段指向的集合类对象
        Commons.shapes.add(shell);
        Commons.toOverlaps.add(shell);
        System.out.println(Commons.shapes);
        executor.execute(shell);
        System.out.println("Shell move");
    }
}
