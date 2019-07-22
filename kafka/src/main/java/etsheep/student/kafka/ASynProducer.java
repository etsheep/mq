package etsheep.student.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by etsheep on 2019-7-16.
 */
public class ASynProducer {
    private static Properties getProps(){
        Properties props =  new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        props.put("acks", "all"); // 发送所有ISR
        props.put("retries", 2); // 重试次数
        props.put("batch.size", 16384); // 批量发送大小
        props.put("buffer.memory", 33554432); // 缓存大小，根据本机内存大小配置
        props.put("linger.ms", 1000); // 发送频率，满足任务一个条件发送
        props.put("client.id", "producer-asyn-1"); // 发送端id,便于统计
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //=1，发送一批消息后要等到确认结果后才能发送下一批，保证顺序性；> 1 发送一批消息后不需等待确认结果即可再发送下一批，如果前一批处理失败，
        // 可能会导致下一批消息先处理，影响顺序性
        props.put("max.in.flight.requests.per.connection", "1");
        return props;
    }

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProps());
        for(int i=0; i< 1000; i++){
            ProducerRecord<String, String> record = new ProducerRecord<>("test-asyn", "topic_"+i,"test-asyn-"+i);
            // 相比同步发送，异步发送需要传入callback,发送结果回来回调callback方法
            producer.send(record, (recordMetadata, e) -> {
                if(e != null){
                    System.out.println("发送失败！");
                    e.printStackTrace();
                }else {
                    System.out.println("发送成功！");
                    System.out.println("topic:"+recordMetadata.topic());
                    System.out.println("partition:"+recordMetadata.partition());
                    System.out.println("offset:"+recordMetadata.offset());
                }
            });
        }

        producer.flush();
        producer.close();
    }
}
