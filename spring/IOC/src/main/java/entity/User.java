package entity;

import java.util.Date;

/**
 * @description: user
 * @author: xjn
 * @create: 2019-04-20 15:19
 **/
public class User {
    private String name;
    private Date date;

    public User(){

    }

    public User(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return (Date)date.clone();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
