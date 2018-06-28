package JVM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: minGW
 * @Date: 2018/6/5 18:02
 * @Description: Btrace插件跟踪
 */
public class BTraceTest {


    /*
     //BTrace Script Template
    import com.sun.btrace.annotations.*;
    import static com.sun.btrace.BTraceUtils.*;
        @BTrace
        public class TracingScript {
            //put your code here
            @OnMethod(clazz="JVM.BTraceTest",method="add",location=@Location(Kind.RETURN))
            public static void func(@Self JVM.BTraceTest instance ,int a,int b,@Return int result){
                println("调用堆栈");
                jstack();
                println(strcat("方法参数A：",str(a)));
                println(strcat("方法参数B：",str(b)));
                println(strcat("方法结果：",str(result)));

            }
        }
     */

    public int add(int a,int b)
    {
        return a + b;
    }

    public static void main(String[] args) throws IOException {

        BTraceTest test = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0;i < 10; i++)
        {
            reader.readLine();
            int a = (int)Math.round(Math.random()*1000);
            int b = (int)Math.round(Math.random()*1000);

            System.out.println(test.add(a,b));
        }
    }
}
