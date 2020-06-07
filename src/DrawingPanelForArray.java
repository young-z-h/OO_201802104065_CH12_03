//201802104065闫天意
import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//画图面板
public final class DrawingPanelForArray extends JPanel {
    public DrawingPanelForArray(Set<Shape> shapes) {
        this.shapes = shapes;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //依次向元素发送drawMyself(Graphics g)消息
        //通知所有对象画出自己
        Iterator shapeIt=this.shapes.iterator();
        while (shapeIt.hasNext()){
            Shape shape = (Shape)shapeIt.next();
            shape.drawMyself(g);
        }
    }
    //要画出的对象保存在Object集合中
    private Collection shapes;
}

