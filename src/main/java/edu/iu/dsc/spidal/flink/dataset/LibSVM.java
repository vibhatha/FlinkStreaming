package edu.iu.dsc.spidal.flink.dataset;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.util.Collector;

import java.util.logging.Logger;

public class LibSVM implements FlatMapFunction<String, Tuple1<String>> {
    private static final Logger LOG = Logger.getLogger(LibSVM.class.getName());
    private int features = 1;

    public LibSVM(int features) {
        this.features = features;
    }

    @Override
    public void flatMap(String value, Collector<Tuple1<String>> out) throws Exception {
        LOG.info("Original Value =>" + value);
        String [] values = value.split(" ");
        String classId = values [0];
        String record = "";
        String extension = "";
        String row = "";
        for (int i = 1; i < values.length ; i++) {
            String featureElem [] = values[i].split(":");
            String featureId = featureElem[0];
            String feature = featureElem[1];
            if (i == values.length -1) {
                record += feature;
            } else {
                record += feature + ",";
            }
        }
        LOG.info((values.length - 1) + " ? " + this.features);
        if((values.length-1) < this.features) {
            int diff = this.features - (values.length-1);
            for (int j = 0; j < diff; j++) {
                if(j == diff -1) {
                    extension += ",0";
                } else {
                    extension += "0,";
                }
            }
        }
        record = classId + "," + record + extension;
        LOG.info("Final Record => "+record);
        out.collect(new Tuple1<String>(record));
    }
}
