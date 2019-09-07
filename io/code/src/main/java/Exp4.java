import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName Exp4
 * @Description 从磁盘读取一个文件到内存中，再打印到控制台
 * @Author admin
 * @Date 2019-09-06 13:23
 * @Version 1.0
 **/
public class Exp4 {
    public static void main(String[] args) {
        File file = null;
        FileInputStream is = null;

        try {
            file = new File("/Users/admin/Desktop/eastbay.txt");
            is = new FileInputStream(file);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }

            byte[] buffer = new byte[800];
            int len;
            while ((len = is.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, len));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
