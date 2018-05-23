package 内存泄漏;

/**
 * @Auther: minGW
 * @Date: 2018/5/22 16:37
 * @Description
 */

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        new Main().init();                      //调用Main的init方法
    }

    public Main() {
    }

    public void init() {
        Listener listener = new Listener() {        //创建Listener实例，重写create方法， Listener的构造函数中会调用create方法
            @Override
            public void create() {
                super.create();
                try {
                    getThis$0(this, "this$0");
                } catch (Exception e) {
                }

                System.out.println(this);
                Field[] field = Main.class.getDeclaredFields();
                System.out.println(field.length);

                for (Field f : field)
                    System.out.println(f.getName());

                Main.this.func();
            }
        };
    }

    public void func() {
        System.out.println("native");
    }

    public static Object getThis$0(Object object, String name) throws Exception {
        Field field = object.getClass().getDeclaredField(name);
        field.setAccessible(true);
        Object this0 = field.get(object);
        final WeakReference reference = new WeakReference(this0);
        field.set(object, new Main() {
            @Override
            public void func() {
                Field[] fields = this.getClass().getDeclaredFields();
                for (Field field1 : fields) {
                    System.out.println(field1.getName());
                }
                System.out.println("hook");
                System.gc();
                Main main = (Main) reference.get();
                main.func();
            }
        });
        return this0;
    }

    public static class Listener {
        public Listener() {
            create();
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    create();
//                }
//            }, 0);
        }

        public void create() {
        }
    }
}
