package src.adapter.demo2;

public class MethodBeforeInterceptor implements MethodInterceptor{
    private Advice advice;

    public MethodBeforeInterceptor(Advice advice) {
        this.advice = advice;
    }

    @Override
    public void doInterceptor() {
        System.out.println("Do Interceptor");
    }
}
