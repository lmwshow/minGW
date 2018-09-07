package 字符串;

/**
 * @Auther: minGW
 * @Date: 2018/8/20 15:07
 * @Description:
 */
public class StringTest {

    public static void main(String[] args){


        //用双引号，新建字符串对象，在字符串常量池中生成"abc"
        String a = "abc";

        //使用new生成，分为new String() 和 “de” 两个对象。
        //首先查看常量池中是否存在"de"字符串，如果存在则不生成
        String b = new String("de");


        //对于表达式来说分为两种情况，编译时确定和运行时确定

        //编译时确定 c = "ababc" 在字符串常量池中生成,如果变量是由final修饰，也能编译时确定
        String c = "ab" + "abc";

        //运行时确定，因为存在变量a， 此时在堆中生成，实际上是通过 new StringBuilder -> append -> toString 获得堆中字符串对象的
        //同时 表达式中的"ab" 会检查常量池中是否存在，进行生成。
        //如果 d = “xx” + "yy" + a + "mm" + "nn" 进行编译时优化
        //对于 + 进行字符串连接时, 会将字符串尽可能多的连接起来，此时字符串常量池中有"xxyy","mmnn"
        String d = "xx" + "yy" + a + "mm" + "nn";

        String e = new String("mm") + new String("nn");
        e.intern();

        String f = "mmnn";

        System.out.println(e == f);
    }
}
