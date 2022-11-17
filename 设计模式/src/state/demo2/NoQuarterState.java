package src.state.demo2;


public class NoQuarterState implements State {

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("you inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterSate());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you have not inserted q quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned, but there is no quarter");
    }

    @Override
    public void dispense() {
        System.out.println("you need to pay first");
    }
}
