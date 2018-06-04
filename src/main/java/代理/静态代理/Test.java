package 代理.静态代理;

/**
 * @Auther: minGW
 * @Date: 2018/6/1 16:18
 * @Description:
 */
public class Test {

    public static void main(String[] args){

        Subject subject = new RealSubject();
        SubjectProxy proxy = new SubjectProxy(subject);

        System.out.println(proxy.SayHello("minGW"));
    }
}
