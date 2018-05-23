package com.minGW.jms_spring.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: minGW
 * @Date: 2018/5/23 16:41
 * @Description: 消费者启动器
 */
public class AppConsumer {

    public static void main(String[] args){

        //直接运行就可以了，因为消费者所有的逻辑都已经托管给Spring
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

        //消息消费是一个异步过程，直接关闭context 可能会导致接受不全

    }
}
