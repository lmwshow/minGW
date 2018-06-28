package Dubbo.SPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: minGW
 * @Date: 2018/6/4 20:05
 * @Description: A very Simple JavaSPI implementation using java reflection
 *
 * 实质上就是通过，配置好某个路径文件，文件中写了接口的所有实现类的全限定名，通过反射加载类实例
 */
public class SimpleServiceLoader {

    private static final String PREFIX = "/META-INF/services/";

    public static <T> List<T> load(Class<T> cls)
    {
        List<String> implClasses = readServiceFile(cls);
        List<T> implList = new ArrayList<>();

        for (String implClass :implClasses)
        {
            Class<T> c =null;

            try {
                c = (Class<T>) Class.forName(implClass);
                implList.add(c.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return implList;
    }

    private static <T> List<String> readServiceFile(Class<T> cls) {

        String infName = cls.getCanonicalName();
        System.out.println("infName:" + infName);

        String fileName = cls.getResource(PREFIX + infName).getPath();
        System.out.println(fileName);

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String line = "";
            List<String> implClasses = new ArrayList<>();

            while ((line = br.readLine())!=null)
            {
                implClasses.add(line);
            }

            return implClasses;
        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + fileName);
            return new ArrayList<>();

        } catch (IOException e) {

            System.out.println("Read file failed: " + fileName);
            return new ArrayList<>();
        }

    }
}
