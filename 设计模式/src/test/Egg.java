package src.test;

public class Egg extends Decorator {
    Egg(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String offerHandPancake() {
        return super.offerHandPancake() + "加鸡蛋";
    }

    @Override
    public Integer calcCost() {
        return super.calcCost() + 2;
    }
}