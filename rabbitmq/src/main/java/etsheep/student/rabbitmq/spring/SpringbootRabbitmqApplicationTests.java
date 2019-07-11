package etsheep.student.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by etsheep on 2019-7-11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private FanoutSender fanoutSender;
//    @Autowired
//    private TopicSender topicSender;
//    @Autowired
//    private DirectSender directSender;

    /**
     * Fanout测试
     * @throws Exception
     */
    @Test
    public void testFanout() throws Exception {
        fanoutSender.send("hhh");
    }



    /**
     * TOPIC测试
     * @throws Exception
     */
//    @Test
//    public void testTopic() throws Exception {
//        topicSender.send(user);
//    }

    /**
     * DIRECT测试
     * @throws Exception
     */
//    @Test
//    public void testDirect() throws Exception {
//        directSender.send(user);
//    }

}