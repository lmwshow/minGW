package 代理.静态代理;

/**
 * @Auther: minGW
 * @Date: 2018/6/1 16:10
 * @Description:
 */
public class RealSubject implements Subject {

    public String SayHello(String name) {
        return "hello" + name;
    }

    public String SayGoodBys() {
        return "good bye";
    }
}
