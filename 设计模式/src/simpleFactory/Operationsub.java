package src.simpleFactory;

/**
 * @ClassName Operationsub
 * @Description 减法
 * @Author admin
 * @Date 2019-06-23 12:31
 * @Version 1.0
 **/
public class Operationsub extends Operation{
    @Override
    public double getResult() {
        double result = 0;
        result = get_numberA() - get_numberB();
        return result;
    }
}
