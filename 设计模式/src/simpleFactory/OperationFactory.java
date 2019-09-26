package src.simpleFactory;

/**
 * @ClassName OperationFactory
 * @Description  工厂
 * @Author admin
 * @Date 2019-06-23 12:33
 * @Version 1.0
 **/
public class OperationFactory {
   public static Operation createOperation(String str){
       Operation operation = null;
       switch (str){
           case "+":
               operation = new Operationadd();
               break;
           case "-":
               operation = new Operationsub();
               break;
       }
       return operation;
   }

}
