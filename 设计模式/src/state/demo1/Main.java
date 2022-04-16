package src.state.demo1;

/**
 * 未用状态模式优化
 */
public class Main {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);
        gumballMachine.dispense();
    }
}
