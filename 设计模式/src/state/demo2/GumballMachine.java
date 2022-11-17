package src.state.demo2;

/**
 * 糖果机
 */
public class GumballMachine {
    private State soldOutState;
    private State noQuarterState;
    private State hasQuarterSate;
    private State soldState;

    private State state = soldOutState;
    private int count = 0;

    public GumballMachine(int count) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterSate = new HasQuarterState(this);
        soldState = new SoldOutState(this);
        this.count = count;
        if (count > 0) {
            state = noQuarterState;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public void setNoQuarterState(State noQuarterState) {
        this.noQuarterState = noQuarterState;
    }

    public State getHasQuarterSate() {
        return hasQuarterSate;
    }

    public void setHasQuarterSate(State hasQuarterSate) {
        this.hasQuarterSate = hasQuarterSate;
    }

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 投入
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * 退回
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * 转动手柄
     */
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    /**
     * 发放
     */
    private void dispense() {
        state.dispense();
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
