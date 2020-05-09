```sell
# 远程复制到本地
scp root@47.89.242.212:/home/skyx_client/taskClient.jar /Users/admin/Desktop
```

```
# 断点续传
rsync -P --rsh=ssh -r /Users/admin/Desktop/shop/ root@catoy.top:/home/scp/test
```

