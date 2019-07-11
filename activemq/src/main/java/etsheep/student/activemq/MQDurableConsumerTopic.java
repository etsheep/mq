package etsheep.student.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by etsheep on 2019-7-11.
 */
public class MQDurableConsumerTopic {

    public static void main (String[] args) throws IOException, JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("10086");
        connection.start();

        connection.setExceptionListener(new MQConsumerQueue());

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Topic topic = session.createTopic("test-topic");
        // Create a MessageConsumer from the Session to the Topic or Queue
        //持久化订阅者，离线后再上线可以接收到离线后的消息
        MessageConsumer consumer = session.createDurableSubscriber(topic, "aaa");
        // Wait for a message
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = null;
                    try {
                        text = textMessage.getText();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
