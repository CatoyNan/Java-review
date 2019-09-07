import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName Exp1
 * 1. 在电脑D盘下创建一个文件为HelloWorld.txt文件，
 * 判断他是文件还是目录，再创建一个目录IOTest,
 * 之后将HelloWorld.txt移动到IOTest目录下去；
 * 之后遍历IOTest这个目录下的文件
 * <p>
 * 程序分析：
 * 1、文件创建使用File的createNewFile()方法
 * 2、判断是文件用isFile(),判断是目录用isDirectory
 * 3、创建目录用：mkdirs()方法
 * 4、移动文件用：renameTo
 * 5、遍历目录用：list（）方法获得存放文件的数组，foreach遍历的方法把文件打印出来
 * @Date 2019-09-05 22:15
 * @Version 1.0
 **/
public class Exp1 {
    public static void main(String[] args) {
        String fileName1 = "/Users/admin/Desktop/HelloWorld.txt";
        File file = new File(fileName1);
        if (!file.exists()) {
            boolean isSuccess;
            try {
                isSuccess = file.createNewFile();
                if (isSuccess) {
                    System.out.println("文件创建成功");
                } else {
                    System.out.println("文件创建失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(file.isDirectory() ? "是目录" : "是文件");

        String dirName = "/Users/admin/Desktop/IOTest";
        File dir = new File(dirName);
        dir.mkdirs();

        if (dir.exists() && file.exists() && dir.isDirectory()) {
            System.out.println(file.renameTo(new File(dir.getPath() + "/" + file.getName())) ? "移动成功" : "移动失败");
        } else {
            return;
        }

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }

        String[] strings = dir.list();
        for (String s : strings) {
            System.out.println(s);
        }

    }
}
