package src.decorator;

/**
 * @ClassName BufferedInputStream
 * @Description 过滤流具体装饰组件
 * @Author admin
 * @Date 2019-09-09 15:43
 * @Version 1.0
 **/
public class BufferedInputStream extends FilterInputStream {

    public BufferedInputStream(Stream stream) {
        super(stream);
    }

    public void option() {
        super.option();
        System.out.println("增加缓存功能");
    }


}
