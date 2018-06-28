package Dubbo.SPI;

import Dubbo.SPI.service.IHelloService;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Auther: minGW
 * @Date: 2018/6/4 19:58
 * @Description: SPI 服务提供接口，通过接口找到接口的实现类
 *
 * ServiceLoader缺点分析

    虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现类全部加载并实例化一遍。如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费。

    获取某个实现类的方式不够灵活，只能通过Iterator形式获取，不能根据某个参数来获取对应的实现类
 */
public class SPITest {

    public static void main(String[] args){


        ServiceLoader<IHelloService> services = ServiceLoader.load(IHelloService.class);

        Iterator<IHelloService> iHelloServiceIterator = services.iterator();


        List<IHelloService> implClasses = SimpleServiceLoader.load(IHelloService.class);

        for (IHelloService cls : implClasses)
            cls.sayHello();

        while (iHelloServiceIterator.hasNext())
        {
            IHelloService service = iHelloServiceIterator.next();
            service.sayHello();
        }
    }
}
