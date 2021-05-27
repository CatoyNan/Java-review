package src.adapter.demo2;

public class Client {
    public static void main(String[] args) {
        Advice advice = new MethodBeforeAdvice();
        AdviceAdapter adapter = new MethodBeforeAdviceAdapter();

        MethodInterceptor inteceptor = adapter.getInteceptor(advice);
        inteceptor.doInterceptor();
    }
}
