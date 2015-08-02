/*
 * 
 */
package sk.antons.util.logging;
import sk.antons.util.logging.conf.SLConf;
import java.util.logging.Logger;

/**
 *
 * @author antons
 */
public class ConfTestik {
    private static Logger log = Logger.getLogger(ConfTestik.class.getName());
    
    public static void main(String[] params) {
        SLConf.reset();
        SLConf.rootLogger().info();
        SLConf.rootLogger().console().pattern()
            .text(", name: ").name(50, -50)
            .text(", sname: ").simplaName()
            .text(", class: ").className()
            .text(", sclass: ").simpleClassName()
            .text(", date: ").date()
            .text(", time: ").time()
            .text(", ftime: ").formatedTime("dd-mm.yyyy")
            .text(", level: ").level(10, -10)
            .text(", method: ").method()
            .text(", thread: ").thread()
            .text(", message: ").message()
            .patternEnd().handler();
        SLConf.logger("sk.antons").all();

        SLConf.simpleConsole("sk.antons");
        SLConf.simpleFile("sk.antons", "target/example.log");
        log.fine("The example log entry");
    }   
}
