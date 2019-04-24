package proxy;

/**
 * @description: 代理类
 * @author: xjn
 * @create: 2019-04-24 18:18
 **/
public class UserServiceProx implements UserService {
    //被代理对象
    private UserService target;
    //通过构造方法传入代理对象
    public UserServiceProx(UserService target){
        this.target = target;
    }

    @Override
    public void addData() {
        System.out.println("准备向数据插入数据");
        target.addData();
        System.out.println("插入数据结束");
    }
}
