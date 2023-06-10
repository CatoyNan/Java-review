package src.test;

public abstract class Decorator implements HandPancake {
    private HandPancake handPancake;

    Decorator(HandPancake handPancake) {
        this.handPancake = handPancake;
    }

    /**
     * 提供手抓饼
     *
     * @return
     */
    @Override
    public String offerHandPancake() {
        return handPancake.offerHandPancake();
    }

    /**
     * 提供手抓饼的价格
     *
     * @return
     */
    @Override
    public Integer calcCost() {
        return handPancake.calcCost();
    }
}