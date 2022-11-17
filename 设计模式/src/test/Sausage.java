package src.test;

public class Sausage extends Decorator {
    Sausage(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String offerHandPancake() {
        return super.offerHandPancake() + " 加香肠";
    }

    @Override
    public Integer calcCost() {
        return super.calcCost() + 3;
    }
}
