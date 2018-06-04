package 代理.静态代理;

/**
 * @Auther: minGW
 * @Date: 2018/6/1 16:13
 * @Description:
 */
public class SubjectProxy {

    private Subject subject;

    public SubjectProxy(Subject subject) {
        this.subject = subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    public String SayHello(String name){
        System.out.println("before");

        String ans = subject.SayHello(name);

        System.out.println("after");

        return ans;
    }

    public String SayGoodBys() {
        System.out.println("before");

        String ans = subject.SayGoodBys();

        System.out.println("after");

        return ans;
    }

}
