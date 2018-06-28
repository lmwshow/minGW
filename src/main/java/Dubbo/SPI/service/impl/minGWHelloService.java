package Dubbo.SPI.service.impl;

import Dubbo.SPI.service.IHelloService;

/**
 * @Auther: minGW
 * @Date: 2018/6/4 19:56
 * @Description:
 */
public class minGWHelloService implements IHelloService {


    @Override
    public void sayHello() {
        System.out.println("minGW");
    }
}
