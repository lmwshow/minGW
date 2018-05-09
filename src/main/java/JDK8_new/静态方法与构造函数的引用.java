package JDK8_new;

/**
 * 类似于引入C++中 函数指针的概念
 */
public class 静态方法与构造函数的引用 {

    public 静态方法与构造函数的引用(String name) {
        System.out.println(name);
    }

    /**
     * 指向某个Function的方法指针  Function为函数式接口
     * @param <F> 传值类型
     * @param <T> 结果类型
     */
    @FunctionalInterface
    interface Fun<F,T>{
        T run(F from);
    }


    @FunctionalInterface
    interface MainFactory<M extends 静态方法与构造函数的引用>
    {
        M run(String name);
    }

    public static String myMethod(String arg)
    {
        return arg;
    }

    public static void main(String[] args){

        Fun<String,String> fun = 静态方法与构造函数的引用::myMethod;
        String res = fun.run("This is arg");
        System.out.println(res);

        MainFactory mainFactory = 静态方法与构造函数的引用::new;
        mainFactory.run("minGW");
    }
}
