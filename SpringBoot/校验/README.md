> ## Request Body 

- 如果不需要分组校验，*@Valid* 和 *@Validated*没有区别
- 如果需要嵌套校验，属性需要加上@Valid修饰
- 如果需要分组校验则需要用@Validated

```java
@PostMapping
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
}

@PostMapping
public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
}

public class User {

    @Id
    private String id;

    @NotEmpty
    private String name;

    @Valid
    @NotNull
    private Address address;
}

public class Address {

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @Size(min = 4, max = 6)
    private String postcode;
}
```





> ## Request Parameters and Path Variables

- controller类上必须加上*@Validated*注解

```java
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping("/{postcode}")
    public ResponseEntity<List<User>> findAllByPostcode(@Size(min = 4, max = 6)
                                                        @PathVariable String postcode) {
        return ResponseEntity.ok(userService.findAllByPostcode(postcode));
    }
}
```



> ## 分组

- controller类上和方法上需要加上*@Validated*注解

```java

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<User> createUser(@Valid
                                           @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping
    @Validated(UpdateUser.class)
    public ResponseEntity<User> updateUser(@Valid
                                           @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }
    //...
}


public interface UpdateUser {}
public interface CreateUser {}

public class User {

    @Id()
    @Null(groups = CreateUser.class)
    @NotNull(groups = UpdateUser.class)
    private String id;

    @NotEmpty
    private String name;

    @NotNull
    @Valid
    private Address address;
}
```

[参考]: https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4

