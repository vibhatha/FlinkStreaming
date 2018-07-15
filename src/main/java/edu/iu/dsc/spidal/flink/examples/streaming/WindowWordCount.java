package edu.iu.dsc.spidal.flink.examples.streaming;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.logging.Logger;

public class WindowWordCount {
    private static final Logger LOG = Logger.getLogger(WindowWordCount.class.getName());

    public static void main(String[] args) throws Exception {

        LOG.info("Streaming Example started ...");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Tuple2<String, Integer>> dataStream = env
                .socketTextStream("localhost", 9999)
                .flatMap(new Splitter())
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        dataStream.print();
        dataStream.writeAsCsv("/home/vibhatha/github/dsc-spidal-forks/FlinkPOC/data/output/streaming-output.csv");
        dataStream.writeAsText("/home/vibhatha/github/dsc-spidal-forks/FlinkPOC/data/output/streaming-output.txt");

        env.execute("Window WordCount");
    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String sentence, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String word: sentence.split(" ")) {
                out.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }

}
