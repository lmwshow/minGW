package socket;

import 线程池的实现.ThreadPool;

import java.io.IOException;
import java.util.Random;

/**
 * @Auther: minGW
 * @Date: 2018/8/1 21:29
 * @Description:
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException {

        //启动客户端
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServiceNormal.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true)
                {
                    String express = "hello";
                    Client.send(express);
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
