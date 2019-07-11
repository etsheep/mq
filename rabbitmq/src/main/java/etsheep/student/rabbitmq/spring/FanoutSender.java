package etsheep.student.rabbitmq.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by etsheep on 2019-7-11.
 */

@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", msg);
    }
}

