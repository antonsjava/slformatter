/*
 * 
 */
package sk.antons.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import sk.antons.util.logging.conf.SLConf;

/**
 *
 * @author antons
 */
public class FncTesitk {
    
    public static void main(String[] argv) {
        SLConf.simpleConsole("sk.antons");
        Logger.getLogger("package").severe("test");
        Logger.getLogger("package").severe("test2");
        Logger.getLogger("pa.ck.age").info("test3");
        Logger.getLogger("package.s.d").severe("test4");
        Logger.getLogger("package.lebo.toto.je.dlhe.preto").warning("test5");
        Logger.getLogger("package").severe("test6");
        Logger.getLogger("package").log(Level.SEVERE, "test6", new IllegalArgumentException("testing"));
        Logger.getLogger(SimpleLineFormatter.class.getName()).severe("test6");

        Logger log = Logger.getLogger(SimpleLineFormatter.class.getName());

        long starttime = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            log.info("test ");
        }
 
        long endtime = System.currentTimeMillis();
        log.severe("time: " + (endtime - starttime));
    }
    
}
