package top.catoy.dao.mapper1;

import org.apache.ibatis.annotations.Select;
import top.catoy.model.User;

public interface UserMapper1 {
    User findAll();
}
