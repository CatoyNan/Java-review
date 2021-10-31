package top.catoy.example.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int id;
    private String name;
    private int age;
    private String job;
    private int sex;
}
