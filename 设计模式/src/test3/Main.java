package src.test3;

public class Main {
    public static void main(String[] args) {
//        RequestQueue requestQueue = new RequestQueue();
//        new ServerThread(requestQueue, "receiveBoy", 6535897L).start();
//        new ClientThread(requestQueue, "sendBoy", 3141592L).start();

//        RequestQueue queue1 = new RequestQueue();
//        RequestQueue queue2 = new RequestQueue();
//        queue1.putRequest(new Request("hello"));
//        new TalkThread(queue1, queue2, "alice").start();
//        new TalkThread(queue2, queue1, "bob").start();

        RequestQueue requestQueue = new RequestQueue();
        ServerThread receiveBoy = new ServerThread(requestQueue, "receiveBoy", 6535897L);
        ClientThread sendBoy = new ClientThread(requestQueue, "sendBoy", 3141592L);
        receiveBoy.start();
        sendBoy.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("call interrupt");
        receiveBoy.interrupt();
        sendBoy.interrupt();
    }
}
