package 代理.JDK动态代理;

/*
    实际对象
 */
public class RealSubject implements Subject {

    public String SayHello(String name) {
        return "hello" + name;
    }

    public String SayGoodBys() {
        return "good bye";
    }
}
