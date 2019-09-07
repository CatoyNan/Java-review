import java.io.File;

/**
 * @ClassName Exp2
 * @Description 递归实现输入任意目录，列出文件以及文件夹
 * @Author admin
 * @Date 2019-09-06 09:02
 * @Version 1.0
 **/
public class Exp2 {
    public static void deepRucursion(File file, String lever) {
        System.out.println(lever + file.getName());
        if (file.isFile()) {
            return;
        }
        File[] files = file.listFiles();
        lever = lever + "——";
        for (File f : files) {
            deepRucursion(f, lever);
        }
    }

    public static void main(String[] args) {
        File file = new File("/Users/admin/Desktop/IOTest");
        if (file.isDirectory() && file.exists()) {
            deepRucursion(file, "");
        }
    }
}
