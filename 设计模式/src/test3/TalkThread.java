package src.test3;

import java.sql.SQLOutput;

public class TalkThread extends Thread{
    private final RequestQueue input;
    private final RequestQueue output;

    public TalkThread(RequestQueue input, RequestQueue output, String name){
        super(name);
        this.input = input;
        this.output = output;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " starting");
        try {
            for (int i=0; i<20; i++) {
                Request request1 = input.getRequest();
                System.out.println(Thread.currentThread().getName() + ": getRequest-" + request1);

                Request request2 = new Request(request1.getName() + "!");
                System.out.println(Thread.currentThread().getName() + ": putRequest-" + request2);
                output.putRequest(request2);
            }
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " ending");
    }
}
