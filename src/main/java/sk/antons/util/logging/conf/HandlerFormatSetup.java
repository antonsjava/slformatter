/*
 * 
 */
package sk.antons.util.logging.conf;

import sk.antons.util.logging.SimpleLineFormatter;

/**
 *
 * @author antons
 */
public class HandlerFormatSetup {
    protected HandlerSetup handlerSetup;
    private StringBuilder pattern = new StringBuilder();
    
    public HandlerFormatSetup(HandlerSetup handlerSetup) {
        this.handlerSetup = handlerSetup;
    }

    public HandlerSetup patternEnd() {
        if(handlerSetup.handler != null) {
            if(pattern.length() > 0) {
                SimpleLineFormatter slf = new SimpleLineFormatter(pattern.toString());
                handlerSetup.handler.setFormatter(slf); 
            }
        }
        return handlerSetup;
    }
    
    public HandlerFormatSetup text(String text) {pattern.append(text); return this;}
    

    public HandlerFormatSetup name() {pattern.append("${name}"); return this;}
    public HandlerFormatSetup name(int max) {pattern.append("${name:"+max+"}"); return this;}
    public HandlerFormatSetup name(int max, int min) {pattern.append("${name:"+max+":"+min+"}"); return this;}

    public HandlerFormatSetup simplaName() {pattern.append("${sname}"); return this;}
    public HandlerFormatSetup simpleName(int max) {pattern.append("${sname:"+max+"}"); return this;}
    public HandlerFormatSetup simpleName(int max, int min) {pattern.append("${sname:"+max+":"+min+"}"); return this;}

    public HandlerFormatSetup level() {pattern.append("${level}"); return this;}
    public HandlerFormatSetup level(int max) {pattern.append("${level:"+max+"}"); return this;}
    public HandlerFormatSetup level(int max, int min) {pattern.append("${level:"+max+":"+min+"}"); return this;}
    
    public HandlerFormatSetup date() {pattern.append("${date}"); return this;}
    
    public HandlerFormatSetup time() {pattern.append("${time}"); return this;}

    public HandlerFormatSetup formatedTime(String format) {pattern.append("${ftime:0:0:"+format+"}"); return this;}

    public HandlerFormatSetup className() {pattern.append("${class}"); return this;}
    public HandlerFormatSetup className(int max) {pattern.append("${class:"+max+"}"); return this;}
    public HandlerFormatSetup className(int max, int min) {pattern.append("${class:"+max+":"+min+"}"); return this;}

    public HandlerFormatSetup simpleClassName() {pattern.append("${sclass}"); return this;}
    public HandlerFormatSetup simpleClassName(int max) {pattern.append("${sclass:"+max+"}"); return this;}
    public HandlerFormatSetup simpleClassName(int max, int min) {pattern.append("${sclass:"+max+":"+min+"}"); return this;}
    
    public HandlerFormatSetup message() {pattern.append("${message}"); return this;}
    public HandlerFormatSetup message(int max) {pattern.append("${message:"+max+"}"); return this;}
    public HandlerFormatSetup message(int max, int min) {pattern.append("${message:"+max+":"+min+"}"); return this;}
    
    public HandlerFormatSetup method() {pattern.append("${method}"); return this;}
    public HandlerFormatSetup method(int max) {pattern.append("${method:"+max+"}"); return this;}
    public HandlerFormatSetup method(int max, int min) {pattern.append("${method:"+max+":"+min+"}"); return this;}
    
    public HandlerFormatSetup thread() {pattern.append("${thread}"); return this;}
    public HandlerFormatSetup thread(int max) {pattern.append("${thread:"+max+"}"); return this;}
    public HandlerFormatSetup thread(int max, int min) {pattern.append("${thread:"+max+":"+min+"}"); return this;}

}
