import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author tg.si@188.com
 * @version 1.0
 */
public class ArrayUtil {

    public static void removeObjectFromCollection(
            Collection<Collection> collectionCollection, Object toRemove){
        //大集合的迭代器，元素必须为Collection类型
        Iterator<Collection> itCollection = collectionCollection.iterator();
        while(itCollection.hasNext()){
            //获得大集合的元素（即小集合）
            Collection collectionElement = itCollection.next();
            //小集合的迭代器，元素必须作为Object类型的对象
            Iterator itForObject = collectionElement.iterator();
            while (itForObject.hasNext()){
                //获得小集合中的元素
                Object el = itForObject.next();
                //如果该元素为要删除的对象
                if(el==toRemove){
                    //通过迭代器删除
                    itForObject.remove();
                    System.out.println("删除! " + toRemove  );
                }
            }
        }
    }
    /**
     * 判断element是否存在数组array中
     *
     * @param array
     * @param element
     * @return
     */
    public static boolean contains(Object[] array, Object element) {
        boolean found = false;
        for (Object object : array) {
            if (element == object) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * 将与element相等的元素置为null
     *
     * @param array
     * @param element
     */
    public static void nullify(Object[] array, Object element) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i);
            if (element == array[i]) {
                array[i] = null;
            }
        }
    }

    //输出所有元素
    public static void output(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + ":");
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[3];
        strings[0] = "a";
        strings[1] = "b";
        strings[2] = "c";

        boolean contained = ArrayUtil.contains(strings, "b");
        System.out.println(contained);
    }

    //定义一个判断对象是否在该集合内的方法
    public static boolean containedInCollection(Object toSearch,Collection collection){
        //先提前定义contained为假，若后面没有查询到在该集合内，则直接返回假
        boolean contained = false;
        //以迭代器的形式返回集合元素
        Iterator it = collection.iterator();
        //遍历集合元素
        while(it.hasNext()){
            //指针下移，返回当前元素
            Object element = it.next();
            //如果查询到有相同的元素
            if (toSearch == element)
                //直接返回真
                contained = true;
        }
        //否则返回假
        return contained;
    }
}
