package 线程池的实现.线程池的堆栈;

import java.util.concurrent.*;

/**
 * @Auther: minGW
 * @Date: 2018/7/30 09:36
 * @Description:
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());

        //使用submit,100/0 的异常会被吞掉，导致不知道程序哪里出问题
        /*
        1. 使用execute
        2. 使用submit + Future

        但是这样的异常堆只能告诉我们异常是在哪里抛出的，而无法知道这个任务到底是在哪里提交的！！
         */
//        for (int i = 0; i < 5; i++) {
//            pools.submit(new DivTask(100, i));
//            pools.execute(new DivTask(100,i));

//            Future tr = pools.submit(new DivTask(100, i));
//            tr.get();
//        }

        TraceThreadPoolExecutor executor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));

        }
    }
}
