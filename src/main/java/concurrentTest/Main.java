package concurrentTest;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Auther: minGW
 * @Date: 2018/7/31 08:18
 * @Description: concurrentlinkedhashmap  https://www.cnblogs.com/yhlx/articles/3069247.html
 *
 * 本身是对ConcurrentHashMap的封装，可以用来实现一个基于LRU策略的缓存
 */
public class Main {

    public static void main(String[] args) throws IOException {

        ConcurrentLinkedHashMap map = new ConcurrentLinkedHashMap.Builder<Integer,Integer>()
                .maximumWeightedCapacity(2).weigher(Weighers.singleton()).build();

        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        map.put(3, 3);
        System.out.println(map.get(1));//null 已经失效了
        System.out.println(map.get(2));


    }
}
