package exp1;

/**
 * @ClassName TestSimpleException
 * @Description TODO
 * @Author admin
 * @Date 2019-07-29 11:11
 * @Version 1.0
 **/
public class TestSimpleException {
    public void fun1() throws SimpleException {

            throw new SimpleException();
    }

    public void fun2(){
        try {
            fun1();
        } catch (SimpleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        TestSimpleException testSimpleException = new TestSimpleException();
        testSimpleException.fun2();
    }
}
