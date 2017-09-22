package cn.lijie.consume;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import cn.lijie.thread.ConsumerThread;

public class MyConsume {
    //	private static Logger LOG = LoggerFactory.getLogger(MyConsume.class);

    public MyConsume() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "118.89.59.251:9092,118.89.60.46:9092,118.89.62.210:9092");
//		properties.put("bootstrap.servers", "119.29.54.46:9092,119.29.4.166:9092,119.29.11.81:9092");
        //设置不自动提交，自己手动更新offset
        properties.put("enable.auto.commit", "false");
//		properties.put("auto.offset.reset", "latest");
        properties.put("auto.offset.reset", "earliest");
//		properties.put("zookeeper.connect", "118.89.59.251:2188,118.89.60.46:2188,118.89.62.210:2188");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "lijieGroup10123123");
        properties.put("auto.commit.interval.ms", "1000");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        //执行消费
        for (int i = 0; i < 4; i++) {
            executor.execute(new ConsumerThread(new KafkaConsumer<String, String>(properties),
                    "skxbContact", "消费者 " + (i + 1)));
        }
    }
}
