package etsheep.student.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by etsheep on 2019-7-11.
 */
public class MQProducerTopic {

    public static void main (String[] args) throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Topic topic = session.createTopic("test-topic");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + Thread.currentThread().hashCode();
        TextMessage message = session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);

        // Clean up
        session.close();
        connection.close();
    }
}
