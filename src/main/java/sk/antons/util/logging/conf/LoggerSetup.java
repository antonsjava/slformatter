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

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger setup helper class. Use SLConmf.logger() to obtain instance of this 
 * class.
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

    /**
     * Enables all levels for logger.
     * @return this instance
     */
    public LoggerSetup all() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.ALL);
        return this;
    }

    /**
     * Enables config level for logger.
     * @return this instance
     */
    public LoggerSetup config() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.CONFIG);
        return this;
    }

    /**
     * Enables fine level for logger.
     * @return this instance
     */
    public LoggerSetup fine() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINE);
        return this;
    }

    /**
     * Enables finer level for logger.
     * @return this instance
     */
    public LoggerSetup finer() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINER);
        return this;
    }

    /**
     * Enables finest level for logger.
     * @return this instance
     */
    public LoggerSetup finest() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.FINEST);
        return this;
    }

    /**
     * Enables info level for logger.
     * @return this instance
     */
    public LoggerSetup info() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.INFO);
        return this;
    }

    /**
     * Disables all levels for logger.
     * @return this instance
     */
    public LoggerSetup off() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.OFF);
        return this;
    }

    /**
     * Enables severe level for logger.
     * @return this instance
     */
    public LoggerSetup severe() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.SEVERE);
        return this;
    }

    /**
     * Enables warning level for logger.
     * @return this instance
     */
    public LoggerSetup warning() {
        Logger logger = Logger.getLogger(name);
        if(logger != null) logger.setLevel(Level.WARNING);
        return this;
    }

    
    /**
     * Creates console handler for this logger. There is necessary 
     * to call HandlerSetup.handler() after whole handler configuration.
     * @return handler configuration helper
     */
    public HandlerSetup console() {
        return new HandlerSetup(this, new ConsoleHandler());
    }

    /**
     * Creates file handler for this logger. There is necessary 
     * to call HandlerSetup.handler() after whole handler configuration.
     * @param name - name of the file
     * @return handler configuration helper
     */
    public HandlerSetup file(String name) {
        try {
            return new HandlerSetup(this, new FileHandler(name));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
    /**
     * Creates file handler for this logger. There is necessary 
     * to call HandlerSetup.handler() after whole handler configuration.
     * @param name - name of the file
     * @param append - true if output should be appended to existing data
     * @return handler configuration helper
     */
    public HandlerSetup file(String name, boolean append) {
        try {
            return new HandlerSetup(this, new FileHandler(name, append));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
    /**
     * Creates file handler for this logger. There is necessary 
     * to call HandlerSetup.handler() after whole handler configuration.
     * @param name - name pattern of the file (@see FileHandler)
     * @param limit - size limit of file
     * @param count - count limit of file
     * @param append - true if output should be appended to existing data
     * @return handler configuration helper
     */
    public HandlerSetup file(String name, int limit, int count, boolean append) {
        try {
            return new HandlerSetup(this, new FileHandler(name, limit, count, append));
        } catch (Exception e) {
            System.out.println(" Unable to open file  " + name + " because of "  + e);
            return new HandlerSetup(this, new ConsoleHandler());
        }
    }
    
}
