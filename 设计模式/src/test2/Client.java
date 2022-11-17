package src.test2;

public class Client {
    public static void main(String[] args) {
        FileInputStream fileInputStream = new FileInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        PushBackBufferedInputStream pushBackBufferedInputStream = new PushBackBufferedInputStream(bufferedInputStream);
        pushBackBufferedInputStream.option();
    }
}
