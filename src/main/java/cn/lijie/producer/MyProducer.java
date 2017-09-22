package cn.lijie.producer;

import cn.lijie.single.KafkaProduceClient;

public class MyProducer {

    public static void main(String[] args) throws Exception {
        produce("skxbContact");
    }

    public static void produce(String topic) throws Exception {

        for (int i = 0; i < 1000; i++) {
            KafkaProduceClient.getInstance().sendKafkaMessage(topic, i + "hello 你好啊");
        }
        Thread.sleep(10000);

    }
}
