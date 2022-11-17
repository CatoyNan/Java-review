package src.decorator;

/**
 * @ClassName Client
 * @Description TODO
 * @Author admin
 * @Date 2019-09-09 16:01
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        FileInputStream file = new FileInputStream();
        BufferedInputStream bf = new BufferedInputStream(file);
        PushBackInputStream bf1 = new PushBackInputStream(bf);
        bf1.option();


    }
}
