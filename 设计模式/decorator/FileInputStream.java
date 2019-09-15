package decorator;

/**
 * @ClassName InputStream
 * @Description 节点流（具体组件），读取字节
 * @Author admin
 * @Date 2019-09-09 15:32
 * @Version 1.0
 **/
public class FileInputStream implements Stream{
    @Override
    public void option() {
        System.out.println("读取文件");
    }
}
