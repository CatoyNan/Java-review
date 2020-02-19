## curl

- -i 显示头信息

- -v 显示通信过程

- -X 指定请求方式

- -x 代理

- -o 保持到文件

- 发送post请求传递json

  ```shell
  curl -H "Content-Type:application/json" -X POST --data '[{"index":["*"],"preference":"1503652289983","ignore_unavailable":"true"},{"sort":[{"timestamp":{"order":"desc"}}],"query":{"must_not":[],"bool":{"must":[{"query_string":{"query":"cluster"}},{"range":{"timestamp":{"gte":"1503667558137","lte":"1503667558137"}}}]}},"from":"0","size":"500","version":"true"}]' http://212.35.247.184:18080
  ```

eg:

```
curl -x http://localhost:1080 --cookie "i10c.bdddb=c2-119bfSEMp1bKx6BhZ9zGrmQGR2PtzKYWzrWP40DM14VxTTQKXb2LmWFGRcPsRbR4jjRLvymGueNwYWQhUAzGryNGR2PtzQWa7oMLasEL6GJsUTQKTV4qmWFTexUsMQ6Zvo1G0xbcKyOsPZxkPaeBrWjWM2UnSxWR0OML07tAgn0nUYKn2YzGRRFLoCGkCGTW0jSt1sEvp3OFj6FRPa4Bs4IJS8bnRzTW08ZG0x9MS1Js4TPmptzGrRGtT8Ps1KYWMZk7YY9LuyPQSTPMPa4fTMALRxVQMU7R0orVPDbmp3OnV6KmUb6Br9GLM2UnRzTW04lZvxEGvbJsUbOhUD6EmWFGRcPsRiorIjRLvymGu3NwPY2lYV4GmbqGRcPsRiK4bQ2G0x9MS1Js4TPmukzGrRGtM2UnUyXZ1sMLawHQuyOSTcNqPaeFvXLGRcTwVVTWanVS4sEvt8OxPYzlZdABr6EQX9Ps1OeWzjUwvxEGudJsU0fhUazHPcESSxUSMPYxpgHCvxEGvbMnU8KmUknwREALRxVQPKY6voRkAsELp4wqPYzhUaTdmWFGSaPsRKYXvp0G04GGu3Jx5" https://www.katespade.com/on/demandware.store/Sites-Shop-Site/en_US/Product-Variation\?pid\=PXRUA186-1\&dwvar_PXRUA186-1_color\=312\&dwvar_PXRUA186-1_size\=U\&Quantity\=1\&format\=ajax\&instart_disable_injection\=true
```

http://www.ruanyifeng.com/blog/2019/09/curl-reference.html