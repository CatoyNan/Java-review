package src.test;

import src.decorator.BufferedInputStream;
import src.decorator.FileInputStream;
import src.decorator.FilterInputStream;
import src.proxy.UserService;
import src.proxy.UserServiceImpl;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {

        //有一个顾客王五,他想吃手抓饼了,他加了一根烤肠 又加了培根
        Customer customerC = new Customer("王五");
        customerC.buy(new Bacon(new Sausage(new NotelessHandPancake())));

        FileInputStream fileInputStream = new FileInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

    }
}
