/*
 * 
 */
package sk.antons.util.logging.conf;

import java.util.logging.LogManager;

/**
 *
 * @author antons
 */
public class SLConf {
    public static void reset() {
        LogManager.getLogManager().reset();
    }
    
    public static LoggerSetup logger(String loggerName) {
        return new LoggerSetup(loggerName);
    }
    
    public static LoggerSetup rootLogger() {
        return new LoggerSetup("");
    }

    public static void simpleConsole(String fullLogger) {
        SLConf.reset();
        SLConf.rootLogger().console().filterAll().pattern()
            .time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(20, -20)
            .text(" ").message()
            .patternEnd().handler();
        SLConf.rootLogger().info();
        SLConf.logger(fullLogger).all();
    }


    public static void simpleFile(String fullLogger, String fileName) {
        SLConf.reset();
        SLConf.rootLogger().console().filterWarn().pattern()
            .time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(20, -20)
            .text(" ").message()
            .patternEnd().handler();
        SLConf.rootLogger().file(fileName).filterAll().pattern()
            .date()
            .text(" ").time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(20, -20)
            .text(" ").message()
            .patternEnd().handler();
        SLConf.rootLogger().info();
        SLConf.logger(fullLogger).all();
    }

    
    
    
}
