package proxy;

/**
 * @description: 被代理类实现接口
 * @author: xjn
 * @create: 2019-04-24 18:17
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void addData() {
        System.out.println("增加数据");
    }
}
