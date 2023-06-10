package src.test;

public class Bacon extends Decorator {
    Bacon(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String offerHandPancake() {
        return super.offerHandPancake() + " 加培根";
    }

    @Override
    public Integer calcCost() {
        return super.calcCost() + 4;
    }
}