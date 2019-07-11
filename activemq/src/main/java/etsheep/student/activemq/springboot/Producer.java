package etsheep.student.activemq.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by etsheep on 2019-7-11.
 */

@RestController
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/sendMsg")
    public void sendMsg(String msg) {
        jmsMessagingTemplate.convertAndSend("my_msg", msg);
        System.out.println("msg发送成功");
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("mobile", "13888888888");
        map.put("content", "王总喜提兰博基尼");
        jmsMessagingTemplate.convertAndSend("my_map", map);
        System.out.println("map发送成功");
        jmsMessagingTemplate.convertAndSend("a", map, new MessagePostProcessor() {
            @Override
            public Message<?> postProcessMessage(Message<?> message) {
                return null;
            }
        });
    }
}
