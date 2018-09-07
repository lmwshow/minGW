package 内部类;

/**
 * @Auther: minGW
 * @Date: 2018/8/17 13:06
 * @Description:
 */
public class Out {

    private static int a;
    private int b ;

    public static class Inner{

        //静态内部类只能访问外部类所有的静态变量和方法，不能访问非静态变量
        public void print()
        {
            System.out.println(a);
        }
    }
}
