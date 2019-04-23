package entity;

import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-23 17:03
 **/
public class User {
    private String name;
    private Date date;
    public String sex;

    public User(){

    }

    public User(String name, Date date, String sex) {
        this.name = name;
        this.date = date;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", sex='" + sex + '\'' +
                '}';
    }
}
