package etsheep.student.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by etsheep on 2019-7-11.
 * 发布订阅模式，一对多，消息被所有订阅者消费
 */
public class MQConsumerTopic {

    public static void main (String[] args) throws IOException, JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(new MQConsumerQueue());

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Topic topic = session.createTopic("test-topic");
        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(topic);
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
