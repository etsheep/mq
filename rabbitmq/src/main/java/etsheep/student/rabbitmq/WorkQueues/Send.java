package etsheep.student.rabbitmq.WorkQueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by etsheep on 2019-7-11.
 */
public class Send {

    private final static String TASK_QUEUE_NAME = "durableTask";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
             boolean durable = true;
             channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
             String message = "durableTask First message.";
             channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
             System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
