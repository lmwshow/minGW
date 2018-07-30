package 线程池的实现.线程池的堆栈;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: minGW
 * @Date: 2018/7/30 09:49
 * @Description:
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    //封装成新的Runnable
    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task,clientTrace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,clientTrace(),Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("client stack trace");
    }

    //使用装饰模式，加入异常打印
    private Runnable wrap(final Runnable task , final Exception clientStack,String clientThreadName)
    {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                }catch (Exception e)
                {
                    clientStack.printStackTrace();
                    throw e;
                }
            }
        };
    }
}
