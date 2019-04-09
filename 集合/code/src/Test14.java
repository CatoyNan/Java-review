import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-08 22:07
 **/
public class Test14 {
    public static void main(String[] args){
        //类名::静态方法名
        Consumer<String> consumer1 = (x) -> {Dog.bark(x);};
        Consumer<String> consumer2 = Dog::bark;

        //实例名::实例方法名
        Dog dog = new Dog("dog1");
        Consumer<String> consumer3 = (x) -> {dog.eat(x);};
        Consumer<String> consumer4 = dog::eat;

        //类名::实例方法名
        //引用方法的参数要与函数式接口中的方法参数对应
        //由于函数式接口中方法的第一个参数是去调用引用的实例方法,
        //因此函数式接口中方法的参数会比引用方法的参数多一个。
        BiConsumer<Dog,Dog> consumer5 = Dog::mkFrinds;
        consumer5.accept(new Dog("dog1"),new Dog("dog2"));
    }
}
class Dog{
    private String name;

    public Dog(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void bark(String name){
        System.out.println(name + "叫");
    }

    public void eat(String food){
        System.out.println("eat" + food);
    }

    public void mkFrinds(Dog dog){
        System.out.println("和" + dog.getName() + "做朋友");
    }
}