/**
 * @ClassName RemoveSpace
 * @Description 去除字符串中的空格
 * @Author admin
 * @Date 2019-09-04 16:39
 * @Version 1.0
 **/
public class RemoveSpace {
    public static void main(String[] args){
        String str = "  123  123";
        char[] c = {160};
        str =  str + String.valueOf(c) + "  123  ";
        System.out.println("***|" + str + "|***");
        //方式一:只能去除首位空格
        String newStr1 = str.trim();
        System.out.println("***|" + newStr1 + "|***");

        //方式二:replace
        String newStr2 = str.replace(" ","");
        System.out.println("***|" + newStr2 + "|***");


        //方法三:replaceAll,可以去除不间断空格&nbsp;
        String newStr3 = str.replaceAll(String.valueOf((char)160),"").replaceAll(" ","");
        System.out.println("***|" + newStr3 + "|***");

        //方法四:replaceAll,正则匹配
        String newStr4 = str.replaceAll("\\s","");
        System.out.println("***|" + newStr4 + "|***");

    }

}
