package com.minGW.jms_spring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: minGW
 * @Date: 2018/5/23 16:37
 * @Description: 消息监听者
 */
public class ConsumerMessageListener implements MessageListener{


    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            System.out.println("接受消息"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
