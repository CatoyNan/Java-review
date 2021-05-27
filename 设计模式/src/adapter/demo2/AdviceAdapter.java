package src.adapter.demo2;

public interface AdviceAdapter {
    MethodInterceptor getInteceptor(Advice advice);
}
