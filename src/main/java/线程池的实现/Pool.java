package 线程池的实现;

/**
 * @Auther: minGW
 * @Date: 2018/5/9 20:24
 * @Description: 线程池接口
 */
public interface Pool<T> {

    //关闭线程池，会等待未完成任务的线程
    void shutdown();

    //关闭线程池，立即关闭，不会等待线程完成
    void stop();

    //执行任务
    void execute(Task task);

    //获取线程
    T getFromPool();

    //归还线程
    void returnToPool(T t);

    //获取线程池关闭状态
    public boolean isShutdown();

    //设置线程池关闭状态
    public void setShutdown(boolean isShutdown);
}
