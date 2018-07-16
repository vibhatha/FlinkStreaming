package edu.iu.dsc.spidal.flink.examples.dataset;

import edu.iu.dsc.spidal.flink.api.EnvironmentalInit;
import edu.iu.dsc.spidal.flink.api.IStructure;
import edu.iu.dsc.spidal.flink.api.InitUtil;
import edu.iu.dsc.spidal.flink.dataset.LibSVM;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple1;

import java.util.List;
import java.util.logging.Logger;

public class LibSVMReadInit implements IStructure {

    private static final Logger LOG = Logger.getLogger(LibSVMReadInit.class.getName());
    private EnvironmentalInit enviInit = null;
    private String[] args = null;
    private DataSet<String> text = null;
    private FlatMapOperator<String, Tuple1<String>> row = null;

    public LibSVMReadInit(String [] args) {
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
    public void build() throws Exception {
        int features = Integer.parseInt(enviInit.parameterTool().get("features"));
        this.row = this.text.flatMap(new LibSVM(features));
    }

    @Override
    public void execute() throws Exception {
       enviInit.env().execute("LibSVM Dataset Example");
    }

    @Override
    public void read() {
        this.text = enviInit.env().readTextFile(enviInit.parameterTool().get("input"));
    }

    @Override
    public void write() throws Exception {
        this.row.writeAsText(enviInit.parameterTool().get("output"));
    }
}
