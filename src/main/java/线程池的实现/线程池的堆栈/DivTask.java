package 线程池的实现.线程池的堆栈;

/**
 * @Auther: minGW
 * @Date: 2018/7/30 09:34
 * @Description:
 */

public class DivTask implements Runnable{

    int a, b;

    public DivTask(int a, int b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public void run() {
        double re = a/b;

        System.out.println(re);
    }
}
