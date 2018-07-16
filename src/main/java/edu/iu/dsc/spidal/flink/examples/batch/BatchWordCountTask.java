package edu.iu.dsc.spidal.flink.examples.batch;

public class BatchWordCountTask {
    public static void main(String[] args) throws Exception {
        BatchWordCountInit batchWordCountInit = new BatchWordCountInit(args);
        batchWordCountInit.initialize();
        batchWordCountInit.read();
        batchWordCountInit.build();
        batchWordCountInit.write();
        batchWordCountInit.execute();
    }
}
