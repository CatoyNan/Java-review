### 消息的投递

- 当交换器没有匹配到队列时，mandatory=true时，消息返回给生产者，可以注册监听器获取返回的消息。

![image-20200104175037533](/Users/admin/Desktop/document/学习/Java-review/rabbitmq/assets/image-20200104175037533.png)

- 当交换器没有匹配到队列时，mandatory=false时，在没有注册监听器的情况下为了防止消息丢失，可以使用备份交换器