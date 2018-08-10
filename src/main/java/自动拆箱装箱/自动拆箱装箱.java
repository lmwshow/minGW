package 自动拆箱装箱;

/**
 * @Auther: minGW
 * @Date: 2018/8/8 15:57
 * @Description:
 */
public class 自动拆箱装箱 {

    public static void main(String[] args)
    {
        //问题出在: 对null 进行了强制类型转换 (long)null
        /*
        总之三元表达式要避免左右类型不一致，以免编译器帮你自动包装。

        三元表达式会对类型自动转换，低转高
         */
//        Long a = (args.length == 0) ? getNull():1;


        Long a = (args.length == 0) ? getNull():Long.valueOf(1);


//        Long a = (args.length == 0) ? 1:getNull();
        //ok 1被三元运算符自动包装为Long，且这代码绕过了对null的封装
        //且1在编译的时候就编成long类型

        System.out.println("reach here");
    }

    private static Long getNull()
    {
        return null;
    }
}
