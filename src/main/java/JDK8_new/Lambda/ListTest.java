package JDK8_new.Lambda;

import org.apache.log4j.helpers.ThreadLocalMap;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Auther: minGW
 * @Date: 2018/5/11 10:31
 * @Description: 遍历列表
 */
public class ListTest {


    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //Consumer接口的使用
        list.forEach((x) -> System.out.println(x));
        //lambda表达式内可以使用方法引用，仅当该方法不修改lambda表达式提供的参数
        list.forEach(System.out::println);



        //在 Java 8 使用 Stream，代码更加简洁易读；而且使用并发模式，程序执行速度更快。
        long start = System.currentTimeMillis();
        list.stream().filter((x) -> x > 3).sorted((x, y) -> y - x).collect(Collectors.toList());
        long end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");

        start = System.currentTimeMillis();
        list.parallelStream().filter((x) -> x > 3).sorted((x, y) -> y - x).collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

    }

}
