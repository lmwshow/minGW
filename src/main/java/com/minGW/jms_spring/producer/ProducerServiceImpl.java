package com.minGW.jms_spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * @Auther: minGW
 * @Date: 2018/5/23 16:07
 * @Description:
 */
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

    //用Resource注入，可以通过name来实现注入不同目的地
    @Resource(name = "queueDestination")
    Destination destination;

//    @Resource(name = "topDestination")
//    Destination destination;

    @Override
    public void sendMessage(String message) {

        //使用jmsTemplate发送消息
        jmsTemplate.send(destination,session -> {
            //创建消息
            TextMessage textMessage = session.createTextMessage(message);

            System.out.println("发送消息"+ textMessage.getText());
            return textMessage;
        });

    }
}
