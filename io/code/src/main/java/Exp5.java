import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName Exp5
 * @Description 在程序中写一个"HelloJavaWorld你好世界"输出到操作系统文件Hello.txt文件中
 * @Author admin
 * @Date 2019-09-06 15:32
 * @Version 1.0
 **/
public class Exp5 {
    public static void main(String[] args) {
        File file = new File("/Users/admin/Desktop/Hello.txt");
        String content = "HelloJavaWorld你好世界";
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            byte[] buffer = content.getBytes();
            fos.write(buffer);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
