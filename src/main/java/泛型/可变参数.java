package 泛型;

/**
 * @Auther: minGW
 * @Date: 2018/6/27 14:52
 * @Description: https://www.jb51.net/article/93949.htm
 *
 * 本质是一个数组
 */
public class 可变参数 {

    //“int[]”和“int…”没有什么不同，只是“int…”是一种新的定义数组形参的方式罢了
    public static void solver(int... a)
    {
        for (int x : a)
            System.out.println(x);
    }


    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5};
        solver(nums);
        //更方便调用
        solver(1);
        solver(1,2);

    }
}
