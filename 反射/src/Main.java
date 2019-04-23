import entity.User;

public class Main {

    public static void main(String[] args) throws Exception{
        User user = new User();

       Class clazz1 = Class.forName("entity.User");
       Class clazz2 = user.getClass();
       Class clazz3 = User.class;

       System.out.println(clazz1);
       System.out.println(clazz2);
       System.out.println(clazz3);
    }
}
