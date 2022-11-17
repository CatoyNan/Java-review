package src.builder.normal;

public abstract class Builder {
    public abstract void setCpu();

    public abstract void setRam();

    public abstract void setDriver();

    public abstract Computer getComputer();
}
