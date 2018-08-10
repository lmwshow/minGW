package 代理.cglib代理;


/*
    使用cglib代理,并不要求委托类必须实现接口，底层采用asm字节码生成框架生成代理类的字节码

    Cglib动态代理执行代理方法效率之所以比JDK的高是因为Cglib采用了FastClass机制，
    它的原理简单来说就是：为代理类和被代理类各生成一个Class，
    这个Class会为代理类或被代理类的方法分配一个index(int类型)。
    这个index当做一个入参，FastClass就可以直接定位要调用的方法直接进行调用，
    这样省去了反射调用，所以调用效率比JDK动态代理通过反射调用高。
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//实现MethodInterceptor接口，定义方法的拦截器，利用Enhancer类生成代理类
/*
    通过代理类的源码可以看到，代理类会获得所有在父类继承来的方法，并且会有MethodProxy与之对应
    对于所有继承过来的方法，代理类中除了有重写的该方法，还会有MethodProxy与之对应
    比如 Method CGLIB$addBook$0$Method、MethodProxy CGLIB$addBook$0$Proxy;
 */

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

    /*
    回调方法
    在CGLIB中，方法的调用并不是通过反射来完成的，
    而是直接对方法进行调用：FastClass对Class对象进行特别的处理，
    比如将会用数组保存method的引用，每次调用方法的时候都是通过一个index下标来保持对方法的引用。
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("before");

        methodProxy.invokeSuper(obj,args);

        System.out.println("after");

        return null;
    }



}
