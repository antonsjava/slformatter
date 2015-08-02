/*
 * 
 */
package sk.antons.util.logging.conf;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antons
 */
public class LoggerSetup {

    private String name;

    public LoggerSetup(String name) {
        this.name = name;
    }

    protected String name(){
        return name;
    };

    public LoggerSetup all() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.ALL);
        return this;
    }

    public LoggerSetup config() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.CONFIG);
        return this;
    }

    public LoggerSetup fine() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINE);
        return this;
    }

    public LoggerSetup finer() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINER);
        return this;
    }

    public LoggerSetup finest() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINEST);
        return this;
    }

    public LoggerSetup info() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.INFO);
        return this;
    }

    public LoggerSetup off() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.OFF);
        return this;
    }

    public LoggerSetup severe() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.SEVERE);
        return this;
    }

    public LoggerSetup warning() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.WARNING);
        return this;
    }

    
    public HandlerSetup console() {
        return new HandlerSetup(this, new ConsoleHandler());
    }

    public HandlerSetup file(String name) {
        try {
            return new HandlerSetup(this, new FileHandler(name));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
    public HandlerSetup file(String name, boolean append) {
        try {
            return new HandlerSetup(this, new FileHandler(name, append));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
    public HandlerSetup file(String name, int limit, int count, boolean append) {
        try {
            return new HandlerSetup(this, new FileHandler(name, limit, count, append));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
}
