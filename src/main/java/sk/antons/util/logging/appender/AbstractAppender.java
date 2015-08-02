/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.antons.util.logging.appender;

import java.util.logging.LogRecord;

/**
 *
 * @author antons
 */
public abstract class AbstractAppender {

    protected int minLength = 0;
    protected boolean minLeft = true;
    protected int maxLength = 0;
    protected boolean maxLeft = true;
    protected String param = null;
    

    public AbstractAppender() {
    }

    public void consolidate() {
        if(minLength < 0) {
            minLength = Math.abs(minLength);
            minLeft = true;
        } else {
            minLeft = false;
        }
        if(maxLength < 0) {
            maxLength = Math.abs(maxLength);
            maxLeft = true;
        } else {
            maxLeft = false;
        }
    }

    protected abstract String format(LogRecord record);

    public void append(StringBuilder sb, LogRecord record) {
        String text = format(record);
        append(sb, text);
    }
            
    protected void append(StringBuilder sb, String text) {
        if(text == null) text = "null";
        if(maxLength > 0) {
            if(text.length() > maxLength) {
                if(!maxLeft) text = text.substring(0, maxLength);
                else text = text.substring(text.length() - maxLength);
            }
        }
        
        if(minLength <= 0) {
            sb.append(text);
        } else {
            if(text.length() < minLength) {
                int length = minLength - text.length();
                if(minLeft) {
                    for(int i = 0; i < length; i++) sb.append(' ');
                    sb.append(text);
                } else {
                    sb.append(text);
                    for(int i = 0; i < length; i++) sb.append(' ');
                }
            } else {
                sb.append(text);
            }
        }
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setParam(String param) {
        this.param = param;
    }


}
