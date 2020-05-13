package src.factoryMethod;

/**
 * @ClassName OriangeFactory
 * @Description TODO
 * @Author admin
 * @Date 2020-05-10 16:44
 * @Version 1.0
 **/
public class OriangeFactory implements Factory{
    @Override
    public Fruit create() {
        return new Oriange();
    }
}
