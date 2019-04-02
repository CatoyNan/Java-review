import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;


public class Main {
    public class  Persion implements Comparable<Persion>{
        private String name;

        public Persion(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Persion p) {
            int diff = name.compareTo(p.getName());
            return diff;
        }

        @Override
        public String toString() {
            return "Persion{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public class  Worker {
        private String name;

        public Worker(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "Persion{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public void run(){
//        Set<Persion> persionSet = new TreeSet<>();
//        persionSet.add(new Persion("小明"));
//        persionSet.add(new Persion("小黄"));
//        persionSet.add(new Persion("小量"));
//        persionSet.add(new Persion("小黑"));
//        System.out.println(persionSet.toString());

//        Set<Worker> workerSet = new TreeSet<>(Comparator.comparing(Worker,));
//        workerSet.add(new Worker("小明"));
//        workerSet.add(new Worker("小黄"));
//        workerSet.add(new Worker("小量"));
//        workerSet.add(new Worker("小黑"));
//        System.out.println(workerSet.toString());
    }


    public static void main(String[] args) {
       Main main = new Main();
//       main.run();
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeIf(number -> (number%2)==0);
        System.out.println(list.toString());
    }

}
