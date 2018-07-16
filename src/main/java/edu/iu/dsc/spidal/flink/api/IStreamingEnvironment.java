package edu.iu.dsc.spidal.flink.api;

public interface IStreamingEnvironment {

    void init();

    void execute() throws Exception;
}
