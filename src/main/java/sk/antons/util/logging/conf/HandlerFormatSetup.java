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

import sk.antons.util.logging.SimpleLineFormatter;

/**
 * Pattern string helper class for handler configuration.
 * @author antons
 */
public class HandlerFormatSetup {
    protected HandlerSetup handlerSetup;
    private StringBuilder pattern = new StringBuilder();
    
    public HandlerFormatSetup(HandlerSetup handlerSetup) {
        this.handlerSetup = handlerSetup;
    }

    /**
     * Marks end of pattern configuration. necessary to call to apply all 
     * pattern configuration steps.
     * @return handler setup helper
     */
    public HandlerSetup patternEnd() {
        if(handlerSetup.handler != null) {
            if(pattern.length() > 0) {
                SimpleLineFormatter slf = new SimpleLineFormatter(pattern.toString());
                handlerSetup.handler.setFormatter(slf); 
            }
        }
        return handlerSetup;
    }
    
    /**
     * Adds constant text appender. 
     * @param text - text to be appended.
     * @return  pattern setup helper
     */
    public HandlerFormatSetup text(String text) {pattern.append(text); return this;}
    

    /**
     * Adds name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup name() {pattern.append("${name}"); return this;}
    /**
     * Adds name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup name(int max) {pattern.append("${name:"+max+"}"); return this;}
    /**
     * Adds name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup name(int max, int min) {pattern.append("${name:"+max+":"+min+"}"); return this;}

    /**
     * Adds simple name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simplaName() {pattern.append("${sname}"); return this;}
    /**
     * Adds simple name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simpleName(int max) {pattern.append("${sname:"+max+"}"); return this;}
    /**
     * Adds simple name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simpleName(int max, int min) {pattern.append("${sname:"+max+":"+min+"}"); return this;}

    /**
     * Adds level appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup level() {pattern.append("${level}"); return this;}
    /**
     * Adds level appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup level(int max) {pattern.append("${level:"+max+"}"); return this;}
    /**
     * Adds level appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup level(int max, int min) {pattern.append("${level:"+max+":"+min+"}"); return this;}
    
    /**
     * Adds date appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup date() {pattern.append("${date}"); return this;}
    
    /**
     * Adds time appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup time() {pattern.append("${time}"); return this;}

    /**
     * Adds formated time appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup formatedTime(String format) {pattern.append("${ftime:0:0:"+format+"}"); return this;}


    /**
     * Adds class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup className() {pattern.append("${class}"); return this;}
    /**
     * Adds class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup className(int max) {pattern.append("${class:"+max+"}"); return this;}
    /**
     * Adds class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup className(int max, int min) {pattern.append("${class:"+max+":"+min+"}"); return this;}

    /**
     * Adds simple class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simpleClassName() {pattern.append("${sclass}"); return this;}
    /**
     * Adds simple class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simpleClassName(int max) {pattern.append("${sclass:"+max+"}"); return this;}
    /**
     * Adds simple class appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup simpleClassName(int max, int min) {pattern.append("${sclass:"+max+":"+min+"}"); return this;}
    
    /**
     * Adds message appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup message() {pattern.append("${message}"); return this;}
    /**
     * Adds message appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup message(int max) {pattern.append("${message:"+max+"}"); return this;}
    /**
     * Adds message appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup message(int max, int min) {pattern.append("${message:"+max+":"+min+"}"); return this;}
    
    /**
     * Adds method appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup method() {pattern.append("${method}"); return this;}
    /**
     * Adds method appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup method(int max) {pattern.append("${method:"+max+"}"); return this;}
    /**
     * Adds method appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup method(int max, int min) {pattern.append("${method:"+max+":"+min+"}"); return this;}
    
    /**
     * adds thread name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup thread() {pattern.append("${thread}"); return this;}
    /**
     * adds thread name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup thread(int max) {pattern.append("${thread:"+max+"}"); return this;}
    /**
     * adds thread name appender. 
     * @return  pattern setup helper
     */
    public HandlerFormatSetup thread(int max, int min) {pattern.append("${thread:"+max+":"+min+"}"); return this;}

}
