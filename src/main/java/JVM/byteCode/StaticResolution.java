package JVM.byteCode;

/**
 * @Auther: minGW
 * @Date: 2018/7/17 16:36
 * @Description:
 */
public class StaticResolution {

    public static void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {

        StaticResolution.sayHello();
        int x = 3;
        switch (x) {
            case 5:
                System.out.println(5);
                break;
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println(0);
        }
    }


}
