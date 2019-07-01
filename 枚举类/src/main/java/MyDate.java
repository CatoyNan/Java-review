/**
 * @ClassName MyDate
 * @Description TODO
 * @Author admin
 * @Date 2019-07-01 11:11
 * @Version 1.0
 **/
public enum MyDate {
    MONDAY(1,"MONDAY"),
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATUADARY,
    SUNDARY;

    private int value;
    private String name;

    private MyDate(int value,String name){
        this.value = value;
        this.name = name;
    }

    private MyDate(){

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void fun(){
        System.out.println("hahah");
    }
}
