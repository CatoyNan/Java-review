import java.io.File;

/**
 * @ClassName Exp3
 * @Description 递归实现列出当前工程下所有.java文件
 * @Author admin
 * @Date 2019-09-06 13:08
 * @Version 1.0
 **/
public class Exp3 {
    public static void deepRucursion(File file) {
        if(file.getName().endsWith(".java")){
            System.out.println(file.getName());
        }
        if (file.isFile()) {
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            deepRucursion(f);
        }
    }

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir"));
        deepRucursion(file);
    }
}
