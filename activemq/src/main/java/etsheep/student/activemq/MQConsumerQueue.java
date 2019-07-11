package etsheep.student.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * Created by etsheep on 2019-7-11.
 * queue模式，一对一，消息只被一个消费者消费
 */
public class MQConsumerQueue implements ExceptionListener{

    public static void main(String[] args) throws JMSException, IOException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(new MQConsumerQueue());

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");
        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
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

    public void onException(JMSException exception) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}
