package src.factoryMethod;

/**
 * @ClassName AppleFactory
 * @Description TODO
 * @Author admin
 * @Date 2020-05-10 16:41
 * @Version 1.0
 **/
public class AppleFactory implements Factory{
    @Override
    public Fruit create() {
        return new Apple();
    }
}
