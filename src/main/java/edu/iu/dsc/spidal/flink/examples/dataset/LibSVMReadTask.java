package edu.iu.dsc.spidal.flink.examples.dataset;

public class LibSVMReadTask {
    public static void main(String[] args) throws Exception {
        LibSVMReadInit libSVMReadInit = new LibSVMReadInit(args);
        libSVMReadInit.initialize();
        libSVMReadInit.read();
        libSVMReadInit.build();
        libSVMReadInit.write();
        libSVMReadInit.execute();
    }
}
