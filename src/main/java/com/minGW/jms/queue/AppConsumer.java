package com.minGW.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: minGW
 * @Date: 2018/5/23 15:19
 * @Description: 消费者
 */
public class AppConsumer {

    private static final String url = "tcp://localhost:61616";
    private static final String queueName = "queue-test";
    public static void main(String[] args) throws JMSException {

        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2.创建Connection
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话(第一个参数 是否使用失误，第二个 应答模式)
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标
        Destination destination = session.createQueue(queueName);

        //6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7.创建监听器
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接受消息"+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        /*
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

            }
        });
         */


        //8.关闭连接,接受信息是一个异步的过程，这里如果直接关闭，可能还没消费，连接就关闭了
        //connection.close();

    }
}
