package edu.iu.dsc.spidal.flink.examples.batch;

import edu.iu.dsc.spidal.flink.api.EnvironmentalInit;
import edu.iu.dsc.spidal.flink.api.IStructure;
import edu.iu.dsc.spidal.flink.api.InitUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.aggregation.Aggregations;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.logging.Logger;

public class BatchWordCountInit implements IStructure {
    private static final Logger LOG = Logger.getLogger(BatchWordCountInit.class.getName());
    private EnvironmentalInit enviInit = null;
    private String[] args = null;
    private  DataSet<String> text = null;
    private DataSet<Tuple2<String, Integer>> counts = null;

    public BatchWordCountInit(String args[]) {
        this.args = args;
    }

    @Override
    public void initialize() {
        if (InitUtil.argCheck(this.args)) {
            enviInit = new EnvironmentalInit(this.args);
        } else {
            LOG.info("Invalid Arguments");
            System.exit(0);
        }
        enviInit.init();
    }


    @Override
    public void build() {
        this.counts =
                // split up the lines in pairs (2-tuples) containing: (word,1)
                this.text.flatMap(new Splitter())
                        // group by the tuple field "0" and sum up tuple field "1"
                        .groupBy(0)
                        .aggregate(Aggregations.SUM, 1);
        // emit result
    }

    @Override
    public void execute() throws Exception {
        enviInit.env().execute("Batch WordCount Example");
    }

    @Override
    public void read() {
        this.text = enviInit.env().readTextFile(enviInit.parameterTool().get("input"));
    }

    @Override
    public void write() {
        this.counts.writeAsText(enviInit.parameterTool().get("output"));
    }

    private class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            // normalize and split the line into words
            String[] tokens = value.split("\\W+");

            // emit the pairs
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}
