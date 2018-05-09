package 代理.JDK动态代理.Test;

/**
 * @Auther: minGW
 * @Date: 2018/4/20 00:22
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        IHello hello = FacadeProxy.newMapperProxy(IHello.class);
        System.out.println(hello.say("hello world"));
    }
}