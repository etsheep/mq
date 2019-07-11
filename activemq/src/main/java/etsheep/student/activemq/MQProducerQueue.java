package etsheep.student.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * Created by etsheep on 2019-7-11.
 */
public class MQProducerQueue {

    public static void main(String[] args) throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + Thread.currentThread().hashCode();
        TextMessage message = session.createTextMessage(text);
        //延迟消息！！！！！！！！！！！！！！
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 1000);
        // Tell the producer to send the message
        System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);

        // Clean up
        session.close();
        connection.close();
    }
}
