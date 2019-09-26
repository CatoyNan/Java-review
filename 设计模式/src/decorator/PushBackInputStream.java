package src.decorator;

/**
 * @ClassName PushBackInputStream
 * @Description 过滤流（具体装饰组件）
 * @Author admin
 * @Date 2019-09-09 15:57
 * @Version 1.0
 **/
public class PushBackInputStream extends FilterInputStream{

    public PushBackInputStream(Stream stream) {
        super(stream);
    }

    @Override
    public void option() {
        super.option();
        System.out.println("增加回退功能");
    }
}
