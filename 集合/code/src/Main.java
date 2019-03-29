import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class Main {
    public class User{
        private String name;
        private String home;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }
    }

    public class Son extends User{
        public void eat(){
            System.out.println("eat");
        }
    }

    public void run(){
        User user1 = new User();
        User user2 = new User();
        user1.setHome("123");
        user2.setHome("4567");
        List<User> users = new ArrayList<>();
        users.add(user1);
        System.out.println(users.contains(user1));

    }

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list1.addAll(list2);
        System.out.println(list1.toString());
//        ((ListIterator<Integer>) iterator).add(88);
//        System.out.println(list.toString());

    }
}
