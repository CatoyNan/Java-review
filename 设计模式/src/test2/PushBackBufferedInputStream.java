package src.test2;

public class PushBackBufferedInputStream implements Stream{
    private Stream stream;

    public PushBackBufferedInputStream(Stream stream){
        this.stream = stream;
    }

    @Override
    public void option() {
        stream.option();
        System.out.println("增加回退功能");
    }
}
