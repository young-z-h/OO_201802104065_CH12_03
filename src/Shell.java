import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class Shell implements Shape,Runnable,OverlapSensitive,CanAttack {
    //定义Fuel的构造器，并对其属性进行赋值指向
    public Shell( int x, int y, int w, int h,int damage,
                  Collection<Collection> SetShell,
                  Collection<OverlapSensitive> toOverlaps,
                  Set<CanBeAttacked> toAttackeds) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.damage = damage;
        this.SetShell = SetShell;
        this.toOverlaps = toOverlaps;
        this.toAttackeds = toAttackeds;
    }


    //左移
    public void moveLeft() {
        this.x-=5;
        //遍历可重叠数组，检查自己与元素的重叠情况
        Iterator iterator = this.toOverlaps.iterator();
            while (iterator.hasNext()){
                OverlapSensitive toOverlap = (OverlapSensitive) iterator.next();
                if (toOverlap==null){continue;}
                //是否与某个元素重叠
                boolean overlapped = OverlapUtil.checkOverlap(this, toOverlap);
                if (overlapped) {
                    if (ArrayUtil.containedInCollection(toOverlap, this.toAttackeds)){
                        this.attack((CanBeAttacked)toOverlap);
                        this.delete();
                    }
//                    if (ArrayUtil.containedInCollection(toOverlap, this.toTransfers)){
//                        CanTransferStrength toTransfer = (CanTransferStrength)toOverlap;
//                        toTransfer.transfer();
//                        this.delete();
//                    }
                    //打印重叠
                    System.out.println("重叠" + toOverlap.toString());
                    break;
                }else {
                    //打印未重叠
                    System.out.println("未重叠" + toOverlap.toString()); } }System.out.println(toString());
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
        Image image = ImgHelper.getImage("src/shell.png");
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
    //声明私有的实例变量伤害damage
    private int damage;
    //可能重叠的对象数组
    public Collection<OverlapSensitive> toOverlaps;
    public Set<CanBeAttacked> toAttackeds;
//    public Set<CanTransferStrength> toTransfers;
    //本对象所在的所有集合
    public Collection<Collection> SetShell;



    public void delete(){
        //在各集合中删除本对象
        ArrayUtil.removeObjectFromCollection(this.SetShell, this);
    }

    @Override
    public void run() {
        while (true) {
            this.moveLeft();
            try {
                Thread.sleep(300);
                //出现异常，捕获异常并打印异常信息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Commons.drawingPanelForArray.repaint();
        }
//        this.delete();
//        Commons.drawingPanelForArray.repaint();
    }

    @Override
    //攻击
    public void attack(CanBeAttacked canBeAttacked) {
        canBeAttacked.attacked(this);
    }
    @Override
    //获得伤害值
    public int getDamage() {
        return this.damage;
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
}

