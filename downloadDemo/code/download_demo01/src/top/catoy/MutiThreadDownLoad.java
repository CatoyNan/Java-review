package top.catoy;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class MutiThreadDownLoad {
    // 同时下载的线程数
    private int threadCount;
    // 服务器请求路径
    private String serverPath;
    //记录下载完成的次数
    public static int number_thread=50;
    //本地路径
    private String localPath;
    //线程计数同步辅助
    private CountDownLatch latch;
 
    public MutiThreadDownLoad(int threadCount, String serverPath, String localPath, CountDownLatch latch) {
        this.number_thread=threadCount;
        this.threadCount = threadCount;
        this.serverPath = serverPath;
        this.localPath = localPath;
        this.latch = latch;
    }

 
    public boolean executeDownLoad() {
        try {
            URL url = new URL(serverPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                //服务器返回的数据的长度，实际上就是文件的长度,单位是字节
                int length = conn.getContentLength();
                System.out.println("文件总长度:" + length + "字节(B)");
                if (length == 0) {
                    return false;
                }

                RandomAccessFile raf = new RandomAccessFile(localPath, "rwd");
                //指定创建的文件的长度
                raf.setLength(length);
                raf.close();
                //分割文件
                int blockSize = length / threadCount;
                for (int threadId = 1; threadId <= threadCount; threadId++) {
                    //第一个线程下载的开始位置
                    int startIndex = (threadId - 1) * blockSize;
                    int endIndex = startIndex + blockSize - 1;
                    if (threadId == threadCount) {
                        //最后一个线程下载的长度稍微长一点
                        endIndex = length;
                    }

                    System.out.println("线程" + threadId + "下载:" + startIndex + "字节~" + endIndex + "字节");
                    new DownLoadThread(threadId, startIndex, endIndex).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return true;
    }


    /**

     * 内部类用于实现下载

     */

    public class DownLoadThread extends Thread {
        //线程ID
        private int threadId;
        //下载起始位置
        private int startIndex;
        //下载结束位置
        private int endIndex;

        public DownLoadThread(int threadId, int startIndex, int endIndex) {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            down_time();
        }

 

        /**

         * 如果超时,或者下载失败,就使用递归重新下载

         */

        public void down_time(){
            try {
                System.out.println("线程" + threadId + "正在下载...");
                URL url = new URL(serverPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //请求服务器下载部分的文件的指定位置
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                int code = conn.getResponseCode();
                System.out.println("线程" + threadId + "请求返回code=" + code);
                InputStream is = conn.getInputStream();//返回资源
                RandomAccessFile raf = new RandomAccessFile(localPath, "rwd");
                //随机写文件的时候从哪个位置开始写
                raf.seek(startIndex);//定位文件
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                }
                is.close();
                raf.close();
                number_thread=number_thread-1;
                System.out.println("线程" + threadId + "下载完毕  "+number_thread);
                //计数值减一
                latch.countDown();
            } catch (Exception e) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa").format(new Date())+":线程 "+threadId + "访问超时,重新下载!!");
                down_time();
            }
        }
    }
}