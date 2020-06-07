import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//201802104065闫天意
public class Commons {
    //方便直接向本对象中添加Shape类型的对象
    public final static Set<Shape> shapes = new CopyOnWriteArraySet<Shape>();
    //方便直接向本对象中添加Overlappables类型的对象
    public final static Collection<OverlapSensitive> toOverlaps = new CopyOnWriteArraySet<OverlapSensitive>();
    public final static Set<CanBeAttacked> canBeAttackeds = new CopyOnWriteArraySet<CanBeAttacked>();
    public static DrawingPanelForArray drawingPanelForArray;
}
