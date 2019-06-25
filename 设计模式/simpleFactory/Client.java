package simpleFactory;

/**
 * @ClassName Client
 * @Description 客户端
 * @Author admin
 * @Date 2019-06-23 12:37
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args){
        Operation operation = OperationFactory.createOperation("+");
        operation.set_numberA(1);
        operation.set_numberB(3);
        double result = operation.getResult();
        System.out.println(result);
    }
}
