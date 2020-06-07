//201802104065闫天意
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public class Tank extends Receiver implements Shape, OverlapSensitive, Controllable, CanAttack,Runnable {
    //定义Tank的构造器，并对其属性进行赋值指向
    public Tank(int strength, int fuel,int damage, int storage, int x, int y, int w, int h) {
        super(fuel, storage);
        this.damage = damage;
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * 定义返回信息的方法
     * @return 返回tank的油量和油箱总量
     */
    public String toString() {
        return "Tank{"+ "tank " +"strength=" + this.strength+
                ", fuel=" + super.fuel +
                ", storage=" + super.storage +
                '}';
    }

    public void getStrength(int add) {
        this.strength+=add;
    }

    @Override
    //左移
    public void moveLeft() {
        //判断油量和生命值是否为零
        if (this.strength !=0 && this.fuel!=0){
            this.fuel-=5;
            this.x-=10;
            //遍历可重叠数组，检查自己与元素的重叠情况
            Iterator iterator = this.toOverlaps.iterator();
            while (iterator.hasNext()){
                OverlapSensitive toOverlap = (OverlapSensitive) iterator.next();
//                if (toOverlap==null){continue;}
                //是否与某个元素重叠
                boolean overlapped = OverlapUtil.checkOverlap(this, toOverlap);
                if (overlapped) {
                    //遍历数组toTransferFuels，检查是否存在当前重叠的对象
                    if (ArrayUtil.containedInCollection(toOverlap, this.toTransfers)){
                        //如果重叠对象是数组toTransferFuels中的一个
                        //则向它发送transfer消息
                        CanTransferStrength toTransfer = (CanTransferStrength)toOverlap;
                        toTransfer.transfer();
                    }
                    if (ArrayUtil.containedInCollection(toOverlap, this.toAttackeds)){
                        this.attack((CanBeAttacked)toOverlap);
                    }
                    //打印重叠
                    System.out.println("重叠" + toOverlap.toString());
                    break;
                }else {
                    //打印未重叠
                    System.out.println("未重叠" + toOverlap.toString());
                }
            }
            System.out.println(toString());
        }else {
            System.out.println("燃料耗尽或生命值耗光!");
        }
    }

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
    //画出自己
    public void drawMyself(Graphics g) {
        Image image = ImgHelper.getImage("src/tank.png");
        g.drawImage(image, this.x, this.y, this.w, this.h, null);
        g.drawRect(x,y,w,h);
        g.drawString("Tank's fuel is " + super.fuel + "strength is " + this.strength +" damage is " +this.damage,this.x,this.y);
    }
    //声明私有的实例变量横坐标x
    private int x;
    //声明私有的实例变量纵坐标y
    private int y;
    //声明私有的实例变量宽度w
    private int w;
    //声明私有的实例变量高度h
    private int h;
    //声明私有的实例变量生命值strength
    private int strength;
    //声明私有的实例变量伤害damage
    private int damage;
    //可能重叠的对象数组
    public Set<OverlapSensitive> toOverlaps;
    public Set<CanTransferStrength> toTransfers;
    public Set<CanBeAttacked> toAttackeds;//可攻击对象数组


    @Override
    //攻击
    public void attack(CanBeAttacked canBeAttacked) {
        this.strength-=this.damage;
        canBeAttacked.attacked(this);
    }
    @Override
    //获得伤害值
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void run() {
        for (int i = 0; i<80; i++){
                if (this.fuel>0) {
                this.moveLeft();
                try {
                    Thread.sleep(200);
                    //出现异常，捕获异常并打印异常信息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Commons.drawingPanelForArray.repaint();
            }else {
                System.out.println("燃料耗尽或坦克已死亡");
                break;
            }
        }
    }
}
