package 线程池的实现;

import java.util.concurrent.*;

/**
 * @Auther: minGW
 * @Date: 2018/5/11 14:45
 * @Description: 自定义拒绝策略
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {

        MyTask task = new MyTask();
//        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(10),
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        Thread t = new Thread(r);
//                        t.setDaemon(true);
//                        System.out.println("create " + t);
//                        return t;
//                    }
//                },
//                new RejectedExecutionHandler() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        System.out.println(r.toString() + "is discard");
//                    }
//                });


        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                (r, executor) -> {
                    System.out.println(r.toString() + "is discard");
                }) {


            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成");            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");            }
        };


        //(r,executor)->{System.out.println(r.toString() + "is discard");}
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            es.submit(task);
            Thread.sleep(10);
        }

    }
}
