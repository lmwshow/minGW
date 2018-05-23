package 内存泄漏;

/**
 * @Auther: minGW
 * @Date: 2018/5/22 17:10
 * @Description
 */

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity{

    protected void onCreate() {
        //一般写法
        Boradcast.addListener((Boradcast.MyListener) this);
        //防泄漏写法,封装成弱引用
        Boradcast.addListener(new NewListener(this));
    }

    public void callback() {
        //todo
    }

    protected void onDestroy() {
        //一般写法
        Boradcast.removeListener((Boradcast.MyListener) this);
    }

    public static class NewListener implements Boradcast.MyListener {
        private WeakReference<MainActivity> reference;

        public NewListener(MainActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void callback() {
            MainActivity activity = reference != null ? reference.get() : null;
            if (activity == null) {
                Boradcast.removeListener(this);
                return;
            }
        }
    }
}

class Boradcast {
    private static Queue<MyListener> tmp = new LinkedList<>();
    private static Queue<MyListener> listeners = new LinkedList<>();

    public static void addListener(MyListener listener) {
        listeners.add(listener);
    }

    public static void removeListener(MyListener listener) {
        listeners.remove(listener);
    }

    private static void dispatchListener() {
        tmp.addAll(listeners);
        for (MyListener listener : tmp) {
            listener.callback();
        }
        tmp.clear();
    }

    public interface MyListener {
        void callback();
    }
}
