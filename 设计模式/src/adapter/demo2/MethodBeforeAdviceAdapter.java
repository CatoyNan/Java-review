package src.adapter.demo2;

public class MethodBeforeAdviceAdapter implements AdviceAdapter {
    @Override
    public MethodInterceptor getInteceptor(Advice advice) {
        return new MethodBeforeInterceptor(advice);
    }
}
