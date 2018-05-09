package 代理.cglib代理;


/*
    使用cglib代理,并不要求委托类必须实现接口，底层采用asm字节码生成框架生成代理类的字节码
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//实现MethodInterceptor接口，定义方法的拦截器，利用Enhancer类生成代理类
public class BookFacadeCglib implements MethodInterceptor{

    private Object target;

    //创建对象
    public Object getInstance(Object target)
    {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());

        //回调方法
        enhancer.setCallback(this);

        return enhancer.create();
    }

    //回调方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("before");

        methodProxy.invokeSuper(obj,args);

        System.out.println("after");

        return null;
    }



}
