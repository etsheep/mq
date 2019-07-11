package etsheep.student.rabbitmq.helloWord;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by etsheep on 2019-7-11.
 */
public class Send {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
             channel.queueDeclare(QUEUE_NAME, false, false, false, null);
             String message = "Hello World!";
             channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
             System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
