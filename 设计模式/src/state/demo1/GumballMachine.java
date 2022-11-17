package src.state.demo1;

/**
 * 糖果机
 */
public class GumballMachine {
    private static final int SOLD_OUT = 0;
    private static final int NO_QUARTER = 1;
    private static final int HAS_QUATER = 2;
    private static final int SOLD = 3;

    private int state = SOLD_OUT;
    private int count = 0;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    /**
     * 投入
     */
    public void insertQuarter() {
        if (state == HAS_QUATER) {
            System.out.println("you can not insert another quarter");
        } else if (state == NO_QUARTER) {
            state = HAS_QUATER;
            System.out.println("you inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("you can not insert quarter, the machine is sold out");
        } else if (state == SOLD) {
            System.out.println("please wait , we are already giving you a gumball");
        }
    }

    /**
     * 退回
     */
    public void ejectQuarter() {
        if (state == HAS_QUATER) {
            System.out.println("quarter returned");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("you haven not inserted a quarter");
        } else if (state == SOLD) {
            System.out.println("sorry, you already turned the crank");
        } else if (state == SOLD_OUT) {
            System.out.println("you can not eject, you have not inserted a quarter yet");
        }
    }

    /**
     * 转动手柄
     */
    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("turning twice does not get you anther gumball");
        } else if (state == NO_QUARTER) {
            System.out.println("you turned bug there is no quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("you turned, but there are no gumballs");
        } else if (state == HAS_QUATER) {
            System.out.println("you turned");
            state = SOLD;
            dispense();
        }
    }

    /**
     * 发放
     */
    public void dispense() {
        if (state == SOLD) {
            System.out.println("a gumball comes rolling out the slot");
            count = count - 1;
            if (count == 0) {
                System.out.println("oops, out of gumballs");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER) {
            System.out.println("you need to pay first");
        } else if (state == SOLD_OUT) {
            System.out.println("no gumball dispensed");
        } else if (state == HAS_QUATER) {
            System.out.println("no gumball dispensed");
        }
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}
