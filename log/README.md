## 一、主要组件

- Appenders: 输出源,日志要输出的位子

  ​	org.apache.log4j.ConsoleAppender（控制台）

  ​	org.apache.log4j.FileAppender（文件）

  ​	org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件）

  ​	org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）

  ​	org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）

- Loggers:记录器,日志的类型和级别

  ​	级别顺序：DEBUG < INFO < WARN < ERROR < FATAL

  ​	规则：

  ​		1.输出自己和比自己级别高的	

  ​		2.有多个记录器,选用类名精确的级别,所有记录器都输出

  ```txt
  log4j.logger.packge.className=lever,hello.File,hello.Console
  
  logtj.logger.packge.name=lever,hello.File,hello.Console
  private static final Logger logger = Logger.getLogger("name");
  
  log4j.rootLogger=lever,hello.File,hello.File
  ```

  

- Layouts:布局,日志以何种形式输出

## 二、配置文件

### 2.1 XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

    <appender name="file" class="org.apache.log4j.DailyRollingFileAppender">
        <param name="file" value="/Users/admin/Desktop/hello.log" />
        <param name="append" value="true" />
        <param name="encoding" value="UTF-8" />
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d %p [%c{10}] - %m%n" />
        </layout>
        <filter class="org.apache.log4j.varia.LevelRangeFilter">
            <param name="levelMin" value="info"/>
            <param name="levelMax" value="info"/>
            <param name="AcceptOnMatch" value="true"/>
        </filter>
    </appender>

    <appender name="Console" class="org.apache.log4j.ConsoleAppender">
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d [%c{10}] - %m%n" />
        </layout>
    </appender>

    <!--
       1. 指定logger的设置，additivity是否遵循缺省的继承机制
       2. 当additivity="false"时，root中的配置就失灵了，不遵循缺省的继承机制
   -->
    <logger name="com.catoy.Exp1" additivity="false">
        <level value="error" />
        <appender-ref ref="file" />
        <appender-ref ref="Console"/>
    </logger>
    
    <root>
        <level value="info"/>
        <appender-ref ref="Console"/>
    </root>

</log4j:configuration>
```



### 2.2 Properties

```properties
log4j.rootLogger=DEBUG,console,dailyFile,im
log4j.additivity.org.apache=true
# 控制台(console)
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.ImmediateFlush=true
log4j.appender.console.Target=System.err
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# 日志文件(logFile)
log4j.appender.logFile=org.apache.log4j.FileAppender
log4j.appender.logFile.Threshold=DEBUG
log4j.appender.logFile.ImmediateFlush=true
log4j.appender.logFile.Append=true
log4j.appender.logFile.File=D:/logs/log.log4j
log4j.appender.logFile.layout=org.apache.log4j.PatternLayout
log4j.appender.logFile.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# 回滚文件(rollingFile)
log4j.appender.rollingFile=org.apache.log4j.RollingFileAppender
log4j.appender.rollingFile.Threshold=DEBUG
log4j.appender.rollingFile.ImmediateFlush=true
log4j.appender.rollingFile.Append=true
log4j.appender.rollingFile.File=D:/logs/log.log4j
log4j.appender.rollingFile.MaxFileSize=200KB
log4j.appender.rollingFile.MaxBackupIndex=50
log4j.appender.rollingFile.layout=org.apache.log4j.PatternLayout
log4j.appender.rollingFile.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# 定期回滚日志文件(dailyFile)
log4j.appender.dailyFile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.dailyFile.Threshold=DEBUG
log4j.appender.dailyFile.ImmediateFlush=true
log4j.appender.dailyFile.Append=true
log4j.appender.dailyFile.File=D:/logs/log.log4j
log4j.appender.dailyFile.DatePattern='.'yyyy-MM-dd
log4j.appender.dailyFile.layout=org.apache.log4j.PatternLayout
log4j.appender.dailyFile.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# 应用于socket
log4j.appender.socket=org.apache.log4j.RollingFileAppender
log4j.appender.socket.RemoteHost=localhost
log4j.appender.socket.Port=5001
log4j.appender.socket.LocationInfo=true
# Set up for Log Factor 5
log4j.appender.socket.layout=org.apache.log4j.PatternLayout
log4j.appender.socket.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# Log Factor 5 Appender
log4j.appender.LF5_APPENDER=org.apache.log4j.lf5.LF5Appender
log4j.appender.LF5_APPENDER.MaxNumberOfRecords=2000
# 发送日志到指定邮件
log4j.appender.mail=org.apache.log4j.net.SMTPAppender
log4j.appender.mail.Threshold=FATAL
log4j.appender.mail.BufferSize=10
log4j.appender.mail.From = xxx@mail.com
log4j.appender.mail.SMTPHost=mail.com
log4j.appender.mail.Subject=Log4J Message
log4j.appender.mail.To= xxx@mail.com
log4j.appender.mail.layout=org.apache.log4j.PatternLayout
log4j.appender.mail.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
# 应用于数据库
log4j.appender.database=org.apache.log4j.jdbc.JDBCAppender
log4j.appender.database.URL=jdbc:mysql://localhost:3306/test
log4j.appender.database.driver=com.mysql.jdbc.Driver
log4j.appender.database.user=root
log4j.appender.database.password=
log4j.appender.database.sql=INSERT INTO LOG4J (Message) VALUES('=[%-5p] %d(%r) --> [%t] %l: %m %x %n')
log4j.appender.database.layout=org.apache.log4j.PatternLayout
log4j.appender.database.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n

# 自定义Appender
log4j.appender.im = net.cybercorlin.util.logger.appender.IMAppender
log4j.appender.im.host = mail.cybercorlin.net
log4j.appender.im.username = username
log4j.appender.im.password = password
log4j.appender.im.recipient = corlin@cybercorlin.net
log4j.appender.im.layout=org.apache.log4j.PatternLayout
log4j.appender.im.layout.ConversionPattern=[%-5p] %d(%r) --> [%t] %l: %m %x %n
```



### 

