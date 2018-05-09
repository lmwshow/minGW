package 线程池的实现;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: minGW
 * @Date: 2018/5/9 20:43
 * @Description: 工作线程
 *
 * 线程池中工作线程与通常线程不同之处在于run方法的不同。
 * 通常的线程，完成run方法之后，自然退出，线程结束。虚拟机在线程结束后回收分配给线程的资源，线程对象被垃圾回收。
 * 而这在池化的工作者线程中应该要避免的，否则线程池就失去了意义。
 * 作为可以被放入池中并可以重新利用的工作线程，它的run()方法不应该结束。
 *
 * 所以，线程池中工作线程run方法执行完任务后，将被回收到池中，然后调用wait方法，阻塞等待，而不是退出循环和run()
 * 这就是实现线程池的核心要点
 */
public class WorkThread extends Thread{

    //线程的任务
    private Task task;
    //线程所在的线程池
    private ThreadPool pool;

    //ThreadLocal 线程变量。是一个以ThreadLocal为健，任意对象为值的存储结构，相当于绑定在线程上的值
    //这里表示是否空闲
    private ThreadLocal<Boolean> idleLocal = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return true;
        }
    };


    //日志组件
    private static final Logger logger = LoggerFactory.getLogger(WorkThread.class);

    public WorkThread(ThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        while (idleLocal!=null && idleLocal.get()){
            logger.debug("{} my task is |{}",this.getName(),task);

            if (task!=null)
            {
                try {
                    idleLocal.set(false);
                    logger.debug("{} will be execute task",this.getName());
                    task.doSomething();
                    synchronized (this)
                    {
                        //归还Thread
                        pool.returnToPool(this);
                        if (pool.isShutdown())
                        {
                            logger.debug("find thread pool shutdown ,I am going die ......");
                            break;
                        }
                        logger.debug("{} do the work and will be wait",this.getName());
                        this.wait();
                        logger.debug("{} is notifyed by other",this.getName());
                    }

                    if (idleLocal!=null)
                        idleLocal.set(true);

                }catch (Exception e)
                {
                    logger.error("thread|{} execute task error",this.getName(),e);
                }
            }

        }

    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        logger.debug("{} set the task|{} now ...",this.getName(),task);
        this.task = task;
        synchronized (this){
            logger.debug("{} set the task synchronized ...",this.getName());
            this.notifyAll();
        }
    }

    public ThreadLocal<Boolean> getIdleLocal() {
        return idleLocal;
    }

    public void setIdleLocal(ThreadLocal<Boolean> idleLocal) {
        this.idleLocal = idleLocal;
    }


    public void close()
    {
        synchronized (this)
        {
            this.notifyAll();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
