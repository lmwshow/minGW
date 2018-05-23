package 内存泄漏;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.WeakHashMap;

/**
 * @Auther: minGW
 * @Date: 2018/5/22 16:21
 * @Description
 *
 * 严格意义上说Java是没有真正的内存泄漏的，这些泄漏和我们看不见的框架内部息息相关，所以养成良好的编程习惯，理解常用框架的架构原理，就能避免这些错误
 */
public class WeakHashMapTest {

    public static void main(String[] args){

        new WeakHashMapTest();
    }

    public WeakHashMapTest()
    {
        final WeakHashMap<Object, Object> map = new WeakHashMap<Object, Object>();
        final Object o1 = new Object();
        final WeakReference reference1 = new WeakReference<Object>(o1);
        Object o2 = new Object();
        final WeakReference reference2 = new WeakReference<Object>(o2);
        map.put(o1, o2);
        o2 = null;
        System.gc();
        //o2虽然置为Null,但是仍然被map引用着，出现泄漏
        System.out.println((reference1.get() == null) + " " + (reference2.get() == null));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                map.toString();
                System.gc();
                System.out.println((reference1.get() == null) + " " + (reference2.get() == null));
            }
        }, 0);
    }
}

class Expand {
    private static WeakHashMap<WeakHashMapTest, HashMap<String, Object>> map = new WeakHashMap<>();

    public static Object getFiledX(String key, WeakHashMapTest main) {
        HashMap<String, Object> m = map.get(main);
        return m == null ? null : m.get(key);
    }

    public static void setFiledX(String key, WeakHashMapTest main, Object o) {
        HashMap<String, Object> m = map.get(main);
        if (m == null) {
            m = new HashMap<>();
            map.put(main, m);
        }
        m.put(key, o);
    }
}
