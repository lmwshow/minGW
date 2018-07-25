package 代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKDynamicProxyDemonstration {

    public static void main(String[] args){

        //代理的真实对象
        Subject realSubject = new RealSubject();

        /*
            InvocationHandlerImpl实现了InvocationHandler接口，  他的作用是能实现方法调用 从代理类到委托类的分派转发
            其内部通常包含指向委托类实例的应用，用于真正执行分派转发过来的方法调用

            而真正的代理类 是Proxy.newProxyInstance 生成的， 它通过三个参数，实现了委托类所有接口，通过handle 从中进行分派转发
         */
        InvocationHandler handler = new InvocationHandlerImpl(realSubject);

        ClassLoader handlerloader = handler.getClass().getClassLoader();
        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();


        Subject subject = (Subject) Proxy.newProxyInstance(handlerloader,interfaces,handler);

        System.out.println("动态代理对象的类型：" + subject.getClass().getName());

        String hello = subject.SayHello("minGW");
        System.out.println(hello);

        subject = (Subject) Proxy.newProxyInstance(loader,interfaces,handler);

        System.out.println("动态代理对象的类型：" + subject.getClass().getName());

        hello = subject.SayHello("minGW");
        System.out.println(hello);

    }
}
