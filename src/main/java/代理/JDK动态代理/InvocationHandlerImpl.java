package 代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
   调用处理器实现类
   每次生成动态代理对象时都需要指定一个实现了该接口的调用处理器对象

   这只是个调用处理器，不是代理类。
   代理类调用方法时，通过调用处理器执行方法，然后加入before和after操作
 */

public class InvocationHandlerImpl implements InvocationHandler {

    //要代理的实际对象
    private Object subject;

    //构造方法，给我们要代理的真实对象赋初值
    public InvocationHandlerImpl(Object subject) {
        this.subject = subject;
    }

    /*
    该方法负责集中处理动态代理类上的所有方法调用
    调用处理器根据者桑参数进行预处理或分派到委托类实例上反射执行
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("在调用之前，我要干点啥呢？");

        System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法是，其自动会跳到代理对象锁关联的handler对象的invoke方法来进行调用
        Object returnValue = method.invoke(subject,args);

        System.out.println("在调用之后，我要干点啥呢？");

        return returnValue;
    }
}
