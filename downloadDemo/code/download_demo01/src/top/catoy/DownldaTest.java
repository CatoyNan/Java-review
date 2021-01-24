package top.catoy;

import java.util.concurrent.CountDownLatch;

public class DownldaTest {
    public static boolean download(String remoteUrl,String localUrl){
        boolean bd=true;
        int threadSize = 6;
        String serverPath = remoteUrl;
        String localPath = localUrl;
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(threadSize);
        MutiThreadDownLoad m = new MutiThreadDownLoad(threadSize, serverPath, localPath, latch);
        try {
            boolean x = m.executeDownLoad();
            //如果文件的长度等于0,则直接跳过等待,不提示错误
            if (x){
                latch.await();
            }else{
                bd=false;//下载失败
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("全部下载结束,共耗时" + (endTime - startTime) / 1000 + "s");
        return bd;
    }

    public static void main(String[] args) {
        DownldaTest downldaTest = new DownldaTest();
        String urlss="http://mirrors.163.com/debian/ls-lR.gz";//要下载的网络资源路径

        String urltt=".\\download";//要存放的本地路径

        downldaTest.download(urlss,urltt);
    }
}
