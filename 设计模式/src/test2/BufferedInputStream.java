package src.test2;

public class BufferedInputStream extends Director{

    public BufferedInputStream(Stream stream){
        super(stream);
    }
    @Override
    public void option() {
        super.option();
        System.out.println("增加缓存功能");
    }
}
