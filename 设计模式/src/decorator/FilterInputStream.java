package src.decorator;

/**
 * @ClassName FilterInputStream
 * @Description 过滤流（装饰类抽象类）
 * @Author admin
 * @Date 2019-09-09 15:35
 * @Version 1.0
 **/
public class FilterInputStream implements Stream{
    private Stream stream;

    public FilterInputStream(Stream stream) {
        this.stream = stream;
    }

    @Override
    public void option() {
        stream.option();
    }
}
