package com.minGW.jms_spring.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: minGW
 * @Date: 2018/5/23 16:15
 * @Description: 生产者启动器
 */
public class AppProducer {

    public static void main(String[] args){

        //close 只有ClassPathXmlApplicationContext有， ApplicationContext没有
        ApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");

        ProducerService service = context.getBean(ProducerService.class);

        for (int i = 0 ; i < 100 ; i++)
            service.sendMessage("test" + i);

        //会自动清理资源
        ((ClassPathXmlApplicationContext)context).close();
    }
}
