package edu.iu.dsc.spidal.flink.api;

public class InitUtil {

    public static boolean argCheck(String args []) {
        if (args.length > 0) {
            return true;
        } else {
          return false;
        }
    }

}
