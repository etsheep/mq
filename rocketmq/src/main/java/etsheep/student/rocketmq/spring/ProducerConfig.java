package etsheep.student.rocketmq.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by etsheep on 2019-7-12.
 */
@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@Data
public class ProducerConfig {
    private String namesrvAddr;

    private String groupName;
}
