package src.factoryMethod;

/**
 * @ClassName Client
 * @Description TODO
 * @Author admin
 * @Date 2020-05-10 16:41
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        Factory appleFactory = new AppleFactory();
        Fruit apple = appleFactory.create();
        apple.desc();

        Factory oriangeFactory = new OriangeFactory();
        Fruit oriange =  oriangeFactory.create();
        oriange.desc();
    }
}
