/*
 * 
 */
package sk.antons.util.logging.conf;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.antons.util.logging.SimpleLineFormatter;

/**
 *
 * @author antons
 */
public class HandlerSetup {
    protected LoggerSetup loggerSetup;
    protected Handler handler;

    public HandlerSetup(LoggerSetup loggerSetup, Handler handler) {
        this.loggerSetup = loggerSetup;
        this.handler = handler;
    }

    public HandlerFormatSetup pattern() {
        return new HandlerFormatSetup(this);
    }

    public HandlerSetup pattern(String pattern) {
        if(pattern == null) pattern = "${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message}";
        if(handler != null) {
            SimpleLineFormatter slf = new SimpleLineFormatter(pattern);
            handler.setFormatter(slf);
        }
        return this;        
    }
    
    public HandlerSetup filterAll() {if(handler != null) handler.setLevel(Level.ALL); return this;}
    public HandlerSetup filterConfig() {if(handler != null) handler.setLevel(Level.CONFIG); return this;}
    public HandlerSetup filterFine() {if(handler != null) handler.setLevel(Level.FINE); return this;}
    public HandlerSetup filterFiner() {if(handler != null) handler.setLevel(Level.FINER); return this;}
    public HandlerSetup filterFinest() {if(handler != null) handler.setLevel(Level.FINEST); return this;}
    public HandlerSetup filterInfo() {if(handler != null) handler.setLevel(Level.INFO); return this;}
    public HandlerSetup filterOff() {if(handler != null) handler.setLevel(Level.OFF); return this;}
    public HandlerSetup filterSevere() {if(handler != null) handler.setLevel(Level.SEVERE); return this;}
    public HandlerSetup filterWarn() {if(handler != null) handler.setLevel(Level.WARNING); return this;}
    
    public HandlerSetup encoding(String value) {
        try {
            if(handler != null) handler.setEncoding(value);        
        } catch(Exception e) {
            System.out.println(" Unable to set encoding " + value + " because of "  + e);
        }
        return this;
    }

    public LoggerSetup handler() {
        Logger logger = Logger.getLogger(loggerSetup.name());
        logger.addHandler(handler);
        return loggerSetup;
    }

}
