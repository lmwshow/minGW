package JDK8_new.Lambda;

/**
 * @Auther: minGW
 * @Date: 2018/5/11 10:14
 * @Description: 使用lambda代替函数式接口Runnable
 */
public class ThreadTest {

    public static void main(String[] args){


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("minGw");
            }
        });

        thread.start();

        Thread lambdaThread = new Thread(()->{System.out.println("minGW");});
        lambdaThread.start();

    }
}
