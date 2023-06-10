package src.test2;

public class Director implements Stream{
    private Stream stream;

    public Director(Stream stream){
        this.stream = stream;
    }

    @Override
    public void option() {
        stream.option();
    }
}
