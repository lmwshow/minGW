package JDK8_new.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: minGW
 * @Date: 2018/5/11 10:18
 * @Description: 断言函数式接口的使用，用于过滤列表
 */
public class PredicateTest {


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //Consumer接口的使用
        list.forEach((x)->System.out.println(x));
        //lambda表达式内可以使用方法引用，仅当该方法不修改lambda表达式提供的参数
        list.forEach(System.out::println);

        System.out.println("Print all numbers:");
        evaluate(list, (n)->true);

        System.out.println("Print no numbers:");
        evaluate(list, (n)->false);

        System.out.println("Print even numbers:");
        evaluate(list, (n)-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        evaluate(list, (n)-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n)-> n > 5 );
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list)  {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

}
