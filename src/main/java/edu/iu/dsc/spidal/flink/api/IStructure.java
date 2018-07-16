package edu.iu.dsc.spidal.flink.api;

public interface IStructure {
    void initialize();

    void build() throws Exception;

    void execute() throws Exception;

    void read();

    void write() throws Exception;
}
