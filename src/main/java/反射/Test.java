package 反射;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Auther: minGW
 * @Date: 2018/7/19 16:21
 * @Description:
 * 在Java 8之前的版本，代码编译为class文件后，方法参数的类型是固定的，但参数名称却丢失了，这和动态语言严重依赖参数名称形成了鲜明对比。
 *
 * 现在，Java 8开始在class文件中保留参数名，给反射带来了极大的便利。jdk8增加了类Parameter
 *
 * 如果编译级别低于1.8，得到的参数名称是无意义的arg0、arg1……
 * 遗憾的是，保留参数名这一选项由编译开关javac -parameters打开，默认是关闭的。
 * 注意此功能必须把代码编译成1.8版本的class才行。
 */
public class Test {

    class Hello{

        public void sayHello(String name)
        {
            System.out.println("hello:" + name);
        }

    }

    public static void main(String[] args){

        Class clazz = Hello.class;
        Method[] methods = clazz.getMethods();

        for (Method method : methods)
        {
            if (method.getName().equals("sayHello")) {
                System.out.println(method.getName());

                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters)
                    System.out.println(parameter.getName());


            }
        }
    }
}
