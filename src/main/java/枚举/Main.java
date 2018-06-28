import java.awt.Image;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.crypto.spec.IvParameterSpec;

import java.util.*;
public class Main {

    public static void main(String[] args) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        double total1 = 0;
        int sum = 0;

        for(int i = 0;i<n;i++){
            int p = in.nextInt();
            int d = in.nextInt();
            sum+= p;
            if(d == 1) total1 += p *0.8;
            else total1 += p;
        }
        double min = total1;
        for(int i = 0;i<m;i++){
            int man = in.nextInt();
            int jian = in.nextInt();
            if(sum >= man && sum - jian < min){
                min = sum - jian;
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(min));

    }
}