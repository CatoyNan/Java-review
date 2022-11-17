package top.catoy.example.pojo.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import top.catoy.example.pojo.data.User;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull(message = "name 不能为空")
    private String name;

    @NotNull(message = "age 不能为空")
    private int age;

    @NotNull(message = "job 不能为空")
    private String job;

    @NotNull(message = "sex 不能为空")
    private String sex;

    public static UserDTO convert(User user) {
        Assert.notNull(user,"user不能为空");
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        if (1 == user.getSex()) {
            userDTO.setSex("man");
        } else {
            userDTO.setSex("woman");
        }
        return userDTO;
    }
}
