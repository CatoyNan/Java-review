import java.util.Date;

/**
 * @description:
 * @author: xjn
 * @create: 2019-04-02 12:58
 **/
public class Employee {
    private String name;
    private int age;
    private Date date;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee =(Employee)obj;
        return name.equals(employee.name);//可以直接访问私有name域
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
