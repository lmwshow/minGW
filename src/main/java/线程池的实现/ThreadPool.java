package 线程池的实现;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: minGW
 * @Date: 2018/5/9 20:58
 * @Description: 线程池
 */
public class ThreadPool implements Pool<WorkThread>{


    //默认线程池的线程数
    private static final int DEFAULT_SIZE = 5;

    //线程池中的空闲线程，用队列表示
    private BlockingDeque<WorkThread> idleThreads;

    //设置线程池是否关闭的标志
    private volatile boolean isShutdown =false;

    //存放线程池中的所有线程，不管是否在执行任务
    private static Set<Thread> threadSet = new HashSet<>();

    //日志组件
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    public ThreadPool()
    {
        this(DEFAULT_SIZE);
    }

    public ThreadPool(int threadSize)
    {
        this.idleThreads = new LinkedBlockingDeque<>(threadSize);
        for(int i=0; i<threadSize; i++){
            WorkThread thread = new WorkThread(this);
            thread.setName("ThreadPool-"+i);
            thread.start();
            idleThreads.add(thread);
            threadSet.add(thread);
        }
    }


    @Override
    public void shutdown() {

        //这里只关闭空闲的线程,所以只遍历idleThreads
        setShutdown(true);
        int threadSize = idleThreads.size();
        logger.debug("threadPool all has {} thread.", threadSize);
        threadSize = idleThreads.size();
        logger.debug("closing pool........");
        for(int i=0; i<threadSize; i++){
            WorkThread thread = getFromPool();
            thread.setTask(null);
            thread.setIdleLocal(null);
            thread.close();
        }
        idleThreads.clear();
        threadSet.clear();

    }

    @Override
    public void stop() {
        //关闭所有线程，遍历threadSet
        logger.info("pool is shutdown now!!!");
        setShutdown(true);
        for(Thread thread : threadSet){
            if(!thread.isInterrupted()){
                logger.info("{} will be interrupt.", thread.getName());
                thread.interrupt();
            }
        }
    }

    @Override
    public void execute(Task task) {
        WorkThread thread = getFromPool();
        logger.debug("I will set the task|{} soon...", task);
        thread.setTask(task);
    }

    @Override
    public WorkThread getFromPool() {
        WorkThread thread = null;
        try {
            logger.debug("get thread from pool, pool all has {} threads .", idleThreads.size());
            thread = idleThreads.take();
            logger.debug("thread {} get from pool, pool all has {} threads", thread.getName(), idleThreads.size());
        } catch (InterruptedException e) {
            logger.error("get from pool error",e);
        }
        return thread;
    }

    @Override
    public void returnToPool(WorkThread workThread) {
        try {
            logger.debug("thread {} return to pool", workThread.getName());
            idleThreads.offer(workThread, 1L, TimeUnit.SECONDS);
            logger.debug("thread {} return to pool, pool all has {} threads", workThread.getName(), idleThreads.size());
        } catch (InterruptedException e) {
            logger.error("thread {} return to pool error ", workThread.getName(), e);
        }
    }


    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    @Override
    public void setShutdown(boolean isShutdown) {
        this.isShutdown = isShutdown;
    }
}
