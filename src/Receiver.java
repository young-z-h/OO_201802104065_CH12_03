//201802104065闫天意
public abstract class Receiver {
    //定义一个Receiver的构造器，并对其属性进行赋值
    public Receiver(int fuel, int storage) {
        this.fuel = fuel;
        this.storage = storage;
    }

    /**
     * 定义一个得到油量缺口的方法
     * @return 返回油量缺口的值
     */
    public int getGap(){
        return this.storage - this.fuel;
    }

    public void receiveFuel(int add)
    {
        this.fuel += add;
    }
    //声明变量fuel作为油量
    protected int fuel;
    //声明变量storage作为油箱储量
    protected int storage;
}