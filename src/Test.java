//201802104065闫天意
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {
    public static void main(String[] args) {
        //创建各类对象，并打印其信息
        //新建DownCounter对象,声明dc变量指向这个对象
        DownCounter dc = new DownCounter();
        Gun gun = new Gun(750,150,70,50);
        Runnable t1 = new Tank(20,200,5,300,700,50,70,50);
        Barrier barrier1 = new Barrier(10, (Tank) t1,500,50,50,50,1);
        Barrier barrier2 = new Barrier(10, (Tank) t1,250,50,70,70,2);
        Runnable t2 = new FirstAid((Tank) t1,600,80,15,15,2);

        Set<Shape> shapes = new HashSet<>();
        Commons.shapes.add(barrier1);
        Commons.shapes.add(barrier2);
        Commons.shapes.add((Shape) t2);
        Commons.shapes.add((Shape) t1);
        Commons.shapes.add(dc);
        Commons.shapes.add(gun);
        Set<OverlapSensitive> beOverlapeds = new HashSet<>();
        beOverlapeds.add(barrier1);
        beOverlapeds.add(barrier2);
        beOverlapeds.add((OverlapSensitive) t2);
//        Set<CanBeAttacked> beAttackeds  = new HashSet<>();
        Commons.canBeAttackeds.add(barrier1);
        Commons.canBeAttackeds.add(barrier2);
        Set<CanTransferStrength> transferStrengths = new HashSet<>();
        transferStrengths.add((CanTransferStrength) t2);

        /**
         * 创建集合类对象where**IsIn
         * 将where**IsIn传入**的私有字段
         * 为where**IsIn集合添加元素
         */
        Collection<Collection> whereFirstAidIsIn = new ArrayList<Collection>();
        ((FirstAid) t2).setWhereIamIn(whereFirstAidIsIn);


        whereFirstAidIsIn.add(Commons.shapes);
        whereFirstAidIsIn.add(beOverlapeds);
        whereFirstAidIsIn.add(transferStrengths);
        Collection<Collection> whereBarrierIsIn = new ArrayList<Collection>();
        barrier1.setWhereIamIn(whereBarrierIsIn);
        barrier2.setWhereIamIn(whereBarrierIsIn);
        whereBarrierIsIn.add(Commons.shapes);
        whereBarrierIsIn.add(Commons.canBeAttackeds);
        whereBarrierIsIn.add(beOverlapeds);
        // 设置tank的关联字段
        ((Tank) t1).toOverlaps=beOverlapeds;
        ((Tank) t1).toAttackeds=Commons.canBeAttackeds;
        ((Tank) t1).toTransfers=transferStrengths;
        //声明Controllable类型的变量controllable指向对象tank
        Controllable controllable = (Controllable) t1;
        //新建DrawingPanel的对象，传入形参shapes，声明drawingPanel变量指向这个对象
        Commons.drawingPanelForArray = new DrawingPanelForArray(Commons.shapes);
        //新建ControlPanel的对象，传入形参controllable，声明ControlPanell变量指向这个对象
        ControlPanel controlPanel = new ControlPanel(controllable);
        //新建MyFrame的对象，传入形参drawingPanel，声明myframe变量指向这个对象
        MyFrame myFrame = new MyFrame(Commons.drawingPanelForArray,controlPanel);
        //设置框架对象的大小
        myFrame.setSize(950,300);
        //设置框架对象为可见
        myFrame.setVisible(true);
        //获得任务类执行器对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        //执行器对象执行任务类对象
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(dc);
        executorService.execute(gun);
        //执行器对象关闭，不再接受新的任务类
//        executorService.shutdown();
    }
}
