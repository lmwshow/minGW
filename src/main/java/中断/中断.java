package 中断;

/**
 * @Auther: minGW
 * @Date: 2018/7/27 22:18
 * @Description:
 */
public class 中断 {

    public static void main(String[] args){

        double f = 7.0/4;
        System.out.println(f);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted())
                {
                    System.out.println("done!");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().interrupted());
                        e.printStackTrace();
                        try {
                            throw e;
                        } catch (InterruptedException e1) {
                            System.out.println("catch");

                            e1.printStackTrace();
                        }

                        System.out.println("f");
                        //重新设置标记位，就代表要结束线程
                        Thread.currentThread().interrupt();
                    }finally {
                        System.out.println("final");
                    }
                }
            }
        });

        t1.start();

        //中断，如果线程内部不判断中断标记，就无法获得中断消息，那就可以一直运行。 如果在可中断的阻塞调用下，阻塞函数会抛出异常，并清空标记位。 我们获取异常后，可以自己决定怎么继续
        t1.interrupt();



    }
}
