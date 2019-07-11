package etsheep.student.activemq.springboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by etsheep on 2019-7-11.
 */

@Component
public class Consumer {

    @JmsListener(destination = "my_msg")
    public void readMsg(String text) {
        System.out.println("接收到消息：" + text);
    }

    @JmsListener(destination = "my_map")
    public void readMap(Map map) {
        System.out.println(map);
    }
}
