package etsheep.student.rabbitmq.spring;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by etsheep on 2019-7-11.
 */
@Component
public class FanoutReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void receiveTopic1(String msg) {
        System.out.println("【receiveFanout1监听到消息】" + msg);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void receiveTopic2(String msg) {
        System.out.println("【receiveFanout2监听到消息】" + msg);
    }
}
