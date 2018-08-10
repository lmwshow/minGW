package 代理.cglib代理;

/*
    这是一个没有实现接口的实现类
    CGLIB 生成的代理类是继承自委托类的
    如果委托类被final修饰，那么它不可被继承，即不可被代理；
    同样，如果委托类中存在final修饰的方法，那么该方法也不可被代理；
 */
public class BookFacadeImpl1 {

    public void addBook()
    {
        System.out.println("add Book!");

    }

    public final void removeBook()
    {
        System.out.println("remove Book");
    }
}
