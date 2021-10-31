package src.builder.normal;

public class MacBuilder extends Builder {

    private Computer computer;

    public MacBuilder() {
        this.computer = new Computer("mac", "mac", "mac");
    }

    @Override
    public void setCpu() {
        this.computer.setCpu("mac");
    }

    @Override
    public void setRam() {
        this.computer.setRam("mac");
    }

    @Override
    public void setDriver() {
        this.computer.setDriver("mac");
    }

    @Override
    public Computer getComputer() {
        return this.computer;
    }
}
