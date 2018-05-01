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

import java.util.logging.LogManager;

/**
 * Helper configuration class. Configures Java logging API 
 * to use SimpleLineFormatter directly form code.
 * @author antons
 */
public class SLConf {

    /**
     * Resets Java logging API for default values.
     */
    public static void reset() {
        LogManager.getLogManager().reset();
    }
    
    /**
     * Provides configuration helper for logger with specified name. 
     * @param loggerName - name of the logger.
     * @return Configuration helper for logger.
     */
    public static LoggerSetup logger(String loggerName) {
        return new LoggerSetup(loggerName);
    }
    
    /**
     * Provides configuration helper for root logger. (logger with name "") 
     * @return Configuration helper for logger.
     */
    public static LoggerSetup rootLogger() {
        return new LoggerSetup("");
    }


    /**
     * Helper method for configuring logging to console only. 
     * All levels are enabled for specified logger and all other
     * loggers are filtered to INFO level.
     * @param fullLogger - name of the logger where all levels are enabled
     */
    public static void simpleConsole(String fullLogger) {
        SLConf.reset();
        SLConf.rootLogger().console().filterAll().pattern()
            .time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(-20, -20)
            .text(" ").message()
            .patternEnd().handler();
        SLConf.rootLogger().info();
        SLConf.logger(fullLogger).all();
    }

    /**
     * Helper method for configuring logging to file and console. 
     * All levels are enabled for specified logger and all other
     * loggers are filtered to INFO level.
     * Everything enabled is logged to specified file and info is 
     * logged to console too.
     * @param fullLogger - name of the logger where all levels are enabled
     * @param fileName - name of the file.
     */
    public static void simpleFile(String fullLogger, String fileName) {
        SLConf.reset();
        SLConf.rootLogger().console().filterWarn().pattern()
            .time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(-20, -20)
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

    /**
     * Helper method for configuring logging to file and console. 
     * All levels are enabled for specified logger and all other
     * loggers are filtered to INFO level.
     * Everything enabled is logged to specified file and info is 
     * logged to console too.
     * @param fullLogger - name of the logger where all levels are enabled
     * @param fileName - name of the file.
     */
    public static void simpleConsoleAndFile(String fullLogger, String fileName) {
        SLConf.reset();
        SLConf.rootLogger().console().filterInfo().pattern()
            .time()
            .text(" ").level(3, -3)
            .text(" ").simpleName(-20, -20)
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
