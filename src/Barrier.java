//201802104065闫天意
import java.awt.*;
import java.util.Collection;

public class Barrier implements Shape, OverlapSensitive, CanBeAttacked {
    //定义Barrier的构造器，并对其属性进行赋值指向
    public Barrier( int strength,Tank tank,int x, int y, int w, int h,int num) {
        this.strength = strength;
        this.tank = tank;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.num = num;
    }

    //定义setWhereiamin方法
    public void setWhereIamIn(Collection<Collection> whereIamIn) {
        this.SetContainingBarrier = whereIamIn;
    }
    /**
     * 定义返回信息的方法
     * @return 返回barrier的血量
     */
    public String toString() {
        return "Barrier{" +"barrier"+this.num+
                "strength=" + this.strength +
                '}';
    }




    @Override
    /**
     * 定义方法drawMyself，用以画出自己
     * 声明Image类型变量image，并通过调用方法，得到图片
     * 规定image的属性并画出
     * 在图片上方输出自己血量，x，y的值
     */
    public void  drawMyself(Graphics g){
        g.drawRect(x,y,w,h);
        Image image = ImgHelper.getImage("src/barrier.png");
        g.drawImage(image, this.x, this.y, this.w, this.h, null);
        g.drawString("Barrier's strength is " + this.strength,this.x,this.y);
    }

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
    //声明私有的tank字段
    private Tank tank;
    //本对象所在的所有集合
    public Collection<Collection>  SetContainingBarrier;
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
    //被攻击
    public void attacked(CanAttack canAttack) {
        this.strength-= canAttack.getDamage();
        if (this.strength<=0) {
            //在各集合中删除本对象
            ArrayUtil.removeObjectFromCollection(this.SetContainingBarrier, this);
        }
    }
}


