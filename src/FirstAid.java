//201802104065闫天意
import java.awt.*;
import java.util.Collection;

public class FirstAid implements Shape,CanTransferStrength ,OverlapSensitive,Runnable {
    //定义Fuel的构造器，并对其属性进行赋值指向
    public FirstAid(Tank tank, int x, int y, int w, int h, int num) {
        this.strength = (h*w)/10;
        this.tank= tank;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.num = num;
    }


    //定义setWhereiamin方法
    public void setWhereIamIn(Collection<Collection> whereIamIn) {
        this.SetfirstAid = whereIamIn;
    }
    /**
     * 定义返回信息的方法
     * @return 返回firstaid的血量
     */
    public String toString() {
        return "FirstAid{" +"firstaid"+this.num+
                "strength=" + this.strength +
                '}';
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
        Image image = ImgHelper.getImage("src/firstAid.png");
        g.drawImage(image, this.x, this.y, this.w, this.h, null);
        g.drawString("Fuel's strength is " + this.strength,this.x,this.y);
    }

    //声明私有的tank字段
    private Tank tank;
    //声明私有的实例变量横坐标x
    private int x;
    //声明私有的实例变量纵坐标y
    private int y;
    //声明私有的实例变量宽度w
    private int w;
    //声明私有的实例变量高度h
    private int h;
    //声明私有的实例变量序号num
    private int num;
    //声明私有的实例变量血量strength
    private int strength;
    //本对象所在的所有集合
    public Collection<Collection> SetfirstAid;
    @Override
    //返回宽度
    public int getW() {
        return this.w;
    }

    @Override
    //返回高度
    public int getH() {
        return this.h;
    }

    @Override
    //返回中心点x坐标
    public int getCx() {
        return this.x+this.w/2;
    }

    @Override
    //返回中心点y坐标
    public int getCy() {
        return this.y+this.h/2;
    }

    @Override
    //加血
    public void transfer() {
        this.tank.getStrength(this.strength);
        this.strength = 0;
        this.delete();
    }

    public void delete(){
        //在各集合中删除本对象
        ArrayUtil.removeObjectFromCollection(this.SetfirstAid, this);
    }
    @Override
    public void run() {
        for (int i = 0; i<50; i++){
            this.y+=3;
            System.out.println("firstAid x = " + this.x + " , y = " + this.y);
            try {
                Thread.sleep(200);
                //出现异常，捕获异常并打印异常信息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Commons.drawingPanelForArray.repaint();
        }
        this.delete();
        Commons.drawingPanelForArray.repaint();
    }
}
