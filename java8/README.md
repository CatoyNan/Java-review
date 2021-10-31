#### 对象数组根据某个字段去重

```java
List<Student> studentList = base.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getId))),
                                ArrayList::new
                        )
                );
```



#### 平铺流

```java
List<Student> studentList = teacherList.stream()
                .map(Teacher::getStudentList)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList())
```

