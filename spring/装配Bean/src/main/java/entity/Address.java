package entity;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-24 23:00
 **/
public class Address {
    private String home;

    public  Address(){

    }
    public Address(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{" +
                "home='" + home + '\'' +
                '}';
    }
}
