package src.test3;

import java.util.Random;

public class ClientThread extends Thread {
    private final RequestQueue requestQueue;
    private final Random random;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Request request = new Request(String.valueOf(i));
                System.out.println(Thread.currentThread().getName() + " produce request " + request);
                requestQueue.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}
