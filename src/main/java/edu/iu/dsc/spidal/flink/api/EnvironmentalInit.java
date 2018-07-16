package edu.iu.dsc.spidal.flink.api;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;

public class EnvironmentalInit implements IStreamingEnvironment {

    private String [] args = null;
    private final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    private ParameterTool params = null;

    public EnvironmentalInit(String [] args) {
        this.args = args;
    }

    @Override
    public void init() {
        final ParameterTool params = ParameterTool.fromArgs(this.args);
        this.env.getConfig().setGlobalJobParameters(params);
        this.params = params;
    }

    @Override
    public void execute() throws Exception {
        this.env.execute();
    }

    public ExecutionEnvironment env() {
        return this.env;
    }

    public String [] args() {
        return this.args;
    }

    public ParameterTool parameterTool() {
        return this.params;
    }
}
