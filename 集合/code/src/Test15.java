/**
 * @description: 基本数据类型
 * @author: xjn
 * @create: 2019-04-10 19:44
 **/
public class Test15 {
    public static void main(String[] args) {
        String s = "哈fsasldjfALKD:J;Aklfj=s-d0-0p9uf:oi";
        char[] c = s.toCharArray();
        for(char x:c){
            if(Character.isLowerCase(x)){
                System.out.println();
            }
        }

        System.out.println(s.charAt(0));
        System.out.println(s.offsetByCodePoints(0,2));
        System.out.println("a".compareTo("z"));
        System.out.println("a".equalsIgnoreCase("A"));
        System.out.println("徐佳楠".startsWith("徐"));
        System.out.println("徐立杰徐几类".lastIndexOf("徐",3));
        System.out.println("徐哈徐".replace(new String("徐"),"xu"));
        System.out.println(" sdfsdf ".trim());
        String message = String.join("-", "Java", "is", "cool");
        System.out.println(message);
        System.out.println(String.valueOf(new Employee("xi",12)));
    }
}