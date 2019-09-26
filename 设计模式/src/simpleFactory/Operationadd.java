package src.simpleFactory;

/**
 * @ClassName Operationadd
 * @Description 加法
 * @Author admin
 * @Date 2019-06-23 12:25
 * @Version 1.0
 **/
public class Operationadd extends Operation{

    @Override
    public double getResult() {
        double result = 0;
        result = get_numberA() + get_numberB();
        return result;
    }
}
