package top.catoy.dao.mapper2;

import org.apache.ibatis.annotations.Select;
import top.catoy.model.User;

public interface UserMapper2 {
    @Select("select * from user")
    User findAll();
}
