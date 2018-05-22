package JDK8_new.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lambda {

    public static void main(String[] args){


        Integer[] nums = new Integer[]{2,3,2,1,3,4,9};
        List<Integer> list = new ArrayList<>(Arrays.asList(nums));

        Collections.sort(list,(num1,num2) -> (num2-num1));

        Arrays.sort(nums,(num1,num2) -> (num2-num1));
        for (int x : list)
            System.out.println(x);
    }
}
