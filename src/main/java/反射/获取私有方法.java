package 反射;

import java.lang.reflect.Field;

/**
 * @Auther: minGW
 * @Date: 2018/8/6 09:09
 * @Description: 获取私有属性需要调用getDeclaredFields,getFields只能获取公有
 */
public class 获取私有方法 {

    static class Hello{

        private String name = "minGW";

        public void sayHello(String name)
        {
            System.out.println("hello:" + name);
        }

        public Hello() {
        }
    }

    public static void main(String[] args) throws IllegalAccessException {

        Hello hello = new Hello();
        Class clazz = hello.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields)
        {
            System.out.println(field.getName());
            System.out.println(field.get(hello));

            if (field.getName().equals("name"))
            {
                //访问私有属性，首先要先设置可以访问
                field.setAccessible(true);
                //设置属性，需要对对应目标进行设置
                field.set(hello,"uxi");
                System.out.println(field.get(hello));
            }
        }
    }

}
