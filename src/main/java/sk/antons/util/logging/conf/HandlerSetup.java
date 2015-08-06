/*
 * Copyright 2015 Anton Straka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sk.antons.util.logging.conf;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.antons.util.logging.SimpleLineFormatter;

/**
 * File or console handler configuration helper class.
 * @author antons
 */
public class HandlerSetup {
    protected LoggerSetup loggerSetup;
    protected Handler handler;

    public HandlerSetup(LoggerSetup loggerSetup, Handler handler) {
        this.loggerSetup = loggerSetup;
        this.handler = handler;
    }
    
    /**
     * Provides helper class for pattern definition. It is necessary to call
     * HandlerFormatSetup.patternEnd() to mark end of the pattern.
     * @return pattern configuration helper class.
     */
    public HandlerFormatSetup pattern() {
        return new HandlerFormatSetup(this);
    }

    /**
     * Creates pattern from provided string. 
     * @param pattern for SimpleLineFormatter
     * @return handler setup helper
     */
    public HandlerSetup pattern(String pattern) {
        if(pattern == null) pattern = "${date} ${time} ${level:3:-3} ${sname:-30:-30}: ${message}";
        if(handler != null) {
            SimpleLineFormatter slf = new SimpleLineFormatter(pattern);
            handler.setFormatter(slf);
        }
        return this;        
    }
    
    /**
     * Sets handler filter to all levels.
     * @return handler setup helper
     */
    public HandlerSetup filterAll() {if(handler != null) handler.setLevel(Level.ALL); return this;}
    /**
     * Sets handler filter to config level.
     * @return handler setup helper
     */
    public HandlerSetup filterConfig() {if(handler != null) handler.setLevel(Level.CONFIG); return this;}
    /**
     * Sets handler filter to fine level.
     * @return handler setup helper
     */
    public HandlerSetup filterFine() {if(handler != null) handler.setLevel(Level.FINE); return this;}
    /**
     * Sets handler filter to finer level.
     * @return handler setup helper
     */
    public HandlerSetup filterFiner() {if(handler != null) handler.setLevel(Level.FINER); return this;}
    /**
     * Sets handler filter to finest level.
     * @return handler setup helper
     */
    public HandlerSetup filterFinest() {if(handler != null) handler.setLevel(Level.FINEST); return this;}
    /**
     * Sets handler filter to info level.
     * @return handler setup helper
     */
    public HandlerSetup filterInfo() {if(handler != null) handler.setLevel(Level.INFO); return this;}
    /**
     * Sets handler filter to no level.
     * @return handler setup helper
     */
    public HandlerSetup filterOff() {if(handler != null) handler.setLevel(Level.OFF); return this;}
    /**
     * Sets handler filter to severe level.
     * @return handler setup helper
     */
    public HandlerSetup filterSevere() {if(handler != null) handler.setLevel(Level.SEVERE); return this;}
    /**
     * Sets handler filter to warning level.
     * @return handler setup helper
     */
    public HandlerSetup filterWarn() {if(handler != null) handler.setLevel(Level.WARNING); return this;}
    
    /**
     * Sets handler encoding.
     * @param value - encoding name
     * @return handler setup helper
     */
    public HandlerSetup encoding(String value) {
        try {
            if(handler != null) handler.setEncoding(value);        
        } catch(Exception e) {
            System.out.println(" Unable to set encoding " + value + " because of "  + e);
        }
        return this;
    }

    /**
     * Ends handler configuration. it is necessary to call this method to apply 
     * all configuration settings in this object.
     * @return logger setup helper
     */
    public LoggerSetup handler() {
        Logger logger = Logger.getLogger(loggerSetup.name());
        logger.addHandler(handler);
        return loggerSetup;
    }

}
