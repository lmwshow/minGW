package 自动拆箱装箱;

/**
 * @Auther: minGW
 * @Date: 2018/8/8 16:04
 * @Description:
 */
public class Main {

    public static void main(String[] args){


        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c==d);
        //true, 在-128~127范围内
        System.out.println(e==f);
        //false
        System.out.println(c==(a+b));
        //true,算数运算，自动拆箱 a + b = 3 为int类型， 包装类和基本类 == 自动拆箱
        System.out.println(c.equals(a+b));
        //true,equals 为对象方法， a + b 计算出来int 3 后进行自动装箱会Integer
        System.out.println(g==(a+b));
        //true, 自动拆箱Long 变为long ，基本类型不同，强制类型转换，低往高转
        System.out.println(g.equals(a+b));
        //flase, int类型的3 自动装箱后为Integer类型，和Long equals 需要先判断类型
        System.out.println(g.equals(a+h));
        //true,  a + h 自动拆箱后，又强制类型转化为long 类型的3 ，再自动装箱成Long 3

        String s = new String("123");
    }
}
