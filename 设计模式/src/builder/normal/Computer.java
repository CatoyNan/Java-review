package src.builder.normal;

public class Computer {
    private String cpu;
    private String ram;
    private String driver;
    private String keyborad;
    private String mouse;

    public Computer(String cpu, String ram, String driver) {
        this.cpu = cpu;
        this.ram = ram;
        this.driver = driver;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
