package src.test;

public class NotelessHandPancake implements HandPancake {
    /**
     * 提供noteless 家的手抓饼一份
     */
    @Override
    public String offerHandPancake() {
        return " noteless 家的手抓饼";
    }

    /**
     * 计算 noteless 家 一份手抓饼的价格
     *
     * @return
     */
    @Override
    public Integer calcCost() {
        return 3;
    }
}