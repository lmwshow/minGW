package 泛型;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: minGW
 * @Date: 2018/6/6 19:10
 * @Description:
 */
public class Main {

    static class Test {
        int value = 1;

        public Test(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            return this.hashCode() == obj.hashCode();
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }



    public static void main(String[] args) throws InterruptedException {


        Test t = new Test(2);

        Test t1 = new Test(3);
        System.out.println(t==t1);
        System.out.println(t.equals(t1));

        Thread.sleep(100);

        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings,new Integer(42));
        String s = strings.get(0);


    }

    //使用原生态会绕过安全监测，逃避了泛型检查
    private static void unsafeAdd(List strings, Object o) {
        strings.add(o);
    }

    //无限制的通配符类型则是安全的
    //由于可以将任意元素放入原生态类型的集合中，很容易破坏类型约束条件
    //所以禁止将任何元素(除了null)加入到list中，以免破坏类型约束条件
    private static void unsafeAdd1(List<?> strings, Object o) {
//        strings.add(o);
        strings.add(null);
    }
}
