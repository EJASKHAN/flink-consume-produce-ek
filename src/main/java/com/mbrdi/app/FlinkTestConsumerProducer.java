package com.mbrdi.app;//Copyright (c) Microsoft Corporation. All rights reserved.
//Licensed under the MIT License.

import com.mbrdi.operator.StreamMapFunctionOne;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class FlinkTestConsumerProducer {

    private static final String CONSUMER_TOPIC = "flink-source";
    private static final String PRODUCER_TOPIC = "flink-sink";
    private static final String FILE_PATH = "src/main/resources/consumer.config";

    public static void main(String... args) {
        try {
            //Load properties from config file
            Properties properties = new Properties();
            properties.load(new FileReader(FILE_PATH));
            
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
           DataStream<String> stream = env.addSource(new FlinkKafkaConsumer<>(CONSUMER_TOPIC, new SimpleStringSchema(), properties));
            stream.print();

            DataStream<String> streamTrans = stream.map(new StreamMapFunctionOne()).uid("StreamMapFunctionOne");
            FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(PRODUCER_TOPIC,
                    new SimpleStringSchema(), properties);
            streamTrans.addSink(producer);

          env.execute("Testing flink consumer/producer");

        } catch(FileNotFoundException e){
            System.out.println("FileNoteFoundException: " + e);
        } catch (Exception e){
            System.out.println("Failed with exception " + e);
        }
    }
}
