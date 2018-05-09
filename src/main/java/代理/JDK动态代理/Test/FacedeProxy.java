package 代理.JDK动态代理.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: minGW
 * @Date: 2018/4/20 00:20
 * @Description
 */
class FacadeProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("接口方法调用开始");
        //执行方法
        System.out.println("method toGenericString:"+method.toGenericString());
        System.out.println("method name:"+method.getName());
        System.out.println("method args:"+(String)args[0]);
        System.out.println("接口方法调用结束");
        return "调用返回值";
    }

    public static <T> T newMapperProxy(Class<T> mapperInterface) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};
        FacadeProxy proxy = new FacadeProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}